/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.views;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.tuneasy.services.UserService;
import tn.esprit.tuneasy.utils.LoginSession;

/**
 *
 * @author Maya
 */
public class ProfilForm extends Form {
Form current;
    UserService userService = new UserService();

    public ProfilForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Profil");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        userService.findUser(LoginSession.emailUser);
        Label fullNameLabel = new Label("Full Name: "+userService.findUser(LoginSession.emailUser).getNom() + " " + userService.findUser(LoginSession.emailUser).getPrenom());
        Label emailLabel = new Label("E-mail: "+userService.findUser(LoginSession.emailUser).getEmail());
        Label idLabel = new Label("ID: "+userService.findUser(LoginSession.emailUser).getId());
        Button logoutButton = new Button("Logout");
        logoutButton.addActionListener(evt -> {
            LoginSession.loggedUser=0;
            LoginSession.emailUser="";
            new LoginForm().show();
        });
        addAll(idLabel, fullNameLabel, emailLabel, logoutButton);
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Competitions", null, (evt) -> {
            new CompetitionForm(0).show();
        });
        getToolbar().addCommandToLeftSideMenu("My Participation", null, (evt) -> {
            new CompetitionForm(1).show();
        });
        getToolbar().addCommandToLeftSideMenu("Profil", null, (evt) -> {
            new ProfilForm().show();
        });
    }
}

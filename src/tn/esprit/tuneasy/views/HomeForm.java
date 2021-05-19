/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.views;

import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.tuneasy.utils.LoginSession;

/**
 *
 * @author Maya
 */
public class HomeForm extends Form {
     Form current;


    public HomeForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("WELCOME");
        setLayout(BoxLayout.y());
        setScrollableY(false);
        //getStyle().setBgColor(0x99CCCC);
        /* *** *YOUR CODE GOES HERE* *** */

        addAll(new Label("Choose an option from side menu"));

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
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt) -> {
            LoginSession.loggedUser=0;
            LoginSession.emailUser="";
            new LoginForm().show();
        });
    }
}

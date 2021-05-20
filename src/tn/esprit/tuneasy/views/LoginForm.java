/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.views;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.tuneasy.entities.User;
import tn.esprit.tuneasy.services.UserService;
import tn.esprit.tuneasy.utils.LoginSession;

/**
 *
 * @author Maya
 */
public class LoginForm extends Form {
     Form current;
    UserService userService = new UserService();

    public LoginForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Login");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        TextField emailTextField = new TextField("", "Login", 20, TextField.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 20, TextField.PASSWORD);
        Label emailLabel = new Label("E-mail: ");
        Label passwordLabel = new Label("Password: ");
        Button loginButton = new Button("LOGIN");
        loginButton.addActionListener(evt -> {
            if (!emailTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
                User user = new User();
                user.setEmail(emailTextField.getText());
                user.setPassword(passwordTextField.getText());
                if (userService.login(user)) {
                    LoginSession.emailUser = emailTextField.getText();
                   LoginSession.loggedUser =  userService.findUser(emailTextField.getText()).getId();
                    new HomeForm().show();
                } else {
                    System.out.println("user doesnt exist");

                    Dialog.show("NOT FOUND", "User does not exist", "OK", null);
                }
            } else {
                System.out.println("Fill in the blanks");

                Dialog.show("NOT FOUND", "Fill in the blanks", "OK", null);
            }
        });
        addAll(emailLabel, emailTextField, passwordLabel, passwordTextField, loginButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Signup", null, (evt) -> {
            new SignupForm().show();
        });

    }
}

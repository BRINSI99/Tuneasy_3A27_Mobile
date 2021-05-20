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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.tuneasy.entities.User;
import tn.esprit.tuneasy.services.UserService;

/**
 *
 * @author Maya
 */
public class SignupForm extends Form{
Form current;

    UserService userService = new UserService();

    public SignupForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Login");
        setLayout(BoxLayout.yCenter());
        /* *** *YOUR CODE GOES HERE* *** */
        Label emailLabel = new Label("E-mail");
        Label passwordLabel = new Label("Password");
        Label rePasswordLabel = new Label("Re-type Password");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        TextField emailTextField = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 20, TextArea.PASSWORD);
        TextField rePasswordTextField = new TextField("", "Re-type Password", 20, TextArea.PASSWORD);
        TextField firstNameTextField = new TextField("", "First Name", 20, TextArea.ANY);
        TextField lastNameTextField = new TextField("", "Last Name", 20, TextArea.ANY);
         Button registerButton = new Button("Register");
        registerButton.addActionListener(evt -> {
            if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
                if (!emailTextField.getText().equals("") && !firstNameTextField.getText().equals("") && !lastNameTextField.getText().equals("") ) {
                    User user = new User(emailTextField.getText(), passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), "Client");
//                    if (userService.login(user)) {
                        userService.register(user);
//                    } else {
//                        Dialog.show("Exist", "User exists", null, "OK");
//
//                    }
                    new LoginForm().show();
                } else {
                    Dialog.show("Invalid Input", "Please check your inputs", null, "OK");
                }
            } else {
                Dialog.show("Invalid Input", "Password doesnt match", null, "OK");
            }
        });
        addAll(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, emailLabel, emailTextField, passwordLabel, passwordTextField, rePasswordLabel, rePasswordTextField, registerButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Signup", null, (evt) -> {
            new LoginForm().show();
        });

    }
}

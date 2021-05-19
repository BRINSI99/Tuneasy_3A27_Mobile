/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author fakhreddine
 */
public class FormSignUp extends Form {
    


    public FormSignUp(Form previous) {
        
       
        
        setTitle("Sign Up");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom");
        TextField tfPrenom = new TextField("","Prenom");
        TextField tfLogin = new TextField("","Login");
        TextField tfPassword = new TextField("","Password");
        tfPassword.setConstraint(TextField.PASSWORD);
        Picker tfDateNaissance = new Picker();

        Button btnSignIn = new Button("Go To Sign In");
        Button btnSignUp = new Button("Register !");
        
        btnSignIn.addActionListener( ev -> {
            previous.showBack();            
        });
        
        btnSignUp.addActionListener( ev -> {
            
            if(tfNom.getText().length() > 0 
                    && tfPrenom.getText().length() > 0
                    && tfLogin.getText().length() > 0
                    && tfPassword.getText().length() > 0
                    && tfDateNaissance.getText().length() > 0  ){
                              
                try {
                   
                    previous.showBack();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    Dialog.show("Error", "Erreur : " +ex.getMessage(), "OK", null);
                }
                
            }else{
                Dialog.show("Error", "Champs vide !", "OK", null);
            }
            
        });
        
        addAll(tfNom, tfPrenom, tfLogin, tfPassword, tfDateNaissance, btnSignUp, btnSignIn);
    }
}

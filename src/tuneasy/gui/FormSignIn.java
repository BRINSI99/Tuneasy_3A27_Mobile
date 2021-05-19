/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;


/**
 *
 * @author fakhreddine
 */
public class FormSignIn extends Form {
    
    

    public FormSignIn() {
        
        
        setUIID("FormSignIn");
       
        setLayout(new FlowLayout(CENTER,CENTER));
        
        TextField tfLogin = new TextField("","Login");
        TextField tfPwd = new TextField("","Password");
        tfPwd.setConstraint(TextField.PASSWORD);
        
        Container cnt = new Container(BoxLayout.y());

        Button btnSignIn = new Button("Sign In");
        Button btnSignUp = new Button("Create Account");
        
        cnt.addAll(tfLogin, tfPwd, btnSignIn, btnSignUp);
        
        btnSignIn.addActionListener( ev -> {
            
            if(tfLogin.getText().length() > 0 && tfPwd.getText().length() > 0  ){
                    
                 new FormHome(this).show();
            }else{
                Dialog.show("Erreur", "Champs vide !", "OK", null);
            }
            
        });
        
        btnSignUp.addActionListener( ev -> {
            new FormSignUp(this).show();
        });
        
        addAll(cnt);
    }
}

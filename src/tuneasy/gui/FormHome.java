/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import tuneasy.services.RestaurantService;


/**
 *
 * @author fakhreddine
 */
public class FormHome extends Form {

    public FormHome(Form previous) {
        setTitle("Home");
        
        setLayout(new FlowLayout(CENTER, CENTER));
        
        add(new Label("Client"));
       
       
        getToolbar().addCommandToSideMenu("Home", null, (evt) -> {
            Dialog.show("Information", "Vous etes dans la form home !", "OK", null);
        });
        
        getToolbar().addCommandToSideMenu("Restaurants", null, (evt) -> {
            new FormResto(this).show();
        });
        
        getToolbar().addCommandToSideMenu("MesReservations", null, (evt) -> {
            new FormMesReservations(this).show();
        });
        /*
        getToolbar().addCommandToSideMenu("Mes Favoris", null, (evt) -> {
            new FormFavoris(this).show();
        });*/
        
        getToolbar().addCommandToRightBar("Logout", null, (evt) -> {
            previous.showBack();
        });
    }   
}

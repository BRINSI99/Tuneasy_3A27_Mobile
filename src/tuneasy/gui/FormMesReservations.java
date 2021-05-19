/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import java.util.List;
import tuneasy.entities.ReservationR;
import tuneasy.entities.Restaurant;
import tuneasy.myapp.MyApplication;
import tuneasy.services.ReservationRService;
import tuneasy.services.RestaurantService;

/**
 *
 * @author BabyViper
 */

public class FormMesReservations extends Form{

    private ReservationRService sv;
    
    public FormMesReservations(Form previous) {
        
        sv = new ReservationRService();
        
        setTitle("Mes Reservation");
        setLayout(BoxLayout.y());
        
        List<ReservationR> Restaurants = ReservationRService.getInstance().affichageRes();
        
        for (int i = 0; i < Restaurants.size(); i++) {
            add(addRestaurantItem(Restaurants.get(i)));
        }
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
        
    
    public Container addRestaurantItem(ReservationR restaurant){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());

        Label lbMarque = new Label("Plat: "+restaurant.getNomplat());
        Label lbModele = new Label("Quantity: "+String.valueOf(restaurant.getQte()));
        Label lbPrix = new Label("Date: "+restaurant.getDate());
        /*
        Label lSupprimer = new Label("Sypprimer");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        */
        details.addAll(lbMarque,lbModele, lbPrix);
        /*
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
           
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","vous voulez supprimer cette publication?","Annuler","Oui")){
                dig.dispose();
            }
            else {
                dig.dispose();
                
                if(ReservationRService.getInstance().deletePublication(restaurant.getId())) {
                    new FormMesReservations(this).show();
                    
                }
            }
            
        });*/
                        
       

       
        

     holder.addAll(details);
        
        
        
        return holder;
    }
}

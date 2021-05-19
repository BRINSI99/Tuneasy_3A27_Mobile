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
import java.util.ArrayList;
import java.util.List;
import tuneasy.entities.Restaurant;
import tuneasy.myapp.MyApplication;
import tuneasy.services.RestaurantService;

/**
 *
 * @author BabyViper
 */
/*
public class FormResto extends Form {
    FormResto(Form previous) {
    setTitle("Liste des restaurants");
       
    SpanLabel sp = new SpanLabel();
        sp.setText(RestaurantService.getInstance().affichageResto().toString());
        add(sp);
    
    ArrayList<Restaurant>list = RestaurantService.getInstance().affichageResto();
        
        for(Restaurant r : list){
        System.out.println(r.toString());
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}

    
}
*/
public class FormResto extends Form{

    private RestaurantService sv;
    
    public FormResto(Form previous) {
        
        sv = new RestaurantService();
        
        setTitle("Liste des Restaurants");
        setLayout(BoxLayout.y());
        
        List<Restaurant> Restaurants = RestaurantService.getInstance().affichageResto();
        
        for (int i = 0; i < Restaurants.size(); i++) {
            add(addRestaurantItem(Restaurants.get(i)));
        }
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
        
    
    public Container addRestaurantItem(Restaurant restaurant){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());

        Label lbMarque = new Label(restaurant.getNom());
        Label lbModele = new Label(restaurant.getType());
        Label lbPrix = new Label(String.valueOf(restaurant.getNote()));
        
        details.addAll(lbMarque,lbModele, lbPrix);
                        
        EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("load.jpg"), false);
        
         int idr = restaurant.getId_resto();

       Image img = URLImage.createToStorage(enc, "restaurant_"+restaurant.getNom(), "file:/C:/wamp64/www/tuneasy/public/upload/img/"+restaurant.getPhoto(), URLImage.RESIZE_SCALE);
       
       
        ImageViewer image = new ImageViewer(img);
        

     holder.addAll(image, details);
        
     
     



     
     lbMarque.addPointerReleasedListener(ev->{
           
               
           new FormPlat(this).show();
        });
        
       
        
        holder.setLeadComponent(lbMarque);
        
        return holder;
    }
}

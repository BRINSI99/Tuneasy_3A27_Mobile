/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;

import tuneasy.entities.Plat;
import tuneasy.myapp.MyApplication;
import tuneasy.services.PlatServices;
import tuneasy.services.ReservationRService;

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
public class FormPlat extends Form{

    private PlatServices sv;
    
    public FormPlat(Form previous) {
        
        sv = new PlatServices();
        
        setTitle("Liste des Plats");
        setLayout(BoxLayout.y());
        
        List<Plat> plats = PlatServices.getInstance().affichagePlat();
        
        for (int i = 0; i < plats.size(); i++) {
            add(addRestaurantItem(plats.get(i)));
        }
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
        
    
    public Container addRestaurantItem(Plat plat){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());

        Label lbMarque = new Label(plat.getNom());
        Label lbModele = new Label(plat.getType());
        Label lbPrix = new Label(String.valueOf(plat.getPrix()));
        TextField num = new TextField("", "Quantite");
         int aux =num.getAsInt(BASELINE);
        
      //  String qte;
        //qte = qt.getText();
        //int qtee =Integer.parseInt(qte);
        Button b = new Button("Reserver");

        
        
        details.addAll(lbMarque,lbModele, lbPrix,num ,b);
                        
        EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("load.jpg"), false);
        
         

       Image img = URLImage.createToStorage(enc, "plat_"+plat.getNom(), "file:/C:/wamp64/www/tuneasy/public/upload/img/"+plat.getPhoto(), URLImage.RESIZE_SCALE);
       
       
        ImageViewer image = new ImageViewer(img);
        

      
        holder.addAll(image, details);
        b.addActionListener((e) ->{
                ReservationRService.getInstance().ajoutReservation(aux, plat.getId_plat(),16);
        
        Message m = new Message("Reservation\n" +
            "\n" +
           "A new reservation has been made.Please Confirm!");
            m.getAttachments().put("file:/C:/wamp64/www/tuneasy/public/upload/img/"+plat.getPhoto(), "image/png");
            Display.getInstance().sendMessage(new String[] {"tuneasy.restaurants@gmail.com"}, "Reservation", m);
        });
       
        
        return holder;
    }
}

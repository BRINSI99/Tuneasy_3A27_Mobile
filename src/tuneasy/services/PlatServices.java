/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tuneasy.entities.Plat;

import tuneasy.utils.Statics;

/**
 *
 * @author BabyViper
 */
public class PlatServices {
   
    
   public ArrayList<Plat> plats;
    
    public static PlatServices instance=null;
    
    private ConnectionRequest req;

    public PlatServices() {
         req = new ConnectionRequest();
    }

    public static PlatServices getInstance() {
        if (instance == null) {
            instance = new PlatServices();
        }
        return instance;
    }
    
    
    public ArrayList<Plat>affichagePlat(){
    ArrayList<Plat> plats = new ArrayList<>();
        String url = Statics.BASE_URL+"/platjson";
    req.setUrl(url);
  
   
    req.addResponseListener(new ActionListener<NetworkEvent>(){
        @Override
        public void actionPerformed(NetworkEvent evt){
        JSONParser jsonp;
        jsonp= new JSONParser();
        
            try {
                Map<String,Object> mapPlat = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
           
            List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>)mapPlat.get("root");
            
            for(Map<String,Object>obj:listOfMaps){
            Plat t = new Plat();
            float id = Float.parseFloat(obj.get("id_plat").toString());
                t.setId_plat((int)id);
                t.setNom(obj.get("nom").toString()); 
                t.setComposition(obj.get("composition").toString());
                float num = Float.parseFloat(obj.get("prix").toString());
                t.setPrix(num);
                t.setType(obj.get("type").toString()); 
                t.setPhoto(obj.get("photo").toString());    
               
                plats.add(t);
            
              
            }
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
         
      
    return plats;
    
    }
}

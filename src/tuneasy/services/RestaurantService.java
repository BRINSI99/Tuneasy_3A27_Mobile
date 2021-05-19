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

import tuneasy.entities.Restaurant;
import tuneasy.utils.Statics;

/**
 *
 * @author BabyViper
 */
public class RestaurantService {
   
    
   public ArrayList<Restaurant> resto;
    
    public static RestaurantService instance=null;
    
    private ConnectionRequest req;

    public RestaurantService() {
         req = new ConnectionRequest();
    }

    public static RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }
    
    
    public ArrayList<Restaurant>affichageResto(){
    ArrayList<Restaurant> resto = new ArrayList<>();
        String url = Statics.BASE_URL+"/restojson";
    req.setUrl(url);
  
   
    req.addResponseListener(new ActionListener<NetworkEvent>(){
        @Override
        public void actionPerformed(NetworkEvent evt){
        JSONParser jsonp;
        jsonp= new JSONParser();
        
            try {
                Map<String,Object> mapResto = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
           
            List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>)mapResto.get("root");
            
            for(Map<String,Object>obj:listOfMaps){
            Restaurant t = new Restaurant();
            float id = Float.parseFloat(obj.get("id_Resto").toString());
            float id2 = Float.parseFloat(obj.get("idResto").toString());    
            t.setId_resto((int)id);
                t.setNom(obj.get("nom").toString()); 
                t.setAdresse(obj.get("adresse").toString());
                t.setVille(obj.get("ville").toString());
                t.setDescription(obj.get("description").toString());    
                t.setType(obj.get("type").toString());
                t.setPhoto(obj.get("photo").toString());
                float num = Float.parseFloat(obj.get("numTel").toString());
                t.setNum_tel((int)num);
                t.setEmail(obj.get("email").toString());
                float note = Float.parseFloat(obj.get("note").toString());
                t.setNote((int)note);
                resto.add(t);
            }
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
         
      
    return resto;
    
    }


public Restaurant detResto(int id,Restaurant rest){
   
        String url = Statics.BASE_URL+"/detailR"+id;
    req.setUrl(url);
  
   String str = new String(req.getResponseData());
    req.addResponseListener((new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp= new JSONParser();
                
                try {
                    Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
                    
                    
                    float idr = Float.parseFloat(obj.get("idResto").toString());
                    rest.setId_resto((int)idr);
                    rest.setNom(obj.get("nom").toString());
                    rest.setAdresse(obj.get("adresse").toString());
                    rest.setVille(obj.get("ville").toString());
                    rest.setDescription(obj.get("description").toString());
                    rest.setType(obj.get("type").toString());
                    rest.setPhoto(obj.get("photo").toString());
                    float num = Float.parseFloat(obj.get("numTel").toString());
                    rest.setNum_tel((int)num);
                    rest.setEmail(obj.get("email").toString());
                    float note = Float.parseFloat(obj.get("note").toString());
                    rest.setNote((int)note);
                    
                    
                    
                } catch (IOException ex) {
                    System.out.println("error"+ex.getMessage());
                }
            }
        }));
    NetworkManager.getInstance().addToQueueAndWait(req);
         
      
    return rest;
    
    }

}

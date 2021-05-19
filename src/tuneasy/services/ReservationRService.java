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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import tuneasy.entities.ReservationR;
import tuneasy.utils.Statics;

/**
 *
 * @author BabyViper
 */
public class ReservationRService {
   
    
   public ArrayList<ReservationR> resto;
     public static boolean resultOk=true ;
    public static ReservationRService instance=null;
  
    
    private ConnectionRequest req;

    public ReservationRService() {
         req = new ConnectionRequest();
    }

    public static ReservationRService getInstance() {
        if (instance == null) {
            instance = new ReservationRService();
        }
        return instance;
    }
    
    
    public ArrayList<ReservationR>affichageRes(){
    ArrayList<ReservationR> resto = new ArrayList<>();
        String url = Statics.BASE_URL+"/resjson";
    req.setUrl(url);
  
   
    req.addResponseListener(new ActionListener<NetworkEvent>(){
        @Override
        public void actionPerformed(NetworkEvent evt){
        JSONParser jsonp;
        jsonp= new JSONParser();
        
            try {
                Map<String,Object> mapRes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
           
            List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>)mapRes.get("root");
            
            for(Map<String,Object>obj:listOfMaps){
            ReservationR t = new ReservationR();
            float id = Float.parseFloat(obj.get("id_resPlat").toString());
                t.setId((int)id);
             t.setEmail(obj.get("email").toString()); 
             t.setNomplat(obj.get("nom").toString());
             t.setDate(obj.get("dateReservation").toString());
                float q = Float.parseFloat(obj.get("Quantity").toString());
                t.setQte((int)q);
                   
                
                
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


    public void ajoutReservation(int qt,int idp,int idc){
        
        String url =Statics.BASE_URL+"/AddRes?quantity="+qt+"&idp="+idp+"&idc="+idc;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

   public boolean deletePublication(int id) {
        String url =Statics.BASE_URL+"/DelR?id="+id ;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

}



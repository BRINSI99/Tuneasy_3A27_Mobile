/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Materiel;
import com.mycompany.utils.Statistics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * @author asus
 */
public class ServiceMateriel {
    public static ServiceMateriel instance = null;
    public ArrayList<Materiel> materiels;
    
    private ConnectionRequest req;
    
    public boolean resultOK;

    private ServiceMateriel() {
        req = new ConnectionRequest();
    }
    
    public static ServiceMateriel getInstance(){
        if (instance == null)
            instance = new ServiceMateriel();
            return instance;
    }
    
    public ArrayList<Materiel>affichageMateriel(){
        ArrayList<Materiel> result = new ArrayList<>();
        
        String url = Statistics.BASE_URL+"/AllForums";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapMateriels = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listofMaps = (List<Map<String,Object>>) mapMateriels.get("root");
                    
                    for(Map<String,Object> obj : listofMaps){
                        Materiel ma = new Materiel();
                        
                        float id = Float.parseFloat( obj.get("id")+"");
                        String nom_materiel = obj.get("nom_materiel").toString();
                        String description_materiel = obj.get("description_materiel").toString();
                        float prix_materiel = Float.parseFloat( obj.get("prix_materiel")+"");
                        
                        ma.setId((int)id);
                        ma.setNom_materiel(nom_materiel);
                        ma.setDescription_materiel(description_materiel);
                        ma.setPrix_materiel(prix_materiel);
                        
                        result.add(ma);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            });
                
                    NetworkManager.getInstance().addToQueueAndWait(req);
                    return result;
    }
    
    public ArrayList<Materiel> parseMateriels(String jsonText) {
        try {
            materiels = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Materiel f = new Materiel();

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setNom_materiel(obj.get("nom_materiel").toString());
                f.setDescription_materiel(obj.get("description_materiel").toString());
                
                float prix = Float.parseFloat(obj.get("prix_materiel").toString());
                f.setPrix_materiel((float) prix);
                

                System.out.print(obj);

                materiels.add(f);
            }
        } catch (IOException ex) {

        }
        return materiels;
    }
    
    public ArrayList<Materiel> getMateriels(int id) {
        String url = Statistics.BASE_URL + "/showPostJSON/" + id; //A CHANGER JSON
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                materiels = parseMateriels(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return materiels;
    }
    
    public boolean addMateriel(Materiel m) {
        String url = Statistics.BASE_URL + "/addMaterielJSON/new?nom_materiel=" + m.getNom_materiel()+ "&description_materiel=" + m.getDescription_materiel()+ "&prix_materiel=" + m.getPrix_materiel(); //création de l'URL
        req.setUrl(url); //A CHANGER JSON
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}

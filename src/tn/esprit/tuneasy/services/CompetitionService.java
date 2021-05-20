/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.services;

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
import tn.esprit.tuneasy.entities.Competition;
import tn.esprit.tuneasy.utils.Database;
import tn.esprit.tuneasy.utils.LoginSession;

/**
 *
 * @author Maya
 */
public class CompetitionService {
     public ArrayList<Competition> competitionArrayList;

    public static CompetitionService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CompetitionService() {
        req = new ConnectionRequest();
    }

    public static CompetitionService getInstance() {
        if (instance == null) {
            instance = new CompetitionService();
        }
        return instance;
    }

    public ArrayList<Competition> parseCompetition(String jsonText){ //Parsing Issues with id and date type
        try {
            competitionArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> competitionListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)competitionListJson.get("root");
            for(Map<String,Object> obj : list){
                Competition competition = new Competition();
                competition.setId((int) Float.parseFloat(obj.get("id").toString()));
                competition.setName(obj.get("Nom").toString());
                competition.setDescription(obj.get("description").toString());
                competition.setCategory(obj.get("category").toString());
                competition.setPlaces((int) Float.parseFloat(obj.get("nombre_de_place").toString()));
                competition.setImage(obj.get("image").toString());
                competition.setAddress(obj.get("adresse").toString());
                competition.setDate(obj.get("date").toString());
                competitionArrayList.add(competition);
            }
        } catch (IOException ex) {
        }
        return competitionArrayList;
    }
    public ArrayList<Competition> showAll(){
        String url = Database.BASE_URL+"competition/api/all"; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                competitionArrayList = parseCompetition(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return competitionArrayList;
    }

    public ArrayList<Competition> showMine() {
        String url = Database.BASE_URL+"competition/api/mine?idUser="+ LoginSession.loggedUser; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                competitionArrayList = parseCompetition(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return competitionArrayList;
    }
}

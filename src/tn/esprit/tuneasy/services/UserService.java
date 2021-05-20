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
import java.util.Map;
import tn.esprit.tuneasy.entities.User;
import tn.esprit.tuneasy.utils.Database;

/**
 *
 * @author Maya
 */
public class UserService {
    public ArrayList<User> userArrayList;
    public static UserService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public UserService() {
        connectionRequest = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean login(User user) {
        String url = Database.BASE_URL + "user/api/login?email=" + user.getEmail() + "&password=" + user.getPassword();
        System.out.println(url);
        System.out.println("i am here");
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userArrayList = parseUser(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        if (userArrayList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User findUser(String email) {
        User user = new User();
        String url = Database.BASE_URL + "user/api/findemail?email=" + email; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userArrayList = parseUser(new String(connectionRequest.getResponseData()));
                user.setId(userArrayList.get(0).getId());
                user.setEmail(userArrayList.get(0).getEmail());
                user.setPassword(userArrayList.get(0).getPassword());
                user.setNom(userArrayList.get(0).getNom());
                user.setPrenom(userArrayList.get(0).getPrenom());
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return user;
    }

    public boolean register(User user) {
        String url = Database.BASE_URL + "user/api/signup?email=" + user.getEmail() +
                "&password=" + user.getPassword() +
                "&firstName=" + user.getNom() +
                "&lastName=" + user.getPrenom(); // Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;

    }

    public ArrayList<User> parseUser(String jsonText) {

        userArrayList = new ArrayList<>();
        JSONParser j = new JSONParser();
        try {
            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            User user = new User();
            user.setId((int) Float.parseFloat(userListJson.get("id").toString()));
            user.setEmail(userListJson.get("email").toString());
            user.setPassword(userListJson.get("password").toString());
            user.setPrenom(userListJson.get("firstname").toString());
            user.setNom(userListJson.get("lastname").toString());
            userArrayList.add(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userArrayList;
    }
}

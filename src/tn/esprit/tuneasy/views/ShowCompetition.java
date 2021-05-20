/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.views;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.tuneasy.entities.Competition;
import tn.esprit.tuneasy.services.CompetitionService;

/**
 *
 * @author Maya
 */
public class ShowCompetition extends Form {

    Form current;
    CompetitionService competitionService = new CompetitionService();

    public ShowCompetition(Form previous, Competition competition, int action) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Competition Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label nameLabel = new Label(competition.getId() + "   " + competition.getName());
        SpanLabel descriptionSpanLabel = new SpanLabel("Description: " + competition.getDescription());
        Label categoryLabel = new Label("Category: " + competition.getCategory());
        Label addressLabel = new Label("Address: " + competition.getAddress());
        Label placeLabel = new Label("Nombre de place: " + competition.getPlaces());
        Label dateLabel = new Label("Date: " + competition.getDate().substring(0, 10));
        Button actionButton = new Button("");
        if (action == 0) {
            actionButton.setText("Reserver");
            actionButton.addActionListener(evt -> {
                // reserver
            });
        } else {
            actionButton.setText("Annuler");
            actionButton.addActionListener(evt -> {
                competitionService.delete();
                new HomeForm().show();
            });
        }
        addAll(nameLabel, categoryLabel, addressLabel, dateLabel, placeLabel, descriptionSpanLabel, actionButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", null, (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Let's participate in this!", new Message("Check out this one: " + competition.getName() + " it's: " + competition.getDate()));
        });

    }

}

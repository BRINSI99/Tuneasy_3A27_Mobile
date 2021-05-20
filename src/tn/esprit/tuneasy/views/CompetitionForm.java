/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Collections;
import tn.esprit.tuneasy.entities.Competition;
import tn.esprit.tuneasy.services.CompetitionService;

/**
 *
 * @author Maya
 */
public class CompetitionForm extends Form{
Form current;
    ArrayList<Competition> competitionArrayList = new ArrayList<>();
    CompetitionService competitionService = new CompetitionService();

    public CompetitionForm(int action) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        if (action == 0) {
            setTitle("Competition List");
            competitionArrayList = competitionService.showAll();
        } else {
            setTitle("My Competition List");
            competitionArrayList = competitionService.showMine();
        }
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Collections.reverse(competitionArrayList);
        showCompetition(action);
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Sort by Name", null, (evt) -> {
            removeAll();
            Collections.sort(competitionArrayList, Competition.nameComparator);
            showCompetition(action);
        });
        getToolbar().addCommandToOverflowMenu("Sort by Category", null, (evt) -> {
            removeAll();
            Collections.sort(competitionArrayList, Competition.categoryComparator);
            showCompetition(action);
        });
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
         getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> {
            new HomeForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("Competitions", null, (evt) -> {
            new CompetitionForm(0).show();
        });
        getToolbar().addCommandToLeftSideMenu("My Participation", null, (evt) -> {
            new CompetitionForm(1).show();
        });
        getToolbar().addCommandToLeftSideMenu("Profil", null, (evt) -> {
            new ProfilForm().show();
        });
    }

    private void showCompetition(int action) {
        if (competitionArrayList.size() > 0) {
            for (Competition competition : competitionArrayList) {
                MultiButton multiButton = new MultiButton();
                multiButton.setTextLine1(competition.getName() + "");
                multiButton.setTextLine2(competition.getDate().substring(0,10) + "");
                multiButton.setTextLine3("Category: "+competition.getCategory() + "");
                multiButton.setTextLine4("Places: "+competition.getPlaces() + "");
                multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
                multiButton.setUIID(competition.getId() + "");
                multiButton.addActionListener(l -> new ShowCompetition(current, competition, action).show());
                add(multiButton);
            }
        } else {
            add(new Label("There is no competition"));
        }
    }
}

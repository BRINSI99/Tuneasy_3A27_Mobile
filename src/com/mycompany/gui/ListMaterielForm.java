/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Materiel;
import com.mycompany.services.ServiceMateriel;
import java.util.ArrayList;
import javafx.scene.paint.Material;

/**
 *
 * @author asus
 */
public class ListMaterielForm extends BaseForm {

    Form current;

    public ListMaterielForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        current = this;
        ArrayList<Materiel> materiels = new ArrayList<>();
        current.setTitle("List Materiel");
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> new AddMaterielForm(res).show());
        fab.bindFabToContainer(current.getContentPane());

        materiels = ServiceMateriel.getInstance().affichageMateriel();

        for (Materiel m : materiels) {
            createMaterielElement(m);

        }

    }

    public void createMaterielElement(Materiel m) {

        Container c = new Container(BoxLayout.x());
      
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(80, 80, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, "400px-AGameOfThrones.jpg",
                "https://www.crushpixel.com/static19/preview2/backpack-icon-flat-style-3298386.jpg");
        background.fetch();
        c.add(background);

        Container detailsContairer = new Container(BoxLayout.y());
        Label lTitre = new Label(m.getNom_materiel());
        Label lDescription = new Label(m.getDescription_materiel());
        detailsContairer.add(lTitre);
        detailsContairer.add(lDescription);
        c.add(detailsContairer);

        Label lPrix = new Label(m.getPrix_materiel() + " dt");
        c.add(lPrix);

        current.add(c);
    }

//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Afficher materiel");
//        getContentPane().setScrollVisible(false);
//        Tabs swipe = new Tabs();
//        
//        Label s1 = new Label();
//        Label s2 = new Label();
//        
//      //  addTab(swipe, s1, res.getImage("back-logo.jpeg"), "", "", res);
//        
//        
//        swipe.setUIID("Container");
//        swipe.getContentPane().setUIID("Container");
//        swipe.hideTabs();
//        
//        ButtonGroup bg  = new ButtonGroup();
//        int size = Display.getInstance().convertToPixels(1);
//        
//        Image unselectedWalkThru = Image.createImage(size, size,0);
//        Graphics g = unselectedWalkThru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAlpha(100);
}

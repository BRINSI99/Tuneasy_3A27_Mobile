/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Materiel;
import com.mycompany.services.ServiceMateriel;

/**
 *
 * @author asus
 */
public class AddMaterielForm extends BaseForm {

    Form current;

    public AddMaterielForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        current = this;

        TextField tfTitre = new TextField("", "Nom du materiel");
        TextArea taDescription = new TextArea("", 500);
        TextField tfPrix = new TextField("", "Prix");
        tfPrix.setConstraint(TextField.NUMERIC);

        Button btnAdd = new Button("Enregistrer");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                Materiel m = new Materiel();
//                m.setNom_materiel(tfTitre.getText());
//                m.setDescription_materiel(taDescription.getText());
//                m.setPrix_materiel(Float.parseFloat(tfPrix.getText()));
//                ServiceMateriel.getInstance().ajoutMateriel(new Materiel());
                    
                    if ((tfTitre.getText().length() == 0) || (taDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Materiel m = new Materiel(tfTitre.getText(), taDescription.getText(), Float.valueOf(tfPrix.getText()) );
                        if (ServiceMateriel.getInstance().addMateriel(m)) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
                            //new ListeMaterielForm(previous, p).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        current.addAll(tfTitre, taDescription, tfPrix, btnAdd);

    }

}

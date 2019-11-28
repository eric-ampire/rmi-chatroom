package com.ericampire.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    Pane loginPane = new Pane();
    @FXML
    Pane homePane = new Pane();
    @FXML
    Pane mesChatRoom = new Pane();
    @FXML
    Pane creerChatRoom = new Pane();
    @FXML
    ImageView deconnexion = new ImageView();
    @FXML
    TextField user = new TextField();
    @FXML
    PasswordField pwd = new PasswordField();
    @FXML
    Button mesbd_retour = new Button();
    @FXML
    Button retour_in_add_bd = new Button();
    @FXML
    Button creer_chatRoom = new Button();
    @FXML
    TextField txt_nombd = new TextField();
    @FXML
    Pane panel_bdexiste, panel_bdcreer, creationPane, chatRoom = new Pane();
    @FXML
    Label txt_bdexiste, txt_bdcreer = new Label();
    @FXML
    TextField txt_nomtable, txt_champ1, txt_champ2, txt_champ3, txt_champ4, txt_champ5 = new TextField();
    @FXML
    TextField txt_type1, txt_type2, txt_type3, txt_type4, txt_type5 = new TextField();
    @FXML
    Pane panel_addchamp = new Pane();
    /**
     * @throws Exception
     */
    String bd;

    @FXML
    public void connexion() throws Exception {
        String log = user.getText();
        String mdp = pwd.getText();
        if ("a".equals(log) && "a".equals(mdp)) {
            homePane.setVisible(true);
            loginPane.setVisible(false);
        } else {
            System.out.println("mysgbdfxml.FXMLDocumentController.deconnexion()");
        }
    }

    @FXML
    public void creationCompte() throws Exception {
        creationPane.setVisible(true);
        loginPane.setVisible(false);
    }

    @FXML
    public void compteCreer() throws Exception {
        creationPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    public void deconnexion() throws Exception {
        homePane.setVisible(false);
        loginPane.setVisible(true);

    }

    @FXML
    public void mesbd_retour() throws Exception {
        homePane.setVisible(true);
        mesChatRoom.setVisible(false);

    }

    @FXML
    public void quittezchat() {
        homePane.setVisible(true);
        chatRoom.setVisible(false);
    }

    @FXML
    public void envoyezMes() {
    }

    @FXML
    public void mesbd() throws Exception {
        homePane.setVisible(false);
        mesChatRoom.setVisible(true);

    }

    @FXML
    public void new_bd() throws Exception {
        homePane.setVisible(false);
        creerChatRoom.setVisible(true);

    }

    @FXML
    public void retour_in_add_bd() throws Exception {
        creerChatRoom.setVisible(false);
        homePane.setVisible(true);


    }

    @FXML
    public void creer_chatRoom() throws Exception {
        creer_chatRoom.setVisible(false);
        chatRoom.setVisible(true);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    public void cancelAccountCreation(ActionEvent actionEvent) {
        creationPane.setVisible(false);
        loginPane.setVisible(true);
    }
}

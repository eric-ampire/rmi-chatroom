package com.ericampire.app.controller;

import com.ericampire.app.model.entity.*;
import com.ericampire.app.model.repository.*;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Component
public class Controller implements Initializable {

    @FXML public TextField txtenvoyz;
    @FXML public ListView<Message> listMessage;
    @FXML public ListView<Message> listHostorique;
    private User currentUser;
    private Groupe currentGroup;

    @FXML public ListView<User> newChatRoomUserLIstView;
    @FXML public TextField edtNewNameChatRoom;

    @Autowired private GroupRepository groupRepository;
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private GroupMessageUserRepository groupMessageUserRepository;
    @Autowired private GroupUserRepository groupUserRepository;

    @FXML public TextField newUsername, newDisplayName, newPassword;
    @FXML public ProgressBar creationProgress;

    // Pane
    @FXML Pane loginPane, homePane, mesChatRoom, creerChatRoom;

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

        Optional<User> foundedUser = userRepository.findUserByUserNameAndPassword(log, mdp);
        if (foundedUser.isPresent()) {
            homePane.setVisible(true);
            loginPane.setVisible(false);

            currentUser = foundedUser.get();
            System.out.println(foundedUser.get().getUserName());

            user.clear();
            pwd.clear();
        } else {
            // Error message
            System.out.println("user not found");
        }
    }

    @FXML
    public void creationCompte() throws Exception {
        creationPane.setVisible(true);
        loginPane.setVisible(false);
    }

    @FXML
    public void compteCreer() throws Exception {
        String username = newUsername.getText();
        String displayName = newDisplayName.getText();
        String password = newPassword.getText();

        if (username.isEmpty() || displayName.isEmpty() || password.isEmpty()) {
            // Show error message
        } else {
            creationProgress.setVisible(true);
            User newUser = new User(username, displayName, password);
            currentUser = userRepository.save(newUser);

            creationPane.setVisible(false);
            loginPane.setVisible(true);

            newUsername.clear();
            newDisplayName.clear();
            newPassword.clear();
            creationProgress.setVisible(false);
        }
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
        String contenu = txtenvoyz.getText();

        if (contenu.isEmpty()) {
            System.out.println("Contenu null");
        } else {

            Message message = new Message();
            message.setDate(new Date());
            message.setContenu(contenu);

            Message currentMessage = messageRepository.save(message);

            // Message
            GroupMessageUser groupMessageUser = new GroupMessageUser();
            groupMessageUser.setIdGroup(currentGroup.getId());
            groupMessageUser.setIdMessage(currentMessage.getId());
            groupMessageUser.setIdUser(currentUser.getId());

            groupMessageUserRepository.save(groupMessageUser);

            listMessage.getItems().clear();
            messageRepository.findAll().forEach(item -> {
                listMessage.getItems().add(item);
            });

            txtenvoyz.clear();
        }
    }

    @FXML
    public void mesbd() throws Exception {
        homePane.setVisible(false);
        mesChatRoom.setVisible(true);

        listHostorique.getItems().clear();
        messageRepository.findAll().forEach(item -> {
            listHostorique.getItems().add(item);
        });
    }

    @FXML
    // Show view for chatroom creation
    public void new_bd() throws Exception {
        newChatRoomUserLIstView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        newChatRoomUserLIstView.getItems().removeAll();
        userRepository.findAll().forEach(user -> {
            newChatRoomUserLIstView.getItems().add(user);
            System.out.println(user);
        });

        homePane.setVisible(false);
        creerChatRoom.setVisible(true);
    }

    @FXML
    public void retour_in_add_bd() throws Exception {
        creerChatRoom.setVisible(false);
        homePane.setVisible(true);


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    public void cancelAccountCreation(ActionEvent actionEvent) {
        creationPane.setVisible(false);
        loginPane.setVisible(true);
    }

    // Create group de chat
    @FXML
    public void createNewChatRoom(MouseEvent mouseEvent) {

        String chatroomName = edtNewNameChatRoom.getText();
        ObservableList<User> selectedUser = newChatRoomUserLIstView.getSelectionModel().getSelectedItems();

        if (chatroomName.isEmpty() || selectedUser.isEmpty()) {
            System.out.println("Erreur input");
        } else {
            Groupe newGroup = new Groupe();
            newGroup.setName(chatroomName);
            currentGroup = groupRepository.save(newGroup);

            selectedUser.forEach(user -> {
                GroupUser groupUser = new GroupUser(currentGroup.getId(), user.getId());
                groupUserRepository.save(groupUser);
            });

            creer_chatRoom.setVisible(false);
            chatRoom.setVisible(true);
        }
    }
}

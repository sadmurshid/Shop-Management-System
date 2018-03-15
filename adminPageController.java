package project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class adminPageController  {

    @FXML BorderPane adminBorderPane;
    @FXML HBox adminTopHbox;
    @FXML VBox adminLeftVbox;
    @FXML GridPane adminCenter;
    @FXML Button settingsButton;
    @FXML Button logoutButton;
    @FXML Button addUserButton;
    @FXML Button removeUserButton;
    @FXML Button inventoryButton;
    @FXML Button ordersButton;
    @FXML Button batchButton;
    @FXML Button debitsButton;
    @FXML Button accountsButton;

    private Stage window;

    public adminPageController() {
        try{
            settingsButton=new Button("SETTINGS");
            addUserButton=new Button("ADD USER");

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize()
    {
        database db=new database();
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(" SYSTEM ADMIN ");

        adminBorderPane.setStyle("-fx-background-color: "+db.userTableGetBackgroundColor("SYSTEM_ADMIN")+";");

        addUserButton.setOnAction(event -> {
            try {
                GridPane pane = FXMLLoader.load(getClass().getResource("addUserPanel.fxml"));
                adminCenter=pane;
                adminBorderPane.setCenter(adminCenter);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        });

    }
}

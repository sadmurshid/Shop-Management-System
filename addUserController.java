package project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import static javafx.scene.control.ContentDisplay.LEFT;
import static javafx.scene.input.KeyCode.KP_LEFT;

public class addUserController {

    @FXML GridPane addUserGridPane;
    @FXML TextField nameTextField=new TextField();
    @FXML PasswordField passwordField=new PasswordField();
    @FXML PasswordField rePasswordField=new PasswordField();
    @FXML TextField phoneTextField=new TextField();
    @FXML TextField addressTextField=new TextField();
    @FXML TextField emailTextField=new TextField();
    @FXML ColorPicker backgroundColorPicker=new ColorPicker();
    @FXML Label warningLabelField=new Label("");
    @FXML Button saveButton=new Button();
    @FXML RadioButton inventoryYesRadio;
    @FXML RadioButton inventoryNoRadio;
    @FXML RadioButton batchYesRadio;
    @FXML RadioButton batchNoRadio;
    @FXML RadioButton orderYesRadio;
    @FXML RadioButton orderNoRadio;
    @FXML RadioButton debitsYesRadio;
    @FXML RadioButton debitsNoRadio;


    @FXML ToggleGroup inventoryAccess;
    @FXML ToggleGroup batchAccess;
    @FXML ToggleGroup orderAccess;
    @FXML ToggleGroup debitsAccess;

    public addUserController(){


    }

    @FXML
    void rePasswordKeyPressed(KeyEvent event){
//        System.out.println(event.getCode());
        String passStr=passwordField.getText();
        String rePassStr=rePasswordField.getText();
        switch (event.getCode()) {

            case ENTER:
                System.out.println("ENTER");
                if(!passStr.equals(rePassStr))
                    warningLabelField.setText("Password didn't match");
                break;

            case TAB:
                System.out.println("ENTER");
                if(!passStr.equals(rePassStr))
                {
                    warningLabelField.setText("Password didn't match");
                }
                break;
        }
    }


    @FXML
    void initialProperty()
    {
        inventoryAccess=new ToggleGroup();
        inventoryYesRadio=new RadioButton("YES");
        inventoryNoRadio=new RadioButton("NO");
        inventoryYesRadio.setToggleGroup(inventoryAccess);
        inventoryNoRadio.setToggleGroup(inventoryAccess);
        inventoryNoRadio.setSelected(true);

        batchAccess=new ToggleGroup();
        batchYesRadio=new RadioButton("YES");
        batchNoRadio=new RadioButton("NO");
        batchYesRadio.setToggleGroup(batchAccess);
        batchNoRadio.setToggleGroup(batchAccess);
        batchNoRadio.setSelected(true);

        orderAccess=new ToggleGroup();
        orderYesRadio=new RadioButton("YES");
        orderNoRadio=new RadioButton("NO");
        orderYesRadio.setToggleGroup(orderAccess);
        orderNoRadio.setToggleGroup(orderAccess);
        orderNoRadio.setSelected(true);

        debitsAccess=new ToggleGroup();
        debitsYesRadio=new RadioButton("YES");
        debitsNoRadio=new RadioButton("NO");
        debitsYesRadio.setToggleGroup(debitsAccess);
        debitsNoRadio.setToggleGroup(debitsAccess);
        debitsNoRadio.setSelected(true);
    }



    @FXML
    public void initialize()
    {
        warningLabelField.setText(" ");


    }

}

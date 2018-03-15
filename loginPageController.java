package project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class loginPageController {
    @FXML private Label errorMessageLabel;
    @FXML Button loginButton = new Button("Login");
    @FXML Button signupButton = new Button();
    @FXML TextField userNameTextfield= new TextField();
    @FXML PasswordField passwordPasswordField=new PasswordField();

    private Stage primaryStage=new Stage();

    database db=new database();

    public loginPageController(Stage pStage)
    {
        primaryStage=pStage;

//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
//            primaryStage=pStage;
//            primaryStage.setTitle("LOGIN PAGE");
//            Scene scene = new Scene(root, 590, 455);
//            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }catch (Exception e)
//        {
//            System.out.println(e.getCause());
//            e.printStackTrace();
//        }

    }

    public loginPageController() {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
//            primaryStage.setTitle("LOGIN");
//            Scene scene = new Scene(root, 590, 455);
//            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }catch (Exception e)
//        {
//            System.out.println(e.getCause());
//            e.printStackTrace();
//        }
    }


    @FXML
    private void initialize(){
        errorMessageLabel.setText("");

        loginButton.setOnAction(event ->{
            try {
                String user = userNameTextfield.getText();
                String password = passwordPasswordField.getText();
                System.out.println("USER : " + user + " PASSWORD : " + password);
                if (db.loginAllowed(user, password) && user.equals("SYSTEM_ADMIN")) {

                    Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                    primaryStage.setTitle("LOGIN");
                    Scene scene=new Scene(root, 590, 455);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    System.out.println("LOG IN SUCCESSFUL");
                } else if (db.loginAllowed(user, password))
                    System.out.println("LOG IN SUCCESSFUL");
                else
                    errorMessageLabel.setText("WRONG USER NAME OR PASSWORD ");
                System.out.println("log in Button clicked");
            }catch(Exception e1){
                e1.printStackTrace();
            }
        });
        signupButton.setOnAction(e->{
            System.out.println("signup Button clicked");
        });
    }








    @FXML
    private void changeLabel(String error)
    {
        try
        {
            errorMessageLabel.setText(error);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}

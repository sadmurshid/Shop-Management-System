package project;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class userPageController {

    private static Stage window;
    @FXML private static BorderPane borderPaneUserPage;

    public static void genericUser(String name)
    {
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(name+" as CLASS TEACHER");

        //BorderPane settings


    }
}

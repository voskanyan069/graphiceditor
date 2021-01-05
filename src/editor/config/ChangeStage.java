package editor.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeStage {
    @SuppressWarnings("rawtypes")
    public static void changeStage(Class thisClass, String path, String title,
                                    int[] minSize, int[] size, boolean resizable) {
        try {
            Parent root = FXMLLoader.load(thisClass.getResource(path));
            Stage primaryStage = new Stage();
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMinWidth(minSize[0]);
            primaryStage.setMinHeight(minSize[1]);
            primaryStage.setWidth(size[0]);
            primaryStage.setHeight(size[1]);
            primaryStage.setResizable(resizable);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImageSorter extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Geniuschung's Image Arrangement");
        primaryStage.setScene(new Scene(root, 400, 400));

        controller.setLogArea();

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public ImageSorter() {
        System.out.println("Hellow Image Sorter");
    }
}

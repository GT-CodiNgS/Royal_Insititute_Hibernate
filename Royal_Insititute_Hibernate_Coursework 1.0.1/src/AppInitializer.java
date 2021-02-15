import entity.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.io.IOException;

import static java.lang.reflect.Array.get;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/Loging.fxml"))));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }
}

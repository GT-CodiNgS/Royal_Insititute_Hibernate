package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DashboardController {

    public ImageView btnCancel;
    public AnchorPane root;
    public JFXButton btnLogout;
    public JFXButton btnStudent;
    public JFXButton btnCourse;
    public JFXButton btnSetting;
    public AnchorPane root1;
    public Label lblTime;
    public Label lblDate;
    public JFXButton btnRegistration;
    public Button btnMinimize;


    public void initialize() throws Exception {

//       rotation part
        RotateTransition rotation = new RotateTransition(Duration.seconds(1), btnCancel);
//        rotation.setCycleCount(Animation.INDEFINITE);
        rotation.setByAngle(360);
        rotation.setAutoReverse(true);
        btnCancel.setOnMouseEntered(e -> rotation.play());
        btnCancel.setOnMouseExited(e -> rotation.stop());
        setTime();

    }

    private void setTime() {
        Timeline timeline =new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
//            lblTime.setStyle("white");
            lblDate.setText((String.valueOf(LocalDate.now())));
//            lblDate.setStyle("white");
        }),


                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnCancelOnAction(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setContentText("Are you Sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        }
    }

    public void mouseEnter(MouseEvent mouseEvent) {
    }

    public void btnLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Signout");
        alert.setContentText("Are you Sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage)root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Loging.fxml"))));
        } else {

        }



    }

    public void btnStudentOnAction(MouseEvent mouseEvent) throws IOException {
        Parent dashboard;
        dashboard = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
        root1.getChildren().setAll(dashboard);
    }

    public void btnCourseOnAction(MouseEvent mouseEvent) throws IOException {
        Parent dashboard;
        dashboard = FXMLLoader.load(getClass().getResource("/view/CourseForm.fxml"));
        root1.getChildren().setAll(dashboard);
    }

    public void btnSettingOnAction(MouseEvent mouseEvent) {

    }


    public void btnRegistrationOnAction(MouseEvent mouseEvent) throws IOException {
        Parent dashboard;
        dashboard = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        root1.getChildren().setAll(dashboard);
    }

    public void btnMinimizeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();


        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);
    }
}

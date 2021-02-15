package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPw;
    public JFXButton btnCancel;
    public JFXButton btnLoging;
    public JFXCheckBox btnShowPw;
    public ImageView btnClose;
    public AnchorPane root;
    public JFXTextField txtpwtemp;

    public void initialize() throws Exception {
       txtPw.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
       txtUserName.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        txtpwtemp.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
//       rotation part
       RotateTransition rotation = new RotateTransition(Duration.seconds(1), btnClose);
        rotation.setByAngle(360);
        rotation.setAutoReverse(true);
        btnClose.setOnMouseEntered(e -> rotation.play());
        btnClose.setOnMouseExited(e -> rotation.stop());
    }


    public void btnCancelOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void btnLogingOnAction(MouseEvent mouseEvent) throws IOException {
        String pw=txtUserName.getText();
        String name=txtPw.getText();


        if (pw.length() > 0 && name.length() > 0) {
            System.out.println(pw);
            System.out.println(name+"aaa");
            if (true/*"gayash".equalsIgnoreCase (name) &&
                    "1234".equalsIgnoreCase(pw)*/) {

                Stage stage = (Stage)root.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Dashboard.fxml"))));

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again!!", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty!!", ButtonType.OK).show();
        }


    }

    public void btnShowPwOnAction(ActionEvent mouseEvent) {
        if (btnShowPw.isSelected()){
            String  password = txtPw.getText();
            txtpwtemp.setText(password);
            txtpwtemp.setVisible(true);
            txtPw.setVisible(false);


        }else{
            txtpwtemp.setVisible(false);
            txtPw.setText(txtpwtemp.getText());
            txtPw.setVisible(true);
        }

    }



    public void btnCloseOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void mouseEnter(MouseEvent mouseEvent) {

    }



}

package controller;

import business.BOFactory;
import business.custom.CourseBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.regex.Pattern;

public class AddCourseController {
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public AnchorPane root1;
    CourseBo bo= BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    public void btnSaveOnAction(MouseEvent mouseEvent) throws Exception {

        if (Pattern.compile("^(C)[0-9]{3}$").matcher(txtCode.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txtName.getText()).matches()) {

            if (Pattern.compile("^[0-9]{1,2}$").matcher(txtDuration.getText()).matches()) {
                if (Pattern.compile("^[0-9]{1,5}$").matcher(txtFee.getText()).matches()) {

                    //////////////
                    boolean isSaved = bo.add(new CourseDTO(txtCode.getText()
                            ,txtName.getText()
                            ,txtDuration.getText()
                            ,txtFee.getText()));
                    if (isSaved) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Saved...!");


                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            Stage stage = (Stage) btnCancel.getScene().getWindow();
                            stage.close();
                        }

                    }else{
                        new Alert(Alert.AlertType.WARNING, "Not Saved,Try again !", ButtonType.OK).show();

                    }


                } else {
                    txtFee.setFocusColor(Paint.valueOf("red"));
                    txtFee.requestFocus();
                }
            } else {
                txtDuration.setFocusColor(Paint.valueOf("red"));
                txtDuration.requestFocus();
            }
            } else {
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }
        } else {
            txtCode.setFocusColor(Paint.valueOf("red"));
            txtCode.requestFocus();
        }
    }




    public void btnCancelOnAction(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("close");
        alert.setContentText("Are you Sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        }
    }
}

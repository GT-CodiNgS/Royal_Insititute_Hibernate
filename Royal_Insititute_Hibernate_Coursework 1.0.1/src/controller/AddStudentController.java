package controller;

import business.BOFactory;
import business.custom.StudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.regex.Pattern;

public class AddStudentController {
    public AnchorPane root1;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXComboBox cmbGender;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public JFXDatePicker txtDate;
    StudentBo bo=BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize() throws Exception {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");


    }

    public void btnSaveOnAction(MouseEvent mouseEvent) throws Exception {
        if (Pattern.compile("^(S)[0-9]{3}$").matcher(txtId.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]+$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{10}$").matcher(txtContact.getText()).matches()) {
                        //////////////////////////////////////////////////
                        boolean isSaved = bo.add(new StudentDTO(txtId.getText()
                                ,txtName.getText(),txtAddress.getText()
                                ,txtContact.getText(),txtDate.getValue().toString()
                                ,cmbGender.getSelectionModel().getSelectedItem().toString()));
                        if (isSaved) {

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Saved...!");


                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                Stage stage = (Stage) btnCancel.getScene().getWindow();
                                stage.close();
                            }

                        }else{
                            new Alert(Alert.AlertType.WARNING, "Please Confirm box!!", ButtonType.OK).show();

                        }
                        ///////////////////////////////////////////////////////////////

                    } else {
                        txtContact.setFocusColor(Paint.valueOf("red"));
                        txtContact.requestFocus();
                    }
                } else {
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                    txtAddress.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }
        } else {
            txtId.setFocusColor(Paint.valueOf("red"));
            txtId.requestFocus();
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

    public void txtDateOnAction(MouseEvent mouseEvent) {
    }
}

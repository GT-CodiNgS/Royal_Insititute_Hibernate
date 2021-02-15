package controller;

import business.BOFactory;
import business.custom.CourseBo;
import business.custom.RegistratonBo;
import business.custom.StudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddRegistrationController {
    public AnchorPane root;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXTextField txtRegNo;

    public JFXTextField txtFee;
    public JFXComboBox cmbSid;
    public JFXComboBox cmbCid;
    public JFXDatePicker txtDate;
    Student student=new Student();
    Course course=new Course();
    RegistratonBo bo= BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);
    CourseBo courseBo= BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);
    StudentBo studentBo= BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    List<StudentDTO> studentDTOS;

    {
        try {
            studentDTOS = studentBo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<CourseDTO> courseDTOS;

    {
        try {
            courseDTOS = courseBo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws Exception {
        loadCombo();


    }
    public void loadCombo() throws Exception {


        for (StudentDTO studentDTO : studentDTOS) {
            String sid=studentDTO.getId();
            cmbSid.getItems().add(sid);
        }
        for (CourseDTO courseDTO  : courseDTOS) {
            String cid=courseDTO.getCode();
            cmbCid.getItems().add(cid);
        }

    }
    public void btnSaveOnAction(MouseEvent mouseEvent) throws Exception {
            student.setId(cmbSid.getSelectionModel().getSelectedItem().toString());
            course.setCode(cmbCid.getSelectionModel().getSelectedItem().toString());
        if  (Pattern.compile("^(R)[0-9]{3}$").matcher(txtRegNo.getText()).matches()) {
            if (Pattern.compile("^[0-9]{1,5}$").matcher(txtFee.getText()).matches()) {

                        //////////////////////////////////////////////////

                        boolean isSaved = bo.add(new RegistrationDTO(txtRegNo.getText()
                                ,txtDate.getValue().toString()
                                ,txtFee.getText()
                                ,cmbSid.getSelectionModel().getSelectedItem().toString()
                                ,cmbCid.getSelectionModel().getSelectedItem().toString()
                                ,student
                                ,course
                        ));
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
                        ///////////////////////////////////////////////////////////////

            } else {
                txtFee.setFocusColor(Paint.valueOf("red"));
                txtFee.requestFocus();
            }
        } else {
            txtRegNo.setFocusColor(Paint.valueOf("red"));
            txtRegNo.requestFocus();
        }





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
}

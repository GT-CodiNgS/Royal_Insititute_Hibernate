package controller;

import business.BOFactory;
import business.custom.CourseBo;
import business.custom.RegistratonBo;
import business.custom.StudentBo;
import com.jfoenix.controls.*;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Registration;
import entity.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.CourseTM;
import model.RegistratonTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegistrationFormController {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public ImageView btnNew;
    public JFXComboBox cmbNo;

    public JFXTextField txtFee;
    public JFXTextField txtStudentId;
    public JFXCheckBox chkUpdate;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXCheckBox chkDelete;
    public JFXTextField txtCourseCode;
    public TableView tblRegistration;
    public TableColumn clmRegNo;
    public TableColumn clmDate;
    public TableColumn clmFee;
    public TableColumn clmSid;
    public TableColumn clmCid;
    public JFXComboBox cmbSid;
    public JFXComboBox cmbCid;
    public JFXTextField txtSid;
    public JFXTextField txtCid;
    public JFXTextField txtDate;
    Student student=new Student();
    Course course=new Course();
    RegistratonBo bo= BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);
    List<RegistrationDTO> list;
    public void initialize() throws Exception {
        list = bo.findAll();
                loadCombo();

        clmRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        clmDate.setCellValueFactory (new PropertyValueFactory<> ("regDate"));
        clmFee.setCellValueFactory(new PropertyValueFactory<> ("regFee"));
        clmSid.setCellValueFactory(new PropertyValueFactory<> ("Sid"));
        clmCid.setCellValueFactory(new PropertyValueFactory<> ("Cid"));

        try {
            loadTable ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadTable() {
        ObservableList<RegistratonTM> items = tblRegistration.getItems();
        items.clear();
        try {
            List<RegistrationDTO> registrationDTOList = bo.findAll();
            for (RegistrationDTO dto : registrationDTOList) {
                items.add(new RegistratonTM(dto.getRegNo()
                        ,dto.getRegDate()
                        ,dto.getRegFee()
                        ,dto.getSid()
                        ,dto.getCid()
                ));
            }
            tblRegistration.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCombo() throws Exception {
        for (RegistrationDTO registrationDTO  : list) {
            String id=registrationDTO.getRegNo();
            cmbNo.getItems().add(id);
        }

    }
    public void btnAddNewOnAction(MouseEvent mouseEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddRegistraion.fxml"));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void btnNewOnAction(MouseEvent mouseEvent) {
    }

    public void cmbNoOnAction(ActionEvent actionEvent) throws Exception {
        RegistrationDTO dto = bo.find(cmbNo.getSelectionModel().getSelectedItem().toString());
        if (dto!=null){
            txtDate.setText(dto.getRegDate());
            txtFee.setText(dto.getRegFee());
            txtSid.setText(dto.getSid());
            txtCid.setText(dto.getCid());


        }else{
//            cmbId.setFocusColor(Paint.valueOf("red"));
//            cmbId.requestFocus();
            clearText();
        }

    }

    public void chkUpdateOnAction(ActionEvent actionEvent) {
        if (chkUpdate.isSelected()){
            txtFee.setEditable(true);

        }else{
            txtFee.setEditable(false);

        }
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws Exception {
        student.setId(txtSid.getText());
        course.setCode(txtCid.getText());
        if (Pattern.compile("^[0-9]{1,5}$").matcher(txtFee.getText()).matches()) {

            //////////////////////////////////////////////////

            if (chkUpdate.isSelected() && (!(chkDelete.isSelected())) ){
                System.out.println("Delete button Selected");
                boolean isUpdated=bo.update(new RegistrationDTO(cmbNo.getSelectionModel().getSelectedItem().toString(),txtDate.getText(),txtFee.getText(),txtSid.getText(),txtCid.getText(),student,course
                ));
                if (isUpdated){
                    new Alert(Alert.AlertType.INFORMATION, "Updated !", ButtonType.OK).show();
                    clearText();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Not Updated,Try again?", ButtonType.YES).show();            }
            }else{
                new Alert(Alert.AlertType.WARNING, "Not Updated !", ButtonType.OK).show();
            }


            ///////////////////////////////////////////////////////////////

        } else {
            txtFee.setFocusColor(Paint.valueOf("red"));
            txtFee.requestFocus();
        }


        /////




    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws Exception {
        if (chkDelete.isSelected() && (!(chkUpdate.isSelected())) ){
            boolean isDeleted=bo.delete(cmbNo.getSelectionModel().getSelectedItem().toString());
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Deleted !", ButtonType.OK).show();
                clearText();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again... !", ButtonType.OK).show();

            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please check Confirm box !", ButtonType.OK).show();
        }
    }
    public void clearText() throws Exception {
        txtDate.clear();
        txtFee.clear();
        txtSid.clear();
        txtCid.clear();

    }

    public void chkDeleteOnAction(ActionEvent actionEvent) {
    }

    public void cmbSidOnAction(ActionEvent actionEvent) {
    }

    public void cmbCidOnAction(ActionEvent actionEvent) {
    }
}

package controller;

import business.BOFactory;
import business.custom.StudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.StudentDTO;
import javafx.animation.RotateTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.CourseTM;
import model.StudentTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentFormController {
    public JFXButton btnAddNew;
    public AnchorPane root;
    public JFXButton btnDelete;
    public JFXCheckBox chkDelete;
    public JFXCheckBox chkUpdate;
    public JFXButton btnUpdate;
    public JFXComboBox cmbId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtxBob;
    public JFXTextField txtContact;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmDob;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmButton;
    public JFXTextField txtGender;
    public TableView tblCourse;
    public TableColumn clmGender;
    public TableView tblStudent;

    StudentBo bo= BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    List<StudentDTO> list;

    public List<StudentDTO> refresh() throws Exception {
        list = bo.findAll();
        return list;
    }
    public void initialize() throws Exception {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory (new PropertyValueFactory<> ("name"));
        clmDob.setCellValueFactory(new PropertyValueFactory<> ("dobDate"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<> ("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<> ("contact"));
        clmGender.setCellValueFactory(new PropertyValueFactory<> ("gender"));
        try {
            loadTable ();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadCombo();
    }
    public void loadCombo() throws Exception {
        list = refresh();
        String id;
        for (StudentDTO studentDTO : list) {
             id=studentDTO.getId();
            cmbId.getItems().add(id);
        }

    }
    public void btnAddNewOnAction(MouseEvent mouseEvent) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddStudent.fxml"));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws Exception {
        if (chkDelete.isSelected() && (!(chkUpdate.isSelected()))){
            boolean isDeleted=bo.delete(cmbId.getSelectionModel().getSelectedItem().toString());
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

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws Exception {
            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]+$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{10}$").matcher(txtContact.getText()).matches()) {
                        //////////////////////////////////////////////////

                        if (chkUpdate.isSelected() && (!(chkDelete.isSelected())) ){
                            boolean isUpdated=bo.update(new StudentDTO(cmbId.getSelectionModel().getSelectedItem().toString()
                                    ,txtName.getText(),txtAddress.getText()
                                    ,txtContact.getText(),txtxBob.getText()
                                    ,txtGender.getText()));
                            if (isUpdated){
                                new Alert(Alert.AlertType.INFORMATION, "Updated !", ButtonType.OK).show();
                                clearText();
                            }else{
                                new Alert(Alert.AlertType.WARNING, "Not Updated,Try again?", ButtonType.YES).show();            }
                        }else{
                            new Alert(Alert.AlertType.WARNING, "Please check Confirm box !", ButtonType.OK).show();
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





    }

    public void chkDeleteOnAction(ActionEvent actionEvent) {

    }

    public void chkUpdateOnAction(ActionEvent actionEvent) {
        if (chkUpdate.isSelected()){
            txtName.setEditable(true);
            txtAddress.setEditable(true);
            txtContact.setEditable(true);
            txtxBob.setEditable(true);

        }else{
            txtName.setEditable(false);
            txtAddress.setEditable(false);
            txtContact.setEditable(false);
            txtxBob.setEditable(false);
        }
    }

    public void cmbIdOnAction(ActionEvent actionEvent) throws Exception {
        StudentDTO dto = bo.find(cmbId.getSelectionModel().getSelectedItem().toString());
        if (dto!=null){
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContact());
            txtxBob.setText(dto.getDobDate());
            txtGender.setText(dto.getGender());

        }else{
//            cmbId.setFocusColor(Paint.valueOf("red"));
//            cmbId.requestFocus();
            clearText();
        }

    }
    public void clearText() throws Exception {
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtxBob.clear();
        txtGender.clear();
        loadCombo();

    }

    public void loadTable() {
        ObservableList<StudentTM> items = tblStudent.getItems();
        items.clear();
        try {
            List<StudentDTO> studentDTOList = bo.findAll();
            for (StudentDTO dto : studentDTOList ) {
                items.add(new StudentTM(dto.getId()
                        ,dto.getName()
                        ,dto.getAddress()
                        ,dto.getContact()
                        ,dto.getDobDate()
                        ,dto.getGender()));
            }
            tblStudent.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

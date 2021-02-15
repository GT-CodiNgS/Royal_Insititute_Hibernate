package controller;

import business.BOFactory;
import business.custom.CourseBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
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
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CourseFormController {


    public JFXButton btnAddNew;
    public JFXComboBox cmbCode;
    public JFXTextField txtName;
    public JFXTextField txtFee;
    public JFXTextField txtDuration;
    public JFXCheckBox chkUpdate;
    public JFXButton btnUpdate;
    public TableView tblCourse;
    public TableColumn clmCode;
    public TableColumn clmName;
    public TableColumn clmDuration;
    public TableColumn clmFee;
    public JFXButton btnDelete;
    public JFXCheckBox chkDelete;
    public AnchorPane root;
    public JFXButton btnRegStudents;
    CourseBo bo= BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);
    List<CourseDTO> list;
    public void initialize() throws Exception {
        list = bo.findAll();
        loadCombo();
        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmName.setCellValueFactory (new PropertyValueFactory<> ("name"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<> ("duration"));
        clmFee.setCellValueFactory(new PropertyValueFactory<> ("fee"));

        try {
            loadTable ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadCombo() throws Exception {

        String id;
        for (CourseDTO courseDTO  : list) {
            id=courseDTO.getCode();
            cmbCode.getItems().add(id);
        }

    }

    public void btnAddNewOnAction(MouseEvent mouseEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddCourse.fxml"));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void cmbCodeOnAction(ActionEvent actionEvent) throws Exception {
        CourseDTO dto = bo.find(cmbCode.getSelectionModel().getSelectedItem().toString());
        if (dto!=null){
            txtName.setText(dto.getName());
            txtDuration.setText(dto.getDuration());
            txtFee.setText(dto.getFee());

        }else{
//            cmbId.setFocusColor(Paint.valueOf("red"));
//            cmbId.requestFocus();
            clearText();
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws Exception {
        if (chkDelete.isSelected() && (!(chkUpdate.isSelected()))){
            System.out.println("Delete button Selected");
            boolean isDeleted=bo.delete(cmbCode.getSelectionModel().getSelectedItem().toString());
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Deleted !", ButtonType.OK).show();
                clearText();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again... !", ButtonType.OK).show();

            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please check", ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws Exception {

            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txtName.getText()).matches()) {

                if (Pattern.compile("^[0-9]{1,2}$").matcher(txtDuration.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{1,5}$").matcher(txtFee.getText()).matches()) {

                        //////////////

                        if (chkUpdate.isSelected() && (!(chkDelete.isSelected()))){
                            System.out.println("update button Selected");
                            boolean isUpdated=bo.update(new CourseDTO(cmbCode.getSelectionModel().getSelectedItem().toString()
                                    ,txtName.getText()
                                    ,txtDuration.getText()
                                    ,txtFee.getText()));
                            if (isUpdated){
                                new Alert(Alert.AlertType.INFORMATION, "Updated !", ButtonType.OK).show();
                                clearText();
                            }else{
                                new Alert(Alert.AlertType.WARNING, "Not Updated,Try again?", ButtonType.YES).show();
                            }

                        }else{
                            new Alert(Alert.AlertType.WARNING, "Please Confirm box!!", ButtonType.OK).show();
                        }
                        /////////////////////////////////////


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





    }

    public void chkUpdateOnAction(ActionEvent actionEvent) {
        if (chkUpdate.isSelected()){
            txtName.setEditable(true);
            txtDuration.setEditable(true);
            txtFee.setEditable(true);
        }else{
            txtName.setEditable(false);
            txtDuration.setEditable(false);
            txtFee.setEditable(false);
        }

    }

    public void chkDeleteOnAction(ActionEvent actionEvent) {

    }
    public void clearText() throws Exception {

        txtName.clear();
        txtFee.clear();
        txtDuration.clear();
        loadCombo();

    }
    public void loadTable() {
        ObservableList<CourseTM> items = tblCourse.getItems();
        items.clear();
        try {
            List<CourseDTO> courseDTOList = bo.findAll();
            for (CourseDTO dto : courseDTOList) {
                items.add(new CourseTM(dto.getCode()
                        , dto.getName()
                        , dto.getDuration()
                        , dto.getFee()
                ));
            }
            tblCourse.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnRegStudentsOnAction(MouseEvent mouseEvent) {

    }
}

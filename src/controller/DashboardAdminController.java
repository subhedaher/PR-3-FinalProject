package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;
import view.ViewManager;

public class DashboardAdminController implements Initializable {

    public static User selectedPatientToUpdate;
    public static Stage updateStage;

    @FXML
    private Button patients;
    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button createPatient;
    @FXML
    private Button showAll;
    @FXML
    private TableView<User> tablePatients;
    @FXML
    private TableColumn<User, Integer> age;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> gender;
    @FXML
    private Button search;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> userName;
    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setCellValueFactory(new PropertyValueFactory("userName"));
        password.setCellValueFactory(new PropertyValueFactory("password"));
        firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        age.setCellValueFactory(new PropertyValueFactory("age"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        gender.setCellValueFactory(new PropertyValueFactory("gender"));
    }

    @FXML
    private void patients(ActionEvent event) {
        ViewManager.dashboardAdmin.changeDashboardAdmin();
    }

    @FXML
    private void appoinments(ActionEvent event) {
        ViewManager.dashboardAdmin.changeappoinments();
    }

    @FXML
    private void bookedAppoinments(ActionEvent event) {
        ViewManager.dashboardAdmin.changeBookedAppoinments();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeDashboardAdmin();
        ViewManager.openLoginAdmin();
        ViewManager.dashboardAdmin = null;
    }

    @FXML
    private void createPatient(ActionEvent event) {
        ViewManager.dashboardAdmin.changeCreatepatient();
    }

    @FXML
    private void showAll(ActionEvent event) throws SQLException {
        txtSearch.setText("");
        ObservableList<User> patients = FXCollections.observableArrayList(User.getAllPatients());
        tablePatients.setItems(patients);

    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        if (txtSearch.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Operation is invalid");
            alert.showAndWait();
        } else {
            ObservableList<User> patient = FXCollections.observableArrayList(User.searchPatientByName(txtSearch.getText()));
            tablePatients.setItems(patient);
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        if (tablePatients.getSelectionModel().getSelectedItem() != null) {
            User selectedPatient = tablePatients.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Patient delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this patient ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedPatient.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Patient deleted");
                    deletedSuccessAlert.setContentText("Patient deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an patient");
            warnAlert.setContentText("Please select an patient from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        if (tablePatients.getSelectionModel().getSelectedItem() != null) {
            selectedPatientToUpdate = tablePatients.getSelectionModel().getSelectedItem();
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/view/AdminFxml/UpdatePatient.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updatePatientScene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(updatePatientScene);
            updateStage.setTitle("Update patient " + selectedPatientToUpdate.getUserName());
            updateStage.show();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an patient");
            warnAlert.setContentText("Please select an patient from the table view");
            warnAlert.show();
        }
    }

}

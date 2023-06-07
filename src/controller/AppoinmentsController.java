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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import view.ViewManager;

public class AppoinmentsController implements Initializable {

    public static Appointment selectedAppointmentToUpdate;
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
    private Button createAppoinments;
    @FXML
    private Button showAllAppoinments;
    @FXML
    private TableView<Appointment> tableAppoinments;
    @FXML
    private TableColumn<Appointment, String> appointmentDate;
    @FXML
    private TableColumn<Appointment, String> appointmentDay;
    @FXML
    private TableColumn<Appointment, String> appointmentTime;
    @FXML
    private TableColumn<Appointment, String> status;
    @FXML
    private Button delete;
    @FXML
    private Button update;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        appointmentDate.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        appointmentDay.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        appointmentTime.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
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
    private void createAppoinments(ActionEvent event) {
        ViewManager.dashboardAdmin.changeCreateAppoinments();
    }

    @FXML
    private void showAllAppoinments(ActionEvent event) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList(Appointment.getAllAppointments());
        tableAppoinments.setItems(appointments);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableAppoinments.getSelectionModel().getSelectedItem() != null) {
            Appointment selectedAppointment = tableAppoinments.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Appointment delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this appointment ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedAppointment.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(AppoinmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AppoinmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Appoinment deleted");
                    deletedSuccessAlert.setContentText("Appoinment deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appoinment");
            warnAlert.setContentText("Please select an appoinment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        if (tableAppoinments.getSelectionModel().getSelectedItem() != null) {
            selectedAppointmentToUpdate = tableAppoinments.getSelectionModel().getSelectedItem();
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/view/AdminFxml/UpdateAppointment.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateAppointmentScene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(updateAppointmentScene);
            updateStage.setTitle("Update Appointment " + selectedAppointmentToUpdate.getId());
            updateStage.show();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appoinment");
            warnAlert.setContentText("Please select an appoinment from the table view");
            warnAlert.show();
        }
    }

}

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.BookedAppointment;
import view.ViewManager;

public class DashboardPatientController implements Initializable {

    Appointment oldAppointment;

    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button showAll;
    @FXML
    private TableView<Appointment> tableAppointments;
    @FXML
    private TableColumn<Appointment, String> date;
    @FXML
    private TableColumn<Appointment, String> day;
    @FXML
    private TableColumn<Appointment, String> time;
    @FXML
    private Button booked;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        day.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        time.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
    }

    @FXML
    private void appoinments(ActionEvent event) {
        ViewManager.dashboardPatient.changeToDashboardPatient();
    }

    @FXML
    private void bookedAppoinments(ActionEvent event) {
        ViewManager.dashboardPatient.changeToBookedAppointments();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeDashboardPatient();
        ViewManager.openLoginPatient();
        ViewManager.dashboardPatient = null;
    }

    @FXML
    private void showAll(ActionEvent event) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList(Appointment.getAllAppointments());
        tableAppointments.setItems(appointments);

    }

    @FXML
    private void booked(ActionEvent event) {
        if (tableAppointments.getSelectionModel().getSelectedItem() != null) {
            Appointment appointments = tableAppointments.getSelectionModel().getSelectedItem();

            Alert bookedConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            bookedConfirmAlert.setTitle("Booked Appointment");
            bookedConfirmAlert.setContentText("Are you sure to booked this appointment ?");
            bookedConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {

                        BookedAppointment bookedAppointment = new BookedAppointment(controller.LoginPatientController.idLoginPatient, appointments.getId(), "waiting", null);
                        System.out.println(controller.LoginPatientController.idLoginPatient);
                        appointments.booked();
                        bookedAppointment.insert();
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardPatientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DashboardPatientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Successflly Booked");
                    deletedSuccessAlert.setContentText("Successflly Booked");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointment");
            warnAlert.setContentText("Please select an appointment from the table view");
            warnAlert.show();
        }
    }

}

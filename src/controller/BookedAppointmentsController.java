package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BookedAppointment;
import view.ViewManager;

public class BookedAppointmentsController implements Initializable {

    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button waitingAppointment;
    @FXML
    private TableView<BookedAppointment> tableAppointments;
    @FXML
    private TableColumn<BookedAppointment, Integer> appointmentId;
    @FXML
    private TableColumn<BookedAppointment, Integer> userId;
    @FXML
    private TableColumn<BookedAppointment, String> status;
    @FXML
    private TableColumn<BookedAppointment, String> comment;
    @FXML
    private Button finishedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        appointmentId.setCellValueFactory(new PropertyValueFactory("userId"));
        userId.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        comment.setCellValueFactory(new PropertyValueFactory("doctorComment"));
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
    private void waitingAppointment(ActionEvent event) throws SQLException {
        ObservableList<BookedAppointment> bookedAppointments = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointmentsWaiting(controller.LoginPatientController.idLoginPatient));
        tableAppointments.setItems(bookedAppointments);
    }

    @FXML
    private void finishedAppointment(ActionEvent event) throws SQLException {
        ObservableList<BookedAppointment> bookedAppointments = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointmentsFinished(controller.LoginPatientController.idLoginPatient));
        tableAppointments.setItems(bookedAppointments);
    }

}

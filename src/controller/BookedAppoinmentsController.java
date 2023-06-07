package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.BookedAppointment;
import view.ViewManager;

public class BookedAppoinmentsController implements Initializable {

    public static BookedAppointment selectedBookedAppointment;
    public static Stage commentStage;

    @FXML
    private Button patients;
    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button ShowAllBookedAppoinments;
    @FXML
    private TableView<BookedAppointment> tableBookedAppoinments;
    @FXML
    private TableColumn<BookedAppointment, Integer> userId;
    @FXML
    private TableColumn<BookedAppointment, Integer> appointmentId;
    @FXML
    private TableColumn<BookedAppointment, String> status;
    @FXML
    private TableColumn<BookedAppointment, String> doctorComment;
    @FXML
    private Button search;
    @FXML
    private Button btnFinished;
    @FXML
    private TextField searchAppointment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        appointmentId.setCellValueFactory(new PropertyValueFactory("userId"));
        userId.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        doctorComment.setCellValueFactory(new PropertyValueFactory("doctorComment"));
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
    private void showAll(ActionEvent event) throws SQLException {
        searchAppointment.setText("");
        ObservableList<BookedAppointment> bookedAppointments = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointments());
        tableBookedAppoinments.setItems(bookedAppointments);
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        if (searchAppointment.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Operation is invalid");
            alert.showAndWait();
        } else {
            ObservableList<BookedAppointment> bookedAppointments = FXCollections.observableArrayList(BookedAppointment.searchBookedAppointmentByName(searchAppointment.getText()));
            tableBookedAppoinments.setItems(bookedAppointments);
        }
    }

    @FXML
    private void btnFinished(ActionEvent event) throws IOException {
        if (tableBookedAppoinments.getSelectionModel().getSelectedItem() != null) {
            this.selectedBookedAppointment = tableBookedAppoinments.getSelectionModel().getSelectedItem();
            FXMLLoader loaderComment = new FXMLLoader(getClass().getResource("/view/AdminFxml/Comment.fxml"));
            Parent rootComment = loaderComment.load();
            Scene updatePatientScene = new Scene(rootComment);
            commentStage = new Stage();
            commentStage.setScene(updatePatientScene);
            commentStage.setTitle("Finished appointment");
            commentStage.show();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointment");
            warnAlert.setContentText("Please select an booked appointment from the table view");
            warnAlert.show();
        }
    }

}

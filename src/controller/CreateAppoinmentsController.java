package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Appointment;
import view.ViewManager;

public class CreateAppoinmentsController implements Initializable {

    @FXML
    private Button patients;
    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtDay;
    @FXML
    private TextField txtDate;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void btnRegister(ActionEvent event) throws SQLException {
        String appoinmenDate = txtDate.getText();
        String appoinmenDay = txtDay.getText();
        String appoinmenTime = txtTime.getText();
        if (appoinmenDate.isEmpty() || appoinmenDay.isEmpty() || appoinmenTime.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("please Fill in all fields!");
            alert.showAndWait();
        } else if (Appointment.checkAppoinments(appoinmenDate, appoinmenTime)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Appointment is already exists!");
            alert.showAndWait();
        } else {
            Appointment appointment = new Appointment(appoinmenDate, appoinmenDay, appoinmenTime, "free");
            appointment.insert();
            emptyFiled();
            ViewManager.dashboardAdmin.changeappoinments();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appoinment inserted");
            alert.setContentText("Appoinment inserted");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        ViewManager.dashboardAdmin.changeappoinments();
    }

    private void emptyFiled() {
        txtDate.setText("");
        txtDay.setText("");
        txtTime.setText("");
    }

}

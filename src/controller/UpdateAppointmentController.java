package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Appointment;

public class UpdateAppointmentController implements Initializable {

    private Appointment oldAppointment;
    @FXML
    private TextField time;
    @FXML
    private TextField day;
    @FXML
    private TextField date;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.oldAppointment = controller.AppoinmentsController.selectedAppointmentToUpdate;
        date.setText(oldAppointment.getAppointmentDate());
        day.setText(oldAppointment.getAppointmentDay());
        time.setText(oldAppointment.getAppointmentTime());
    }

    @FXML
    private void btnSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String adate = date.getText();
        String aday = day.getText();
        String atime = time.getText();
        if (adate.isEmpty() || aday.isEmpty() || atime.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("please Fill in all fields!");
            alert.showAndWait();
        } else if (Appointment.checkAppoinments(adate, aday)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Appointment is already exists!");
            alert.showAndWait();
        } else {
            Appointment newUAppointment = new Appointment(adate, aday, atime, "free");
            newUAppointment.setId(oldAppointment.getId());
            newUAppointment.update();
            controller.AppoinmentsController.updateStage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment updated");
            alert.setContentText("Appointment updated");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        controller.AppoinmentsController.updateStage.close();
    }

}

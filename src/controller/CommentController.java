package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.BookedAppointment;

public class CommentController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextArea comment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String savedComment = comment.getText();
        if (savedComment.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("please Fill in all fields!");
            alert.showAndWait();
        } else {
            BookedAppointment bookedAppointment = new BookedAppointment(controller.BookedAppoinmentsController.selectedBookedAppointment.getUserId(), controller.BookedAppoinmentsController.selectedBookedAppointment.getAppointmentId(), controller.BookedAppoinmentsController.selectedBookedAppointment.getStatus(), savedComment);
            bookedAppointment.setId(controller.BookedAppoinmentsController.selectedBookedAppointment.getId());
            bookedAppointment.finishedBookedAppointment();
            controller.BookedAppoinmentsController.commentStage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Finished");
            alert.setContentText("Appointment Finished");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        controller.BookedAppoinmentsController.commentStage.close();
    }

}

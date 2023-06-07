package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardPatient extends Stage {

    private Scene dashboardPatient;
    private Scene bookedAppointments;

    public DashboardPatient() throws IOException {
        FXMLLoader dashboardPatientFxml = new FXMLLoader(getClass().getResource("PatientFxml/DashboardPatient.fxml"));
        Parent dashboardPatientRoot = dashboardPatientFxml.load();
        dashboardPatient = new Scene(dashboardPatientRoot);

        FXMLLoader bookedAppointmentsFxml = new FXMLLoader(getClass().getResource("PatientFxml/BookedAppointments.fxml"));
        Parent bookedAppointmentsRoot = bookedAppointmentsFxml.load();
        bookedAppointments = new Scene(bookedAppointmentsRoot);
        
        this.setTitle("Dashboar Patient");
        this.setScene(dashboardPatient);

    }

    public void changeToDashboardPatient() {
        this.setScene(dashboardPatient);
    }

    public void changeToBookedAppointments() {
        this.setScene(bookedAppointments);
    }
}

package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardAdmin extends Stage {

    private Scene dashboardAdmin;
    private Scene createPatient;
    private Scene appoinments;
    private Scene createAppoinment;
    private Scene bookedAppoinments;

    public DashboardAdmin() throws IOException {
        FXMLLoader dashboardAdminFxml = new FXMLLoader(getClass().getResource("AdminFxml/DashboardAdmin.fxml"));
        Parent dashboardAdminRoot = dashboardAdminFxml.load();
        dashboardAdmin = new Scene(dashboardAdminRoot);

        FXMLLoader createPatientFxml = new FXMLLoader(getClass().getResource("AdminFxml/CreatePatient.fxml"));
        Parent createPatientRoot = createPatientFxml.load();
        createPatient = new Scene(createPatientRoot);

        FXMLLoader appoinmentsFxml = new FXMLLoader(getClass().getResource("AdminFxml/Appoinments.fxml"));
        Parent appoinmentsRoot = appoinmentsFxml.load();
        appoinments = new Scene(appoinmentsRoot);

        FXMLLoader createAppoinmentFxml = new FXMLLoader(getClass().getResource("AdminFxml/CreateAppoinment.fxml"));
        Parent createAppoinmentRoot = createAppoinmentFxml.load();
        createAppoinment = new Scene(createAppoinmentRoot);

        FXMLLoader bookedAppoinmentsFxml = new FXMLLoader(getClass().getResource("AdminFxml/BookedAppoinments.fxml"));
        Parent bookedAppoinmentsRoot = bookedAppoinmentsFxml.load();
        bookedAppoinments = new Scene(bookedAppoinmentsRoot);

        this.setTitle("Dashboar Admin");
        this.setScene(dashboardAdmin);
    }

    public void changeCreatepatient() {
        this.setScene(createPatient);
    }

    public void changeDashboardAdmin() {
        this.setScene(dashboardAdmin);
    }

    public void changeappoinments() {
        this.setScene(appoinments);
    }

    public void changeCreateAppoinments() {
        this.setScene(createAppoinment);
    }

    public void changeBookedAppoinments() {
        this.setScene(bookedAppoinments);
    }

}

package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPatient extends Stage {

    private Scene loginPatient;

    public LoginPatient() throws IOException {
        FXMLLoader loginPatientFxml = new FXMLLoader(getClass().getResource("PatientFxml/LoginPatient.fxml"));
        Parent loginPatientRoot = loginPatientFxml.load();
        loginPatient = new Scene(loginPatientRoot);
        this.setTitle("Login Patient");
        this.setScene(loginPatient);
    }
}

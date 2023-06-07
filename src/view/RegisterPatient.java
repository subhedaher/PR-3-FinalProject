package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterPatient extends Stage {

    private Scene registerPatient;

    public RegisterPatient() throws IOException {
        FXMLLoader RegisterPatientFxml = new FXMLLoader(getClass().getResource("PatientFxml/RegisterPatient.fxml"));
        Parent RegisterPatientRoot = RegisterPatientFxml.load();
        registerPatient = new Scene(RegisterPatientRoot);
        this.setTitle("Register Patient");
        this.setScene(registerPatient);
    }
}

package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginAdmin extends Stage {

    private Scene loginAdmin;

    public LoginAdmin() throws IOException {
        FXMLLoader loginAdminFxml = new FXMLLoader(getClass().getResource("AdminFxml/LoginAdmin.fxml"));
        Parent loginAdminRoot = loginAdminFxml.load();
        loginAdmin = new Scene(loginAdminRoot);
        this.setTitle("Login Admin");
        this.setScene(loginAdmin);
    }
}

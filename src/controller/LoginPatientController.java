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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import view.ViewManager;

public class LoginPatientController implements Initializable {
    
    public static int idLoginPatient;
    
    @FXML
    private Button btnLoginAdmin;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink createAccount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void btnLoginAdmin(ActionEvent event) throws IOException {
        txtUserName.setText("");
        txtPassword.setText("");
        ViewManager.closeLoginPatient();
        ViewManager.openLoginAdmin();
    }
    
    @FXML
    private void btnLogin(ActionEvent event) throws IOException, SQLException {
        boolean check = User.loginPatient(txtUserName.getText(), txtPassword.getText());
        boolean test = true;
        if (check) {
            test = false;
            User user = new User("", "", "", "", 0, "", "", "", "");
            this.idLoginPatient = user.getId(txtUserName.getText());
            ViewManager.closeLoginPatient();
            ViewManager.openDashboardPatient();
        }
        if (test) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Incorrect username or password!");
            alert.showAndWait();
        }
        txtUserName.setText("");
        txtPassword.setText("");
    }
    
    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        ViewManager.closeLoginPatient();
        ViewManager.openRegisterPatient();
    }
    
}

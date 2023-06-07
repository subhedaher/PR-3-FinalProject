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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.User;
import view.ViewManager;

public class LoginAdminController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;
    @FXML
    private ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException, SQLException {
        boolean check = User.loginAdmin(txtUserName.getText(), txtPassword.getText());
        boolean test = true;
        if (check) {
            test = false;
            ViewManager.closeLoginAdmin();
            ViewManager.openDashboardAdmin();
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
    private void btnCancel(ActionEvent event) throws IOException {
        txtUserName.setText("");
        txtPassword.setText("");
        ViewManager.closeLoginAdmin();
        ViewManager.openLoginPatient();
    }

}

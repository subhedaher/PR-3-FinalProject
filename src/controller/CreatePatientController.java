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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.User;
import view.ViewManager;

public class CreatePatientController implements Initializable {

    @FXML
    private Button patients;
    @FXML
    private Button appoinments;
    @FXML
    private Button bookedAppoinments;
    @FXML
    private Button logout;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtFistName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtPhone;
    @FXML
    private RadioButton txtMale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton txtFemale;
    @FXML
    private RadioButton txtAdmin;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton txtPatient;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtEmail;

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
    private void btnCancel(ActionEvent event) {
        emptyFiled();
        ViewManager.dashboardAdmin.changeDashboardAdmin();
    }

    @FXML
    private void btnRegister(ActionEvent event) throws IOException, SQLException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String firstname = txtFistName.getText();
        String lastname = txtLastName.getText();
        String age = txtAge.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String genderr = ((RadioButton) gender.getSelectedToggle()).getText();
        String rolee = ((RadioButton) role.getSelectedToggle()).getText();
        if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("please Fill in all fields!");
            alert.showAndWait();
        } else if (User.checkUserName(username)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Username already exists!");
            alert.showAndWait();
        } else {
            int agee = Integer.parseInt(txtAge.getText());
            User user = new User(username, password, firstname, lastname, agee, email, phone, genderr, rolee);
            user.insert();
            emptyFiled();
            ViewManager.dashboardAdmin.changeDashboardAdmin();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User inserted");
            alert.setContentText("User inserted");
            alert.showAndWait();
        }
    }

    private void emptyFiled() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtFistName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

}

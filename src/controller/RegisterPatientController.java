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

public class RegisterPatientController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnRegister;
    @FXML
    private RadioButton txtFemale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton txtMale;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFistName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnRegister(ActionEvent event) throws SQLException, IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String firstname = txtFistName.getText();
        String lastname = txtLastName.getText();
        String age = txtAge.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String genderr = ((RadioButton) gender.getSelectedToggle()).getText();
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
            User user = new User(username, password, firstname, lastname, agee, email, phone, genderr, "patient");
            user.insert();
            emptyFiled();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User inserted");
            alert.setContentText("User inserted");
            alert.showAndWait();
            ViewManager.closeRegisterPatient();
            ViewManager.openLoginPatient();
        }

    }

    @FXML
    private void btnCancel(ActionEvent event) throws IOException {
        emptyFiled();
        ViewManager.closeRegisterPatient();
        ViewManager.openLoginPatient();
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

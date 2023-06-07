package controller;

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

public class UpdatePatientController implements Initializable {

    private User oldPatient;

    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton txtPatient;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton txtAdmin;
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
    private Button btnRegister;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.oldPatient = controller.DashboardAdminController.selectedPatientToUpdate;
        txtUserName.setText(oldPatient.getUserName());
        txtPassword.setText(oldPatient.getPassword());
        txtFistName.setText(oldPatient.getFirstName());
        txtLastName.setText(oldPatient.getLastName());
        txtAge.setText(oldPatient.getAge() + "");
        txtEmail.setText(oldPatient.getEmail());
        txtPhone.setText(oldPatient.getPhone());
        if (oldPatient.getGender().equals("female")) {
            gender.selectToggle(txtFemale);
        }
        if (oldPatient.getRole().equals("admin")) {
            role.selectToggle(txtAdmin);
        }
    }

    @FXML
    private void btnSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String firstName = txtFistName.getText();
        String lastName = txtLastName.getText();
        String age = txtAge.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String genderr = ((RadioButton) gender.getSelectedToggle()).getText();
        String rolee = ((RadioButton) role.getSelectedToggle()).getText();
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty()) {
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
            int agee = Integer.parseInt(age);
            User newUPatient = new User(username, password, firstName, lastName, agee, email, phone, genderr, rolee);
            newUPatient.setId(oldPatient.getId());
            newUPatient.update();
            controller.DashboardAdminController.updateStage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient updated");
            alert.setContentText("Patient updated");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        controller.DashboardAdminController.updateStage.close();
    }

}

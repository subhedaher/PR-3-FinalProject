package view;

import java.io.IOException;

public class ViewManager {

    public static LoginPatient loginPatient = null;
    public static LoginAdmin loginAdmin = null;
    public static RegisterPatient registerPatient = null;
    public static DashboardAdmin dashboardAdmin = null;
    public static DashboardPatient dashboardPatient = null;

    private ViewManager() {
    }

    public static void openLoginPatient() throws IOException {
        if (loginPatient == null) {
            loginPatient = new LoginPatient();
            loginPatient.show();
        }
        loginPatient.show();
    }

    public static void closeLoginPatient() throws IOException {
        if (loginPatient == null) {
            loginPatient = new LoginPatient();
            loginPatient.close();
        }
        loginPatient.close();
    }

    public static void openLoginAdmin() throws IOException {
        if (loginAdmin == null) {
            loginAdmin = new LoginAdmin();
            loginAdmin.show();
        }
        loginAdmin.show();
    }

    public static void closeLoginAdmin() throws IOException {
        if (loginAdmin == null) {
            loginAdmin = new LoginAdmin();
            loginAdmin.close();
        }
        loginAdmin.close();
    }

    public static void openRegisterPatient() throws IOException {
        if (registerPatient == null) {
            registerPatient = new RegisterPatient();
            registerPatient.show();
        }
        registerPatient.show();
    }

    public static void closeRegisterPatient() throws IOException {
        if (registerPatient == null) {
            registerPatient = new RegisterPatient();
            registerPatient.close();
        }
        registerPatient.close();
    }

    public static void openDashboardAdmin() throws IOException {
        if (dashboardAdmin == null) {
            dashboardAdmin = new DashboardAdmin();
            dashboardAdmin.show();
        }
        dashboardAdmin.show();
    }

    public static void closeDashboardAdmin() throws IOException {
        if (dashboardAdmin == null) {
            dashboardAdmin = new DashboardAdmin();
            dashboardAdmin.close();
        }
        dashboardAdmin.close();
    }

    public static void openDashboardPatient() throws IOException {
        if (dashboardPatient == null) {
            dashboardPatient = new DashboardPatient();
            dashboardPatient.show();
        }
        dashboardPatient.show();
    }

    public static void closeDashboardPatient() throws IOException {
        if (dashboardPatient == null) {
            dashboardPatient = new DashboardPatient();
            dashboardPatient.close();
        }
        dashboardPatient.close();
    }

}

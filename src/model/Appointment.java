package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Appointment {

    private int id;
    private String appointmentDate;
    private String appointmentDay;
    private String appointmentTime;
    private String status;

    public Appointment(String appointmentDate, String appointmentDay, String appointmentTime, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int insert() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "insert into appointments (id,appointment_date,appointment_day,appointment_time,status) values (?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getAppointmentDate());
        ps.setString(3, this.getAppointmentDay());
        ps.setString(4, this.getAppointmentTime());
        ps.setString(5, this.getStatus());

        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getId() + " Appointment was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static boolean checkAppoinments(String date, String time) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from appointments where appointment_date	= ? and appointment_time = ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, date);
        ps.setString(2, time);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Appointment> getAllAppointments() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "select * from appointments where status = 'free'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appointment appointment = new Appointment(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            appointment.setId(rs.getInt(1));
            appointments.add(appointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE appointments SET appointment_date=?, appointment_day=?, appointment_time=? , status=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getAppointmentDate());
        ps.setString(2, this.getAppointmentDay());
        ps.setString(3, this.getAppointmentTime());
        ps.setString(4, this.getStatus());
        ps.setInt(5, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment with id : " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM appointments WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The appoinment with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int booked() throws SQLException, ClassNotFoundException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE appointments SET status = 'booked' WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment with id : " + this.getId() + " was booked successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}

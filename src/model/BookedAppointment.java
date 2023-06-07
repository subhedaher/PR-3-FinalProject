package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookedAppointment {

    private int id;
    private int userId;
    private int appointmentId;
    private String status;
    private String doctorComment;

    public BookedAppointment(int userId, int appointmentId, String status, String doctorComment) {
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.status = status;
        this.doctorComment = doctorComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public int insert() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "insert into booked_appointments (id,appointment_id ,user_id ,status,doctor_comment) values (?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getAppointmentId());
        ps.setInt(3, this.getUserId());
        ps.setString(4, this.getStatus());
        ps.setString(5, this.getDoctorComment());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getId() + " Booked Appointments was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<BookedAppointment> getAllBookedAppointmentsWaiting(int userId) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "select * from booked_appointments where user_id = ? and status = 'waiting'";
        ps = c.prepareStatement(sql);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            bookedAppointment.setId(rs.getInt(1));
            bookedAppointments.add(bookedAppointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

    public static ArrayList<BookedAppointment> getAllBookedAppointmentsFinished(int userId) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "select * from booked_appointments where user_id = ? and status = 'finished'";
        ps = c.prepareStatement(sql);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            bookedAppointment.setId(rs.getInt(1));
            bookedAppointments.add(bookedAppointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

    public static ArrayList<BookedAppointment> getAllBookedAppointments() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "select * from booked_appointments";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            bookedAppointment.setId(rs.getInt(1));
            bookedAppointments.add(bookedAppointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

    public int finishedBookedAppointment() throws SQLException, ClassNotFoundException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE booked_appointments SET status = 'finished', doctor_comment=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getDoctorComment());
        ps.setInt(2, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment with id : " + this.getId() + " was finished successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<BookedAppointment> searchBookedAppointmentByName(String firstName) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "select * from booked_appointments WHERE user_id = ? ";
        ps = c.prepareStatement(sql);
        User u = new User("", "", "", "", 0, "", "", "", "");
        int userId = u.getId(firstName);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            bookedAppointment.setId(rs.getInt(1));
            bookedAppointments.add(bookedAppointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

}

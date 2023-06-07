package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(String userName, String password, String firstName, String lastName, int age, String email, String phone, String gender, String role) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int insert() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "insert into users (id,username,password,firstname,lastname,age,email,phone,gender,role) values (?,?,?,?,?,?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getUserName());
        ps.setString(3, this.getPassword());
        ps.setString(4, this.getFirstName());
        ps.setString(5, this.getLastName());
        ps.setInt(6, this.getAge());
        ps.setString(7, this.getEmail());
        ps.setString(8, this.getPhone());
        ps.setString(9, this.getGender());
        ps.setString(10, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUserName() + " User was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static boolean checkUserName(String userName) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from users where username = ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, userName);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<User> getAllPatients() throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users where role = 'patient'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            user.setId(rs.getInt(1));
            users.add(user);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }

    public static boolean loginAdmin(String username, String password) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users where username = ? and password = ? and role = 'admin'";
        ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return false;
    }

    public static boolean loginPatient(String username, String password) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from users where username = ? and password = ? and role = 'patient'";
        ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return false;
    }

    public static ArrayList<User> searchPatientByName(String firstName) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users where firstname = ? and role = 'patient'";
        ps = c.prepareStatement(sql);
        ps.setString(1, firstName);
        rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            user.setId(rs.getInt(1));
            users.add(user);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE USERS SET USERNAME=?, PASSWORD=?, firstname=? , lastname=?,age=?,email=?,phone=?,gender=?,role=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getUserName());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstName());
        ps.setString(4, this.getLastName());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        ps.setInt(10, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Patient with name : " + this.getFirstName() + " was updated successfully!");
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
        String sql = "DELETE FROM USERS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with name : " + this.getFirstName() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int getId(String userName) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        String sql = "select id FROM USERS WHERE userName=?";
        int id = -1;
        ps = c.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static String getNameById(int userId) throws SQLException {
        Connection c = DataBase.getInstance().getConnection();
        PreparedStatement ps = null;
        String sql = "select firstname FROM USERS WHERE id=?";
        String name = "";
        ps = c.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            name = rs.getString("firstname");
        }
        return name;
    }
}

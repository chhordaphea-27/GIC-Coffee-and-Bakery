package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;

public class UserManagement extends Management<User> {
    @Override
    protected User mapRowToModel(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setSex(rs.getString("sex"));
        user.setRole(rs.getString("role"));
        user.setDob(rs.getDate("dob"));
        user.setAge(rs.getInt("age"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    
    
    public void addUser(User user) {
        String query = "INSERT INTO user (first_name, last_name, sex, role, dob, age, username, password) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSex());
            stmt.setString(4, user.getRole());
            stmt.setDate(5, (Date) user.getDob());
            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getUsername());
            stmt.setString(8, user.getPassword());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET first_name = ?, last_name = ?, sex = ?, role = ?, dob = ?, age = ?, " +
                       "username = ?, password = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSex());
            stmt.setString(4, user.getRole());

            java.util.Date utilDate = user.getDob();
            Date sqlDate = new Date(utilDate.getTime());
            stmt.setDate(5, sqlDate);

            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getUsername());
            stmt.setString(8, user.getPassword());
            stmt.setInt(9, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("No user found with the given ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        return getAll(query);
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        return getById(id, query);
    }

    public List<User> searchUsers(String keyword) {
        String query = "SELECT * FROM user WHERE first_name LIKE ? OR last_name LIKE ?";
        return query(keyword, query);
    }

    public User loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.Connection;
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
        add(user, query);
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET first_name = ?, last_name = ?, sex = ?, role = ?, " +
                "dob = ?, age = ?, username = ?, password = ? WHERE id = ?";
        update(user, query);
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
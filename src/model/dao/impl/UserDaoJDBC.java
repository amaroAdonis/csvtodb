package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.UserDao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBC implements UserDao {

    private Connection conn;

    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<String> readAllUsers() {
        String path = "C:/Users/amaro/Downloads/Users tests - Página1.csv";
        List<String> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (i > 0) {
                    users.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return users;
    }


    @Override
    public void importToDB(List<String> users) throws NullPointerException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO users (User, firstName, lastName, email) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            for (var user : users) {
                String splitter = ",";
                String[] userSplitted = user.split(splitter);
                st.setString(1, userSplitted[0]);
                st.setString(2, userSplitted[1]);
                st.setString(3, userSplitted[2]);
                st.setString(4, userSplitted[3]);

                st.executeUpdate();

            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public List<String> findAll() {
        List<String> usersList = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM users ORDER BY User");

            rs = st.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    usersList.add((String) rs.getObject(i));
                }

            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return usersList;
    }

}

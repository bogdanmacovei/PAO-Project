package Clase.service;

import Clase.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlUserService {

    private User user;

    @Value("${strcon}")
    private String strcon;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<User> selectAll() throws SQLException {
        List<User> listUsers = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("SELECT id, first_name, last_name, user_name, img from mydb.userCvorum");
            resultSet = preparedStatement.executeQuery();
            listUsers = writeResultSet(resultSet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
            return listUsers;
        }
    }

    public List<User> writeResultSet(ResultSet resultSet) throws SQLException {
        List<User> listUsers = new ArrayList<>();
        while (resultSet.next()) {

            final User.Builder builder = User.builder();

            builder.withId(resultSet.getInt("id"))
                   .withFirstName(resultSet.getString("first_name"))
                   .withLastName(resultSet.getString("last_name"))
                   .withUserName(resultSet.getString("user_name"))
                   .withImg(resultSet.getString("img"));

            final User user = builder.build();

            listUsers.add(user);
        }

        return listUsers;
    }

    public void insertUser(User user) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into mydb.userCvorum (first_name, last_name, user_name, password)" +
                            " values (?, ?, ?, ?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void updateImage(UpdateImage updateImage) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update mydb.userCvorum set img = ? where id = ?");
            preparedStatement.setString(1, updateImage.getImg());
            preparedStatement.setInt(2, updateImage.getId());

            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void deleteUser(int ID) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("delete from mydb.userCvorum where id = ? ; ");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

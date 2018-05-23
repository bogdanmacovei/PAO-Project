package Clase.service;

import Clase.model.UtilizatorPAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.rmi.CORBA.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlUtilizatorPAOService {
    private UtilizatorPAO utilizatorPAO;

    @Value("${strcon}")
    private String strcon;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<UtilizatorPAO> selectAll () throws SQLException {
        List<UtilizatorPAO> listUtilizatori = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("SELECT * FROM mydb.utilizatorPAO");
            resultSet = preparedStatement.executeQuery();
            listUtilizatori = writeResultSet(resultSet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listUtilizatori;
    }

    private List<UtilizatorPAO> writeResultSet (ResultSet resultSet) throws SQLException {
        List<UtilizatorPAO> listUtilizatori = new ArrayList<>();

        while(resultSet.next()) {
            final UtilizatorPAO.Builder builder = UtilizatorPAO.builder();

            builder.withId(resultSet.getInt("id"))
                   .withNume(resultSet.getString("nume"))
                   .withPrenume(resultSet.getString("prenume"))
                   .withUsername(resultSet.getString("username"))
                   .withParola(resultSet.getString("parola"))
                   .withCategorie(resultSet.getString("categorie"));

            final UtilizatorPAO utilizatorPAO = builder.build();

            listUtilizatori.add(utilizatorPAO);
        }

        return listUtilizatori;
    }

    public void insertUser(UtilizatorPAO utilizatorPAO) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("insert into mydb.utilizatorPAO (nume, " +
                    "prenume, username, parola, categorie) values (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, utilizatorPAO.getNume());
            preparedStatement.setString(2, utilizatorPAO.getPrenume());
            preparedStatement.setString(3, utilizatorPAO.getUsername());
            preparedStatement.setString(4, utilizatorPAO.getParola());
            preparedStatement.setString(5, utilizatorPAO.getCategorie());
            preparedStatement.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void deleteUser (int id) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("delete from mydb.utilizatorPAO where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public String getPassword (String username) throws SQLException {
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("SELECT parola FROM mydb.utilizatorPAO " +
                    "where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            password = resultSet.getString("parola");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return password;
    }

    public String getCategory (String username) throws SQLException {
        String category = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement ("select categorie from mydb.utilizatorPAO " +
                    "where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            category = resultSet.getString("categorie");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return category;
    }

    public void updateCategory (UpdateCategorie updateCategorie) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update mydb.utilizatorPAO set categorie = ? where " +
                    "username = ?");

            preparedStatement.setString(1, updateCategorie.getCategorie());
            preparedStatement.setString(2, updateCategorie.getUsername());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public int getId (String username) throws SQLException {
        int id = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("select id from mydb.utilizatorPAO where " +
                    "username = ?");
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            id = resultSet.getInt("id");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return id;
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

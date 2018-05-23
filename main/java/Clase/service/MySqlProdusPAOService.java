package Clase.service;

import Clase.model.ProdusPAO;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProdusPAOService {
    private ProdusPAO produsPAO;

    @Value("${strcon}")
    private String strcon;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<ProdusPAO> selectAll () throws SQLException {
        List<ProdusPAO> listProduse = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * FROM produsPAO WHERE cantitate > 0");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final ProdusPAO.Builder builder = ProdusPAO.builder();

                builder.withId(resultSet.getInt("id"))
                       .withNume(resultSet.getString("nume"))
                       .withCategorie(resultSet.getString("categorie"))
                       .withPretUnitar(resultSet.getDouble("pret_unitar"))
                       .withCantitate(resultSet.getInt("cantitate"))
                       .withImagine(resultSet.getString("imagine"));

                final ProdusPAO produsPAO = builder.build();

                listProduse.add(produsPAO);

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listProduse;
    }

    public List<ProdusPAO> selectAllByCategory (String category) throws SQLException {
        List<ProdusPAO> listProduse = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * FROM produsPAO where categorie = ? AND cantitate > 0");
            preparedStatement.setString(1, category);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final ProdusPAO.Builder builder = ProdusPAO.builder();

                builder.withId(resultSet.getInt("id"))
                       .withNume(resultSet.getString("nume"))
                       .withCategorie(resultSet.getString("categorie"))
                       .withPretUnitar(resultSet.getDouble("pret_unitar"))
                       .withCantitate(resultSet.getInt("cantitate"))
                       .withImagine(resultSet.getString("imagine"));

                final ProdusPAO produsPAO = builder.build();

                listProduse.add(produsPAO);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listProduse;
    }

    public List<ProdusPAO> selectLike (String name) throws SQLException {
        List<ProdusPAO> listProduse = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            String sql = "SELECT * FROM mydb.produsPAO WHERE nume LIKE ? and cantitate > 0";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final ProdusPAO.Builder builder = ProdusPAO.builder();

                builder.withId(resultSet.getInt("id"))
                        .withNume(resultSet.getString("nume"))
                        .withCategorie(resultSet.getString("categorie"))
                        .withPretUnitar(resultSet.getDouble("pret_unitar"))
                        .withCantitate(resultSet.getInt("cantitate"))
                        .withImagine(resultSet.getString("imagine"));

                final ProdusPAO produsPAO = builder.build();

                listProduse.add(produsPAO);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listProduse;
    }

    public List<ProdusPAO> selectById (int id) throws SQLException {
        List<ProdusPAO> listProduse = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * from mydb.produsPAO where id = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final ProdusPAO.Builder builder = ProdusPAO.builder();

                builder.withId(resultSet.getInt("id"))
                        .withNume(resultSet.getString("nume"))
                        .withCategorie(resultSet.getString("categorie"))
                        .withPretUnitar(resultSet.getDouble("pret_unitar"))
                        .withCantitate(resultSet.getInt("cantitate"))
                        .withImagine(resultSet.getString("imagine"));

                final ProdusPAO produsPAO = builder.build();

                listProduse.add(produsPAO);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listProduse;
    }

    public void delete(int id) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("DELETE FROM produsPAO where id = ?");
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

    public void insert (ProdusPAO produsPAO) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("insert into mydb.produsPAO (nume, " +
                    "categorie, pret_unitar, cantitate, imagine) values (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, produsPAO.getNume());
            preparedStatement.setString(2, produsPAO.getCategorie());
            preparedStatement.setDouble(3, produsPAO.getPret_unitar());
            preparedStatement.setInt(4, produsPAO.getCantitate());
            preparedStatement.setString(5, produsPAO.getImagine());
            preparedStatement.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void update (UpdateCantitate updateCantitate) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("UPDATE mydb.produsPAO set cantitate = cantitate - 1 " +
                    "where id = ?");

            preparedStatement.setInt(1, updateCantitate.getId());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void updatePlus (UpdateCantitate updateCantitate) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("UPDATE mydb.produsPAO set cantitate = cantitate + 1 " +
                    "where id = ?");

            preparedStatement.setInt(1, updateCantitate.getId());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
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

package Clase.service;

import Clase.model.OrdDetailsPAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlOrdDetailsPAOService {
    private OrdDetailsPAO ordDetailsPao;

    @Value("${strcon}")
    private String strcon;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public List<OrdDetailsPAO> selectAll () throws SQLException {
        List<OrdDetailsPAO> listOrdDetails = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * FROM ordDetailsPAO");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                final OrdDetailsPAO.Builder builder = OrdDetailsPAO.builder();

                builder.withId(resultSet.getInt("id"))
                       .withIdUtilizator(resultSet.getInt("idUtilizator"))
                       .withIdProdus(resultSet.getInt("idProdus"))
                       .withCantitate(resultSet.getInt("cantitate"))
                       .withPret(resultSet.getDouble("pret"));

                final OrdDetailsPAO ordDetailsPAO = builder.build();

                listOrdDetails.add(ordDetailsPAO);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listOrdDetails;
    }

    public List<OrdDetailsPAO> selectByUserId (int id) throws SQLException {
        List<OrdDetailsPAO> listOrdDetails = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * FROM ordDetailsPAO WHERE idUtilizator = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                final OrdDetailsPAO.Builder builder = OrdDetailsPAO.builder();

                builder.withId(resultSet.getInt("id"))
                        .withIdUtilizator(resultSet.getInt("idUtilizator"))
                        .withIdProdus(resultSet.getInt("idProdus"))
                        .withCantitate(resultSet.getInt("cantitate"))
                        .withPret(resultSet.getDouble("pret"));

                final OrdDetailsPAO ordDetailsPAO = builder.build();

                listOrdDetails.add(ordDetailsPAO);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listOrdDetails;
    }

    public List<OrdDetailsPAO> selectByProdusId (int id) throws SQLException {
        List<OrdDetailsPAO> listOrdDetails = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT * " +
                    "FROM ordDetailsPAO WHERE idProdus = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                final OrdDetailsPAO.Builder builder = OrdDetailsPAO.builder();

                builder.withId(resultSet.getInt("id"))
                        .withIdUtilizator(resultSet.getInt("idUtilizator"))
                        .withIdProdus(resultSet.getInt("idProdus"))
                        .withCantitate(resultSet.getInt("cantitate"))
                        .withPret(resultSet.getDouble("pret"));

                final OrdDetailsPAO ordDetailsPAO = builder.build();

                listOrdDetails.add(ordDetailsPAO);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listOrdDetails;
    }

    public void insert (OrdDetailsPAO ordDetailsPAO) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("INSERT INTO mydb.ordDetailsPAO (idUtilizator, idProdus, " +
                    "cantitate, pret) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, ordDetailsPAO.getIdUtilizator());
            preparedStatement.setInt(2, ordDetailsPAO.getIdProdus());
            preparedStatement.setInt(3, ordDetailsPAO.getCantitate());
            preparedStatement.setDouble(4, ordDetailsPAO.getPret());

            preparedStatement.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void delete(int id) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("DELETE FROM mydb.ordDetailsPAO where id = ?");
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

    public List<ProdRaport> getRaportProducts () throws SQLException {
        List<ProdRaport> listProduse = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT idProdus, SUM(cantitate), SUM(pret) " +
                    "FROM ordDetailsPAO GROUP BY idProdus");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idProdus");
                int cantitate = resultSet.getInt(2);
                double pret = resultSet.getDouble(3);

                final ProdRaport prodRaport = new ProdRaport(id, cantitate, pret);

                listProduse.add(prodRaport);
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

    public List<ClientRaport> getRaportClient () throws SQLException {
        List<ClientRaport> listClient = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(strcon);

            preparedStatement = connect.prepareStatement("SELECT idUtilizator, SUM(cantitate), SUM(pret) " +
                    "FROM ordDetailsPAO GROUP BY idUtilizator");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idUtilizator");
                int cantitate = resultSet.getInt(2);
                double pret = resultSet.getDouble(3);

                final ClientRaport clientRaport = new ClientRaport(id, cantitate, pret);

                listClient.add(clientRaport);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }

        return listClient;
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

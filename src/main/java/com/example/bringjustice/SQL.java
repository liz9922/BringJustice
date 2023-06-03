package com.example.bringjustice;
import java.sql.*;

public class SQL {

    private Connection connection;

    public SQL() {

    }

    // funkar
    public void connect(String databasePath) {
        try {
            // Anslut till databasen
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath + ".db");
            System.out.println("Databasanslutning etablerad.");
        } catch (SQLException e) {
            System.err.println("Anslutningsfel: " + e.getMessage());
        }
    }

    // funkar
    public void disconnect() {
        try {
            // Stänger anslutningen till databasen
            if (connection != null) {
                connection.close();
                System.out.println("Databasanslutning stängd.");
            }
        } catch (SQLException e) {
            System.err.println("Stängningsfel: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query, Object... params) {
        ResultSet resultSet = null;

        try {
            // Skapa en PreparedStatement med den givna frågan
            PreparedStatement statement = connection.prepareStatement(query);

            // Ange värdena för parametrarna i frågan
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Kör frågan och returnera resultatet
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQL-frågefel: " + e.getMessage());
        }

        return resultSet;
    }

    public int executeUpdate(String query, Object... params) {
        int rowsAffected = 0;

        try {
            // Skapa en PreparedStatement med den givna frågan
            PreparedStatement statement = connection.prepareStatement(query);

            // Ange värdena för parametrarna i frågan
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // Kör uppdateringsfrågan och returnera antalet påverkade rader
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL-uppdateringsfel: " + e.getMessage());
        }

        return rowsAffected;
    }

    public void insertPlayer(Player player) {
        String query = "INSERT INTO player(userName, password, exempel1, exempel2)"
                + " VALUES(?,?,?,?);";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, player.getName());
            prepared.setString(2, player.getPassword());
            prepared.setString(3, player.getCaptured());
            prepared.setString(4, player.getKilled());
            prepared.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gick inte att lägga till denna playern.");
        }
    }

    // Exempel på metoder för att spara och hämta spelarstatistik
    public void savePlayerStats(String playerName, int numCaptures, int killed) {
        String query = "INSERT INTO player_stats (player_name, captures, killed) VALUES (?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"public void savePlayerStats");
        }

    }

    public ResultSet getPlayerStats(String playerName) {
        String query = "SELECT * FROM player_stats WHERE player_name = '" + playerName + "'";
        return executeQuery(query);
    }
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS player ("
                + "player_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "player_name VARCHAR(30),"
                + "password VARCHAR(30)"
                + ");";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            System.out.println("Tabell 'player' skapad.");
        } catch (SQLException e) {
            System.err.println("Fel med att skapa tabell 'player': " + e.getMessage());
        }
    }

    public void createTablePlayerStatistik() {
        String query = "CREATE TABLE IF NOT EXISTS playerStatistik ("
                + "captured INTEGER,"
                + "killed INTEGER"
                + ");";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            System.out.println("Tabell 'playerStatistik' skapad.");
        } catch (SQLException e) {
            System.err.println("Fel med att skapa tabell 'playerStatistik': " + e.getMessage());
        }
    }

}
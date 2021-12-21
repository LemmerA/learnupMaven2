package ru.learnup.javaqa.learnupmvn2.jdbc;

import ru.learnup.javaqa.learnupmvn2.jdbc.entities.Steps;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.StepsLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    private Connection conn;

    public DbHelper (String url, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Steps> getAllSteps() {
        try {
            List<Steps> result = new ArrayList<>();
            final Statement statement = conn.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM steps");
            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final int day = resultSet.getInt("day");
                final int steps = resultSet.getInt("steps");
                final Timestamp dateCreate = resultSet.getTimestamp("date_create");
                final Timestamp dateUpdate = resultSet.getTimestamp("date_update");

                result.add(
                        new Steps(id, day, steps, dateCreate, dateUpdate));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StepsLog> getAllStepsLog() {
        try {
            List<StepsLog> result = new ArrayList<>();
            final Statement statement = conn.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM steps_log");
            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final int day = resultSet.getInt("day");
                final int steps = resultSet.getInt("steps");
                final Timestamp dateCreate = resultSet.getTimestamp("date_create");
                final boolean isCommitted = resultSet.getBoolean("is_committed");

                result.add(
                        new StepsLog(id,day, steps, dateCreate, isCommitted));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addSteps(StepsLog steps) {
        try {
            final PreparedStatement logQuery = conn.prepareStatement("INSERT INTO steps_log (day, steps, date_create) VALUES (?, ?, ?);");
            logQuery.setInt(1, steps.getDay());
            logQuery.setInt(2, steps.getSteps());
            logQuery.setTimestamp(3, steps.getDateCreate());

            if (logQuery.executeUpdate() == 1) {
                final PreparedStatement checkQuery = conn.prepareStatement("SELECT * FROM steps WHERE day=?");
                checkQuery.setInt(1, steps.getDay());
                ResultSet found = checkQuery.executeQuery();

                if (found.next()) {
                    final PreparedStatement updQuery = conn.prepareStatement("UPDATE steps SET steps=?, date_update=? WHERE day=?;");

                    try {
                        updQuery.setInt(1, Math.addExact(found.getInt("steps"), steps.getSteps()));
                    } catch (ArithmeticException e){
                        updQuery.setInt(1, Integer.MAX_VALUE);
                    }

                    updQuery.setTimestamp(2, steps.getDateCreate());
                    updQuery.setInt(3, steps.getDay());
                    updQuery.executeUpdate();

                } else {
                    final PreparedStatement insQuery = conn.prepareStatement("INSERT INTO steps (day, steps, date_create) VALUES (?, ?, ?);");
                    insQuery.setInt(1, steps.getDay());
                    insQuery.setInt(2, steps.getSteps());
                    insQuery.setTimestamp(3, steps.getDateCreate());
                    insQuery.executeUpdate();

                }
                final PreparedStatement logUpdQuery = conn.prepareStatement("UPDATE steps_log SET is_committed=? WHERE day=? AND steps=? AND date_create=?;");
                logUpdQuery.setBoolean(1, true);
                logUpdQuery.setInt(2, steps.getDay());
                logUpdQuery.setInt(3, steps.getSteps());
                logUpdQuery.setTimestamp(4, steps.getDateCreate());

                return logUpdQuery.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

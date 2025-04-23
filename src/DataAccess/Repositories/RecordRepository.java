package DataAccess.Repositories;

import DataAccess.Contracts.RecordDao;
import DataAccess.Entities.Record;
import DataAccess.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecordRepository implements RecordDao {
    @Override
    public ArrayList<Record> getRecordsByUserId(int userId) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        ArrayList<Record> records = new ArrayList<>();

        String query = "SELECT * FROM records where user_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                records.add(
                        new Record(
                                result.getInt("id"),
                                result.getInt("user_id"),
                                result.getInt("score"),
                                result.getDate("date").toLocalDate(),
                                result.getTime("play_time").toLocalTime()
                        )
                );
            }
            return records;
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return records;
    }

    @Override
    public ArrayList<Record> getTopRecords() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        ArrayList<Record> records = new ArrayList<>();

        String query = "SELECT * FROM records ORDER BY score DESC LIMIT 10 ";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ResultSet result = statement.executeQuery();
            while(result.next()){
                records.add(
                        new Record(
                                result.getInt("id"),
                                result.getInt("user_id"),
                                result.getInt("score"),
                                result.getDate("date").toLocalDate(),
                                result.getTime("play_time").toLocalTime()
                        )
                );
            }
            return records;
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return records;
    }

    @Override
    public void createRecord(int score, int user_id, LocalDate date, LocalTime playTime) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();


        String query = "INSERT INTO records (score, user_id, date, play_time) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, score);
            statement.setInt(2, user_id);
            statement.setDate(3, Date.valueOf(date));
            statement.setTime(4, Time.valueOf(playTime));
            statement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
    }
}

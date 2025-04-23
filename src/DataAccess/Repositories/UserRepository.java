package DataAccess.Repositories;

import DataAccess.Contracts.UserDao;
import DataAccess.DatabaseConnection;
import DataAccess.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserDao {
    @Override
    public void create(String username) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        User user = getByUsername(username);

        if(user != null){
            return;
        }

        String query = "INSERT INTO users (username) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, username);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        String query = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new User(resultSet.getInt("id"),
                        resultSet.getString("username")
                );
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        String query = "SELECT * FROM users";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                users.add(new User(resultSet.getInt("id"), resultSet.getString("username")));
            }
            return users;
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return users;
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        String query = "SELECT * FROM users WHERE username = ?";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new User(resultSet.getInt("id"),
                                resultSet.getString("username")
                );
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            connection.close();
        }
        return null;
    }
}

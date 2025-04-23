package DataAccess.Contracts;

import DataAccess.Entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(String username) throws SQLException;
    User getById(int id) throws SQLException;
    List<User> getAll() throws SQLException;
    User getByUsername(String username) throws SQLException;
}

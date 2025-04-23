package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public Connection getConnection(){ return connection; }

    public void closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

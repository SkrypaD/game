package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.127.126.26:3306/game", "root", "");
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

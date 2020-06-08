package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStarter {
    private Connection connection = connect();
    private Statement statement;


    public void dbStarter(){
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Cant find driver");
            e.printStackTrace();
        }
        try{
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/taudio",
                    "postgres",
                    "9204");
            System.out.println("SUCCESSFULLY CONNECTED");
        } catch (SQLException e){
            System.out.println("FAILED CONNECTION");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}

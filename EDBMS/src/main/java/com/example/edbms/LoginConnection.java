package com.example.edbms;

import java.sql.*;

public class LoginConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName="";
        String databaseUser="";
        String databasePassword="";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            databaseLink = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");

        }
        catch(Exception e){
            System.out.println(e);
        }

        return databaseLink;

    }
}

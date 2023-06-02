package com.example.edbms;

import java.io.*;
import java.util.*;
import java.sql.*;

public class JDBC_select {
    public static void main(String args[]) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            ResultSet rs=smt.executeQuery("select *from emp");

            while(rs.next()){
                int eid=rs.getInt(1);
                String ename=rs.getString(2);
                double esal=rs.getDouble(3);

                System.out.println("Emp Id: "+eid);
                System.out.println("Emp Name: "+ename);
                System.out.println("Emp Salary: "+esal+"\n");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

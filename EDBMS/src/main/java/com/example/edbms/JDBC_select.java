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
            String name="Akash Kamthe";
            ResultSet rs=smt.executeQuery("select *from Emp where ename='"+name+"'");

            while(rs.next()){
                int eid=rs.getInt(1);
                String ename=rs.getString(2);
                String gen= rs.getString(3);
                String DOB= rs.getString(4);
                String dept= rs.getString(5);



                System.out.println("Emp Id: "+eid);
                System.out.println("Emp Name: "+ename);
                System.out.println("Emp Name: "+gen);
                System.out.println("Emp Name: "+DOB);
                System.out.println("Emp Name: "+dept);

            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

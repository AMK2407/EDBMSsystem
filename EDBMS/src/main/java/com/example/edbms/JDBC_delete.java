package com.example.edbms;

import java.io.*;
import java.util.*;
import java.sql.*;

public class JDBC_delete {
    public static void main (String [] args){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt=con.createStatement();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("\n-----To Delete Employee Record-----\n\n");

            while(true){
                System.out.print("Enter Emp Id: ");
                int eno=Integer.parseInt(br.readLine());

                int count=smt.executeUpdate("delete from emp where eno="+eno);
                if(count>0){
                    System.out.println(count+" Record Deleted");
                }
                else
                    System.out.println("Employee ID to be deleted does not EXIST\nTry Again!");

                System.out.print("\nDo you want to delete more records [Yes/No] => ");
                String ch=br.readLine();
                if(ch.equalsIgnoreCase("NO"))
                    break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

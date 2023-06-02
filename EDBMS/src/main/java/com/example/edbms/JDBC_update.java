package com.example.edbms;

import java.io.*;
import java.util.*;
import java.sql.*;

public class JDBC_update {
    public static void main(String args[]){
        String value; int eid; double esal;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sql321");
            Statement smt = con.createStatement();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("\n-----To Change Employee Details-----\n\n");

            while(true){
                System.out.print("Enter Emp Id: ");
                value=br.readLine();
                eid=Integer.parseInt(value);


                System.out.print("Enter Emp Salary: ");
                value=br.readLine();
                esal=Double.parseDouble(value);

                int count=smt.executeUpdate("update emp set esal="+esal+"where eno="+eid);

                if(count>0){
                    System.out.println(count+" Record Updated");
                }
                else
                    System.out.println("No Record Updated\nTry Again !");

                System.out.print("\nDo you want to update more records [Yes/No] => ");
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


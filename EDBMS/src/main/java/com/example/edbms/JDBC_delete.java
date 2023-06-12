package com.example.edbms;

import java.io.*;

import java.sql.*;

public class JDBC_delete {
    public static void main (String [] args){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt=con.createStatement();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("\n-----To Delete Employee Records in ALl Tables-----\n\n");

            while(true){
                int count;
                System.out.print("Enter Emp Id: ");
                int eno=Integer.parseInt(br.readLine());

                //Delete Record from Employee Details
                count=smt.executeUpdate("delete from emp where eno="+eno);
                if(count>0){
                    System.out.println(count+" Employee Record Deleted");
                }
                else
                    System.out.println("Employee ID to be deleted does not EXIST\nTry Again!");


                //Delete Record from Salary Details
                count=smt.executeUpdate("delete from SalaryDets where eno="+eno);
                if(count>0){
                    System.out.println(count+" Employee Salary Details Record Deleted");
                }
                else
                    System.out.println("Employee ID to be deleted does not EXIST\nTry Again!");


                //Delete Record from Project Details
                count=smt.executeUpdate("delete from ProjectDets where eno="+eno);
                if(count>0){
                    System.out.println(count+" Employee Salary Details Record Deleted");
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

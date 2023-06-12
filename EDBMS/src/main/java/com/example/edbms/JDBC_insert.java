package com.example.edbms;

import java.io.*;
import java.util.*;
import java.sql.*;

public class JDBC_insert {
    public static void main (String args[]) throws ClassNotFoundException {


        //Employee Details
        try{
            System.out.println("------Enter Employee Details------\n");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into emp values(?,?,?,?,?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("Enter Emp Id: ");
                int eno=Integer.parseInt(br.readLine());
                System.out.print("Enter Emp Name: ");
                String ename=br.readLine();
                System.out.print("Enter Emp Gender: ");
                String gender=br.readLine();
                System.out.print("Enter Emp Date of Birth: ");
                String DOB =br.readLine();
                System.out.print("Enter Employee Join Date: ");
                String JoinDate =br.readLine();
                System.out.print("Enter Employee Department: ");
                String Dept =br.readLine();

                psmt.setInt(1, eno);
                psmt.setString(2, ename);
                psmt.setString(3, gender);
                psmt.setString(4, DOB);
                psmt.setString(5, JoinDate);
                psmt.setString(6, Dept);

                int count=psmt.executeUpdate();
                if(count>0){
                    System.out.println(count+" Record Inserted");
                }
                else
                    System.out.println("No Record Inserted");

                System.out.print("\nDo you want to insert more records [Yes/No] => ");
                String ch=br.readLine();
                if(ch.equalsIgnoreCase("NO"))
                    break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


        //User Account Details
        try{
            System.out.println("------Enter Employee Login Details------\n");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into UserAccDets values(?,?,?,?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("Enter Emp Id: ");
                int eno=Integer.parseInt(br.readLine());
                System.out.print("Enter First Name: ");
                String efname=br.readLine();
                System.out.print("Enter Last Name: ");
                String elname=br.readLine();
                System.out.print("Enter User_Name: ");
                String UserName=br.readLine();
                System.out.print("Enter Password: ");
                String Password=br.readLine();

                psmt.setInt(1, eno);
                psmt.setString(2, efname);
                psmt.setString(3, elname);
                psmt.setString(4, UserName);
                psmt.setString(5, Password);

                int count=psmt.executeUpdate();
                if(count>0){
                    System.out.println(count+" Record Inserted");
                }
                else
                    System.out.println("No Record Inserted");

                System.out.print("\nDo you want to insert more records [Yes/No] => ");
                String ch=br.readLine();
                if(ch.equalsIgnoreCase("NO"))
                    break;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }


        //Salary Details
        try{
            System.out.println("\n------Enter Salary Details------\n");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into SalaryDets values(?,?,?,?,?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("Enter Emp Id: ");
                int eno = Integer.parseInt(br.readLine());
                System.out.print("Enter Basic Pay: ");
                int BPay = Integer.parseInt(br.readLine());
                System.out.print("Enter DA: ");
                int DA = Integer.parseInt(br.readLine());
                System.out.print("Enter HRA: ");
                int HRA = Integer.parseInt(br.readLine());
                System.out.print("Enter TA: ");
                int TA = Integer.parseInt(br.readLine());
                System.out.print("Enter HA: ");
                int HA = Integer.parseInt(br.readLine());

                psmt.setInt(1, eno);
                psmt.setInt(2, BPay);
                psmt.setInt(3, DA);
                psmt.setInt(4, HRA);
                psmt.setInt(5, TA);
                psmt.setInt(6, HA);

                int count=psmt.executeUpdate();
                if(count>0){
                    System.out.println(count+" Record Inserted");
                }
                else
                    System.out.println("No Record Inserted");

                System.out.print("\nDo you want to insert more records [Yes/No] => ");
                String ch = br.readLine();
                if(ch.equalsIgnoreCase("NO"))
                    break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


        //Project Details
        try{
            System.out.println("\n------Enter Project Details------\n");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into ProjectDets values(?,?,?,?,?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("Enter Emp ID: ");
                int eno = Integer.parseInt(br.readLine());
                System.out.print("Enter Project ID: ");
                int Proj_ID = Integer.parseInt(br.readLine());
                System.out.print("Enter Project Name: ");
                String Proj_Name = br.readLine();
                System.out.print("Enter Client Name: ");
                String client = br.readLine();
                System.out.print("Enter Project Commencement Date: ");
                String commencementDate = br.readLine();
                System.out.print("Enter Project Status: ");
                String Status = br.readLine();

                psmt.setInt(1, eno);
                psmt.setInt(2, Proj_ID);
                psmt.setString(3, Proj_Name);
                psmt.setString(4, client);
                psmt.setString(5, commencementDate);
                psmt.setString(6, Status);

                int count=psmt.executeUpdate();
                if(count>0){
                    System.out.println(count+" Record Inserted");
                }
                else
                    System.out.println("No Record Inserted");

                System.out.print("\nDo you want to insert more records [Yes/No] => ");
                String ch=  br.readLine();
                if(ch.equalsIgnoreCase("NO"))
                    break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}


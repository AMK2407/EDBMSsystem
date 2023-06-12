package com.example.edbms;

import java.sql.*;

public class JDBC_connection {
    public static void main (String []args){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            System.out.println("Driver Loaded");
            Statement smt = con.createStatement();

            smt.executeUpdate("create table EmpTemp(eno number primary key, ename varchar(40),  gender varchar(1), DOB varchar(11), JoinDate varchar(11), Department varchar(20))");
            System.out.println("Table EmployeeDetailsTemp Created Successfully");

            smt.executeUpdate("create table Emp(eno number primary key, ename varchar(40),  gender varchar(1), DOB varchar(11), JoinDate varchar(11), Department varchar(20) )");
            System.out.println("Table Employee Details Created Successfully");

            smt.executeUpdate("create table UserAccDets(eno number(20), FirstName varchar(20), LastName varchar(20), UserName varchar(20), Password varchar(20))");
            System.out.println("Table UserAccountDetails Created Successfully");

            smt.executeUpdate("create table SalaryDets(eno number(5), BasicPay number(7), DA number(7), HRA number(7), TA number(7), HA number(7))");
            System.out.println("Table SalaryDetails Created Successfully");

            smt.executeUpdate("create table ProjectDets(eno number(5), Proj_ID number(3), Proj_Name varchar(20), client varchar(7), commencementDate varchar(11), Status varchar(7))");
            System.out.println("Table ProjectDetails Created Successfully");



            smt.executeUpdate("create table SalaryDetsTemp(eno number(5), BasicPay number(7), DA number(7), HRA number(7), TA number(7), HA number(7))");
            System.out.println("Table SalaryDetailsTemp Created Successfully");

            smt.executeUpdate("create table ProjectDetsTemp(eno number(5), Proj_ID number(3), Proj_Name varchar(20), client varchar(7), commencementDate varchar(11), Status varchar(7))");
            System.out.println("Table ProjectDetailsTemp Created Successfully");

            con.close();
        }

        catch (Exception e) {
            System.out.print(e);
        }
    }
}


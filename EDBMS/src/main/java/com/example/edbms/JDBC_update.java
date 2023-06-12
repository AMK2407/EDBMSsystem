package com.example.edbms;

import java.io.*;
import java.sql.*;

public class JDBC_update {
    public static void main(String [] args) throws IOException {

        String value;
        int eid;
        int esal;

        String ans;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n\nDo You want to change Salary Details : ");
        ans=br.readLine();

        if (!ans.equalsIgnoreCase("NO")){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sql321");
                Statement smt = con.createStatement();


                System.out.print("\n-----To Change Employee Details-----\n\n");

                while(true){
                    int count;
                    System.out.print("Enter Employee ID: ");
                    value=br.readLine();
                    eid=Integer.parseInt(value);


                    System.out.print("Enter Employee Basic Pay: ");
                    value=br.readLine();
                    esal= Integer.parseInt(value);
                    smt.executeUpdate("update SalaryDets set  BasicPay="+esal+"where eno="+eid);

                    System.out.print("Enter Employee DA: ");
                    value=br.readLine();
                    esal= Integer.parseInt(value);
                    smt.executeUpdate("update SalaryDets set DA="+esal+"where eno="+eid);

                    System.out.print("Enter Employee HRA: ");
                    value=br.readLine();
                    esal= Integer.parseInt(value);
                    smt.executeUpdate("update SalaryDets set HRA="+esal+"where eno="+eid);

                    System.out.print("Enter Employee TA: ");
                    value=br.readLine();
                    esal=Integer.parseInt(value);
                    smt.executeUpdate("update SalaryDets set TA="+esal+"where eno="+eid);

                    System.out.print("Enter Employee HA: ");
                    value=br.readLine();
                    esal=Integer.parseInt(value);
                    count=smt.executeUpdate("update SalaryDets set HA="+esal+"where eno="+eid);

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
                con.close();
                System.out.println("Done !!!!");
            }
            catch(Exception e){
                System.out.println(e);
            }

        }



        System.out.println("\n\nDo You want to change Project Details : ");
        ans=br.readLine();

        if (!ans.equalsIgnoreCase("NO")) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sql321");
                Statement smt = con.createStatement();


                System.out.print("\n-----To Change Project Details-----\n\n");

                while (true) {
                    int count;
                    System.out.print("Enter Employee ID: ");
                    value = br.readLine();
                    eid = Integer.parseInt(value);


                    System.out.print("Enter New Project ID: ");
                    value = br.readLine();
                    esal = Integer.parseInt(value);
                    smt.executeUpdate("update ProjectDets set  Proj_ID=" + esal + "where eno=" + eid);

                    System.out.print("Enter New Project Name: ");
                    value = br.readLine();
                    smt.executeUpdate("update ProjectDets set  Proj_Name='" + value + "'here eno=" + eid);

                    System.out.print("Enter New Client Name: ");
                    value = br.readLine();
                    smt.executeUpdate("update ProjectDets set client='" + value + "' where eno="+eid);

                    System.out.print("Enter New Commencement Date: ");
                    value = br.readLine();
                    smt.executeUpdate("update ProjectDets set commencementDate='" + value + "'where eno=" + eid);

                    System.out.print("Enter New Status: ");
                    value = br.readLine();
                    count=smt.executeUpdate("update ProjectDets set Status='" + value + "'where eno=" + eid);

                    if (count > 0) {
                        System.out.println(count + " Record Updated");
                    } else
                        System.out.println("No Record Updated\nTry Again !");

                    System.out.print("\nDo you want to update more records [Yes/No] => ");
                    String ch = br.readLine();
                    if (ch.equalsIgnoreCase("NO"))
                        break;
                }
                con.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Done !!!!");
    }
}


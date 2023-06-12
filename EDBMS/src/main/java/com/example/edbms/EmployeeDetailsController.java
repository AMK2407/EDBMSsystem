package com.example.edbms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;


public class EmployeeDetailsController implements Initializable{

    @FXML
    private Button projectDetsButton;
    @FXML
    private Button salaryDetsButton;
    @FXML
    private Label empIDLabel;
    @FXML
    private Label empNameLabel;
    @FXML
    private Label empGenderLabel;
    @FXML
    private Label empDOBLabel;
    @FXML
    private Label empJoinDateLabel;
    @FXML
    private Label empDepartmentLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    //Receive Employee Details from LoginController
    int UserEmpNo;
    String UserEname;
    String UserGender;
    String UserDOB;
    String UserDepartment;
    String UserJoinDate;

    //Pass Salary Details to Salary Details Controller
    int Bpay, DA, HRA, TA, HA;

    //Pass Project Details to Project Details Controller
    int Proj_ID;
    String ProjectName;
    String client;
    String commencementDate;
    String Status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            ResultSet rs=smt.executeQuery("select *from EmpTemp");

            while(rs.next()){
                UserEmpNo=rs.getInt(1);
                UserEname=rs.getString(2);
                UserGender= rs.getString(3);
                UserDOB= rs.getString(4);
                UserJoinDate= rs.getString(5);
                UserDepartment= rs.getString(6);
            }

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //Set all values
        empIDLabel.setText(Integer.toString(UserEmpNo));
        empNameLabel.setText(UserEname);
        empGenderLabel.setText(UserGender);
        empDOBLabel.setText(UserDOB);
        empJoinDateLabel.setText(UserJoinDate);
        empDepartmentLabel.setText(UserDepartment);
    }


    public void switchtoProjectDetails() throws IOException {
        PasstoProjectDets();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectDetails.fxml"));
        Parent mainScreenRoot = loader.load();
        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Get the current stage and set the main screen scene
        Stage primaryStage = (Stage) projectDetsButton.getScene().getWindow();
        primaryStage.setScene(mainScreenScene);
    }

    public void switchtoSalaryDetails () throws IOException {
        PasstoSalaryDets();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SalaryDetails.fxml"));
        Parent mainScreenRoot = loader.load();
        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Get the current stage and set the main screen scene
        Stage primaryStage = (Stage) salaryDetsButton.getScene().getWindow();
        primaryStage.setScene(mainScreenScene);
    }

    public void logoutButtonOnAction() throws IOException {

        //Delete temporary record from EmpTemp on logging out
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            smt.executeQuery("delete from EmpTemp");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //Switch to Login Page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent mainScreenRoot = loader.load();
        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Get the current stage and set the main screen scene
        Stage primaryStage = (Stage) salaryDetsButton.getScene().getWindow();
        primaryStage.setScene(mainScreenScene);


    }


    public void ReceiveEmpDets(int user_eno, String user_ename, String user_gender, String user_DOB, String user_JoinDate, String user_Department) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into EmpTemp values(?,?,?,?,?,?)");

            while(true){
                psmt.setInt(1, user_eno);
                psmt.setString(2, user_ename);
                psmt.setString(3, user_gender);
                psmt.setString(4, user_DOB);
                psmt.setString(5, user_JoinDate);
                psmt.setString(6, user_Department);

                int count=psmt.executeUpdate();
                if(count>0){
                    System.out.println(count+" Record Temporarily Inserted");
                }
                break;
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }



    public void PasstoProjectDets() throws IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from EmpTemp ");

            while(rs.next()){
                UserEmpNo = rs.getInt(1);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        System.out.println(UserEmpNo);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from ProjectDets where eno="+UserEmpNo);

            while(rs.next()){
                Proj_ID = rs.getInt(2);
                ProjectName = rs.getString(3);
                client = rs.getString(4);
                commencementDate = rs.getString(5);
                Status = rs.getString(6);

            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectDetails.fxml"));
            Parent destinationRoot = loader.load();
            ProjectDetailsController destinationController = loader.getController();

            // Pass values to the destination controller
            destinationController.ReceiveProjectDets(UserEmpNo, Proj_ID, ProjectName, client, commencementDate, Status);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void PasstoSalaryDets() throws IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from EmpTemp ");

            while(rs.next()){
                UserEmpNo = rs.getInt(1);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        System.out.println(UserEmpNo);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from SalaryDets where eno="+UserEmpNo);

            while(rs.next()){
                Bpay = rs.getInt(2);
                DA = rs.getInt(3);
                HRA = rs.getInt(4);
                TA = rs.getInt(5);
                HA = rs.getInt(6);

            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SalaryDetails.fxml"));
            Parent destinationRoot = loader.load();
            SalaryDetailsController destinationController = loader.getController();

            // Pass values to the destination controller
            destinationController.ReceiveSalaryDets(UserEmpNo, Bpay, DA, HRA, TA, HA);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

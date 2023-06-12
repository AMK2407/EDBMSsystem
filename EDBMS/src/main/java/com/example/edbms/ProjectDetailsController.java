package com.example.edbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProjectDetailsController implements Initializable {

    @FXML
    private Button empDetailsButton;
    @FXML
    private Label Proj_IDLabel;
    @FXML
    private Label ProjectNameLabel;
    @FXML
    private Label clientLabel;
    @FXML
    private Label commencementDateLabel;
    @FXML
    private Label StatusLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    //Receive values from ProjectDetsTemp table
    int EmpNo;
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
            ResultSet rs=smt.executeQuery("select *from ProjectDetsTemp");

            while(rs.next()){
                EmpNo=rs.getInt(1);
                Proj_ID=rs.getInt(2);
                ProjectName=rs.getString(3);
                client=rs.getString(4);
                commencementDate=rs.getString(5);
                Status=rs.getString(6);
            }

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //Set all values
        Proj_IDLabel.setText(Integer.toString(Proj_ID));
        ProjectNameLabel.setText(ProjectName);
        clientLabel.setText(client);
        commencementDateLabel.setText(commencementDate);
        StatusLabel.setText(Status);
    }


    public void switchtoEmployeeDetails() throws IOException {

        //Delete temporary record from SalaryDetsTemp on logging out
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            smt.executeQuery("delete from ProjectDetsTemp");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //Switch back to Employee Details
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeDetails.fxml"));
        Parent mainScreenRoot = loader.load();
        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Get the current stage and set the main screen scene
        Stage primaryStage = (Stage) empDetailsButton.getScene().getWindow();
        primaryStage.setScene(mainScreenScene);
    }



    public void ReceiveProjectDets(int UserEmpNo, int Proj_ID, String ProjectName, String client, String commencementDate, String Status) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into ProjectDetsTemp values(?,?,?,?,?,?)");

            while(true){
                psmt.setInt(1, UserEmpNo);
                psmt.setInt(2, Proj_ID);
                psmt.setString(3, ProjectName);
                psmt.setString(4, client);
                psmt.setString(5, commencementDate);
                psmt.setString(6, Status);

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

}


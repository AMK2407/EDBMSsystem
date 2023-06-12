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

public class SalaryDetailsController implements Initializable {

    @FXML
    private Button empDetailsButton;
    @FXML
    private Label BasicPayLabel;
    @FXML
    private Label DALabel;
    @FXML
    private Label HRALabel;
    @FXML
    private Label TALabel;
    @FXML
    private Label HALabel;



    private Stage stage;
    private Scene scene;
    private Parent root;

    //Receive values from SalaryDetsTemp table
    int EmpNo;
    int BasicPay;
    int DA;
    int HRA;
    int TA;
    int HA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            ResultSet rs=smt.executeQuery("select *from SalaryDetsTemp");

            while(rs.next()){
                EmpNo=rs.getInt(1);
                BasicPay=rs.getInt(2);
                DA=rs.getInt(3);
                HRA=rs.getInt(4);
                TA=rs.getInt(5);
                HA=rs.getInt(6);
            }

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //Set all values
        BasicPayLabel.setText(Integer.toString(BasicPay));
        DALabel.setText(Integer.toString(DA));
        HRALabel.setText(Integer.toString(HRA));
        TALabel.setText(Integer.toString(TA));
        HALabel.setText(Integer.toString(HA));
    }




    public void switchtoEmployeeDetails() throws IOException {

        //Delete temporary record from SalaryDetsTemp on logging out
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement smt = con.createStatement();
            smt.executeQuery("delete from SalaryDetsTemp");

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


    public void ReceiveSalaryDets(int user_eno, int Bpay, int DA, int HRA, int TA, int HA) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            PreparedStatement psmt=con.prepareStatement("insert into SalaryDetsTemp values(?,?,?,?,?,?)");

            while(true){
                psmt.setInt(1, user_eno);
                psmt.setInt(2, Bpay);
                psmt.setInt(3, DA);
                psmt.setInt(4, HRA);
                psmt.setInt(5, TA);
                psmt.setInt(6, HA);

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

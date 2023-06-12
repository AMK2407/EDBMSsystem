package com.example.edbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    String Username;

    int user_eno = 0;
    String user_ename = " ";
    String user_gender = " ";
    String user_DOB = " ";
    String user_JoinDate = " ";
    String user_Department = " ";

    public void loginButtonOnAction(ActionEvent event){

        if(usernameTextField.getText().isBlank() && passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("You Try to Login !");
            loginMessageLabel.setText("Please Enter your Username And Password");
        }
        else{
            validateLogin();
        }
    }

    public void validateLogin(){
        LoginConnection connectNow = new LoginConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "Select count(1) from UserAccDets where UserName= '"+ usernameTextField.getText()+"' AND password = '"+passwordPasswordField.getText()+"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("Welcome !!!");
                    Username = usernameTextField.getText();
                    PassEmpDets(Username);
                    switchtoEmployeeDetails();
                }
                else{
                    loginMessageLabel.setText("Invalid Login. Please Try Again !!!");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void createAccountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeDetails.fxml"));
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root, 600,400));
            registerStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void switchtoRegisterNewUserPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterNewUserPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void PassEmpDets(String Username) throws IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from UserAccDets where UserName='"+Username+"'");

            while(rs.next()){
                user_eno = rs.getInt(1);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        System.out.println(user_eno);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sql321");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *from Emp where eno='"+user_eno+"'");

            while(rs.next()){
                user_eno = rs.getInt(1);
                user_ename= rs.getString(2);
                user_gender= rs.getString(3);
                user_DOB = rs.getString(4); ;
                user_JoinDate = rs.getString(5); ;
                user_Department = rs.getString(6);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeDetails.fxml"));
            Parent destinationRoot = loader.load();
            EmployeeDetailsController destinationController = loader.getController();

            // Pass values to the destination controller
            destinationController.ReceiveEmpDets(user_eno, user_ename, user_gender, user_DOB, user_JoinDate, user_Department);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchtoEmployeeDetails() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeDetails.fxml"));
        Parent mainScreenRoot = loader.load();

        // Get the controller instance

        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Get the current stage and set the main screen scene
        Stage primaryStage = (Stage) loginButton.getScene().getWindow();
        primaryStage.setScene(mainScreenScene);
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginButtonOnAction(ActionEvent event){

        if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("You Try to Login !");
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please Enter your Username And Password");
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
                    createAccountForm();
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

    public void switchtoLoginPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoEmployeeDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EmployeeDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSalaryDetails (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SalaryDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoProjectDetails (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SalaryDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
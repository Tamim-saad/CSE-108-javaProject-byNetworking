package myCLient;

import commonClassPackage.DataWrapper;
import commonClassPackage.Movie;
import commonClassPackage.SocketWrapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class clientMain extends Application{
    @FXML
    TextField userText;
    @FXML
    Label warningText;

    public static List<Movie> clientMovieArray=new ArrayList<>();
    public static String COMPANY_NAME;
    public static final int smallHeight=600;
    public static final int smallWidth=400;
    public static final int largeHeight=1015;
    public static final int largeWidth=650;



    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("clientMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("Movie Action");
        stage.setScene(scene);
        stage.show();
    }

public static void main(String[]args) throws IOException {
        launch();
}
 public void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
         COMPANY_NAME = userText.getText();
     //connect with server
      SocketWrapper server = new SocketWrapper("127.0.0.1", 3333);
             server.write("giveMyList," + COMPANY_NAME);

         //read from server
         Object clientObjectData = server.read();
         DataWrapper clientWrapperData = (DataWrapper) clientObjectData;

         //making movie array
             clientMovieArray = (ArrayList) clientWrapperData.movieArray;

         //show warning
        if (clientMovieArray.size()==0){

            Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();

            Alert.AlertType type= Alert.AlertType.WARNING;
            Alert alert=new Alert(type,"");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initModality(stage.getModality());
            alert.getDialogPane().setContentText("No Company Exists in Database with This Name");
            alert.getDialogPane().setHeaderText("WARNING!!!");
            alert.showAndWait();
        }else {
            //log in stage

            //show window
            Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("companyHome.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
            stage.setTitle("Production Company Searching Option!");
            stage.setScene(scene);
            stage.show();
            //////////////////////////////////////////////////
        }

    }
    public void resetAction(ActionEvent actionEvent) {
    userText.setText(null);
    warningText.setText(null);
    }
    public  void onCompanyHomeBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("clientMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("You are logged in as "+COMPANY_NAME);
        stage.setScene(scene);
        stage.show();
    }
    public void onSearchMoviesClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("searchOption.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), largeHeight, largeWidth);
        stage.setTitle("You are logged in as "+COMPANY_NAME);
        stage.setScene(scene);
        stage.show();
    }
    public void onAddMovieClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("addMovie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), largeHeight, largeWidth);
        stage.setTitle("You are logged in as "+COMPANY_NAME);
        stage.setScene(scene);
        stage.show();
    }
    public void onTransferMovieClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("transferMovie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("You are logged in as "+COMPANY_NAME);
        stage.setScene(scene);
        stage.show();
    }
    public void onSearchMoviesBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("companyHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("Production Company Searching Option!");
        stage.setScene(scene);
        stage.show();

    }
    public void onTransferMovieBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("companyHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("Production Company Searching Option");
        stage.setScene(scene);
        stage.show();
    }
    public void onADDMovieBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(clientMain.class.getResource("companyHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), smallHeight, smallWidth);
        stage.setTitle("Production Company Searching Option");
        stage.setScene(scene);
        stage.show();
    }

    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

}
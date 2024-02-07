package myCLient;

import commonClassPackage.DataWrapper;
import commonClassPackage.Movie;
import commonClassPackage.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
public class AddMovieController extends clientMain{
    @FXML
    TextField toInputTitle;
    @FXML
    TextField toInputYear;
    @FXML
    TextField toInputGenre1;
    @FXML
    TextField toInputGenre2;
    @FXML
    TextField toInputGenre3;
    @FXML
    TextField toInputRunTime;
    @FXML
    TextField toInputBudget;
    @FXML
    TextField toInputRevenue;

    @Override
    public void onADDMovieBackClick(ActionEvent actionEvent) throws IOException {
        super.onADDMovieBackClick(actionEvent);
    }
    public void onResetAddInformationClick(ActionEvent actionEvent) {

        toInputTitle.setText(null);
        toInputYear.setText(null);
        toInputGenre1.setText(null);
        toInputGenre2.setText(null);
        toInputGenre3.setText(null);
        toInputRunTime.setText(null);
        toInputBudget.setText(null);
        toInputRevenue.setText(null);
    }

    public void onADDMovieClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        SocketWrapper server=new SocketWrapper("127.0.0.1",3333);

        server.write( "addAndUpdateMyList,"+
        toInputTitle.getText()+","+
        toInputYear.getText()+","+
        toInputGenre1.getText()+","+
        toInputGenre2.getText()+","+
        toInputGenre3.getText()+","+
        toInputRunTime.getText()+","+
        COMPANY_NAME+","+
        toInputBudget.getText()+","+
        toInputRevenue.getText());

        Object clientObjectData=server.read();
        DataWrapper clientWrapperData=(DataWrapper)clientObjectData;
         clientMovieArray = (ArrayList) clientWrapperData.movieArray;

         ////////////
             Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();

             Alert.AlertType type= Alert.AlertType.CONFIRMATION;
             Alert alert=new Alert(type,"");

             alert.initModality(Modality.APPLICATION_MODAL);
             alert.initModality(stage.getModality());
             alert.getDialogPane().setContentText("Added Successfully in List");
             alert.getDialogPane().setHeaderText("DONE!!");
             alert.showAndWait();
             //////////////////////////

        toInputTitle.setText(null);
        toInputYear.setText(null);
        toInputGenre1.setText(null);
        toInputGenre2.setText(null);
        toInputGenre3.setText(null);
        toInputRunTime.setText(null);
        toInputBudget.setText(null);
        toInputRevenue.setText(null);
        }

    }
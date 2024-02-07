package myCLient;

import commonClassPackage.DataWrapper;
import commonClassPackage.Movie;
import commonClassPackage.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TransferMovieController extends clientMain{
    @FXML
    TextField movieTitleToTransfer;
    @FXML
    TextField companyNameToTransfer;
    @FXML
    TableView<Movie> transferMoviesTable;
    @FXML
    TableColumn<Movie, String> transferMoviesTableTitleCol;

    @FXML
    void initialize() {
        transferMoviesTableTitleCol.setCellValueFactory(new PropertyValueFactory<>("Movie_name"));
        //////////////////////////////////
        new Thread(()->{
            while(true){
                int size1=clientMovieArray.size();
                SocketWrapper server = null;
                try {
                    server = new SocketWrapper("127.0.0.1", 3333);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    server.write("giveMyList," + COMPANY_NAME);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //read from server
                Object clientObjectData = null;
                try {
                    clientObjectData = server.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                DataWrapper clientWrapperData = (DataWrapper) clientObjectData;

                //making movie array
                clientMovieArray = (ArrayList) clientWrapperData.movieArray;
                int size2=clientMovieArray.size();
                if(size1!=size2){
                    onTransferMovieLoadClick(null);
                }
            }
        }).start();;
    }
    @Override
    public void onTransferMovieBackClick(ActionEvent actionEvent) throws IOException {
        super.onTransferMovieBackClick(actionEvent);
    }
    public void onResetAddInformationClick(ActionEvent actionEvent) {
        movieTitleToTransfer.setText(null);
        companyNameToTransfer.setText(null);
    }
    public void onTransferMovieClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int count1=clientMovieArray.size();
        //connect with server
        SocketWrapper server=new SocketWrapper("127.0.0.1",3333);

        //sending message
        server.write( "transferAndUpdateMyList,"+
                COMPANY_NAME+","+
                movieTitleToTransfer.getText()+","+
                companyNameToTransfer.getText());

        //read message
        Object clientObjectData=server.read();
        DataWrapper clientWrapperData=(DataWrapper)clientObjectData;

        //update client movie array
            clientMovieArray = (ArrayList) clientWrapperData.movieArray;
            int count2=clientMovieArray.size();
            if (count1==(count2+1)){

                onTransferMovieLoadClick(null);
                Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();

                Alert.AlertType type= Alert.AlertType.CONFIRMATION;
                Alert alert=new Alert(type,"");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initModality(stage.getModality());
                alert.getDialogPane().setContentText("Transferred Successfully in List");
                alert.getDialogPane().setHeaderText("DONE!!");
                alert.showAndWait();
                //////////////////////////

                movieTitleToTransfer.setText(null);
                companyNameToTransfer.setText(null);
            }
    }


    public void onTransferMovieLoadClick(ActionEvent actionEvent) {

        transferMoviesTable.getItems().clear();
        for (Movie s:clientMovieArray){
            transferMoviesTable.getItems().add(s);
        }
    }
}

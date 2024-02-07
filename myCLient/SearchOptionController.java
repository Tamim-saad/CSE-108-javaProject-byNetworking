package myCLient;

import commonClassPackage.DataWrapper;
import commonClassPackage.Movie;
import commonClassPackage.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchOptionController extends clientMain {
    private static String functionStatus=null;
    @FXML
    TextField toInputTitle;
    @FXML
    TextField toInputForDelete;
    @FXML
    TextField toInputYear;
    @FXML
    TextField toInputGenre;
    @FXML
    TextField toInputTime;
    @FXML
    TableView<Movie> searchMoviesTable;
    @FXML
    TableColumn<Movie, String> searchMoviesTableTitleCol;
    @FXML
    TableColumn<Movie, Integer> searchMoviesTableYearCol;
    @FXML
    TableColumn<Movie, String> searchMoviesTableG1Col;
    @FXML
    TableColumn<Movie, String> searchMoviesTableG2Col;
    @FXML
    TableColumn<Movie, String> searchMoviesTableG3Col;
    @FXML
    TableColumn<Movie, Integer> searchMoviesTableTimeCol;
    @FXML
    TableColumn<Movie, Integer> searchMoviesTableBudgetCol;
    @FXML
    TableColumn<Movie, Integer> searchMoviesTableRevenueCol;
    @FXML
    void initialize(){
        searchMoviesTableTitleCol.setCellValueFactory(new PropertyValueFactory<>("Movie_name"));
        searchMoviesTableYearCol.setCellValueFactory(new  PropertyValueFactory<>("Releasing_year"));
        searchMoviesTableG1Col.setCellValueFactory(new  PropertyValueFactory<>("Genre1"));

        searchMoviesTableG2Col.setCellValueFactory(new  PropertyValueFactory<>("Genre2"));
        searchMoviesTableG3Col.setCellValueFactory(new  PropertyValueFactory<>("Genre3"));
        searchMoviesTableTimeCol.setCellValueFactory(new  PropertyValueFactory<>("Running_time"));
        searchMoviesTableBudgetCol.setCellValueFactory(new  PropertyValueFactory<>("Budget"));
        searchMoviesTableRevenueCol.setCellValueFactory(new  PropertyValueFactory<>("Revenue"));

        new Thread(()-> {

            while (true){
            try {
                //connect with server
                SocketWrapper server2= new SocketWrapper("127.0.0.1", 3333);
                server2.write("giveMyList," + COMPANY_NAME);
                Object clientObjectData = server2.read();
                DataWrapper clientWrapperData = (DataWrapper) clientObjectData;

                //checking and making movie array
                        clientMovieArray = (ArrayList) clientWrapperData.movieArray;
                   if(clientWrapperData.Status){
                       SocketWrapper server3= new SocketWrapper("127.0.0.1", 3333);
                       server3.write("confirmFromTransferredCompany,"+COMPANY_NAME);

                              if(functionStatus.equalsIgnoreCase("onSearchAllMoviesyClick")){
                           onSearchAllMoviesyClick(null);
                       } else if (functionStatus.equalsIgnoreCase("onSearchByMovieGenreClick")) {
                           onSearchByMovieGenreClick(null);
                       } else if (functionStatus.equalsIgnoreCase("onSearchByMovieTitleClick")) {
                           onSearchByMovieTitleClick(null);
                       } else if (functionStatus.equalsIgnoreCase("onSearchByYearClick")) {
                           onSearchByYearClick(null);
                       } else if (functionStatus.equalsIgnoreCase("onSearchByRunTimeClick")) {
                           onSearchByRunTimeClick(null);
                       }
                   }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            }

        }).start();
    }
    public void onSearchByRunTimeClick(ActionEvent actionEvent) {
        functionStatus="onSearchByRunTimeClick";

        //split minimum and maximum range
        String[] timeString=toInputTime.getText().split("-");
            int[] time={Integer.parseInt(timeString[0]),Integer.parseInt(timeString[1])};

        //making movie array by input run time range
        List<Movie> modifiedMovieArray=new ArrayList<>();
        for (Movie s:clientMovieArray){
            if(time[0]<=s.getRunning_time()&&s.getRunning_time()<=time[1]){
                modifiedMovieArray.add(s);
            }
        }

        //show in table
        searchMoviesTable.getItems().clear();
        for (Movie s:modifiedMovieArray){
            searchMoviesTable.getItems().add(s);
        }
    }

    @Override
    public void onSearchMoviesBackClick(ActionEvent actionEvent) throws IOException {
        super.onSearchMoviesBackClick(actionEvent);
    }
    public void onSearchByYearClick(ActionEvent actionEvent) {
        functionStatus="onSearchByYearClick";
        //making movie array by input year
        List<Movie> modifiedMovieArray=new ArrayList<>();
        for (Movie s:clientMovieArray){
            if(Integer.parseInt(toInputYear.getText())==s.getReleasing_year()){
                modifiedMovieArray.add(s);
            }
        }

        //show in table
        searchMoviesTable.getItems().clear();
        for (Movie s:modifiedMovieArray){
            searchMoviesTable.getItems().add(s);
        }

    }

    public void onSearchByMovieTitleClick(ActionEvent actionEvent) {
        functionStatus="onSearchByMovieTitleClick";

        //making movie array by input title
        List<Movie> modifiedMovieArray=new ArrayList<>();
        for (Movie s:clientMovieArray){
            if((toInputTitle.getText()).equalsIgnoreCase(s.getMovie_name())){
                modifiedMovieArray.add(s);
            }
        }

        //show in table
        searchMoviesTable.getItems().clear();
        for (Movie s:modifiedMovieArray){
            searchMoviesTable.getItems().add(s);
        }

    }

    public void onSearchByMovieGenreClick(ActionEvent actionEvent) {
        functionStatus="onSearchByMovieGenreClick";

        //making movie array by input genre
        List<Movie> modifiedMovieArray=new ArrayList<>();
        for (Movie s:clientMovieArray){
            if((toInputGenre.getText()).equalsIgnoreCase(s.getGenre1())||(toInputGenre.getText()).equalsIgnoreCase(s.getGenre2())||(toInputGenre.getText()).equalsIgnoreCase(s.getGenre3())){
                modifiedMovieArray.add(s);
            }
        }

        //show in table
        searchMoviesTable.getItems().clear();
        for (Movie s:modifiedMovieArray){
            searchMoviesTable.getItems().add(s);
        }

    }
    public void onSearchAllMoviesyClick(ActionEvent actionEvent) {
        functionStatus="onSearchAllMoviesyClick";

        //show movies in table
        searchMoviesTable.getItems().clear();
        for (Movie s:clientMovieArray){
            searchMoviesTable.getItems().add(s);
        }

    }
    public void onMovieDeleteClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        //connect to server
        SocketWrapper server=new SocketWrapper("127.0.0.1",3333);
        server.write( "deleteAndUpdateMyList,"+clientMovieArray.get(0).getCompany()+","+toInputForDelete.getText());

        //read from server
        Object clientObjectData=server.read();
        DataWrapper clientWrapperData=(DataWrapper)clientObjectData;

        //making new company movie array
            clientMovieArray = (ArrayList) clientWrapperData.movieArray;

        //show in table
        searchMoviesTable.getItems().clear();
        for (Movie s:clientMovieArray){
            searchMoviesTable.getItems().add(s);
        }
    }
}

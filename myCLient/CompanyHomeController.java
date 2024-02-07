package myCLient;

import commonClassPackage.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class CompanyHomeController extends clientMain{
    @FXML
    Label toShowTotalMovie;
    @FXML
    Label toShowBestMovie;
    @FXML
    Label toShowTotalProfit;
    @FXML
    Label companyHomeTotalProfit;
    @FXML
    Label companyHomeMovieCount;
    @FXML
    Label companyHomeClientName;
    @FXML
    Label toShowCompanyName;
    @FXML
    Label companyHomeBestMovie;

    @Override
    public void onSearchMoviesClick(ActionEvent actionEvent) throws IOException {
        super.onSearchMoviesClick(actionEvent);
    }
    @Override
    public void onAddMovieClick(ActionEvent actionEvent) throws IOException {
        super.onAddMovieClick(actionEvent);
    }
    @Override
    public void onCompanyHomeBackClick(ActionEvent actionEvent) throws IOException {
        super.onCompanyHomeBackClick(actionEvent);
    }

    public void onCompanyHomeDetailsClick(ActionEvent actionEvent) {
        toShowTotalMovie.setText("Total Number of Movies :");
        toShowTotalProfit.setText("Total Profit :");
        toShowBestMovie.setText("Best Movie :");
        toShowCompanyName.setText(COMPANY_NAME);
        companyHomeMovieCount.setText(String.valueOf(clientMovieArray.size()));
        long profit=0;

            for (int i=0;i<clientMovieArray.size();i++){
                profit+=clientMovieArray.get(i).getRevenue()-clientMovieArray.get(i).getBudget();
            }
            Movie s=clientMovieArray.get(0);
        for (int i=1;i<clientMovieArray.size();i++){
            if((s.getRevenue()-s.getBudget())<(clientMovieArray.get(i).getRevenue()-clientMovieArray.get(i).getBudget())){
                s=clientMovieArray.get(i);
            }
        }
        companyHomeTotalProfit.setText(String.valueOf(profit)+" $");
        companyHomeBestMovie.setText(s.getMovie_name());
    }
}

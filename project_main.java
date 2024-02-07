import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class project_main {
    private static final String INPUT_FILE_NAME = "movies.txt";
    public static List<Movie> movieList = new ArrayList();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] out = line.split(",");
            Movie temp_movie = new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8]));
            movieList.add(temp_movie);
        }
        br.close();

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1) Search Movies");
            System.out.println("2) Search Production Companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit System");

            Scanner scn = new Scanner(System.in);
            int main_menu_choice= scn.nextInt();


                if(main_menu_choice==1) {
                    System.out.println("Movie Searching Options:\n" +
                            "1) By Movie Title\n" +
                            "2) By Release Year \n" +
                            "3) By Genre \n" +
                            "4) By Production Company\n" +
                            "5) By Running Time \n" +
                            "6) Top 10 Movies \n" +
                            "7) Back to Main Menu");

                    Scanner scn2 = new Scanner(System.in);
                    int x = scn2.nextInt();

                    switch (x) {
                        case 1:
                            Functions.search_by_Title();
                            break;
                        case 2:
                            Functions.search_by_year();
                            break;
                        case 3:
                            Functions.search_by_genre();
                            break;
                        case 4:
                            Functions.search_by_company();
                            break;
                        case 5:
                            Functions.search_by_running_time();
                            break;
                        case 6:
                            Functions.top_10_movies();
                            break;
                        case 7:
                            break;
                    }
                }

                if(main_menu_choice==2) {
                    System.out.println("Production Company Searching Options:\n" +
                            "1) Most Recent Movies \n" +
                            "2) Movies with the Maximum Revenue\n" +
                            "3) Total Profit\n" +
                            "4) List of Production Companies and the Count of their Produced Movies \n" +
                            "5) Back to Main Menu");

                    Scanner scn3 = new Scanner(System.in);
                    int x1 = scn3.nextInt();

                    switch (x1) {
                        case 1:
                            Functions.company_recent_movies();
                            break;
                        case 2:
                            Functions.company_maximum_revenue_movies();
                            break;
                        case 3:
                            Functions.company_total_profit();
                            break;
                        case 4:
                            Functions.all_company_and_movies_count();
                            break;
                        case 5:
                            break;
                    }
                }

                if(main_menu_choice==3){
                    Functions.add_new_movie_in_database();
                }

                if (main_menu_choice==4){
                    System.out.println("System Terminated");
                    break;
                }
            }
        }
    }
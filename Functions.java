import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functions extends project_main{
    public static void search_by_Title(){
        System.out.println("Please enter the movie title:");
        int temp = 0;
        Scanner scn3 = new Scanner(System.in);
        String str= scn3.nextLine();
        for (Movie s : movieList) {
            if (s.getMovie_name().equals(str)) {
                temp = 1;
                System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
            }
        }
        if (temp == 0) {
            System.out.println("No such movie with this name");
        }
    }
    public static void search_by_year(){
        System.out.println("Please enter Releasing year of movie:");
        Scanner scn3 = new Scanner(System.in);
        int i= scn3.nextInt();
        int temp = 0;
        for (Movie s : movieList) {
            if (s.getReleasing_year() == i) {
                temp = 1;
                System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
            }
        }
        if (temp == 0) {
            System.out.println("No such movie with this release year");
        }
    }
    public static void search_by_genre(){
        System.out.println("Please enter Genre of the movie:");
        Scanner scn3 = new Scanner(System.in);
        String str= scn3.nextLine();
        int temp = 0;
        for (Movie s : movieList) {
            if (s.getGenre1().equals(str) || s.getGenre2().equals(str) || s.getGenre3().equals(str)) {
                temp = 1;
                System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
            }
        }
        if (temp == 0) {
            System.out.println("“No such movie with this genre");
        }
    }
    public static void search_by_company() {
        System.out.println("Please enter Production company of movie:");
        Scanner scn3 = new Scanner(System.in);
        String str = scn3.nextLine();
        int temp = 0;
        for (Movie s : movieList) {
            if (s.getCompany().equals(str)) {
                temp = 1;
                System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
            }
        }
        if (temp == 0) {
            System.out.println("No such movie with this production company");
        }
    }
    public static void search_by_running_time(){
        System.out.println("Please enter the range of Running Time of movie:");
        Scanner scn3 = new Scanner(System.in);
        int temp = 0, low = scn3.nextInt(), high = scn3.nextInt();
        for (Movie s : movieList) {
            if (low <= s.getRunning_time() && s.getRunning_time() <= high) {
                temp = 1;
                System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
            }
        }
        if (temp == 0) {
            System.out.println("No such movie with this running time range");
        }
    }
    public static void top_10_movies() {
        List<Movie> movieList2 = new ArrayList<>(movieList);
        double[] index_array = new double[movieList2.size()];
        for (int i = 0; i < movieList2.size(); i++) {
            index_array[i] = movieList2.get(i).getRevenue() - movieList2.get(i).getBudget();
        }
        for (int i = 0; i < index_array.length - 1; i++) {
            for (int j = i + 1; j < index_array.length; j++) {
                if (index_array[i] < index_array[j]) {
                    double t = index_array[i];
                    index_array[i] = index_array[j];
                    index_array[j] = t;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < movieList2.size(); j++) {
                if (index_array[i] == movieList2.get(j).getRevenue() - movieList2.get(j).getBudget()) {
                    Movie s = movieList2.get(j);
                    System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
                    movieList2.remove(j);
                }
            }
        }
    }
    public static void company_recent_movies(){
        System.out.println("Please input a production company:");
        Scanner scn3 = new Scanner(System.in);
        String str= scn3.nextLine();
        int temp=0;
        List<Movie> movieList2 = new ArrayList();
        for (Movie s : movieList) {
            if (s.getCompany().equals(str)) {
                temp=1;
                movieList2.add(s);
            }
        }
        if(temp!=0){
        int[] index_array = new int[movieList2.size()];
        for (int i = 0; i < movieList2.size(); i++) {
            index_array[i] = movieList2.get(i).getReleasing_year();
        }
        for (int i = 0; i < index_array.length - 1; i++) {
            for (int j = i + 1; j < index_array.length; j++) {
                if (index_array[i] < index_array[j]) {
                    int t = index_array[i];
                    index_array[i] = index_array[j];
                    index_array[j] = t;
                }
            }
        }
        for (int i = 0; i <index_array.length; i++) {
            for (int j = 0; j < movieList2.size(); j++) {
                if (index_array[i] == movieList2.get(j).getReleasing_year()) {
                    Movie s = movieList2.get(j);
                    System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
                    movieList2.remove(j);
                }
            }
        }}
        if(temp==0) System.out.println("No such production company with this name");
    }
    public static void company_maximum_revenue_movies(){
        System.out.println("Please input a production company:");
        Scanner scn3 = new Scanner(System.in);
        String str= scn3.nextLine();

        List<Movie> movieList2 = new ArrayList();
        for (Movie s : movieList) {
            if (s.getCompany().equals(str)) {
                movieList2.add(s);
            }
        }
        if(movieList2.size()>0){
        long[] index_array = new long[movieList2.size()];
        for (int i = 0; i < movieList2.size(); i++) {
            index_array[i] = movieList2.get(i).getRevenue();
        }
        for (int i = 0; i < index_array.length - 1; i++) {
            for (int j = i + 1; j < index_array.length; j++) {
                if (index_array[i] < index_array[j]) {
                    long t = index_array[i];
                    index_array[i] = index_array[j];
                    index_array[j] = t;
                }
            }
        }
        for (int i = 0; i <index_array.length; i++) {
            for (int j = 0; j < movieList2.size(); j++) {
                if (index_array[i] == movieList2.get(j).getRevenue()) {
                    Movie s = movieList2.get(j);
                    System.out.println(s.getMovie_name() + " " + s.getReleasing_year() + " " + s.getGenre1() + " " + s.getGenre2() + " " + s.getGenre3() + " " + s.getRunning_time() + " " + s.getCompany() + " " + s.getBudget() + " " + s.getRevenue());
                    movieList2.remove(j);
                }
            }
        }}
        else System.out.println("No such production company with this name");
    }
    public static void company_total_profit(){
        System.out.println("Please input a production company:");
        Scanner scn3 = new Scanner(System.in);
        String str= scn3.nextLine();

        List<Movie> movieList2 = new ArrayList();
        for (Movie s : movieList) {
            if (s.getCompany().equals(str)) {
                movieList2.add(s);
            }
        }
        if(movieList2.size()>0){
        double total_profit=0;
        for (Movie s : movieList2) {
            total_profit+=s.getRevenue()-s.getBudget();
        }
        System.out.println("Total Profit:"+total_profit);
        }
        else System.out.println("No such production company with this name");
    }
    public static void all_company_and_movies_count(){
        List<String> company_list= new ArrayList();
        company_list.add(movieList.get(1).getCompany());

        for (int i = 0; i < movieList.size(); i++) {
            String temp_name= movieList.get(i).getCompany();
            for (int j =0 ; j < company_list.size(); j++) {
                int ch=0;
                for (String s:company_list) {
                    if(s.equals(temp_name)) ch++;
                }
                if(ch==0) {
                    company_list.add(temp_name);
                }
            }
        }

            for (int i = 0; i <company_list.size(); i++) {
               String temp_name = company_list.get(i);
                int count=0;
                for (int j=0; j<movieList.size(); j++) {
                    if (movieList.get(j).getCompany().equals(temp_name)) {
                        count++;
                    }
                }
                System.out.println(temp_name + ", Total Number of Movies:" + count);
            }
        /*for (int i = 0; i <company_list.size(); i++) {
            String temp_name = company_list.get(i);
            int count=0;
            for (String s:company_list){
                if (s.equals(temp_name)) count++;
            }
            System.out.println(temp_name +" "+ count);
        }*/
    }
    public static void add_new_movie_in_database(){
        Movie temp_movie_for_add =new Movie();

        System.out.println("Please Enter the Movie Name:");
        Scanner add_scn = new Scanner(System.in);
        String str= add_scn.nextLine();
        temp_movie_for_add.setMovie_name(str);

        System.out.println("Please Enter Releasing year:");
        add_scn= new Scanner(System.in);
        double temp_var=add_scn.nextInt();
        temp_movie_for_add.setReleasing_year((int) temp_var);

        System.out.println("Please Enter Running Time:");
        add_scn= new Scanner(System.in);
        temp_var=add_scn.nextInt();
        temp_movie_for_add.setRunning_time((int) temp_var);

        System.out.println("Please Enter Maximum three genre of the Movie(comma(\",\") separated):");
        add_scn=new Scanner(System.in);
        String Genre = add_scn.nextLine();
        String[] split_genre=Genre.split(",");
        temp_movie_for_add.setGenre1(split_genre[0]);
        temp_movie_for_add.setGenre2(split_genre[1]);
        temp_movie_for_add.setGenre3(split_genre[2]);

        System.out.println("Please Enter the Company Name:");
        add_scn=new Scanner(System.in);
        str= add_scn.nextLine();
        temp_movie_for_add.setCompany(str);

        System.out.println("Please Enter Budget of the Movie:");
        add_scn= new Scanner(System.in);
        temp_var=add_scn.nextInt();
        temp_movie_for_add.setBudget((int) temp_var);

        System.out.println("Please Enter Revenue of the Movie:");
        add_scn= new Scanner(System.in);
        temp_var=add_scn.nextInt();
        temp_movie_for_add.setRevenue((int) temp_var);

        movieList.add(temp_movie_for_add);
    }
}

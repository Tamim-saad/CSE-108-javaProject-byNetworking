class Movie {
    private String Movie_name;
    private int Releasing_year;
    private String Genre1;
    private String Genre2;
    private String Genre3;
    private int Running_time;
    private String Company;
    private long Budget;
    private long Revenue;

    public Movie(String name,int year,String g1,String g2,String g3, int time,String company,long budget,long revenue){
        Movie_name=name;
        Releasing_year=year;
        Genre1=g1;
        Genre2=g2;
        Genre3=g3;
        Running_time=time;
        Company=company;
        Budget=budget;
        Revenue=revenue;
    }
    public Movie(){    }
    public void setMovie_name(String movie_name) {
        Movie_name = movie_name;
    }
    public void setReleasing_year(int releasing_year) {
        Releasing_year = releasing_year;
    }
    public void setGenre1(String genre1) {
        Genre1 = genre1;
    }
    public void setGenre2(String genre2) {
        Genre2 = genre2;
    }
    public void setGenre3(String genre3) {
        Genre3 = genre3;
    }
    public void setRunning_time(int running_time) {
        Running_time = running_time;
    }
    public void setCompany(String company) {
        Company = company;
    }
    public void setBudget(long budget) {
        Budget = budget;
    }
    public void setRevenue(long revenue) {
        Revenue = revenue;
    }
    public int getReleasing_year() {
        return Releasing_year;
    }
    public int getRunning_time() {
        return Running_time;
    }
    public long getBudget() {
        return Budget;
    }
    public long getRevenue() {
        return Revenue;
    }
    public String getCompany() {
        return Company;
    }
    public String getGenre1() {
        return Genre1;
    }
    public String getGenre2() {
        return Genre2;
    }
    public String getMovie_name() {
        return Movie_name;
    }
    public String getGenre3() {
        return Genre3;
    }
}
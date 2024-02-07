package server;

import commonClassPackage.DataWrapper;
import commonClassPackage.Movie;
import commonClassPackage.SocketWrapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.currentThread;
import java.util.*;

public class Server {
    private static Boolean transferStatus=false;
    private static String transferredCompany;
    public  static HashMap<String,SocketWrapper> clients=new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        HashMap<String, String> userMap;
//
//        userMap = new HashMap<>();
//        userMap.put("a", "1");
//        userMap.put("b", "b");
//        userMap.put("c", "c");
//        userMap.put("d", "d");
//        userMap.put("e", "e");


/////////////////////////////////////////////////////////////
        new Thread(() -> {

            ServerSocket server= null;
            try {
                server = new ServerSocket(3333);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Server started");
            ArrayList<Object> arrayList=new ArrayList<Object>();
            //HashMap<Integer, Object> clientsList=new HashMap<Integer,Object>();

            while (true) {
                /////////////////////////////////
                List<Movie> movieList = new ArrayList<>();
                List<Movie> companyMovieList = new ArrayList<>();

                Socket clientSocket = null;
                SocketWrapper client = null;
                Object finalObjectdata;
                String stringData;
                String[] splitClientCommand;

                try {
                    clientSocket = server.accept();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    client = new SocketWrapper(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Object objectdata = new Object();
                try {
                    objectdata = client.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                finalObjectdata = objectdata;
                 stringData=(String) objectdata;
                 splitClientCommand = stringData.split(",");
                clients.put(splitClientCommand[1], client);

                /////////////////////////////////////////////////////////////////
                if (splitClientCommand[0].equalsIgnoreCase("confirmFromTransferredCompany")) {
                    transferStatus=false;
                }
                else if (splitClientCommand[0].equals("giveMyList") && splitClientCommand[1] != null) {
                    // new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;
                            String[] out = line.split(",");
                            Movie temp_movie = new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8]));
                            movieList.add(temp_movie);
                        }
                        br.close();
                        for (Movie s : movieList) {
                            if (splitClientCommand[1].equalsIgnoreCase(s.getCompany())) {
                                companyMovieList.add(s);
                            }
                        }
                        DataWrapper serverToCompany = new DataWrapper(splitClientCommand[1], companyMovieList, transferStatus);
                        clients.get(splitClientCommand[1]).write(serverToCompany);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (splitClientCommand[0].equals("addAndUpdateMyList")) {
                    //new Thread(() -> {
                    try {
                        //appending new movie to existing text file
                        BufferedWriter output = new BufferedWriter(new FileWriter("movies.txt", true));
                        String addTemp = splitClientCommand[1] + "," + splitClientCommand[2] + "," + splitClientCommand[3] + "," + splitClientCommand[4] + "," + splitClientCommand[5] + "," + splitClientCommand[6] + "," + splitClientCommand[7] + "," + splitClientCommand[8] + "," + splitClientCommand[9];
                        output.write(addTemp + System.lineSeparator());
                        output.close();

                        //making movies array
                        BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;
                            String[] out = line.split(",");
                            Movie temp_movie = new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8]));
                            movieList.add(temp_movie);
                        }
                        br.close();

                        //making company movies array
                        for (Movie s : movieList) {
                            if (splitClientCommand[7].equalsIgnoreCase(s.getCompany())) {
                                companyMovieList.add(s);
                            }
                        }
                        //sending company movies array
                        DataWrapper serverToCompany = new DataWrapper(companyMovieList.get(0).getCompany(), companyMovieList, transferStatus);
                        clients.get(splitClientCommand[1]).write(serverToCompany);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (splitClientCommand[0].equals("deleteAndUpdateMyList")) {
                    //new Thread(() -> {
                    try {
                        //making movies array
                        BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;
                            String[] out = line.split(",");
                            Movie temp_movie = new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8]));
                            movieList.add(temp_movie);
                        }
                        br.close();
                        //deleting movie
                        String name2 = currentThread().getName();
                        for (Movie s : movieList) {
                            if (s.getMovie_name().equalsIgnoreCase(splitClientCommand[2])) {
                                movieList.remove(s);
                                break;
                            }
                        }

                        //write changed information to existing file
                        Writer writer = new BufferedWriter(new FileWriter("movies.txt"));
                        for (int i = 0; i < movieList.size(); i++) {
                            String addTemp = movieList.get(i).getMovie_name() + "," + movieList.get(i).getReleasing_year() + "," + movieList.get(i).getGenre1() + "," + movieList.get(i).getGenre2() + "," + movieList.get(i).getGenre3() + "," + movieList.get(i).getRunning_time() + "," + movieList.get(i).getCompany() + "," + movieList.get(i).getBudget() + "," + movieList.get(i).getRevenue();
                            writer.write(addTemp + System.lineSeparator());
                        }
                        writer.close();
                        //making and passing company's movie array
                        for (Movie s : movieList) {
                            if (splitClientCommand[1].equalsIgnoreCase(s.getCompany())) {
                                companyMovieList.add(s);
                            }
                        }
//                            client.write(companyMovieList);
                        DataWrapper serverToCompany = new DataWrapper(companyMovieList.get(0).getCompany(), companyMovieList, transferStatus);
                        clients.get(splitClientCommand[1]).write(serverToCompany);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (splitClientCommand[0].equals("transferAndUpdateMyList")) {
                    try {
                        //making movies array
                        BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;
                            String[] out = line.split(",");
                            Movie temp_movie = new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8]));
                            movieList.add(temp_movie);
                        }
                        br.close();

                        //transferring movie
                        for (Movie s : movieList) {
                            if (s.getMovie_name().equalsIgnoreCase(splitClientCommand[2])) {
                                s.setCompany(splitClientCommand[3]);
                                break;
                            }
                        }

                        //write changed information to existing file
                        Writer writer = new BufferedWriter(new FileWriter("movies.txt"));
                        for (int i = 0; i < movieList.size(); i++) {
                            String addTemp = movieList.get(i).getMovie_name() + "," + movieList.get(i).getReleasing_year() + "," + movieList.get(i).getGenre1() + "," + movieList.get(i).getGenre2() + "," + movieList.get(i).getGenre3() + "," + movieList.get(i).getRunning_time() + "," + movieList.get(i).getCompany() + "," + movieList.get(i).getBudget() + "," + movieList.get(i).getRevenue();
                            writer.write(addTemp + System.lineSeparator());
                        }
                        writer.close();
                        //making and passing company's movie array
                        transferStatus = true;
                        for (Movie s : movieList) {
                            if (splitClientCommand[1].equalsIgnoreCase(s.getCompany())) {
                                companyMovieList.add(s);
                            }
                        }
                        DataWrapper serverToCompany = new DataWrapper(splitClientCommand[1], companyMovieList, transferStatus);
                        clients.get(splitClientCommand[1]).write(serverToCompany);

                        //transferring movie array to transferred company
                        List<Movie> transferredCompanyMovieList=new ArrayList<>();

                        for(Movie s:movieList){
                            if (splitClientCommand[3].equalsIgnoreCase(s.getCompany())){
                                transferredCompanyMovieList.add(s);
                            }
                        }
                        DataWrapper serverToTransferredCompany = new DataWrapper(splitClientCommand[3], transferredCompanyMovieList, transferStatus);

                        clients.get(splitClientCommand[3]).write(serverToTransferredCompany);
                        //transferStatus=false;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        /*
        ServerSocket server2 = new ServerSocket(1111);
        System.out.println("Server started by 1111");
        new Thread(() -> {
            Socket clientSocket2 = null;
            try {
                clientSocket2 = server2.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SocketWrapper client = null;
            try {
                client = new SocketWrapper(clientSocket2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Object objectdata2 = new Object();
            try {
                objectdata2 = client.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Object finalObjectdata2 = objectdata2;
            String stringData2 = (String) finalObjectdata2;
            String[] splitClientCommand2 = stringData2.split(",");
            //System.out.println(splitClientCommand[0]);


            if (splitClientCommand2[0].equals("checkTransferStatus")&&transferStatus==true&&splitClientCommand2[1].equalsIgnoreCase(transferredCompany)) {
                for (Movie s : movieList) {
                    if (splitClientCommand2[1].equalsIgnoreCase(s.getCompany())) {
                        companyMovieList.add(s);
                    }
                }
                DataWrapper serverToCompany = new DataWrapper(splitClientCommand2[1], companyMovieList, transferStatus);
                try {
                    client.write(serverToCompany);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                transferStatus=false;
            }
        }).start();*/
    }
}
package commonClassPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataWrapper implements Serializable {
    public String company;
    public List movieArray;
    public Boolean Status =false;
    public DataWrapper(String command, List data,Boolean changeStatus){
        this.company=command;
        this.movieArray=data;
        this.Status=changeStatus;
    }
}

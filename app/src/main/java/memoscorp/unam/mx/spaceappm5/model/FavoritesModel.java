package memoscorp.unam.mx.spaceappm5.model;

import java.io.Serializable;

/**
 * Created by GuillermoAB on 16/08/2016.
 */
public class FavoritesModel implements Serializable {
    public int id;
    public String cImage_URI;
    public String cFullName;
    public String cEarth_Date;
    public String cRover_Name;
    public int nTotal_Photo;

    public FavoritesModel(int id,String cImage_URI,String cFullName,String cEarth_Date,String cRover_Name,int nTotal_Photo){
        this.id = id;
        this.cImage_URI = cImage_URI;
        this.cFullName = cFullName;
        this.cEarth_Date = cEarth_Date;
        this.cRover_Name = cRover_Name;
        this.nTotal_Photo = nTotal_Photo;
    }
}

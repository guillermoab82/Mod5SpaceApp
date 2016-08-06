package memoscorp.unam.mx.spaceappm5.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    private Integer id;
    @SerializedName("sol")
    private Integer sol;
    @SerializedName("camera")
    private Camera camera;
    @SerializedName("img_src")
    private String imgSrc;
    @SerializedName("earth_date")
    private String earthDate;
    @SerializedName("rover")
    private Rover rover;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The sol
     */
    public Integer getSol() {
        return sol;
    }

    /**
     * 
     * @param sol
     *     The sol
     */
    public void setSol(Integer sol) {
        this.sol = sol;
    }

    /**
     * 
     * @return
     *     The camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * 
     * @param camera
     *     The camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * 
     * @return
     *     The imgSrc
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * 
     * @param imgSrc
     *     The img_src
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     * 
     * @return
     *     The earthDate
     */
    public String getEarthDate() {
        return earthDate;
    }

    /**
     * 
     * @param earthDate
     *     The earth_date
     */
    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    /**
     * 
     * @return
     *     The rover
     */
    public Rover getRover() {
        return rover;
    }

    /**
     * 
     * @param rover
     *     The rover
     */
    public void setRover(Rover rover) {
        this.rover = rover;
    }

}

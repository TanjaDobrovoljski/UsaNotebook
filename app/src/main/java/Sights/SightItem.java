package Sights;

public class SightItem {
    private String title="";
    private String description="";
    private String id;
    private String secret;
    private String server;
    private String farm;
    private Tuple<Double,Double> coordinates;

    public SightItem(){}
    public SightItem (String title,String description,Tuple<Double,Double> coordinates,String id, String secret, String server, String farm)
    {
        this.title=title;
        this.description=description;
        this.coordinates=coordinates;
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public SightItem(String id, String secret, String server, String farm) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tuple<Double, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Tuple<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }


    public String getId() {
        return id;
    }
    public String getUrl() {
        return "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg";
    }
}

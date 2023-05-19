package Sights;

public class SightItem {
    private String title="";
    private String description="";
    private Tuple<Double,Double> coordinates;

    public SightItem(){}
    public SightItem (String title,String description,Tuple<Double,Double> coordinates)
    {
        this.title=title;
        this.description=description;
        this.coordinates=coordinates;
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
}

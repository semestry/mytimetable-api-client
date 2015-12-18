package nl.eveoh.mytimetable.apiclient.model;

/**
 * @author Erik van Paassen
 */
public class LocationTimetable extends Timetable {

    private int locationCapacity;

    private String locationUrl;

    public LocationTimetable() {
        super();
    }

    public LocationTimetable(String key, String description, int locationCapacity, String locationUrl) {
        super(key, description);
        this.locationCapacity = locationCapacity;
        this.locationUrl = locationUrl;
    }

    public int getLocationCapacity() {
        return locationCapacity;
    }

    public void setLocationCapacity(int locationCapacity) {
        this.locationCapacity = locationCapacity;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }
}

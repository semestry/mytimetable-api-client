package nl.eveoh.mytimetable.apiclient.model;

/**
 * @author Erik van Paassen
 */
public class LocationTimetable extends Timetable {

    private int locationCapacity;


    public LocationTimetable() {
        super();
    }

    public LocationTimetable(String key, String description, int locationCapacity) {
        super(key, description);
        this.locationCapacity = locationCapacity;
    }

    public int getLocationCapacity() {
        return locationCapacity;
    }

    public void setLocationCapacity(int locationCapacity) {
        this.locationCapacity = locationCapacity;
    }
}

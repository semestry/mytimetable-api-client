package nl.eveoh.mytimetable.apiclient.model;

import com.google.common.base.MoreObjects;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;

/**
 * A location timetable
 * <p>
 * Has additional location-specific properties.
 *
 * @author Erik van Paassen
 */
public class LocationTimetable extends Timetable {

    /**
     * Capacity of the location
     */
    private int locationCapacity;

    /**
     * URL of the location
     *
     * <p>
     * This value is only set for {@link Configuration.MyTimetable_Version} >= {@link
     * Configuration.MyTimetable_Version#V3_1}.
     */
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("locationCapacity", locationCapacity)
                .add("locationUrl", locationUrl)
                .toString();
    }
}

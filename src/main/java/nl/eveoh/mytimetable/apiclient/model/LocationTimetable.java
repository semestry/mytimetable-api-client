package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("locationCapacity")
    private int capacity;

    /**
     * URL of the location
     * <p>
     * This value is only set for {@link Configuration.MyTimetable_Version} >= {@link
     * Configuration.MyTimetable_Version#V3_1}.
     */
    @JsonProperty("locationUrl")
    private String url;


    public LocationTimetable() {
        super();
    }

    public LocationTimetable(String key, String description, int capacity, String url) {
        super(key, description);
        this.capacity = capacity;
        this.url = url;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("capacity", capacity).add("url", url).toString();
    }
}

package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * A timetable
 *
 * @author Erik van Paassen
 */
public class Timetable {

    /**
     * Unique identifier of the timetable
     */
    @JsonProperty("value")
    private String key;

    /**
     * Host key of the timetable
     */
    private String hostKey;

    /**
     * Description of the timetable
     */
    private String description;


    public Timetable() {
    }

    public Timetable(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHostKey() {
        return hostKey;
    }

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key", key)
                .add("hostKey", hostKey)
                .add("description", description)
                .toString();
    }
}

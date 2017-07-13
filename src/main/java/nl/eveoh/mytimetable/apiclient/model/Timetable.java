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
    private String id;

    /**
     * External identifier of the timetable
     */
    @JsonProperty("hostKey")
    private String key;

    /**
     * Description of the timetable
     */
    private String description;


    public Timetable() {
    }

    public Timetable(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
                .add("id", id)
                .add("key", key)
                .add("description", description)
                .toString();
    }
}

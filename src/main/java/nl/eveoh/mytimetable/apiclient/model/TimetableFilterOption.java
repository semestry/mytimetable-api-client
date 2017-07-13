package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Filter option for a {@link TimetableFilterType}
 *
 * @author Erik van Paassen
 */
public class TimetableFilterOption {

    /**
     * Unique identifier of the filter option
     */
    @JsonProperty("value")
    private String id;

    /**
     * External identifier of the filter option
     */
    @JsonProperty("hostKey")
    private String key;

    /**
     * Description of the filter option
     */
    @JsonProperty("name")
    private String description;


    public TimetableFilterOption() {
    }

    public TimetableFilterOption(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public TimetableFilterOption(String id, String key, String description) {
        this.id = id;
        this.key = key;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimetableFilterOption that = (TimetableFilterOption) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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

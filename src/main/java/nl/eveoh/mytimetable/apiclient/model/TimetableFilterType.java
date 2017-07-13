package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Filter type. Represents a filter dropdown box, which appears when searching for timetables.
 *
 * @author Erik van Paassen
 */
public class TimetableFilterType {

    /**
     * Uniquely identified type of the filter
     */
    @JsonProperty("type")
    private String id;

    /**
     * Possible options for this filter
     *
     * @see TimetableFilterOption
     */
    @JsonProperty("option")
    private List<TimetableFilterOption> options;


    public TimetableFilterType() {
    }

    public TimetableFilterType(String id, List<TimetableFilterOption> options) {
        this.id = id;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TimetableFilterOption> getOptions() {
        return options;
    }

    public void setOptions(List<TimetableFilterOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("options", options).toString();
    }
}

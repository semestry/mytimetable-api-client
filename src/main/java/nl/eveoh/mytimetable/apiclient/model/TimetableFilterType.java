package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Filter type
 *
 * @author Erik van Paassen
 */
public class TimetableFilterType {

    /**
     * The uniquely identified type of the filter
     */
    private String type;

    /**
     * The possible options for this filter
     *
     * @see TimetableFilterOption
     */
    @JsonProperty("option")
    private List<TimetableFilterOption> options;


    public TimetableFilterType() {
    }

    public TimetableFilterType(String type, List<TimetableFilterOption> options) {
        this.type = type;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TimetableFilterOption> getOptions() {
        return options;
    }

    public void setOptions(List<TimetableFilterOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", type).add("options", options).toString();
    }
}

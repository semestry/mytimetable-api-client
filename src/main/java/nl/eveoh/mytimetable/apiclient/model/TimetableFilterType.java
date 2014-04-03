package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Erik van Paassen
 */
public class TimetableFilterType {

    private String type;

    @JsonProperty("option")
    private List<TimetableFilterOption> options;


    public TimetableFilterType() {}

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
}

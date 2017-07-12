package nl.eveoh.mytimetable.apiclient.model;

import com.google.common.base.MoreObjects;

/**
 * Filter option
 *
 * @author Erik van Paassen
 */
public class TimetableFilterOption {

    /**
     * The human readable name of the filter option
     */
    private String name;

    /**
     * The value that uniquely identifies the filter option
     */
    private String value;

    /**
     * The host key of the filter option
     */
    private String hostKey;


    public TimetableFilterOption() {
    }

    public TimetableFilterOption(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public TimetableFilterOption(String name, String value, String hostKey) {
        this.name = name;
        this.value = value;
        this.hostKey = hostKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHostKey() {
        return hostKey;
    }

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
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

        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("value", value)
                .add("hostKey", hostKey)
                .toString();
    }
}

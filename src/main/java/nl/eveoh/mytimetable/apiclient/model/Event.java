/*
 * Copyright 2013 - 2014 Eveoh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.eveoh.mytimetable.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * A timetable event.
 *
 * @author Marco Krikke
 */
public class Event {

    /**
     * Description of the activity.
     */
    @JsonProperty(required = true)
    private String activityDescription;

    /**
     * Start date of the activity.
     */
    @JsonProperty(required = true)
    private Date startDate;

    /**
     * End date of the activity.
     */
    @JsonProperty(required = true)
    private Date endDate;

    /**
     * List of locations for this activity.
     */
    @JsonProperty(required = true)
    private List<Location> locations;

    /**
     * List of staff members for this activity.
     */
    @JsonProperty(required = true)
    private List<String> staffMembers;

    @JsonProperty(required = true)
    private String activityTypeDescription;

    public Event() {}

    public Event(String activityDescription, Date startDate, Date endDate, List<Location> locations, List<String> staffMembers, String activityTypeDescription) {
        this.activityDescription = activityDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locations = locations;
        this.staffMembers = staffMembers;
        this.activityTypeDescription = activityTypeDescription;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<String> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<String> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public String getActivityType() {
        return activityTypeDescription;
    }

    public void setActivityType(String activityTypeDescription) {
        this.activityTypeDescription = activityTypeDescription;
    }
}

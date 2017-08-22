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
    @JsonProperty(required = true, value = "moduleCode")
    private String activityCode;

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

    /**
     * Note 1 of the activity.
     */
    @JsonProperty(required = true, value = "notes")
    private String note1;

    /**
     * Note 2 of the activity.
     */
    @JsonProperty(required = true, value = "notes2")
    private String note2;

    /**
     * Note 3 of the activity.
     */
    @JsonProperty(required = true, value = "notes3")
    private String note3;


    public Event() {}

    public Event(String activityDescription, Date startDate, Date endDate, List<Location> locations,
                 List<String> staffMembers, String activityTypeDescription) {
        this.activityDescription = activityDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locations = locations;
        this.staffMembers = staffMembers;
        this.activityTypeDescription = activityTypeDescription;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
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

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }
}

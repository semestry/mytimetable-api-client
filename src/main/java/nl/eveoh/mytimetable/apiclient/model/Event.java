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
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.base.MoreObjects;

import java.util.Date;
import java.util.List;

/**
 * A timetable event.
 *
 * @author Marco Krikke
 */
public class Event {

    /**
     * Unique id of the activity.
     */
    private String id;

    /**
     * Description of the activity.
     */
    private String activityDescription;

    /**
     * Module code of the activity. Can be {@code null}.
     */
    private String moduleCode;

    /**
     * Start date of the activity.
     */
    private Date startDate;

    /**
     * End date of the activity.
     */
    private Date endDate;

    /**
     * Department for this activity
     */
    private Department department;

    /**
     * Event type of the activity
     */
    @JsonUnwrapped
    private EventType eventType;

    /**
     * List of staff members for this activity.
     */
    private List<StaffMember> staffMembers;

    /**
     * List of locations for this activity.
     */
    private List<Location> locations;

    /**
     * List of student sets for this activity.
     */
    private List<String> studentSets;

    /**
     * List of tags for this activity.
     */
    private List<Tag> tags;

    /**
     * Note 1
     */
    @JsonProperty("notes")
    private String note1;

    /**
     * Note 2
     */
    @JsonProperty("notes2")
    private String note2;

    /**
     * Note 3
     */
    @JsonProperty("notes3")
    private String note3;

    /**
     * True if highlighted
     */
    private boolean highlighted;

    /**
     * True if filtered
     */
    private boolean filtered;


    public Event() {
    }

    public Event(String activityDescription, Date startDate, Date endDate, List<Location> locations) {
        this.activityDescription = activityDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locations = locations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EventType getEventType() {
        return eventType.getId() == null ? null : eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<String> getStudentSets() {
        return studentSets;
    }

    public void setStudentSets(List<String> studentSets) {
        this.studentSets = studentSets;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("activityDescription", activityDescription)
                .add("moduleCode", moduleCode)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("department", department)
                .add("eventType", eventType)
                .add("staffMembers", staffMembers)
                .add("locations", locations)
                .add("studentSets", studentSets)
                .add("tags", tags)
                .add("note1", note1)
                .add("note2", note2)
                .add("note3", note3)
                .add("highlighted", highlighted)
                .add("filtered", filtered)
                .toString();
    }
}

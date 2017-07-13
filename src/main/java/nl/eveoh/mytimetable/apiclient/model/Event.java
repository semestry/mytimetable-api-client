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
 * A timetable event
 *
 * @author Marco Krikke
 */
public class Event {

    /**
     * Unique identifier of the activity.
     */
    private String id;

    /**
     * Description of the activity.
     */
    @JsonProperty("activityDescription")
    private String description;

    /**
     * External identifier of the module of the activity (usually the module code). Can be {@code null}.
     */
    @JsonProperty("moduleCode")
    private String moduleKey;

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
    private List<StudentSet> studentSets;

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

    public Event(String description, Date startDate, Date endDate, List<Location> locations) {
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey;
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

    public List<StudentSet> getStudentSets() {
        return studentSets;
    }

    public void setStudentSets(List<StudentSet> studentSets) {
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
                .add("description", description)
                .add("moduleKey", moduleKey)
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

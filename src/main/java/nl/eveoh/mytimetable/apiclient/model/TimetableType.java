/*
 * Copyright 2013 - 2017 Eveoh
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
import com.google.common.base.MoreObjects;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;

/**
 * A timetable type
 *
 * @author Marco Krikke
 */
public class TimetableType {

    /**
     * Unique identifier of the timetable type
     */
    @JsonProperty("name")
    private String id;

    /**
     * Description of the timetable type
     */
    private String description;

    /**
     * Weight determining the order the timetable types are displayed.
     */
    private int weight;

    /**
     * Parent timetable types can have child timetable types.
     * <p>
     * E.g. a building timetable can consist of multiple room child timetables.
     */
    private boolean parent;

    /**
     * True if any options can be selected on this timetable type.
     */
    private boolean optionSelectable;

    /**
     * True if options should be selectable from the user interface.
     * <p>
     * Returns {@code null} for {@link Configuration.MyTimetable_Version} = {@link
     * Configuration.MyTimetable_Version#V3_0}.
     *
     * @since MyTimetable 3.1
     */
    private Boolean optionSelectableInInterface;

    /**
     * True if any options can be selected on the child timetables of this timetable type.
     */
    private boolean childOptionSelectable;

    /**
     * True if child options should be selectable from the user interface.
     * <p>
     * Returns {@code null} for {@link Configuration.MyTimetable_Version} = {@link
     * Configuration.MyTimetable_Version#V3_0}.
     *
     * @since MyTimetable 3.1
     */
    private Boolean childOptionSelectableInInterface;


    public TimetableType() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public boolean isOptionSelectable() {
        return optionSelectable;
    }

    public void setOptionSelectable(boolean optionSelectable) {
        this.optionSelectable = optionSelectable;
    }

    public Boolean getOptionSelectableInInterface() {
        return optionSelectableInInterface;
    }

    public void setOptionSelectableInInterface(Boolean optionSelectableInInterface) {
        this.optionSelectableInInterface = optionSelectableInInterface;
    }

    public boolean isChildOptionSelectable() {
        return childOptionSelectable;
    }

    public void setChildOptionSelectable(boolean childOptionSelectable) {
        this.childOptionSelectable = childOptionSelectable;
    }

    public Boolean getChildOptionSelectableInInterface() {
        return childOptionSelectableInInterface;
    }

    public void setChildOptionSelectableInInterface(Boolean childOptionSelectableInInterface) {
        this.childOptionSelectableInInterface = childOptionSelectableInInterface;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("weight", weight)
                .add("parent", parent)
                .add("optionSelectable", optionSelectable)
                .add("optionSelectableInInterface", optionSelectableInInterface)
                .add("childOptionSelectable", childOptionSelectable)
                .add("childOptionSelectableInInterface", childOptionSelectableInInterface)
                .toString();
    }
}

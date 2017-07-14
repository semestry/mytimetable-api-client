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

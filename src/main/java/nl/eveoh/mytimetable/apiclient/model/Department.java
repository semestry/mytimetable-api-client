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
 * Department for an event
 *
 * @author Marco Krikke
 */
public class Department {

    /**
     * Unique identifier of the department
     */
    private String id;

    /**
     * External identifier of the department
     * <p>
     * This value is only set for {@link Configuration.MyTimetable_Version} >= {@link
     * Configuration.MyTimetable_Version#V3_2} (patch version). Defaults to null.
     *
     * @since MyTimetable 3.2
     */
    private String key;

    /**
     * Description of the department
     */
    @JsonProperty("name")
    private String description;


    public Department() {
    }

    public Department(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("key", key)
                .add("description", description)
                .toString();
    }
}

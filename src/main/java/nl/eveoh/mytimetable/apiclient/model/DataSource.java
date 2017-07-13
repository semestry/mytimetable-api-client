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
 * MyTimetable data source
 *
 * @author Marco Krikke
 */
public class DataSource {

    /**
     * Unique identifier of the data source
     */
    @JsonProperty("key")
    private String id;

    /**
     * Description of the data source
     * <p>
     * This value is only set for {@link Configuration.MyTimetable_Version} >= {@link
     * Configuration.MyTimetable_Version#V3_1}.
     *
     * @since MyTimetable 3.1
     */
    @JsonProperty("label")
    private String description;


    public DataSource() {
    }

    public DataSource(String id) {
        this.id = id;
    }

    public DataSource(String id, String description) {
        this.id = id;
        this.description = description;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("description", description).toString();
    }
}

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
 * A location timetable
 * <p>
 * Has additional location-specific properties.
 *
 * @author Erik van Paassen
 */
public class LocationTimetable extends Timetable {

    /**
     * Capacity of the location
     */
    @JsonProperty("locationCapacity")
    private int capacity;

    /**
     * URL of the location
     * <p>
     * This value is only set for {@link Configuration.MyTimetable_Version} >= {@link
     * Configuration.MyTimetable_Version#V3_1}.
     */
    @JsonProperty("locationUrl")
    private String url;


    public LocationTimetable() {
        super();
    }

    public LocationTimetable(String key, String description, int capacity, String url) {
        super(key, description);
        this.capacity = capacity;
        this.url = url;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("capacity", capacity).add("url", url).toString();
    }
}

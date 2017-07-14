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

import java.util.ArrayList;

/**
 * Location for an event
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class Location {

    /**
     * Unique identifier of the location
     */
    private String id;

    /**
     * External identifier of the location
     */
    private String key;

    /**
     * Description of the location
     */
    @JsonProperty("name")
    private String description;

    /**
     * Capacity of the location
     */
    private int capacity;

    /**
     * Capacity of the location when used for exams. Can be {@code null}.
     */
    private Integer examCapacity;

    /**
     * URL of the location
     */
    private String url;

    /**
     * List of unique identifiers of locations that are 'avoid concurrent' with this location
     */
    private ArrayList<String> avoidConcurrencyLocationIds;


    public Location() {
    }

    public Location(String description) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getExamCapacity() {
        return examCapacity;
    }

    public void setExamCapacity(Integer examCapacity) {
        this.examCapacity = examCapacity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getAvoidConcurrencyLocationIds() {
        return avoidConcurrencyLocationIds;
    }

    public void setAvoidConcurrencyLocationIds(ArrayList<String> avoidConcurrencyLocationIds) {
        this.avoidConcurrencyLocationIds = avoidConcurrencyLocationIds;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("key", key)
                .add("description", description)
                .add("capacity", capacity)
                .add("examCapacity", examCapacity)
                .add("url", url)
                .add("avoidConcurrencyLocationIds", avoidConcurrencyLocationIds)
                .toString();
    }
}

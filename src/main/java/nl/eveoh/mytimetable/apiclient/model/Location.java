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

import com.google.common.base.MoreObjects;

import java.util.ArrayList;

/**
 * Location for an event.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class Location {

    /**
     * The unique identifier of the location
     */
    private String id;

    /**
     * The human readable name of the location
     */
    private String name;

    /**
     * The external identifier of the location
     */
    private String key;

    /**
     * The capacity of the location
     */
    private int capacity;

    /**
     * The capacity of the location when used for exams. Can be {@code null}.
     */
    private Integer examCapacity;

    /**
     * The URL of the location
     */
    private String url;

    /**
     * A list of unique identifiers of locations that are 'avoid concurrent' with this location
     */
    private ArrayList<String> avoidConcurrencyLocationIds;


    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
                .add("name", name)
                .add("key", key)
                .add("capacity", capacity)
                .add("examCapacity", examCapacity)
                .add("url", url)
                .add("avoidConcurrencyLocationIds", avoidConcurrencyLocationIds)
                .toString();
    }
}

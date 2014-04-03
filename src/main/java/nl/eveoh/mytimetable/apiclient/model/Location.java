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

import java.util.ArrayList;

/**
 * Location for an event.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class Location {

    private ArrayList<String> avoidConcurrencyLocationIds;
    private String capacity;
    private String id;
    private String key;

    /**
     * Name of the location.
     */
    private String name;

    private String url;



    public Location() {}

    public Location(String name) {
        this.name = name;
    }

    public ArrayList<String> getAvoidConcurrencyLocationIds() {
        return avoidConcurrencyLocationIds;
    }

    public void setAvoidConcurrencyLocationIds(ArrayList<String> avoidConcurrencyLocationIds) {
        this.avoidConcurrencyLocationIds = avoidConcurrencyLocationIds;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

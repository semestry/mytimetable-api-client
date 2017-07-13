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

/**
 * Staff member for an event.
 *
 * @author Marco Krikke
 */
public class StaffMember {

    /**
     * The human readable name of the staff member
     */
    private String name;

    /**
     * The identifier of the staff member
     */
    private String key;


    public StaffMember() {
    }

    public StaffMember(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("key", key).toString();
    }
}

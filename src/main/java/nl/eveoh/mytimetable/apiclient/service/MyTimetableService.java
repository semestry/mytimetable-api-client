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

package nl.eveoh.mytimetable.apiclient.service;

import nl.eveoh.mytimetable.apiclient.model.*;

import java.io.Closeable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface for a service which communicates with the MyTimetable API.
 */
public interface MyTimetableService extends Closeable {

    /**
     * Returns the upcoming events for the given user.
     *
     * @param username        username of the user to get the events of.
     *
     * @return List of events for the user.
     */
    public List<Event> getUpcomingEvents(String username, int limit);

    /**
     * Query the schedule for the specified timetable identifier.
     *
     * @param key       The key corresponding with the specific timetable, as retrieved using the {@code
     *                  getTimetables()} call.
     * @param startDate The start date to retrieve events from, or none for the beginning of times.
     * @param endDate   The end date to retrieve events till, or none for the end of times.
     * @param limit     The maximum amount of records returned, 0 for no limit.
     *
     * @return List of {@link Event} objects.
     */
    List<Event> getTimetableByKey(String key, Date startDate, Date endDate, int limit);

    /**
     * Query the schedule for the specified timetable identifier.
     *
     * @param key       The host key corresponding with the specific timetable.
     * @param type      The type of the timetable to retrieve.
     * @param startDate The start date to retrieve events from, or none for the beginning of times.
     * @param endDate   The end date to retrieve events till, or none for the end of times.
     * @param limit     The maximum amount of records returned, 0 for no limit.
     *
     * @return          List of {@link Event} objects.
     */
    public List<Event> getTimetableByHostKey(String key, String type, Date startDate, Date endDate, int limit);

    public List<Timetable> getTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters, int limit, int offset);

    public List<LocationTimetable> getLocationTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters, int limit, int offset);

    public List<TimetableFilterType> getTimetableFilters(String type, String d, Map<String, TimetableFilterOption> filters);
}

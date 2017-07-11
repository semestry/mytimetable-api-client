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
 *
 * @author Erik van Paassen
 * @author Marco Krikke
 */
public interface MyTimetableService extends Closeable {

    /**
     * Returns the upcoming events for the given user.
     *
     * @param username username of the user to get the events of
     * @param limit    The maximum number of {@link Event}s to return
     *
     * @return List of events for the user.
     */
    List<Event> getUpcomingEvents(String username, int limit);

    /**
     * Returns a list of events for the specified timetable identifier.
     *
     * @param key       The key corresponding with the specific timetable, as retrieved using the {@code
     *                  getTimetables()} call.
     * @param startDate The start date to retrieve events from, or {@code null} for the beginning of times.
     * @param endDate   The end date to retrieve events till, or {@code null} for the end of times.
     * @param limit     The maximum amount of records returned, or 0 for no limit.
     *
     * @return List of {@link Event} objects.
     */
    List<Event> getTimetableByKey(String key, Date startDate, Date endDate, int limit);

    /**
     * Returns a list of events for the specified timetable host key.
     *
     * @param key       The host key corresponding with the specific timetable.
     * @param type      The type of the timetable to retrieve.
     * @param startDate The start date to retrieve events from, or {@code null} for the beginning of times.
     * @param endDate   The end date to retrieve events till, or {@code null} for the end of times.
     * @param limit     The maximum amount of records returned, or 0 for no limit.
     *
     * @return List of {@link Event} objects.
     */
    List<Event> getTimetableByHostKey(String key, String type, Date startDate, Date endDate, int limit);

    /**
     * Returns a list of timetables for a specific type.
     *
     * @param type    The type of the timetables, e.g. 'module'.
     * @param d       Datasource to search in. When using {@code null}, the highest priority datasource is used (usually
     *                the most current data source).
     * @param q       Search string to filter results on.
     * @param filters Map containing multiple filters. The map key contains the filter name (e.g. 'department'), and the
     *                map value the selected filter value represented by a {@link TimetableFilterOption}. The filter
     *                value scan be retrieved via the {@link #getTimetableFilters(String, String, Map)} call. Or {@code
     *                null} when no filtering needs to be applied.
     * @param limit   The maximum amount of records returned, or 0 for no limit.
     * @param offset  Starting position of the records returned (e.g. offset=5 skips the first 5 records).
     *
     * @return List of {@link Timetable} objects.
     */
    List<Timetable> getTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters,
                                  int limit, int offset);

    /**
     * Returns a list of timetables for a specific location type.
     *
     * @param type    The type of the timetables, e.g. 'location'.
     * @param d       Datasource to search in. When using {@code null}, the highest priority datasource is used (usually
     *                the most current data source).
     * @param q       Search string to filter results on.
     * @param filters Map containing multiple filters. The map key contains the filter name (e.g. 'zone'), and the map
     *                value the selected filter value represented by a {@link TimetableFilterOption}. The filter value
     *                scan be retrieved via the {@link #getTimetableFilters(String, String, Map)} call. Or {@code null}
     *                when no filtering needs to be applied.
     * @param limit   The maximum amount of records returned, or 0 for no limit.
     * @param offset  Starting position of the records returned (e.g. offset=5 skips the first 5 records).
     *
     * @return List of {@link LocationTimetable} objects.
     */
    List<LocationTimetable> getLocationTimetables(String type, String d, String q,
                                                  Map<String, TimetableFilterOption> filters, int limit, int offset);

    /**
     * Returns all filters types available for a specific timetable type, including a list of possible filter
     * descriptions and identifiers (a list of {@link TimetableFilterOption}s.
     * <p>
     * Filters can filtered again, since they can depend on each other. One example are module timetables that can be
     * filtered on Department and Programme of Study. When retrieving the results for the type 'module', two filter
     * types 'department' and 'pos' are returned, including all possible filter values for those types:
     * <p>
     * <pre>
     * <code>
     *     filterattribute:
     *     - option:
     *       - hostKey: "ENG"
     *         name: Department of Engineering
     *         value: 9934A3FB833F4A77B926C1C09EE8B39A
     *       - hostKey: "LE&amp;G"
     *         name: Department of Law, Economics, and Governance
     *         value: 868A5C3C8FC0485FBAF93CA037E290A2
     *       - hostKey: "SCI"
     *         name: Department of Science
     *         value: 5E0B90EEBA1E4655847BF6C546D57B67
     *       type: department
     *     - option:
     *       - hostKey: "ASTR"
     *         name: Astrophysics
     *         value: F08E92E7FEFF4C2984CA6B557AB491D7
     *       - hostKey: "CE"
     *         name: Civil Engineering
     *         value: 960F8608992846669EB884B5546A8AC3
     *       - hostKey: "ECON"
     *         name: Economics and Business Economics
     *         value: DFA31B4515AE4450B8B3A9B850BC9BC4
     *       - hostKey: "MATH"
     *         name: Mathematics
     *         value: E89A6C967562494ABE489F4ADBAE5217
     *       - hostKey: "ME"
     *         name: Mechanical Engineering
     *         value: A9FD5B91A85B4A25A27D9AC3651B987C
     *       - hostKey: "PLAW"
     *         name: Patent Law
     *         value: B21E7E61367C493D988CB9DF49355ED9
     *       type: pos
     * </code>
     * </pre>
     * </p>
     * <p>
     * One can put in a specific value for the filter type 'department', which in return filters the values of the
     * dependant filter 'pos'. E.g. when filtering on filter type 'department' and the option for 'Department of
     * Engineering', one gets these results:
     * </p>
     * <p>
     * <pre>
     * <code>
     *     filterattribute:
     *     - option:
     *       - hostKey: "ENG"
     *         name: Department of Engineering
     *         value: 9934A3FB833F4A77B926C1C09EE8B39A
     *       - hostKey: "LE&amp;G"
     *         name: Department of Law, Economics, and Governance
     *         value: 868A5C3C8FC0485FBAF93CA037E290A2
     *       - hostKey: "SCI"
     *         name: Department of Science
     *         value: 5E0B90EEBA1E4655847BF6C546D57B67
     *       type: department
     *     - option:
     *       - hostKey: "CE"
     *         name: Civil Engineering
     *         value: 960F8608992846669EB884B5546A8AC3
     *       - hostKey: "ME"
     *         name: Mechanical Engineering
     *         value: A9FD5B91A85B4A25A27D9AC3651B987C
     *       type: pos
     * </code>
     * </pre>
     * </p>
     *
     * @param type    The type of the timetables to retrieve the filters for, e.g. 'location'.
     * @param d       Datasource to search in. When using {@code null}, the highest priority datasource is used (usually
     *                the most current data source).
     * @param filters Map containing multiple filters. The map key contains the filter name (e.g. 'department'), and the
     *                map value the selected filter value represented by a {@link TimetableFilterOption}. The filter
     *                value scan be retrieved via the {@link #getTimetableFilters(String, String, Map)} call. Or {@code
     *                null} when no filtering needs to be applied.
     *
     * @return List of {@link TimetableFilterType} objects.
     */
    List<TimetableFilterType> getTimetableFilters(String type, String d, Map<String, TimetableFilterOption> filters);
}

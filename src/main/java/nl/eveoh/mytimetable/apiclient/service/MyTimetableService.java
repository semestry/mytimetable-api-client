/*
 * Eveoh MyTimetable, Web interface for timetables.
 *
 * Copyright (c) 2010 - 2013 Eveoh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, see src/main/webapp/license/gpl-3.0.txt.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package nl.eveoh.mytimetable.apiclient.service;

import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.configuration.ConfigurationChangeListener;
import nl.eveoh.mytimetable.apiclient.model.Event;

import java.util.List;

/**
 * Interface for a service which communicates with the MyTimetable API.
 */
public interface MyTimetableService extends ConfigurationChangeListener {

    /**
     * Returns the upcoming events for the given user.
     *
     * @param username        username of the user to get the events of.
     * @param configuration Configuration object.
     *
     * @return List of events for the user.
     */
    public List<Event> getUpcomingEvents(String username, Configuration configuration);
}
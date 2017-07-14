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

package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.Event;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class EventListStreamMapperTest extends BaseStreamMapperTest<EventListStreamMapper> {

    @Override
    protected EventListStreamMapper getMapper() {
        return new EventListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<Event> events = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/eventlist.json"));

        Assert.assertEquals(2, events.size());

        // First event
        Assert.assertEquals("2017!066090D30ADAF22CC2B90B6EEA969D14-20170712", events.get(0).getId());
        Assert.assertEquals("Complex Functions", events.get(0).getDescription());
        Assert.assertNull(events.get(0).getModuleKey());
        Assert.assertEquals(new Date(1499867100000L), events.get(0).getStartDate());
        Assert.assertEquals(new Date(1499873400000L), events.get(0).getEndDate());

        Assert.assertEquals("2017!646ADCA666D4A88402CA46C26A73803C", events.get(0).getDepartment().getId());
        Assert.assertEquals("Department of Science", events.get(0).getDepartment().getDescription());

        Assert.assertNotNull(events.get(0).getEventType());
        Assert.assertEquals("LECTURE", events.get(0).getEventType().getId());
        Assert.assertEquals("Lecture", events.get(0).getEventType().getDescription());
        Assert.assertEquals("#2c3e50", events.get(0).getEventType().getColor());

        Assert.assertEquals(2, events.get(0).getStaffMembers().size());
        Assert.assertEquals("HAR", events.get(0).getStaffMembers().get(0).getKey());
        Assert.assertEquals("Mr. Harris (Har)", events.get(0).getStaffMembers().get(0).getDescription());
        Assert.assertEquals("WAL", events.get(0).getStaffMembers().get(1).getKey());
        Assert.assertEquals("Ms. Walker (Wal)", events.get(0).getStaffMembers().get(1).getDescription());

        Assert.assertEquals(2, events.get(0).getLocations().size());
        Assert.assertEquals("2017!62F1A7C5D0008267EEDD2BE7454F7A8E", events.get(0).getLocations().get(0).getId());
        Assert.assertEquals("N-A02", events.get(0).getLocations().get(0).getKey());
        Assert.assertEquals("N-A02", events.get(0).getLocations().get(0).getDescription());
        Assert.assertNull(events.get(0).getLocations().get(0).getUrl());
        Assert.assertEquals(175, events.get(0).getLocations().get(0).getCapacity());
        Assert.assertNull(events.get(0).getLocations().get(0).getExamCapacity());
        Assert.assertEquals(0, events.get(0).getLocations().get(0).getAvoidConcurrencyLocationIds().size());
        Assert.assertEquals("2017!98EAE9AF089FE3C93536F64A1142FC0B", events.get(0).getLocations().get(1).getId());
        Assert.assertEquals("N-B12", events.get(0).getLocations().get(1).getKey());
        Assert.assertEquals("N-B12", events.get(0).getLocations().get(1).getDescription());
        Assert.assertNull(events.get(0).getLocations().get(1).getUrl());
        Assert.assertEquals(150, events.get(0).getLocations().get(1).getCapacity());
        Assert.assertNull(events.get(0).getLocations().get(1).getExamCapacity());
        Assert.assertEquals(0, events.get(0).getLocations().get(1).getAvoidConcurrencyLocationIds().size());

        Assert.assertEquals(2, events.get(0).getStudentSets().size());
        Assert.assertEquals("GROUP-A1", events.get(0).getStudentSets().get(0).getKey());
        Assert.assertEquals("GROUP-B2", events.get(0).getStudentSets().get(1).getKey());

        Assert.assertEquals(0, events.get(0).getTags().size());

        Assert.assertEquals("This is note 1", events.get(0).getNote1());
        Assert.assertNull(events.get(0).getNote2());
        Assert.assertEquals("This is note 3", events.get(0).getNote3());

        Assert.assertFalse(events.get(0).isHighlighted());
        Assert.assertFalse(events.get(0).isFiltered());


        // Second event
        Assert.assertEquals("2017!066090D30ADAF22C C2B90B6EEA969D38-20170712", events.get(1).getId());
        Assert.assertEquals("Transport & Planning", events.get(1).getDescription());
        Assert.assertEquals("TP", events.get(1).getModuleKey());
        Assert.assertEquals(new Date(1499867100000L), events.get(1).getStartDate());
        Assert.assertEquals(new Date(1499873400000L), events.get(1).getEndDate());

        Assert.assertEquals("2017!0F5E7058EC134851B6EF5356A1864B04", events.get(1).getDepartment().getId());
        Assert.assertEquals("Department of Engineering", events.get(1).getDepartment().getDescription());

        Assert.assertNull(events.get(1).getEventType());

        Assert.assertEquals(1, events.get(1).getStaffMembers().size());
        Assert.assertEquals("WAL", events.get(1).getStaffMembers().get(0).getKey());
        Assert.assertEquals("Ms. Walker (Wal)", events.get(1).getStaffMembers().get(0).getDescription());

        Assert.assertEquals(1, events.get(1).getLocations().size());
        Assert.assertEquals("2017!C1256059D21BFEB108F872F4D6F936F4", events.get(1).getLocations().get(0).getId());
        Assert.assertEquals("N-F31", events.get(1).getLocations().get(0).getKey());
        Assert.assertEquals("N-F31", events.get(1).getLocations().get(0).getDescription());
        Assert.assertEquals("http://test.com", events.get(1).getLocations().get(0).getUrl());
        Assert.assertEquals(150, events.get(1).getLocations().get(0).getCapacity());
        Assert.assertEquals(new Integer(60), events.get(1).getLocations().get(0).getExamCapacity());
        Assert.assertEquals(2, events.get(1).getLocations().get(0).getAvoidConcurrencyLocationIds().size());
        Assert.assertEquals("2017!C1256059D21BFEB108F872F4D6F936FF",
                events.get(1).getLocations().get(0).getAvoidConcurrencyLocationIds().get(0));
        Assert.assertEquals("2017!C1256059D21BFEB108F872F4D6F936FE",
                events.get(1).getLocations().get(0).getAvoidConcurrencyLocationIds().get(1));

        Assert.assertEquals(0, events.get(1).getStudentSets().size());

        Assert.assertEquals(2, events.get(1).getTags().size());
        Assert.assertEquals("TagA", events.get(1).getTags().get(0).getKey());
        Assert.assertEquals("Tag A", events.get(1).getTags().get(0).getDescription());
        Assert.assertEquals("TagB", events.get(1).getTags().get(1).getKey());
        Assert.assertEquals("Tag B", events.get(1).getTags().get(1).getDescription());

        Assert.assertNull(events.get(1).getNote1());
        Assert.assertEquals("This is note 2", events.get(1).getNote2());
        Assert.assertNull(events.get(1).getNote3());

        Assert.assertTrue(events.get(1).isHighlighted());
        Assert.assertTrue(events.get(1).isFiltered());
    }
}

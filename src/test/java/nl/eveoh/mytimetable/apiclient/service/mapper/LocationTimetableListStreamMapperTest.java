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

import nl.eveoh.mytimetable.apiclient.model.LocationTimetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LocationTimetableListStreamMapperTest extends BaseStreamMapperTest<LocationTimetableListMapper> {

    @Override
    protected LocationTimetableListMapper getMapper() {
        return new LocationTimetableListMapper(getObjectMapper());
    }

    @Test
    public void testMapper_3_0() throws Exception {
        List<LocationTimetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/locationtimetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("N-A01 (200)", timetables.get(0).getDescription());
        Assert.assertEquals("N-A01", timetables.get(0).getKey());
        Assert.assertEquals("2017!location!5CD96299730A0DF26206320F08354B7C", timetables.get(0).getId());
        Assert.assertEquals(200, timetables.get(0).getCapacity());
        Assert.assertNull(timetables.get(0).getUrl());

        Assert.assertEquals("N-A02 (175)", timetables.get(1).getDescription());
        Assert.assertEquals("N-A02", timetables.get(1).getKey());
        Assert.assertEquals("2017!location!62F1A7C5D0008267EEDD2BE7454F7A8E", timetables.get(1).getId());
        Assert.assertEquals(175, timetables.get(1).getCapacity());
        Assert.assertNull(timetables.get(1).getUrl());
    }

    @Test
    public void testMapper_3_1() throws Exception {
        List<LocationTimetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_1/locationtimetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("N-A01 (200)", timetables.get(0).getDescription());
        Assert.assertEquals("N-A01", timetables.get(0).getKey());
        Assert.assertEquals("2017!location!5CD96299730A0DF26206320F08354B7C", timetables.get(0).getId());
        Assert.assertEquals(200, timetables.get(0).getCapacity());
        Assert.assertEquals("http://test.com", timetables.get(0).getUrl());

        Assert.assertEquals("N-A02 (175)", timetables.get(1).getDescription());
        Assert.assertEquals("N-A02", timetables.get(1).getKey());
        Assert.assertEquals("2017!location!62F1A7C5D0008267EEDD2BE7454F7A8E", timetables.get(1).getId());
        Assert.assertEquals(175, timetables.get(1).getCapacity());
        Assert.assertNull(timetables.get(1).getUrl());
    }
}

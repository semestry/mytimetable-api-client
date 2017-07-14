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

import nl.eveoh.mytimetable.apiclient.model.Timetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableListStreamMapperTest extends BaseStreamMapperTest<TimetableListMapper> {

    @Override
    protected TimetableListMapper getMapper() {
        return new TimetableListMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<Timetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("Automotive Engineering", timetables.get(0).getDescription());
        Assert.assertEquals("AE", timetables.get(0).getKey());
        Assert.assertEquals("2017!module!49CBD432F01E8C5057B344E02604A76F", timetables.get(0).getId());

        Assert.assertEquals("BioRobotics", timetables.get(1).getDescription());
        Assert.assertEquals("BR", timetables.get(1).getKey());
        Assert.assertEquals("2017!module!49CBD432F01E8C5057B344E02604A770", timetables.get(1).getId());
    }
}

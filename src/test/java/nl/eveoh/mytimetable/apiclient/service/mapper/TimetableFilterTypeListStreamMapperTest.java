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

import nl.eveoh.mytimetable.apiclient.model.TimetableFilterType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableFilterTypeListStreamMapperTest extends BaseStreamMapperTest<TimetableFilterTypeListMapper> {

    @Override
    protected TimetableFilterTypeListMapper getMapper() {
        return new TimetableFilterTypeListMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<TimetableFilterType> filterTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetablefiltertypelist.json"));

        Assert.assertEquals(2, filterTypes.size());

        Assert.assertEquals(2, filterTypes.get(0).getOptions().size());
        Assert.assertEquals("posdepartment", filterTypes.get(0).getId());

        Assert.assertEquals("Department of Engineering", filterTypes.get(0).getOptions().get(0).getDescription());
        Assert.assertEquals("0F5E7058EC134851B6EF5356A1864B04", filterTypes.get(0).getOptions().get(0).getId());
        Assert.assertEquals("DE", filterTypes.get(0).getOptions().get(0).getKey());

        Assert.assertEquals("Department of Law, Economics, and Governance",
                filterTypes.get(0).getOptions().get(1).getDescription());
        Assert.assertEquals("646ADCA666D4A88402CA46C26A738046", filterTypes.get(0).getOptions().get(1).getId());
        Assert.assertEquals("DLEG", filterTypes.get(0).getOptions().get(1).getKey());


        Assert.assertEquals(1, filterTypes.get(1).getOptions().size());
        Assert.assertEquals("pos", filterTypes.get(1).getId());

        Assert.assertEquals("Astrophysics", filterTypes.get(1).getOptions().get(0).getDescription());
        Assert.assertEquals("0F2C927DF37F3A2BEF1F1713768E4EDE", filterTypes.get(1).getOptions().get(0).getId());
        Assert.assertEquals("ASTR", filterTypes.get(1).getOptions().get(0).getKey());
    }
}

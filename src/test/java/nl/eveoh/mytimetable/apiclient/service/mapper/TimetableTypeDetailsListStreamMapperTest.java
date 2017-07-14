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

import nl.eveoh.mytimetable.apiclient.model.TimetableType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableTypeDetailsListStreamMapperTest
        extends BaseStreamMapperTest<TimetableTypeDetailsListStreamMapper> {

    @Override
    protected TimetableTypeDetailsListStreamMapper getMapper() {
        return new TimetableTypeDetailsListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper_3_0() throws Exception {
        List<TimetableType> timetableTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetabletypedetailslist.json"));

        Assert.assertEquals(2, timetableTypes.size());

        Assert.assertFalse(timetableTypes.get(0).isChildOptionSelectable());
        Assert.assertEquals("Course", timetableTypes.get(0).getDescription());
        Assert.assertEquals("module", timetableTypes.get(0).getId());
        Assert.assertFalse(timetableTypes.get(0).isOptionSelectable());
        Assert.assertFalse(timetableTypes.get(0).isParent());
        Assert.assertEquals(1, timetableTypes.get(0).getWeight());
        Assert.assertNull(timetableTypes.get(0).getChildOptionSelectableInInterface());
        Assert.assertNull(timetableTypes.get(0).getOptionSelectableInInterface());

        Assert.assertTrue(timetableTypes.get(1).isChildOptionSelectable());
        Assert.assertEquals("Programme of study", timetableTypes.get(1).getDescription());
        Assert.assertEquals("pos", timetableTypes.get(1).getId());
        Assert.assertTrue(timetableTypes.get(1).isOptionSelectable());
        Assert.assertTrue(timetableTypes.get(1).isParent());
        Assert.assertEquals(2, timetableTypes.get(1).getWeight());
        Assert.assertNull(timetableTypes.get(1).getChildOptionSelectableInInterface());
        Assert.assertNull(timetableTypes.get(1).getOptionSelectableInInterface());
    }

    @Test
    public void testMapper_3_1() throws Exception {
        List<TimetableType> timetableTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_1/timetabletypedetailslist.json"));

        Assert.assertEquals(2, timetableTypes.size());

        Assert.assertFalse(timetableTypes.get(0).isChildOptionSelectable());
        Assert.assertEquals("Course", timetableTypes.get(0).getDescription());
        Assert.assertEquals("module", timetableTypes.get(0).getId());
        Assert.assertFalse(timetableTypes.get(0).isOptionSelectable());
        Assert.assertFalse(timetableTypes.get(0).isParent());
        Assert.assertEquals(1, timetableTypes.get(0).getWeight());
        Assert.assertFalse(timetableTypes.get(0).getChildOptionSelectableInInterface());
        Assert.assertFalse(timetableTypes.get(0).getOptionSelectableInInterface());

        Assert.assertTrue(timetableTypes.get(1).isChildOptionSelectable());
        Assert.assertEquals("Programme of study", timetableTypes.get(1).getDescription());
        Assert.assertEquals("pos", timetableTypes.get(1).getId());
        Assert.assertTrue(timetableTypes.get(1).isOptionSelectable());
        Assert.assertTrue(timetableTypes.get(1).isParent());
        Assert.assertEquals(2, timetableTypes.get(1).getWeight());
        Assert.assertTrue(timetableTypes.get(1).getChildOptionSelectableInInterface());
        Assert.assertTrue(timetableTypes.get(1).getOptionSelectableInInterface());
    }
}

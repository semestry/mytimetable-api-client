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

import nl.eveoh.mytimetable.apiclient.model.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TermListStreamMapperTest extends BaseStreamMapperTest<TermListStreamMapper> {

    @Override
    protected TermListStreamMapper getMapper() {
        return new TermListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<Term> terms = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_2/terms.json"));

        Assert.assertEquals(2, terms.size());

        Assert.assertEquals("2016!E5399C05B9F7AB0060A61B46BAD833E0", terms.get(0).getId());
        Assert.assertNull(terms.get(0).getKey());
        Assert.assertEquals("Two DateRanges", terms.get(0).getDescription());
        Assert.assertEquals(new Date(1473026400000L), terms.get(0).getDateRanges().get(0).getStartDate());
        Assert.assertEquals(new Date(1477868400000L), terms.get(0).getDateRanges().get(0).getEndDate());
        Assert.assertEquals(new Date(1477868400000L), terms.get(0).getDateRanges().get(1).getStartDate());
        Assert.assertEquals(new Date(1482102000000L), terms.get(0).getDateRanges().get(1).getEndDate());

        Assert.assertEquals("2017!E5399C05B9F7AB0060A61B46BAD833DF", terms.get(1).getId());
        Assert.assertEquals("TestKey", terms.get(1).getKey());
        Assert.assertEquals("Semester2", terms.get(1).getDescription());
        Assert.assertEquals(new Date(1517785200000L), terms.get(1).getDateRanges().get(0).getStartDate());
        Assert.assertEquals(new Date(1535925600000L), terms.get(1).getDateRanges().get(0).getEndDate());
    }
}

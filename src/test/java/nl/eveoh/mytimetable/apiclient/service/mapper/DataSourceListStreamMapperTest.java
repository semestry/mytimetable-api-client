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

import nl.eveoh.mytimetable.apiclient.model.DataSource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataSourceListStreamMapperTest extends BaseStreamMapperTest<DataSourceListStreamMapper> {

    @Override
    protected DataSourceListStreamMapper getMapper() {
        return new DataSourceListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<DataSource> dataSources = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/datasourceslist.json"));

        Assert.assertEquals(3, dataSources.size());
        Assert.assertEquals("2017", dataSources.get(0).getId());
        Assert.assertNull(dataSources.get(0).getDescription());
        Assert.assertEquals("2016", dataSources.get(1).getId());
        Assert.assertNull(dataSources.get(1).getDescription());
        Assert.assertEquals("2015", dataSources.get(2).getId());
        Assert.assertNull(dataSources.get(2).getDescription());
    }
}

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

import nl.eveoh.mytimetable.apiclient.model.ErrorMessage;
import org.junit.Assert;
import org.junit.Test;

public class ErrorStreamMapperTest extends BaseStreamMapperTest<ErrorStreamMapper> {

    @Override
    protected ErrorStreamMapper getMapper() {
        return new ErrorStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        ErrorMessage errorMessage = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/errormessage.json"));

        Assert.assertEquals("timetable_not_found", errorMessage.getType());
        Assert.assertEquals("The requested timetable was not found.", errorMessage.getMessage());
    }
}

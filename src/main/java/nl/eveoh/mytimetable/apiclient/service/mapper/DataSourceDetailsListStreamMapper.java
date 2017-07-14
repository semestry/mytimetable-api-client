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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Marco Krikke
 */
public class DataSourceDetailsListStreamMapper extends StreamMapper<List<DataSource>> {

    public DataSourceDetailsListStreamMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<DataSource> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("databases")
                    .forType(mapper.getTypeFactory().constructCollectionType(List.class, DataSource.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

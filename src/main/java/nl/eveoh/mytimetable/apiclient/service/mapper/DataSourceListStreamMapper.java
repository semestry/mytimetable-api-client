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
public class DataSourceListStreamMapper extends StreamMapper<List<DataSource>> {

    public DataSourceListStreamMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<DataSource> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("database")
                    .forType(mapper.getTypeFactory().constructCollectionType(List.class, DataSource.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

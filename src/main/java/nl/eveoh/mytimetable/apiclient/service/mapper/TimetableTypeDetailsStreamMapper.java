package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.TimetableType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Marco Krikke
 */
public class TimetableTypeDetailsStreamMapper extends StreamMapper<List<TimetableType>> {

    public TimetableTypeDetailsStreamMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<TimetableType> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("timetableTypes")
                    .forType(mapper.getTypeFactory().constructCollectionType(List.class, TimetableType.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

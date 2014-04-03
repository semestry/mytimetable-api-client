package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.Timetable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Erik van Paassen
 */
public class TimetableListMapper extends StreamMapper<List<Timetable>> {

    public TimetableListMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<Timetable> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("timetable")
                    .withType(mapper.getTypeFactory().constructCollectionType(List.class, Timetable.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

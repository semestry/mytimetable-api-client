package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.LocationTimetable;
import nl.eveoh.mytimetable.apiclient.model.Timetable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Erik van Paassen
 */
public class LocationTimetableListMapper extends StreamMapper<List<LocationTimetable>> {

    public LocationTimetableListMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<LocationTimetable> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("timetable")
                    .withType(mapper.getTypeFactory().constructCollectionType(List.class, LocationTimetable.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

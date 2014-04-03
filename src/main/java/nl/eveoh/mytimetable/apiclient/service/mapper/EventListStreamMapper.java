package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Erik van Paassen
 */
public class EventListStreamMapper extends StreamMapper<List<Event>> {

    public EventListStreamMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<Event> map(InputStream stream) {
        try {
            return mapper.readValue(stream, mapper.getTypeFactory().constructCollectionType(List.class, Event.class));
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

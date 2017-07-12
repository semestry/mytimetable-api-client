package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

/**
 * @author Erik van Paassen
 */
public abstract class StreamMapper<T> {

    public ObjectMapper mapper;

    public StreamMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public abstract T map(InputStream stream);
}

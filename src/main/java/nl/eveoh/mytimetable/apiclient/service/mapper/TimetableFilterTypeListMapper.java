package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.exception.StreamMappingException;
import nl.eveoh.mytimetable.apiclient.model.TimetableFilterType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Erik van Paassen
 */
public class TimetableFilterTypeListMapper extends StreamMapper<List<TimetableFilterType>> {

    public TimetableFilterTypeListMapper(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public List<TimetableFilterType> map(InputStream stream) {
        try {
            return mapper.reader()
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .withRootName("filterattribute")
                    .forType(mapper.getTypeFactory().constructCollectionType(List.class, TimetableFilterType.class))
                    .readValue(stream);
        } catch (IOException e) {
            throw new StreamMappingException(e);
        }
    }
}

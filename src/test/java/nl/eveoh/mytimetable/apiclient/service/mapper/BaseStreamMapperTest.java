package nl.eveoh.mytimetable.apiclient.service.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import java.io.InputStream;

public abstract class BaseStreamMapperTest<T extends StreamMapper> {

    protected T mapper;


    @Before
    public void setUp() throws Exception {
        this.mapper = getMapper();
    }

    protected abstract T getMapper();

    protected ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    protected InputStream getClassPathResourceAsStream(String path) {
        return getClass().getClassLoader().getResourceAsStream(path);
    }
}

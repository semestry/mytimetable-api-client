package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.DataSource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataSourceListStreamMapperTest extends BaseStreamMapperTest<DataSourceListStreamMapper> {

    @Override
    protected DataSourceListStreamMapper getMapper() {
        return new DataSourceListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<DataSource> dataSources = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/datasourceslist.json"));

        Assert.assertEquals(3, dataSources.size());
        Assert.assertEquals("2017", dataSources.get(0).getId());
        Assert.assertNull(dataSources.get(0).getDescription());
        Assert.assertEquals("2016", dataSources.get(1).getId());
        Assert.assertNull(dataSources.get(1).getDescription());
        Assert.assertEquals("2015", dataSources.get(2).getId());
        Assert.assertNull(dataSources.get(2).getDescription());
    }
}

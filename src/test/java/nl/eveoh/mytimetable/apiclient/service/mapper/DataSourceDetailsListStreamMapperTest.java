package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.DataSource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataSourceDetailsListStreamMapperTest extends BaseStreamMapperTest<DataSourceDetailsListStreamMapper> {

    @Override
    protected DataSourceDetailsListStreamMapper getMapper() {
        return new DataSourceDetailsListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<DataSource> dataSources = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_1/datasourcedetailslist.json"));

        Assert.assertEquals(3, dataSources.size());
        Assert.assertEquals("2017", dataSources.get(0).getKey());
        Assert.assertEquals("2017", dataSources.get(0).getLabel());
        Assert.assertEquals("2016", dataSources.get(1).getKey());
        Assert.assertEquals("2016", dataSources.get(1).getLabel());
        Assert.assertEquals("2015", dataSources.get(2).getKey());
        Assert.assertEquals("2015", dataSources.get(2).getLabel());
    }
}

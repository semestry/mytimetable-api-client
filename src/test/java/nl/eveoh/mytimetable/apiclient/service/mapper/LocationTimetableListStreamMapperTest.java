package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.LocationTimetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LocationTimetableListStreamMapperTest extends BaseStreamMapperTest<LocationTimetableListMapper> {

    @Override
    protected LocationTimetableListMapper getMapper() {
        return new LocationTimetableListMapper(getObjectMapper());
    }

    @Test
    public void testMapper_3_0() throws Exception {
        List<LocationTimetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/locationtimetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("N-A01 (200)", timetables.get(0).getDescription());
        Assert.assertEquals("N-A01", timetables.get(0).getKey());
        Assert.assertEquals("2017!location!5CD96299730A0DF26206320F08354B7C", timetables.get(0).getId());
        Assert.assertEquals(200, timetables.get(0).getCapacity());
        Assert.assertNull(timetables.get(0).getUrl());

        Assert.assertEquals("N-A02 (175)", timetables.get(1).getDescription());
        Assert.assertEquals("N-A02", timetables.get(1).getKey());
        Assert.assertEquals("2017!location!62F1A7C5D0008267EEDD2BE7454F7A8E", timetables.get(1).getId());
        Assert.assertEquals(175, timetables.get(1).getCapacity());
        Assert.assertNull(timetables.get(1).getUrl());
    }

    @Test
    public void testMapper_3_1() throws Exception {
        List<LocationTimetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_1/locationtimetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("N-A01 (200)", timetables.get(0).getDescription());
        Assert.assertEquals("N-A01", timetables.get(0).getKey());
        Assert.assertEquals("2017!location!5CD96299730A0DF26206320F08354B7C", timetables.get(0).getId());
        Assert.assertEquals(200, timetables.get(0).getCapacity());
        Assert.assertEquals("http://test.com", timetables.get(0).getUrl());

        Assert.assertEquals("N-A02 (175)", timetables.get(1).getDescription());
        Assert.assertEquals("N-A02", timetables.get(1).getKey());
        Assert.assertEquals("2017!location!62F1A7C5D0008267EEDD2BE7454F7A8E", timetables.get(1).getId());
        Assert.assertEquals(175, timetables.get(1).getCapacity());
        Assert.assertNull(timetables.get(1).getUrl());
    }
}

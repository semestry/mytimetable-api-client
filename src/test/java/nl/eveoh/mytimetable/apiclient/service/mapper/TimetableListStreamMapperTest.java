package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.Timetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableListStreamMapperTest extends BaseStreamMapperTest<TimetableListMapper> {

    @Override
    protected TimetableListMapper getMapper() {
        return new TimetableListMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<Timetable> timetables = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetablelist.json"));

        Assert.assertEquals(2, timetables.size());

        Assert.assertEquals("Automotive Engineering", timetables.get(0).getDescription());
        Assert.assertEquals("AE", timetables.get(0).getKey());
        Assert.assertEquals("2017!module!49CBD432F01E8C5057B344E02604A76F", timetables.get(0).getId());

        Assert.assertEquals("BioRobotics", timetables.get(1).getDescription());
        Assert.assertEquals("BR", timetables.get(1).getKey());
        Assert.assertEquals("2017!module!49CBD432F01E8C5057B344E02604A770", timetables.get(1).getId());
    }
}

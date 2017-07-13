package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.TimetableFilterType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableFilterTypeListStreamMapperTest extends BaseStreamMapperTest<TimetableFilterTypeListMapper> {

    @Override
    protected TimetableFilterTypeListMapper getMapper() {
        return new TimetableFilterTypeListMapper(getObjectMapper());
    }

    @Test
    public void testMapper() throws Exception {
        List<TimetableFilterType> filterTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetablefiltertypelist.json"));

        Assert.assertEquals(2, filterTypes.size());

        Assert.assertEquals(2, filterTypes.get(0).getOptions().size());
        Assert.assertEquals("posdepartment", filterTypes.get(0).getId());

        Assert.assertEquals("Department of Engineering", filterTypes.get(0).getOptions().get(0).getDescription());
        Assert.assertEquals("0F5E7058EC134851B6EF5356A1864B04", filterTypes.get(0).getOptions().get(0).getId());
        Assert.assertEquals("DE", filterTypes.get(0).getOptions().get(0).getKey());

        Assert.assertEquals("Department of Law, Economics, and Governance",
                filterTypes.get(0).getOptions().get(1).getDescription());
        Assert.assertEquals("646ADCA666D4A88402CA46C26A738046", filterTypes.get(0).getOptions().get(1).getId());
        Assert.assertEquals("DLEG", filterTypes.get(0).getOptions().get(1).getKey());


        Assert.assertEquals(1, filterTypes.get(1).getOptions().size());
        Assert.assertEquals("pos", filterTypes.get(1).getId());

        Assert.assertEquals("Astrophysics", filterTypes.get(1).getOptions().get(0).getDescription());
        Assert.assertEquals("0F2C927DF37F3A2BEF1F1713768E4EDE", filterTypes.get(1).getOptions().get(0).getId());
        Assert.assertEquals("ASTR", filterTypes.get(1).getOptions().get(0).getKey());
    }
}

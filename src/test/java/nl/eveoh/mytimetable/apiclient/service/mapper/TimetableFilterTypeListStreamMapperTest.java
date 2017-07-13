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
        Assert.assertEquals("posdepartment", filterTypes.get(0).getType());

        Assert.assertEquals("Department of Engineering", filterTypes.get(0).getOptions().get(0).getName());
        Assert.assertEquals("0F5E7058EC134851B6EF5356A1864B04", filterTypes.get(0).getOptions().get(0).getValue());
        Assert.assertEquals("DE", filterTypes.get(0).getOptions().get(0).getHostKey());

        Assert.assertEquals("Department of Law, Economics, and Governance",
                filterTypes.get(0).getOptions().get(1).getName());
        Assert.assertEquals("646ADCA666D4A88402CA46C26A738046", filterTypes.get(0).getOptions().get(1).getValue());
        Assert.assertEquals("DLEG", filterTypes.get(0).getOptions().get(1).getHostKey());


        Assert.assertEquals(1, filterTypes.get(1).getOptions().size());
        Assert.assertEquals("pos", filterTypes.get(1).getType());

        Assert.assertEquals("Astrophysics", filterTypes.get(1).getOptions().get(0).getName());
        Assert.assertEquals("0F2C927DF37F3A2BEF1F1713768E4EDE", filterTypes.get(1).getOptions().get(0).getValue());
        Assert.assertEquals("ASTR", filterTypes.get(1).getOptions().get(0).getHostKey());
    }
}

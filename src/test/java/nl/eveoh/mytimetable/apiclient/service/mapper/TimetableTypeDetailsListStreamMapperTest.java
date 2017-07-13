package nl.eveoh.mytimetable.apiclient.service.mapper;

import nl.eveoh.mytimetable.apiclient.model.TimetableType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TimetableTypeDetailsListStreamMapperTest
        extends BaseStreamMapperTest<TimetableTypeDetailsListStreamMapper> {

    @Override
    protected TimetableTypeDetailsListStreamMapper getMapper() {
        return new TimetableTypeDetailsListStreamMapper(getObjectMapper());
    }

    @Test
    public void testMapper_3_0() throws Exception {
        List<TimetableType> timetableTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_0/timetabletypedetailslist.json"));

        Assert.assertEquals(2, timetableTypes.size());

        Assert.assertFalse(timetableTypes.get(0).isChildOptionSelectable());
        Assert.assertEquals("Course", timetableTypes.get(0).getDescription());
        Assert.assertEquals("module", timetableTypes.get(0).getName());
        Assert.assertFalse(timetableTypes.get(0).isOptionSelectable());
        Assert.assertFalse(timetableTypes.get(0).isParent());
        Assert.assertEquals(1, timetableTypes.get(0).getWeight());
        Assert.assertNull(timetableTypes.get(0).getChildOptionSelectableInInterface());
        Assert.assertNull(timetableTypes.get(0).getOptionSelectableInInterface());

        Assert.assertTrue(timetableTypes.get(1).isChildOptionSelectable());
        Assert.assertEquals("Programme of study", timetableTypes.get(1).getDescription());
        Assert.assertEquals("pos", timetableTypes.get(1).getName());
        Assert.assertTrue(timetableTypes.get(1).isOptionSelectable());
        Assert.assertTrue(timetableTypes.get(1).isParent());
        Assert.assertEquals(2, timetableTypes.get(1).getWeight());
        Assert.assertNull(timetableTypes.get(1).getChildOptionSelectableInInterface());
        Assert.assertNull(timetableTypes.get(1).getOptionSelectableInInterface());
    }

    @Test
    public void testMapper_3_1() throws Exception {
        List<TimetableType> timetableTypes = mapper.map(getClassPathResourceAsStream(
                "nl/eveoh/mytimetable/apiclient/service/mapper/MyTimetable_3_1/timetabletypedetailslist.json"));

        Assert.assertEquals(2, timetableTypes.size());

        Assert.assertFalse(timetableTypes.get(0).isChildOptionSelectable());
        Assert.assertEquals("Course", timetableTypes.get(0).getDescription());
        Assert.assertEquals("module", timetableTypes.get(0).getName());
        Assert.assertFalse(timetableTypes.get(0).isOptionSelectable());
        Assert.assertFalse(timetableTypes.get(0).isParent());
        Assert.assertEquals(1, timetableTypes.get(0).getWeight());
        Assert.assertFalse(timetableTypes.get(0).getChildOptionSelectableInInterface());
        Assert.assertFalse(timetableTypes.get(0).getOptionSelectableInInterface());

        Assert.assertTrue(timetableTypes.get(1).isChildOptionSelectable());
        Assert.assertEquals("Programme of study", timetableTypes.get(1).getDescription());
        Assert.assertEquals("pos", timetableTypes.get(1).getName());
        Assert.assertTrue(timetableTypes.get(1).isOptionSelectable());
        Assert.assertTrue(timetableTypes.get(1).isParent());
        Assert.assertEquals(2, timetableTypes.get(1).getWeight());
        Assert.assertTrue(timetableTypes.get(1).getChildOptionSelectableInInterface());
        Assert.assertTrue(timetableTypes.get(1).getOptionSelectableInInterface());
    }
}

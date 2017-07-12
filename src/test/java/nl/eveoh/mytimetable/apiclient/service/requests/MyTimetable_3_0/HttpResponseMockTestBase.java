package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_0;

import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.service.requests.AbstractHttpResponseMockTestBase;

public class HttpResponseMockTestBase extends AbstractHttpResponseMockTestBase {

    @Override
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration(API_KEY, API_ENDPOINT);
        configuration.setMyTimetableVersion(Configuration.MyTimetable_Version.V3_0);

        return configuration;
    }
}

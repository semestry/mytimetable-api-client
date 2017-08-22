/*
 * Copyright 2013 - 2014 Eveoh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.eveoh.mytimetable.apiclient.configuration;

import java.util.Properties;

/**
 * Extended Configuration object, which contains extra properties especially suited for widgets like building blocks.
 *
 * @author Erik van Paassen
 */
public class WidgetConfiguration extends Configuration {

    private static final String CUSTOM_CSS = "customCss";
    private static final String APPLICATION_URI_DESCRIPTION_OVERRIDE = "applicationUriDescriptionOverride";
    private static final String SHOW_STAFF = "showStaff";
    private static final String USE_STUDENT_ID = "useStudentId";

    /**
     * Custom CSS to be included in the building block.
     */
    private String customCss;

    /**
     * Description to be shown for the application link
     */
    private String applicationUriDescriptionOverride;

    /**
     * Show staff column
     */
    private boolean showStaff;

    /**
     * Use student id instead of username
     */
    private boolean useStudentId;


    public WidgetConfiguration() {
    }

    public WidgetConfiguration(Properties properties) {
        super(properties);

        customCss = properties.getProperty(CUSTOM_CSS);
    }

    public String getCustomCss() {
        return customCss;
    }

    public void setCustomCss(String customCss) {
        this.customCss = customCss;
    }

    public String getApplicationUriDescriptionOverride() {
        return applicationUriDescriptionOverride;
    }

    public void setApplicationUriDescriptionOverride(String applicationUriDescriptionOverride) {
        this.applicationUriDescriptionOverride = applicationUriDescriptionOverride;
    }

    public boolean isShowStaff() {
        return showStaff;
    }

    public void setShowStaff(boolean showStaff) {
        this.showStaff = showStaff;
    }

    public boolean isUseStudentId() {
        return useStudentId;
    }

    public void setUseStudentId(boolean useStudentId) {
        this.useStudentId = useStudentId;
    }

    @Override
    public Properties toProperties() {
        Properties ret = super.toProperties();
        ret.setProperty(SHOW_STAFF, Boolean.toString(showStaff));
        ret.setProperty(USE_STUDENT_ID, Boolean.toString(useStudentId));

        if (customCss != null) {
            ret.setProperty(CUSTOM_CSS, customCss);
        }

        if (applicationUriDescriptionOverride != null) {
            ret.setProperty(APPLICATION_URI_DESCRIPTION_OVERRIDE, applicationUriDescriptionOverride);
        }

        return ret;
    }
}

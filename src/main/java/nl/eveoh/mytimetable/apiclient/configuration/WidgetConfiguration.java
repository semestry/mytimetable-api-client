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

    private static final String SHOW_CODE = "showCode";
    private static final String SHOW_DESCRIPTION = "showDescription";
    private static final String SHOW_STAFF = "showStaff";
    private static final String SHOW_NOTE_1 = "showNote1";
    private static final String SHOW_NOTE_2 = "showNote2";
    private static final String SHOW_NOTE_3 = "showNote3";

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
     * Show code column
     */
    private boolean showCode;

    /**
     * Show description column
     */
    private boolean showDescription;

    /**
     * Show staff column
     */
    private boolean showStaff;

    /**
     * Show note 1 column
     */
    private boolean showNote1;

    /**
     * Show note 2 column
     */
    private boolean showNote2;

    /**
     * Show note 3 column
     */
    private boolean showNote3;

    /**
     * Use student id instead of username
     */
    private boolean useStudentId;


    public WidgetConfiguration() {
    }

    public WidgetConfiguration(Properties properties) {
        super(properties);

        showCode = Boolean.parseBoolean(properties.getProperty(SHOW_CODE));
        showDescription = Boolean.parseBoolean(properties.getProperty(SHOW_DESCRIPTION));
        showStaff = Boolean.parseBoolean(properties.getProperty(SHOW_STAFF));
        showNote1 = Boolean.parseBoolean(properties.getProperty(SHOW_NOTE_1));
        showNote2 = Boolean.parseBoolean(properties.getProperty(SHOW_NOTE_2));
        showNote3 = Boolean.parseBoolean(properties.getProperty(SHOW_NOTE_3));
        useStudentId = Boolean.parseBoolean(properties.getProperty(USE_STUDENT_ID));

        customCss = properties.getProperty(CUSTOM_CSS);
        applicationUriDescriptionOverride = properties.getProperty(APPLICATION_URI_DESCRIPTION_OVERRIDE);
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

    public boolean isShowCode() {
        return showCode;
    }

    public void setShowCode(boolean showCode) {
        this.showCode = showCode;
    }

    public boolean isShowDescription() {
        return showDescription;
    }

    public void setShowDescription(boolean showDescription) {
        this.showDescription = showDescription;
    }

    public boolean isShowStaff() {
        return showStaff;
    }

    public void setShowStaff(boolean showStaff) {
        this.showStaff = showStaff;
    }

    public boolean isShowNote1() {
        return showNote1;
    }

    public void setShowNote1(boolean showNote1) {
        this.showNote1 = showNote1;
    }

    public boolean isShowNote2() {
        return showNote2;
    }

    public void setShowNote2(boolean showNote2) {
        this.showNote2 = showNote2;
    }

    public boolean isShowNote3() {
        return showNote3;
    }

    public void setShowNote3(boolean showNote3) {
        this.showNote3 = showNote3;
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
        ret.setProperty(SHOW_CODE, Boolean.toString(showCode));
        ret.setProperty(SHOW_DESCRIPTION, Boolean.toString(showDescription));
        ret.setProperty(SHOW_STAFF, Boolean.toString(showStaff));
        ret.setProperty(SHOW_NOTE_1, Boolean.toString(showNote1));
        ret.setProperty(SHOW_NOTE_2, Boolean.toString(showNote2));
        ret.setProperty(SHOW_NOTE_3, Boolean.toString(showNote3));
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

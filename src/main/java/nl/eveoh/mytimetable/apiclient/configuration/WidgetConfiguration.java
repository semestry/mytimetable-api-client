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

    /**
     * Custom CSS to be included in the building block.
     */
    private String customCss;


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

    @Override
    public Properties toProperties() {
        Properties ret = super.toProperties();

        if (customCss != null)
            ret.setProperty(CUSTOM_CSS, customCss);

        return ret;
    }
}

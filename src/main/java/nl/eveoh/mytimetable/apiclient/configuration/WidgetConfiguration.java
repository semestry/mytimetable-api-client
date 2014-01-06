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

package nl.eveoh.mytimetable.apiclient.configuration;

/**
 * @author Erik van Paassen
 */
public interface ConfigurationChangeListener {

    /**
     * Should be called on a change in the configuration.
     * @param configuration The new configuration.
     */
    public void onConfigurationChanged(Configuration configuration);
}
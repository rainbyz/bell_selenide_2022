package selenide.helpers;

import org.aeonbits.owner.ConfigFactory;
import selenide.helpers.properties.interfaces.BrowserConfigurationProperties;
import selenide.helpers.properties.interfaces.SelenideLoggerTypeProperties;
import selenide.helpers.properties.interfaces.TimeProperties;
import selenide.helpers.properties.interfaces.UrlProperties;

public class Properties {
    public static UrlProperties urlProperties = ConfigFactory.create(UrlProperties.class);
    public static TimeProperties timeProperties = ConfigFactory.create(TimeProperties.class);
    public static SelenideLoggerTypeProperties selenideLoggerTypeProperties
            = ConfigFactory.create(SelenideLoggerTypeProperties.class);
    public static BrowserConfigurationProperties browserConfigurationProperties
            = ConfigFactory.create(BrowserConfigurationProperties.class);
}
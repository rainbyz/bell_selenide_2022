package selenide.helpers.properties.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/properties/selenideLoggerType.properties",
        "system:properties",
        "system:env"})
public interface SelenideLoggerTypeProperties extends Config {
    @Config.Key("type")
    String getType();
}

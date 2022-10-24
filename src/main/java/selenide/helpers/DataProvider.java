package selenide.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of("Электроника", "Смартфоны", new String[]{"Apple"}, "iPhone")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"ASUS"}, "ASUS")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"Black Shark"}, "Black Shark")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"OnePlus"}, "OnePlus")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"Google"}, "Google")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"Seals"}, "Seals")

                // Arguments.of("Электроника1", "Смартфоны", new String[]{"Apple"}, "iPhone")
                // Arguments.of("Электроника", "Смартфоны1", new String[]{"Apple"}, "iPhone")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"Apple1"}, "iPhone")
                // Arguments.of("Электроника", "Смартфоны", new String[]{"Apple", "Google", "OnePlus"}, "iPhone")
        );
    }
}

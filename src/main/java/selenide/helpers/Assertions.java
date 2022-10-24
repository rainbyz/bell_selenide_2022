package selenide.helpers;

import io.qameta.allure.Step;

public class Assertions {
    @Step("Проверка условия")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}

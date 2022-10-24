package selenide.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class YandexSearchHomePage {
    private final String marketButton = "//*[@data-id = 'market']";

    @Step("Переход на Яндекс.Маркет из главной страницы Яндекс.Поиска")
    public MarketHomePage openYandexMarket() {
        $x(marketButton).shouldBe(editable).click();
        switchTo().window(1);
        return page(MarketHomePage.class);
    }
}

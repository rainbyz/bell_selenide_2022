package selenide.pages;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static selenide.helpers.Properties.timeProperties;

public class MarketHomePage {
    protected String catalogButton = "//button[@id = 'catalogPopupButton']";
    protected String catalogPopup = "//*[@id = 'catalogPopup']";
    protected String categories = "//li[@data-zone-name = 'category-link']";
    protected String alsoButton = "//li//span[contains(text(), 'Ещё')]";
    protected String subcategories = "//ul[@data-autotest-id = 'subItems']//a";

    @Step("Открытие окна \"Каталога\"")
    public MarketHomePage openCatalogPopup() {
        $x(catalogButton)
                .shouldBe(visible)
                .shouldBe(editable)
                .click();
        $x(catalogPopup).shouldBe(visible);
        return this;
    }

    @Step("Выбор раздела/категории \"{category}\" и переход на подраздел \"{subcategory}\"")
    public <T extends MarketHomePage> T selectCategoryAndSubcategory(String category, String subcategory, Class<T> typeNextPage) {
        $$x(categories).shouldBe(sizeGreaterThan(1),
                Duration.ofMillis(timeProperties.getDefaultTimeout()));

        $$x(categories).find(text(category))
                .shouldBe(visible)
                .shouldBe(editable)
                .hover();

        $$x(alsoButton)
                .asFixedIterable()
                .stream()
                .forEach(button -> button
                        .shouldBe(visible)
                        .shouldBe(editable)
                        .click());

        $$x(subcategories).find(text(subcategory))
                .shouldBe(visible)
                .shouldBe(editable)
                .click();

        return typeNextPage.cast(page(typeNextPage));
    }
}

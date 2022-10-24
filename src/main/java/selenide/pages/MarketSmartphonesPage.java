package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static selenide.helpers.Assertions.assertTrue;
import static selenide.helpers.Properties.timeProperties;

public class MarketSmartphonesPage extends MarketHomePage {
    protected String filters = "//fieldset";
    protected String expandFilterButton = ".//span[@role = 'button']";
    protected String searchField = ".//input[@type = 'text']";
    protected String resetSearchButton = ".//button[@title='Очистить']";
    protected String checkboxLocator = ".//label[contains(string(), '%s')]";
    protected String loader = "//span[@role='progressbar']";
    protected String showMoreButton = "//button[@data-auto = 'pager-more' and contains(string(), 'Показать ещё')]";
    protected String resultsLoader = "//*[@data-grabber='SearchSerp']//span[@role='progressbar']";
    protected String results = "(//*[@data-zone-name = 'SearchSerp'])[1]//article//h3//a[@title]";
    protected String resultsBottom = "//div[@data-zone-name = 'SearchPager']";

    @Step("Задать параметр «Производитель»: {args}")
    public MarketSmartphonesPage setManufacturers(String[] args) {
        for (String arg : args) {
            setCheckbox("Производитель", arg);
        }
        return this;
    }

    @Step("Проверка, что каждый результат поиска содержит ключевое слово: {keyWord}")
    public void checkResults(String keyWord) {
        loadAllResults();
        assertTrue($$x(results)
                        .asDynamicIterable()
                        .stream()
                        .allMatch(title -> title.toString().contains(keyWord)),
                "В результатах поиска есть не только \"" + keyWord + "\"!");
    }

    private void printDifference(String keyWord) {
        $$x(results)
                .asDynamicIterable()
                .stream()
                // !title.toString().contains(keyWord)
                .filter(title -> !title.getAttribute("title").contains(keyWord))
                .forEach(System.out::println);
    }

    private void loadAllResults() {
        $x(resultsBottom).scrollTo();

        long start = System.currentTimeMillis();
        while ($x(showMoreButton).exists()
                && System.currentTimeMillis() - start < timeProperties.getLoopTimeout()) {
            $x(showMoreButton)
                    .scrollTo()
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .click();
            waitForLoader(loader);
        }
    }

    private void setCheckbox(String filterTitle, String checkboxTitle) {
        SelenideElement filter = $$x(filters).find(text(filterTitle)).shouldBe(visible);
        if (filter.$x(expandFilterButton).exists()) {
            filter.$x(expandFilterButton)
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .click();
            waitForLoaderDisappear(loader);
        }

        if (filter.$x(searchField).exists()) {
            if (filter.$x(resetSearchButton).exists())
                filter.$x(resetSearchButton)
                        .shouldBe(visible)
                        .shouldBe(editable)
                        .click();
            filter.$x(searchField)
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .setValue(checkboxTitle)
                    .pressEnter();
        }


        String fullCheckboxLocator = String.format(checkboxLocator, checkboxTitle);
        SelenideElement temp;
        temp = filter.$x(fullCheckboxLocator);
        temp
                .should(appear, Duration.ofMillis(timeProperties.getDefaultTimeout()))
                .shouldBe(editable);
        if (!temp.isSelected()) {
            temp.click();
            waitForLoader(resultsLoader);
        }
    }

    private void waitForLoader(String locator) {
        $x(locator).should(appear,
                Duration.ofMillis(timeProperties.getDefaultTimeout()));
        $x(locator).should(disappear,
                Duration.ofMillis(timeProperties.getDefaultTimeout()));
    }

    private void waitForLoaderDisappear(String locator) {
        $x(locator).should(disappear,
                Duration.ofMillis(timeProperties.getDefaultTimeout()));
    }
}

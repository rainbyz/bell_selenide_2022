package selenide.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenide.pages.MarketSmartphonesPage;
import selenide.pages.YandexSearchHomePage;

import static com.codeborne.selenide.Selenide.open;
import static selenide.helpers.Properties.urlProperties;

public class TestCases extends BaseTests {
    @DisplayName("Проверка результатов поиска смартфонов в Яндекс.Маркете")
    @ParameterizedTest
    @MethodSource("selenide.helpers.DataProvider#provideParams")
    public void test(String category, String subcategory, String[] manufacturers, String keyWord) {
        open(urlProperties.getYandexSearchHomeUrl(), YandexSearchHomePage.class)
                .openYandexMarket()
                .openCatalogPopup()
                .selectCategoryAndSubcategory(category, subcategory, MarketSmartphonesPage.class)
                .setManufacturers(manufacturers)
                .checkResults(keyWord);
    }
}
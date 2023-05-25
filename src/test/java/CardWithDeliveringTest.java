
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
public class CardWithDeliveringTest {
    String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldPositiveTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Калининград");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(50, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская Мария");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(3, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldHyphenAndDatePicker() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Йошкар-Ола");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(4, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская-Маглена Алла-Виктория");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(4, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldForShortNamesAndSpaces() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Уфа");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(20, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Ким Чен Ын");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(20, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldForLongNamesTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Великий Новгород");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(180, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Архиневолокоточерепопиндриковский Абдурахмангаджи");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(180, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }
    @Test
    public void removingAnEmptyTemplate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(0, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5)).should(exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void wrongCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Комсомольск-на-Амуре");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская Мария");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);

    }

    @Test
    public void DateLessThanThreeDays() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Москва");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(1, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская Мария");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);

    }

    @Test
    public void theNameFieldInLatin() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Москва");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Dikanskaia Maria");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);

    }

    @Test
    public void testOnIncorrectPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Москва");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская Мария");
        $("[data-test-id='phone'] input").setValue("891147716");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);

    }

    @Test
    public void consentCheckboxNotChecked() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Москва");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Диканская Мария");
        $("[data-test-id='phone'] input").setValue("+79114771614");
        $("[data-test-id='agreement']");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);

    }

}

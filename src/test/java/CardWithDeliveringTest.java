import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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


}

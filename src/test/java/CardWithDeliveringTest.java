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
        $("[data-test-id='city'] input ").setValue("Майкоп");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(50, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Сумароков Григорий");
        $("[data-test-id='phone'] input").setValue("+79996604951");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(50, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldHyphenAndDatePicker() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Омск");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(4, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Крылов-Иванов Дмитрий");
        $("[data-test-id='phone'] input").setValue("+79184697452");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(4, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldForShortNamesAndSpaces() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Воронеж");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(20, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Ляо Пинь Дзы");
        $("[data-test-id='phone'] input").setValue("+79524871523");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(20, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }

    @Test
    public void shouldForLongNamesTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input ").setValue("Чита");
        $("[data-test-id='date'] input ").doubleClick().sendKeys(generateDate(180, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Приветкакделов Нормальносамкактий");
        $("[data-test-id='phone'] input").setValue("+79284175684");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(180, "dd.MM.yyyy")),
                Duration.ofSeconds(15)).shouldBe(visible);

    }


}

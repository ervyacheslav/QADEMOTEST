import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open(/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue("Vyacheslav");
        $("#lastName").setValue("Erokhin");
        $("#userEmail").setValue("er.vyacheslav2023@gmail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("9508628655");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-dropdown-container").$(byText("March")).click();
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__day--004").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("2023-09-15 19.04.06.jpg");
        $("#currentAddress").setValue("Currect Address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaipur")).click();
        $("#submit").click();

        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(
                text("Vyacheslav Erokhin"),
                text("er.vyacheslav2023@gmail.com"),
                text("Male"),
                text("9508628655"),
                text("04 March,1992"),
                text("Computer Science"),
                text("Sports"),
                text("2023-09-15 19.04.06.jpg"),
                text("Currect Address"),
                text("Rajasthan Jaipur")
        );
    }
}

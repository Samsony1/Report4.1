package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;


class CardDeliveryTest {

    private String serviceUrl = "http://localhost:9999";
    private String cityInputCss = "[data-test-id='city'] input.input__control";
    private String nameInputCss = "[data-test-id='name'] input.input__control";
    private String dateInputCss = "[data-test-id='date'] input.input__control";
    private String phoneInputCss = "[data-test-id='phone'] input.input__control";
    private String checkBoxCss = "[data-test-id=agreement]";
    private String button = ".button";
    private String datePlusFiveDays = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    private String datePlusSixDays = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }



    @Test
    void shouldTestPositive() {
        RegistrationByCardInfo info = new RegistrationByCardInfo();
        open(serviceUrl);
        $(cityInputCss).setValue(info.getCity());
        $(dateInputCss).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $(dateInputCss).setValue(datePlusFiveDays);
        $(nameInputCss).setValue(info.getName());
        $(phoneInputCss).setValue(info.getPhoneNumber());
        $(checkBoxCss).click();
        $$(button).find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }


    @Test
    void shouldTestPositiveReplanning() {
        RegistrationByCardInfo info = new RegistrationByCardInfo();
        open(serviceUrl);
        $(cityInputCss).setValue(info.getCity());
        $(dateInputCss).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $(dateInputCss).setValue(datePlusFiveDays);
        $(nameInputCss).setValue(info.getName());
        $(phoneInputCss).setValue(info.getPhoneNumber());
        $(checkBoxCss).click();
        $$(button).find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);

        $(dateInputCss).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $(dateInputCss).setValue(datePlusSixDays);
        $$(button).find(exactText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).waitUntil(visible, 5000);
        $$(button).find(exactText("Перепланировать")).click();
        $(withText("Успешно")).waitUntil(visible, 5000);
    }
}










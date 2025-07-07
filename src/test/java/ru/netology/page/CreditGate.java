package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditGate {
    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[placeholder='08']");
    private SelenideElement year = $("[placeholder='22']");
    private SelenideElement owner = $x("//*[contains(text(),'Владелец')]/..//input");
    private SelenideElement cvv = $("[placeholder='999']");
    private SelenideElement continueButton = $x("//*[contains(text(),'Продолжить')]");
    private SelenideElement notification = $(".notification");

    public void fillForm(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvv.setValue(cardInfo.getCvv());
        continueButton.click();
    }

    public void checkSuccessNotification() {
        notification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно"));
    }

    public void checkErrorNotification() {
        notification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Ошибка"));
    }

    public void checkInvalidFormat() {
        $(".input__sub").shouldBe(visible)
                .shouldHave(text("Неверный формат"));
    }

    public void checkExpiredCardError() {
        $(".input__sub").shouldBe(visible)
                .shouldHave(text("Истёк срок действия карты"));
    }
}

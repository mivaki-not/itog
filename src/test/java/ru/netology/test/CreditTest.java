package ru.netology.test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditGate;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        $x("//*[contains(text(),'Купить в кредит')]").click();
    }

    @Test
    @DisplayName("Успешная выдача кредита по карте")
    void shouldSuccessCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getApprovedCard());
        creditPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("Отказ в кредите по отклоненной карте")
    void shouldDeclineCreditWithDeclinedCard() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getCreditDeclinedCard());
        creditPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("Неверный номер карты при оформлении кредита")
    void shouldShowErrorForInvalidCardNumberInCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getCreditInvalidCardNumber());
        creditPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Просроченная карта при оформлении кредита")
    void shouldShowErrorForExpiredCardInCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getCreditExpiredCard());
        creditPage.checkExpiredCardError();
    }

    @Test
    @DisplayName("Неверный владелец карты при оформлении кредита")
    void shouldShowErrorForInvalidOwnerInCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getCreditInvalidOwner());
        creditPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Неверный CVV при оформлении кредита")
    void shouldShowErrorForInvalidCVVInCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(DataHelper.getCreditInvalidCVV());
        creditPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Пустые поля формы кредита")
    void shouldShowErrorsForEmptyFieldsInCredit() {
        var creditPage = new CreditGate();
        creditPage.fillForm(new DataHelper.CardInfo("", "", "", "", ""));

        // Проверяем сообщения об ошибках для всех полей
        final ElementsCollection should = $$(".input__sub").shouldHave(CollectionCondition.size(5))
                .should(CollectionCondition.containExactTextsCaseSensitive(
                        "Неверный формат",
                        "Неверный формат",
                        "Неверный формат",
                        "Поле обязательно для заполнения",
                        "Неверный формат"
                ));
    }
}


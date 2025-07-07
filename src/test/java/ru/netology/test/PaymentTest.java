package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentGate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;

public class PaymentTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Успешная оплата по карте")
    void shouldSuccessPayment() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getApprovedCard());
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Отклоненная карта для оплаты")
    void shouldDeclinePayment() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getDeclinedCard());
        paymentPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Неверный номер карты")
    void shouldInvalidCardNumber() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getInvalidCardNumber());
        paymentPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Просроченная карта")
    void shouldExpiredCard() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getExpiredCard());
        paymentPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Неверный владелец")
    void shouldInvalidOwner() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getInvalidOwner());
        paymentPage.checkInvalidFormat();
    }

    @Test
    @DisplayName("Неверный CVV")
    void shouldInvalidCVV() {
        var paymentPage = new PaymentGate();
        paymentPage.fillForm(DataHelper.getInvalidCVV());
        paymentPage.checkInvalidFormat();
    }
}
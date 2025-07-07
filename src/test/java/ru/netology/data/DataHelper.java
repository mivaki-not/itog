package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class  DataHelper {

    private static Faker faker = new Faker(new Locale("en"));


    public static CardInfo getApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getInvalidCardNumber() {
        return new CardInfo("4444 4444 4444 444", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getExpiredCard() {
        return new CardInfo("4444 4444 4444 4441", "12", "20", "IVAN IVANOV", "123");
    }

    public static CardInfo getInvalidOwner() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "ИВАН ИВАНОВ", "123");
    }

    public static CardInfo getInvalidCVV() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "12");
    }

    public static CardInfo getCreditApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getCreditDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getCreditInvalidCardNumber() {
        return new CardInfo("4444 4444 4444 444", "12", "25", "IVAN IVANOV", "123");
    }

    public static CardInfo getCreditExpiredCard() {
        return new CardInfo("4444 4444 4444 4441", "12", "20", "IVAN IVANOV", "123");
    }

    public static CardInfo getCreditInvalidOwner() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "ИВАН ИВАНОВ", "123");
    }

    public static CardInfo getCreditInvalidCVV() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "12");
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvv;
    }
}

package ru.frigesty.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate(int day, String month, int year){
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year));
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(String.valueOf(day))).click();
    }
}
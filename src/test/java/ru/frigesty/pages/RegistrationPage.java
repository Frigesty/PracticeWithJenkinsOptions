package ru.frigesty.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.frigesty.pages.components.CalendarComponent;
import ru.frigesty.pages.components.ModalComponent;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
                                  lastNameInput = $("#lastName"),
                                  userEmailInput = $("#userEmail"),
                                  genderWrapper = $("#genterWrapper"),
                                  mobileNumberInput = $("#userNumber"),
                                  birthDateInput = $("#dateOfBirthInput"),
                                  subjectsInput = $("#subjectsInput"),
                                  hobbiesWrapper = $("#hobbiesWrapper"),
                                  uploadPicture = $("#uploadPicture"),
                                  addressInput = $("#currentAddress"),
                                  stateInput = $("#state"),
                                  cityInput = $("#city"),
                                  stateCityWrapper = $("#stateCity-wrapper"),
                                  submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    ModalComponent modalComponent = new ModalComponent();

    public void openPage() {
        open("/automation-practice-form");
    }

    public void removeBanners() {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    public void setFirstName(String value) {
        $(firstNameInput).setValue(value);
    }

    public void setLastName(String value) {
        $(lastNameInput).setValue(value);
    }

    public void setEmail(String value) {
        userEmailInput.setValue(value);
    }

    public void chooseGender(String value) {
        genderWrapper.$(byText(value)).click();
    }

    public void setMobileNumber(String value) {
        mobileNumberInput.setValue(value);
    }

    public void setBirthDate(int day, String month, int year) {
        birthDateInput.click();
        calendarComponent.setDate(day, month, year);
    }

    public void setAndChooseSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
    }

    public void chooseHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();
    }

    public void uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
    }

    public void setAddress(String value) {
        addressInput.setValue(value);
    }

    public void chooseStateAndCity(String state, String city) {
        stateInput.scrollTo().click();
        stateCityWrapper.$(byText(state)).scrollTo().click();
        cityInput.scrollTo().click();
        stateCityWrapper.$(byText(city)).scrollTo().click();
    }

    public void clickSubmit() {
        submitButton.scrollTo().click();
    }

    public RegistrationPage verifyRegistrationResultsModalAppears() {
        modalComponent.verifyModalAppears();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        modalComponent.verifyResult(key, value);

        return this;
    }

    public RegistrationPage firstNameFieldInvalidationCheck() {
        firstNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }

    public void lastNameFieldInvalidationCheck() {
        lastNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    public void userEmailFieldInvalidationCheck() {
        userEmailInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    public void genderFieldInvalidationCheck() {
        ElementsCollection radioLabels = $$(".custom-radio label");
        for (SelenideElement label : radioLabels) {
            label.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        }
    }

    public void mobileNumberFieldInvalidationCheck() {
        mobileNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
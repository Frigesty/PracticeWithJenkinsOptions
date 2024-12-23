package ru.frigesty.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.frigesty.pages.RegistrationPage;
import ru.frigesty.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestDataGenerator gData = new TestDataGenerator();

    @Tag("simple")
    @DisplayName("Тест на полное заполнение формы")
    @Test
    void fullFillFormTest() {

        step("Открываем страницу", () -> registrationPage.openPage());
        step("Убираем банеры", () -> registrationPage.removeBanners());
        step("Вводим имя", () -> registrationPage.setFirstName(gData.firstName));
        step("Вводим фамилию", () -> registrationPage.setLastName(gData.lastName));
        step("Вводим email", () -> registrationPage.setEmail(gData.userEmail));
        step("Выбираем пол", () -> registrationPage.chooseGender(gData.userGender));
        step("Вводим номер телефона", () -> registrationPage.setMobileNumber(gData.userNumber));
        step("Указываем дату рождения", () ->
                registrationPage.setBirthDate(gData.dayOfBirth, gData.monthOfBirth, gData.yearOfBirth));
        step("Выбираем предмет", () -> registrationPage.setAndChooseSubject(gData.subject));
        step("Выбираем хобби", () -> registrationPage.chooseHobbies(gData.hobbies));
        step("Загружаем картинку", () -> registrationPage.uploadPicture(gData.pictures));
        step("Вводим адрес", () -> registrationPage.setAddress(gData.currentAddress));
        step("Выбираем штат и город", () ->
                registrationPage.chooseStateAndCity(gData.randomState, gData.randomCity));
        step("Подтверждаем форму", () -> registrationPage.clickSubmit());

        step("Проверяем результаты", () -> {
            registrationPage.verifyRegistrationResultsModalAppears()
                .verifyResult("Student Name", gData.firstName + " " + gData.lastName)
                .verifyResult("Student Email", gData.userEmail)
                .verifyResult("Gender", gData.userGender)
                .verifyResult("Mobile", gData.userNumber)
                .verifyResult("Date of Birth", gData.dayOfBirth + " " + gData.monthOfBirth + "," + gData.yearOfBirth)
                .verifyResult("Subjects", gData.subject)
                .verifyResult("Hobbies", gData.hobbies)
                .verifyResult("Picture", gData.pictures)
                .verifyResult("Address", gData.currentAddress)
                .verifyResult("State and City", gData.randomState + " " + gData.randomCity);
        });
    }

    @Tag("simple")
    @DisplayName("Тест на минимальное заполнение формы")
    @Test
    void minimalFillFormTest() {

        step("Открываем страницу", () -> registrationPage.openPage());
        step("Убираем банеры", () -> registrationPage.removeBanners());
        step("Вводим имя", () -> registrationPage.setFirstName(gData.firstName));
        step("Вводим фамилию", () -> registrationPage.setLastName(gData.lastName));
        step("Выбираем пол", () -> registrationPage.chooseGender(gData.userGender));
        step("Вводим номер телефона", () -> registrationPage.setMobileNumber(gData.userNumber));
        step("Указываем дату рождения", () -> registrationPage.
                setBirthDate(gData.dayOfBirth, gData.monthOfBirth, gData.yearOfBirth));
        step("Подтверждаем форму", () -> registrationPage.clickSubmit());

        step("Проверяем результаты", () -> {
            registrationPage.verifyRegistrationResultsModalAppears()
                    .verifyResult("Student Name", gData.firstName + " " + gData.lastName)
                    .verifyResult("Gender", gData.userGender)
                    .verifyResult("Mobile", gData.userNumber)
                    .verifyResult("Date of Birth",
                            gData.dayOfBirth + " " + gData.monthOfBirth + "," + gData.yearOfBirth);
        });
    }

    @Tag("simple")
    @DisplayName("Негативный тест на заполнение формы")
    @Test
    void negativeFillFormTest() {

        step("Открываем страницу", () -> registrationPage.openPage());
        step("Убираем банеры", () -> registrationPage.removeBanners());
        step("Вводим пустое имя", () -> registrationPage.setFirstName(""));
        step("Вводим пустую фамилию", () -> registrationPage.setLastName(""));
        step("Вводим некорректный email", () -> registrationPage.setEmail("invalid-email"));
        step("Вводим некорректный номер телефона", () -> registrationPage.setMobileNumber("LL00011111"));
        step("Выбираем корректную дату рождения", () ->
                registrationPage.setBirthDate(gData.dayOfBirth, gData.monthOfBirth, gData.yearOfBirth));
        step("Вводим адрес", () -> registrationPage.setAddress(gData.currentAddress));
        step("Отправляем форму", () -> registrationPage.clickSubmit());

        step("Проверяем валидацию имени", () -> registrationPage.firstNameFieldInvalidationCheck());
        step("Проверяем валидацию фамилии", () -> registrationPage.lastNameFieldInvalidationCheck());
        step("Проверяем валидацию email", () -> registrationPage.userEmailFieldInvalidationCheck());
        step("Проверяем валидацию гендера", () -> registrationPage.genderFieldInvalidationCheck());
        step("Проверяем валидацию номера телефона", () -> registrationPage.mobileNumberFieldInvalidationCheck());
    }
}
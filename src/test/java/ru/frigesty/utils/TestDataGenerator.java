package ru.frigesty.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class TestDataGenerator {

    public String firstName;
    public String lastName;
    public String userEmail;
    public String userGender;
    public String userNumber;
    public String subject;
    public String hobbies;
    public String pictures;
    public String currentAddress;
    public String randomState;
    public String randomCity;
    public String monthOfBirth;
    public int dayOfBirth;
    public int yearOfBirth;

    Faker faker = new Faker();

    public TestDataGenerator() {

        java.util.Date birthDateObject = faker.date().birthday(20, 30);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        String formattedDate = sdf.format(birthDateObject);
        String[] dateParts = formattedDate.split("\\s|,");
        int day = Integer.parseInt(dateParts[0]);
        String month = dateParts[1];
        int year = Integer.parseInt(dateParts[2]);

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress();
        userGender = faker.options().option(gender);
        userNumber = "89" + faker.phoneNumber().subscriberNumber(8);
        dayOfBirth = day;
        monthOfBirth = month;
        yearOfBirth = year;
        subject = faker.options().option(subjects);
        hobbies = faker.options().option(hobbyOptions);
        pictures = faker.options().option(morePictures);
        currentAddress = faker.address().streetAddress();
        randomState = faker.options().option(stateCities.keySet().toArray(new String[0]));
        randomCity = getRandomCity(randomState);
    }

    public static String[] subjects = {"Accounting", "Maths", "Arts", "English", "Physics", "Chemistry",
            "Computer Science", "Economics", "Social Studies", "History", "Civics", "Commerce", "Hindi", "Biology"};

    public static String[] hobbyOptions = {"Reading", "Sports", "Music"};

    public static String[] gender = {"Male", "Female", "Other"};

    public static String[] morePictures = {"Duck.png", "marina.png", "mem.png"};

    private static final Map<String, String[]> stateCities = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );

    public static String getRandomCity(String state) {
        String[] cities = stateCities.get(state);
        return new Faker().options().option(cities);
    }
}
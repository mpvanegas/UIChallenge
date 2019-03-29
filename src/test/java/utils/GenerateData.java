package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class GenerateData {

    public static String getRandomEmail() {
        Random rand = new Random();

        int numCharsEmail = rand.nextInt(5) + 5;
        String email = RandomStringUtils.randomAlphanumeric(numCharsEmail);

        int numCharsDomain = rand.nextInt(5) + 5;
        String domain = RandomStringUtils.randomAlphabetic(numCharsDomain);

        return email + "@" + domain + ".com";
    }

    public static String getRandomAlphabetic() {
        Random rand = new Random();
        int numChars = rand.nextInt(5) + 5;
        return RandomStringUtils.randomAlphabetic(numChars);
    }

    public static String getRandomAlphaNumeric() {
        Random rand = new Random();
        int numChars = rand.nextInt(5) + 5;
        return RandomStringUtils.randomAlphanumeric(numChars);
    }
}

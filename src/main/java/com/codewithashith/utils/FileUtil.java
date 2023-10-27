package com.codewithashith.utils;

import java.io.File;

public class FileUtil {

    private static File credentailsFile;

    public static File getCredentialsFile() {
        if (credentailsFile == null)
            credentailsFile = new File("src/main/java/com/codewithashith/assests/credentials.csv");
        return credentailsFile;
    }

    public static String getFilePath() {
        return "src/main/java/com/codewithashith/assests/";
    }
}

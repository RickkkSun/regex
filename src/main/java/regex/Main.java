package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Try the regex utilities.\n");

        System.out.print("Enter a password to validate (min length 8): ");
        String pwd = sc.nextLine();
        System.out.println("Valid password? " + checkForPassword(pwd, 8));

        System.out.print("\nEnter a sentence; I'll extract emails: ");
        String sentence = sc.nextLine();
        List<String> emails = extractEmails(sentence);
        System.out.println("Extracted emails: " + emails);

        System.out.print("\nEnter a string; I'll check for repeated capital letter: ");
        String s = sc.nextLine();
        System.out.println("Contains the same capital letter twice? " + checkForDoubles(s));

        sc.close();
    }

    public static boolean checkForPassword(String password, int minLength) {
        if (password == null || minLength < 0) return false;
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}$";
        return password.matches(regex);
    }

    // Only extract UofT emails (utoronto.ca), with optional subdomains (e.g., mail.utoronto.ca)
    public static List<String> extractEmails(String text) {
        List<String> result = new ArrayList<>();
        if (text == null) return result;

        Pattern p = Pattern.compile(
                "[A-Za-z0-9._%+-]+@(?:(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)*)utoronto\\.ca"
        );

        Matcher m = p.matcher(text);
        while (m.find()) result.add(m.group());
        return result;
    }

    public static boolean checkForDoubles(String str) {
        if (str == null) return false;
        return str.matches(".*([A-Z]).*\\1.*");
    }
}

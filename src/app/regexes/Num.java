package app.regexes;

import java.util.regex.Pattern;

public class Num {
    private static String pattern = "\\-{0,1}[0-9]*[0-9]";

    private static boolean isTerminal(String text)
    {
       return Pattern.matches(pattern,text);
    }

    public static boolean isNumber(String text)
    {
        return isTerminal(text);
    }
}

package app.regexes;

import java.util.regex.Pattern;

class Variable {
    private static String pattern = "[a-zA-Z]*[a-zA-Z]";

    private static boolean isTerminal(String text)
    {
        return Pattern.matches(pattern,text);
    }

    static boolean isVariable(String text)
    {
        return isTerminal(text);
    }
}
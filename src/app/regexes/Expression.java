package app.regexes;

import java.util.ArrayList;
import java.util.Arrays;

public class Expression {

    public static boolean isExpression(String text)
    {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(text.split("\\s+")));
        int t = 0, s = 0;

        if (arr.size() == 1 && isTerminal(arr.get(0))) return true;
        else if (arr.size() == 2 || arr.size() == 0) return false;

        for (String s1 : arr) {
            if (isTerminal(s1)) t++;
            else if (s1.equals("+") || s1.equals("-") || s1.equals("*")) {
                s++;
            }
        }

        if (arr.size() == (t + s) && t == s + 1)
        {
            while (arr.size() > 1)
            {
                for (int i = 0; i < arr.size(); i++)
                {
                    if ((arr.get(i).equals("+") || arr.get(i).equals("-") || arr.get(i).equals("*")) && i > 1)
                    {

                        if (isTerminal(arr.get(i - 2)) && isTerminal(arr.get(i - 1)))
                        {
                            arr.set(i - 2, "0");
                            arr.remove(i);
                            arr.remove(i - 1);
                            break;
                        }
                    }
                    else if ((arr.get(i).equals("+") || arr.get(i).equals("-") || arr.get(i).equals("*")) && i < 2)
                        return false;
                }

                if (arr.size() == 2) break;
                if (arr.size() == 1) return true;
            }
        }
        return false;
    }

    private static boolean isTerminal(String text)
    {
        return (Num.isNumber(text) || Variable.isVariable(text));
    }
}

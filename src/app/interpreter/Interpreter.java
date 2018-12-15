package app.interpreter;

import app.regexes.Expression;
import app.regexes.Num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Interpreter {

    private String[] tmp;
    private CommandType commandType;
    private Map<String, Integer> memory;

    public Interpreter(Map<String, Integer> memory)
    {
        this.memory = memory;
    }

    public void interpret(String commandLine) throws Exception
    {
        Integer result;
        CommandLine command = new CommandLine();

        commandType = command.analyze(commandLine).getCommandType();

        if (commandType == CommandType.WYPISANIE)
        {
            System.out.println(solve(commandLine));
        }
        else if (commandType == CommandType.PRZYPISANIE)
        {
            tmp = commandLine.split("\\s+\\=\\s+");
            result = solve(tmp[1]);
            if(result!=null) memory.put(tmp[0], result);
        }


    }

    private Integer solve(String line) throws Exception
    {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(line.split("\\s+")));
        int tmp;

        if(Expression.isExpression(line))
        {
            for (int i = 0; i < arr.size(); i++)
            {
                switch (arr.get(i))
                {
                    case "+":
                        tmp = getInteger(arr.get(i - 2)) + getInteger(arr.get(i - 1));
                        arr.set(i - 1, Integer.toString(tmp));
                        arr.remove(i);
                        arr.remove(i - 2);
                        i -= 2;
                        break;
                    case "-":
                        tmp = getInteger(arr.get(i - 2)) - getInteger(arr.get(i - 1));
                        arr.set(i - 1, Integer.toString(tmp));
                        arr.remove(i);
                        arr.remove(i - 2);
                        i -= 2;
                        break;
                    case "*":
                        tmp = getInteger(arr.get(i - 2)) * getInteger(arr.get(i - 1));
                        arr.set(i - 1, Integer.toString(tmp));
                        arr.remove(i);
                        arr.remove(i - 2);
                        i -= 2;
                        break;
                }
            }

            if (arr.get(0) == null) arr.remove(0);

            return getInteger(arr.get(0));
        }
        else return null;
    }

    private Integer getInteger(String key) throws Exception
    {
        return Num.isNumber(key) ? Integer.parseInt(key) : getFromMemory(key);
    }

    private Integer getFromMemory(String key) throws Exception
    {
        if (memory.containsKey(key)) return memory.get(key);
        else throw new Exception("???");
    }
}
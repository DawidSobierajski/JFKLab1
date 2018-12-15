package app.interpreter;

import app.regexes.Expression;

class CommandLine {
    private CommandType commandType;

    CommandLine analyze(String line) throws Exception
    {
        String[] temp;
        if (line.contains("="))
        {
            temp = line.split("\\s+\\=\\s+");

            if (temp.length == 2 && isCorrect(temp[0]) && isCorrect(temp[1]))
            {
                this.commandType = CommandType.PRZYPISANIE;
            } else throw new Exception("Error");

        }
        else
        {
            if (isCorrect(line)) this.commandType = CommandType.WYPISANIE;
        }
        return this;
    }

    CommandType getCommandType()
    {
        return commandType;
    }

    private boolean isCorrect(String line)
    {
        return Expression.isExpression(line);
    }
}

package app;

import app.interpreter.Interpreter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args)
    {
        Map<String,Integer> memory = new HashMap<>();
        Interpreter i = new Interpreter(memory);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz zrodlo P/p(plik) lub I/i(standardowe wejscie). Aby wyjsc wpisz exit()");
        System.out.print(">> ");

        String wybor = scanner.next().toUpperCase();

        switch (wybor)
        {
            case "P":
                try
                {
                    System.out.println("Wybrałeś plik.");
   
                 ArrayList<String> instructionLines = new ArrayList<>(Files.readAllLines(new File(args[0]).toPath(), Charset.forName("UTF-8")));
                    instructionLines.set(0, instructionLines.get(0).substring(1));

                    for (String instructionLine : instructionLines)
                    {
                        try
                        {
                            i.interpret(instructionLine);
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            break;

            case "I":
                System.out.println("Wybrales wprowadzanie przez terminal. Wpisz komende.");
                String commandLine;

                while(true)
                {
                    commandLine = scanner.nextLine();

                    if(commandLine.equals("exit()")) break;
                    else
                    {
                        try
                        {
                            i.interpret(commandLine);
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            break;

            case "EXIT()": break;

            default:
                System.out.println("Zle zrodlo.");
            break;
        }
    }
}

package br.com.dv.metro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern INPUT_PATTERN = Pattern.compile("\"([^\"]*)\"|\\b(\\S+)\\b");

    private InputHandler() {
    }

    public static List<String> parseInput() {
        List<String> inputArgs = new ArrayList<>();

        String line = SCANNER.nextLine();
        Matcher matcher = INPUT_PATTERN.matcher(line);

        while (matcher.find()) {
            inputArgs.add(matcher.group(1) != null ? matcher.group(1) : matcher.group(2));
        }

        return inputArgs;
    }

}

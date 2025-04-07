package org.hyperskill.config;



import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.hyperskill.exception.ParamNotDefinedException;

import static sorting.util.Constants.*;

public class ConfigFactory {

    public static ConfigParams getConfig(String[] inputArgs) {
        List<String> arguments = Arrays.asList(inputArgs);

        var dataType = getParam(arguments, DATA_TYPE_FLAG);
        var sortingStrategy = getParam(arguments, SORTING_STRATEGY_FLAG);
        var inputFileName = getParam(arguments, INPUT_FILE);
        var outputFileName = getParam(arguments, OUTPUT_FILE);

        arguments.stream()
                .filter(ConfigFactory::isArgumentInvalid)
                .forEach(a -> System.out.printf("""
                        "%s" is not a valid parameter. It will be skipped.""", a));

        return new ConfigParams(dataType, sortingStrategy, inputFileName, outputFileName);
    }

    private static String getParam(List<String> arguments, String flag) {
        if (!arguments.contains(flag)) {
            return getDefaultParam(flag);
        }
        return arguments.stream()
                .dropWhile(arg -> !flag.equals(arg))
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new ParamNotDefinedException(flag));
    }

    private static String getDefaultParam(String flag) {
        return switch (flag) {
            case DATA_TYPE_FLAG -> WORD;
            case SORTING_STRATEGY_FLAG -> SORTING_NATURAL;
            default -> Strings.EMPTY;
        };
    }

    private static boolean isArgumentInvalid(String argument) {
        return argument.startsWith(FLAG_PREFIX)
                && !DATA_TYPE_FLAG.equals(argument)
                && !SORTING_STRATEGY_FLAG.equals(argument)
                && !INPUT_FILE.equals(argument)
                && !OUTPUT_FILE.equals(argument);
    }
}

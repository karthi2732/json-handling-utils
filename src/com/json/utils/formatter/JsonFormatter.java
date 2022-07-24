package com.json.utils.formatter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonFormatter {

    private static final char OPEN_CURLY_BRACE = '{';

    private static final char CLOSE_CURLY_BRACE = '}';

    private static final char OPEN_SQUARE_BRACE = '[';

    private static final char CLOSE_SQUARE_BRACE = ']';

    private static final char KEY_VALUE_SEPERATOR = ',';

    private static final char BLANK_SPACE = ' ';

    private static final char NEW_LINE_CHAR = '\n';

    private static final char DOUBLE_QUOTE = '"';

    // Represents the number of white spaces need to be printed for formatting
    private static int NO_OF_NEW_LINE_WHITE_SPACES = 0;


    /**
     * For Formatting, only data of Json payload is taken into account.
     * Blank Spaces and New Line Characters within double quotes are considered as Data of Json Paylaod.
     * Other than that, all the white spaces and new lines are ignored.
     */
    public static boolean isJsonData(char ch, int isDataWithinDoubleQuotes) {

        if (isDataWithinDoubleQuotes==0 && (ch==BLANK_SPACE || ch==NEW_LINE_CHAR))
            return false;

        return true;
    }

    public static void printFormattedJson(char ch, int indentation) {

        if (ch==OPEN_CURLY_BRACE || ch==OPEN_SQUARE_BRACE) {
            NO_OF_NEW_LINE_WHITE_SPACES+= indentation;
        }

        if (ch==CLOSE_CURLY_BRACE || ch==CLOSE_SQUARE_BRACE) {
            NO_OF_NEW_LINE_WHITE_SPACES-= indentation;
        }

        if (ch==OPEN_CURLY_BRACE || ch==OPEN_SQUARE_BRACE || ch==KEY_VALUE_SEPERATOR){

            System.out.println(ch);
            printNewLineWhiteSpaces();

        } else if (ch==CLOSE_CURLY_BRACE || ch==CLOSE_SQUARE_BRACE) {

            System.out.println();
            printNewLineWhiteSpaces();
            System.out.print(ch);

        } else if (ch==':'){
            System.out.print(": ");
        } else {
            System.out.print(ch);
        }

    }


    public static void printNewLineWhiteSpaces() {

        for (int i=0; i<NO_OF_NEW_LINE_WHITE_SPACES; i++)
            System.out.print(' ');

    }


    public static void main(String[] args) throws IOException {

        // File name along with path
        String file = "/home/karthi32/KARTHI/Work/Github/json-handling-utils/src/com/json/utils/formatter/sample.json";

        // Represents the indentation space while printing json data
        int indentation = 2;

        // Create File object and check if file exists before execution
        File jsonFile = new File(file);
        if (!jsonFile.exists())
            System.exit(0);

        FileReader jsonFileReader = new FileReader(jsonFile);

        int isDataInDoubleQuotes = 0, ascii;

        // `read()` method of FileReader is used to read Json File
        // `read()` method returns int value pointing to ASCII value of characters
        // -1 is returned by `read()` method when end of stream is reached
        while (true) {

            ascii = jsonFileReader.read();

            // Exit execution of Program when EOF is reached
            if (ascii==-1)
                System.exit(1);

            char ch = (char) ascii;

            if (ch==DOUBLE_QUOTE)
                isDataInDoubleQuotes = ~(isDataInDoubleQuotes);

            if (isJsonData(ch, isDataInDoubleQuotes)) {
                printFormattedJson(ch, indentation);
            }

        }

    }

}
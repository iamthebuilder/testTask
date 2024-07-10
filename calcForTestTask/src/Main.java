import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(calc(enteringTheString()));
    }

    public static String calc(String input) {
        String[] parts;
        try {
            parts = checkForCorrectInputAndParseIntoParts(input);
            return String.valueOf(doArithmeticExpression(parts));
        } catch (MyExceptionForInputString exception) {
            return exception.getMessage();
        }
    }

    public static String enteringTheString() {
        String input;
        Scanner ownScanner = new Scanner(System.in);
        System.out.println(Phrases.ENTER_OF_ARITHMETIC_OPERATION);
        input = ownScanner.nextLine().trim();
        return input;
    }

    public static String[] checkForCorrectInputAndParseIntoParts(String input) throws MyExceptionForInputString {
        String regex = "\\b([1-9]|10)\\b\\s*([+\\-*/])\\s*\\b([1-9]|10)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String[] result = new String[3];
            result[0] = matcher.group(1);
            result[1] = matcher.group(2);
            result[2] = matcher.group(3);
            return result;
        } else {
            throw new MyExceptionForInputString(Phrases.ERROR_OF_INPUT_STRING);
        }
    }

    public static int doArithmeticExpression(String[] parts) {
        int firstNumber = Integer.parseInt(parts[0]);
        int secondNumber = Integer.parseInt(parts[2]);

        if (parts[1].equals("+")) {
            return firstNumber + secondNumber;
        }
        if (parts[1].equals("-")) {
            return firstNumber - secondNumber;
        }
        if (parts[1].equals("*")) {
            return firstNumber * secondNumber;
        }
        if (parts[1].equals("/")) {
            return firstNumber / secondNumber;
        }
        return 1;
    }
}

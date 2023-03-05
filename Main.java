import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение состоящее из 2 чисел (арабских или римских), через пробел: ");
        String input = scan.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = input.split(" ");
        if (operands.length != 3) throw new Exception("Введите два операнда и один оператор(+,-,/,*) через пробел!");
        oper = detectOperation(operands[1]);
        if (oper == null) throw new Exception("Введите один из операторов(+,-,/,*)");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[2])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[2]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[2])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
            isRoman = false;
        }
        else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) { //  почему так? Почему num2 < 1  и ||
            throw new Exception("т.к. по условию задачи нужно использовать от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper){

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}

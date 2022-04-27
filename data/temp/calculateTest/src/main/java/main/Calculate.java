

package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calculate {
    public Calculate() {
    }

    public static Double calculate(List<String> postfix) {
        try {
            Deque<Double> stack = new ArrayDeque<>();
            for (String x : postfix) {
                switch (x) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-": {
                        Double b = stack.pop();
                        Double a = stack.pop();
                        stack.push(a - b);
                        break;
                    }
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/": {
                        Double b = stack.pop();
                        Double a = stack.pop();
                        if (b == 0) {
                            ConsoleUtil.error("Деление на ноль!");
                            return null;
                        } else {
                            stack.push(a / b);
                        }
                        break;
                    }
                    case "u-":
                        stack.push(-stack.pop());
                        break;
                    default:
                        stack.push(Double.valueOf(x));
                        break;
                }
            }
            return stack.pop();
        } catch (Exception ex) {
            ConsoleUtil.error("Возникла ошибка при вычислении! Код ошибки: " + ex.getLocalizedMessage());
        }
        return null;
    }
}

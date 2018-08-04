package com.example.yjh.test;

import java.math.BigDecimal;
import java.util.ArrayDeque;

public class Calculator {
    //计算表达式的值
    public String caculate(String string) throws Exception {
        int x = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                x++;
            } else if (string.charAt(i) == ')') {
                x--;
            }
        }
        if (x < 0) {
            throw new Exception("错误，右括号数量大于左括号");
        }
        ArrayDeque<String> value = new ArrayDeque<>();
        ArrayDeque<String> operation = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();
        String[] expression = formatInput(string);
        for (String s : expression) {
            if (s.equals(" ") || s.length() == 0) {
                continue;
            } else if (s.charAt(0) == '-' && !s.equals("-")) {
                stringBuilder.append(s + " ");
            } else if (isDigital(s.charAt(0))) {
                stringBuilder.append(s + " ");
            } else if (isOperater(s.charAt(0)) || s.charAt(0) == '(' || s.charAt(0) == ')') {
                if (s.equals("(")) {
                    operation.push(s);
                    continue;
                }
                if (s.equals(")")) {
                    while (!operation.getFirst().equals("(")) {
                        if (!operation.getFirst().equals("(")) {
                            stringBuilder.append(operation.poll()+" ");
                        }
                    }
                    if (operation.getFirst().equals("(")) {
                        operation.pop();
                        continue;
                    }
                }
                if (operation.isEmpty()) {
                    operation.push(s);
                    continue;
                } else if (priorityIsBiggerOrTheSame(s, operation.getFirst())) {
                    operation.push(s);
                    continue;
                } else {
                    while (!operation.isEmpty() && !priorityIsBiggerOrTheSame(s, operation.getFirst())) {
                        stringBuilder.append(operation.poll() +" ");
                    }
                    operation.push(s);
                    continue;
                }
            }
        }
        while (!operation.isEmpty()) {
            stringBuilder.append(operation.poll());
        }
        expression = formatInfixExpression(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());

        for (String s : expression) {
            if (s.equals(" ") || s.length() == 0) {
                continue;
            } else if (isDigital(s.charAt(0))) {
                value.push(s);
            } else if (s.charAt(0) == '-' && !s.equals("-")) {
                value.push(s);
            } else if (isOperater(s.charAt(0))) {
                if (value.size() >= 2) {
                    BigDecimal last = new BigDecimal(value.poll());
                    BigDecimal last_p = new BigDecimal(value.poll());
                    if (s.equals("-")) {
                        value.push(last_p.subtract(last).toString());
                    } else if (s.equals("+")) {
                        value.push(last_p.add(last).toString());
                    } else if (s.equals("*")) {
                        value.push(last_p.multiply(last).toString());
                    } else if (s.equals("/")) {
                        value.push(last_p.divide(last, 15, BigDecimal.ROUND_HALF_DOWN).toString());
                    }
                }
            }
        }
        return value.poll();
    }

    public static boolean isOperater(int a) {
        if (a == '+' || a == '-' || a == '*' || a == '/')
            return true;
        return false;
    }

    public static boolean isDigital(int a) {
        if (a >= '0' && a <= '9')
            return true;
        return false;
    }

    // 格式化中缀表达式输入，即在符号前后添加空格，便于后面的分隔操作
    public static String[] formatInput(String s) {
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && c == '-') {
                temp += c;
            } else if (isOperater(c) || c == '(' || c == ')') {
                if ((i - 1) >= 0 && (i + 1) < s.length()
                        && (isOperater(s.charAt(i - 1)) || s.charAt(i - 1) == '(' || s.charAt(i - 1) == ' ')
                        && c == '-' && isDigital(s.charAt(i + 1))) {
                    temp += " " + c;
                } else {
                    temp += " " + c + " ";
                }
            } else
                temp += c;// 数字不用加空格
        }
        return temp.split(" ");// 分割
    }

    public static String[] formatInfixExpression(String s) {
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isOperater(c)) {
                if (c == '-' && i + 1 < s.length() && isDigital(s.charAt(i + 1))) {
                    temp += " " + c;
                } else {
                    temp += " " + c + " ";
                }
            } else {
                temp += c;// 数字不用加空格
            }
        }
        return temp.split(" ");// 分割
    }

    // 优先级判断，a是否大于b
    public static boolean priorityIsBiggerOrTheSame(String a, String b) {
        String s = " +- */";
        return s.indexOf(a) - s.indexOf(b) >= 2;
    }
}
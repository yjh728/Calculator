package com.example.yjh.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    public StringBuilder results = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button0 = (Button) findViewById(R.id.number_0);
        Button button1 = (Button) findViewById(R.id.number_1);
        Button button2 = (Button) findViewById(R.id.number_2);
        Button button3 = (Button) findViewById(R.id.number_3);
        Button button4 = (Button) findViewById(R.id.number_4);
        Button button5 = (Button) findViewById(R.id.number_5);
        Button button6 = (Button) findViewById(R.id.number_6);
        Button button7 = (Button) findViewById(R.id.number_7);
        Button button8 = (Button) findViewById(R.id.number_8);
        Button button9 = (Button) findViewById(R.id.number_9);

        Button button_add = (Button) findViewById(R.id.symbol_add);
        Button button_sub = (Button) findViewById(R.id.symbol_sub);
        Button button_mult = (Button) findViewById(R.id.symbol_mult);
        Button button_div = (Button) findViewById(R.id.symbol_div);
        Button button_del = (Button) findViewById(R.id.symbol_del);
        Button button_dian = (Button) findViewById(R.id.symbol_dian);
        Button button_equal = (Button) findViewById(R.id.symbol_equal);
        Button button_left = (Button) findViewById(R.id.symbol_left);
        Button button_right = (Button) findViewById(R.id.symbol_right);
        Button button_ac = (Button) findViewById(R.id.symbol_ac);
        this.textView = (TextView) findViewById(R.id.text_view);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_mult.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_dian.setOnClickListener(this);
        button_left.setOnClickListener(this);
        button_ac.setOnClickListener(this);
        button_right.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int last = 0;
        int last_p = 0;
        if (results.length() != 0) {
            last = results.codePointAt(results.length() - 1);
            if (results.length() > 1)
                last_p = results.codePointBefore(results.length() - 1);
        }
        if (results.indexOf("=") > 0) {
            results.replace(0, results.length(), "");
        }

        switch (view.getId()) {
            case R.id.number_0:
                if (last == ')') {
                    results.append("*0");
                } else {
                    results.append('0');
                }
                textView.setText(results);
                break;
            case R.id.number_1:
                if (last == ')') {
                    results.append("*1");
                } else {
                    results.append('1');
                }
                textView.setText(results);
                break;
            case R.id.number_2:
                if (last == ')') {
                    results.append("*2");
                } else {
                    results.append('2');
                }
                textView.setText(results);
                break;
            case R.id.number_3:
                if (last == ')') {
                    results.append("*3");
                } else {
                    results.append('3');
                }
                textView.setText(results);
                break;
            case R.id.number_4:
                if (last == ')') {
                    results.append("*4");
                } else {
                    results.append('4');
                }
                textView.setText(results);
                break;
            case R.id.number_5:
                if (last == ')') {
                    results.append("*5");
                } else {
                    results.append('5');
                }
                textView.setText(results);
                break;
            case R.id.number_6:
                if (last == ')') {
                    results.append("*6");
                } else {
                    results.append('6');
                }
                textView.setText(results);
                break;
            case R.id.number_7:
                if (last == ')') {
                    results.append("*7");
                } else {
                    results.append('7');
                }
                textView.setText(results);
                break;
            case R.id.number_8:
                if (last == ')') {
                    results.append("*8");
                } else {
                    results.append('8');
                }
                textView.setText(results);
                break;
            case R.id.number_9:
                if (last == ')') {
                    results.append(" * 9");
                } else {
                    results.append('9');
                }
                textView.setText(results);
                break;
            case R.id.symbol_add:
                if (isDigital(last) || last == ')' || last == '.') {
                    results.append('+');
                } else if (isOperater(last) && isOperater(last_p)) {
                    results.replace(results.length() - 2, results.length(), "+");
                } else if (isOperater(last)) {
                    results.setCharAt(results.length() - 1, '+');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_sub:
                if (isDigital(last) || last == ')' || last == '(' || last == '.') {
                    results.append('-');
                } else if (last == '-' || last == '+') {
                    results.setCharAt(results.length() - 1, '-');
                } else if (last == '*' || last == '/') {
                    results.append('-');
                } else if (results.length() == 0) {
                    results.append('-');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_mult:
                if (isDigital(last) || last == ')' || last == '.') {
                    results.append('*');
                } else if (isOperater(last)) {
                    results.setCharAt(results.length() - 1, '*');
                } else if (isOperater(last) && isOperater(last_p)) {
                    results.replace(results.length() - 2, results.length(), "*");
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_div:
                if (isDigital(last) || last == ')' || last == '.') {
                    results.append('/');
                } else if (isOperater(last) && isOperater(last_p)) {
                    results.replace(results.length() - 2, results.length(), "/");
                } else if (isOperater(last)) {
                    results.setCharAt(results.length() - 1, '/');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_del:
                if (results.indexOf("=") > 0) {
                    results.replace(0, results.length(), "");
                } else if (results.length() > 0) {
                    results.deleteCharAt(results.length() - 1);
                }
                textView.setText(results);
                break;
            case R.id.symbol_ac:
                results.delete(0, results.length());
                textView.setText(results);
                break;
            case R.id.symbol_dian:
                int index = results.lastIndexOf(".");
                boolean flag = false;
                for (int i = index; i > 0 && i < results.length(); i++) {
                    int x = results.charAt(i);
                    if (isOperater(x) || x == '(' || x==')') {
                        flag = true;
                        break;
                    }
                }
                if ((isDigital(last) && flag) || (isDigital(last) && index<0)) {
                    results.append('.');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_left:
                if (last == '-' && isOperater(last_p)) {
                    results.replace(results.length() - 1, results.length(), "(-");
                } else if (isDigital(last)) {
                    results.append("*(");
                } else if (isOperater(last)) {
                    results.append('(');
                } else if (last == ')') {
                    results.append("*(");
                } else if (last == '(') {
                    results.append('(');
                } else if (results.length() == 0) {
                    results.append('(');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_right:
                if (isDigital(last) || last == ')') {
                    results.append(')');
                } else {
                    Toast.makeText(MainActivity.this, "错误",
                            Toast.LENGTH_SHORT).show();
                }
                textView.setText(results);
                break;
            case R.id.symbol_equal:
                try {
                    Calculator calculator = new Calculator();
                    results.replace(0, results.length(), calculator.caculate(results.toString()));
                    textView.setText(results);
                } catch (Exception e) {
                    textView.setText(e.getMessage());
                    results.replace(0, results.length(), "");
                }
            default:
                break;

        }
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
}

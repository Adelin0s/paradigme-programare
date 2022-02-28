import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.*;

public class Calculator extends JFrame
{
    JButton digits[] = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton operators[] = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C "),
            new JButton(" ( "),
            new JButton(" ) "),
    };

    String oper_values[] = {"+", "-", "*", "/", "=", "", "(", ")"};

    String value;
    char operator;

    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
        calculator.setSize(300, 200);
        calculator.setTitle(" Java-Calc, PP Lab1 ");
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static int Priority(char  c)
    {
        if (c == '+' || c == '-')
            return 1;
        if (c == '*' || c == '/')
            return 2;
        if (c == '^')
            return 3;
        return -1;
    }

    static String transform(String expr)
    {
        String result = "";

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<expr.length(); ++i)
        {
            char c = expr.charAt(i);

            if (Character.isLetterOrDigit(c))
                result += c;
            else if (c == '(')
            {
                stack.push(c);
            }
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                {
                    result += stack.pop();
                }

                stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Priority(c) <= Priority(stack.peek()))
                {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty())
        {
            if(stack.peek() == '(')
            {
                return "Invalid expression";
            }
            result += stack.pop();
        }
        return result;
    }

    static int processExpression(String expr)
    {
        Stack<Integer> stack=new Stack<>();

        for(int i=0;i<expr.length();i++)
        {
            char c=expr.charAt(i);

            if(Character.isDigit(c))
                stack.push(c - '0');
            else
            {
                int x1 = stack.pop();
                int val2 = stack.pop();

                switch(c)
                {
                    case '+':
                        stack.push(val2 + x1);
                        break;
                    case '-':
                        stack.push(val2 - x1);
                        break;
                    case '/':
                        stack.push(val2 / x1);
                        break;
                    case '*':
                        stack.push(val2 * x1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public Calculator()
    {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i = 0; i < 10; i++)
            buttonpanel.add(digits[i]);

        for (int i = 0; i < 8; i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i=0;i<10;i++)
        {
            int finalI = i;
            digits[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    area.append(Integer.toString(finalI));
                }
            });
        }

        for (int i=0;i<8;i++)
        {
            int finalI = i;
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    if (finalI == 5)
                        area.setText("");
                    else
                    if (finalI == 4)
                    {
                        area.setText("Result: " + processExpression(transform(area.getText())));
                    }
                    else
                    {
                        area.append(oper_values[finalI]);
                        operator = oper_values[finalI].charAt(0);
                    }
                }
            });
        }
    }
}
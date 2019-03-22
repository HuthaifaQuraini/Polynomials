package Code_97;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class Polynomial {

    public static String printEquation(Polynomial p) {
        Node temp = p.list.head;
        String s = "";
        if (p.list.isEmpty()) {
            return "0";
        }
        NumberFormat formatter = new DecimalFormat("#0.0000");
        while(temp!=null){
            temp.coefficient=Double.parseDouble(formatter.format(temp.coefficient));
            temp=temp.next;
        }
        temp=p.list.head;
        while (temp != null) {
            if (temp.expression == 0) {
                if (temp.coefficient > 0) {
                    s += "+" + temp.coefficient;
                } else if (temp.coefficient == 0) {
                    s += "+" + temp.coefficient;
                } else {
                    s += temp.coefficient;
                }
                
            } 
            else if (temp.expression == 1) {
                if (temp.coefficient > 0) {
                    s += "+" + temp.coefficient + "X";
                } else if (temp.coefficient == 0) {
                    s += "+" + temp.coefficient;
                } else {
                    s += temp.coefficient + "X";
                }
            } else if (temp.coefficient > 0) {
                s += "+" + temp.coefficient + "X^" + temp.expression + "";
            } else if (temp.coefficient == 0) {
                s += "+" + temp.coefficient;
            }
            else {
                s += temp.coefficient + "X^" + temp.expression + "";
            }

            temp = temp.next;
        }
        if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        return s;

    }

    private static String formatEquation(String equation) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == ' ') {
                continue;
            } else if (equation.charAt(i) == 'x' || equation.charAt(i) == 'X') {
                result.append("x");
            } else {
                result.append(equation.charAt(i) + "");
            }
        }
        return result.toString();
    }

    public static double SolveEquation(double num, Polynomial p) {
        Node temp = p.list.head;
        double sum = 0;
        while (temp != null) {
            if (temp.expression != 0) {
                sum += Math.pow(num, temp.expression) * temp.coefficient;
            } else {
                sum += temp.coefficient;
            }
            temp = temp.next;
        }
        return sum;
    }
    public LinkedList list;

    public Polynomial() {
        list = new LinkedList();
    }

    public static Polynomial PolynomialAdd(Polynomial p1, Polynomial p2) {
        Polynomial p = new Polynomial();
        Node current1;
        Node current2 = p2.list.head;

        current1 = p1.list.head;
        double co;
        while (current1 != null) {
            co = p2.list.contain(current1.expression);

            if (co != 0) {
                if ((current1.coefficient + co) == 0) {
                    current1 = current1.next;
                    continue;
                } else {
                    p.list.addLast(current1.coefficient + co, current1.expression);
                }
            } else {
                p.list.addLast(current1.coefficient, current1.expression);
            }
            current1 = current1.next;
        }
        current1 = p1.list.head;
        while (current2 != null) {
            co = p1.list.contain(current2.expression);
            if (co == 0) {
                p.list.addLast(current2.coefficient, current2.expression);
            }
            current2 = current2.next;
        }
        return p;
    }

    public static Polynomial PolynomialSubtrc(Polynomial p1, Polynomial p2) {
        Polynomial p = new Polynomial();
        Node current1;
        Node current2 = p2.list.head;

        current1 = p1.list.head;
        double co;
        while (current1 != null) {
            co = p2.list.contain(current1.expression);
            if (co != 0) {
                if ((current1.coefficient - co) == 0) {
                    current1 = current1.next;
                    continue;
                } else {
                    p.list.addLast(current1.coefficient - co, current1.expression);
                }
            } else {
                p.list.addLast(current1.coefficient, current1.expression);
            }
            current1 = current1.next;
        }
        current1 = p1.list.head;
        while (current2 != null) {
            co = p1.list.contain(current2.expression);
            if (co == 0) {
                p.list.addLast(-current2.coefficient, current2.expression);
            }
            current2 = current2.next;
        }
        return p;
    }

    public static Polynomial PolynomialMultyplay(Polynomial p1, Polynomial p2) {
        double arr1[] = new double[p1.list.last.expression + 1];
        Node temp1 = p1.list.head;
        while (temp1 != null) {
            arr1[temp1.expression] = temp1.coefficient;
            temp1 = temp1.next;
        }
        double arr2[] = new double[p2.list.last.expression + 1];
        Node temp2 = p2.list.head;
        while (temp2 != null) {
            arr2[temp2.expression] = temp2.coefficient;
            temp2 = temp2.next;
        }

        double Res[] = new double[arr1.length + arr2.length - 1];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                Res[i + j] += arr1[i] * arr2[j];
            }
        }
        Polynomial p = new Polynomial();
        for (int i = 0; i < Res.length; i++) {
            if (Res[i] == 0) {
                continue;
            }
            p.list.addbySort(Res[i], i);
        }
        return p;
    }

    public static void preparePoly(String equation, Polynomial p) {
        equation = formatEquation(equation);
        String tok, op = "";
        String toCheck;
        double co;int exp;
        StringTokenizer st = new StringTokenizer(equation, "+-", true);
        while (st.hasMoreTokens()) {
            tok = op + st.nextToken();
            if (tok.equals("+") || tok.equals("-")) {
                op = tok;
                continue;
            }
            op = "";
            if (tok.contains("^")) {
                exp = Integer.parseInt(tok.substring(tok.indexOf("^") + 1));
                if (tok.indexOf("x") == 0) {
                    co = 1;
                } else if (tok.indexOf("x") == 1 && (tok.charAt(0) == '+' || tok.charAt(0) == '-')) {
                    toCheck = tok.charAt(0) + "1";
                    co = Double.parseDouble(toCheck);
                } else {
                    co = Double.parseDouble(tok.substring(0, tok.indexOf("x")));
                }
                p.list.addbySort(co, exp);
            } else if (tok.contains("x")) {
                exp = 1;
                if (tok.length() == 1) {
                    co = 1;
                } else if (tok.indexOf("x") == 1 && (tok.charAt(0) == '+' || tok.charAt(0) == '-')) {
                    toCheck = tok.charAt(0) + "1";
                    co = Double.parseDouble(toCheck);
                } else {
                    co = Double.parseDouble(tok.substring(0, tok.indexOf("x")));
                }
                p.list.addbySort(co, exp);

            } else {
                p.list.addbySort(Double.parseDouble(tok), 0);
            }

        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_97;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author hodaifa
 */
public class prefix {
   static Stack<Character> s = new <Character>Stack();
    static String result = "";

    public  static String Prepare(String st){
        s=new Stack<>();
        result="";
        StringBuilder str=new StringBuilder();
        for(int i=st.length()-1;i>=0;i--)
            if(st.charAt(i)==')')
                str.append('(');
            else if(st.charAt(i)=='(')
                str.append(')');
        else
                str.append(st.charAt(i));
        st=str.toString();
       
        for (int i = 0; i < st.length();i++) {
            switch (st.charAt(i)) {
                case '+':
                case '-':
                    Prefix(st.charAt(i), 1);
                    break;
                case '*':
                case '/':
                    Prefix(st.charAt(i), 2);
                    break;
                case '(':
                    
                    s.push('(');
                    break;
                case ')':
                   
                        while (!s.empty()) {
                            if (s.peek() == '(') {
                                s.pop();
                                break;
                            }
                            result += s.pop();
                        }

                    break;
                case ' ':;break;
                default:
                    result += st.charAt(i) + "";
            }
        }
        while (!s.empty()) {
            result += s.pop() + "";
        }
        str=new StringBuilder(result);
        s=new Stack<>();
       return str.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String st = in.nextLine();
        Prepare(st);
        
    }
    public static void Prefix(char c, int rank) {
        int topRank;
        if (s.empty()) {
            s.add(c);
        } else if (s.peek() == '(') {
            s.push(c);
            
        } else {
            if (s.peek() == '-' || s.peek() == '+') {
                topRank = 1;
            } else {
                topRank = 2;
            }
            if (topRank <= rank) {
                s.push(c);
            } else {
                result += s.pop();
                s.push(c);
            }
        }

    }
}

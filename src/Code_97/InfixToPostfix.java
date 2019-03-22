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
public class InfixToPostfix {
    static Stack<Character> s = new <Character>Stack();
    static String result = "";
    
    public static String Prepare(String st){
        s=new Stack<>();
        result="";
          for (int i = 0; i < st.length();i++) {
            switch (st.charAt(i)) {
                case '+':
                case '-':
                    postfix(st.charAt(i), 1);
                    break;
                case '*':
                case '/':
                    postfix(st.charAt(i), 2);
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
                default:
                    result += st.charAt(i) + "";
            }
        }
        while (!s.empty()) {
            result += s.pop() + "";
        }
        
        return result;
    }
    
    public static void postfix(char c, int rank) {
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
            if (topRank < rank) {
                s.push(c);
            } else {
                result += s.pop();
                s.push(c);
            }
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_97;

/**
 *
 * @author Hodaifa A Quraini
 */
class Node{
    double coefficient;
    int expression;
    Node next;

    public Node(double coefficient, int expression) {
        this.coefficient = coefficient;
        this.expression = expression;
    }
    
}
public class LinkedList {

    public Node head;
    public Node last;
    int size=0;
   

    public LinkedList() {

    }

    public void addFirst(double co,int exp) {
        Node temp = new Node(co,exp);
        
        if (head==null) {
            head = last = temp;
            size++;
        } else {
            temp.next = head;
            head = temp;
            size++;
        }

    }


    public void addLast(double co,int exp) {
        if (isEmpty()) {
            addFirst(co,exp);
            
            
        } else {
            Node temp = new Node(co,exp);
           last.next=temp;
           last=temp;
           size++;

        }

    }


    
    public boolean isEmpty() {
        boolean flag = false;
        if (head == null) {
            flag = true;
        }
        return flag;
    }

    public void addbySort(double co,int exp) {
        if(co==0)
            return;
        Node temp = head;
        Node priv = new Node(co,exp);
        if (temp==null) {
            addFirst(co,exp);
        } else if (size == 1 && temp.expression <exp) {
            addLast(co,exp);
        } else if (temp.expression > exp) {
            addFirst(co,exp);
        } else if (last.expression < exp) {
            addLast(co,exp);
        } else if (temp == null) {
            addLast(co,exp);
        } else {
            while (temp != null && temp.next.expression < exp) {
                temp = temp.next;

            }
            priv.next = temp.next;
            temp.next = priv;

            size++;
        }
    }
    public double contain(int key){
        double co=0;
        
        Node current=head;
        while(current!=null)
        {
            if(current.expression==key){
                co=current.coefficient;
                return co;
            }
            current=current.next;
        }
        return co;
    }
    public void print(){
        Node temp=head;
        String s="";
        while(temp!=null)
        {
            s+=temp.coefficient+" "+temp.expression+"---------->";
            temp=temp.next;
        }
        s+="null";
        System.out.println(s);
    }
   public void sort() {
        if (size > 1) {
            boolean wasChanged;

            do {
                Node current = head;
                Node previous = null;
                Node next = head.next;
                wasChanged = false;

                while ( next != null ) {
                    if (current.expression > next.expression) {
                        wasChanged = true;

                        if ( previous != null ) {
                            Node sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            Node sig = next.next;

                            head = next;
                            next.next = current;
                            current.next = sig;
                        }

                        previous = next;
                        next = current.next;
                    } else { 
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                } 
            } while( wasChanged );
        }
    }
  

    
}

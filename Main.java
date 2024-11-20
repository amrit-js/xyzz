class Node {
    int coefficient;
    int exponent;
    Node next;

    public Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

class Polynomial {
    Node head;

    public Polynomial() {
        this.head = null;
    }


    public void addTerm(int coefficient, int exponent) {
        if (coefficient == 0) return;
        Node newNode = new Node(coefficient, exponent);
        if (head == null || head.exponent < exponent) {
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            Node previous = null;
            while (curr != null && curr.exponent >= exponent) {
                if (curr.exponent == exponent) {
                    curr.coefficient += coefficient;
                    return;
                }
                previous = curr;
                curr = curr.next;
            }
            newNode.next = curr;
            previous.next = newNode;
        }
    }


    public void printPolynomial() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.coefficient + "x^" + curr.exponent);
            if (curr.next != null) {
                System.out.print(" + ");
            }
            curr = curr.next;
        }
        System.out.println();
    }


    public static Polynomial multiplyPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        Node curr1 = p1.head;

        while (curr1 != null) {
            Node curr2 = p2.head;
            while (curr2 != null) {
                int newCoefficient = curr1.coefficient * curr2.coefficient;
                int newExponent = curr1.exponent + curr2.exponent;
                result.addTerm(newCoefficient, newExponent);
                curr2 = curr2.next;
            }
            curr1 = curr1.next;
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addTerm(3, 2);
        p1.addTerm(5, 1);
        p1.addTerm(6, 0);

        Polynomial p2 = new Polynomial();
        p2.addTerm(4, 2);
        p2.addTerm(2, 1);
        p2.addTerm(1, 0);

        System.out.println("Polynomial 1:");
        p1.printPolynomial();

        System.out.println("Polynomial 2:");
        p2.printPolynomial();

        Polynomial result = Polynomial.multiplyPolynomials(p1, p2);
        System.out.println("Result of Multiplication:");
        result.printPolynomial();
    }
}

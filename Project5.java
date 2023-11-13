package cmsc256.Projects.Project5;

import bridges.base.BinTreeElement;
import java.util.Stack;

public class Project5 {

    // Method to build a parse tree from a given expression
    public static bridges.base.BinTreeElement<String> buildParseTree(String expression) {

        // Split the expression into tokens
        String[] list = expression.split(" ");

        // Initialize the root of the parse tree
        BinTreeElement<String> root = new BinTreeElement<>(" ");

        // Stack to keep track of tree elements
        Stack<BinTreeElement> obj1 = new Stack<BinTreeElement>();

        // Current element being processed
        BinTreeElement<String> current = root;

        // Loop through each token in the expression
        for(String item: list){

            if (item.equals("(")){

                // If token is '(', create a new left child
                current.setLeft(new BinTreeElement<>(" "));

                // Push the current element onto the stack
                obj1.push(current);

                // Move to the left child
                current=current.getLeft();

            } else if (item.equals(")")){

                // If token is ')', pop an element from the stack
                if(!obj1.empty()) {
                    current = obj1.pop();
                }

            } else if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")){

                // If token is an operator, set the current element's value, create a new right child
                current.setValue(item);
                current.setRight(new BinTreeElement<>(" "));

                // Push the current element onto the stack and move to the right child
                obj1.push(current);
                current=current.getRight();

            } else if (Character.isDigit(item.charAt(0))){

                // If token is a digit, set the current element's value and pop an element from the stack
                current.setValue(item);
                current=obj1.pop();

            } else {

                // If token is invalid, throw an exception
                throw new IllegalArgumentException();
            }
        }
        // Return the root of the parse tree
        return root;
    }

    // Method to recursively evaluate the mathematical expression represented by the parse tree
    public static double evaluate(bridges.base.BinTreeElement<String> tree){

        // Check if the current node is null
        if(tree==null){
            return Double.NaN; // Return NaN for null nodes
        }

        double output;

        // If the current node is a leaf node (no left or right children), convert the value to double
        if (tree.getLeft() == null && tree.getRight() == null) {

            output = Double.parseDouble(tree.getValue());

        } else if (tree.getValue().equals("+")){

            // If the current node represents addition, recursively evaluate left and right children and sum them
            output = evaluate(tree.getLeft()) + evaluate(tree.getRight());

        } else if (tree.getValue().equals("*")){

            // If the current node represents multiplication, recursively evaluate left and right children and multiply them
            output = evaluate(tree.getLeft()) * evaluate(tree.getRight());

        } else if (tree.getValue().equals("-")){

            // If the current node represents subtraction, recursively evaluate left and right children and subtract them
            output = evaluate(tree.getLeft()) - evaluate(tree.getRight());

        } else if (tree.getValue().equals("/")){

            // If the current node represents division, check if the denominator is zero, and then divide left and right children
            if (evaluate(tree.getRight())==0){
                throw new ArithmeticException();
            }
            output = evaluate(tree.getLeft()) / evaluate(tree.getRight());

        } else {

            // If the current node represents modulo, recursively evaluate left and right children and calculate the remainder
            output = evaluate(tree.getLeft()) % evaluate(tree.getRight());
        }

        return output;
    }

    // Method to recursively get the original infix equation from the parse tree
    public static String getEquation(bridges.base.BinTreeElement<String> tree){
        String output = "";

        // If the left child is not null, append left subtree with surrounding parentheses
        if(tree.getLeft() != null){
            output += "( "+ getEquation(tree.getLeft())+ " ";
        }

        // Append the current node's value
        output += tree.getValue()+" ";

        // If the right child is not null, append right subtree with surrounding parentheses
        if(tree.getRight() != null){
            output +=  getEquation(tree.getRight()) + " )";
        }

        return output.trim();
    }
}
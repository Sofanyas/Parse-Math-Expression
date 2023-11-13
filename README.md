# Parse Math Expression

## Description

This Java project provides functionality to build a parse tree from a mathematical expression, recursively evaluate the expression represented by the parse tree, and retrieve the original infix equation from the parse tree.

## Usage

The `ParseMathExpression` class contains the following methods:

### 1. `buildParseTree(String expression)`

This method takes a mathematical expression as input and returns the root of the parse tree constructed from the expression.

```java
bridges.base.BinTreeElement<String> root = ParseMathExpression.buildParseTree("3 + ( 5 * 2 )");
```

### 2. `evaluate(bridges.base.BinTreeElement<String> tree)`

This method recursively evaluates the mathematical expression represented by the parse tree and returns the result.

```java
double result = ParseMathExpression.evaluate(root);
```

### 3. `getEquation(bridges.base.BinTreeElement<String> tree)`

This method recursively retrieves the original infix equation from the parse tree.

```java
String originalEquation = ParseMathExpression.getEquation(root);
```

## Example

```java
public class Main {
    public static void main(String[] args) {
        String expression = "3 + ( 5 * 2 )";
        bridges.base.BinTreeElement<String> root = ParseMathExpression.buildParseTree(expression);

        double result = ParseMathExpression.evaluate(root);
        System.out.println("Result: " + result);

        String originalEquation = ParseMathExpression.getEquation(root);
        System.out.println("Original Equation: " + originalEquation);
    }
}
```

## Credits

This project was created by Sofanyas Genene on November 10th, 2022.

## Acknowledgements

Special thanks to the [bridges.base.BinTreeElement](https://bridgesuncc.github.io/doc/java-api/current/bridges/base/BinTreeElement.html) class for facilitating the construction of the parse tree.

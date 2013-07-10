package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String a;
        Scanner scanner = new Scanner(System.in);

        Lexer l;
        ArithmeticExprParser p = new ArithmeticExprParser();
        BasicExpr expr;
        BasicProgram pr = new BasicProgram();

        do {
            System.out.print("> ");
            a = scanner.nextLine();
            l = new Lexer(a);
            p.reset();
            try {
                expr = p.parse(l.lex());
                System.out.println(((IntValue) expr.eval(pr)).value);
            } catch (ParensDoNotMatchException e) {
                System.out.println("Unbalanced Parentheses");
            } catch (MalformedArithmeticExpressionException e) {
                System.out.println("Malformed Arithmetic Expression");
            } catch (CannotLexException e) {
                System.out.println("Incorrect String; Cannot split to lexemes");
            }
        } while(!a.equals("exit"));

        scanner.close();
    }
}

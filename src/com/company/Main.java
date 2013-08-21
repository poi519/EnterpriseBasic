package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String a;
        Scanner scanner = new Scanner(System.in);

        Lexer l;
        BasicParser p = new BasicParser();
        BasicExpr expr;
        BasicProgram pr = new BasicProgram();

        do {
            System.out.print("> ");
            a = scanner.nextLine();
            l = new Lexer(a);
            try {
                expr = p.parseLine(l.lex());
                if(expr instanceof LabelledLine) {
                    pr.putLine(((LabelledLine) expr).number, a, ((LabelledLine) expr).expression);
                } else
                    System.out.println(expr.eval(pr).toString());
            } catch (ParensDoNotMatchException e) {
                System.out.println("Unbalanced Parentheses");
            } catch (MalformedArithmeticExpressionException e) {
                System.out.println("Malformed Arithmetic Expression");
            } catch (CannotLexException e) {
                System.out.println("Incorrect String; Cannot split to lexemes");
            } catch (ParseTimeException e) {
                System.out.println("Parse time exception");
            } catch (EvalTimeException e) {
                System.out.println("Cannot evaluate this expression");
            }
        } while(!a.equals("exit"));

        scanner.close();
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String a;
        Scanner scanner = new Scanner(System.in);

        Lexer l;
        Parser p = new Parser();
        BasicExpr expr;
        BasicProgram pr = new BasicProgram();
//        Integer[][] table =
//                {{6,   1,   1,   1,   1,   1,   5},
//                        {5,   1,   1,   1,   1,   1,   3},
//                        {4,   1,   2,   2,   1,   1,   4},
//                        {4,   1,   2,   2,   1,   1,   4},
//                        {4,   1,   4,   4,   2,   2,   4},
//                        {4,   1,   4,   4,   2,   2,   4}};
//
//        System.out.println(table[0][0]);
//
//        ArithmeticOperator pl = new LexPlus();
//        System.out.println(pl.index());

        do {
            System.out.print("> ");
            a = scanner.nextLine();
            l = new Lexer(a, 0);
            p.reset();
            try {
                expr = p.parse(l.lex());
                System.out.println(((IntValue) expr.eval(pr)).value);
            } catch (ParensDoNotMatchException e) {
                System.out.println("Unbalanced Parentheses");
            }
        } while(!a.equals("exit"));

        scanner.close();
    }
}

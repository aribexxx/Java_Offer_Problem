package Datastructure.String;

import java.util.Scanner;

public class BasicLoopLearning {


    public static void main(String[] argv) {
        //break & continue
        label:
        for (int i = 0; i <= 4; i++) {

            for (int j = 1; j <= 8; j++) {

                if (j % 4 == 0) {
                    // break;//when j==4的倍数,break out of the nearest for loop
                    //continue;// j==4的倍数,j will continue to next iteration of for loop
                    break label;
                }
                System.out.print("j" + j);//break: j>=4/8 won't print
                //continue: skip j==4/8     }
                System.out.println("i" + i);
            }
        }
        //-----------Scanner------------
        Scanner sc = new Scanner(System.in);
        System.out.println("Input next:");
        String n = sc.next();    //can not read after whitespace .eg. "hello world", only output "hello"
        System.out.println(n);

        System.out.println("Input nextLine:");
        String consume_Enter = sc.nextLine();// without this line to consume the extra \n(created by hit "enter" of last next())
        //   nl will be skipped.(nl gets a "\n" and terminate directly)
        String nl = sc.nextLine();
        String[] pieces = nl.split("\\s+");//split with space.\\s,first '\' means escape
        System.out.println(pieces[0]);

        //-----------Scanner--END----------
        System.out.println("input num:  ");
        int a = sc.nextInt();
        switch (a) {
            case 1:
                System.out.println("case 1");
                //break;
                // this will print all cases until it reaches a break.
            case 2:
                System.out.println("case 2");
                break;
            case 3:
                System.out.println("case 3");
            default:
                System.out.println("default");
        }


    }


}




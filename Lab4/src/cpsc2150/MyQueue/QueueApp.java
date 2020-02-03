package cpsc2150.MyQueue;

import java.util.Scanner;

public class QueueApp {

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        IntegerQueueI q;

        System.out.println("Enter 1 for array implementation, press 2 for list implementation:");
        int n = scanner.nextInt();
        if (n == 1) {
            q = new ArrayQueueImp();
        } else {
            q = new ListQueueImp();
        }

        Integer x = 42;
        q.add(x);
        x = 17;
        q.add(x);
        x = 37;
        q.add(x);
        x = 36;
        q.add(x);
        x = 12;
        q.add(x);

        System.out.println(q.toString());
    }
}

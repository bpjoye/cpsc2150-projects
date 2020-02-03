package cpsc2151.myQueue;

import java.util.Scanner;

public class IntegerQueueApp {

    private static Scanner in;

    public static void main(String[] args) {

        in = new Scanner(System.in);
        // this queue is for integers
        IQueue<Integer> q;

        System.out.println("Enter 1 for array implementation, press 2 for list implementation:");
        int answer = in.nextInt();
        if (answer == 1) {
            // call Array constructor
            q = new ArrayQueueImp<>();
        } else {
            // call List constructor
            q = new ListQueueImp<>();
        }


        displayMenu();
        answer = in.nextInt();
        while(answer != 8) {

            // clears the input buffer
            in.nextLine();

            if(answer == 1) {
                addToQueue(q);
            } else if(answer == 2) {
                getNext(q);
            } else if(answer == 3) {
                peekInQueue(q);
            } else if(answer == 4) {
                peekAtEnd(q);
            } else if(answer == 5) {
                insertInQueue(q);
            } else if(answer == 6) {
                getFromQueue(q);
            } else if(answer == 7) {
                removeFromQueue(q);
            } else {
                System.out.println("Not a valid option!");
            }

            System.out.println("Queue is: ");
            System.out.println(q.toString());
            System.out.println(" ");
            displayMenu();
            answer = in.nextInt();
        }
    }

    private static void displayMenu() {
        System.out.println("Select an option: ");
        System.out.println("1. Add to the Queue");
        System.out.println("2. Get next number from the Queue");
        System.out.println("3. Peek at the front of the Queue");
        System.out.println("4. Peek at the end of the Queue");
        System.out.println("5. Insert in the Queue");
        System.out.println("6. Get a position in the Queue");
        System.out.println("7. Remove from a position in the Queue");
        System.out.println("8. Quit");
    }

    private static void addToQueue(IQueue q) {
        System.out.println("What number to add to the queue?");
        int x = in.nextInt();
        q.add(x);
    }

    private static void getNext(IQueue q) {
        if (q.size() == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("Next number is " + q.pop());
        }
    }

    private static void peekInQueue(IQueue q) {
        if (q.size() == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("Next number is " + q.peek());
        }
    }

    private static void peekAtEnd(IQueue q) {
        if (q.size() == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("Peek: " + q.endOfQueue());
        }
    }

    private static void insertInQueue(IQueue q) {
        System.out.println("What number to add to the queue?");
        int x = in.nextInt();
        System.out.println("What position to insert in?");
        int y = in.nextInt();
        while (y < 1 || y > q.size()) {
            System.out.println("Not a valid position in the queue!");
            System.out.println("What position to insert in?");
            y = in.nextInt();
        }
        q.insert(x,y);
    }

    private static void getFromQueue(IQueue q) {
        if (q.size() == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("What position to get in the queue?");
            int x = in.nextInt();
            while (x < 1 || x > q.size()) {
                System.out.println("Not a valid position in the queue!");
                System.out.println("What position to insert in?");
                x = in.nextInt();
            }
            System.out.println(q.get(x) + " is at position " + x + " in the queue");
        }
    }

    private static void removeFromQueue(IQueue q) {

        if (q.size() == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("What position to remove from the queue?");
            int x = in.nextInt();
            while (x < 1 || x > q.size()) {
                System.out.println("Not a valid position in the queue!");
                System.out.println("What position to insert in?");
                x = in.nextInt();
            }
            System.out.println(q.remove(x) + " was at position " + x + " in the queue");
        }
    }
}

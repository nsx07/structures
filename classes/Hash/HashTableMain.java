package classes.Hash;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class HashTableMain {

    public static class Person {
        public int id;
        public String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person {" + " id= " + id + ", name= '" + name + '\'' + '}';
        }
    }

    public static Scanner scan = new Scanner(System.in);

    public static int input(String message) {
        System.out.print(message);
        return scan.nextInt();
    }
    public static String inputString(String message) {
        System.out.print(message);
        return scan.next();
    }

    public static void main(String[] args) {
        HashTableExternal<Integer, Person> hashT = new HashTableExternal<>();
//        HashTableInternal<Integer, Person> hashT = new HashTableInternal<>();

        Scanner scan = new Scanner(System.in);
        int value = 0;

        while (value != 0x999) {
            System.out.print("\n\r 0 - add; 1 - remove; 2 - search; 3 - print; 4 - clear; \n\r option: ");

            value = scan.nextInt();
            switch (value) {
                case 0 -> {
                    int i = input("id: ");
                    Instant start = Instant.now();
                    var result = hashT.add(i, new Person(i, inputString("nome: ")));

                    System.out.printf("%d - [%d ns]", result, Duration.between(start, Instant.now()).getNano());
                }
                case 1 -> {
                    Instant start = Instant.now();
                    var result = hashT.remove(input("id: "));

                    System.out.printf("%s - [%d ns]", result, Duration.between(start, Instant.now()).getNano());
                }
                case 2 -> {
                    Instant start = Instant.now();
                    var result = hashT.search(input("id: "));

                    System.out.printf("%s - [%d ns]", result, Duration.between(start, Instant.now()).getNano());
                }
                case 3 -> {
                    hashT.print();
                }
                case 4 -> {
                    hashT.clear();
                }
            }

        }

        scan.close();

    }

}

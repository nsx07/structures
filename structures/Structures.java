import classes.Tree;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class Structures {

    public static Scanner scan = new Scanner(System.in);
    public static int input(String message) {
        System.out.print(message);
        return scan.nextInt();
    }

    public static int askOption() {
        System.out.print("""
           \nQual operação deseja realizar?\s
                 1 - Adicionar\s
                 2 - Remover\s
                 3 - Buscar\s
                 4 - AutoRandom\s
                 5 - Limpar\s
                 0 - Sair \n
             Opção: """);
        return scan.nextInt();
    }

    public static Random random = new Random(LocalDateTime.now().getNano() / LocalDateTime.now().getSecond() + 1);
    public static void main(String[] args) throws Exception {


        Tree<Integer> tree = new Tree<>();
        boolean auto = false;
        int autoRounds = 0;
        int interval = 0;

        while (true) {

            if (!auto || autoRounds == 0) {
                tree.print();
                int option = askOption();

                switch (option) {
                    case 0 ->  {
                        scan.close();
                        System.exit(0);
                    }
                    case 1 -> {
                        tree.add(input("elemento: "));
                    }
                    case 2 -> {
                        tree.remove(input("elemento: "));
                    }
                    case 3 -> {
                        System.out.println(tree.get(input("elemento: ")) != null ? "Encontrado!" : "Não encontrado!");
                    }
                    case 4 -> {
                        auto = true;
                        interval = input("informe o intervalo de cada round(ms): ");
                        autoRounds = input("informe quantos rounds random deseja: ");
                    }
                    case 5 -> {tree.clear();}
                    default -> {
                        continue;
                    }
                }

                continue;
            }

            autoRounds--;
            tree.add(random.nextInt(0, 10000));
            Thread.sleep(interval);

        }

    }

}

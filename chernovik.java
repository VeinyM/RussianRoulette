import java.util.*;

public class RussianRoulette {
    public static void main(String[] args) {
        Revolver revolver = new Revolver();
        Display display = new Display();
        Scanner scanner = new Scanner(System.in);

        if (display.menu() == 11) { // SinglePlayer
            System.out.println("Count of players: ");
            int count_p = scanner.nextInt();

            System.out.println(Revolver.string_of_Players(count_p));
            revolver.setDrum();
            display.reload();
            display.revolverInfo();

            if (revolver.Fire()) {
                display.lose();
            } else {
                display.win();
            }
        } else { // Multiplayer
            System.out.println("Enter the number of players: ");
            int count_p = scanner.nextInt();
            List<String> players = new ArrayList<>();

            for (int i = 0; i < count_p; i++) {
                System.out.println("Enter name of player " + (i + 1) + ": ");
                players.add(scanner.next());
            }

            revolver.setDrum();
            display.reload();
            display.revolverInfo();

            int currentPlayerIndex = 0;
            while (players.size() > 1) {
                System.out.println("\nCurrent player: " + players.get(currentPlayerIndex));
                System.out.println();
                System.out.println("1. Shoot yourself");
                System.out.println("2. Shoot another player");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    if (revolver.Fire()) {
                        display.lose();
                        System.out.println(players.get(currentPlayerIndex) + " is out!");
                        players.remove(currentPlayerIndex);
                        if (currentPlayerIndex == players.size()) {
                            currentPlayerIndex = 0;
                        }
                    } else {
                        System.out.println(players.get(currentPlayerIndex) + " survived!");
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    }
                } else if (choice == 2) {
                    System.out.println("Choose a player to shoot:");
                    for (int i = 0; i < players.size(); i++) {
                        if (i != currentPlayerIndex) {
                            System.out.println((i + 1) + ". " + players.get(i));
                        }
                    }
                    int targetIndex = scanner.nextInt() - 1;
                    if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                        System.out.println("Invalid choice. Try again.");
                        continue;
                    }

                    if (revolver.Fire()) {
                        display.lose();
                        System.out.println(players.get(targetIndex) + " is out!");
                        players.remove(targetIndex);
                        if (targetIndex < currentPlayerIndex) {
                            currentPlayerIndex--;
                        }
                        if (currentPlayerIndex == players.size()) {
                            currentPlayerIndex = 0;
                        }
                    } else {
                        System.out.println(players.get(targetIndex) + " survived!");
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    }
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

            System.out.println("The winner is: " + players.get(0) + "!");
        }
    }
}

class Revolver extends Display {
    Random random = new Random();
    int[] drum;
    int currentBulletIndex = 0;

    public Revolver() {
        drum = new int[random.nextInt(5) + 2]; // Random size between 2 and 6
    }

    public void setDrum() {
        int badPellets = random.nextInt(drum.length / 2) + 1; // At least 1 bad pellet
        Arrays.fill(drum, 0); // Reset drum to all empty
        for (int i = 0; i < badPellets; i++) {
            drum[i] = 1;
        }
        // Shuffle the drum
        for (int i = 0; i < drum.length; i++) {
            int swapIndex = random.nextInt(drum.length);
            int temp = drum[i];
            drum[i] = drum[swapIndex];
            drum[swapIndex] = temp;
        }
        currentBulletIndex = 0; // Reset index after reloading
        System.out.println("Drum reloaded: " + Arrays.toString(drum));
    }

    public boolean Fire() {
        if (currentBulletIndex >= drum.length) {
            System.out.println("\nAll bullets used! Reloading the revolver...");
            setDrum(); // Reload the drum
        }
        boolean result = drum[currentBulletIndex] == 1;
        currentBulletIndex++;
        return result;
    }

    public static String string_of_Players(int count_of_players) {
        Scanner scanner = new Scanner(System.in);
        String[] array_players = new String[count_of_players];
        for (int i = 0; i < array_players.length; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            array_players[i] = scanner.next();
        }
        return Arrays.toString(array_players);
    }
}


class Display {
    public void revolverInfo() {
        System.out.println("\nGun reloaded and shuffled!");
        try {
            Thread.sleep(333);
        } catch (InterruptedException ex) {
        }
        System.out.println("Good luck!");
        System.out.println("-------Game Starts-------");
    }

    public void lose() {
        System.out.println("You LOSE!");
    }

    public void win() {
        System.out.println("You WIN!");
    }

    public void reload() {
        for (int i = 0; i < 3; i++) {
            System.out.print("Spinning the drum");
            for (int j = 0; j < 3; j++) {
                try {
                    Thread.sleep(666);
                } catch (InterruptedException ex) {
                }
                System.out.print(".");
            }
            System.out.println();
        }
    }

    public int menu() {
        Scanner scan = new Scanner(System.in);

        int choice = 0;
        System.out.println("Welcome to the Russian Roulette game!");
        System.out.println("Do you want to play? (y/n)");
        String decision = scan.next().toLowerCase();
        if (decision.equals("y")) {
            System.out.println("Here we go, partner!\n");
            choice += 10;
        } else if (decision.equals("n")) {
            System.out.println("What a shame, partner!");
            System.exit(0);
        } else {
            System.out.println("You typed that wrong, partner! Please make it clear next time!");
        }

        System.out.println("SinglePlayer or Multiplayer? (s/m)");
        decision = scan.next().toLowerCase();
        if (decision.equals("s")) {
            System.out.println("Starting Singleplayer!!\n");
            choice += 1;
        } else if (decision.equals("m")) {
            System.out.println("Starting Multiplayer!!\n");
            choice += 2;
        } else {
            System.out.println("You typed that wrong, partner! Please make it clear next time!");
        }
        return choice;
    }
}

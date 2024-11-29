import java.util.*;

public class RussianRoulette {
    public static void main(String[] args) {
        Revolver revolver = new Revolver();
        Display display = new Display();
        clearConsole clear = new clearConsole();

//        clear.clearConsole();                    <---- КРЧ ЕЛДОС БУЛ КОНСОЛЬ ТАЗАЛАЙД
        display.menu();
//        if () {              <---ТУРА БЕРСЫН
            revolver.setDrum();
            display.reload();
            display.revolverInfo();
            System.out.println();
            if (revolver.Fire()) {
                display.lose();
            } else {
                display.win();
//            }              Тура берсын
        }
    }
}

class Revolver{
    int[] badPellets = new int[2];
    int[] drum = new int[6];
    int length = drum.length;

    public void setDrum() {       //<----------МЫНАҒАН 8 или 12 ОК БОЛАТЫНЫН И Т.Д. ЖАСАЙ САЛ

        for (int i=0;i<badPellets.length;i++){
            drum[i] = 1;
        }

        for (int i=0;i<drum.length;i++){

            if (i==5){
                break;
            }
            int temp;

            for (int j=0;j < (int)(Math.random()*drum.length);j++){
                temp = drum[j];
                drum[j] = drum[j+1];
                drum[j+1] = temp;
            }
        }
    }

    public boolean Fire(){
        if (drum[length-1] == 1) {
            return true;
        }else {
            this.length -= 1;
            return Fire();
        }
    }
}


class Display{                                                     //ФИЧА КОС

    public void revolverInfo(){
        System.out.println("\nGun reloaded and shuffled!");

        try {
            Thread.sleep(200);
        }

        catch (InterruptedException ex){}

        System.out.println("Good luck!");
    }

    public void lose(){
        System.out.println("You LOSE!");
    }

    public void win(){
        System.out.println("You WIN!");
    }

    public void reload(){
        for (int i = 0; i < 3; i++) {
            char dot = '.';
            System.out.print("Shuffling");
            for (int j=0;j<3;j++){
                try {
                Thread.sleep(666);
            } catch (InterruptedException ex) {
            }
                System.out.printf("%c",dot);
            }
            try {
                Thread.sleep(2000);  //2000 ms = 2 sec
            } catch (InterruptedException ex) {
            }
            System.out.println();

        }
    }

    public int menu(){                                //МЫНАНЫ ӨЗІМ ЖАСАЙ САЛАМ
        Scanner scan = new Scanner(System.in);
        int choice=0;
        System.out.println("Welcome to the Russian Roulette game!");
        System.out.println("Do you want to play?(y/n)");
        String ScanDecision = scan.next().toLowerCase();
        if (ScanDecision.equals("y")){
            System.out.println("Here we go, partner!\n");
            choice+=10;
        }else if (ScanDecision.equals("n")) {
            System.out.println("What a shame, partner!");
            System.exit(0);
        }else{
            System.out.println("You typed that wrong, partner! Please make it clear next time!");
        }
//        ПОТОМ ДОБАВИМ БОТА И МУЛЬТИПЛЕЕР!
        System.out.println("SinglePlayer or Multiplayer?(s/m)");
        ScanDecision = scan.next().toLowerCase();
        if (ScanDecision.equals("s")){
            System.out.println("Starting Singleplayer!!\n");
            choice += 1;
        } else if (ScanDecision.equals("m")) {
            System.out.println("Starting Multiplayer!!\n");
            choice += 2;
        }else{
            System.out.println("You typed that wrong, partner! Please make it clear next time!");
        }
        return choice;
    }
}

class clearConsole{                                         // КОНСОЛЬ ТАЗАЛАУ

    public void clearConsole(){
        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {}
    }
}

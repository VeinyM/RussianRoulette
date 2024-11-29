import java.util.*;

public class RussianRoulette {
    public static void main(String[] args) {
        Revolver revolver = new Revolver();
        Display display = new Display();
        display.menu();
        revolver.setDrum();
        display.reload();
        display.revolverInfo();
        System.out.println();
        if (revolver.Fire()){
            display.lose();
        }else {
            display.win();
        }
    }
}

class Revolver{
    int[] drum = {1,0,0,0,0,0};

    public void setDrum() { // ЕЛДОС ШАФЛ ЖАСА
        int[] otherDrum = new int[6];
        for (int i=0;i<drum.length;i++){
            if (i==5)   {break;}
            int temp;
            for (int j=0;j < (int)(Math.random()*6);j++){
                temp = drum[j];
                drum[j] = drum[j+1];
                drum[j+1] = temp;
            }
        }
    }

    public boolean Fire(){
        for (int i=0;i<drum.length;i++){
            if (drum[i]==1){
                return true;
            }
        }
        return false;
    }
}


class Display{

    public void revolverInfo(){
        System.out.println("Gun reloaded and shuffled!");
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
            System.out.println("Shuffling...");
            try {
                Thread.sleep(2000);  //2000 ms = 2 sec
            } catch (InterruptedException ex) {
            }
        }
    }

    public void menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Russian Roulette game!");
        System.out.println("Do you want to play?(y/n)");
        if (scan.next().toLowerCase().equals("y")){
            System.out.println("Here we go, partner!");
        }else if (scan.next().toLowerCase().equals("n")) {
            System.out.println("What a shame, partner!");
        }else{
            System.out.println("You typed that wrong, partner!");
        }
        System.out.println("SinglePlayer or Multiplayer?(s/m)");
    }
}


import java.util.Random;
import java.util.Scanner;

public class NUMBER_GAME{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Random r=new Random();
        int lowerbound=1,upperbound=100,maxattempts=7;
        int roundsWon=0;

        System.out.println("WELCOME TO NUMBER GAME!");

        while (true) {
            int secretnumber=r.nextInt(upperbound - lowerbound +1) + lowerbound;
            int attempts=0;
            boolean guessed_correct=false;

            System.out.println("\n I have chosen a number between "+lowerbound+" and "+upperbound+". Try to guess it!");

            while (attempts<maxattempts) {
                System.out.println("Attempt "+(attempts+1)+"/"+maxattempts+"Enter your guess:");
                int guess;
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input");
                    sc.next();
                }
                guess=sc.nextInt();
                attempts++;

                if (guess<secretnumber) {
                    System.out.println("Too low! Try Again.");
                }
                else  if (guess>secretnumber) {
                    System.out.println("Too high! Try Again.");
                }
                else {
                    System.out.println("Congratulations! You guessed the number "+secretnumber+"correctly in "+attempts+"attempts.");
                    roundsWon++;
                    guessed_correct=true;
                    break;
                }
            }
            if (!guessed_correct) {
                System.out.println("You have all "+maxattempts+"attempts. The correct number was "+secretnumber);
            }
            System.out.println("Rounds won: "+roundsWon);
            System.out.println("Do you want to play again? (yes/no)");
            String playAgain=sc.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thank you for playing! You won "+roundsWon+" rounds");
                break;
            }
        }
        sc.close();
    }
}

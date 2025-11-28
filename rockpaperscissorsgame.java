import java.util.Scanner;

public class rockpaperscissorsgame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] choices = {"rock", "paper", "scissors"};
        
        System.out.println("Welcome to Rock Paper Scissors!");
        System.out.println("Type 'rock', 'paper', or 'scissors' to play. Type 'quit' to exit.\n");
        
        while (true) {
            System.out.print("Your choice: ");
            String userInput = scanner.nextLine().toLowerCase().trim();
            
            if (userInput.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }
            
            if (!userInput.equals("rock") && !userInput.equals("paper") && !userInput.equals("scissors")) {
                System.out.println("Invalid input. Please enter 'rock', 'paper', or 'scissors'.\n");
                continue;
            }
            
            String computerChoice = choices[(int)(Math.random() * 3)];
            System.out.println("Computer chose: " + computerChoice);
            
            String result = determineWinner(userInput, computerChoice);
            System.out.println(result + "\n");
        }
        
        scanner.close();
    }
    
    static String determineWinner(String user, String computer) {
        if (user.equals(computer)) {
            return "It's a tie!";
        }
        
        if ((user.equals("rock") && computer.equals("scissors")) ||
            (user.equals("paper") && computer.equals("rock")) ||
            (user.equals("scissors") && computer.equals("paper"))) {
            return "You win!";
        }
        
        return "Computer wins!";
    }
}
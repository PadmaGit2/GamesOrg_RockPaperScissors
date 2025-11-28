import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RockPaperScissorsJavaFX extends Application {
    private Label resultLabel;
    private Label computerChoiceLabel;
    private Label scoreLabel;
    private int wins = 0, losses = 0, ties = 0;
    private String[] choices = {"rock", "paper", "scissors"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rock Paper Scissors - JavaFX");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        // Main container
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2c3e50;");
        root.setAlignment(Pos.TOP_CENTER);

        // Title
        Label titleLabel = new Label("Rock Paper Scissors");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: white;");

        // Score display
        scoreLabel = new Label("Wins: 0 | Losses: 0 | Ties: 0");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scoreLabel.setStyle("-fx-text-fill: #ecf0f1;");

        // Computer choice display
        computerChoiceLabel = new Label("Computer chose: ?");
        computerChoiceLabel.setFont(Font.font("Arial", 14));
        computerChoiceLabel.setStyle("-fx-text-fill: #3498db;");

        // Result display
        resultLabel = new Label("Make your choice!");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        resultLabel.setStyle("-fx-text-fill: #2ecc71;");

        // Buttons panel
        HBox buttonPanel = new HBox(15);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(20, 0, 0, 0));

        Button rockBtn = createStyledButton("Rock", "#e74c3c");
        Button paperBtn = createStyledButton("Paper", "#3498db");
        Button scissorsBtn = createStyledButton("scissors", "#9b59b6");

        rockBtn.setOnAction(e -> playGame("rock"));
        paperBtn.setOnAction(e -> playGame("paper"));
        scissorsBtn.setOnAction(e -> playGame("scissors"));

        buttonPanel.getChildren().addAll(rockBtn, paperBtn, scissorsBtn);

        // Reset button
        Button resetBtn = new Button("Reset Score");
        resetBtn.setStyle("-fx-padding: 10px 20px; -fx-font-size: 12px; -fx-background-color: #95a5a6;");
        resetBtn.setOnAction(e -> resetScore());

        HBox resetPanel = new HBox();
        resetPanel.setAlignment(Pos.CENTER);
        resetPanel.getChildren().add(resetBtn);

        // Add all to root
        root.getChildren().addAll(titleLabel, scoreLabel, computerChoiceLabel, resultLabel, buttonPanel, resetPanel);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-padding: 15px 30px; -fx-font-size: 16px; -fx-font-weight: bold; " +
                     "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-cursor: hand;");
        btn.setPrefWidth(120);
        return btn;
    }

    private void playGame(String userChoice) {
        String computerChoice = choices[(int)(Math.random() * 3)];
        String result = determineWinner(userChoice, computerChoice);

        computerChoiceLabel.setText("Computer chose: " + computerChoice);
        resultLabel.setText(result);

        if (result.equals("You win!")) {
            resultLabel.setStyle("-fx-text-fill: #2ecc71;");
            wins++;
        } else if (result.equals("Computer wins!")) {
            resultLabel.setStyle("-fx-text-fill: #e74c3c;");
            losses++;
        } else {
            resultLabel.setStyle("-fx-text-fill: #f39c12;");
            ties++;
        }

        scoreLabel.setText("Wins: " + wins + " | Losses: " + losses + " | Ties: " + ties);
    }

    private void resetScore() {
        wins = 0;
        losses = 0;
        ties = 0;
        scoreLabel.setText("Wins: 0 | Losses: 0 | Ties: 0");
        resultLabel.setText("Make your choice!");
        computerChoiceLabel.setText("Computer chose: ?");
    }

    static String determineWinner(String user, String computer) {
        if (user.equals(computer)) return "It's a tie!";
        if ((user.equals("rock") && computer.equals("scissors")) ||
            (user.equals("paper") && computer.equals("rock")) ||
            (user.equals("scissors") && computer.equals("paper"))) {
            return "You win!";
        }
        return "Computer wins!";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
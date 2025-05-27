import javafx.application.Platform;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Random;

public class RockPaperScissorGameFX extends Application 
{
    Button rockButton, paperButton, scissorButton;
    Label resultLabel, scoreLabel, cpuLabel, cpuLabel0;
    int userScore, computerScore;
    Image rockImage, paperImage, scissorImage;
    Font font;

    @Override
    public void start(Stage primaryStage) 
	{
        primaryStage.setTitle("Rock, Paper, Scissors Game");

        font = new Font("Arial", 30);

        // Load images
		rockImage = new Image("file:/Rock.png");
        paperImage = new Image("file:/Paper.png");
        scissorImage = new Image("file:/Scissor.png");
		
        ImageView rockView = new ImageView(rockImage);
        ImageView paperView = new ImageView(paperImage);
        ImageView scissorView = new ImageView(scissorImage);

        // Initialize components
        rockButton = new Button("",rockView);
        paperButton = new Button("",paperView);
        scissorButton = new Button("",scissorView);

        resultLabel = new Label("Make your choice!");
        cpuLabel0 = new Label("CPU's choice:");
        cpuLabel = new Label("CPU");
        scoreLabel = new Label("User Score: 0, Computer Score: 0");

        cpuLabel0.setFont(font);
        resultLabel.setFont(font);
        scoreLabel.setFont(font);
        cpuLabel.setFont(font);

        // Set up layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(cpuLabel0, 0, 1, 3, 1);
        gridPane.add(cpuLabel, 0, 2, 3, 1);
        gridPane.add(rockButton, 1, 2);
        gridPane.add(paperButton, 0, 3);
        gridPane.add(scissorButton, 1, 3);
        gridPane.add(resultLabel, 0, 4, 3, 1);
        gridPane.add(scoreLabel, 0, 5, 3, 1);

        // Set up scene
        Scene scene = new Scene(gridPane, 700, 700);
        primaryStage.setScene(scene);

        // Set up event handlers
        rockButton.setOnAction(e -> handleButtonAction(rockImage));
        paperButton.setOnAction(e -> handleButtonAction(paperImage));
        scissorButton.setOnAction(e -> handleButtonAction(scissorImage));

        primaryStage.setOnCloseRequest(event -> {
            // Terminate the JavaFX application
            Platform.exit();
        });

        // Show the stage
        primaryStage.show();
        
    }

    private void handleButtonAction(Image userChoice) 
	{
        Image[] choices = {rockImage, paperImage, scissorImage};
        Random random = new Random();
        int computerChoiceIndex = random.nextInt(3);
        Image computerChoice = choices[computerChoiceIndex];

        cpuLabel.setText("");
        cpuLabel.setGraphic(new ImageView(computerChoice));

        String result = determineWinner(userChoice, computerChoice);
        resultLabel.setText("Result: " + result);
        scoreLabel.setText("User Score : " + userScore + ", Computer Score: " + computerScore);
    }

    private String determineWinner(Image userChoice, Image computerChoice) 
	{
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } 
		else if ((userChoice.equals(rockImage) && computerChoice.equals(scissorImage)) ||
                (userChoice.equals(paperImage) && computerChoice.equals(rockImage)) ||
                (userChoice.equals(scissorImage) && computerChoice.equals(paperImage))) {
            userScore++;
            return "You win!";
        } 
		else {
            computerScore++;
            return "Computer wins!";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

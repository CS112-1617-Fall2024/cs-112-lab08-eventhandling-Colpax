package edu.miracosta.cs112.lab08eventhandling;

//IMPORTED PACKAGES
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Arrays;
import javafx.scene.control.ProgressBar;

public class Main extends Application {
    //CONSTANTS
    int cardNumber = 0;
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };
    static int[] showedCards = new int[LOTERIA_CARDS.length];
    //CLASS-LEVEL VARIABLES


    //GUI METHODS
    public static void main(String[] args) {
        Arrays.fill(showedCards, LOTERIA_CARDS.length);
        for (int x = 0; x < showedCards.length; x++) {
            boolean isThere = false;
            int randnum = (int)(Math.random() * LOTERIA_CARDS.length);

            for (int showedCard : showedCards) {
                if (showedCard == randnum) {
                    isThere = true;
                    break;
                }
            }
            if(isThere){
                x--;
                continue;
            }
            showedCards[x] = randnum;
        }
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button drawButton = new Button("Draw a card");
        Label title = new Label("EChALE STEM Loteria");
        title.setFont(new Font(20));
        Label description = new Label("Click button below to draw a new card");
        String path = ""; //folder with pictures
        Image image = new Image(path + "0.png");
        ImageView card = new ImageView();
        card.setFitWidth(270);
        card.setPreserveRatio(true);
        card.setImage(image);
        ProgressBar bar = new ProgressBar();
        bar.setMaxWidth(200);
        bar.setProgress(0);

        VBox layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().add(title);
        layout.getChildren().add(card);
        layout.getChildren().add(description);
        layout.getChildren().add(drawButton);
        layout.getChildren().add(bar);

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (cardNumber >= LOTERIA_CARDS.length){
                    Image image = new Image(path + "0.png");
                    card.setImage(image);
                    description.setText("GAME OVER. No more cards!");
                    bar.setStyle("-fx-accent: red;");
                    drawButton.setDisable(true);
                }
                else {
                    Image image = new Image(path + LOTERIA_CARDS[showedCards[cardNumber]].getImageName());
                    description.setText(LOTERIA_CARDS[showedCards[cardNumber]].getCardName());
                    card.setImage(image);
                    cardNumber++;
                    bar.setProgress((1.0 / LOTERIA_CARDS.length) * cardNumber);
                }
            }
        });

        Scene scene = new Scene(layout, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Loteria");
        primaryStage.show();
    }
}
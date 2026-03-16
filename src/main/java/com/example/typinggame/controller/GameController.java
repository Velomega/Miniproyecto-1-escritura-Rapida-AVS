package com.example.typinggame.controller;

import com.example.typinggame.model.Interface.GameAdapter;
import com.example.typinggame.model.Interface.IScore;
import com.example.typinggame.model.Interface.ITime;
import com.example.typinggame.model.Interface.IWords;
import com.example.typinggame.model.Score;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import com.example.typinggame.model.Words;
import com.example.typinggame.model.Time;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


/**
 * Main controller for the game session.
 * It manages the interaction between the word, score, and time models,
 * handles user input via JavaFX events, and updates the UI in real-time.
 */
public class GameController {

    @FXML private Button btnRestart;
    @FXML private Label timeLabel;
    @FXML private Button btnValidate;
    @FXML private Label wordLabel;
    @FXML private TextField wordTextField;
    @FXML private ImageView validationIcon;
    @FXML private Label chapterLabel;
    @FXML private Label scoreLabel;
    @FXML private Label seasonLabel;
    @FXML private ProgressBar timeBar;


    private final IWords wordList = new Words();
    private final IScore score = new Score();
    private final ITime time = new Time();
    private String currentWord;
    private final PauseTransition pause = new PauseTransition(Duration.seconds(1));
    private Timeline gameTimer;
    private final GameHandler gameHandler = new GameHandler();

    /**
     * Inner class that handles game event callbacks.
     * Uses the Adapter pattern to update the score and UI feedback
     * based on the player's performance.
     */
    public class GameHandler extends GameAdapter {
        @Override
        public void onCorrectWord() {
            score.addPoint();
            wordLabel.setText("Correcto!");
            updateValidationImage(true);
        }

        @Override
        public void onWrongWord() {
            score.addError();
            if (time.getRemainingTime() <= 0) {
                wordLabel.setText("Tiempo!");
            } else {
                wordLabel.setText("Error!");
            }
            updateValidationImage(false);
        }
    }

    /**
     * Initializes the controller after the FXML has been loaded.
     * Sets up the initial state of the game, listeners, and the timer.
     */
    public void initialize(){
    currentWord= wordList.wordsGenerator();
    scoreLabel.setText("Puntaje: 0");
    seasonLabel.setText("Temporada: 1");
    chapterLabel.setText("Capítulo: 1");
    wordLabel.setText(currentWord);
    javafx.application.Platform.runLater(() -> wordTextField.requestFocus());
    setupScoreListeners();
    setupTimer();
    startGame();
    }

    /**
     * Binds the score properties to the UI labels.
     * Uses observable listeners to automatically update the view when data changes.
     */
    private void setupScoreListeners() {
        score.pointsProperty().addListener((obs, oldVal, newVal) ->
                scoreLabel.setText("Puntaje: " + newVal));
        score.seasonProperty().addListener((obs, oldVal, newVal) ->
                seasonLabel.setText("Temporada: " + newVal));
        score.chapterProperty().addListener((obs, oldVal, newVal) ->
                chapterLabel.setText("Capítulo: " + newVal));
    }

    /**
     * Validates the user's input against the current target word.
     * Handles transitions between correct/incorrect states and advances progress.
     */
    public void validate() {
        gameTimer.stop();
        String userWord = wordTextField.getText().trim();
        boolean isCorrect = userWord.equalsIgnoreCase(currentWord);

        if (isCorrect) {
            wordTextField.clear();
            gameHandler.onCorrectWord();
            ((Words) wordList).advanceProgress();
            score.nextStep(wordList.getCurrentLevel());
        } else {
            wordTextField.clear();
            gameHandler.onWrongWord();

        }


        if (isCorrect || time.getRemainingTime() > 0) {
            pause.setOnFinished(event -> {
                resetIcon(null);


                currentWord = wordList.wordsGenerator();
                wordLabel.setText(currentWord);

                time.restart(wordList.getCurrentLevel());
                gameTimer.play();
            });
            pause.play();
        } else {
            handleTimeout();
        }
    }

    /**
     * Updates the visual feedback icon (Checkmark or X) based on input validity.
     * @param isCorrect True if the input was correct, false otherwise.
     */
    public void updateValidationImage(boolean isCorrect)  {
        String path;

        if(isCorrect){
            path="/com/example/typinggame/imagenes/correcto.jpg";
        }else{
            path="/com/example/typinggame/imagenes/incorrecto.png";
        }


        var resource = getClass().getResource(path);
        if (resource != null) {
            Image image = new Image(resource.toExternalForm());
            validationIcon.setImage(image);
        }

    }

    /**
     * Resets the visual feedback icon to its neutral/loading state.
     * @param event The action event (can be null).
     */
    private void resetIcon(ActionEvent event) {
        String neutralPath = "/com/example/typinggame/imagenes/luz_roja_mueve.gif";
        var neutralResource = getClass().getResource(neutralPath);

        if (neutralResource != null) {
            validationIcon.setImage(new Image(neutralResource.toExternalForm()));
        }
    }

    /**
     * Triggered when the validation button is clicked.
     * @param event The ActionEvent from the button.
     */
    @FXML
    public void onValidateWord(ActionEvent event) {
        validate();
    }

    /**
     * Handles key events, specifically checking for the ENTER key to validate the word.
     * @param event The KeyEvent captured from the text field.
     */
    @FXML
    void onHandleEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            validate();
        }
    }

    /**
     * Configures the countdown timer and its UI binding.
     */
    private void setupTimer() {
        time.remainingTimeProperty().addListener((observable, oldValue, newValue) -> {
            timeLabel.setText("Tiempo: " + newValue + "s");

            if (newValue.intValue() <= 0) {

                validate();
            }
        });

        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            time.decrease();
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the initial game countdown and timer.
     */
    public void startGame() {
        time.restart();
        gameTimer.play();
    }

    /**
     * Resets the current game session to its initial state.
     * Reshuffles words, resets the score, and restarts the timer.
     * @param event The ActionEvent from the restart button.
     */
    public void onRestartGame(ActionEvent event) {

        gameTimer.stop();


        score.reset();
        time.restart();
        wordList.reset();


        currentWord = wordList.wordsGenerator();


        wordLabel.setText(currentWord);
        wordTextField.clear();
        scoreLabel.setText("Puntaje: 0");
        seasonLabel.setText("Temporada: 1");
        chapterLabel.setText("Capítulo: 1");
        timeLabel.setText("Tiempo: 20s");


        resetIcon(null);

        wordTextField.requestFocus();


        gameTimer.play();
    }
    /**
     * Performs a complete reset of the entire game state and UI components.
     * This method resets the score, timer, and word bank to their initial values,
     * updates all interface labels to their starting text, and restarts the
     * game loop from the first level.
     */


    /**
     * Processes a "Game Over" scenario when the player runs out of time.
     * Transitions to the EndStage with the final calculated statistics.
     */
    private void handleTimeout() {
        gameTimer.stop();
        wordLabel.setText("¡PERDISTE!");
        updateValidationImage(false);

        PauseTransition gameOverPause = new PauseTransition(Duration.seconds(2));
        gameOverPause.setOnFinished(e -> {
            try {
                int finalLevel = ((wordList.getCurrentLevel() - 1) * 5) + ((Words) wordList).getWordsServedInLevel();
                int finalScore = score.getPoints();

                new com.example.typinggame.View.EndStage(finalLevel, finalScore);

                javafx.stage.Stage currentStage = (javafx.stage.Stage) wordLabel.getScene().getWindow();
                if (currentStage != null) currentStage.close();

            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        });
        gameOverPause.play();
    }



}

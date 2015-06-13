package com.jonplonk.obfuscated;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class Game extends ActionBarActivity {

    TextView score, multiplier, lives, word_desc, target_word, action_prompt;
    Button upper_left, upper_right, lower_left, lower_right;
    private ArrayList<String[]> gameData;
    private ArrayList<int[]> gameCombinations;
    private Random randomiser;
    String selectedWord;

    int pLives;
    int pScore;
    float pMultiplier;
    int baseScore;

    int ALPHABET = 26;
    int CONVERT_LETTER = 65;

    public static String DIFFICULTY = "normal";
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = (TextView) findViewById(R.id.score);
        multiplier = (TextView) findViewById(R.id.multiplier);
        lives = (TextView) findViewById(R.id.lives);
        word_desc = (TextView) findViewById(R.id.word_desc);
        target_word = (TextView) findViewById(R.id.target_word);
        action_prompt = (TextView) findViewById(R.id.action_prompt);

        // needs to be before gameCombinations method is called, otherwise NullPointerEx.
        Intent startGame = getIntent();
        difficulty = startGame.getStringExtra(DIFFICULTY);

        randomiser = new Random(System.currentTimeMillis());
        gameData = loadData();
        gameCombinations = loadCombinations();

        pLives = 3;
        pScore = 0;
        pMultiplier = 0;

        final MediaPlayer buzz = MediaPlayer.create(Game.this, R.raw.buzz);
        // final MediaPlayer slap = MediaPlayer.create(Game.this, R.raw.slap);
        final MediaPlayer beep = MediaPlayer.create(Game.this, R.raw.beep);
        final MediaPlayer bleep = MediaPlayer.create(Game.this, R.raw.bleep);

        // final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        // calculate base score
        baseScore = calculateBaseScore(difficulty);

        upper_left = (Button) findViewById(R.id.upper_left);
        upper_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                if (pLives < 1)
                    nextActivity(Integer.toString(pScore));
                else if (!checkAnswer(upper_left.getText().toString(), selectedWord, beep, bleep)) {
                    gameLost(buzz);
                    if (pLives < 1)
                        nextActivity(Integer.toString(pScore));
                }
            }
        });

        upper_right = (Button) findViewById(R.id.upper_right);
        upper_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                if (pLives < 1)
                    nextActivity(Integer.toString(pScore));
                else if (!checkAnswer(upper_right.getText().toString(), selectedWord, beep, bleep)) {
                    gameLost(buzz);
                    if (pLives < 1)
                        nextActivity(Integer.toString(pScore));
                }
            }
        });

        lower_left = (Button) findViewById(R.id.lower_left);
        lower_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                if (pLives < 1)
                    nextActivity(Integer.toString(pScore));
                else if (!checkAnswer(lower_left.getText().toString(), selectedWord, beep, bleep)) {
                    gameLost(buzz);
                    if (pLives < 1)
                        nextActivity(Integer.toString(pScore));
                }
            }
        });

        lower_right = (Button) findViewById(R.id.lower_right);
        lower_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                if (pLives < 1)
                    nextActivity(Integer.toString(pScore));
                else if (!checkAnswer(lower_right.getText().toString(), selectedWord, beep, bleep))
                    gameLost(buzz);
                if (pLives < 1)
                    nextActivity(Integer.toString(pScore));
            }
        });

        // load the first game
        selectedWord = newGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String[]> loadData() {
        ArrayList<String[]> gameData = new ArrayList<>();

        gameData.add(getResources().getStringArray(R.array.game1));
        gameData.add(getResources().getStringArray(R.array.game2));
        gameData.add(getResources().getStringArray(R.array.game3));
        gameData.add(getResources().getStringArray(R.array.game4));
        gameData.add(getResources().getStringArray(R.array.game5));
        gameData.add(getResources().getStringArray(R.array.game6));
        gameData.add(getResources().getStringArray(R.array.game7));
        gameData.add(getResources().getStringArray(R.array.game8));
        gameData.add(getResources().getStringArray(R.array.game9));
        gameData.add(getResources().getStringArray(R.array.game10));
        gameData.add(getResources().getStringArray(R.array.game11));
        gameData.add(getResources().getStringArray(R.array.game12));
        gameData.add(getResources().getStringArray(R.array.game13));
        gameData.add(getResources().getStringArray(R.array.game14));
        gameData.add(getResources().getStringArray(R.array.game15));
        gameData.add(getResources().getStringArray(R.array.game16));
        gameData.add(getResources().getStringArray(R.array.game17));

        return gameData;
    }

    private ArrayList<int[]> loadCombinations() {
        ArrayList<int[]> gameCombinations = new ArrayList<>();

        switch(difficulty) {

            case "easy":
                int[] ec1 = {0, 1};
                gameCombinations.add(ec1);
                int[] ec2 = {0, 2};
                gameCombinations.add(ec2);
                int[] ec3 = {0, 3};
                gameCombinations.add(ec3);
                int[] ec4 = {0, 4};
                gameCombinations.add(ec4);
                int[] ec5 = {1, 2};
                gameCombinations.add(ec5);
                int[] ec6 = {1, 3};
                gameCombinations.add(ec6);
                int[] ec7 = {1, 4};
                gameCombinations.add(ec7);
                int[] ec8 = {2, 3};
                gameCombinations.add(ec8);
                int[] ec9 = {2, 4};
                gameCombinations.add(ec9);
                int[] ec10 = {3, 4};
                gameCombinations.add(ec10);
                break;

            case "hard":
                int[] hc1 = {1, 2, 3, 4};
                gameCombinations.add(hc1);
                int[] hc2 = {0, 2, 3, 4};
                gameCombinations.add(hc2);
                int[] hc3 = {0, 1, 3, 4};
                gameCombinations.add(hc3);
                int[] hc4 = {0, 1, 2, 4};
                gameCombinations.add(hc4);
                int[] hc5 = {0, 1, 2, 3};
                gameCombinations.add(hc5);
                break;

            case "impossible":
                int[] impoCombo = {0, 1, 2, 3, 4};
                gameCombinations.add(impoCombo);
                break;

            default:        // default = normal mode
                int[] combo = {0, 1, 2};
                gameCombinations.add(combo);
                int[] combo2 = {0, 1, 3};
                gameCombinations.add(combo2);
                int[] combo3 = {0, 1, 4};
                gameCombinations.add(combo3);
                int[] combo4 = {0, 2, 3};
                gameCombinations.add(combo4);
                int[] combo5 = {0, 2, 4};
                gameCombinations.add(combo5);
                int[] combo6 = {0, 3, 4};
                gameCombinations.add(combo6);
                int[] combo7 = {1, 2, 3};
                gameCombinations.add(combo7);
                int[] combo8 = {1, 2, 4};
                gameCombinations.add(combo8);
                int[] combo9 = {1, 3, 4};
                gameCombinations.add(combo9);
                int[] combo10 = {2, 3, 4};
                gameCombinations.add(combo10);
                break;

        }

        return gameCombinations;
    }

    private String newGame() {

        // choose a game for the user to play
        String[] selectedGame = gameData.get(randomiser.nextInt(gameData.size()));

        // choose a word, chop up the word into chars, generate a cipher
        String selectedWord = selectedGame[randomiser.nextInt(4)];
        char[] choppedUpWord = selectedWord.toCharArray();
        int steps = randomiser.nextInt(25) + 1;

        // select a combination of numbers to encipher
        int letterSelection = 0;

        switch (difficulty) {

            case "easy":
            case "medium":
                letterSelection = randomiser.nextInt(10);
                break;

            case "hard":
                letterSelection = randomiser.nextInt(5);
                break;

        }

        int[] combination = gameCombinations.get(letterSelection);

        switch (difficulty) {

            case "easy":
                choppedUpWord[combination[0]] = encipher(choppedUpWord[combination[0]], steps);
                choppedUpWord[combination[1]] = encipher(choppedUpWord[combination[1]], steps);
                break;

            case "hard":
                choppedUpWord[combination[0]] = encipher(choppedUpWord[combination[0]], steps);
                choppedUpWord[combination[1]] = encipher(choppedUpWord[combination[1]], steps);
                choppedUpWord[combination[2]] = encipher(choppedUpWord[combination[2]], steps);
                choppedUpWord[combination[3]] = encipher(choppedUpWord[combination[3]], steps);
                break;

            case "impossible":
                choppedUpWord[combination[0]] = encipher(choppedUpWord[combination[0]], steps);
                choppedUpWord[combination[1]] = encipher(choppedUpWord[combination[1]], steps);
                choppedUpWord[combination[2]] = encipher(choppedUpWord[combination[2]], steps);
                choppedUpWord[combination[3]] = encipher(choppedUpWord[combination[3]], steps);
                choppedUpWord[combination[4]] = encipher(choppedUpWord[combination[4]], steps);
                break;

            default:    // normal is always the default case
                choppedUpWord[combination[0]] = encipher(choppedUpWord[combination[0]], steps);
                choppedUpWord[combination[1]] = encipher(choppedUpWord[combination[1]], steps);
                choppedUpWord[combination[2]] = encipher(choppedUpWord[combination[2]], steps);
                break;

        }

        String wordToGuess = new String(choppedUpWord);

        // change the text on the app for a new game; get user to guess word
        word_desc.setText(R.string.word_desc);
        target_word.setText(wordToGuess);
        action_prompt.setText(R.string.action_prompt);

        upper_left.setText(selectedGame[0]);
        upper_right.setText(selectedGame[1]);
        lower_left.setText(selectedGame[2]);
        lower_right.setText(selectedGame[3]);

        return selectedWord;
    }

    private char encipher(char letter, int steps) {
        int letterToProcess = ((int) letter) - CONVERT_LETTER;
        int applySteps = (letterToProcess + steps) % ALPHABET;

        return (char) (applySteps + CONVERT_LETTER);
    }

    private boolean checkAnswer(String buttonText, String roundAnswer,
                                MediaPlayer beep, MediaPlayer bleep) {

        if (buttonText.equals(roundAnswer)) {
            // then the answer is correct: update score, multiplier and start new game
            pScore += baseScore + (pScore * pMultiplier);
            score.setText(Integer.toString(pScore));

            pMultiplier = ((pMultiplier * 100) + 1) / 100;
            multiplier.setText(Float.toString(pMultiplier));

            selectedWord = newGame();

            if (randomiser.nextBoolean())
                beep.start();
            else
                bleep.start();

            return true;
        }

        // otherwise the function reaches here, which means the answer was wrong
        return false;
    }

    private void nextActivity(String finalScore) {
        Intent gameOver = new Intent(Game.this, GameOver.class);
        gameOver.putExtra(GameOver.FINAL_SCORE, finalScore);
        startActivity(gameOver);
    }

    private void gameLost(MediaPlayer buzz) {
        pLives--;
        lives.setText(Integer.toString(pLives));
        pMultiplier = 0;
        multiplier.setText(Float.toString(pMultiplier));
        if (pLives != 0)
            buzz.start();
    }

    private int calculateBaseScore(String difficulty) {

        switch (difficulty) {

            case "easy":
                return 25;

            case "hard":
                return 150;

            case "impossible":
                return 250;

            default:        // default = normal difficulty
                return 75;
        }

    }
}

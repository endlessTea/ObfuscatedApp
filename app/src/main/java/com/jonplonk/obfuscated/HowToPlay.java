package com.jonplonk.obfuscated;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;


public class HowToPlay extends ActionBarActivity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int demoCount = 0;
    private Target t1, t2, t3, t4, t5, t6;

    TextView score, multiplier, lives, target_word;
    ImageView heart;
    Button upper_left, lower_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        score = (TextView) findViewById(R.id.score);
        multiplier = (TextView) findViewById(R.id.multiplier);
        lives = (TextView) findViewById(R.id.lives);
        target_word = (TextView) findViewById(R.id.target_word);

        upper_left = (Button) findViewById(R.id.upper_left);
        lower_right = (Button) findViewById(R.id.lower_right);

        heart = (ImageView) findViewById(R.id.heart_image);

        // http://stackoverflow.com/questions/8381514/android-converting-color-image-to-grayscale
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        heart.setColorFilter(filter);

        t1 = new ViewTarget(R.id.target_word, this);
        t2 = new ViewTarget(R.id.upper_left, this);
        t3 = new ViewTarget(R.id.lower_right, this);
        t4 = new ViewTarget(R.id.score, this);
        t5 = new ViewTarget(R.id.multiplier, this);
        t6 = new ViewTarget(R.id.lives, this);

        // https://github.com/amlcurran/ShowcaseView/blob/master/sample/src/main/java/com/github/
        // amlcurran/showcaseview/sample/SampleActivity.java
        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin + 750);

        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(Target.NONE)
                .setOnClickListener(this)
                .setContentTitle("TUTORIAL")
                .setContentText("Press 'NEXT' to learn about\n" +
                        "the features of the game.")
                .setStyle(R.style.DemoStyle)
                .build();
        showcaseView.setButtonPosition(lps);
        showcaseView.setButtonText("Next");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_how_to_play, menu);
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

    @Override
    public void onClick(View v) {

        switch(demoCount) {

            case 0:
                target_word.setTextColor(0xffffffff);
                showcaseView.setShowcase(t1, true);
                showcaseView.setContentTitle("THE OBFUSCATED WORD");
                showcaseView.setContentText("Here's a word that has had 3 of its letters "+
                        "rotated by a random number of places. In this case, " +
                        "the last three letters were selected and then rotated " +
                        "by 11 places. \n" +
                        "C becomes N, O becomes Z, A becomes L.");
                break;

            case 1:
                target_word.setTextColor(0xd1000000);
                upper_left.setBackgroundColor(0xff81faff);
                showcaseView.setShowcase(t2, true);
                showcaseView.setContentTitle("CHOOSE AN ANSWER");
                showcaseView.setContentText("You need to use your reasoning skills to decide " +
                        "which of four choices was the original word. \n" +
                        "This takes practice, so if \nyou're new to the game you\n" +
                        "may wish to start on Easy Mode!");
                break;

            case 2:
                upper_left.setBackgroundColor(0x86000000);
                lower_right.setBackgroundColor(0xffff958e);
                showcaseView.setShowcase(t3, true);
                showcaseView.setContentTitle("CORRECT/WRONG ANSWERS");
                showcaseView.setContentText("Cocoa is the right answer, so you would tap this \n" +
                        "tile. You'll hear a 'BEEP', because it is a correct answer.\n" +
                        "A BUZZER will sound if \nyou select an incorrect answer.\n");
                break;

            case 3:
                lower_right.setBackgroundColor(0x86000000);
                score.setTextColor(0xffffffff);
                showcaseView.setShowcase(t4, true);
                showcaseView.setContentTitle("POINTS?");
                showcaseView.setContentText("Depending on the difficulty level you choose, the " +
                        "game will grant you an initial amount of points for every correct " +
                        "answer:\n" +
                        " * Easy Mode: 25 points\n" +
                        " * Medium Mode: 75 points\n" +
                        " * Hard Mode: 150 points\n" +
                        " * Impossible: 250 points");
                break;

            case 4:
                score.setTextColor(0xd1000000);
                multiplier.setTextColor(0xffffffff);
                showcaseView.setShowcase(t5, true);
                showcaseView.setContentTitle("BONUS POINTS?!");
                showcaseView.setContentText("Obfuscated will reward you for consecutive correct " +
                        "answers! Your MULTIPLIER BONUS will be applied " +
                        "to the next correct answer and it will increase by 0.01. " +
                        "If you guess incorrectly, your streak is broken and you lose your " +
                        "multiplier bonus.");
                break;

            case 5:
                multiplier.setTextColor(0xd1000000);
                lives.setTextColor(0xffffffff);
                showcaseView.setShowcase(t6, true);
                showcaseView.setContentTitle("DON'T LOSE YOUR LIVES");
                showcaseView.setContentText("Be careful, you only get 3 lives - every wrong " +
                        "answer will cause you to lose a life. When you " +
                        "lose all your lives, the game is over and you " +
                        "will be presented with your final score.\n\n" +
                        "PRESS 'NEXT' TO START PLAYING!");
                break;

            case 6:
                showcaseView.hide();
                Intent pickDifficulty = new Intent(HowToPlay.this, SelectDifficulty.class);
                startActivity(pickDifficulty);
                break;
        }

        demoCount++;

    }
}

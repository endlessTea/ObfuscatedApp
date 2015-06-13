package com.jonplonk.obfuscated;

        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;


public class GameOver extends ActionBarActivity {

    public static String FINAL_SCORE = "default";

    TextView scoreDisplay;
    Button replay, exitB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        final MediaPlayer airhorn = MediaPlayer.create(GameOver.this, R.raw.airhorn);
        airhorn.start();

        scoreDisplay = (TextView) findViewById(R.id.final_score);
        Intent endGame = getIntent();
        String finalScore = endGame.getStringExtra(FINAL_SCORE);
        scoreDisplay.setText(finalScore);

        replay = (Button) findViewById(R.id.replay_button);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickDifficulty = new Intent(GameOver.this, SelectDifficulty.class);
                startActivity(pickDifficulty);
            }
        });

        exitB = (Button) findViewById(R.id.exit_button);
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://stackoverflow.com/questions/3226495/android-exit-application-code
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
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
}

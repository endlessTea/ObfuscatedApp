package com.jonplonk.obfuscated;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SelectDifficulty extends ActionBarActivity {

    Button easy_b, med_b, hard_b, imp_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);

        easy_b = (Button) findViewById(R.id.easy_button);
        easy_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("easy");
            }
        });

        med_b = (Button) findViewById(R.id.medium_button);
        med_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("normal");
            }
        });

        hard_b = (Button) findViewById(R.id.hard_button);
        hard_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("hard");
            }
        });

        imp_b = (Button) findViewById(R.id.impossible_button);
        imp_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("impossible");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_difficulty, menu);
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

    private void nextActivity(String difficulty) {
        Intent gameBegin = new Intent(SelectDifficulty.this, Game.class);
        gameBegin.putExtra(Game.DIFFICULTY, difficulty);
        startActivity(gameBegin);
    }
}

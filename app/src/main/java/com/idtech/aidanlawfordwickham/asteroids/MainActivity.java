package com.idtech.aidanlawfordwickham.asteroids;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

// class notes: https://docs.google.com/document/d/e/2PACX-1vSGYH9guqE6TafBoxijLtcepgrxoWj9alalwik9vcH0kEK55rXxEKeGH-RIVJSrTjmZPq5a4gK2WH7_/pub

public class MainActivity extends Activity {

    int score;
    int newHighscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
    }

    public void setGameOverView(final int s, final int highScore) {

        score = s;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                if(score > highScore) {
                    editor.putInt("Saved Highscore", score);
                    editor.apply();
                    newHighscore = score;
                }
                int highestScore = sharedPref.getInt("Saved Highscore", score);
                GameView gameView = (GameView)findViewById(R.id.gameView);
                gameView.setEnabled(false);
                TextView scoreTextView = (TextView)findViewById(R.id.scoreTextView);
                scoreTextView.setText("Your score: " + score);
                TextView highScoreTextView = (TextView)findViewById(R.id.highScoreTextView);
                highScoreTextView.setText("High score: " + newHighscore);
                LinearLayout scoreLayout = (LinearLayout)findViewById(R.id.scoreLayout);
                scoreLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void restartGame(View view) {
        // Hide Score UI
        LinearLayout scoreLayout = (LinearLayout)findViewById(R.id.scoreLayout);
        scoreLayout.setVisibility(View.GONE);
        GameView gameView = (GameView)findViewById(R.id.gameView);
        // gameView.resetGame(); // OR whatever you need to do to reset the game
        gameView.setgameOver(false);
        gameView.setEnabled(true);
    }

    public void mainMenu(View view) {
        onBackPressed();
    }
}

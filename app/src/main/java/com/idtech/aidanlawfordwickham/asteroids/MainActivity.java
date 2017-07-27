package com.idtech.aidanlawfordwickham.asteroids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
    }

    public void setGameOverView(int s, final int highScore) {
        score = s;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GameView gameView = (GameView)findViewById(R.id.gameView);
                gameView.setEnabled(false);
                TextView scoreTextView = (TextView)findViewById(R.id.scoreTextView);
                scoreTextView.setText("Your score: " + score);
                TextView highScoreTextView = (TextView)findViewById(R.id.highScoreTextView);
                highScoreTextView.setText("High score: " + highScore);
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

package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int scoreA;
    private int scoreB;
    private TextView textViewScoreA;
    private TextView textViewScoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = 0;
        scoreB = 0;

        textViewScoreA = (TextView) findViewById(R.id.text_view_score_a);
        textViewScoreB = (TextView) findViewById(R.id.text_view_score_b);

        Button buttonOneA = (Button) findViewById(R.id.button_one_a);
        Button buttonTwoA = (Button) findViewById(R.id.button_two_a);
        Button buttonThreeA = (Button) findViewById(R.id.button_three_a);
        Button buttonSixA = (Button) findViewById(R.id.button_six_a);

        Button buttonOneB = (Button) findViewById(R.id.button_one_b);
        Button buttonTwoB = (Button) findViewById(R.id.button_two_b);
        Button buttonThreeB = (Button) findViewById(R.id.button_three_b);
        Button buttonSixB = (Button) findViewById(R.id.button_six_b);

        Button reset = (Button) findViewById(R.id.button_reset);

        buttonOneA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTeamAIncrement(1);
            }
        });

        buttonTwoA.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamAIncrement(2);
            }
        }));

        buttonThreeA.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamAIncrement(3);
            }
        }));

        buttonSixA.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamAIncrement(6);
            }
        }));

        buttonOneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTeamBIncrement(1);
            }
        });

        buttonTwoB.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamBIncrement(2);
            }
        }));

        buttonThreeB.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamBIncrement(3);
            }
        }));

        buttonSixB.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                handleTeamBIncrement(6);
            }
        }));

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                reset();
            }
        });

        initScores();
    }

    private void handleTeamAIncrement(int incrementStep) {
        scoreA = handleClickOnIncrementButton(scoreA, incrementStep, textViewScoreA);
    }

    private void handleTeamBIncrement(int incrementStep) {
        scoreB = handleClickOnIncrementButton(scoreB, incrementStep, textViewScoreB);
    }

    private int handleClickOnIncrementButton(int scoreProperty, int incrementStep, TextView scoreTextView) {
        scoreProperty += incrementStep;
        scoreTextView.setText("" + scoreProperty);

        return scoreProperty;
    }

    private void reset() {
        initScores();

        textViewScoreA.setText("" + scoreA);
        textViewScoreB.setText("" + scoreB);
    }

    private void initScores() {
        scoreB = 0;
        scoreA = 0;
    }
}

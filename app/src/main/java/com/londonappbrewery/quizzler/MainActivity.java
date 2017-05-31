package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.onClick;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends Activity {




    // TODO: Declare member variables here:
    Button mtrue_button;
    Button mfalse_button;
    TextView mQuestion_view;
    int mQuestion;
    int mIndex;
    int mScore;
    TextView mScoreTextView;
    ProgressBar mProgressBar;




    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreTextView = (TextView) findViewById(R.id.score);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score:"+mScore+"/"+mQuestionBank.length);

        }
        else{
            mScore = 0;
            mIndex = 0;

        }

        mtrue_button = (Button) findViewById(R.id.true_button);
        mfalse_button = (Button) findViewById(R.id.false_button);
        mQuestion_view = (TextView) findViewById(R.id.question_text_view);
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestion_view.setText(mQuestion);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mtrue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                updateNewQuestion();

            }
        });
        mfalse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
                updateNewQuestion();

            }
        });

//        TrueFalse newQuestion = new TrueFalse(R.string.question_1, true);



    }

    public void updateNewQuestion(){
        mIndex = (mIndex+1) % mQuestionBank.length;

        if(mIndex == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You scored " +mScore + " points!" );
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestion_view.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score:"+mScore+"/"+mQuestionBank.length);

    }

    public void checkAnswer(boolean userAnswer){
        Boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(userAnswer == correctAnswer)
        {   mScore = mScore+1;
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}

package com.londonappbrewery.quizzler;

/**
 * Created by geethikatripuramallu on 5/30/17.
 */

public class TrueFalse {
    private int mQuestionId;
    private boolean mAnswer;
    public TrueFalse(int questionResourceId, Boolean trueOrFalse){
        mQuestionId = questionResourceId;
        mAnswer = trueOrFalse;

    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}

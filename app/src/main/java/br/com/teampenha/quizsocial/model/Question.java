package br.com.teampenha.quizsocial.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Question implements Parcelable {
    private AlternativeQuestion mAlternativeOne;
    private AlternativeQuestion mAlternativeTwo;
    private QuestionFactor mFactor;

    public Question(AlternativeQuestion one, AlternativeQuestion two) {
        mAlternativeOne = one;
        mAlternativeTwo = two;
        mFactor = generateQuestionFactor();
    }

    public AlternativeQuestion getAlternativeOne() {
        return mAlternativeOne;
    }

    public AlternativeQuestion getAlternativeTwo() {
        return mAlternativeTwo;
    }

    private QuestionFactor generateQuestionFactor() {
        final int randomInt = new Random().nextInt(2);
        QuestionFactor factor;
        if (randomInt == 1) {
            factor = new GreaterFactor(mAlternativeOne, mAlternativeTwo);
        } else {
            factor = new LessFactor(mAlternativeOne, mAlternativeOne);
        }

        return factor;
    }

    public QuestionFactor getQuestionFactor() {
        return mFactor;
    }

    protected Question(Parcel in) {
        mAlternativeOne = in.readParcelable(AlternativeQuestion.class.getClassLoader());
        mAlternativeTwo = in.readParcelable(AlternativeQuestion.class.getClassLoader());
        mFactor = generateQuestionFactor();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mAlternativeOne, flags);
        dest.writeParcelable(mAlternativeTwo, flags);
    }
}

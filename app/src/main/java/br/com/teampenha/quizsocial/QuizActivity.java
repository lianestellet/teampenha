package br.com.teampenha.quizsocial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.teampenha.quizsocial.model.AlternativeQuestion;
import br.com.teampenha.quizsocial.model.Question;
import br.com.teampenha.quizsocial.mvp.MvpQuizPresenter;
import br.com.teampenha.quizsocial.mvp.MvpQuizView;
import br.com.teampenha.quizsocial.presenter.QuizPresenter;

public class QuizActivity extends AppCompatActivity implements MvpQuizView {

    public static final String QUESTION_BUNDLE_KEY = "questions_bundle";

    private TextView mAlternativeOneTextView;
    private TextView mAlternativeTwoTextView;

    private TextView mTitle;

    private MvpQuizPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        View layoutTitle = findViewById(R.id.layout_title);
        View layoutOne = findViewById(R.id.layout_question_one);
        View layoutTwo = findViewById(R.id.layout_question_two);

        mTitle = (TextView) layoutTitle.findViewById(R.id.txt_title);
        mAlternativeOneTextView = (TextView) layoutOne.findViewById(R.id.txt_question);
        mAlternativeTwoTextView = (TextView) layoutTwo.findViewById(R.id.txt_question);

        mAlternativeOneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isCorrectAnswer(1);
            }
        });

        mAlternativeTwoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isCorrectAnswer(2);
            }
        });

        MvpQuizView view = this;
        ArrayList<Question> questions = getIntent().getParcelableArrayListExtra(QUESTION_BUNDLE_KEY);

        mPresenter = new QuizPresenter(view, questions);
        mPresenter.startQuiz();
    }

    @Override
    public void setAlternativeOne(AlternativeQuestion alternative) {
        mAlternativeOneTextView.setText(alternative.getDescription());
    }

    @Override
    public void setAlternativeTwo(AlternativeQuestion alternative) {
        mAlternativeTwoTextView.setText(alternative.getDescription());
    }

    @Override
    public void setGreatherQuestionTitle() {
        mTitle.setText(R.string.greather_than);
    }

    @Override
    public void setLessQuestionTitle() {
        mTitle.setText(R.string.less_than);
    }


    @Override
    public void animateAnswer() {
        mPresenter.nextQuestion();
    }

    @Override
    public void finishQuiz() {
        Context context = this;
        Intent it = new Intent(context, ResultActivity.class);
        startActivity(it);
    }

    @Override
    public void showGameOver() {
        // TODO finalizar o jogo
        Toast.makeText(this, "Vocë perdeu todas as vidas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateRemaingLives(int lives) {
        // TODO update nas vidas o jogo
        Toast.makeText(this, String.format("Vocë tem %d vidas", lives), Toast.LENGTH_SHORT).show();
    }
}

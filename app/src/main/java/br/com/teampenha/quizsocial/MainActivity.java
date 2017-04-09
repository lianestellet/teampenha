package br.com.teampenha.quizsocial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.teampenha.quizsocial.model.Question;
import br.com.teampenha.quizsocial.mvp.MvpMainPresenter;
import br.com.teampenha.quizsocial.mvp.MvpMainView;
import br.com.teampenha.quizsocial.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MvpMainView {

    private MvpMainPresenter mPresenter;

    private TextView mLastPointsTextView;

    private Button mStartNewQuizButton;

    private View mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartNewQuizButton = (Button) findViewById(R.id.btn_start_quiz);
        mLastPointsTextView = (TextView) findViewById(R.id.txt_last_score);
        mLoadingView = findViewById(R.id.linear_progress);

        MvpMainView mvpView = this;
        mPresenter = new MainPresenter(mvpView);
        mPresenter.initMainScreen();

        mStartNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startNewQuiz();
            }
        });
    }

    @Override
    public void showLastPoints(int points) {
        String formatedPoints = String.format(getString(R.string.last_points_format), points);
        mLastPointsTextView.setText(formatedPoints);
    }

    @Override
    public void showProgressBar() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mLoadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enableNextButton() {
        mStartNewQuizButton.setEnabled(true);
    }

    @Override
    public void disableNextButton() {
        mStartNewQuizButton.setEnabled(false);
    }

    @Override
    public void startNewQuiz(ArrayList<Question> questions) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(QuizActivity.QUESTION_BUNDLE_KEY, questions);

        Context context = this;
        Intent it = new Intent(context, QuizActivity.class);
        it.putExtras(bundle);

        startActivity(it);
    }

    @Override
    public void logOffFacebook() {

    }
}

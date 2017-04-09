package br.com.teampenha.quizsocial;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                new QuestionTask().execute();
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

    public void goQuiz(View v) {
        Intent i = new Intent(this, ResultActivity.class);
        startActivity(i);
    }

    private class QuestionTask extends AsyncTask<Void, Void, ArrayList<Question>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPresenter.startLoadingProcess();
        }

        @Override
        protected ArrayList<Question> doInBackground(Void... params) {

            final String womanJson = readFileFromRawDirectory(R.raw.woman_questions);
            final String generalJson = readFileFromRawDirectory(R.raw.general_questions);

            try {
                return mPresenter.loadQuestions(womanJson, generalJson);
            } catch (IOException e) {
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Question> questions) {
            super.onPostExecute(questions);
            mPresenter.startNewQuiz(questions);
        }
    }

    private String readFileFromRawDirectory(int resourceId){
        InputStream iStream = getResources().openRawResource(resourceId);
        InputStreamReader inputReader = new InputStreamReader(iStream);
        BufferedReader bufferedReader = new BufferedReader(inputReader);

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while ( (line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.hideProgressBar();
    }
}

package br.com.teampenha.quizsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.teampenha.quizsocial.presenter.ResultPresenter;

public class ResultActivity extends AppCompatActivity {

    private TextView txtPoints, txtCompartilhar, txtDesafio, txtJogarNovamente;
    private ResultPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        this.ini();

        txtPoints.setText(mPresenter.getPoint());
    }
    public void ini() {
        txtPoints = (TextView) findViewById(R.id.content_points);
        txtCompartilhar = (TextView) findViewById(R.id.compartilhar);
        txtDesafio = (TextView) findViewById(R.id.desafiar);
        txtJogarNovamente = (TextView) findViewById(R.id.jogar_novamente);

        mPresenter = new ResultPresenter();
    }
}

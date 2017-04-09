package br.com.teampenha.quizsocial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import br.com.teampenha.quizsocial.presenter.ResultPresenter;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtPoints, txtCompartilhar, txtDesafio, txtJogarNovamente;
    private Button btFinish;
    private ResultPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://www.agenciapatriciagalvao.org.br/dossie/pesquisas/mapa-da-violencia-2015-homicidio-de-mulheres-no-brasil-flacsoopas-omsonu-mulheresspm-2015/"))
                .setContentDescription("Assassinato de mulheres por familiares")
                .build();

        ShareButton shareButton = (ShareButton)findViewById(R.id.shareButton);
        shareButton.setShareContent(content);

        this.ini();

        txtPoints.setText(mPresenter.getPoint());
    }
    public void ini() {
        txtPoints = (TextView) findViewById(R.id.content_points);
        txtJogarNovamente = (TextView) findViewById(R.id.jogar_novamente);
        btFinish = (Button) findViewById(R.id.btn_finish);

        btFinish.setOnClickListener(this);
        txtJogarNovamente.setOnClickListener(this);

        mPresenter = new ResultPresenter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_finish:
                finish();
                break;
            case R.id.jogar_novamente:
                finish();
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
    }
}

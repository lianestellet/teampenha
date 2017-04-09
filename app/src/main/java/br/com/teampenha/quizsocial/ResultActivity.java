package br.com.teampenha.quizsocial;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import br.com.teampenha.quizsocial.presenter.ResultPresenter;

public class ResultActivity extends AppCompatActivity {

    private TextView txtPoints, txtCompartilhar, txtDesafio, txtJogarNovamente;
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
        txtCompartilhar = (TextView) findViewById(R.id.compartilhar);
        txtDesafio = (TextView) findViewById(R.id.desafiar);
        txtJogarNovamente = (TextView) findViewById(R.id.jogar_novamente);

        mPresenter = new ResultPresenter();
    }
}

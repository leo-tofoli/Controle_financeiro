package br.projeto.controlefinanceiro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AprendaActivity extends AppCompatActivity {
    private static final String TAG = "CadastroUsuarioActivity";
    private WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprenda);

        view = (WebView) findViewById(R.id.webView);
        view.setWebChromeClient(new WebChromeClient());
        view.getSettings().setJavaScriptEnabled(true);
        String url ="file:///android_asset/aprena.html";
        view.loadUrl(url);

        BottomNavigationView navigation = findViewById(R.id.bottom_navegation);
        navigation.setSelectedItemId(R.id.aprenda);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.lancamento:
                        startActivity(new Intent(getApplicationContext(), ListaLancamentoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.aprenda:
                        return true;
                }
                return false;
            }
        });

    }
}

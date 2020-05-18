package br.projeto.controlefinanceiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetalhesLancamentoActivity extends AppCompatActivity {
    private Lancamento lancamento;
    private TextView textViewDescricao;
    private TextView textViewValor;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_lancamento);
        Intent intent = getIntent();
        lancamento = (Lancamento)intent.getSerializableExtra("lancamento");

        textViewDescricao = findViewById(R.id.textViewDescricaoValue);
        textViewValor = findViewById(R.id.textViewValorValue);
        textViewData = findViewById(R.id.textViewDataValue);

        textViewDescricao.setText(lancamento.getDescricao());
        textViewValor.setText(lancamento.getValor().toString());
        textViewData.setText(lancamento.getData());
    }

    public void deletarLancamento(View view) {
        Intent intent = new Intent(this, CadastroLancamentoActivity.class);
        intent.putExtra("excluir",lancamento);
        startActivity(intent);
        finish();
    }

    public void alterarLancamento(View view) {
        Intent intent = new Intent(this, CadastroLancamentoActivity.class);
        intent.putExtra("lancamento",lancamento);
        startActivity(intent);
        finish();
    }

    public void fecharTela(View view) {
        finish();
    }
}

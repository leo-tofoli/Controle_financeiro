package br.projeto.controlefinanceiro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CadastroLancamentoActivity extends AppCompatActivity {
    private static final String TAG = "CadastroLancamentoActivity";
    private TextInputEditText descricao;
    private TextInputEditText valor;
    private TextInputEditText data;
    private AppDatabase db;

    private Lancamento lancamentoAlt;
    private boolean alterar = false;
    private Button botaoAlterar;
    private Button botaoExcluir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lancamento);
        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();

        descricao = (TextInputEditText)findViewById(R.id.cadDescricaoEditTextInput);
        valor = (TextInputEditText)findViewById(R.id.cadValorEditTextInput);
        data = (TextInputEditText)findViewById(R.id.cadDataEditTextInput);
        botaoAlterar = findViewById(R.id.buttonAlterar);
        botaoExcluir = findViewById(R.id.buttonDeletar);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("lancamento")){
            lancamentoAlt = (Lancamento)intent.getSerializableExtra("lancamento");
            alterar = true;
            botaoAlterar.setText("Alterar Registro");
            preecherCampos();
        }
        //else{
        //    lancamentoAlt = (Lancamento)intent.getSerializableExtra("excluir");
        //    botaoExcluir.setText("Excluir Registro");
        //    preecherCampos();
        //    deletarSenha();
        //}

    }

    private void preecherCampos(){
        descricao.setText(lancamentoAlt.getDescricao());
        valor.setText(lancamentoAlt.getValor().toString());
        data.setText(lancamentoAlt.getData());
    }

    public void cadastrarLancamento(View view) {
        if(alterar){
            lancamentoAlt.setDescricao(descricao.getText().toString());
            lancamentoAlt.setValor(Double.parseDouble(valor.getText().toString()));
            lancamentoAlt.setData(data.getText().toString());

            new AlterarSenhaAsyncTask().execute(lancamentoAlt);

        }else{
            Lancamento lancamento = new Lancamento(
                    data.getText().toString(),
                    Double.parseDouble(valor.getText().toString()),
                    descricao.getText().toString()
            );

            new SaveLancamentoAsyncTask().execute(lancamento);
        }
    }

    public void deletarSenha(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir")
                .setMessage("Deseja deletar esse registro")
                .setNegativeButton("Nao", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeletarSenhaAsyncTask().execute(lancamentoAlt);
                    }
                });

    }

    private class SaveLancamentoAsyncTask extends AsyncTask<Lancamento, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Lancamento... lancamentos) {
            db.lancamentoDao().insertAll(lancamentos);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroLancamentoActivity.this,"Salvo com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class AlterarSenhaAsyncTask extends AsyncTask<Lancamento, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(Lancamento... lancamentos) {
            db.lancamentoDao().updateSenha(lancamentos[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroLancamentoActivity.this,"Alterada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class DeletarSenhaAsyncTask extends AsyncTask<Lancamento, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(Lancamento... lancamentos) {
            db.lancamentoDao().delete(lancamentos[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroLancamentoActivity.this,"Excluido com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}


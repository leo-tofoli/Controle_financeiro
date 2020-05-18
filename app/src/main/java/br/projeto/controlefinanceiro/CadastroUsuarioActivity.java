package br.projeto.controlefinanceiro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastroUsuarioActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText pergunta;
    private TextInputEditText resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        usuario = (TextInputEditText)findViewById(R.id.usuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.senhaEditTextInput);
        pergunta = (TextInputEditText)findViewById(R.id.perguntaEditTextInput);
        resposta = (TextInputEditText)findViewById(R.id.respostaEditTextInput);
        Hawk.init(this).build();
    }

    public void cadastrarUsuario(View view){
        // TODO fazer o cadastro
        Log.d(TAG,"Clicou no fazer cadastro!");

        Hawk.put("usuario", usuario.getText().toString());
        Hawk.put("senha", senha.getText().toString());
        Hawk.put("pergunta", pergunta.getText().toString());
        Hawk.put("resposta", resposta.getText().toString());

        Toast.makeText(this,"Cadastro Realizado com Sucesso",Toast.LENGTH_LONG).show();

        finish();
    }
}

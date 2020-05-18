package br.projeto.controlefinanceiro;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaLancamentoActivity extends AppCompatActivity {

    private static final String TAG = "ListaLancamentoActivity";
    private List<Lancamento> listaLancamento;
    private ListView lancamentoListView;
    private ArrayAdapter <Lancamento> adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lancamento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();

        //listas
        listaLancamento = geraLista();
        lancamentoListView = findViewById(R.id.lancamentoListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLancamento);
        lancamentoListView.setAdapter(adapter);
        lancamentoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lancamento lancamentoSelecionada = listaLancamento.get(position);
                Toast.makeText(ListaLancamentoActivity.this,"Lancamento selecionada: "
                        + lancamentoSelecionada.getDescricao(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListaLancamentoActivity.this, DetalhesLancamentoActivity.class);
                intent.putExtra("lancamento", lancamentoSelecionada);
                startActivity(intent);
            }
        });

        //Navigation
        BottomNavigationView navigation = findViewById(R.id.bottom_navegation);
        navigation.setSelectedItemId(R.id.lancamento);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.lancamento:
                        return true;
                    case R.id.aprenda:
                        startActivity(new Intent(getApplicationContext(), AprendaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        listaLancamento = geraLista();
        if(adapter != null) {
            adapter.clear();
            adapter.addAll(listaLancamento);
            adapter.notifyDataSetChanged();
        }
    }

    public void adicionarLancamento(View v){
        Intent intent = new Intent(this,CadastroLancamentoActivity.class);
        startActivity(intent);
    }

    public List<Lancamento> geraLista(){
        List<Lancamento> lista = null;
        try {
            lista = new GetLancamentoAsyncTask().execute().get();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }


    private class GetLancamentoAsyncTask extends AsyncTask<Void, Void,List<Lancamento>>
    {
        @Override
        protected List<Lancamento> doInBackground(Void... url) {
            return db.lancamentoDao().getAll();
        }
    }

}

package br.projeto.controlefinanceiro;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Lancamento.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LancamentoDAO lancamentoDao();
}

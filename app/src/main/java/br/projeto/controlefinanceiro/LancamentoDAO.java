package br.projeto.controlefinanceiro;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LancamentoDAO {
    @Query("SELECT * FROM lancamento")
    List<Lancamento> getAll();

    @Insert
    void insertAll(Lancamento... lancamentos);

    @Update
    void updateSenha(Lancamento lancamentos);

    @Delete
    void delete(Lancamento lancamentos);
}

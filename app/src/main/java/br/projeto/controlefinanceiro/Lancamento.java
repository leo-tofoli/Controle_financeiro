package br.projeto.controlefinanceiro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Lancamento implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "Tipo")
    private String tipo;
    @ColumnInfo(name = "Categoria")
    private String categoria;
    @ColumnInfo(name = "Data")
    private String data;
    @ColumnInfo(name = "Valor")
    private Double valor;
    @ColumnInfo(name = "Descricao")
    private String descricao;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "descricao='" + descricao + '\'' +
                '}';
    }

    public Lancamento(String data, Double valor, String descricao) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }
    public Lancamento() {

    }
}

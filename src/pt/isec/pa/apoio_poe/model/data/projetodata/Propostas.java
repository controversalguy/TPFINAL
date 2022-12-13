package pt.isec.pa.apoio_poe.model.data.projetodata;

import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;

import java.io.Serializable;
import java.util.Objects;

public class Propostas implements Serializable,Cloneable  {
    private String codProposta;
    private String titulo;
    private long nrAluno;

    public Propostas(String codProposta, String titulo) {
        this.codProposta = codProposta;
        this.titulo = titulo;
    }

    public Propostas( String codProposta, String titulo,long nrAluno) {
        this.codProposta = codProposta;
        this.titulo = titulo;
        this.nrAluno = nrAluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodProposta() {
        return codProposta;
    }

    public void setCodProposta(String codProposta) {
        this.codProposta = codProposta;
    }

    public long getNrAluno() {
        return nrAluno;
    }

    public void setNrAluno(long nrAluno) {
        this.nrAluno = nrAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Propostas)) return false;
        Propostas propostas = (Propostas) o;
        return Objects.equals(codProposta, propostas.codProposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProposta);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Propostas newPropostas = (Propostas) super.clone();
            return newPropostas;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s,%s,",codProposta,titulo));
        return sb.toString();
    }
}

package pt.isec.pa.apoio_poe.model.data.alunodata;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Aluno implements Serializable{

    private static long count = 0;
    private long id; // id do aluno- 2019133920
    private String nomeAluno;    //nome do aluno
    private String emailAluno;   // email do aluno
    private String siglaCurso;   // curso do aluno
    private String siglaRamo;    // ramo do aluno
    private double classificacao; // classificação final
    private boolean podeEstagio;   // possibilidade de estágio além de projetos

    public Aluno(long id, String nomeAluno, String emailAluno, String siglaCurso, String siglaRamo, double classificacao, boolean podeEstagio) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.emailAluno = emailAluno;
        this.siglaCurso = siglaCurso;
        this.siglaRamo = siglaRamo;
        this.classificacao = classificacao;
        this.podeEstagio = podeEstagio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getSiglaCurso() {
        return siglaCurso;
    }

    public void setSiglaCurso(String siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    public String getSiglaRamo() {
        return siglaRamo;
    }

    public void setSiglaRamo(String siglaRamo) {
        this.siglaRamo = siglaRamo;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean getAcesso() {
        return podeEstagio;
    }

    public void setPodeEstagio(boolean podeEstagio) {
        this.podeEstagio = podeEstagio;
    }

    public void setNome(String newNome) {
        nomeAluno = newNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Aluno)) return false;
        Aluno aluno = (Aluno) o;
        return id == aluno.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Aluno newAluno = (Aluno) super.clone();
            return newAluno;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d,%s,%s,%s,%s,",id,nomeAluno,emailAluno,siglaCurso,siglaRamo));
        sb.append(String.format("%.3f", classificacao).replaceAll(",","."));
        sb.append(String.format(",%b",podeEstagio));
        sb.append('\n');
        return sb.toString();
    }
}

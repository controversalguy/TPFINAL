package pt.isec.pa.apoio_poe.model.data.docentedata;


import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Docente implements Serializable,Cloneable {
    private String nomeDocente;
    private String emailDocente;

    public Docente(String nomeDocente, String emailDocente) {
        this.nomeDocente = nomeDocente;
        this.emailDocente = emailDocente;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        System.out.println("docente"+emailDocente);
        this.emailDocente = emailDocente;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Aluno)) return false;
        Docente docente = (Docente) o;
        return Objects.equals(emailDocente, docente.getEmailDocente());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Docente newDocente = (Docente) super.clone();
            return newDocente;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(emailDocente);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s,%s",nomeDocente,emailDocente));
        sb.append('\n');
        return sb.toString();
    }

}

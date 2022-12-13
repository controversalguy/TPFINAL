package pt.isec.pa.apoio_poe.model.data.candidaturadata;

import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// ver com o Ricas
public class Candidaturas implements Serializable,Cloneable{

    private long nrAluno;
    private List<String> candidaturas;


    public Candidaturas(long nrAluno, List<String> candidaturas) {
        this.nrAluno = nrAluno;
        this.candidaturas = candidaturas;
    }


    public long getNrAluno() {
        return nrAluno;
    }

    public void setNrAluno(long nrAluno) {
        this.nrAluno = nrAluno;
    }

    public List<String> getCandidaturas() {
        return candidaturas;
    }

    public int verificaCodCandidatura(String codCandidatura) {

        return candidaturas.indexOf(codCandidatura);
    }

    public void setCandidaturas(List<String> candidaturas) {
        this.candidaturas = candidaturas;
    }


    @Override
    public int hashCode() {
        return Objects.hash(nrAluno, candidaturas);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Candidaturas newCandidaturas = (Candidaturas) super.clone();
            return newCandidaturas;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String toString() { // ver isso com
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d,",nrAluno));
        for (int i = 0; i < candidaturas.size() ; i++) {
            sb.append(String.format("%s",candidaturas.get(i)));
            if(i!=candidaturas.size()-1)
                sb.append(",");
        }
        sb.append("\n");
        return sb.toString();
    }

        /*private static int idProposta = 0; // ver ids depois (está mal feito, rever)
    private List<Aluno> listaAutoProposto; // lista de quem já se autopropos
    private List<Aluno> listaAlunosCandidatos; // lista de quem já se candidatou
/*    private List<Aluno> listaAlunosCandidatos;*/

    // não são permitidos escolha de projetos/estágios que já estejam atribuidos a um aluno


}

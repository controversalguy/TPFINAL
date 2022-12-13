package pt.isec.pa.apoio_poe.model.data.projetodata;

import java.io.Serializable;

public class ProjetoAutoProposto extends Propostas implements Serializable {
    private long nrALuno;

    public ProjetoAutoProposto (String codProposta, String titulo, long nrAluno){
        super(codProposta, titulo,nrAluno);
        this.nrALuno = nrAluno;
    }

    public void setNrALuno(long nrALuno) {
        this.nrALuno = nrALuno;
    }

    public long getNrALuno() {
        return nrALuno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T3,");
        sb.append(super.toString());
        sb.append(String.format("%d",nrALuno));
        sb.append('\n');
        return sb.toString();
    }

// cada aluno só pode apresentar uma proposta
}

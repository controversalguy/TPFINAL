package pt.isec.pa.apoio_poe.model.data.projetodata;

import java.io.Serializable;

public class Projeto extends Propostas implements Serializable {
    private long nrAluno;
    private String siglaRamo; // RAS, DA, SI
    private String emailDocente;

    public Projeto( String codProposta,  String ramo,String titulo, String emailDocente) {
        super(codProposta, titulo);
        this.siglaRamo = ramo;
        this.emailDocente = emailDocente;
    }

    @Override
    public void setNrAluno(long nrAluno) {
        this.nrAluno = nrAluno;
    }

    public void setSiglaRamo(String siglaRamo) {
        this.siglaRamo = siglaRamo;
    }

    public String getSiglaRamo() {
        return siglaRamo;
    }

    public Projeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno) {
        super(codProposta, titulo,nrAluno);
        this.nrAluno = nrAluno;
        this.siglaRamo = ramo;
        this.emailDocente = emailDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(nrAluno != 0){
            sb.append("T2,");
            sb.append(super.toString());
            sb.append(String.format("%s,%s,%d",siglaRamo,emailDocente,nrAluno));
            sb.append('\n');
            return sb.toString();
        }
        sb.append("T2,");
        sb.append(super.toString());
        sb.append(String.format("%s,%s",siglaRamo,emailDocente));
        sb.append('\n');
        return sb.toString();
    }
}

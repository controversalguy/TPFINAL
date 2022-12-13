package pt.isec.pa.apoio_poe.model.data.projetodata;

import java.io.Serializable;

public class Estagio extends Propostas implements Serializable {
    private String siglaRamoEstagio; // Estágio direcionado para ramos da Informática (DA,SI,RAS)
    private String instituicaoEstagio; // Instituicao destino do estagio
    private long nrAluno;

    public Estagio(String codProposta, String ramo, String titulo, String instituicao) {
        super(codProposta, titulo);
        this.siglaRamoEstagio = ramo;
        this.instituicaoEstagio = instituicao;
    }

    // O Estágio poderá ter a indicação do aluno (nrAluno) ao qual deve ser atribuído
    public Estagio(String codProposta, String ramo, String titulo, String instituicao, long nrAluno) {
        super(codProposta, titulo,nrAluno);
        this.siglaRamoEstagio = ramo;
        this.instituicaoEstagio = instituicao;
        this.nrAluno = nrAluno;
    }

    public String getInstituicaoEstagio() {
        return instituicaoEstagio;
    }

    public void setSiglaRamoEstagio(String siglaRamoEstagio) {
        this.siglaRamoEstagio = siglaRamoEstagio;
    }

    public void setInstituicaoEstagio(String instituicaoEstagio) {
        this.instituicaoEstagio = instituicaoEstagio;
    }

    @Override
    public void setNrAluno(long nrAluno) {
        this.nrAluno = nrAluno;
    }

    public String getSiglaRamoEstagio() {
        return siglaRamoEstagio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(nrAluno != 0){
            sb.append("T1,");
            sb.append(super.toString());
            sb.append(String.format("%s,%s,%d",siglaRamoEstagio,instituicaoEstagio,nrAluno));
            sb.append('\n');
            return sb.toString();
        }
        sb.append("T1,");
        sb.append(super.toString());
        sb.append(String.format("%s,%s",siglaRamoEstagio,instituicaoEstagio));
        return sb.toString();
    }
}

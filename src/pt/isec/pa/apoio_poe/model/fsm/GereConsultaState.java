package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;
import java.util.Map;

public class GereConsultaState extends GestaoEstagioStateAdapter {
    protected GereConsultaState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public List<Aluno> listaAlunosComProposta() {
        return data.listaAlunosComProposta();
    }

    @Override
    public List<Aluno> listaAlunosComCandSemProp() {
        return data.listaAlunosComCandSemProp();
    }

    @Override
    public List<Propostas> propostasDisponiveis() {
        return data.listaPropostasDisp();
    }

    @Override
    public List<Propostas> propostasAtribuidas() {
        return data.listaPropostasAtribuidas();
    }
    @Override
    public List<String> getOrientadores(){
        return data.getOrientadores();
    }
    @Override
    public int getOrientacaoDocente(String doc){
        return data.getOrientacaoDocente(doc);
    }
    @Override
    public double getMediaOrientacao(){
        return data.getMediaOrientacao();
    }
    @Override
    public int getMinimoOrientacao(){
        return data.getMinimoOrientacao();
    }
    @Override
    public int getMaximaOrientacao(){
        return data.getMaximaOrientacao();
    }

    @Override
    public Map<Docente, List<String>> orientacoesDocente() {
        data.contaOrientacoes();
        return data.listanOrientacoes();
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.CONSULTA;
    }
}

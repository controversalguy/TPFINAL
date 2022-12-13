package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;
import java.util.Map;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class AtribuicaoOrientadorState extends GestaoEstagioStateAdapter {

    public AtribuicaoOrientadorState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public GestaoEstagioState getState() {
        return ATRIBUICAO_ORIENTADOR;
    }

    @Override
    public boolean atribuiProponentesProjeto() {
        return data.atribuiProponentesProjeto();
    }

    @Override
    public boolean atribuiProponenteManual(String cod,String email) {
        return data.atribuiProponenteManual(cod,email);
    }

    @Override
    public boolean removeProponenteManual(String cod, String email) {
        return data.removeProponenteManual(cod,email);
    }
    @Override
    public boolean alteraProponente(String cod,String email){
        return data.alteraProponente(cod,email);
    }

    @Override
    public Propostas getProposta(String text) {
        return data.getProposta(text);
    }

    @Override
    public List<Aluno> listaAlunosSemAtribuicao() {
        return data.listaAlunosSemAtribuicao();
    }

    @Override
    public List<Aluno> listaAlunoComOrientador() {
        return data.listaAlunoComOrientador();
    }

    @Override
    public List<Aluno> listaAlunoSemOrientador() {
        return data.listaAlunoSemOrientador();
    }

    @Override
    public Map<Docente, List<String>> listanOrientacoes() {
        data.contaOrientacoes();
        return data.listanOrientacoes();
    }
    @Override
    public List<Docente> consultaProponente(){
        return data.consultaProponente();
    }


    @Override
    public List<String> contaOrientacoes() {
        return data.contaOrientacoes();
    }

    @Override
    public void exportaCsvFase4(String filename) throws Exception {
        data.exportaCsvFase4(filename);
    }

    @Override
    public boolean fecharFaseOrientador() {
        estadoSeguinte(CONSULTA);
        return data.fecharFaseOrientador();
    }

    @Override
    public void regressarFaseAnterior() {
        if (!data.getPhaseLocks(2)) estadoSeguinte(ATRIBUICAO_PROPOSTA);
        else estadoSeguinte(ATRIBUICAO_PROPOSTA_LOCK);
    }

    @Override
    public void avancarFase() {
        estadoSeguinte(CONSULTA);
    }

}

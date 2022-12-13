package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class GereCandidaturaState extends GestaoEstagioStateAdapter {
    protected GereCandidaturaState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public boolean insereCandidatura(Long id, String propostas) {
        return data.insereCandidatura(id, propostas);
    }

    @Override
    public List<Candidaturas> consultaCandidaturas() {
        return data.listaCandidaturas();
    }


    @Override
    public List<Aluno> listaAutoproposta() {
        return data.listaAutoproposta();
    }

    @Override
    public List<Aluno> listaCandidaturas() {
        return data.listaComCandidaturas();
    }

    @Override
    public List<Aluno> listaSemCandidaturas() {
        return data.listaSemCandidaturas();
    }

    @Override
    public List<Propostas> listaAutopropostas() {
        estadoSeguinte(GestaoEstagioState.CANDIDATURA);
        return data.listaAutopropostas();
    }

    @Override
    public List<Propostas> listaProjetos() {
        estadoSeguinte(GestaoEstagioState.CANDIDATURA);
        return data.listaProjetos();
    }

    @Override
    public List<Propostas> listaPropComCand() {
        estadoSeguinte(GestaoEstagioState.CANDIDATURA);
        return data.listaPropComCand();
    }

    @Override
    public List<Propostas> listaPropSemCand() {
        estadoSeguinte(GestaoEstagioState.CANDIDATURA);
        return data.listaPropSemCand();
    }

    @Override
    public List<Aluno> listaAlunosSemAtribuicao() {
        return data.listaAlunosSemAtribuicao();
    }

    @Override
    public void exportaCsvCandidaturas(String filename) throws Exception {
        data.exportaCsvCandidaturas(filename);
    }

    @Override
    public void regressarFaseAnterior() {
        if (!data.getPhaseLocks(0)) estadoSeguinte(INICIA_CONFIG);
        else estadoSeguinte(INICIA_CONFIG_LOCK);
    }

    @Override
    public boolean fecharFaseCandidatura() {
        if (data.fecharFaseCandidatura()) {
            estadoSeguinte(CANDIDATURA_LOCK);
            return true;
        }
        return false;
    }

    @Override
    public void avancarFase() {
        if (!data.getPhaseLocks(2)) estadoSeguinte(ATRIBUICAO_PROPOSTA);
        else estadoSeguinte(ATRIBUICAO_PROPOSTA_LOCK);

    }

    @Override
    public boolean importaCandidaturas(String filename) throws Exception {
        return data.importaCandidaturas(filename);
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.CANDIDATURA;
    }
}

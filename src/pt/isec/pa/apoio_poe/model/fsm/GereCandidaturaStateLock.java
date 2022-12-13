package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;
import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.INICIA_CONFIG_LOCK;

public class GereCandidaturaStateLock extends GestaoEstagioStateAdapter {
    public GereCandidaturaStateLock(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public void avancarFase() {
        if (!data.getPhaseLocks(2)) estadoSeguinte(ATRIBUICAO_PROPOSTA);
        else estadoSeguinte(ATRIBUICAO_PROPOSTA_LOCK);

    }
    @Override
    public List<Candidaturas> listaCandidaturasLock() {
        return data.listaCandidaturas();
    }

    @Override
    public void regressarFaseAnterior() {
        if (!data.getPhaseLocks(0)) estadoSeguinte(INICIA_CONFIG);
        else estadoSeguinte(INICIA_CONFIG_LOCK);
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.CANDIDATURA_LOCK;
    }


}

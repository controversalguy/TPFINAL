package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class AtribuicaoPropostaStateLock extends GestaoEstagioStateAdapter {
    public AtribuicaoPropostaStateLock(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public void avancarFase() {
        estadoSeguinte(ATRIBUICAO_ORIENTADOR);
    }

    @Override
    public void regressarFaseAnterior() {
        if (!data.getPhaseLocks(1)) estadoSeguinte(CANDIDATURA);
        else estadoSeguinte(CANDIDATURA_LOCK);
    }

    @Override
    public List<Propostas> listaPropostasAtribuidas() {
        return data.listaPropostasAtribuidas();
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.ATRIBUICAO_PROPOSTA_LOCK;
    }

}

package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.EXPORTA_DADOS;
import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.INICIA_CONFIG;

public class ExportaDadosState extends GestaoEstagioStateAdapter {
    public ExportaDadosState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public void exportaCsvAlunos(String filename) throws Exception {
        data.exportaCsvAlunos(filename);
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public void exportaCsvDocentes(String filename) throws Exception {
        data.exportaCsvDocentes(filename);
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public void exportaCsvPropostas(String filename) throws Exception {
        data.exportaCsvPropostas(filename);
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public void regressarFaseAnterior() {
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public GestaoEstagioState getState() {
        return EXPORTA_DADOS;
    }
}

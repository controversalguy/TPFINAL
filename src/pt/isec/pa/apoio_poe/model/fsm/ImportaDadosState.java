package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;

public class ImportaDadosState extends GestaoEstagioStateAdapter {

    public ImportaDadosState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.IMPORTA_DADOS;
    }


    @Override
    public boolean importaPropostas(String filename) throws Exception {
        estadoSeguinte(GestaoEstagioState.INICIA_CONFIG);
        return data.importaPropostas(filename);
    }

    @Override
    public boolean importaAlunos(String filename) throws Exception {
        estadoSeguinte(GestaoEstagioState.INICIA_CONFIG);
        return data.importaAlunos(filename);
    }

    @Override
    public boolean importaDocentes(String filename) throws Exception {
        estadoSeguinte(GestaoEstagioState.INICIA_CONFIG);
        return data.importaDocentes(filename);
    }

    @Override
    public void regressarFaseAnterior() {
        estadoSeguinte(GestaoEstagioState.INICIA_CONFIG);
    }
}

package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class IniciaConfigStateLock extends GestaoEstagioStateAdapter{
    public IniciaConfigStateLock(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context,data);
    }

    @Override
    public void avancarFase() {
        if(!data.getPhaseLocks(1))estadoSeguinte(CANDIDATURA);
        else estadoSeguinte(CANDIDATURA_LOCK);
    }

    @Override
    public List<Aluno> listaAlunos() {
        estadoSeguinte(INICIA_CONFIG_LOCK);
        return data.listaAlunos();
    }

    @Override
    public List<Propostas> listaPropostas() {
        estadoSeguinte(INICIA_CONFIG_LOCK);
        return data.listaPropostas();
    }

    @Override
    public List<Docente> listaDocentes() {
        estadoSeguinte(INICIA_CONFIG_LOCK);
        return data.listaDocentes();
    }

    @Override
    public GestaoEstagioState getState() {
        return INICIA_CONFIG_LOCK;
    }
}

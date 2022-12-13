package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class GereDocentesState extends GestaoEstagioStateAdapter {
    public GereDocentesState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public boolean verificaEmailDocente(String emailDocente) {
        return data.verificaEmailDocente(emailDocente);
    }

    @Override
    public boolean editarEmailDocente(String emailantigo, String emailnovo) {
        return data.editarEmailDocente(emailantigo, emailnovo);
    }

    @Override
    public boolean editarNomeDocente(String email, String nome) {
        return data.editarNomeDocente(email, nome);
    }

    // este inserir tem que ser alterado (meta 2) passar para o gestaoEstagioData
    @Override
    public boolean inserirDocente(String nome, String email) {
        return data.insereDocente(nome, email);
    }


    // este eliminar tem que ser alterado (meta 2) passar para o gestaoEstagioData
    @Override
    public boolean eliminar(String email) {
        estadoSeguinte(INICIA_CONFIG);
        return data.eliminaDocente(email);
    }

    @Override
    public boolean consulta(String email) {
        estadoSeguinte(INICIA_CONFIG);
        return data.consultarDocente(email);
    }

    @Override
    public Docente getDocente(String email) {
        return data.getDocente(email);
    }

    @Override
    public List<Docente> listaDocentes() {
        return data.listaDocentes();
    }

    @Override
    public void regressarFaseAnterior() {
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public GestaoEstagioState getState() {
        return GERE_DOCENTES;
    }
}

package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class GereAlunosState extends GestaoEstagioStateAdapter {
    public GereAlunosState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);

    }

    @Override
    public boolean verificaIdAluno(Long id) {
        return data.verificaIdAluno(id);
    }

    @Override
    public boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio) {
        return data.inserirAluno(id, nome, email, curso, ramo, classificacao, podeEstagio);
    }

    @Override
    public boolean editarNomeAluno(Long id, String nome) {
        return data.editarNomeAluno(id, nome);
    }


    @Override
    public boolean editarEmailAluno(Long id, String email) {
        return data.editarEmailAluno(id, email);
    }

    @Override
    public boolean editarRamoAluno(Long id, String ramo) {
        return data.editarRamoAluno(id, ramo);
    }

    @Override
    public boolean editarCursoAluno(Long id, String curso) {
        // estadoSeguinte(INICIA_CONFIG);
        return data.editarCursoAluno(id, curso);
    }

    @Override
    public boolean editarClassificacaoAluno(Long id, Double classificacao) {
        // estadoSeguinte(INICIA_CONFIG);
        return data.editarClassificacaoAluno(id, classificacao);
    }

    @Override
    public boolean editarAcessoAluno(Long id, String acesso) {
        //estadoSeguinte(INICIA_CONFIG);
        return data.editarAcessoAluno(id, acesso);
    }


    @Override
    public boolean eliminar(String id) {
        // estadoSeguinte(INICIA_CONFIG);
        return data.eliminarAluno(id);
    }

    @Override
    public boolean consulta(String id) {
        estadoSeguinte(INICIA_CONFIG);
        return data.consultaAluno(id);
    }

    @Override
    public boolean getAcesso(String id) {
        return data.getAcesso(Long.parseLong(id));
    }

    @Override
    public List<Aluno> listaAlunos() {
        return data.listaAlunos();
    }

    @Override
    public Aluno getAluno(String id) {
        return data.getAluno(Long.parseLong(id));
    }

    @Override
    public void regressarFaseAnterior() {
        estadoSeguinte(INICIA_CONFIG);
    }

    @Override
    public GestaoEstagioState getState() {
        return GERE_ALUNOS;
    }
}

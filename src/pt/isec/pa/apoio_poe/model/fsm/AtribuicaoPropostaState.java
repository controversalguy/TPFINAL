package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;
import java.util.Map;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class AtribuicaoPropostaState extends GestaoEstagioStateAdapter {
    public AtribuicaoPropostaState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public GestaoEstagioState getState() {
        return ATRIBUICAO_PROPOSTA;
    }

    @Override
    public boolean atribuiAutoProposta() {
        return data.atribuiAutoProposta();
    }

    @Override
    public boolean atribuiPropostaManual(long nrAluno, String codProposta) {
        return data.atribuiPropostaManual(nrAluno, codProposta);
    }

    @Override
    public List<Aluno> listaAlunosComAutoProp() {
        return data.listaAlunosComAutoProp();
    }

    @Override
    public List<Aluno> listaAlunosComCand() {
        return data.listaAlunosComCand();
    }

    @Override
    public boolean atribuiPropostaDisp() {
        return data.atribuiPropostaDisp();
    }

    @Override
    public Map<Aluno, Integer> listaPropostaAtribuida() {
        return data.listaPropostaAtribuida();
    }

    @Override
    public List<Aluno> listaAlunosSemAtribuicao() {
        return data.listaAlunosSemAtribuicao();
    }

    public boolean removePropostaManual(long nrAluno, String codProposta) {
        return data.removePropostaManual(nrAluno, codProposta);
    }

    @Override
    public List<Propostas> listaPropostasDisp() {
        return data.listaPropostasDisp();
    }

    @Override
    public List<Propostas> listaPropostasAtribuidas() {
        return data.listaPropostasAtribuidas();
    }

    @Override
    public void exportaCsvFase3(String filename) throws Exception {
        data.exportaCsvFase3(filename);
    }
    @Override
    public List<Propostas> listaProjetoAtribuido(){
        return data.listaProjetoAtribuido();
    }
    @Override
    public List<Propostas> listaProjetos() {
        return data.listaProjetos();
    }

    @Override
    public void regressarFaseAnterior() {
        if (!data.getPhaseLocks(1)) estadoSeguinte(CANDIDATURA);
        else estadoSeguinte(CANDIDATURA_LOCK);
    }

    @Override
    public boolean fecharFaseAtribuiPropostas() {
        if (data.fecharFaseAtribuiPropostas()) {
            estadoSeguinte(ATRIBUICAO_PROPOSTA_LOCK);
            return true;
        }
        return false;
    }

    @Override
    public List<Propostas> listaAutopropostas() {
        return data.listaAutopropostas();
    }

    @Override
    public void avancarFase() {
        estadoSeguinte(ATRIBUICAO_ORIENTADOR);
    }

}

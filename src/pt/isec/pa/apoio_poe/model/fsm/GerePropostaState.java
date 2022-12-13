package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.projetodata.Estagio;
import pt.isec.pa.apoio_poe.model.data.projetodata.Projeto;
import pt.isec.pa.apoio_poe.model.data.projetodata.ProjetoAutoProposto;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class GerePropostaState extends GestaoEstagioStateAdapter {
    public GerePropostaState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public GestaoEstagioState getState() {
        return GERE_PROPOSTAS;
    }

    @Override
    public boolean insereEstagio(String codProposta, String ramo, String titulo, String instituicao, long nrAluno) {
        return data.inserePropostas(new Estagio(codProposta, ramo, titulo, instituicao, nrAluno));
    }

    @Override
    public boolean insereProjeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno) {
        return data.inserePropostas(new Projeto(codProposta, ramo, titulo, emailDocente, nrAluno));
    }

    @Override
    public boolean insereAutoproposto(String codProposta, String titulo, long nrAluno) {
        return data.inserePropostas(new ProjetoAutoProposto(codProposta, titulo, nrAluno));
    }

    @Override
    public boolean editarProposta(String cod, String dados, int op) {
        return data.editarProposta(cod, dados, op);
    }

    @Override
    public boolean eliminar(String codProposta) {
        return data.eliminaProposta(codProposta);

    }

    @Override
    public Propostas getProposta(String text) {
        return data.getProposta(text);
    }

    @Override
    public boolean consultaAutoProposto(String cod) {
        estadoSeguinte(INICIA_CONFIG);
        return data.listaAutoProposto(cod);
    }

    @Override
    public boolean consultaEstagio(String cod) {
        estadoSeguinte(INICIA_CONFIG);
        return data.listaEstagio(cod);
    }

    @Override
    public boolean consultaProjeto(String cod) {
        estadoSeguinte(INICIA_CONFIG);
        return data.listaProjeto(cod);
    }

    @Override
    public List<Propostas> listaPropostas() {
        return data.listaPropostas();
    }

    @Override
    public void regressarFaseAnterior() {
        estadoSeguinte(INICIA_CONFIG);
    }

}

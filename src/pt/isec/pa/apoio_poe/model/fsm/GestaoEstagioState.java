package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;

import java.io.Serializable;

public enum GestaoEstagioState implements Serializable {
    INICIO, IMPORTA_DADOS, EXPORTA_DADOS, INICIA_CONFIG, GERE_ALUNOS, GERE_DOCENTES, GERE_PROPOSTAS,
    CANDIDATURA, ATRIBUICAO_PROPOSTA, ATRIBUICAO_ORIENTADOR, CONSULTA, INICIA_CONFIG_LOCK, CANDIDATURA_LOCK, ATRIBUICAO_PROPOSTA_LOCK;

    IGestaoEstagioState createState(GestaoEstagioContext context, GestaoEstagioData data) {
        return switch (this) {
            case INICIA_CONFIG -> new IniciaConfigState(context, data);
            case IMPORTA_DADOS -> new ImportaDadosState(context, data);
            case EXPORTA_DADOS -> new ExportaDadosState(context, data);
            case GERE_ALUNOS -> new GereAlunosState(context, data);
            case GERE_DOCENTES -> new GereDocentesState(context, data);
            case CANDIDATURA -> new GereCandidaturaState(context, data);
            case GERE_PROPOSTAS -> new GerePropostaState(context, data);
            case ATRIBUICAO_PROPOSTA -> new AtribuicaoPropostaState(context, data);
            case ATRIBUICAO_ORIENTADOR -> new AtribuicaoOrientadorState(context, data);
            case CONSULTA -> new GereConsultaState(context, data);
            case INICIA_CONFIG_LOCK -> new IniciaConfigStateLock(context, data);
            case CANDIDATURA_LOCK -> new GereCandidaturaStateLock(context, data);
            case ATRIBUICAO_PROPOSTA_LOCK -> new AtribuicaoPropostaStateLock(context, data);
            default -> new InicioState(context, data);
        };
    }
}

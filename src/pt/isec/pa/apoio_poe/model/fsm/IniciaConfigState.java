package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.*;

public class IniciaConfigState extends GestaoEstagioStateAdapter {
    protected IniciaConfigState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.INICIA_CONFIG;
    }

    @Override
    public boolean recebeModo(int op) {
        switch (op) {
            case 0 -> {
                estadoSeguinte(INICIA_CONFIG);
                return true;
            }
            case 1 -> {
                estadoSeguinte(GERE_ALUNOS);
                return true;
            }
            case 2 -> {
                estadoSeguinte(GERE_DOCENTES);
                return true;
            }
            case 3 -> {
                estadoSeguinte(GERE_PROPOSTAS);
                return true;
            }
            case 4 -> {
                estadoSeguinte(IMPORTA_DADOS);
                return true;
            }
            case 5 -> {
                estadoSeguinte(EXPORTA_DADOS);
                return true;
            }
            case 6 -> {
                if (data.fecharFaseConfig()) {
                    System.out.println("Fase Fechada com Sucesso");
                    estadoSeguinte(INICIA_CONFIG_LOCK);
                    return true;
                } else {
                    System.out.println("Tentativa de Fechar Fase sem sucesso");
                    return false;
                }
            }
            case 7 -> {
                if (!data.getPhaseLocks(1)) estadoSeguinte(CANDIDATURA);
                else estadoSeguinte(CANDIDATURA_LOCK);
            }
            default -> {
                return false;
            }
        }
        return true;
    }
}

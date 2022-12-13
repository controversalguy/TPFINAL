package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;

public class InicioState extends GestaoEstagioStateAdapter{
    public InicioState(GestaoEstagioContext context, GestaoEstagioData data) {
        super(context, data);
    }

    @Override
    public void start() {
        data.initGame();
        estadoSeguinte(GestaoEstagioState.INICIA_CONFIG);
    }

    @Override
    public GestaoEstagioState getState() {
        return GestaoEstagioState.INICIO;
    }
}

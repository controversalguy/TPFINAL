package pt.isec.pa.apoio_poe.model.fsm;

public class AtribuiProponenteCommand extends CommandAdapter {

    private final String email;
    private final String cod;

    protected AtribuiProponenteCommand(IGestaoEstagioState state,String cod, String email) {
        super(state);
        this.email = email;
        this.cod = cod;
    }

    @Override
    public boolean execute() {
        return state.atribuiProponenteManual(cod,email);
    }

    @Override
    public boolean undo() {
        return state.removeProponenteManual(cod,email);
    }
}

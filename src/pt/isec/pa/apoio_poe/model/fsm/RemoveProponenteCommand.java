package pt.isec.pa.apoio_poe.model.fsm;

public class RemoveProponenteCommand extends CommandAdapter{

    private final String email;
    private final String cod;

    protected RemoveProponenteCommand(IGestaoEstagioState state,String cod,String email) {
        super(state);
        this.email = email;
        this.cod = cod;
    }

    @Override
    public boolean execute() {
        return state.removeProponenteManual(cod,email);
    }

    @Override
    public boolean undo() {
        return state.atribuiProponenteManual(cod,email);
    }
}


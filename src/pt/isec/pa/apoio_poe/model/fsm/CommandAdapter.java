package pt.isec.pa.apoio_poe.model.fsm;

abstract class CommandAdapter implements ICommand {
    protected IGestaoEstagioState state;

    protected CommandAdapter(IGestaoEstagioState state){
        this.state = state;
    }
}

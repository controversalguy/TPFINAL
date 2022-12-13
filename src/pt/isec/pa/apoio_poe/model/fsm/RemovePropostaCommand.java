package pt.isec.pa.apoio_poe.model.fsm;

public class RemovePropostaCommand extends CommandAdapter {

    private final String codigo;

    private final long nrAluno;

    protected RemovePropostaCommand(IGestaoEstagioState state, long nrAluno, String codigo) {
        super(state);
        this.nrAluno = nrAluno;
        this.codigo = codigo;
    }

    @Override
    public boolean execute() {
        return state.removePropostaManual(nrAluno,codigo);
    }

    @Override
    public boolean undo() {
        return state.atribuiPropostaManual(nrAluno,codigo);
    }
}

package pt.isec.pa.apoio_poe.model.fsm;

public class AtribuiPropostaCommand extends CommandAdapter {

    private final String codigo;

    private final long nrAluno;

    protected AtribuiPropostaCommand(IGestaoEstagioState state, long nrAluno, String codigo) {
        super(state);
        this.nrAluno = nrAluno;
        this.codigo = codigo;
    }

    @Override
    public boolean execute() {
        return state.atribuiPropostaManual(nrAluno, codigo);
    }

    @Override
    public boolean undo() {
        return state.removePropostaManual(nrAluno, codigo);
    }
}

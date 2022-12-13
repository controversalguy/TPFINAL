package pt.isec.pa.apoio_poe.model.fsm;

public interface ICommand {

    boolean execute();

    boolean undo();
}

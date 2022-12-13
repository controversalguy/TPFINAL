package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager implements Serializable {
    private Deque<ICommand> history;
    private Deque<ICommand> redoCmds;

    public CommandManager() {
        this.history = new ArrayDeque<>();
        this.redoCmds = new ArrayDeque<>();
    }

    public boolean invokeCommand(ICommand cmd) {
        redoCmds.clear();

        if (cmd.execute()) {
            history.push(cmd);
            return true;
        }
        history.clear();
        return false;
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }

        ICommand cmd = history.pop();
        cmd.undo();
        redoCmds.push(cmd);

        return true;
    }

    public boolean redo() {
        if (redoCmds.isEmpty()) return false;

        ICommand cmd = redoCmds.pop();
        cmd.execute();
        history.push(cmd);

        return true;
    }
}

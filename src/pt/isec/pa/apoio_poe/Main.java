package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;
import pt.isec.pa.apoio_poe.ui.text.GestaoEstagioUI;

public class Main {

    public static void main(String[] args) throws Exception {
        Application.launch(MainJFX.class,args);
        GestaoEstagioManager fsm = new GestaoEstagioManager();
        GestaoEstagioUI ui = new GestaoEstagioUI(fsm);
        ui.start();

    }
}


package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

public class IniciaConfigUI extends BorderPane {

    GestaoEstagioManager fsm;
    Button btnNext,btnPrevious;

    public IniciaConfigUI(GestaoEstagioManager fsm){
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        this.setCenter(new AppBar(fsm));
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> { update(); });

    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.INICIA_CONFIG) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

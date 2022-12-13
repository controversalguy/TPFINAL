package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

public class InicioUI extends BorderPane {

    GestaoEstagioManager fsm;
    Button btnStart,btnExit;
    public InicioUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnStart = new Button("Start");
        btnStart.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        HBox hBox = new HBox(btnStart,btnExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        this.setCenter(hBox);

    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> { update(); });
        btnStart.setOnAction( event -> {
            fsm.start();
        });
        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.INICIO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

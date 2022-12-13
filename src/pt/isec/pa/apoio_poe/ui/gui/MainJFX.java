package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.ui.gui.RootPane;

public class MainJFX extends Application {
    GestaoEstagioManager fsm;
    @Override
    public void init() throws Exception {
        super.init();
        fsm = new GestaoEstagioManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(fsm);
        Scene scene = new Scene(root,700,400);
        stage.setScene(scene);
        stage.setTitle("Gestão Estágio");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.show();
    }
}

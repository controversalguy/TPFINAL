package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.fsm.AtribuicaoOrientadorState;
import pt.isec.pa.apoio_poe.model.fsm.AtribuicaoPropostaState;
import pt.isec.pa.apoio_poe.model.fsm.AtribuicaoPropostaStateLock;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.Objects;

public class RootPane extends BorderPane {
    GestaoEstagioManager fsm;

    public RootPane(GestaoEstagioManager fsm) {
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        StackPane stackPane = new StackPane(
                new InicioUI(fsm),new IniciaConfigUI(fsm),new GestaoAlunosUI(fsm),new GestaoDocentesUI(fsm),new GestaoPropostasUI(fsm)
                , new ImportadadosUI(fsm),new ExportadadosUI(fsm),new IniciaConfigLockUI(fsm),new GereCandidaturasUI(fsm),new GereCandidaturasLockUI(fsm),new AtribuicaoPropostaUI(fsm),
                new AtribuicaoPropostaLockUI(fsm),new AtribuicaoOrientadorUI(fsm),new GereConsultaUI(fsm));
        stackPane.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("background.png")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));
        this.setCenter(stackPane);
    }

    private void registerHandlers() {
    }

    private void update() {
        
    }
}

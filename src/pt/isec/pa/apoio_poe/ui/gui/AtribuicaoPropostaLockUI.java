package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

public class AtribuicaoPropostaLockUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnConsulta,btnAvanca,btnRegressa;
    VBox btnVbox, propVbox;
    ListView<Propostas> listaPropAt;

    static int clicks=0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    public AtribuicaoPropostaLockUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }




    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        listaPropAt = new ListView<>();
        btnConsulta = new ToggleButton("Consulta Propostas Lock");
        btnConsulta.setPrefHeight(TOGGLE_HEIGHT);
        btnConsulta.setPrefWidth(TOGGLE_WIDTH);
        btnAvanca = new ToggleButton("Avançar Fase");
        btnAvanca.setPrefHeight(TOGGLE_HEIGHT);
        btnAvanca.setPrefWidth(TOGGLE_WIDTH);
        btnRegressa = new ToggleButton("Regressar");
        btnRegressa.setPrefHeight(TOGGLE_HEIGHT);
        btnRegressa.setPrefWidth(TOGGLE_WIDTH);

        btnVbox = new VBox(btnConsulta,btnAvanca,btnRegressa);
        btnVbox.setAlignment(Pos.CENTER);
        this.setCenter(btnVbox);

        listaPropAt = new ListView<>();
        propVbox = new VBox(listaPropAt);
        propVbox.prefWidth(500);
        propVbox.setAlignment(Pos.CENTER_RIGHT);
        propVbox.setVisible(false);
        propVbox.setManaged(false);
        this.setRight(propVbox);




    }
    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());
        btnConsulta.setOnAction(actionEvent -> {

            clicks++;
            if (clicks % 2 == 0) {
                listaPropAt.setVisible(false);
                propVbox.setVisible(false);
                propVbox.setManaged(false);

            } else {
                listaPropAt.setVisible(true);
                propVbox.setManaged(true);
                propVbox.setVisible(true);
                this.setRight(propVbox);
            }

        });
        btnAvanca.setOnAction(actionEvent -> fsm.avancarFase());
        btnRegressa.setOnAction(actionEvent -> fsm.regressaFaseAnterior());
    }
    private void update() {
        if (fsm.getState() != GestaoEstagioState.ATRIBUICAO_PROPOSTA_LOCK) {
            this.setVisible(false);
            return;
        }
        listaPropAt.getItems().clear();
        listaPropAt.getItems().addAll(fsm.listaPropostasAtribuidas());
        this.setVisible(true);
    }
}

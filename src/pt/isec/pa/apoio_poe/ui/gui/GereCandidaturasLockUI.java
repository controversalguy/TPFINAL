package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

public class GereCandidaturasLockUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnConsulta,btnAvanca,btnRegressa;
    VBox btnbox,candbox;
    ListView <Candidaturas> listaCandidaturas;
    static int clicks=0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    public GereCandidaturasLockUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        listaCandidaturas = new ListView<>();
        btnConsulta = new ToggleButton("Consulta Candidaturas");
        btnConsulta.setPrefHeight(TOGGLE_HEIGHT);
        btnConsulta.setPrefWidth(TOGGLE_WIDTH);
        btnAvanca = new ToggleButton("Avançar Fase");
        btnAvanca.setPrefHeight(TOGGLE_HEIGHT);
        btnAvanca.setPrefWidth(TOGGLE_WIDTH);
        btnRegressa = new ToggleButton("Regressar");
        btnRegressa.setPrefHeight(TOGGLE_HEIGHT);
        btnRegressa.setPrefWidth(TOGGLE_WIDTH);

        btnbox = new VBox(btnConsulta,btnAvanca,btnRegressa);
        btnbox.setAlignment(Pos.CENTER);
        btnbox.setSpacing(10);
        this.setCenter(btnbox);

        listaCandidaturas = new ListView<>();
        candbox = new VBox(listaCandidaturas);
        candbox.prefWidth(500);
        candbox.setVisible(false);
        this.setRight(candbox);
        candbox.setManaged(false);

    }
    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());
        btnConsulta.setOnAction(actionEvent -> {
            clicks++;
            if(clicks % 2 ==0){
                listaCandidaturas.setVisible(false);
                candbox.setVisible(false);
                candbox.setManaged(false);

            }else{
                listaCandidaturas.setVisible(true);
                candbox.setVisible(true);
                candbox.setManaged(true);
                this.setRight(candbox);
            }

        });
        btnAvanca.setOnAction(actionEvent -> fsm.avancarFase());
        btnRegressa.setOnAction(actionEvent -> fsm.regressaFaseAnterior());
    }
    private void update() {
        if (fsm.getState() != GestaoEstagioState.CANDIDATURA_LOCK) {
            this.setVisible(false);
            return;
        }
        listaCandidaturas.getItems().clear();
        listaCandidaturas.getItems().addAll(fsm.listaCandidaturasLock());
        this.setVisible(true);
    }

}

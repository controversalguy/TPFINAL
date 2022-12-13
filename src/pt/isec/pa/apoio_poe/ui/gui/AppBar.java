package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;

public class AppBar extends BorderPane {
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    GestaoEstagioManager fsm;

    ToggleButton btnAlunos,btnDocentes,btnPropostas,btnimporta,btnexporta,btnFechaFase,btnNext;


    public AppBar(GestaoEstagioManager fsm){
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnAlunos = new ToggleButton("Gestão de Alunos");
        btnAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunos.setPrefWidth(TOGGLE_WIDTH);
        btnDocentes = new ToggleButton("Gestão de Docentes");
        btnDocentes.setPrefHeight(TOGGLE_HEIGHT);
        btnDocentes.setPrefWidth(TOGGLE_WIDTH);
        btnPropostas = new ToggleButton("Gestão de Propostas");
        btnPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnPropostas.setPrefWidth(TOGGLE_WIDTH);
        btnimporta = new ToggleButton("Importa");
        btnimporta.setPrefHeight(TOGGLE_HEIGHT);
        btnimporta.setPrefWidth(TOGGLE_WIDTH);
        btnexporta = new ToggleButton("Exporta");
        btnexporta.setPrefHeight(TOGGLE_HEIGHT);
        btnexporta.setPrefWidth(TOGGLE_WIDTH);
        btnFechaFase = new ToggleButton("Fecha Fase");
        btnFechaFase.setPrefHeight(TOGGLE_HEIGHT);
        btnFechaFase.setPrefWidth(TOGGLE_WIDTH);
        btnNext = new ToggleButton("Avança fase");
        btnNext.setPrefHeight(TOGGLE_HEIGHT);
        btnNext.setPrefWidth(TOGGLE_WIDTH);
        VBox box = new VBox(btnAlunos,btnDocentes,btnPropostas,btnimporta,btnexporta,btnFechaFase,btnNext);
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        this.setCenter(box);

    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> {
            update();
        });
        btnAlunos.setOnAction(ev -> fsm.recebeModo(1));
        btnDocentes.setOnAction(ev -> fsm.recebeModo(2));
        btnPropostas.setOnAction(ev -> fsm.recebeModo(3));
        btnimporta.setOnAction(ev -> fsm.recebeModo(4));
        btnexporta.setOnAction(ev -> fsm.recebeModo(5));
        btnFechaFase.setOnAction(ev ->{
            if(!fsm.recebeModo(6)){
                ToastMessage.show(getScene().getWindow(), "Não existem propostas suficientes para todos os alunos");
            }
        });
        btnNext.setOnAction(ev -> fsm.recebeModo(7));

    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.INICIA_CONFIG) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }
}
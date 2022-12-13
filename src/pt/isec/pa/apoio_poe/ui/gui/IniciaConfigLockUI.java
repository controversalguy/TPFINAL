package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;


public class IniciaConfigLockUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnAlunos,btnDocentes,btnPropostas, btnAvancar;
    ListView<Aluno> listaAlunos;
    ListView <Docente>listaDocentes;
    ListView <Propostas> listaPropostas;
    VBox alunosVbox ,docentesVbox,propostasVbox;
    static int clicks = 0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    public IniciaConfigLockUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnAlunos = new ToggleButton("Consulta de Alunos");
        btnAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunos.setPrefWidth(TOGGLE_WIDTH);
        btnDocentes = new ToggleButton("Consulta de Docentes");
        btnDocentes.setPrefHeight(TOGGLE_HEIGHT);
        btnDocentes.setPrefWidth(TOGGLE_WIDTH);
        btnPropostas = new ToggleButton("Consulta de Propostas");
        btnPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnPropostas.setPrefWidth(TOGGLE_WIDTH);
        btnAvancar = new ToggleButton("Avança");
        btnAvancar.setPrefHeight(TOGGLE_HEIGHT);
        btnAvancar.setPrefWidth(TOGGLE_WIDTH);
        VBox box = new VBox(btnAlunos,btnDocentes,btnPropostas, btnAvancar);
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        this.setCenter(box);
        listaAlunos = new ListView<>();
        listaDocentes = new ListView<>();
        listaPropostas = new ListView<>();
        alunosVbox = new VBox(listaAlunos);
        alunosVbox.setVisible(false);
        alunosVbox.setPrefWidth(500);
        this.setRight(alunosVbox);
        alunosVbox.setManaged(false);
        docentesVbox = new VBox(listaDocentes);
        docentesVbox.setVisible(false);
        docentesVbox.setPrefWidth(500);
        this.setRight(docentesVbox);
        docentesVbox.setManaged(false);
        propostasVbox = new VBox(listaPropostas);
        propostasVbox.setVisible(false);
        propostasVbox.setPrefWidth(500);
        this.setRight(propostasVbox);
        propostasVbox.setManaged(false);
    }


    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());
        btnAvancar.setOnAction(actionEvent -> fsm.avancarFase());
        btnAlunos.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaAlunos.setVisible(false);
                alunosVbox.setVisible(false);
                alunosVbox.setManaged(false);

            } else {
                listaAlunos.setVisible(true);
                alunosVbox.setVisible(true);
                alunosVbox.setManaged(true);
                this.setRight(alunosVbox);
            }
        });
        btnDocentes.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaDocentes.setVisible(false);
                docentesVbox.setVisible(false);
                docentesVbox.setManaged(false);

            } else {
                listaDocentes.setVisible(true);
                docentesVbox.setVisible(true);
                docentesVbox.setManaged(true);
                this.setRight(docentesVbox);
            }

        });
        btnPropostas.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaPropostas.setVisible(false);
                propostasVbox.setVisible(false);
                propostasVbox.setManaged(false);

            } else {
                listaPropostas.setVisible(true);
                propostasVbox.setVisible(true);
                propostasVbox.setManaged(true);
                this.setRight(propostasVbox);
            }
        });
    }
    private void update() {

        if (fsm.getState() != GestaoEstagioState.INICIA_CONFIG_LOCK) {
            this.setVisible(false);
            return;
        }
        listaAlunos.getItems().clear();
        listaDocentes.getItems().clear();
        listaPropostas.getItems().clear();
        listaAlunos.getItems().addAll(fsm.listaAlunos());
        listaDocentes.getItems().addAll(fsm.listaDocentes());
        listaPropostas.getItems().addAll(fsm.listaPropostas());
        this.setVisible(true);
    }
}

package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;

import java.io.File;


public class ImportadadosUI extends BorderPane{
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    private String filename;
    private FileChooser fileChooser;
    private Label label;
    private ToggleButton btnimportaAlunos,btnimportaDocentes,btnimportaPropostas,btnimportaVolta;
    GestaoEstagioManager fsm;
        public ImportadadosUI(GestaoEstagioManager fsm) {
            this.fsm = fsm;
            createViews();
            registerHandlers();
            update();
        }


    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnimportaAlunos = new ToggleButton("Importa Alunos");
        btnimportaAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnimportaAlunos.setPrefWidth(TOGGLE_WIDTH);
        btnimportaDocentes = new ToggleButton("Importa Docentes");
        btnimportaDocentes.setPrefHeight(TOGGLE_HEIGHT);
        btnimportaDocentes.setPrefWidth(TOGGLE_WIDTH);
        btnimportaPropostas = new ToggleButton("Importa Propostas");
        btnimportaPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnimportaPropostas.setPrefWidth(TOGGLE_WIDTH);
        btnimportaVolta = new ToggleButton("Regressar");
        btnimportaVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnimportaVolta.setPrefWidth(TOGGLE_WIDTH);



        VBox Box = new VBox(btnimportaAlunos,btnimportaDocentes,btnimportaPropostas,btnimportaVolta);
        Box.setAlignment(Pos.CENTER);
        this.setCenter(Box);
    }
    private void registerHandlers() {

        fsm.addPropertyChangeListener(evt -> { update(); });
        btnimportaAlunos.setOnAction(evt -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Seleciona o ficheiro");
            fileChooser.setInitialDirectory(new File("..\\TemplateTP"));
            label = new Label("no files selected");
            Stage stage = (Stage) btnimportaAlunos.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                //System.out.println(file.getAbsolutePath());
                filename = file.getName();
                label.setText(file.getAbsolutePath()
                        + "  selected");
            }

            try {
                if(!fsm.importaAlunos(filename)) ToastMessage.show(getScene().getWindow(), "Erro Importa");
                else ToastMessage.show(getScene().getWindow(), "Importa Alunos Bem Sucedido");
                fsm.recebeModo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        btnimportaDocentes.setOnAction(evt -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Seleciona o ficheiro");
            fileChooser.setInitialDirectory(new File("..\\TemplateTP"));
            label = new Label("no files selected");
            Stage stage = (Stage) btnimportaDocentes.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                //System.out.println(file.getAbsolutePath());
                filename = file.getName();
                label.setText(file.getAbsolutePath()
                        + "  selected");
            }
            try {
                if(!fsm.importaDocentes(filename))ToastMessage.show(getScene().getWindow(), "Erro Importa");
                else ToastMessage.show(getScene().getWindow(), "Importa Docentes Bem Sucedido");
                fsm.recebeModo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnimportaPropostas.setOnAction(evt -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Seleciona o ficheiro");
            fileChooser.setInitialDirectory(new File("..\\TemplateTP"));
            label = new Label("no files selected");
            Stage stage = (Stage) btnimportaPropostas.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                //System.out.println(file.getAbsolutePath());
                filename = file.getName();
                label.setText(file.getAbsolutePath()
                        + "  selected");
            }
            try {
                if(!fsm.importaPropostas(filename))ToastMessage.show(getScene().getWindow(), "Erro Importa (Importa Docentes e Alunos!)");
                else ToastMessage.show(getScene().getWindow(), "Importa Propostas Bem Sucedido");
                fsm.recebeModo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnimportaVolta.setOnAction(actionEvent -> {
            fsm.regressaFaseAnterior();
        });
    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.IMPORTA_DADOS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
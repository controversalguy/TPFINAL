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

import java.io.File;


public class ExportadadosUI extends BorderPane{
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    private String filename;
    private FileChooser fileChooser;
    private Label label;
    private ToggleButton btnExportaAlunos,btnExportaDocentes,btnExportaPropostas,btnExportaVolta;
    GestaoEstagioManager fsm;
    public ExportadadosUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnExportaAlunos = new ToggleButton("Exporta Alunos");
        btnExportaAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnExportaAlunos.setPrefWidth(TOGGLE_WIDTH);
        btnExportaDocentes = new ToggleButton("Exporta Docentes");
        btnExportaDocentes.setPrefHeight(TOGGLE_HEIGHT);
        btnExportaDocentes.setPrefWidth(TOGGLE_WIDTH);
        btnExportaPropostas = new ToggleButton("Exporta Propostas");
        btnExportaPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnExportaPropostas.setPrefWidth(TOGGLE_WIDTH);
        btnExportaVolta = new ToggleButton("Regressa");
        btnExportaVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnExportaVolta.setPrefWidth(TOGGLE_WIDTH);


        VBox Box = new VBox(btnExportaAlunos,btnExportaDocentes,btnExportaPropostas,btnExportaVolta);
        Box.setAlignment(Pos.CENTER);
        this.setCenter(Box);
    }
    private void registerHandlers() {

        fsm.addPropertyChangeListener(evt -> { update(); });
        btnExportaAlunos.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage stage = (Stage) btnExportaAlunos.getScene().getWindow();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                filename = file.getName();
                try {
                    fsm.exportaCsvAlunos(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        btnExportaDocentes.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage stage = (Stage) btnExportaAlunos.getScene().getWindow();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                filename = file.getName();
                try {
                    fsm.exportaCsvDocentes(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnExportaPropostas.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage stage = (Stage) btnExportaAlunos.getScene().getWindow();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                filename = file.getName();
                try {
                    fsm.exportaCsvPropostas(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnExportaVolta.setOnAction(actionEvent -> {
            fsm.regressaFaseAnterior();
        });

    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.EXPORTA_DADOS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}

package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

import java.io.File;
import java.util.Arrays;

public class AppMenu extends MenuBar {

    GestaoEstagioManager fsm;
    Menu mnFile;
    MenuItem mnNew,mnOpen,mnSave,mnExit;
    Menu mnEdit;
    MenuItem mnUndo,mnRedo;


    public AppMenu(GestaoEstagioManager fsm){
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        mnFile = new Menu("File");
        mnNew = new MenuItem("_New");
        //mnNew.setGraphic(ImageManager.getImageView("Clear-icon.png",ITEM_IMG_SIZE));
        mnOpen = new MenuItem("_Open");
        mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCodeCombination.CONTROL_DOWN));
        mnSave = new MenuItem("_Save");
        mnExit = new MenuItem("_Exit");
        mnFile.getItems().addAll(mnNew,mnOpen,mnSave,new SeparatorMenuItem(),mnExit);

        mnEdit = new Menu("Edit");
        mnUndo = new MenuItem("_Undo");
        mnRedo = new MenuItem("_Redo");
        mnEdit.getItems().addAll(mnUndo,mnRedo);


        this.getMenus().addAll(mnFile,mnEdit);
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> {
            update();
        });
        mnNew.setOnAction(e -> fsm.start());
        mnOpen.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All","*.*"));

            File hfile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if(hfile!=null) {
                try {
                    fsm.load(hfile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
        mnUndo.setOnAction(actionEvent -> fsm.undo());
        mnRedo.setOnAction(actionEvent -> fsm.redo());
        mnSave.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File save...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All","*.*"));

           File hfile = fileChooser.showSaveDialog(this.getScene().getWindow());
           if(hfile!=null) {
               try {
                   fsm.save(hfile);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }

           }


        });

        mnExit.setOnAction(ev -> Platform.exit());

    }

    private void update() {
        if (fsm.getState() == GestaoEstagioState.ATRIBUICAO_ORIENTADOR || fsm.getState() == GestaoEstagioState.ATRIBUICAO_PROPOSTA) {
            mnUndo.setDisable(false);
            mnRedo.setDisable(false);
            return;
        }
        mnUndo.setDisable(true);
        mnRedo.setDisable(true);
    }
}

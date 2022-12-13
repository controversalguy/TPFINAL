package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;

import java.io.File;
import java.util.Optional;

public class AtribuicaoPropostaUI extends BorderPane {
    static int clicks = 0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    GestaoEstagioManager fsm;
    private String filename;
    ToggleButton btnAtProp,btnAlunos,btnAlunosT3Associada,btnAlunosCandidatos,btnAlunosPropAt,btnAlunosSemPropAt, btnVoltaAt,
            btnVoltaInicio, btnVoltaAlunos, btnAtPropAuto, btnAtPropSemAt, btnAtPropManual, btnRemovePropManual, btnProp,
            btnPropAuto, btnPropDoc, btnPropDisp, btnPropAt,btnVoltaProp,btnExporta,btnFechaFase,btnAvanca;
    VBox vBoxBtn,vBoxbtnAlunos,alunosT3Vbox,alunosCandidatosVbox,alunosPropAtVbox,alunosSemPropAtVbox,vBoxbtnAtProp,
            vBoxbtnProp,PropAutoVbox,PropDocVbox,PropDispVbox,PropAtVbox;
    ListView listaAlunosT3,listaAlunosCandidatos,listaAlunosPropAt,listaAlunosSemPropAt,listaPropAuto,listaPropDoc
            ,listaPropDisp,listaPropAt;
    public AtribuicaoPropostaUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnExporta = new ToggleButton("Exporta");
        btnExporta.setPrefHeight(TOGGLE_HEIGHT);
        btnExporta.setPrefWidth(TOGGLE_WIDTH);

        btnFechaFase = new ToggleButton("Fecha Fase");
        btnFechaFase.setPrefHeight(TOGGLE_HEIGHT);
        btnFechaFase.setPrefWidth(TOGGLE_WIDTH);

        btnAvanca = new ToggleButton("Avançar Fase");
        btnAvanca.setPrefHeight(TOGGLE_HEIGHT);
        btnAvanca.setPrefWidth(TOGGLE_WIDTH);

        btnVoltaAt = new ToggleButton("Regressar");
        btnVoltaAt.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaAt.setPrefWidth(TOGGLE_WIDTH);

        btnVoltaInicio = new ToggleButton("Regressar");
        btnVoltaInicio.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaInicio.setPrefWidth(TOGGLE_WIDTH);

        btnVoltaAlunos = new ToggleButton("Regressar");
        btnVoltaAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaAlunos.setPrefWidth(TOGGLE_WIDTH);

        btnAtProp = new ToggleButton("Atribuição de Propostas");
        btnAtProp.setPrefHeight(TOGGLE_HEIGHT);
        btnAtProp.setPrefWidth(TOGGLE_WIDTH);

        btnAtPropAuto = new ToggleButton("Atribuição autopropostas e propostas docentes");
        btnAtPropAuto.setPrefHeight(TOGGLE_HEIGHT);
        btnAtPropAuto.setPrefWidth(TOGGLE_WIDTH);

        btnAtPropSemAt = new ToggleButton("Atribuição propostas disponíveis");
        btnAtPropSemAt.setPrefHeight(TOGGLE_HEIGHT);
        btnAtPropSemAt.setPrefWidth(TOGGLE_WIDTH);

        btnAtPropManual = new ToggleButton("Atribuição Manual");
        btnAtPropManual.setPrefHeight(TOGGLE_HEIGHT);
        btnAtPropManual.setPrefWidth(TOGGLE_WIDTH);

        btnRemovePropManual = new ToggleButton("Remove Proposta Atribuida");
        btnRemovePropManual.setPrefHeight(TOGGLE_HEIGHT);
        btnRemovePropManual.setPrefWidth(TOGGLE_WIDTH);

        vBoxbtnAtProp = new VBox(btnAtPropAuto, btnAtPropSemAt, btnAtPropManual, btnRemovePropManual,btnVoltaAt);
        vBoxbtnAtProp.setAlignment(Pos.CENTER);
        vBoxbtnAtProp.setSpacing(10);
        vBoxbtnAtProp.setVisible(false);
        vBoxbtnAtProp.setManaged(false);

        btnAlunos = new ToggleButton("Lista Alunos");
        btnAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunos.setPrefWidth(TOGGLE_WIDTH);

        btnAlunosT3Associada = new ToggleButton("AutoProposta Associada");
        btnAlunosT3Associada.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunosT3Associada.setPrefWidth(TOGGLE_WIDTH);

        btnAlunosCandidatos = new ToggleButton("Com candidatura");
        btnAlunosCandidatos.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunosCandidatos.setPrefWidth(TOGGLE_WIDTH);

        btnAlunosPropAt = new ToggleButton("Proposta Atribuida");
        btnAlunosPropAt.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunosPropAt.setPrefWidth(TOGGLE_WIDTH);

        btnAlunosSemPropAt = new ToggleButton("Sem Proposta Atribuida");
        btnAlunosSemPropAt.setPrefHeight(TOGGLE_HEIGHT);
        btnAlunosSemPropAt.setPrefWidth(TOGGLE_WIDTH);


        btnProp = new ToggleButton("Lista Propostas");
        btnProp.setPrefHeight(TOGGLE_HEIGHT);
        btnProp.setPrefWidth(TOGGLE_WIDTH);

        btnPropAuto = new ToggleButton("AutoPropostos");
        btnPropAuto.setPrefHeight(TOGGLE_HEIGHT);
        btnPropAuto.setPrefWidth(TOGGLE_WIDTH);
        btnPropDoc = new ToggleButton("Propostas Docentes");
        btnPropDoc.setPrefHeight(TOGGLE_HEIGHT);
        btnPropDoc.setPrefWidth(TOGGLE_WIDTH);
        btnPropDisp = new ToggleButton("Propostas Disponíveis");
        btnPropDisp.setPrefHeight(TOGGLE_HEIGHT);
        btnPropDisp.setPrefWidth(TOGGLE_WIDTH);
        btnPropAt = new ToggleButton("Propostas Atribuídas");
        btnPropAt.setPrefHeight(TOGGLE_HEIGHT);
        btnPropAt.setPrefWidth(TOGGLE_WIDTH);

        btnVoltaProp = new ToggleButton("Regressar");
        btnVoltaProp.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaProp.setPrefWidth(TOGGLE_WIDTH);

        vBoxbtnProp = new VBox(btnPropAuto, btnPropDoc, btnPropDisp, btnPropAt,btnVoltaProp);
        vBoxbtnProp.setAlignment(Pos.CENTER);
        vBoxbtnProp.setSpacing(10);
        vBoxbtnProp.setVisible(false);
        vBoxbtnProp.setManaged(false);

        vBoxBtn = new VBox(btnAtProp, btnAlunos,btnProp,btnExporta, btnFechaFase,btnAvanca,btnVoltaInicio);
        vBoxBtn.setAlignment(Pos.CENTER);
        vBoxBtn.setSpacing(10);
        this.setCenter(vBoxBtn);
        vBoxbtnAlunos = new VBox(btnAlunosT3Associada, btnAlunosCandidatos, btnAlunosPropAt, btnAlunosSemPropAt, btnVoltaAlunos);
        vBoxbtnAlunos.setAlignment(Pos.CENTER);
        vBoxbtnAlunos.setSpacing(10);
        vBoxbtnAlunos.setVisible(false);
        vBoxbtnAlunos.setManaged(false);

        listaAlunosT3 = new ListView<>();
        alunosT3Vbox = new VBox(listaAlunosT3);
        alunosT3Vbox.setVisible(false);
        alunosT3Vbox.setPrefWidth(500);
        alunosT3Vbox.setManaged(false);

        listaAlunosCandidatos = new ListView<>();
        alunosCandidatosVbox = new VBox(listaAlunosCandidatos);
        alunosCandidatosVbox.setVisible(false);
        alunosCandidatosVbox.setPrefWidth(500);
        alunosCandidatosVbox.setManaged(false);

        listaAlunosPropAt = new ListView<>();
        alunosPropAtVbox = new VBox(listaAlunosPropAt);
        alunosPropAtVbox.setVisible(false);
        alunosPropAtVbox.setPrefWidth(500);
        alunosPropAtVbox.setManaged(false);

        listaAlunosSemPropAt = new ListView<>();
        alunosSemPropAtVbox = new VBox(listaAlunosSemPropAt);
        alunosSemPropAtVbox.setVisible(false);
        alunosSemPropAtVbox.setPrefWidth(500);
        alunosSemPropAtVbox.setManaged(false);

        listaPropAuto = new ListView<>();
        PropAutoVbox = new VBox(listaPropAuto);
        PropAutoVbox.setVisible(false);
        PropAutoVbox.setPrefWidth(500);
        PropAutoVbox.setManaged(false);

        listaPropDoc = new ListView<>();
        PropDocVbox = new VBox(listaPropDoc);
        PropDocVbox.setVisible(false);
        PropDocVbox.setPrefWidth(500);
        PropDocVbox.setManaged(false);

        listaPropDisp = new ListView<>();
        PropDispVbox = new VBox(listaPropDisp);
        PropDispVbox.setVisible(false);
        PropDispVbox.setPrefWidth(500);
        PropDispVbox.setManaged(false);

        listaPropAt = new ListView<>();
        PropAtVbox = new VBox(listaPropAt);
        PropAtVbox.setVisible(false);
        PropAtVbox.setPrefWidth(500);
        PropAtVbox.setManaged(false);



    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> {
            update();
        });
        btnAtProp.setOnAction(actionEvent -> {
            vBoxBtn.setVisible(false);
            vBoxBtn.setManaged(false);
            vBoxbtnAtProp.setVisible(true);
            vBoxbtnAtProp.setManaged(true);
            vBoxbtnAtProp.setAlignment(Pos.CENTER);
            this.setCenter(vBoxbtnAtProp);
            btnAtPropAuto.setOnAction(automatico->{
                if(!fsm.atribuiAutoProposta())
                    ToastMessage.show(getScene().getWindow(), "Não há nada para atribuir");
                else
                    ToastMessage.show(getScene().getWindow(), "Proposta Atribuida com sucesso");
            });
            btnAtPropSemAt.setOnAction(at->{
                if(!fsm.atribuiPropostaDisp())
                    ToastMessage.show(getScene().getWindow(), "Não há nada para atribuir");
                else
                    ToastMessage.show(getScene().getWindow(), "Proposta Atribuida com sucesso");
            });
            //TODO VALIDAR
            btnAtPropManual.setOnAction(a->{

                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Gestão de Propostas");
                dialog.setHeaderText("Atribui Proposta");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField id = new TextField();
                id.setPromptText("2015123456");
                TextField codProp = new TextField();
                codProp.setPromptText("P010");


                grid.add(new Label("Nr de Aluno:"), 0, 4);
                grid.add(id, 1, 4);
                grid.add(new Label("Cod Proposta:"), 0, 0);
                grid.add(codProp, 1, 0);


                dialog.getDialogPane().setContent(grid);

                Optional result = dialog.showAndWait();

                result.ifPresent(t -> {
                    if (t == okButtonType) {
                        if(!fsm.atribuiPropostaManual(Long.parseLong(id.getText()),codProp.getText()))
                            ToastMessage.show(getScene().getWindow(), "Operação atribui sem sucesso");
                        else ToastMessage.show(getScene().getWindow(), "Atribuíu proposta com sucesso");
                    }else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                });

            });
            btnRemovePropManual.setOnAction(Event->{
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Gestão de Propostas");
                dialog.setHeaderText("Remover Proposta Manual");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField id = new TextField();
                id.setPromptText("2015123456");
                TextField codProp = new TextField();
                codProp.setPromptText("P010");


                grid.add(new Label("Nr de Aluno:"), 0, 4);
                grid.add(id, 1, 4);
                grid.add(new Label("Cod Proposta:"), 0, 0);
                grid.add(codProp, 1, 0);


                dialog.getDialogPane().setContent(grid);

                Optional result = dialog.showAndWait();

                result.ifPresent(t -> {
                    if (t == okButtonType) {
                        if(!fsm.removePropostaManual(Long.parseLong(id.getText()),codProp.getText()))
                            ToastMessage.show(getScene().getWindow(), "Operação remover sem sucesso");
                        else ToastMessage.show(getScene().getWindow(), "Removeu Proposta com sucesso");
                    }else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                });

            });
            btnVoltaAt.setOnAction(as -> {
                vBoxBtn.setVisible(true);
                vBoxBtn.setManaged(true);
                vBoxbtnAtProp.setVisible(false);
                vBoxbtnAtProp.setManaged(false);
                vBoxBtn.setAlignment(Pos.CENTER);
                this.setCenter(vBoxBtn);
            });
        });
        btnAlunos.setOnAction(a -> {
            vBoxBtn.setVisible(false);
            vBoxBtn.setManaged(false);
            vBoxbtnAlunos.setVisible(true);
            vBoxbtnAlunos.setManaged(true);
            this.setCenter(vBoxbtnAlunos);

            btnAlunosT3Associada.setOnAction(actionEvent -> {
                    clicks++;
                    if (clicks % 2 == 0) {
                        listaAlunosT3.setVisible(false);
                        alunosT3Vbox.setVisible(false);
                        alunosT3Vbox.setManaged(false);
                    } else {
                        listaAlunosT3.setVisible(true);
                        alunosT3Vbox.setVisible(true);
                        alunosT3Vbox.setManaged(true);
                        this.setRight(alunosT3Vbox);
                    }
            });
            btnAlunosCandidatos.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaAlunosCandidatos.setVisible(false);
                    alunosCandidatosVbox.setVisible(false);
                    alunosCandidatosVbox.setManaged(false);
                } else {
                    listaAlunosCandidatos.setVisible(true);
                    alunosCandidatosVbox.setVisible(true);
                    alunosCandidatosVbox.setManaged(true);
                    this.setRight(alunosCandidatosVbox);
                }
            });
            btnAlunosPropAt.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaAlunosPropAt.setVisible(false);
                    alunosPropAtVbox.setVisible(false);
                    alunosPropAtVbox.setManaged(false);
                } else {
                    listaAlunosPropAt.setVisible(true);
                    alunosPropAtVbox.setVisible(true);
                    alunosPropAtVbox.setManaged(true);
                    this.setRight(alunosPropAtVbox);
                }
            });
            btnAlunosSemPropAt.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaAlunosSemPropAt.setVisible(false);
                    alunosSemPropAtVbox.setVisible(false);
                    alunosSemPropAtVbox.setManaged(false);
                } else {
                    listaAlunosSemPropAt.setVisible(true);
                    alunosSemPropAtVbox.setVisible(true);
                    alunosSemPropAtVbox.setManaged(true);
                    this.setRight(alunosSemPropAtVbox);
                }
            });
            btnVoltaAlunos.setOnAction(actionEvent -> {
                vBoxBtn.setVisible(true);
                vBoxBtn.setManaged(true);
                vBoxbtnAlunos.setVisible(false);
                vBoxbtnAlunos.setManaged(false);
                this.setCenter(vBoxBtn);
            });
        });
        btnProp.setOnAction(d -> {
            vBoxBtn.setVisible(false);
            vBoxBtn.setManaged(false);
            vBoxbtnProp.setVisible(true);
            vBoxbtnProp.setManaged(true);
            this.setCenter(vBoxbtnProp);

            btnPropAuto.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropAuto.setVisible(false);
                    PropAutoVbox.setVisible(false);
                    PropAutoVbox.setManaged(false);
                } else {
                    listaPropAuto.setVisible(true);
                    PropAutoVbox.setVisible(true);
                    PropAutoVbox.setManaged(true);
                    this.setRight(PropAutoVbox);
                }
            });
            btnPropDoc.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropDoc.setVisible(false);
                    PropDocVbox.setVisible(false);
                    PropDocVbox.setManaged(false);
                } else {
                    listaPropDoc.setVisible(true);
                    PropDocVbox.setVisible(true);
                    PropDocVbox.setManaged(true);
                    this.setRight(PropDocVbox);
                }
            });
            btnPropDisp.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropDisp.setVisible(false);
                    PropDispVbox.setVisible(false);
                    PropDispVbox.setManaged(false);
                } else {
                    listaPropDisp.setVisible(true);
                    PropDispVbox.setVisible(true);
                    PropDispVbox.setManaged(true);
                    this.setRight(PropDispVbox);
                }
            });
            btnPropAt.setOnAction(actionEvent -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropAt.setVisible(false);
                    PropAtVbox.setVisible(false);
                    PropAtVbox.setManaged(false);
                } else {
                    listaPropAt.setVisible(true);
                    PropAtVbox.setVisible(true);
                    PropAtVbox.setManaged(true);
                    this.setRight(PropAtVbox);
                }
            });
            btnVoltaProp.setOnAction(actionEvent -> {
                vBoxBtn.setVisible(true);
                vBoxBtn.setManaged(true);
                vBoxbtnProp.setVisible(false);
                vBoxbtnProp.setManaged(false);
                this.setCenter(vBoxBtn);
            });
        });
        btnExporta.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage stage = (Stage) btnExporta.getScene().getWindow();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                filename = file.getName();
                try {
                    fsm.exportaCsvFase3(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnFechaFase.setOnAction(actionEvent -> {
            if(!fsm.fecharFaseAtribuiPropostas()){
                ToastMessage.show(getScene().getWindow(),"Alunos com candidaturas submetidas sem projeto atribuído");
            }
        });
        btnAvanca.setOnAction(actionEvent -> {
            fsm.avancarFase();
        });
        btnVoltaInicio.setOnAction(actionEvent -> {
            fsm.regressaFaseAnterior();
        });
    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.ATRIBUICAO_PROPOSTA) {
            this.setVisible(false);
            return;
        }
        listaAlunosSemPropAt.getItems().clear();
        listaAlunosSemPropAt.getItems().addAll(fsm.listaAlunosSemAtribuicao());
        listaAlunosCandidatos.getItems().clear();
        listaAlunosCandidatos.getItems().addAll(fsm.listaAlunosComCand());
        listaAlunosT3.getItems().clear();
        listaAlunosT3.getItems().addAll(fsm.listaAlunosComAutoProp());
        listaAlunosPropAt.getItems().clear();
        listaAlunosPropAt.getItems().addAll(fsm.listaPropostaAtribuida());
        listaPropAuto.getItems().clear();
        listaPropAuto.getItems().addAll(fsm.listaAutopropostas());
        listaPropDoc.getItems().clear();
        listaPropDoc.getItems().addAll(fsm.listaProjetoAtribuido());
        listaPropDisp.getItems().clear();
        listaPropDisp.getItems().addAll(fsm.listaPropostasDisp());
        listaPropAt.getItems().clear();
        listaPropAt.getItems().addAll(fsm.listaPropostasAtribuidas());
        this.setVisible(true);
    }
    }

package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class GereCandidaturasUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnlistaAlunos,btnlistaPropostas,btnimporta,btnExporta,btnFecharFase,btnRegressar,
            btnAvanca,btnCandidatura,btnInserir,btnConsultar,btnlistaAlunosT3,btnlistaAlunosCandidatos,btnlistaAlunosNCandidatos,btnListaPT3,btnListaPT2,btnListaPCand,btnListaPSemCand,btnVolta, btnVoltaAlunos, btnVoltaPropostas;
    ListView listaAlunosT3 = new ListView<>(),listaAlunosCandidatos = new ListView<>(),listaAlunosNCandidatos = new ListView<>(),
            listaPropT3= new ListView<>(),listaPropT2= new ListView<>(),listaPropCand= new ListView<>(),listaPropSemCand= new ListView<>(),listaCandidaturas= new ListView<>();
    VBox propostasvbox,boxCandidatura,boxbtns,boxlistaCandidatura,boxlistaAlunos,boxListaPropostas,alunosT3Vbox,alunosCandidatosVbox,alunosNCandidatosVbox,propsT3box,propsT2box,propsCandbox,propsSemCandbox;

    String filename;
    private FileChooser fileChooser;
    static int clicks = 0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;

    public GereCandidaturasUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnCandidatura = new ToggleButton("Gere Candidaturas");
        btnCandidatura.setPrefHeight(TOGGLE_HEIGHT);
        btnCandidatura.setPrefWidth(TOGGLE_WIDTH);

        btnlistaAlunos = new ToggleButton("Lista Alunos");
        btnlistaAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnlistaAlunos.setPrefWidth(TOGGLE_WIDTH);

        btnlistaAlunosT3 = new ToggleButton("Alunos AutoPropostos");
        btnlistaAlunosT3.setPrefHeight(TOGGLE_HEIGHT);
        btnlistaAlunosT3.setPrefWidth(TOGGLE_WIDTH);

        btnlistaAlunosCandidatos = new ToggleButton("Alunos Candidatos");
        btnlistaAlunosCandidatos.setPrefHeight(TOGGLE_HEIGHT);
        btnlistaAlunosCandidatos.setPrefWidth(TOGGLE_WIDTH);

        btnlistaAlunosNCandidatos = new ToggleButton("Alunos Sem Candidatura");
        btnlistaAlunosNCandidatos.setPrefHeight(TOGGLE_HEIGHT);
        btnlistaAlunosNCandidatos.setPrefWidth(TOGGLE_WIDTH);

        btnListaPT3 = new ToggleButton("Auto Propostas");
        btnListaPT3.setPrefHeight(TOGGLE_HEIGHT);
        btnListaPT3.setPrefWidth(TOGGLE_WIDTH);

        btnListaPT2 = new ToggleButton("Propostas de Docentes");
        btnListaPT2.setPrefHeight(TOGGLE_HEIGHT);
        btnListaPT2.setPrefWidth(TOGGLE_WIDTH);

        btnListaPCand = new ToggleButton("Propostas com Candidatura");
        btnListaPCand.setPrefHeight(TOGGLE_HEIGHT);
        btnListaPCand.setPrefWidth(TOGGLE_WIDTH);

        btnListaPSemCand = new ToggleButton("Propostas Sem Candidatura");
        btnListaPSemCand.setPrefHeight(TOGGLE_HEIGHT);
        btnListaPSemCand.setPrefWidth(TOGGLE_WIDTH);


        btnVolta = new ToggleButton("Regressar");
        btnVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnVolta.setPrefWidth(TOGGLE_WIDTH);
        btnVoltaAlunos = new ToggleButton("Regressar");
        btnVoltaAlunos.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaAlunos.setPrefWidth(TOGGLE_WIDTH);
        btnVoltaPropostas = new ToggleButton("Regressar");
        btnVoltaPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnVoltaPropostas.setPrefWidth(TOGGLE_WIDTH);
        boxlistaAlunos = new VBox(btnlistaAlunosT3,btnlistaAlunosCandidatos, btnlistaAlunosNCandidatos, btnVoltaAlunos);
        boxListaPropostas = new VBox(btnListaPT3,btnListaPT2, btnListaPCand,btnListaPSemCand, btnVoltaPropostas);

        btnlistaPropostas = new ToggleButton("Lista Propostas");
        btnlistaPropostas.setPrefHeight(TOGGLE_HEIGHT);
        btnlistaPropostas.setPrefWidth(TOGGLE_WIDTH);

        btnimporta = new ToggleButton("Importar Candidaturas");
        btnimporta.setPrefHeight(TOGGLE_HEIGHT);
        btnimporta.setPrefWidth(TOGGLE_WIDTH);

        btnExporta = new ToggleButton("Exportar Candidaturas");
        btnExporta.setPrefHeight(TOGGLE_HEIGHT);
        btnExporta.setPrefWidth(TOGGLE_WIDTH);

        btnRegressar = new ToggleButton("Regressar");
        btnRegressar.setPrefHeight(TOGGLE_HEIGHT);
        btnRegressar.setPrefWidth(TOGGLE_WIDTH);

        btnFecharFase = new ToggleButton("Fechar Fase");
        btnFecharFase.setPrefHeight(TOGGLE_HEIGHT);
        btnFecharFase.setPrefWidth(TOGGLE_WIDTH);

        btnAvanca = new ToggleButton("Avançar Fase");
        btnAvanca.setPrefHeight(TOGGLE_HEIGHT);
        btnAvanca.setPrefWidth(TOGGLE_WIDTH);

        btnInserir = new ToggleButton("Inserir");
        btnInserir.setPrefHeight(TOGGLE_HEIGHT);
        btnInserir.setPrefWidth(TOGGLE_WIDTH);

        btnConsultar = new ToggleButton("Consultar");
        btnConsultar.setPrefHeight(TOGGLE_HEIGHT);
        btnConsultar.setPrefWidth(TOGGLE_WIDTH);

        boxbtns = new VBox(btnCandidatura,btnlistaAlunos,btnlistaPropostas,btnimporta,btnExporta,btnFecharFase, btnAvanca,btnRegressar);
        boxbtns.setAlignment(Pos.CENTER);
        boxbtns.setSpacing(10);
        this.setCenter(boxbtns);
        alunosT3Vbox = new VBox(listaAlunosT3);
        alunosT3Vbox.setVisible(false);
        alunosT3Vbox.setPrefWidth(500);
        alunosT3Vbox.setManaged(false);

        alunosCandidatosVbox = new VBox(listaAlunosCandidatos);
        alunosCandidatosVbox.setVisible(false);
        alunosCandidatosVbox.setPrefWidth(500);
        alunosCandidatosVbox.setManaged(false);

        alunosNCandidatosVbox = new VBox(listaAlunosNCandidatos);
        alunosNCandidatosVbox.setVisible(false);
        alunosNCandidatosVbox.setPrefWidth(500);
        alunosNCandidatosVbox.setManaged(false);


        propsT3box = new VBox(listaPropT3);
        propsT3box.setVisible(false);
        propsT3box.setPrefWidth(500);
        propsT3box.setManaged(false);

        propsT2box = new VBox(listaPropT2);
        propsT2box.setVisible(false);
        propsT2box.setPrefWidth(500);
        propsT2box.setManaged(false);

        propsCandbox = new VBox(listaPropCand);
        propsCandbox.setVisible(false);
        propsCandbox.setPrefWidth(500);
        propsCandbox.setManaged(false);

        propsSemCandbox = new VBox(listaPropSemCand);
        propsSemCandbox.setVisible(false);
        propsSemCandbox.setPrefWidth(500);
        propsSemCandbox.setManaged(false);

        boxCandidatura = new VBox(btnInserir,btnConsultar,btnVolta);
        boxCandidatura.setAlignment(Pos.CENTER);
        boxCandidatura.setSpacing(10);
        boxCandidatura.setVisible(false);
        boxCandidatura.setManaged(false);
        boxlistaCandidatura = new VBox(listaCandidaturas);
        boxlistaCandidatura.setSpacing(10);
        boxlistaCandidatura.setPrefWidth(500);
        boxlistaCandidatura.setAlignment(Pos.CENTER);
        boxlistaCandidatura.setVisible(false);
        boxlistaCandidatura.setManaged(false);
    }
    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> {
            update();
        });
        btnCandidatura.setOnAction(a -> {
                boxbtns.setVisible(false);
                boxbtns.setManaged(false);

                boxCandidatura.setVisible(true);
                boxCandidatura.setManaged(true);
                this.setCenter(boxCandidatura);

            btnInserir.setOnAction(event -> {
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Gestão de Candidaturas");
                dialog.setHeaderText("Inserir Candidatura");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));
                TextField id = new TextField();
                id.setPromptText("2019133920");
                TextField p1 = new TextField();
                p1.setPromptText("P010");
                TextField p2 = new TextField();
                p2.setPromptText("P011");
                TextField p3 = new TextField();
                p3.setPromptText("P027");
                TextField p4 = new TextField();
                p4.setPromptText("P023");
                TextField p5 = new TextField();
                p5.setPromptText("P031");
                TextField p6 = new TextField();
                p6.setPromptText("P022");

                grid.add(new Label("Número de aluno:"), 0, 0);
                grid.add(id, 1, 0);
                grid.add(new Label("Primeira opção:"), 0, 1);
                grid.add(p1, 1, 1);
                grid.add(new Label("Segunda opção:"), 0, 2);
                grid.add(p2, 1, 2);
                grid.add(new Label("Terceira opção:"), 0, 3);
                grid.add(p3, 1, 3);
                grid.add(new Label("Quarta opção:"), 0, 4);
                grid.add(p4, 1, 4);
                grid.add(new Label("Quinta opção:"), 0, 5);
                grid.add(p5, 1, 5);
                grid.add(new Label("Sexta opção:"), 0, 6);
                grid.add(p6, 1, 6);
                dialog.getDialogPane().setContent(grid);
                StringBuilder candidatura = new StringBuilder();


                Optional resultInserir = dialog.showAndWait();
                if (p1.getText()!=null) {
                    candidatura.append(p1.getText());
                }
                if (p2.getText()!=null) {
                    candidatura.append(" ").append(p2.getText());
                }
                if (p3.getText()!=null) {
                    candidatura.append(" ").append(p3.getText());
                }
                if (p4.getText()!=null) {
                    candidatura.append(" ").append(p4.getText());
                }
                if (p5.getText()!=null) {
                    candidatura.append(" ").append(p5.getText());
                }
                if (p6.getText()!=null) {
                    candidatura.append(" ").append(p6.getText());
                }
                resultInserir.ifPresent(t -> {
                    if (t == okButtonType) {
                            if(!fsm.insereCandidatura(Long.valueOf(id.getText()), String.valueOf(candidatura)))
                                ToastMessage.show(getScene().getWindow(), "Erro ao inserir Candidatura (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Candidatura inserida com sucesso");
                    }else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                });
            });
            btnConsultar.setOnAction(actionEvent ->{
                clicks++;
                if (clicks % 2 == 0) {
                    listaCandidaturas.setVisible(false);
                    boxlistaCandidatura.setVisible(false);
                    boxlistaCandidatura.setManaged(false);

                } else {
                    listaCandidaturas.setVisible(true);
                    boxlistaCandidatura.setVisible(true);
                    boxlistaCandidatura.setManaged(true);
                    this.setRight(boxlistaCandidatura);
                }
            });

            btnVolta.setOnAction(ev -> {
                boxCandidatura.setVisible(false);
                boxCandidatura.setManaged(false);
                boxbtns.setVisible(true);
                boxbtns.setManaged(true);
                this.setCenter(boxbtns);
                //this.setCenter(boxCandidatura);
            });

        });
        btnlistaAlunos.setOnAction(actionEvent -> {
            boxbtns.setVisible(false);
            boxbtns.setManaged(false);
            boxlistaAlunos.setVisible(true);
            boxlistaAlunos.setManaged(true);
            boxlistaAlunos.setAlignment(Pos.CENTER);
            this.setCenter(boxlistaAlunos);
            btnlistaAlunosT3.setOnAction(action -> {
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
            btnlistaAlunosCandidatos.setOnAction(action -> {
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
            btnlistaAlunosNCandidatos.setOnAction(action -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaAlunosNCandidatos.setVisible(false);
                    alunosNCandidatosVbox.setVisible(false);
                    alunosNCandidatosVbox.setManaged(false);

                } else {
                    listaAlunosNCandidatos.setVisible(true);
                    alunosNCandidatosVbox.setVisible(true);
                    alunosNCandidatosVbox.setManaged(true);
                    this.setRight(alunosNCandidatosVbox);
                }
            });
            btnVoltaAlunos.setOnAction(ev -> {
                boxlistaAlunos.setVisible(false);
                boxlistaAlunos.setManaged(false);
                boxbtns.setVisible(true);
                boxbtns.setManaged(true);
                this.setCenter(boxbtns);
                //this.setCenter(boxCandidatura);
            });
        });

        btnlistaPropostas.setOnAction(actionEvent -> {
            boxbtns.setVisible(false);
            boxbtns.setManaged(false);
            boxListaPropostas.setVisible(true);
            boxListaPropostas.setManaged(true);
            boxListaPropostas.setAlignment(Pos.CENTER);
            this.setCenter(boxListaPropostas);
            btnListaPT3.setOnAction(action -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropT3.setVisible(false);
                    propsT3box.setVisible(false);
                    propsT3box.setManaged(false);

                } else {
                    listaPropT3.setVisible(true);
                    propsT3box.setVisible(true);
                    propsT3box.setManaged(true);
                    this.setRight(propsT3box);
                }
            });
            btnListaPT2.setOnAction(action -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropT2.setVisible(false);
                    propsT2box.setVisible(false);
                    propsT2box.setManaged(false);

                } else {
                    listaPropT2.setVisible(true);
                    propsT2box.setVisible(true);
                    propsT2box.setManaged(true);
                    this.setRight(propsT2box);
                }
            });
            btnListaPCand.setOnAction(action -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropCand.setVisible(false);
                    propsCandbox.setVisible(false);
                    propsCandbox.setManaged(false);

                } else {
                    listaPropCand.setVisible(true);
                    propsCandbox.setVisible(true);
                    propsCandbox.setManaged(true);
                    this.setRight(propsCandbox);
                }
            });
            btnListaPSemCand.setOnAction(action -> {
                clicks++;
                if (clicks % 2 == 0) {
                    listaPropSemCand.setVisible(false);
                    propsSemCandbox.setVisible(false);
                    propsSemCandbox.setManaged(false);

                } else {
                    listaPropSemCand.setVisible(true);
                    propsSemCandbox.setVisible(true);
                    propsSemCandbox.setManaged(true);
                    this.setRight(propsSemCandbox);
                }
            });
            btnVoltaPropostas.setOnAction(ev -> {
                boxListaPropostas.setVisible(false);
                boxListaPropostas.setManaged(false);
                boxbtns.setVisible(true);
                boxbtns.setManaged(true);
                this.setCenter(boxbtns);
                //this.setCenter(boxCandidatura);
            });
        });
        btnimporta.setOnAction(actionEvent -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Seleciona o ficheiro");
            fileChooser.setInitialDirectory(new File("..\\TemplateTP"));
            Label label = new Label("no files selected");
            Stage stage = (Stage) btnimporta.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {

                filename = file.getName();
                label.setText(file.getAbsolutePath()
                        + "  selected");
                try {;
                    if(!fsm.importaCandidaturas(filename)) ToastMessage.show(getScene().getWindow(), "Erro Importa (Importa Propostas!)");
                    else ToastMessage.show(getScene().getWindow(), "Importa Candidaturas Bem Sucedido");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        });
        btnFecharFase.setOnAction(actionEvent -> {
            if(!fsm.fecharFaseCandidatura())
                ToastMessage.show(getScene().getWindow(), "Fase IniciaConfig aberta");
        });
        btnRegressar.setOnAction(actionEvent -> {
            fsm.regressaFaseAnterior();
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
                    fsm.exportaCsvCandidaturas(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        btnAvanca.setOnAction(actionEvent -> {
            fsm.avancarFase();
        });
    }
    private void update() {

        if (fsm.getState() != GestaoEstagioState.CANDIDATURA) {
            this.setVisible(false);
            return;
        }
        listaCandidaturas.getItems().clear();
        listaAlunosT3.getItems().clear();
        listaAlunosCandidatos.getItems().clear();
        listaAlunosNCandidatos.getItems().clear();
        listaPropT3.getItems().clear();
        listaPropT2.getItems().clear();
        listaPropCand.getItems().clear();
        listaPropSemCand.getItems().clear();

        listaCandidaturas.getItems().addAll(fsm.consultaCandidaturas());
        listaAlunosT3.getItems().addAll(fsm.listaAutoproposta());
        listaAlunosCandidatos.getItems().addAll(fsm.listaComCandidaturas());
        listaAlunosNCandidatos.getItems().addAll(fsm.listaSemCandidaturas());
        listaPropT3.getItems().addAll(fsm.listaAutopropostas());
        listaPropT2.getItems().addAll(fsm.listaProjetos());
        listaPropCand.getItems().addAll(fsm.listaPropComCand());
        listaPropSemCand.getItems().addAll(fsm.listaPropSemCand());

        this.setVisible(true);
    }

}
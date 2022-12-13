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
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Projeto;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;

import java.io.File;
import java.util.Optional;

public class AtribuicaoOrientadorUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnVolta,btnAtOr,btnAtOrAuto,btnAtOrManual,btnAtOrConsulta,btnAtOrEdita,btnAtOrRemove,
            btnAtOrVolta,btnOr,btnOrAt,btnOrNaoAt,btnOrTotal,btnExporta,btnOrVolta,btnAvanca,btnFechaFase;
    private String filename;
    VBox vBoxBtns,vBoxAtOr,vBoxOr,listaProponenteVbox,listaAtribuidosVBox,listaNAtribuidosVBox,listaNOrientacoesVBox;
    ListView listaProponente,listaAtribuidos,listaNAtribuidos,listaNOrientacoes;
    private static int clicks=0;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    public AtribuicaoOrientadorUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnVolta = new ToggleButton("Regressa");
        btnVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnVolta.setPrefWidth(TOGGLE_WIDTH);

        btnAtOr = new ToggleButton("Atribuição Orientadores");
        btnAtOr.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOr.setPrefWidth(TOGGLE_WIDTH);

        btnAvanca = new ToggleButton("Avançar Fase");
        btnAvanca.setPrefHeight(TOGGLE_HEIGHT);
        btnAvanca.setPrefWidth(TOGGLE_WIDTH);

        btnFechaFase = new ToggleButton("Fechar Fase");
        btnFechaFase.setPrefHeight(TOGGLE_HEIGHT);
        btnFechaFase.setPrefWidth(TOGGLE_WIDTH);

        btnExporta = new ToggleButton("Exporta");
        btnExporta.setPrefHeight(TOGGLE_HEIGHT);
        btnExporta.setPrefWidth(TOGGLE_WIDTH);


        btnAtOrAuto = new ToggleButton("Proponente Automático");
        btnAtOrAuto.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrAuto.setPrefWidth(TOGGLE_WIDTH);
        btnAtOrManual = new ToggleButton("Proponente Manual");
        btnAtOrManual.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrManual .setPrefWidth(TOGGLE_WIDTH);
        btnAtOrConsulta = new ToggleButton("Consulta Proponente");
        btnAtOrConsulta.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrConsulta.setPrefWidth(TOGGLE_WIDTH);
        btnAtOrEdita = new ToggleButton("Edita Proponente");
        btnAtOrEdita.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrEdita.setPrefWidth(TOGGLE_WIDTH);
        btnAtOrRemove = new ToggleButton("Remove Proponente Manual");
        btnAtOrRemove.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrRemove.setPrefWidth(TOGGLE_WIDTH);
        btnAtOrVolta = new ToggleButton("Regressa");
        btnAtOrVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnAtOrVolta.setPrefWidth(TOGGLE_WIDTH);

        btnOr = new ToggleButton("Dados Orientadores");
        btnOr.setPrefHeight(TOGGLE_HEIGHT);
        btnOr.setPrefWidth(TOGGLE_WIDTH);

        btnOrAt= new ToggleButton("Atribuídos");
        btnOrAt.setPrefHeight(TOGGLE_HEIGHT);
        btnOrAt.setPrefWidth(TOGGLE_WIDTH);
        btnOrNaoAt = new ToggleButton("Não Atribuidos");
        btnOrNaoAt.setPrefHeight(TOGGLE_HEIGHT);
        btnOrNaoAt .setPrefWidth(TOGGLE_WIDTH);
        btnOrTotal = new ToggleButton("Número Orientações");
        btnOrTotal.setPrefHeight(TOGGLE_HEIGHT);
        btnOrTotal.setPrefWidth(TOGGLE_WIDTH);
        btnOrVolta = new ToggleButton("Regressa");
        btnOrVolta.setPrefHeight(TOGGLE_HEIGHT);
        btnOrVolta.setPrefWidth(TOGGLE_WIDTH);


        vBoxOr = new VBox(btnOrAt,btnOrNaoAt,btnOrTotal,btnOrVolta);
        vBoxOr.setAlignment(Pos.CENTER);
        vBoxOr.setSpacing(10);
        vBoxOr.setVisible(false);
        vBoxOr.setManaged(false);

        vBoxAtOr = new VBox(btnAtOrAuto, btnAtOrManual, btnAtOrConsulta, btnAtOrEdita,btnAtOrRemove, btnAtOrVolta);
        vBoxAtOr.setAlignment(Pos.CENTER);
        vBoxAtOr.setSpacing(10);
        vBoxAtOr.setVisible(false);
        vBoxAtOr.setManaged(false);

        vBoxBtns = new VBox(btnAtOr,btnOr,btnFechaFase,btnExporta,btnAvanca,btnVolta);
        vBoxBtns.setAlignment(Pos.CENTER);
        vBoxBtns.setSpacing(10);
        this.setCenter(vBoxBtns);

        listaProponente = new ListView<>();
        listaProponenteVbox = new VBox(listaProponente);
        listaProponenteVbox.prefWidth(500);
        listaProponenteVbox.setAlignment(Pos.CENTER_RIGHT);
        listaProponenteVbox.setVisible(false);
        listaProponenteVbox.setManaged(false);
        this.setRight(listaProponenteVbox);

        listaAtribuidos = new ListView<>();
        listaAtribuidosVBox = new VBox(listaAtribuidos);
        listaAtribuidosVBox.prefWidth(500);
        listaAtribuidosVBox.setAlignment(Pos.CENTER_RIGHT);
        listaAtribuidosVBox.setVisible(false);
        listaAtribuidosVBox.setManaged(false);
        this.setRight(listaAtribuidosVBox);

        listaNAtribuidos = new ListView<>();
        listaNAtribuidosVBox = new VBox(listaNAtribuidos);
        listaNAtribuidosVBox.prefWidth(500);
        listaNAtribuidosVBox.setAlignment(Pos.CENTER_RIGHT);
        listaNAtribuidosVBox.setVisible(false);
        listaNAtribuidosVBox.setManaged(false);
        this.setRight(listaNAtribuidosVBox);

        listaNOrientacoes = new ListView<>();
        listaNOrientacoesVBox = new VBox(listaNOrientacoes);
        listaNOrientacoesVBox.prefWidth(500);
        listaNOrientacoesVBox.setAlignment(Pos.CENTER_RIGHT);
        listaNOrientacoesVBox.setVisible(false);
        listaNOrientacoesVBox.setManaged(false);
        this.setRight(listaNOrientacoesVBox);
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> {
            update();
        });
        btnAtOr.setOnAction(actionEvent -> {
            vBoxBtns.setVisible(false);
            vBoxBtns.setManaged(false);
            vBoxAtOr.setVisible(true);
            vBoxAtOr.setManaged(true);
            vBoxAtOr.setAlignment(Pos.CENTER);
            this.setCenter(vBoxAtOr);
            btnAtOrAuto.setOnAction(Event -> {
                if(!fsm.atribuiProponentesProjeto())
                    ToastMessage.show(getScene().getWindow(),"Não há nada para atribuir");
                else ToastMessage.show(getScene().getWindow(),"Proponente Atribuído com Sucesso");
            });
            btnAtOrManual.setOnAction(Event -> {
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Gestão Orientador");
                dialog.setHeaderText("Atribui Orientador");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField email = new TextField();
                email.setPromptText("2015123456");
                TextField codProp = new TextField();
                codProp.setPromptText("P010");


                grid.add(new Label("Email Docente:"), 0, 4);
                grid.add(email, 1, 4);
                grid.add(new Label("Cod Proposta:"), 0, 0);
                grid.add(codProp, 1, 0);
                dialog.getDialogPane().setContent(grid);

                Optional result = dialog.showAndWait();
                result.ifPresent(t -> {
                    if (t == okButtonType) {
                        if (!fsm.atribuiProponenteManual(codProp.getText(),email.getText())) {
                            ToastMessage.show(getScene().getWindow(), "Sem proponentes de projeto para atribuir");
                        }else ToastMessage.show(getScene().getWindow(), "Atribui proponente com sucesso");
                    }else{
                        ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    }
                });
            });

            btnAtOrRemove.setOnAction(Event -> {
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Gestão Orientador");
                dialog.setHeaderText("Atribui Orientador");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField email = new TextField();
                email.setPromptText("2015123456");
                TextField codProp = new TextField();
                codProp.setPromptText("P010");


                grid.add(new Label("Email Docente:"), 0, 4);
                grid.add(email, 1, 4);
                grid.add(new Label("Cod Proposta:"), 0, 0);
                grid.add(codProp, 1, 0);
                dialog.getDialogPane().setContent(grid);

                Optional result = dialog.showAndWait();
                result.ifPresent(t -> {
                    if (t == okButtonType) {
                        if (!fsm.removeProponenteManual(codProp.getText(),email.getText())) {
                            ToastMessage.show(getScene().getWindow(), "Sem proponentes de projeto para remover");
                        }else ToastMessage.show(getScene().getWindow(), "Removeu Proponente com sucesso");
                    }else{
                        ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    }
                });
            });

            btnAtOrConsulta.setOnAction(Event->{
                clicks++;
                if(clicks % 2 ==0){
                    listaProponente.setVisible(false);
                    listaProponenteVbox.setVisible(false);
                    listaProponenteVbox.setManaged(false);

                }else{
                    listaProponente.setVisible(true);
                    listaProponenteVbox.setVisible(true);
                    listaProponenteVbox.setManaged(true);
                    this.setRight(listaProponenteVbox);
                }
            });
            btnAtOrEdita.setOnAction(ev ->{
                Propostas proposta ;
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setTitle("Editar");
                dialog1.setHeaderText("Editar Proponente Projeto");
                dialog1.setContentText("Digite proposta do docente:");

// Traditional way to get the response value.
                Optional<String> result = dialog1.showAndWait();
                if(result.isPresent()){
                    proposta = fsm.getProposta(result.get());
                }
                else{
                    ToastMessage.show(getScene().getWindow(), "Proposta Invalida");
                    fsm.regressaFaseAnterior();
                    return;
                }
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Edição"+proposta.getCodProposta());
                dialog.setHeaderText("Editar Proponete Projeto");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField email = new TextField();
                email.setText(((Projeto) proposta).getEmailDocente());

                grid.add(new Label("Email:"), 0, 0);
                grid.add(email, 1, 0);

                dialog.getDialogPane().setContent(grid);

                Optional resultEditar = dialog.showAndWait();

                resultEditar.ifPresent(t -> {
                    if (t == okButtonType) {
                        if (!((Projeto) proposta).getEmailDocente().equals(email.getText()))
                            if(!fsm.alteraProponente(proposta.getCodProposta(),email.getText()))
                                ToastMessage.show(getScene().getWindow(), "Erro ao editar proponente projeto");
                    }
                });

            });
            btnAtOrVolta.setOnAction(Event -> {
                vBoxAtOr.setVisible(false);
                vBoxAtOr.setManaged(false);
                vBoxBtns.setVisible(true);
                vBoxBtns.setManaged(true);
                vBoxBtns.setAlignment(Pos.CENTER);
                this.setCenter(vBoxBtns);
            });
        });
        btnOr.setOnAction(actionEvent -> {
            vBoxBtns.setVisible(false);
            vBoxBtns.setManaged(false);
            vBoxOr.setVisible(true);
            vBoxOr.setManaged(true);
            vBoxOr.setAlignment(Pos.CENTER);
            this.setCenter(vBoxOr);

            btnOrAt.setOnAction(Event->{
                clicks++;
                if(clicks % 2 ==0){
                    listaAtribuidos.setVisible(false);
                    listaAtribuidosVBox.setVisible(false);
                    listaAtribuidosVBox.setManaged(false);

                }else{
                    listaAtribuidos.setVisible(true);
                    listaAtribuidosVBox.setVisible(true);
                    listaAtribuidosVBox.setManaged(true);
                    this.setRight(listaAtribuidosVBox);
                }
            });
            btnOrNaoAt.setOnAction(Event->{
                clicks++;
                if(clicks % 2 ==0){
                    listaNAtribuidos.setVisible(false);
                    listaNAtribuidosVBox.setVisible(false);
                    listaNAtribuidosVBox.setManaged(false);

                }else{
                    listaNAtribuidos.setVisible(true);
                    listaNAtribuidosVBox.setVisible(true);
                    listaNAtribuidosVBox.setManaged(true);
                    this.setRight(listaNAtribuidosVBox);
                }
            });

            btnOrTotal.setOnAction(Event->{
                clicks++;
                if(clicks % 2 ==0){
                    listaNOrientacoes.setVisible(false);
                    listaNOrientacoesVBox.setVisible(false);
                    listaNOrientacoesVBox.setManaged(false);

                }else{
                    listaNOrientacoes.setVisible(true);
                    listaNOrientacoesVBox.setVisible(true);
                    listaNOrientacoesVBox.setManaged(true);
                    this.setRight(listaNOrientacoesVBox);
                }
            });

            btnOrVolta.setOnAction(Event -> {
                vBoxOr.setVisible(false);
                vBoxOr.setManaged(false);
                vBoxBtns.setVisible(true);
                vBoxBtns.setManaged(true);
                vBoxBtns.setAlignment(Pos.CENTER);
                this.setCenter(vBoxBtns);
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
                    fsm.exportaCsvFase4(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnAvanca.setOnAction(actionEvent -> {
            fsm.avancarFase();
        });
        btnFechaFase.setOnAction(actionEvent -> {
            fsm.fecharFaseOrientador();
        });
        btnVolta.setOnAction(actionEvent -> {
            fsm.regressaFaseAnterior();
        });
    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.ATRIBUICAO_ORIENTADOR) {
            this.setVisible(false);
            return;
        }
        listaProponente.getItems().clear();
        listaProponente.getItems().addAll(fsm.consultaProponente());
        listaAtribuidos.getItems().clear();
        listaAtribuidos.getItems().addAll(fsm.listaAlunoComOrientador());
        listaNAtribuidos.getItems().clear();
        listaNAtribuidos.getItems().addAll(fsm.listaAlunoSemOrientador());
        listaNOrientacoes.getItems().clear();
        listaNOrientacoes.getItems().addAll(fsm.listanOrientacoes());
        this.setVisible(true);
    }
}


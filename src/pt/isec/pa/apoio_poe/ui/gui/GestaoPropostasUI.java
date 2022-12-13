package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import pt.isec.pa.apoio_poe.model.data.projetodata.Estagio;
import pt.isec.pa.apoio_poe.model.data.projetodata.Projeto;
import pt.isec.pa.apoio_poe.model.data.projetodata.ProjetoAutoProposto;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;
import java.util.Optional;

public class GestaoPropostasUI extends BorderPane {
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;

    GestaoEstagioManager fsm;
    static int clicks = 0;
    ToggleButton btnEstagio,btnProjeto,btnAutoproposta,btnRegressar;
    ToggleButton btnInserir,btnEditar,btnEliminar,btnConsultar;
    ToggleGroup group;
    VBox listaVbox;
    HBox buttons;
    ListView <Propostas> listView;

    public GestaoPropostasUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnEstagio = new ToggleButton("Estagio");
        btnEstagio.setPrefHeight(TOGGLE_HEIGHT);
        btnEstagio.setPrefWidth(TOGGLE_WIDTH);
        btnProjeto = new ToggleButton("Projeto");
        btnProjeto.setPrefHeight(TOGGLE_HEIGHT);
        btnProjeto.setPrefWidth(TOGGLE_WIDTH);
        btnAutoproposta = new ToggleButton("Auto Proposta");
        btnAutoproposta.setPrefHeight(TOGGLE_HEIGHT);
        btnAutoproposta.setPrefWidth(TOGGLE_WIDTH);
        btnRegressar = new ToggleButton("Regressar");
        btnRegressar.setPrefHeight(TOGGLE_HEIGHT);
        btnRegressar.setPrefWidth(TOGGLE_WIDTH);
        group = new ToggleGroup();
        group.getToggles().addAll(btnEstagio, btnProjeto,btnAutoproposta);

        buttons= new HBox(btnEstagio,btnProjeto,btnAutoproposta);
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(10);
        buttons.setVisible(true);
        buttons.setManaged(true);
        this.setTop(buttons);

        btnInserir = new ToggleButton("Inserir");
        btnInserir.setPrefHeight(TOGGLE_HEIGHT);
        btnInserir.setPrefWidth(TOGGLE_WIDTH);
        btnInserir.disableProperty().bind(group.selectedToggleProperty().isNull());
        btnEditar = new ToggleButton("Editar");
        btnEditar.setPrefHeight(TOGGLE_HEIGHT);
        btnEditar.setPrefWidth(TOGGLE_WIDTH);
        btnEditar.disableProperty().bind(group.selectedToggleProperty().isNull());
        btnEliminar = new ToggleButton("Eliminar");
        btnEliminar.setPrefHeight(TOGGLE_HEIGHT);
        btnEliminar.setPrefWidth(TOGGLE_WIDTH);
        btnEliminar.disableProperty().bind(group.selectedToggleProperty().isNull());
        btnConsultar = new ToggleButton("Consultar");
        btnConsultar.setPrefHeight(TOGGLE_HEIGHT);
        btnConsultar.setPrefWidth(TOGGLE_WIDTH);
        btnConsultar.disableProperty().bind(group.selectedToggleProperty().isNull());
        VBox vbox = new VBox(btnInserir,btnEditar,btnEliminar,btnConsultar,btnRegressar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        this.setCenter(vbox);
        listView= new ListView<>();
        listaVbox = new VBox(listView);
        listaVbox.setVisible(false);
        listaVbox.setPrefWidth(500);
        this.setRight(listaVbox);
        listaVbox.setManaged(false);
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());

        btnInserir.setOnAction(e -> {
            ToggleButton selected = (ToggleButton)group.getSelectedToggle();
            if (selected != null) {
                if(btnEstagio.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Inserir Estágio");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 20, 10, 10));
                    //T1,P010,DA,Aplicação para gestão de condomínios,Caves e Sótãos.Lda,2015123456
                    TextField codProp = new TextField();
                    codProp.setPromptText("P010");
                    CheckBox da = new CheckBox("DA");
                    CheckBox si = new CheckBox("SI");
                    CheckBox ras = new CheckBox("RAS");
                    HBox ramos = new HBox(da, si, ras);
                    ramos.setSpacing(10);
                    TextField titulo = new TextField();
                    titulo.setPromptText("Aplicação para gestão de condomínios");
                    TextField inst = new TextField();
                    inst.setPromptText("Caves e Sótãos.Lda");
                    TextField nr = new TextField();
                    nr.setPromptText("2015123456");

                    grid.add(new Label("Cod Proposta:"), 0, 0);
                    grid.add(codProp, 1, 0);
                    grid.add(new Label("Ramo:"), 0, 1);
                    grid.add(ramos, 1, 1);
                    grid.add(new Label("Titulo:"), 0, 2);
                    grid.add(titulo, 1, 2);
                    grid.add(new Label("Instituição:"), 0, 3);
                    grid.add(inst, 1, 3);
                    grid.add(new Label("Nr de Aluno:"), 0, 4);
                    grid.add(nr, 1, 4);

                    da.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if(!aBoolean) {
                            ras.setDisable(true);
                            si.setDisable(true);
                        }
                        else{
                            ras.setDisable(false);
                            si.setDisable(false);
                        }
                    });
                    ras.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if(!aBoolean) {
                            da.setDisable(true);
                            si.setDisable(true);
                        }else{
                            da.setDisable(false);
                            si.setDisable(false);
                        }
                    });
                    si.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if(!aBoolean) {
                            da.setDisable(true);
                            ras.setDisable(true);
                        }else{
                            da.setDisable(false);
                            ras.setDisable(false);
                        }
                    });

                    dialog.getDialogPane().setContent(grid);

                    Optional resultInserir = dialog.showAndWait();

                    resultInserir.ifPresent(t -> {
                        if (t == okButtonType) {
                            boolean f=true;
                            if (!da.isDisable()){if(!fsm.insereEstagio(codProp.getText(),da.getText(),titulo.getText(),inst.getText(),Long.parseLong(nr.getText())))f=false;}
                            if (!si.isDisable()){if(!fsm.insereEstagio(codProp.getText(),si.getText(),titulo.getText(),inst.getText(),Long.parseLong(nr.getText())))f=false;}
                            if (!ras.isDisable()){if(!fsm.insereEstagio(codProp.getText(),ras.getText(),titulo.getText(),inst.getText(),Long.parseLong(nr.getText())))f=false;}
                            if(f) ToastMessage.show(getScene().getWindow(), "Proposta inserida com sucesso");
                            else ToastMessage.show(getScene().getWindow(), "Erro ao Inserir Estagio (Dados Invalidos)");
                        }
                    });
                }
                if(btnProjeto.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Inserir Projeto");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 20, 10, 10));
                    //T2,P027,RAS|SI|DA,Shared Wallet,ans@isec.pt,2022987654
                    TextField codProp = new TextField();
                    codProp.setPromptText("P027");
                    CheckBox da = new CheckBox("DA");
                    CheckBox si = new CheckBox("SI");
                    CheckBox ras = new CheckBox("RAS");
                    HBox ramos = new HBox(da, si, ras);
                    TextField titulo = new TextField();
                    titulo.setPromptText("Shared Wallet");
                    TextField email = new TextField();
                    email.setPromptText("ans@isec.pt");
                    TextField nr = new TextField();
                    nr.setPromptText("2015123456");

                    grid.add(new Label("Cod Proposta:"), 0, 0);
                    grid.add(codProp, 1, 0);
                    grid.add(new Label("Ramo:"), 0, 1);
                    grid.add(ramos, 1, 1);
                    grid.add(new Label("Titulo:"), 0, 2);
                    grid.add(titulo, 1, 2);
                    grid.add(new Label("Email:"), 0, 3);
                    grid.add(email, 1, 3);
                    grid.add(new Label("Nr de Aluno:"), 0, 4);
                    grid.add(nr, 1, 4);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultInserir = dialog.showAndWait();

                    resultInserir.ifPresent(t -> {
                        if (t == okButtonType) {
                            boolean f=true;
                            if (!da.isDisable()){if(!fsm.insereProjeto(codProp.getText(),da.getText(),titulo.getText(),email.getText(),Long.parseLong(nr.getText())))f=false;}
                            if (!si.isDisable()){if(!fsm.insereProjeto(codProp.getText(),si.getText(),titulo.getText(),email.getText(),Long.parseLong(nr.getText())))f=false;}
                            if (!ras.isDisable()){if(!fsm.insereProjeto(codProp.getText(),ras.getText(),titulo.getText(),email.getText(),Long.parseLong(nr.getText())))f=false;}

                            if(!f)ToastMessage.show(getScene().getWindow(), "Erro ao Inserir Projeto (Dados Invalidos)");
                        }
                    });
                }
                if(btnAutoproposta.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Inserir Auto Proposta");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 20, 10, 10));
                    //T3,P007,Comunicação através de sinais de fumo,2020111111
                    TextField codProp = new TextField();
                    codProp.setPromptText("P007");
                    TextField titulo = new TextField();
                    titulo.setPromptText("Comunicação através de sinais de fumo");
                    TextField nr = new TextField();
                    nr.setPromptText("2020111111");

                    grid.add(new Label("Cod Proposta:"), 0, 0);
                    grid.add(codProp, 1, 0);
                    grid.add(new Label("Titulo:"), 0, 1);
                    grid.add(titulo, 1, 1);
                    grid.add(new Label("Nr de Aluno:"), 0, 2);
                    grid.add(nr, 1, 2);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultInserir = dialog.showAndWait();

                    resultInserir.ifPresent(t -> {
                        if (t == okButtonType) {
                            if(!fsm.insereAutoproposto(codProp.getText(),titulo.getText(),Long.parseLong(nr.getText())))
                                ToastMessage.show(getScene().getWindow(), "Erro ao Inserir AutoProposta (Dados Invalidos)");
                        }
                    });
                }
            }
        });
        btnEditar.setOnAction(ev -> {
            ToggleButton selected = (ToggleButton)group.getSelectedToggle();
            if (selected != null) {
                if(btnEstagio.isSelected()) {
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Editar Estagio");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEditar = dialog.showAndWait();
                    Estagio e;

                    if (resultEditar.isPresent()) {
                        e = (Estagio) fsm.getProposta(cod.getText());
                        if(e==null){
                            ToastMessage.show(getScene().getWindow(), "Estagio Invalido");
                            fsm.regressaFaseAnterior();
                            return;
                        }
                    } else {
                        ToastMessage.show(getScene().getWindow(), "Estagio Invalido");
                        fsm.regressaFaseAnterior();
                        return;
                    }

                    Dialog<Pair<String, String>> dialog2 = new Dialog<>();
                    dialog2.setTitle("Edição de Estágio");
                    dialog2.setHeaderText("Editar Estágio");

                    ButtonType okButtonType2 = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog2.getDialogPane().getButtonTypes().addAll(okButtonType2, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid2 = new GridPane();
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    grid2.setPadding(new Insets(20, 20, 10, 10));
                    //T1,P010,DA,Aplicação para gestão de condomínios,Caves e Sótãos.Lda,2015123456
                    CheckBox da = new CheckBox("DA");
                    CheckBox si = new CheckBox("SI");
                    CheckBox ras = new CheckBox("RAS");
                    switch (e.getSiglaRamoEstagio()) {
                        case "DA" -> da.setSelected(true);
                        case "SI" -> si.setSelected(true);
                        case "RAS" -> ras.setSelected(true);
                    }
                    HBox ramos = new HBox(da, si, ras);
                    ramos.setSpacing(10);
                    TextField titulo = new TextField(e.getTitulo());
                    TextField inst = new TextField(e.getInstituicaoEstagio());
                    TextField nr = new TextField(String.valueOf(e.getNrAluno()));

                    grid2.add(new Label("Ramo:"), 0, 0);
                    grid2.add(ramos, 1, 0);
                    grid2.add(new Label("Titulo:"), 0, 1);
                    grid2.add(titulo, 1, 1);
                    grid2.add(new Label("Instituição:"), 0, 2);
                    grid2.add(inst, 1, 2);
                    grid2.add(new Label("Nr de Aluno:"), 0, 3);
                    grid2.add(nr, 1, 3);

                    da.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if (!aBoolean) {
                            ras.setDisable(true);
                            si.setDisable(true);
                        } else {
                            ras.setDisable(false);
                            si.setDisable(false);
                        }
                    });
                    ras.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if (!aBoolean) {
                            da.setDisable(true);
                            si.setDisable(true);
                        } else {
                            da.setDisable(false);
                            si.setDisable(false);
                        }
                    });
                    si.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if (!aBoolean) {
                            da.setDisable(true);
                            ras.setDisable(true);
                        } else {
                            da.setDisable(false);
                            ras.setDisable(false);
                        }
                    });

                    dialog2.getDialogPane().setContent(grid2);

                    Optional resultEditar2 = dialog2.showAndWait();

                    resultEditar2.ifPresent(t -> {
                        if (t == okButtonType2) {
                            boolean f = true;
                            if (da.isSelected()) {
                                if (!da.getText().equals(e.getSiglaRamoEstagio()))
                                    if (!fsm.editarProposta(e.getCodProposta(), da.getText(), 1)) f = false;
                            } else if (si.isSelected()) {
                                if (!si.getText().equals(e.getSiglaRamoEstagio()))
                                    if (!fsm.editarProposta(e.getCodProposta(), si.getText(), 1)) f = false;
                            } else if (ras.isSelected()) {
                                if (!ras.getText().equals(e.getSiglaRamoEstagio()))
                                    if (!fsm.editarProposta(e.getCodProposta(), ras.getText(), 1)) f = false;
                            }
                            if (!e.getTitulo().equals(titulo.getText()))
                                if(!fsm.editarProposta(e.getCodProposta(),titulo.getText(),2))f=false;
                            if (!e.getInstituicaoEstagio().equals(inst.getText())){
                                if(!fsm.editarProposta(e.getCodProposta(),inst.getText(),3))f=false;
                            }

                            if (e.getNrAluno() != Long.parseLong(nr.getText()))
                                if(!fsm.editarProposta(e.getCodProposta(),nr.getText(),4))f=false;

                            if(!f) ToastMessage.show(getScene().getWindow(), "Erro ao Editar Estagio (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Estagio Editado com sucesso");
                        }
                    });
                }
                if(btnProjeto.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Editar Projeto");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEditar = dialog.showAndWait();
                    Projeto p;

                    if(resultEditar.isPresent()){
                        p = (Projeto) fsm.getProposta(cod.getText());
                        if(p==null){
                            ToastMessage.show(getScene().getWindow(), "Projeto Invalido");
                            fsm.regressaFaseAnterior();
                            return;
                        }
                    }
                    else{
                        ToastMessage.show(getScene().getWindow(), "Projeto Invalido");
                        fsm.regressaFaseAnterior();
                        return;
                    }

                    Dialog<Pair<String, String>> dialog2 = new Dialog<>();
                    dialog2.setTitle("Edição de Projeto");
                    dialog2.setHeaderText("Editar Projeto");

                    ButtonType okButtonType2 = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog2.getDialogPane().getButtonTypes().addAll(okButtonType2, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid2 = new GridPane();
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    grid2.setPadding(new Insets(20, 20, 10, 10));
                    //T1,P010,DA,Aplicação para gestão de condomínios,Caves e Sótãos.Lda,2015123456
                    CheckBox da = new CheckBox("DA");
                    CheckBox si = new CheckBox("SI");
                    CheckBox ras = new CheckBox("RAS");
                    switch (p.getSiglaRamo()) {
                        case "DA" -> da.setSelected(true);
                        case "SI" -> si.setSelected(true);
                        case "RAS" -> ras.setSelected(true);
                    }
                    HBox ramos = new HBox(da, si, ras);
                    ramos.setSpacing(10);
                    TextField titulo = new TextField(p.getTitulo());
                    TextField email = new TextField(p.getEmailDocente());
                    TextField nr = new TextField(String.valueOf(p.getNrAluno()));

                    grid2.add(new Label("Ramo:"), 0, 0);
                    grid2.add(ramos, 1, 0);
                    grid2.add(new Label("Titulo:"), 0, 1);
                    grid2.add(titulo, 1, 1);
                    grid2.add(new Label("Email docente:"), 0, 2);
                    grid2.add(email, 1, 2);
                    grid2.add(new Label("Nr de Aluno:"), 0, 3);
                    grid2.add(nr, 1, 3);



                    dialog2.getDialogPane().setContent(grid2);

                    Optional resultEditar2 = dialog2.showAndWait();

                    resultEditar2.ifPresent(t -> {
                        if (t == okButtonType2) {
                            boolean f = true;
                            if (da.isSelected()) {
                                if (!da.getText().equals(p.getSiglaRamo()))
                                    if (!fsm.editarProposta(p.getCodProposta(), da.getText(), 1)) f = false;
                            } else if (si.isSelected()) {
                                if (!si.getText().equals(p.getSiglaRamo()))
                                    if (!fsm.editarProposta(p.getCodProposta(), si.getText(), 1)) f = false;
                            } else if (ras.isSelected()) {
                                if (!ras.getText().equals(p.getSiglaRamo()))
                                    if (!fsm.editarProposta(p.getCodProposta(), ras.getText(), 1)) f = false;
                            }
                            if (!p.getTitulo().equals(titulo.getText()))
                                if(!fsm.editarProposta(p.getCodProposta(),titulo.getText(),2))f=false;
                            if (!p.getEmailDocente().equals(email.getText()))
                                if(!fsm.editarProposta(p.getCodProposta(),email.getText(),5))f=false;
                            if (p.getNrAluno() != Long.parseLong(nr.getText()))
                                if(!fsm.editarProposta(p.getCodProposta(),nr.getText(),4))f=false;

                            if(!f) ToastMessage.show(getScene().getWindow(), "Erro ao Editar Projeto (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Projeto Editado com sucesso");
                        }
                    });
                }
                if(btnAutoproposta.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Editar Auto Proposta");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEditar = dialog.showAndWait();
                    ProjetoAutoProposto p;

                    if(resultEditar.isPresent()){
                        p = (ProjetoAutoProposto) fsm.getProposta(cod.getText());
                        if(p==null){
                            ToastMessage.show(getScene().getWindow(), "Auto Proposta Invalida");
                            fsm.regressaFaseAnterior();
                            return;
                        }
                    }
                    else{
                        ToastMessage.show(getScene().getWindow(), "Auto Proposta Invalida");
                        fsm.regressaFaseAnterior();
                        return;
                    }
                    Dialog<Pair<String, String>> dialog2 = new Dialog<>();
                    dialog2.setTitle("Edição de Auto Proposta");
                    dialog2.setHeaderText("Editar Auto Proposta");

                    ButtonType okButtonType2 = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog2.getDialogPane().getButtonTypes().addAll(okButtonType2, ButtonType.CANCEL);
// Create the username and password labels and fields.

                    GridPane grid2 = new GridPane();
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    grid2.setPadding(new Insets(20, 20, 10, 10));

                    TextField titulo = new TextField(p.getTitulo());
                    TextField nr = new TextField(String.valueOf(p.getNrAluno()));

                    grid.add(new Label("Titulo:"), 0, 0);
                    grid.add(titulo, 1, 0);
                    grid.add(new Label("Numero de Aluno:"), 0, 1);
                    grid.add(nr, 1, 1);

                    dialog2.getDialogPane().setContent(grid);

                    Optional resultEditar2 = dialog2.showAndWait();

                    resultEditar2.ifPresent(t -> {
                        if (t == okButtonType2) {
                            boolean f = true;
                            if (!p.getTitulo().equals(titulo.getText()))
                                if(!fsm.editarProposta(p.getCodProposta(),titulo.getText(),2))f=false;
                            if (p.getNrAluno() != Long.parseLong(nr.getText()))
                                if(!fsm.editarProposta(p.getCodProposta(),nr.getText(),4))f=false;

                            if(!f) ToastMessage.show(getScene().getWindow(), "Erro ao Editar AutoProposta (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Auto Proposta Editada com sucesso");
                        }
                    });

                }
            }
        });

        btnEliminar.setOnAction(ev -> {
            ToggleButton selected = (ToggleButton)group.getSelectedToggle();
            if (selected != null) {
                if(btnEstagio.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Eliminar Estagio");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEliminar = dialog.showAndWait();

                    resultEliminar.ifPresent(t -> {
                        if (t == okButtonType) {
                            if(!fsm.eliminaProposta(cod.getText()))
                                ToastMessage.show(getScene().getWindow(), "Erro ao Eliminar Estagio (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Estagio eliminado com sucesso");
                        }else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    });


                    //fsm.regressaFaseAnterior();
                }
                if(btnProjeto.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Eliminar Projeto");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEliminar = dialog.showAndWait();

                    resultEliminar.ifPresent(t -> {
                        if (t == okButtonType) {
                            if(!fsm.eliminaProposta(cod.getText()))
                                ToastMessage.show(getScene().getWindow(), "Erro ao Eliminar Projeto (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Projeto eliminado com sucesso");
                        } else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    });

                }
                if(btnAutoproposta.isSelected()){
                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Gestão de Propostas");
                    dialog.setHeaderText("Eliminar Auto Proposta");

                    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
                    // Create the username and password labels and fields.

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField cod = new TextField();
                    cod.setPromptText("P010");

                    grid.add(new Label("Codigo Proposta:"), 0, 0);
                    grid.add(cod, 1, 0);

                    dialog.getDialogPane().setContent(grid);

                    Optional resultEliminar = dialog.showAndWait();

                    resultEliminar.ifPresent(t -> {
                        if (t == okButtonType) {
                            if(!fsm.eliminaProposta(cod.getText()))
                                ToastMessage.show(getScene().getWindow(), "Erro ao Eliminar Auto proposta (Dados Invalidos)");
                            else ToastMessage.show(getScene().getWindow(), "Auto Proposta eliminada com sucesso");
                        } else ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    });
                }
            }
        });

        btnConsultar.setOnAction(ev -> {
            clicks++;
            if(clicks % 2 ==0){
                buttons.setVisible(true);
                buttons.setManaged(true);
                this.setTop(buttons);
                listView.setVisible(false);
                listaVbox.setVisible(false);
                listaVbox.setManaged(false);

            }else{
                buttons.setVisible(false);
                buttons.setManaged(false);
                listView.setVisible(true);
                listaVbox.setVisible(true);
                listaVbox.setManaged(true);
                this.setRight(listaVbox);
            }
        });

        btnRegressar.setOnAction(ev -> fsm.regressaFaseAnterior());

    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.GERE_PROPOSTAS) {
            this.setVisible(false);
            return;
        }
        listView.getItems().clear();
        listView.getItems().addAll(fsm.listaPropostas());
        this.setVisible(true);
    }
}


package pt.isec.pa.apoio_poe.ui.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class GestaoAlunosUI extends BorderPane {
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    GestaoEstagioManager fsm;
    static int clicks = 0;
    ToggleButton btnInserir,btnEditar,btnEliminar,btnConsultar,btnRegressar;
    VBox listaVbox;
    ListView <Aluno> listView ;
    public GestaoAlunosUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        this.setTop(new VBox(new AppMenu(fsm)));
// Set the icon (must be included in the project).
        listView = new ListView<>();
        btnInserir = new ToggleButton("Inserir");
        btnInserir.setPrefHeight(TOGGLE_HEIGHT);
        btnInserir.setPrefWidth(TOGGLE_WIDTH);
        btnEditar = new ToggleButton("Editar");
        btnEditar.setPrefHeight(TOGGLE_HEIGHT);
        btnEditar.setPrefWidth(TOGGLE_WIDTH);
        btnEliminar = new ToggleButton("Eliminar");
        btnEliminar.setPrefHeight(TOGGLE_HEIGHT);
        btnEliminar.setPrefWidth(TOGGLE_WIDTH);
        btnConsultar = new ToggleButton("Consultar");
        btnConsultar.setPrefHeight(TOGGLE_HEIGHT);
        btnConsultar.setPrefWidth(TOGGLE_WIDTH);
        btnRegressar = new ToggleButton("Regressar");
        btnRegressar.setPrefHeight(TOGGLE_HEIGHT);
        btnRegressar.setPrefWidth(TOGGLE_WIDTH);
        VBox btns = new VBox(btnInserir,btnEditar,btnEliminar,btnConsultar,btnRegressar);
        btns.setAlignment(Pos.CENTER);
        btns.setSpacing(10);
        this.setCenter(btns);
        listaVbox = new VBox(listView);
        listaVbox.setVisible(false);
        listaVbox.setPrefWidth(500);
        this.setRight(listaVbox);
        listaVbox.setManaged(false);
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());
        btnEliminar.setOnAction(actionEvent -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Gestão de Alunos");
            dialog.setHeaderText("Eliminar Aluno");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
            // Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField id = new TextField();
            id.setPromptText("2020111111");

            grid.add(new Label("Numero de Aluno:"), 0, 0);
            grid.add(id, 1, 0);

            dialog.getDialogPane().setContent(grid);

            Optional resultInserir = dialog.showAndWait();

            resultInserir.ifPresent(t -> {
                if (t == okButtonType) {
                    if(!fsm.eliminarAluno((Long.parseLong(id.getText()))))
                        ToastMessage.show(getScene().getWindow(), "Erro ao Eliminar Aluno (Dados Invalidos)");
                    else ToastMessage.show(getScene().getWindow(), "Aluno eliminado com sucesso");
                }
            });


        });

        btnInserir.setOnAction(ev -> {

            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Gestão de Alunos");
            dialog.setHeaderText("Inserir Aluno");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));


            Slider slider = new Slider(0, 20, 1);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(1);
            slider.setBlockIncrement(1);
            Label l = new Label(" ");

            // set the color of the text
            l.setTextFill(Color.BLACK);
            VBox classificacao = new VBox(slider, l); // agrupa valor e slider
            TextField id = new TextField();
            id.setPromptText("2019133920");
            TextField nome = new TextField();
            nome.setPromptText("Francisco José Duarte Simões");
            TextField email = new TextField();
            email.setPromptText("a2019133920@isec.pt");
            CheckBox lei = new CheckBox("LEI");
            CheckBox leipl = new CheckBox("LEI-PL");
            HBox curso = new HBox(lei, leipl);
            curso.setSpacing(10);
            CheckBox da = new CheckBox("DA");
            CheckBox si = new CheckBox("SI");
            CheckBox ras = new CheckBox("RAS");
            HBox ramos = new HBox(da, si, ras);
            ramos.setSpacing(10);
            CheckBox True = new CheckBox("Sim");
            CheckBox False = new CheckBox("Não");
            HBox podeEstagio = new HBox(True, False);
            podeEstagio.setSpacing(10);

            grid.add(new Label("Número de aluno:"), 0, 0);
            grid.add(id, 1, 0);
            grid.add(new Label("Nome Completo:"), 0, 1);
            grid.add(nome, 1, 1);
            grid.add(new Label("Email:"), 0, 2);
            grid.add(email, 1, 2);
            grid.add(new Label("Curso:"), 0, 3);
            grid.add(curso, 1, 3);
            grid.add(new Label("Ramo:"), 0, 4);
            grid.add(ramos, 1, 4);
            grid.add(new Label("Classificação:"), 0, 5);
            grid.add(classificacao, 1, 5);
            grid.add(new Label("Possibilidade de estágio:"), 0, 6);
            grid.add(podeEstagio, 1, 6);

            slider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> l.setText("value: " + newValue)
            );
            lei.selectedProperty().addListener((observableValue, aBoolean, t1) -> leipl.setDisable(!aBoolean));
            leipl.selectedProperty().addListener((observableValue, aBoolean, t1) -> lei.setDisable(!aBoolean));
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
            True.selectedProperty().addListener((observableValue, aBoolean, t1) -> False.setDisable(!aBoolean));
            False.selectedProperty().addListener((observableValue, aBoolean, t1) -> True.setDisable(!aBoolean));



            dialog.getDialogPane().setContent(grid);

            Optional resultInserir = dialog.showAndWait();

            resultInserir.ifPresent(t -> {
                boolean f;
                if (t == okButtonType) {
                    if (id.getText() != null && email.getText() != null && (lei.isSelected() || leipl.isSelected()) && (da.isSelected() || si.isSelected() || ras.isSelected()) && (True.isSelected() || False.isSelected()) && slider.getValue() > 0) {
                        BigDecimal bd = BigDecimal.valueOf(slider.getValue());
                        bd = bd.setScale(2, RoundingMode.HALF_UP);
                        f = true;

                        if (!lei.isDisable()) {
                            if (!da.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), lei.getText(), da.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                            if (!si.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), lei.getText(), si.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                            if (!ras.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), lei.getText(), ras.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                        } else {
                            if (!da.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), leipl.getText(), da.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                            if (!si.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), leipl.getText(), si.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                            if (!ras.isDisable()) {
                                if (!fsm.inserirAluno(Long.parseLong(id.getText()), nome.getText(), email.getText(), leipl.getText(), ras.getText(),
                                        bd.doubleValue(), True.getText())) f = false;
                            }
                        }
                        if (!f) ToastMessage.show(getScene().getWindow(), "Erro ao Inserir Aluno (Dados Invalidos)");
                        else ToastMessage.show(getScene().getWindow(), "Aluno Inserido!!!");
                    }
                }else
                    ToastMessage.show(getScene().getWindow(), "Sem efeito");
            });
        });

        btnConsultar.setOnAction(ev -> {
            clicks++;
            if (clicks % 2 == 0) {
                listView.setVisible(false);
                listaVbox.setVisible(false);
                listaVbox.setManaged(false);

            } else {
                listView.setVisible(true);
                listaVbox.setVisible(true);
                listaVbox.setManaged(true);
                this.setRight(listaVbox);
            }
        });

        btnEditar.setOnAction(actionEvent -> {
            Aluno aluno ;
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Editar");
            dialog1.setHeaderText("Editar aluno");
            dialog1.setContentText("Digite o número do aluno:");

// Traditional way to get the response value.
            Optional<String> result = dialog1.showAndWait();
            if(result.isPresent()){
                if(result.get().isEmpty() || !result.get().matches(".*[0-9].*")){
                    ToastMessage.show(getScene().getWindow(), "Numero de aluno Invalido");
                    fsm.regressaFaseAnterior();
                    return;
                }
                aluno = fsm.getAluno(result.get());
                if (aluno == null) {
                    ToastMessage.show(getScene().getWindow(), "Numero de aluno Invalido");
                    fsm.regressaFaseAnterior();
                    return;
                }
            }
            else{
                if(result.equals(Optional.empty())) {
                    ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    return;
                }
                return;
            }

            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edição"+aluno.getId());
            dialog.setHeaderText("Editar aluno");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            Slider slider = new Slider(0,20,aluno.getClassificacao()*100);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(1);
            slider.setBlockIncrement(1);
            Label l = new Label(" ");

            // set the color of the text
            l.setTextFill(Color.BLACK);
            l.setText("Value = "+aluno.getClassificacao());
            VBox classificacao = new VBox(slider, l); // agrupa valor e slider
            TextField id = new TextField();
            id.setText(String.valueOf(aluno.getId()));
            TextField nome = new TextField();
            nome.setText(aluno.getNomeAluno());
            TextField email = new TextField();
            email.setText(aluno.getEmailAluno());
            CheckBox lei = new CheckBox("LEI");
            CheckBox leipl = new CheckBox("LEI-PL");
            if(aluno.getSiglaCurso().equals("LEI")){
                lei.setSelected(true);
                leipl.setDisable(true);
            }
            if(aluno.getSiglaCurso().equals("LEI-PL")){
                leipl.setSelected(true);
                lei.setDisable(true);
            }
            HBox curso = new HBox(lei,leipl);
            curso.setSpacing(10);
            CheckBox da = new CheckBox("DA");
            CheckBox si = new CheckBox("SI");
            CheckBox ras = new CheckBox("RAS");
            if(aluno.getSiglaRamo().equals("DA"))
                da.setSelected(true);
            if(aluno.getSiglaRamo().equals("SI"))
                si.setSelected(true);
            if(aluno.getSiglaRamo().equals("RAS"))
                ras.setSelected(true);
            HBox ramos = new HBox(da,si,ras);
            ramos.setSpacing(10);

            CheckBox True = new CheckBox("Sim");
            CheckBox False = new CheckBox("Não");

            if(aluno.getAcesso()){
                True.setSelected(true);
                False.setDisable(true);
            }else{
                False.setSelected(true);
                True.setDisable(true);
            }
            HBox podeEstagio = new HBox(True,False);
            podeEstagio.setSpacing(10);

            grid.add(new Label("Número de aluno:"), 0, 0);
            grid.add(id, 1, 0);
            grid.add(new Label("Nome Completo:"), 0, 1);
            grid.add(nome, 1, 1);
            grid.add(new Label("Email:"), 0, 2);
            grid.add(email, 1, 2);
            grid.add(new Label("Curso:"), 0, 3);
            grid.add(curso, 1, 3);
            grid.add(new Label("Ramo:"), 0, 4);
            grid.add(ramos, 1, 4);
            grid.add(new Label("Classificação:"), 0, 5);
            grid.add(classificacao, 1, 5);
            grid.add(new Label("Possibilidade de estágio:"), 0, 6);
            grid.add(podeEstagio, 1, 6);

            slider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> l.setText("value: " + newValue)
            );

            lei.selectedProperty().addListener((observableValue, aBoolean, t1) -> leipl.setDisable(!aBoolean));
            leipl.selectedProperty().addListener((observableValue, aBoolean, t1) -> lei.setDisable(!aBoolean));

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

            True.selectedProperty().addListener((observableValue, aBoolean, t1) -> False.setDisable(!aBoolean));
            False.selectedProperty().addListener((observableValue, aBoolean, t1) -> True.setDisable(!aBoolean));

            dialog.getDialogPane().setContent(grid);

            BigDecimal bd = BigDecimal.valueOf(slider.getValue());
            bd = bd.setScale(2, RoundingMode.HALF_UP);

            Optional resultEditar = dialog.showAndWait();

            BigDecimal finalBd = bd;
            resultEditar.ifPresent(t -> {
                boolean f;
                if (t == okButtonType) {
                    f=true;
                    if (!aluno.getNomeAluno().equals(nome.getText())){
                        if(!fsm.editarNomeAluno(aluno.getId(), nome.getText()))f=false;}
                    if (!aluno.getEmailAluno().equals(email.getText())){
                        if(!fsm.editarEmailAluno(aluno.getId(), email.getText()))f=false;}
                    if (da.isSelected()) {
                        if (!da.getText().equals(aluno.getSiglaRamo())){
                            if(!fsm.editarRamoAluno(aluno.getId(), da.getText()))f=false;}
                    } else if (si.isSelected()) {
                        if (!si.getText().equals(aluno.getSiglaRamo())){
                            if(!fsm.editarRamoAluno(aluno.getId(), si.getText()))f=false;}
                    } else if (ras.isSelected()) {
                        if (!ras.getText().equals(aluno.getSiglaRamo())){
                            if(!fsm.editarRamoAluno(aluno.getId(), ras.getText()))f=false;}
                    }
                    if (lei.isSelected()) {
                        if (!lei.getText().equals(aluno.getSiglaCurso())){
                            if(!fsm.editarCursoAluno(aluno.getId(), lei.getText()))f=false;}
                    } else if (leipl.isSelected()) {
                        if (!leipl.getText().equals(aluno.getSiglaCurso())){
                            if(!fsm.editarCursoAluno(aluno.getId(), leipl.getText()))f=false;}
                    }
                    if (True.isSelected()) {
                        if (aluno.getAcesso()){
                            if(!fsm.editarAcessoAluno(aluno.getId(), True.getText()))f=false;}
                    } else if (False.isSelected()) {
                        if (!aluno.getAcesso()){
                            if(!fsm.editarAcessoAluno(aluno.getId(), False.getText()))f=false;}
                    }
                    if(finalBd.doubleValue() != aluno.getClassificacao()){
                        if(!fsm.editarClassificacaoAluno(aluno.getId(), slider.getValue()))f=false;}

                    if(f) ToastMessage.show(getScene().getWindow(), "Erro ao Editar Aluno (Nenhum valor atualizado ou valor atualizado é inválido)");
                    else ToastMessage.show(getScene().getWindow(), "Aluno editado com sucesso");
                }
            });
        });

        btnRegressar.setOnAction(ev -> fsm.regressaFaseAnterior());

    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.GERE_ALUNOS) {
            this.setVisible(false);
            return;
        }
        listView.getItems().clear();
        listView.getItems().addAll(fsm.listaAlunos());
        this.setVisible(true);
    }
}

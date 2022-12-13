package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;
import pt.isec.pa.apoio_poe.utils.ToastMessage;

import java.util.Optional;

public class GestaoDocentesUI extends BorderPane {
    GestaoEstagioManager fsm;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    static int clicks = 0;
    ToggleButton btnInserir,btnEditar,btnEliminar,btnConsultar,btnRegressar;
    VBox listaVbox;
    ListView <Docente>listView;

    public GestaoDocentesUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
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

        VBox box = new VBox(btnInserir,btnEditar,btnEliminar,btnConsultar,btnRegressar);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        this.setCenter(box);
        listView = new ListView<>();
        listaVbox = new VBox(listView);
        listaVbox.setVisible(false);
        listaVbox.setPrefWidth(500);
        this.setRight(listaVbox);
        listaVbox.setManaged(false);
    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());


        btnInserir.setOnAction(ev -> {
            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Gestão de Docentes");
            dialog.setHeaderText("Inserir Docente");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 20, 10, 10));

            TextField nome = new TextField();
            nome.setPromptText("Alvaro Nunes");
            TextField email = new TextField();
            email.setPromptText("ans@isec.pt");

            grid.add(new Label("Primeiro e Segundo nome:"), 0, 0);
            grid.add(nome, 1, 0);
            grid.add(new Label("Email:"), 0, 1);
            grid.add(email, 1, 1);

            dialog.getDialogPane().setContent(grid);

            Optional resultInserir = dialog.showAndWait();

            resultInserir.ifPresent(t -> {
                if (t == okButtonType) {
                    if(!fsm.inserirDocente(nome.getText(),email.getText()))
                        ToastMessage.show(getScene().getWindow(), "Erro ao Inserir Docente (Dados Invalidos)");
                    else ToastMessage.show(getScene().getWindow(), "Docente Inserido com sucesso");
                }else
                    ToastMessage.show(getScene().getWindow(), "Sem efeito");
            });

            //fsm.regressaFaseAnterior();
        });

        btnEditar.setOnAction(ev -> {
            Docente docente ;
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Editar");
            dialog1.setHeaderText("Editar Docente");
            dialog1.setContentText("Digite email do docente:");

// Traditional way to get the response value.
            Optional<String> result = dialog1.showAndWait();
            if(result.isPresent()){
                if(result.get().isEmpty()){
                    ToastMessage.show(getScene().getWindow(), "Email de docente Invalido");
                    fsm.regressaFaseAnterior();
                    return;
                }
                docente = fsm.getDocente(result.get());
                if (docente == null) {
                    ToastMessage.show(getScene().getWindow(), "Email de docente Invalido");
                    fsm.regressaFaseAnterior();
                    return;
                }
            }
            else{
                if(result.equals(Optional.empty())){
                    ToastMessage.show(getScene().getWindow(), "Sem efeito");
                    return;
                }
                return;
            }

            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edição"+docente.getEmailDocente());
            dialog.setHeaderText("Editar Docente");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
// Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nome = new TextField();
            nome.setText(docente.getNomeDocente());

            grid.add(new Label("Nome:"), 0, 0);
            grid.add(nome, 1, 0);

            dialog.getDialogPane().setContent(grid);

            Optional resultEditar = dialog.showAndWait();

            resultEditar.ifPresent(t -> {
                if (t == okButtonType) {
                    if (!docente.getNomeDocente().equals(nome.getText()))
                        if(!fsm.editarNomeDocente(docente.getEmailDocente(),nome.getText()))
                            ToastMessage.show(getScene().getWindow(), "Erro ao Editar Docente (Dados Invalidos)");
                        else ToastMessage.show(getScene().getWindow(), "Docente editado com sucesso");
                }
            });
        });

        btnEliminar.setOnAction(ev -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Gestão de Docentes");
            dialog.setHeaderText("Eliminar Docente");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
            // Create the username and password labels and fields.

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField email = new TextField();
            email.setPromptText("ans@isec.pt");

            grid.add(new Label("Email:"), 0, 0);
            grid.add(email, 1, 0);

            dialog.getDialogPane().setContent(grid);

            Optional resultInserir = dialog.showAndWait();

            resultInserir.ifPresent(t -> {
                if (t == okButtonType) {
                    if(!fsm.eliminarDocente(email.getText()))
                        ToastMessage.show(getScene().getWindow(), "Erro ao Eliminar Docente (Dados Invalidos)");
                    else
                        ToastMessage.show(getScene().getWindow(), "Docente eliminado com sucesso");
                }
            });
        });

        btnConsultar.setOnAction(ev -> {
            clicks++;
            if(clicks % 2 ==0){
                listView.setVisible(false);
                listaVbox.setVisible(false);
                listaVbox.setManaged(false);

            }else{
                listView.setVisible(true);
                listaVbox.setVisible(true);
                listaVbox.setManaged(true);
                this.setRight(listaVbox);
            }
        });

        btnRegressar.setOnAction(ev -> fsm.regressaFaseAnterior());
    }

    private void update() {
        if (fsm.getState() != GestaoEstagioState.GERE_DOCENTES) {
            this.setVisible(false);
            return;
        }

        listView.getItems().clear();
        listView.getItems().addAll(fsm.listaDocentes());
        this.setVisible(true);
    }

}





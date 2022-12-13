package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GereConsultaUI extends BorderPane {
    GestaoEstagioManager fsm;
    ToggleButton btnEstudanteAt, btnCandidatoSemAt,btnPropDisp,btnPropAtrib,btnNOrientacoes,barChart;
    VBox vBoxBtns,listaEstudanteAtVbox,listaCandidatoSemAtVbox,listaPropDispVbox,listaPropAtVbox,listaNOrVbox,barChartBox;
    ListView <Aluno> listaEstudanteAt,listaCandidatoSemAt;
    ListView<Propostas>listaPropAt,listaPropDisp;
    ListView listaNOr;
    CategoryAxis xAxis ;
    NumberAxis yAxis ;
    BarChart<String, Number> chart;
    XYChart.Series<String, Number> series1,series2,series3,series4;
    private static final int TOGGLE_HEIGHT = 40;
    private static final int TOGGLE_WIDTH = 150;
    private static int clicks =0 ;
    public GereConsultaUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new VBox(new AppMenu(fsm)));
        btnEstudanteAt = new ToggleButton("Alunos com Proposta Atribuída");
        btnEstudanteAt.setPrefHeight(TOGGLE_HEIGHT);
        btnEstudanteAt.setPrefWidth(TOGGLE_WIDTH);
        btnCandidatoSemAt = new ToggleButton("Alunos com Proposta Atribuída + Candidatura");
        btnCandidatoSemAt.setPrefHeight(TOGGLE_HEIGHT);
        btnCandidatoSemAt.setPrefWidth(TOGGLE_WIDTH);
        btnPropDisp = new ToggleButton("Propostas Disponíveis");
        btnPropDisp.setPrefHeight(TOGGLE_HEIGHT);
        btnPropDisp.setPrefWidth(TOGGLE_WIDTH);
        btnPropAtrib = new ToggleButton("Propostas Atribuídas");
        btnPropAtrib.setPrefHeight(TOGGLE_HEIGHT);
        btnPropAtrib.setPrefWidth(TOGGLE_WIDTH);
        btnNOrientacoes = new ToggleButton("Número de orientações");
        btnNOrientacoes.setPrefHeight(TOGGLE_HEIGHT);
        btnNOrientacoes.setPrefWidth(TOGGLE_WIDTH);
        barChart = new ToggleButton("Gráfico Orientações");
        barChart.setPrefHeight(TOGGLE_HEIGHT);
        barChart.setPrefWidth(TOGGLE_WIDTH);
        listaEstudanteAt = new ListView<>();
        listaEstudanteAtVbox = new VBox(listaEstudanteAt);
        listaEstudanteAtVbox.setVisible(false);
        listaEstudanteAtVbox.setPrefWidth(500);
        this.setRight(listaEstudanteAtVbox);
        listaEstudanteAtVbox.setManaged(false);
        listaCandidatoSemAt = new ListView<>();
        listaCandidatoSemAtVbox = new VBox(listaCandidatoSemAt);
        listaCandidatoSemAtVbox.setVisible(false);
        listaCandidatoSemAtVbox.setPrefWidth(500);
        this.setRight(listaCandidatoSemAtVbox);
        listaCandidatoSemAtVbox.setManaged(false);
        listaPropDisp = new ListView<>();
        listaPropDispVbox = new VBox(listaPropDisp);
        listaPropDispVbox.setVisible(false);
        listaPropDispVbox.setPrefWidth(500);
        this.setRight(listaPropDispVbox);
        listaPropDispVbox.setManaged(false);
        listaPropAt = new ListView<>();
        listaPropAtVbox = new VBox(listaPropAt);
        listaPropAtVbox.setVisible(false);
        listaPropAtVbox.setPrefWidth(500);
        this.setRight(listaPropAtVbox);
        listaPropAtVbox.setManaged(false);
        
        listaNOr = new ListView<>();
        listaNOrVbox = new VBox(listaNOr);
        listaNOrVbox.setVisible(false);
        listaNOrVbox.setPrefWidth(500);
        this.setRight(listaNOrVbox);
        listaNOrVbox.setManaged(false);


        xAxis = new CategoryAxis();
        xAxis.setLabel("Categorias");
        yAxis = new NumberAxis();
        yAxis.setLabel("Numero de Orientações");

        series1 = new XYChart.Series<>(); series2 = new XYChart.Series<>(); series3 = new XYChart.Series<>(); series4 = new XYChart.Series<>();
        series1.setName("Média");series2.setName("Minimo");series3.setName("Máximo");series4.setName("Orientação");
        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Comparação Docente e Numero de Orientações");
        barChartBox = new VBox(chart);
        barChartBox.setVisible(false);
        barChartBox.setManaged(false);
        this.setRight(barChartBox);

        vBoxBtns = new VBox(btnEstudanteAt, btnCandidatoSemAt,btnPropDisp,btnPropAtrib,btnNOrientacoes,barChart);
        vBoxBtns.setAlignment(Pos.CENTER);
        vBoxBtns.setSpacing(10);
        this.setCenter(vBoxBtns);


    }

    private void registerHandlers() {
        fsm.addPropertyChangeListener(evt -> update());
        btnEstudanteAt.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaEstudanteAtVbox.setVisible(false);
                listaEstudanteAtVbox.setManaged(false);
                listaEstudanteAt.setVisible(false);
            } else {
                listaEstudanteAt.setVisible(true);
                listaEstudanteAtVbox.setVisible(true);
                listaEstudanteAtVbox.setManaged(true);
                this.setRight(listaEstudanteAtVbox);
            }
        });
        btnCandidatoSemAt.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaCandidatoSemAtVbox.setVisible(false);
                listaCandidatoSemAtVbox.setManaged(false);
                listaCandidatoSemAt.setVisible(false);
            } else {
                listaCandidatoSemAt.setVisible(true);
                listaCandidatoSemAtVbox.setVisible(true);
                listaCandidatoSemAtVbox.setManaged(true);
                this.setRight(listaCandidatoSemAtVbox);
            }
        });
        btnPropDisp.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaPropDispVbox.setVisible(false);
                listaPropDispVbox.setManaged(false);
                listaPropDisp.setVisible(false);
            } else {
                listaPropDisp.setVisible(true);
                listaPropDispVbox.setVisible(true);
                listaPropDispVbox.setManaged(true);
                this.setRight(listaPropDispVbox);
            }
        });

        btnPropAtrib.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaPropAtVbox.setVisible(false);
                listaPropAtVbox.setManaged(false);
                listaPropAt.setVisible(false);
            } else {
                listaPropAt.setVisible(true);
                listaPropAtVbox.setVisible(true);
                listaPropAtVbox.setManaged(true);
                this.setRight(listaPropAtVbox);
            }
        });


        btnNOrientacoes.setOnAction(actionEvent -> {
            clicks++;
            if (clicks % 2 == 0) {
                listaNOrVbox.setVisible(false);
                listaNOrVbox.setManaged(false);
                listaNOr.setVisible(false);
            } else {
                listaNOr.setVisible(true);
                listaNOrVbox.setVisible(true);
                listaNOrVbox.setManaged(true);
                this.setRight(listaNOrVbox);
            }
        });

        barChart.setOnAction(ev -> {
            clicks++;
            if (clicks % 2 == 0) {
                barChartBox.setVisible(false);
                barChartBox.setManaged(false);
                chart.setVisible(false);
            }else {


                chart.setVisible(true);
                barChartBox.setVisible(true);
                barChartBox.setManaged(true);
                this.setRight(barChartBox);
            }

        });


    }


    private void update() {
        if (fsm.getState() != GestaoEstagioState.CONSULTA) {
            this.setVisible(false);
            return;
        }



        //Setting the data to bar chart
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(fsm.getOrientadores()));

        for (String d: fsm.getOrientadores()) {
            series1.getData().add(new XYChart.Data<>(d, fsm.getMediaOrientacao()));
            series2.getData().add(new XYChart.Data<>(d, fsm.getMinimoOrientacao()));
            series3.getData().add(new XYChart.Data<>(d, fsm.getMaximaOrientacao()));
            series4.getData().add(new XYChart.Data<>(d, fsm.getOrientacaoDocente(d)));
        }
        chart.getData().addAll(series1, series2, series3,series4);
        listaEstudanteAt.getItems().addAll(fsm.listaAlunosComProposta());
        listaCandidatoSemAt.getItems().addAll(fsm.listaAlunosComCandSemProp());
        listaPropDisp.getItems().addAll(fsm.propostasDisponiveis());
        listaPropAt.getItems().addAll(fsm.propostasAtribuidas());
        listaNOr.getItems().addAll(fsm.orientacoesDocente());

        this.setVisible(true);
    }
}
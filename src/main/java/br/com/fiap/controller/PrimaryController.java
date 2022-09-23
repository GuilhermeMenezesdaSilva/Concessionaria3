package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.VeiculoDao;
import br.com.fiap.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;
    @FXML private TextField textFieldPreco;
    @FXML private TextField textFieldAno;

    @FXML private ListView<Veiculo> listView;

    public void salvar(){
        var veiculo = carregarVeiculoDoFormulario();
        try {
            VeiculoDao dao = new VeiculoDao();
            dao.inserir(veiculo);
            mostrarAlerta("Veículo cadatrado com sucesso");
            limparFormulario();
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        }
    }

    private void atualizarListView(List<Veiculo> lista) {
        listView.getItems().clear();
        listView.getItems().addAll(lista);
    }

    private void limparFormulario() {
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldPlaca.setText("");
        textFieldPreco.setText("");
        textFieldAno.setText("");
    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    private Veiculo carregarVeiculoDoFormulario() {
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String placa = textFieldPlaca.getText();
        double preco = Double.valueOf( textFieldPreco.getText() );
        int ano = Integer.valueOf( textFieldAno.getText() );
        
        return new Veiculo(marca, modelo, placa, preco, ano);
    }

    public void ordenarPorPreco(){
        // lista.sort((o1, o2) -> Double.compare(o1.getPreco(), o2.getPreco()));
    }

    public void ordenarPorAno(){
        // lista.sort((o1, o2) -> Integer.compare(o1.getAno(), o2.getAno()));
    }

    public void carregarDados(){
        try {
            atualizarListView( new VeiculoDao().buscarTodos());
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        }
    }
    
}

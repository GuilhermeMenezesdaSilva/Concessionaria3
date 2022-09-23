package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Veiculo;

public class VeiculoDao {

    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private String user = "RM89384";
    private String password = "310302";

    public void inserir(Veiculo veiculo) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO T_DDDC_C_VEICULO (id, marca, modelo, placa, preco, ano) " +
        "VALUES (SEQ_VEICULO.nextVal, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, veiculo.getMarca());
        stmt.setString(2, veiculo.getModelo());
        stmt.setString(3, veiculo.getPlaca());
        stmt.setDouble(4, veiculo.getPreco());
        stmt.setInt(5, veiculo.getAno());
        stmt.execute();
        con.close();
    }

    public List<Veiculo> buscarTodos() throws SQLException {
        List<Veiculo> lista = new ArrayList<>();

        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM T_DDDC_C_VEICULO";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            String placa = rs.getString("placa");
            double preco = rs.getDouble("preco");
            int ano = rs.getInt("ano");
            Veiculo veiculo = new Veiculo(marca, modelo, placa, preco, ano);
            lista.add(veiculo);
        }

        con.close();
        return lista;
    }
}
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Produto;

public class ProdutoDAO {
    public void inserir(Produto produto) {
        ConectaBD con = new ConectaBD();
        String sql = "INSERT INTO produto (numeroChassi, placa, modelo, nome, valor) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, produto.getNumeroChassi());
            pst.setString(2, produto.getPlaca());
            pst.setString(3, produto.getModelo());
            pst.setString(4, produto.getNome());
            pst.setDouble(5, produto.getValor());
            pst.execute();
            System.out.println("Produto " + produto.getNome() + " inserido");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Produto> consultarTodos() {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM produto";
        List<Produto> lista = new LinkedList<>();
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int numeroChassi = rs.getInt("numeroChassi");
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");

                Produto produto = new Produto(numeroChassi, placa, modelo, nome, valor);
                lista.add(produto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public Produto consultar(int numeroChassi) {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM produto WHERE numeroChassi = ?";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, numeroChassi);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");

                Produto produto = new Produto(numeroChassi, placa, modelo, nome, valor);
                return produto;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean excluir(int numeroChassi) {
        ConectaBD con = new ConectaBD();
        String sql = "DELETE FROM produto WHERE numeroChassi = ?";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, numeroChassi);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void atualizar(Produto produto) {
        ConectaBD con = new ConectaBD();
        String sql = "UPDATE produto SET placa = ?, modelo = ?, nome = ?, valor = ? WHERE numeroChassi = ?";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getPlaca());
            pst.setString(2, produto.getModelo());
            pst.setString(3, produto.getNome());
            pst.setDouble(4, produto.getValor());
            pst.setInt(5, produto.getNumeroChassi());
            pst.executeUpdate();
            System.out.println("Produto " + produto.getNome() + " atualizado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
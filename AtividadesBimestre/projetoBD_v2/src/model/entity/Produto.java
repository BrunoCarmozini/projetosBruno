package model.entity;

import model.dao.ProdutoDAO;

public class Produto {
    private int id;
    private int numeroChassi;
    private String placa;
    private String modelo;
    private String nome;
    private double valor;

    public Produto() {
    }

    public Produto(int numeroChassi, String placa, String modelo, String nome, double valor) {
        this.numeroChassi = numeroChassi;
        this.placa = placa;
        this.modelo = modelo;
        this.nome = nome;
        this.valor = valor;
    }

    

    public int getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(int numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void inserir() {
        System.out.println("Inserindo produto: " + nome);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.inserir(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
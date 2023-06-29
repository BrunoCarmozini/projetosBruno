import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.dao.PessoaDAO;
import model.dao.ProdutoDAO;
import model.entity.Pessoa;
import model.entity.Produto;

public class App {
    public static String leString(String msg) {
        String valor = JOptionPane.showInputDialog(null, msg);
        return valor;
    }

    public static int menu() {
        Scanner teclado = new Scanner(System.in);        
        System.out.println("MENU");
        System.out.println("1- Inserir Pessoa");
        System.out.println("2- Listar todas as Pessoas");
        System.out.println("3- Listar Pessoa por ID");
        System.out.println("4- Excluir Pessoa por ID");
        System.out.println("5- Atualizar Pessoa");
        System.out.println("6- Inserir Produto");
        System.out.println("7- Listar todos os Produtos");
        System.out.println("8- Listar Produto por número de chassi");
        System.out.println("9- Excluir Produto por número de chassi");
        System.out.println("10- Atualizar Produto");
        System.out.println("11- Sair");
        System.out.print("Digite: ");
        return teclado.nextInt();
    }
    
    public static void metodoInserirPessoa() {
        String nome = leString("Digite o nome da Pessoa");
        String email = leString("Digite o e-mail da Pessoa");
        Pessoa pessoa = new Pessoa(nome, email);
        pessoa.inserir();       
    }

    public static void metodoConsultarTodosPessoas() {
        List<Pessoa> registros = new PessoaDAO().consultarTodos();
        if (!registros.isEmpty()){
            String saida = "id\tNome\tEmail\n";
            for (int i = 0; i < registros.size(); i++) {
                Pessoa p = registros.get(i);
                saida += p.getId() + "\t";
                saida += p.getNome() + "\t";
                saida += p.getEmail() + "\n";                
            }
            JOptionPane.showMessageDialog(null, new JTextArea(saida));
        } else {
            System.out.println("Não há registros de Pessoas");
        }
    }
    
    public static void metodoExcluirPessoa() {
        String tmp = leString("Digite o ID da Pessoa para excluir");
        int id = Integer.parseInt(tmp);
        PessoaDAO dao = new PessoaDAO();
        if (dao.excluir(id)) {
            JOptionPane.showMessageDialog(null, "Registro " + id + " excluído");
        } else {
            JOptionPane.showMessageDialog(null, "Registro " + id + " não existe");
        }
    }

    public static void metodoAtualizarPessoa(Pessoa p) {
    String nomeAntigo = p.getNome();
    String emailAntigo = p.getEmail();
    String novoNome = leString("Alterar nome de " + nomeAntigo);
    String novoEmail = leString("Alterar email de " + emailAntigo);
    p.setNome(novoNome);
    p.setEmail(novoEmail);
    PessoaDAO pessoaDAO = new PessoaDAO();
    pessoaDAO.atualizar(p);
}
    
    public static void metodoInserirProduto() {
        int numeroChassi = Integer.parseInt(leString("Digite o número de chassi do Produto"));
        String placa = leString("Digite a placa do Produto");
        String modelo = leString("Digite o modelo do Produto");
        String nome = leString("Digite o nome do Produto");
        double valor = Double.parseDouble(leString("Digite o valor do Produto"));
        Produto produto = new Produto(numeroChassi, placa, modelo, nome, valor);
        produto.inserir();
    }

    public static void metodoConsultarTodosProdutos() {
        List<Produto> registros = new ProdutoDAO().consultarTodos();
        if (!registros.isEmpty()){
            String saida = "Número Chassi\tPlaca\tModelo\tNome\tValor\n";
            for (int i = 0; i < registros.size(); i++) {
                Produto p = registros.get(i);
                saida += p.getNumeroChassi() + "\t";
                saida += p.getPlaca() + "\t";
                saida += p.getModelo() + "\t";
                saida += p.getNome() + "\t";
                saida += p.getValor() + "\n";
            }
            JOptionPane.showMessageDialog(null, new JTextArea(saida));
        } else {
            System.out.println("Não há registros de Produtos");
        }
    }
    
    public static void metodoExcluirProduto() {
        int numeroChassi = Integer.parseInt(leString("Digite o número de chassi do Produto para excluir"));
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.excluir(numeroChassi)) {
            JOptionPane.showMessageDialog(null, "Registro " + numeroChassi + " excluído");
        } else {
            JOptionPane.showMessageDialog(null, "Registro " + numeroChassi + " não existe");
        }
    }
    
    public static void metodoAtualizarProduto(Produto p) {
    int numeroChassiAntigo = p.getNumeroChassi();
    String placaAntiga = p.getPlaca();
    String modeloAntigo = p.getModelo();
    String nomeAntigo = p.getNome();
    double valorAntigo = p.getValor();

    int novoNumeroChassi = Integer.parseInt(leString("Alterar número de chassi de " + numeroChassiAntigo));
    String novaPlaca = leString("Alterar placa de " + placaAntiga);
    String novoModelo = leString("Alterar modelo de " + modeloAntigo);
    String novoNome = leString("Alterar nome de " + nomeAntigo);
    double novoValor = Double.parseDouble(leString("Alterar valor de " + valorAntigo));

    p.setNumeroChassi(novoNumeroChassi);
    p.setPlaca(novaPlaca);
    p.setModelo(novoModelo);
    p.setNome(novoNome);
    p.setValor(novoValor);

    ProdutoDAO produtoDAO = new ProdutoDAO();
    produtoDAO.atualizar(p);
}

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    metodoInserirPessoa();
                    break;
                case 2:
                    metodoConsultarTodosPessoas();
                    break;
                case 3:
                    int id = Integer.parseInt(leString("Digite o ID da Pessoa para consultar"));
                    Pessoa pessoa = new PessoaDAO().consultar(id);
                    if (pessoa != null) {
                        JOptionPane.showMessageDialog(null, "ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nEmail: " + pessoa.getEmail());
                    } else {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrada");
                    }
                    break;
                case 4:
                    metodoExcluirPessoa();
                    break;
                case 5:
                    int idAtualizar = Integer.parseInt(leString("Digite o ID da Pessoa para atualizar"));
                    Pessoa pessoaAtualizar = new PessoaDAO().consultar(idAtualizar);
                    if (pessoaAtualizar != null) {
                        metodoAtualizarPessoa(pessoaAtualizar);
                    } else {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrada");
                    }
                    break;
                case 6:
                    metodoInserirProduto();
                    break;
                case 7:
                    metodoConsultarTodosProdutos();
                    break;
                case 8:
                    int numeroChassi = Integer.parseInt(leString("Digite o número de chassi do Produto para consultar"));
                    Produto produto = new ProdutoDAO().consultar(numeroChassi);
                    if (produto != null) {
                        JOptionPane.showMessageDialog(null, "Número Chassi: " + produto.getNumeroChassi() + "\nPlaca: " + produto.getPlaca() + "\nModelo: " + produto.getModelo() + "\nNome: " + produto.getNome() + "\nValor: " + produto.getValor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado");
                    }
                    break;
                case 9:
                    metodoExcluirProduto();
                    break;
                case 10:
                    int numeroChassiAtualizar = Integer.parseInt(leString("Digite o número de chassi do Produto para atualizar"));
                    Produto produtoAtualizar = new ProdutoDAO().consultar(numeroChassiAtualizar);
                    if (produtoAtualizar != null) {
                        metodoAtualizarProduto(produtoAtualizar);
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado");
                    }
                    break;
                case 11:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 11);
    }
}
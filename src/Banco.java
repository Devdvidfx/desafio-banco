import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarContaInterativamente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adicionar Nova Conta:");

        // Solicitar o nome do cliente
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = new Cliente(nomeCliente);

        // Escolher o tipo de conta
        System.out.print("Escolha o tipo de conta (C - Conta Corrente, P - Conta Poupança): ");
        String tipoConta = scanner.nextLine();

        // Criar a conta com base no tipo escolhido
        Conta conta;
        if (tipoConta.equalsIgnoreCase("C")) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta.equalsIgnoreCase("P")) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido. Conta Corrente será criada por padrão.");
            conta = new ContaCorrente(cliente);
        }

        // Adicionar a conta ao banco
        adicionarConta(conta);

        System.out.println("Conta adicionada com sucesso!");

        // Fechar o scanner
        scanner.close();
    }

    public Conta adicionarConta(Conta conta) {
        contas.add(conta);
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = new ArrayList<>(contas);
    }
}
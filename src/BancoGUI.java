import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoGUI {

    private static Banco banco;

    public static void main(String[] args) {
        banco = new Banco("Banco da Prosperidade");

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Banco");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addButton = new JButton("Adicionar Nova Conta");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContaInterativamente();
            }
        });

        frame.getContentPane().add(addButton);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void adicionarContaInterativamente() {
        JFrame inputFrame = new JFrame("Adicionar Nova Conta");
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField nomeField = new JTextField(20);
        JTextField tipoContaField = new JTextField(1);

        JButton addButton = new JButton("Adicionar Conta");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarConta(nomeField.getText(), tipoContaField.getText());
                inputFrame.dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome do Cliente: "));
        panel.add(nomeField);
        panel.add(new JLabel("Tipo de Conta (C - Conta Corrente, P - Conta Poupança): "));
        panel.add(tipoContaField);
        panel.add(addButton);

        inputFrame.getContentPane().add(panel);
        inputFrame.setSize(400, 150);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setVisible(true);
    }

    private static void adicionarConta(String nomeCliente, String tipoConta) {
        try {
            validarEntrada(nomeCliente, tipoConta);

            Cliente cliente = new Cliente(nomeCliente);

            Conta conta;
            if (tipoConta.equalsIgnoreCase("C")) {
                conta = new ContaCorrente(cliente);
            } else if (tipoConta.equalsIgnoreCase("P")) {
                conta = new ContaPoupanca(cliente);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de conta inválido. Conta Corrente será criada por padrão.");
                conta = new ContaCorrente(cliente);
            }

            banco.adicionarConta(conta);
            JOptionPane.showMessageDialog(null, "Conta adicionada com sucesso!");

            // Exibe na janela o extrato
            JOptionPane.showMessageDialog(null, conta.getExtratoString(), "Extrato da Conta", JOptionPane.INFORMATION_MESSAGE);

            // Imprime o extrato da conta
            System.out.println("Informações da conta adicionada:");
            conta.imprimirExtrato();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void validarEntrada(String nomeCliente, String tipoConta) throws Exception {
        if (nomeCliente.isEmpty() || tipoConta.isEmpty()) {
            throw new Exception("Nome do cliente e tipo de conta são obrigatórios.");
        }

        if (!tipoConta.equalsIgnoreCase("C") && !tipoConta.equalsIgnoreCase("P")) {
            throw new Exception("Tipo de conta inválido. Use C para Conta Corrente ou P para Conta Poupança.");
        }
    }
}

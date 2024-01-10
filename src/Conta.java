import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Conta implements IConta {
    protected static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private LocalDateTime dataHoraUltimaTransacao;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.dataHoraUltimaTransacao = LocalDateTime.now();
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
        setDataHoraUltimaTransacao(LocalDateTime.now());
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        setDataHoraUltimaTransacao(LocalDateTime.now());
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
        setDataHoraUltimaTransacao(LocalDateTime.now());
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Nome do Cliente: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));

        if (getDataHoraUltimaTransacao() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = getDataHoraUltimaTransacao().format(formatter);
            System.out.println(String.format("Data e Hora da última transação: %s", dataHoraFormatada));
        } else {
            System.out.println("Data e Hora da última transação:");
        }
    }
    public String getExtratoString() {
        StringBuilder extrato = new StringBuilder();
        extrato.append("*** Extrato da conta ***\n");
        imprimirInfosComuns();
        return extrato.toString();
    }

    public LocalDateTime getDataHoraUltimaTransacao() {
        return dataHoraUltimaTransacao;
    }

    public void setDataHoraUltimaTransacao(LocalDateTime dataHoraUltimaTransacao) {
        this.dataHoraUltimaTransacao = dataHoraUltimaTransacao;
    }
}

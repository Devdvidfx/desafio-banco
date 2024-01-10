import java.time.LocalDateTime;

public interface IConta {
    LocalDateTime DataHoraUltimaTransacao = null;
    
    String getExtratoString();

    void sacar(double valor);

    void depositar(double valor);

    void transferir(double valor, Conta contaDestino);

    void imprimirExtrato();
}

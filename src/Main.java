public class Main {
    public static void main(String[] args) {

        Cliente cliente = null;
		IConta cc = new ContaCorrente(cliente);
        cc.depositar(0);

        IConta poupanca = new ContaPoupanca(cliente);
        poupanca.depositar(0);

        cc.transferir(100, (Conta) poupanca);

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}

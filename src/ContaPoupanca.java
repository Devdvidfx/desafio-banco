public class ContaPoupanca extends Conta {
	
	public ContaPoupanca (Cliente cliente) {
		super(cliente);
	}
	@Override
	public String getExtratoString() {
        StringBuilder extrato = new StringBuilder("*** Extrato da Conta Poupança ***\n");
        extrato.append(super.getExtratoString());
        return extrato.toString();
    }
	@Override
	public void imprimirExtrato() {
		System.out.println("*** Imprimir Extrato da conta Poupança ***");
		super.imprimirInfosComuns();
	}
}

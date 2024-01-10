public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}
	@Override
    public String getExtratoString() {
        StringBuilder extrato = new StringBuilder("*** Extrato da Conta Corrente ***\n");
        extrato.append(super.getExtratoString());
        return extrato.toString();
    }
	@Override
	public void imprimirExtrato() {
		System.out.println("*** Imprimir Extrato da conta Corrente ***");
		super.imprimirInfosComuns();
	}
	
}

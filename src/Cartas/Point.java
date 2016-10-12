package pr1;

public enum Valor {
	A('A');
	
	private char valor;
	private String desc;
	
	public String toString(){
		return this.desc;
	}
	
	Valor(char valor){
		this.valor = valor;
	}
}

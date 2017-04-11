package pt.foundthat.model;


public class Instituicao implements Comparable <Instituicao> {	
	private int codigo;
	private String nome;

	public Instituicao(int codigo, String nome) {
		super();		
		this.codigo = codigo;		
		this.nome = nome;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Instituicao other) {
		return getNome().compareTo(other.getNome()) ;
	}

}

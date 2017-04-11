package pt.foundthat.model;

public class Sala implements Comparable<Sala> {


	private String nome;

	public Sala(String nome) {
		super();
		this.nome = nome;
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
	public int compareTo(Sala other) {		

		return getNome().compareTo(other.getNome()) ;
	}

}

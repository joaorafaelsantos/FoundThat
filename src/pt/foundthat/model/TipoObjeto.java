package pt.foundthat.model;


public class TipoObjeto implements Comparable <TipoObjeto> {

	private static int cod;
	private int codigo;
	private String nome;	
	private Instituicao codigoIS;

	public TipoObjeto(int codigo, String nome, Instituicao codigoIS) {
		super();
		cod++;
		this.nome = nome;
		this.codigo = cod;
		this.codigoIS = codigoIS;
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

	public Instituicao getCodigoIS() {
		return codigoIS;
	}

	public void setCodigoIS(Instituicao codigoIS) {
		this.codigoIS = codigoIS;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(TipoObjeto other) {
		return getNome().compareTo(other.getNome()) ;
	}



}

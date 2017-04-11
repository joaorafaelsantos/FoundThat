package pt.foundthat.controller;

import pt.foundthat.model.Instituicao;
import pt.foundthat.model.TipoObjeto;

public class ManagerTipoObjeto {

	public static boolean isTipoObjeto(String nome) {
		boolean res = false;
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			if (to.getNome().equals(nome)) {
				res = true;
			}

		}

		return res;	
	}

	public static boolean adicionarTipoObjeto(String nome, String nomeIS) {
		boolean res = false;
		if (!isTipoObjeto(nome)) {
			for (Instituicao is : FoundThat.instituicoes) {
				if (is.getNome().equals(nomeIS)) {
					TipoObjeto to = new TipoObjeto(getLastCode(), nome, is);
					FoundThat.tipoObjetos.add(to);
					res = true;
				} 	
			}
		}
		return res;	
	}

	public static boolean removerTipoObjeto(String nome) {
		boolean res = false;
		if (isTipoObjeto(nome) == true) {
			for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
				TipoObjeto to = FoundThat.tipoObjetos.get(i);
				if (to.getNome().equals(nome)) {
					FoundThat.tipoObjetos.remove(i);	
				}	
			}
			res = false;	
		}

		return res;
	}

	public static boolean alterarTipoObjeto(String novoObjeto, String objetoAntigo, String novaIS, String isAntiga) {
		boolean res = false;
		if (!isTipoObjeto(novoObjeto)) {
			for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
				TipoObjeto to = FoundThat.tipoObjetos.get(i);
				if (to.getNome().equals(objetoAntigo)) {
					for (Instituicao is : FoundThat.instituicoes) {
						if (is.getNome().equals(novaIS)) {
							to.setNome(novoObjeto);
							to.setCodigoIS(is);
						}
					}

				}			
			}
			res = true;
		} 
		else {
			for (Instituicao is : FoundThat.instituicoes) {
				if (novaIS.equals(isAntiga)) {
					res = false;
				}
				else {
					for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
						TipoObjeto to = FoundThat.tipoObjetos.get(i);
						if (to.getNome().equals(objetoAntigo)) {
							if (is.getNome().equals(novaIS)) {
								to.setNome(novoObjeto);
								to.setCodigoIS(is);
							}


						}			
					}
					res = true;
				}
			}

		}
		return res;	
	}

	public static int getLastCode() {
		return FoundThat.tipoObjetos.get(FoundThat.tipoObjetos.size() - 1).getCodigo()+1;
	}

}

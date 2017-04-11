package pt.foundthat.controller;

import pt.foundthat.model.Instituicao;
import pt.foundthat.model.TipoObjeto;

public class ManagerIS {

	public static boolean isIS(String nome) {
		boolean res = false;
		for (Instituicao is : FoundThat.instituicoes) {
			if (is.getNome().equals(nome)) {
				res = true;
			}

		}

		return res;	
	}

	public static boolean isAtribuida(String nome) {
		boolean res = false;
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			if (to.getCodigoIS().getNome().equals(nome)) {
				res = true;
			}

		}

		return res;	
	}

	public static boolean adicionarIS(String nome) {
		boolean res = false;
		if (!isIS(nome)) {
			Instituicao is = new Instituicao(getLastCode(), nome);
			FoundThat.instituicoes.add(is);
			res = true;
		} 
		return res;		
	}

	public static boolean removerIS(String nome) {
		boolean res = false;
		if (isIS(nome) == true) {
			for (int i = 0; i < FoundThat.instituicoes.size(); i++) {
				Instituicao is = FoundThat.instituicoes.get(i);
				if (is.getNome().equals(nome)) {
					FoundThat.instituicoes.remove(i);	
				}	
			}
			res = false;	
		}

		return res;
	}

	public static boolean alterarIS(String novaIS, String isAntiga) {
		boolean res = false;
		if (!isIS(novaIS)) {
			for (int i = 0; i < FoundThat.instituicoes.size(); i++) {
				Instituicao is = FoundThat.instituicoes.get(i);
				if (is.getNome().equals(isAntiga)) {
					is.setNome(novaIS);
				}

			}
			res = true;
		} 
		return res;	
	}












	public static int getLastCode() {
		return FoundThat.instituicoes.get(FoundThat.instituicoes.size() - 1).getCodigo()+1;
	}









}

package pt.foundthat.controller;

import pt.foundthat.model.TipoUser;

public class ManagerTipoUser {

	public static boolean isPerfilUser(String nome) {
		boolean res = false;
		for (TipoUser tu : FoundThat.tipoUsers) {
			if (tu.getNome().equals(nome)) {
				res = true;
			}			
		}	
		return res;
	}

	public static boolean isPerfil(String nome, Boolean registo, Boolean reclamacao, Boolean importacao, Boolean listagens, Boolean doacoes, Boolean configuracoes) {

		boolean res = false;
		for (TipoUser tu : FoundThat.tipoUsers) {
			if (tu.getNome().equals(nome)) {
				if (tu.isRegisto() == registo &&  tu.isReclamacao() == reclamacao && tu.isImportacao() == importacao && tu.isListagens() == listagens && tu.isDoacoes() ==  doacoes && tu.isConfiguracoes() == configuracoes) {
					res = true;
				}
			}
		}

		return res;
	}

	public static boolean adicionarPerfil(String nome, Boolean registo, Boolean reclamacao, Boolean importacao, Boolean listagens, Boolean doacoes, Boolean configuracoes) {
		boolean res = false;
		if (!isPerfilUser(nome)) {
			TipoUser tu = new TipoUser(getLastCode(), nome, registo, reclamacao, importacao, listagens, doacoes, configuracoes);
			FoundThat.tipoUsers.add(tu);
			res = true;
		} 
		return res;	
	}

	public static boolean removerPerfil(String nome) {
		boolean res = false;
		if (isPerfilUser(nome) == true) {
			for (int i = 0; i < FoundThat.tipoUsers.size(); i++) {
				TipoUser us = FoundThat.tipoUsers.get(i);
				if (us.getNome().equals(nome)) {
					FoundThat.tipoUsers.remove(i);	
				}	
			}
			res = false;	
		}

		return res;
	}

	public static boolean alterarPerfil(String nome, String nomeAntigo, Boolean registo, Boolean reclamacao, Boolean importacao, Boolean listagens, Boolean doacoes, Boolean configuracoes) {
		boolean res = false;
		if (!isPerfilUser(nome) || (isPerfilUser(nome) == true && isPerfil(nomeAntigo, registo, reclamacao, importacao, listagens, doacoes, configuracoes) == false)) {
			for (int i = 0; i < FoundThat.tipoUsers.size(); i++) {
				TipoUser tu = FoundThat.tipoUsers.get(i);
				if (tu.getNome().equals(nomeAntigo)) {
					tu.setNome(nome);
					tu.setRegisto(registo);
					tu.setReclamacao(reclamacao);
					tu.setImportacao(importacao);
					tu.setListagens(listagens);
					tu.setDoacoes(doacoes);
					tu.setConfiguracoes(configuracoes);
				}			
			}
			res = true;
		} 

		return res;	
	}

	public static int getLastCode() {
		if (FoundThat.tipoUsers.size() == 0) {
			return 1;
		}
		else {
			return FoundThat.tipoUsers.get(FoundThat.tipoUsers.size() - 1).getCodigo()+1;
		}
	}

}

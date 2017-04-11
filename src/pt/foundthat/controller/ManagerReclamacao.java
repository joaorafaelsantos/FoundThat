package pt.foundthat.controller;

import pt.foundthat.model.Registo;

public class ManagerReclamacao {


	public static boolean reclamacarObjeto(int codigo) {
		boolean res = false;
		for (int i = 0; i < FoundThat.registos.size(); i++) {
			Registo reg = FoundThat.registos.get(i);
			if (reg.getCodigo() == codigo) {
				FoundThat.registos.remove(i);
				res = true;
			}
		}	


		return res;
	}

}

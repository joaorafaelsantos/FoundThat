package pt.foundthat.controller;

import pt.foundthat.model.Registo;

public class ManagerDoacao {


	public static boolean doarObjeto(int codigo) {
		boolean res = false;

		for (int i = 0; i < FoundThat.registos.size(); i++) {
			Registo reg = FoundThat.registos.get(i);
			if (reg.getCodigo() == codigo) {
				Registo registo = FoundThat.registos.get(i);
				FoundThat.registos.remove(i);
				FoundThat.doacoes.add(registo);
				res = true;
			}
		}	
		return res;
	}
}
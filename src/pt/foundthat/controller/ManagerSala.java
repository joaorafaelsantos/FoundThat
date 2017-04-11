package pt.foundthat.controller;

import pt.foundthat.model.Sala;

public class ManagerSala {


	public static boolean isSala(String sala) {
		boolean res = false;
		for (Sala s : FoundThat.salas) {
			if (s.getNome().equals(sala)) {
				res = true;
			}

		}

		return res;	
	}

	public static boolean adicionarSala(String sala) {
		boolean res = false;
		if (!isSala(sala)) {
			Sala s = new Sala(sala);
			FoundThat.salas.add(s);
			res = true;
		} 
		return res;		
	}

	public static boolean removerSala(String sala) {
		boolean res = false;
		if (isSala(sala) == true) {
			for (int i = 0; i < FoundThat.salas.size(); i++) {
				Sala s = FoundThat.salas.get(i);
				if (s.getNome().equals(sala)) {
					FoundThat.salas.remove(i);	
				}	
			}
			res = false;	
		}

		return res;
	}

	public static boolean alterarSala(String sala, String salaAntiga) {
		boolean res = false;
		if (!isSala(sala)) {
			for (int i = 0; i < FoundThat.salas.size(); i++) {
				Sala s = FoundThat.salas.get(i);
				if (s.getNome().equals(salaAntiga)) {
					s.setNome(sala);
				}

			}
			res = true;
		} 
		return res;	
	}
}
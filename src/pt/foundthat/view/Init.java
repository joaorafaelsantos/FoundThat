package pt.foundthat.view;

import javax.swing.JOptionPane;

import pt.foundthat.controller.FoundThat;

public class Init {

	public static void main(String[] args) {
		try {
			FoundThat.carregarFicheiro();
			FrmLogin f = new FrmLogin();
			f.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}

	}

}

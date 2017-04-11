package pt.foundthat.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import pt.foundthat.controller.FoundThat;

public class FrmImportacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtNomeFicheiro;
	private static JTextField txtDir;
	public static String nomeFicheiroImportacao;
	private JButton btnSair;
	private JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	private static JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmImportacao frame = new FrmImportacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */



	public FrmImportacao() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmImportacao.class.getResource("/pt/foundthat/resources/lupa.png")));
		setResizable(false);
		setTitle("Importa\u00E7\u00E3o - FoundThat");
		//OPCOES MESSAGEBOX(SIM/NÃO)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("Não");
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int selectedOption = JOptionPane.showOptionDialog(null, "Deseja sair da aplicação?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					try {
						FoundThat.gravarFicheiro();
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}

			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFileChooser jfc = new JFileChooser();

		//JTABLE
		String[] nomeColunas = {"Código", "Objeto", "Cor", "Estado", "Sala"};
		@SuppressWarnings("unused")
		int cont = 0;
		String[][] regs = null;

		dtm = new DefaultTableModel(regs, nomeColunas);
		table = new JTable(dtm){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable ( int row, int column )
			{
				return false;
			}
		};
		table.setForeground(new Color(25, 25, 112));
		table.setBackground(new Color(220, 220, 220));
		table.getTableHeader().setEnabled(false);
		table.setBounds(32, 77, 269, 97);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(300, 60));
		table.setFillsViewportHeight(true);
		table.setSelectionBackground(new Color(210, 105, 30));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(70, 94, 302, 87);	
		contentPane.add(scrollPane);

		txtNomeFicheiro = new JTextField();
		txtNomeFicheiro.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNomeFicheiro.setForeground(new Color(25, 25, 112));
		txtNomeFicheiro.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtNomeFicheiro.setEnabled(false);
		txtNomeFicheiro.setBackground(new Color(220, 220, 220));
		txtNomeFicheiro.setBounds(123, 12, 285, 23);
		contentPane.add(txtNomeFicheiro);
		txtNomeFicheiro.setColumns(10);

		txtDir = new JTextField();
		txtDir.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtDir.setForeground(new Color(25, 25, 112));
		txtDir.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtDir.setEnabled(false);
		txtDir.setBackground(new Color(220, 220, 220));
		txtDir.setBounds(123, 45, 285, 23);
		contentPane.add(txtDir);
		txtDir.setColumns(10);


		//BOTÃO ABRIR
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.setBorder(null);
		btnAbrir.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAbrir.setBackground(new Color(25, 25, 112));
		btnAbrir.setForeground(new Color(255, 255, 255));
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAbrir.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAbrir.setBackground(new Color(25, 25, 112));
			}
		});
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rVal = jfc.showOpenDialog(FrmImportacao.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().getName().contains(".txt")) {
						txtNomeFicheiro.setText(jfc.getSelectedFile().getName());
						txtDir.setText(jfc.getCurrentDirectory().toString());
						nomeFicheiroImportacao = txtNomeFicheiro.getText();
						txtNomeFicheiro.setForeground(new Color(25, 25, 112));
						txtDir.setForeground(new Color(25, 25, 112));
						try {
							FoundThat.carregarImportacao();

							//JTABLE

							dtm.setRowCount(0);
							dtm.setColumnCount(0);

							int cont = 0;
							String[][] regs = new String[FoundThat.importacoes.size()][5];
							for (int i = 0; i < FoundThat.importacoes.size(); i++) {
								regs[cont][0] = Integer.toString(FoundThat.importacoes.get(i).getCodigo());
								regs[cont][1] = FoundThat.importacoes.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + FoundThat.importacoes.get(i).getObjeto().getNome().substring(1).toLowerCase();
								regs[cont][2] = FoundThat.importacoes.get(i).getCor().substring(0, 1).toUpperCase() + FoundThat.importacoes.get(i).getCor().substring(1).toLowerCase();
								regs[cont][3] = FoundThat.importacoes.get(i).getEstado().substring(0, 1).toUpperCase() + FoundThat.importacoes.get(i).getEstado().substring(1).toLowerCase();
								regs[cont][4] = FoundThat.importacoes.get(i).getSala().toString();
								cont++;
							}

							for (int j = 0; j < nomeColunas.length; j++) {
								dtm.addColumn(nomeColunas[j]);
							}
							for (int i = 0; i < regs.length; i++) {
								dtm.addRow(regs[i]);
							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Ficheiro corrompido!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Insira um ficheiro de texto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						txtDir.setText("");
						txtNomeFicheiro.setText("");
						dtm.setRowCount(0);
					}
					if (rVal == JFileChooser.CANCEL_OPTION) {
						txtNomeFicheiro.setText("");
						txtDir.setText("");
					}


				}
			}
		});
		btnAbrir.setBounds(24, 11, 89, 23);
		contentPane.add(btnAbrir);


		//BOTÃO +INFO
		btnInfo = new JButton("+Info");
		btnInfo.setBorder(null);
		btnInfo.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnInfo.setBackground(new Color(25, 25, 112));
		btnInfo.setForeground(new Color(255, 255, 255));
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInfo.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnInfo.setBackground(new Color(25, 25, 112));
			}
		});
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					int cod =  table.getSelectedRow();
					String info = "Nome: " + FoundThat.importacoes.get(cod).getNome() + "\n" + "E-mail: " + FoundThat.importacoes.get(cod).getEmail() + 
							"\n" + "Sala: " + FoundThat.importacoes.get(cod).getSala() + "\n" + "Dia: " + FoundThat.formatoDataRegisto.format(FoundThat.importacoes.get(cod).getData()) + 
							"\n" + "Hora: " + FoundThat.importacoes.get(cod).getHora() + "\n" + "Tipo de objeto: " + FoundThat.importacoes.get(cod).getObjeto().getNome().substring(0, 1).toUpperCase() + 
							FoundThat.importacoes.get(cod).getObjeto().getNome().substring(1).toLowerCase() + "\n" + "Cor: " + FoundThat.importacoes.get(cod).getCor().substring(0, 1).toUpperCase() +  
							FoundThat.importacoes.get(cod).getCor().substring(1).toLowerCase() + "\n" + "Estado: " + FoundThat.importacoes.get(cod).getEstado().substring(0, 1).toUpperCase() + 
							FoundThat.importacoes.get(cod).getEstado().substring(1).toLowerCase() + "\n" + "Descrição: " + FoundThat.importacoes.get(cod).getDescricao() ;
					JOptionPane.showMessageDialog(null, info, "FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnInfo.setBounds(80, 201, 89, 23);
		contentPane.add(btnInfo);

		//BOTÃO GRAVAR
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBorder(null);
		btnGravar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnGravar.setBackground(new Color(25, 25, 112));
		btnGravar.setForeground(new Color(255, 255, 255));
		btnGravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGravar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGravar.setBackground(new Color(25, 25, 112));
			}
		});
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDir.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um ficheiro!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					try {
						JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						FoundThat.gravarFicheiro();
						clear();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnGravar.setBounds(24, 45, 89, 23);
		contentPane.add(btnGravar);

		//BOTÃO SAIR
		btnSair = new JButton("Sair");
		btnSair.setBorder(null);
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSair.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setBackground(new Color(25, 25, 112));
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				this.setVisible(false);
				dispose();
				new FrmMain().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setBounds(278, 201, 89, 23);
		contentPane.add(btnSair);

		//BOTÃO LIMPAR
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(null);
		btnLimpar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnLimpar.setBackground(new Color(25, 25, 112));
		btnLimpar.setForeground(new Color(255, 255, 255));
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setBackground(new Color(25, 25, 112));
			}
		});
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnLimpar.setBounds(179, 201, 89, 23);
		contentPane.add(btnLimpar);

	}

	public static void clear() {
		txtDir.setText("");
		txtNomeFicheiro.setText("");
		dtm.setRowCount(0);
	}
}

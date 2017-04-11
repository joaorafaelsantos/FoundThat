package pt.foundthat.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerUser;



public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public static JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setResizable(false);
		//OPCOES MESSAGEBOX(SIM/NÃO)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("Não");
		Image image;
		try {
			image = ImageIO.read(getClass().getResource("/pt/foundthat/resources/lupa.png"));
			setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("FoundThat");
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 315);
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		@SuppressWarnings("unused")
		Border border = BorderFactory.createLineBorder(Color.BLACK);



		JButton btnLogin = new JButton("");
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/pt/foundthat/resources/lupa3.png")).getImage()));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (ManagerUser.isUser(txtUser.getText(), txtPassword.getText()) == true) {
						FoundThat.lerDoacoesIS();
						dispose();
						FrmMain main = new FrmMain();
						main.setVisible(true);
						FrmMain.checkUser();	
					} 
					else {
						JOptionPane.showMessageDialog(getParent(), "Dados incorretos!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						txtUser.setText("");
						txtPassword.setText("");

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
				}

			}

			@SuppressWarnings("unused")
			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnLogin.setBounds(318, 210, 41, 54);
		contentPane.add(btnLogin);
		JLabel lblUser = new JLabel("Utilizador");
		lblUser.setFont(new Font("Myanmar Text", Font.PLAIN, 20));
		lblUser.setBounds(72, 212, 116, 26);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Palavra-passe");
		lblPassword.setFont(new Font("Myanmar Text", Font.PLAIN, 20));
		lblPassword.setBounds(73, 238, 128, 26);
		contentPane.add(lblPassword);

		JLabel lblLogo = new JLabel("");

		lblLogo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/pt/foundthat/resources/logo.png")).getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT)));


		lblLogo.setBounds(138, 20, 160, 160);
		contentPane.add(lblLogo);

		txtUser = new JTextField();
		txtUser.setBounds(198, 213, 110, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(198, 239, 110, 20);
		contentPane.add(txtPassword);
	}
}

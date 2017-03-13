package login;

import java.applet.Applet;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import join.Join;
import main.main;
import month.Month;

public class Login extends JFrame {
	private JButton jbtLogin;
	private JButton jbtJoin;
	private JTextField jtxID;
	private JPasswordField jtxPW;
	private JTextField jtxTOP;
	private ImageIcon loginimg = new ImageIcon("img/로그인.jpg");
	private ImageIcon joinimg = new ImageIcon("img/회원가입.jpg");
	private ImageIcon IDimg = new ImageIcon("img/ID.jpg");
	private ImageIcon PASSimg = new ImageIcon("img/PASS.jpg");
	private ImageIcon Titleimg = new ImageIcon("img/title.jpg");
	private ImageIcon idwrongimg = new ImageIcon("img/idwrong.jpg");
	private ImageIcon passwordwrongimg = new ImageIcon("img/failpassword.jpg");
	// String connect = "jdbc:mysql://203.252.22.165:3306/f4", user = "mw9027",
	// // 학교
	String connect = "jdbc:mysql://121.168.72.64:3306/f4", user = "mw9027", // 기숙사
			passwd = "1234";
	Connection conn;
	Statement stat;

	public Login() {
		JPanel title = new JPanel();
		JPanel login = new JPanel();
		JPanel login2 = new JPanel();

		title.add(new JLabel(Titleimg, JLabel.CENTER));
		login.setLayout(new GridLayout(2, 2));
		login.add(new JLabel(IDimg, JLabel.CENTER));
		login.add(jtxID = new JTextField(null));
		login.add(new JLabel(PASSimg, JLabel.CENTER));
		login.add(jtxPW = new JPasswordField(null));
		add(title, BorderLayout.NORTH);

		add(login, BorderLayout.CENTER);
		login2.setLayout(new GridLayout(1, 2));

		login2.add(jbtJoin = new JButton(joinimg));
		login2.add(jbtLogin = new JButton(loginimg));
		add(login2, BorderLayout.SOUTH);

		setSize(600, 900);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		jbtJoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new Join();
			}
		});
		jbtLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// new Month();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(connect, user, passwd);
					stat = conn.createStatement();
					ResultSet rs = stat.executeQuery("select * from tb_user");
					while (rs.next()) {
						if (rs.getString("id").equals(jtxID.getText())) {
							if (rs.getString("pw").equals(jtxPW.getText())) {
								setVisible(false);
								new EAsports().playAudioFile();
								// new Month();
								main.login_session = jtxID.getText();
							} else {
								JFrame frame = new JFrame();
								frame.add(new JLabel(passwordwrongimg));
								frame.setVisible(true);
								frame.setSize(300, 100);
								frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								frame.setLocationRelativeTo(null);
								frame.setTitle("Login Error");
							}
							break;
						} else if (!rs.getString("id").equals(jtxID.getText())
								&& rs.isLast()) {
							JFrame frame = new JFrame();
							frame.add(new JLabel(idwrongimg));
							frame.setVisible(true);
							frame.setSize(500, 150);
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame.setLocationRelativeTo(null);
							frame.setTitle("Login Error");
						}

					}
				} catch (ClassNotFoundException E) {
					System.out.println("적재오류");
				} catch (SQLException E) {
					System.out.println("연결오류");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}

package join;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import login.Login;

public class Join extends JFrame {

	static private JButton Ok;
	private JButton Cancle;
	private JButton as;
	private JTextField jtxID, jtxNAME, jtxPhone;
	private JPasswordField jtxPW, jtxPW_CONFIRM;
	private JTextArea jtxCOM = new JTextArea("완료");
	private ImageIcon JIDimg= new ImageIcon("img/join/id.jpg");
	private ImageIcon Jpassimg= new ImageIcon("img/join/pass.jpg");
	private ImageIcon Jpass1img= new ImageIcon("img/join/pass1.jpg");
	private ImageIcon Jnameimg= new ImageIcon("img/join/name.jpg");
	private ImageIcon Jphoneimg= new ImageIcon("img/join/phone.jpg");
	private ImageIcon Jokimg= new ImageIcon("img/join/ok.jpg");
	private ImageIcon Jcancelimg= new ImageIcon("img/join/cancel.jpg");
	private ImageIcon Jsuccessimg = new ImageIcon("img/join/success.jpg");
	// String connect = "jdbc:mysql://203.252.22.165:3306/f4", user = "mw9027",
	// // 학교
	String connect = "jdbc:mysql://121.168.72.64:3306/f4", user = "mw9027", // 기숙사
			passwd = "1234";
	Connection conn;
	Statement stat;

	public Join() {
		JPanel ab = new JPanel();
		JPanel ab2 = new JPanel();
		ab.setLayout(new GridLayout(5, 3));
		ab.add(new JLabel(JIDimg));
		ab.add(jtxID = new JTextField(null));
		ab.add(new JLabel(Jpassimg));
		ab.add(jtxPW = new JPasswordField(null));
		ab.add(new JLabel(Jpass1img));
		ab.add(jtxPW_CONFIRM = new JPasswordField(null));
		ab.add(new JLabel(Jnameimg));
		ab.add(jtxNAME = new JTextField(null));
		ab.add(new JLabel(Jphoneimg));
		ab.add(jtxPhone = new JTextField(null));
		add(ab, BorderLayout.NORTH);
		ab2.setLayout(new GridLayout(1, 2));
		ab2.add(Ok = new JButton(Jokimg));
		ab2.add(Cancle = new JButton(Jcancelimg));
		add(ab2);
		setSize(350, 380);
		setTitle("Cloud Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		Ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String query = "insert into tb_user(id,pw,name,phone) values('"
						+ jtxID.getText() + "','" + jtxPW.getText() + "','"
						+ jtxNAME.getText() + "','" + jtxPhone.getText() + "')";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(connect, user, passwd);
					stat = conn.createStatement();
					int i = stat.executeUpdate(query);
					if (i == 1)
						System.out.println("성공");

				} catch (ClassNotFoundException E) {
					System.out.println("적재오류");
				} catch (SQLException E) {
					System.out.println("연결오류");
				}
				JFrame frame = new JFrame();
				frame.setSize(250, 110);
				frame.add(new JLabel(Jsuccessimg));
				setVisible(false);
				new Login();
				/*frame.add(new JButton("확인"),BorderLayout.SOUTH);
				as.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setVisible(false);
						new Login();
					}
				});*/
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
				frame.setTitle("message");
				frame.setVisible(true);
			}
		});
		Cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new Login();

			}
		});

	}
}
package request;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.main;

public class request extends JFrame implements ActionListener {
	// String connect = "jdbc:mysql://203.252.22.165:3306/f4", user = "mw9027",
	// // 학교
	String connect = "jdbc:mysql://121.168.72.64:3306/f4", user = "mw9027", // 기숙사
			passwd = "1234";
	Connection conn;
	Statement stat;
	String matching;
	JTextField jtf;
	JButton Okjbt = new JButton("신청");
	JButton Canceljbt = new JButton("취소");
	public request(String match_num) {
		matching = match_num;
		JPanel jpl = new JPanel();
		setSize(400, 400);
		setTitle("request");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connect, user, passwd);
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("select * from tb_match where num ='"
							+ match_num + "'");
			rs.next();
			jpl.add(new JLabel("상대방 ID : " + rs.getString("id")));
			jpl.add(new JLabel("상대망 메세지"));
			jpl.add(new JLabel(rs.getString("message")));
			jtf=new JTextField(50);
			jtf.setSize(400,200);
			jpl.add(jtf);
			jpl.add(Okjbt);
			jpl.add(Canceljbt);
			jpl.setLayout(new GridLayout(10, 1));
			Canceljbt.addActionListener(this);
			Okjbt.addActionListener(this);
			add(jpl);

		} catch (ClassNotFoundException E) {
			System.out.println("적재오류");
		} catch (SQLException E) {
			System.out.println("연결오류");
		}

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Okjbt)
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(connect, user, passwd);
				stat = conn.createStatement();
				String query = "update tb_match set matching = matching+1 where num ='"
						+ matching + "'";
				stat.executeUpdate(query);
				query = "insert into tb_matching(id,match_num,message) values('"+main.login_session+"','"+matching+"','"+jtf.getText()+"')";
				stat.executeUpdate(query);
				JFrame jf = new JFrame();
				JPanel jpl = new JPanel();
				jf.setTitle("");
				jf.setSize(300, 500);
				jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);
				jpl.add(new JLabel("신청하였습니다"));
				jf.add(jpl);
				setVisible(false);
				
			} catch (ClassNotFoundException E) {
				System.out.println("적재오류");
			} catch (SQLException E) {
				System.out.println("연결오류");
			}
		else if (e.getSource() == Canceljbt)
			setVisible(false);
	}

}

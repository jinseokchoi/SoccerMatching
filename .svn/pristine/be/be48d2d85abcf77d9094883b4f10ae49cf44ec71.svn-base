package show;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.main;
import reply.reply;

public class Show extends JFrame {
	// String connect = "jdbc:mysql://203.252.22.165:3306/f4", user = "mw9027",
	// // 학교
	String connect = "jdbc:mysql://121.168.72.64:3306/f4", user = "mw9027", // 기숙사
			passwd = "1234";
	Connection conn;
	Statement stat;
	List<Component> jlb = new ArrayList<Component>();
	List<JButton> jbt = new ArrayList<JButton>();
	private ImageIcon dateimg = new ImageIcon("img/show/date.jpg");
	private ImageIcon timeimg = new ImageIcon("img/show/time.jpg");
	private ImageIcon requestimg = new ImageIcon("img/show/request.jpg");
	private ImageIcon acceptimg = new ImageIcon("img/show/accept.jpg");

	public Show() {

		int n = 0;
		JPanel[] jpl = new JPanel[3];

		jpl[0] = new JPanel();
		jpl[0].setBackground(Color.white);
		jpl[0].setLayout(new GridLayout(1, 4));

		jpl[1] = new JPanel();

		jpl[2] = new JPanel();
		jpl[2].setBackground(Color.white);
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connect, user, passwd);
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from tb_match");

			jpl[0].add(new JLabel(dateimg));
			jpl[0].add(new JLabel(timeimg));
			jpl[0].add(new JLabel(requestimg));
			jpl[0].add(new JLabel(acceptimg));
			add(jpl[0], BorderLayout.NORTH);
			/*
			 * jpl[0].add(new JLabel(
			 * "      날짜               시간          신청들어온수            신청수락"));
			 */
			while (rs.next()) {
				if (rs.getString("id").equals(main.login_session)
						&& rs.getString("status").equals("0")) {
					jlb.add(new JLabel("     " + rs.getString("date")
							+ "      " + rs.getString("s_hour") + ":"
							+ rs.getString("s_minute") + "~"
							+ rs.getString("e_hour") + ":"
							+ rs.getString("e_minute") + "   " + "          "
							+ rs.getString("matching")));
					jbt.add(new JButton("수락"));
					jbt.get(n).setName(rs.getString("num"));
					jlb.add(jbt.get(n));
					jbt.get(n).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							new reply(((JButton) e.getSource()).getName());
							System.out.println(((JButton) e.getSource()).getName());
							setVisible(false);

						}
					});
					n++;
				}
			}

			for (int i = 0; i < n * 2; i++)
				jpl[2].add(jlb.get(i));
			if (n < 10)
				jpl[1].setLayout(new GridLayout(10, 2, 10, 10));
			else
				jpl[1].setLayout(new GridLayout(n, 2, 10, 10));

			jpl[1].add(jpl[0]);
			jpl[1].add(jpl[2]);
			jpl[1].setLayout(new GridLayout(2, 1));
			add(jpl[1]);
			setVisible(true);
		} catch (ClassNotFoundException E) {
			System.out.println("적재오류");
		} catch (SQLException E) {
			System.out.println("연결오류");
		}
	}
}

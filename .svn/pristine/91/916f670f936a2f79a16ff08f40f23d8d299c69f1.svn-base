package make;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.Login;
import main.main;

public class Make extends JFrame {
	private String[] Year = { "2014", "2015" };
	private String[] Month = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12" };
	private String[] date = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String[] Hour = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24" };
	private String[] Minute = { "00", "15", "30", "45" };
	private JComboBox JCB_Year = new JComboBox(Year);
	private JComboBox JCB_Month = new JComboBox(Month);
	private JComboBox JCB_date = new JComboBox(date);
	private JComboBox Start_hour = new JComboBox(Hour);
	private JComboBox Start_minute = new JComboBox(Minute);
	private JComboBox End_hour = new JComboBox(Hour);
	private JComboBox End_minute = new JComboBox(Minute);
	
	private ImageIcon dateimg = new ImageIcon("img/make/date.jpg");
	private ImageIcon timeimg = new ImageIcon("img/make/time.jpg");
	private ImageIcon okimg = new ImageIcon("img/make/ok.jpg");	
	private ImageIcon cancelimg = new ImageIcon("img/make/cancel.jpg");
	private ImageIcon sucimg = new ImageIcon("img/make/makesuc.jpg");

private JButton OK = new JButton(okimg);
	private JButton Cancel = new JButton(cancelimg);

	// String connect = "jdbc:mysql://203.252.22.165:3306/f4", user = "mw9027",
	// // 학교
	String connect = "jdbc:mysql://121.168.72.64:3306/f4", user = "mw9027", // 기숙사
			passwd = "1234";
	Connection conn;
	Statement stat;

	public Make() {
		JPanel Month_day = new JPanel();
		Month_day.setBackground(Color.white);

		JPanel Time = new JPanel();
		Time.setBackground(Color.white);

		JPanel North = new JPanel();
		North.setBackground(Color.white);

		North.setLayout(new GridLayout(2, 2, 1, 1));
		Time.add(Start_hour);
		Time.add(Start_minute);
		Time.add(new JLabel(" ~ "));
		Time.add(End_hour);
		Time.add(End_minute);
		Month_day.add(JCB_Year);
		Month_day.add(JCB_Month);
		Month_day.add(JCB_date);
		North.add(new JLabel(dateimg));
		North.add(Month_day);
		North.add(new JLabel(timeimg));
		North.add(Time);
		JPanel adc = new JPanel();
		adc.setBackground(Color.white);
		OK.setBorder(null);
    	OK.setContentAreaFilled(false);
    	Cancel.setBorder(null);
    	Cancel.setContentAreaFilled(false);
		adc.add(OK);
		adc.add(Cancel);
		add(North, BorderLayout.NORTH);
		add(adc, BorderLayout.SOUTH);
		Cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String query = "insert into tb_match(id,date,s_hour,s_minute,e_hour,e_minute) values('"
						+ main.login_session
						+ "','"
						+ JCB_Year.getSelectedItem()
						+ "-"
						+ JCB_Month.getSelectedItem()
						+ "-"
						+ JCB_date.getSelectedItem()
						+ "','"
						+ Start_hour.getSelectedItem()
						+ "','"
						+ Start_minute.getSelectedItem()
						+ "','"
						+ End_hour.getSelectedItem()
						+ "','"
						+ End_minute.getSelectedItem() + "')";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(connect, user, passwd);
					stat = conn.createStatement();
					int i = stat.executeUpdate(query);
					JFrame frame = new JFrame();
					frame.setVisible(true);
					frame.setSize(300, 180);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setLocationRelativeTo(null);

					if (i == 1) {
						frame.setTitle("생성 성공");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.add(new JLabel(sucimg));

					} else {
						frame.setTitle("생성 실패");
						frame.add(new JLabel("생성 성공"));
					}

				} catch (ClassNotFoundException E) {
					System.out.println("적재오류");
				} catch (SQLException E) {
					System.out.println("연결오류");
				}

				setVisible(false);
			}
		});

		setSize(500, 200);
		setTitle("Make");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}

package login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

import month.Month;

public class EAsports extends JFrame {
	private ImageIcon eaIcon = new ImageIcon("img/fi.jpg");
	private static int EXTERNAL_BUFFER_SIZE = 128000;

	public EAsports() throws Exception {
		JPanel p1 = new JPanel();
		JButton ea = new JButton(eaIcon);
		ea.setBorder(null);
    	ea.setContentAreaFilled(false);
		p1.add(ea);
		ea.addActionListener(new MyActionListener());
		add(p1);

		setSize(1050, 750);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EAsports");
		setVisible(true);

	}
	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new Month();

		}
	}
	public void playAudioFile() throws Exception {// 메소드

		String audioFile = "EA.wav"; // 연결할 wav파일 위치

		Clip clip;
		File soundFile = new File(audioFile);
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(soundFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();

			int nBytesRead = 0;
			byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0) {
					line.write(abData, 0, nBytesRead);
				}
			}
			line.drain();
			line.close();
			audioInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	

}

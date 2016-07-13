package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FileExplorer extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {

		FileExplorer frame = new FileExplorer();
		frame.setVisible(true);

	}

	public FileExplorer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(12, 12, 117, 25);
		btnBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openDialogBox();

			}

		});
		contentPane.add(btnBrowse);

		JButton btnColor = new JButton("Add color");
		btnColor.setBounds(302, 12, 117, 25);
		btnColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showColor();

			}

		});
		contentPane.add(btnColor);

	}

	protected void openDialogBox() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		File selectedFile = fileChooser.getSelectedFile();
		File files[] = ScanDemo.getFiles(selectedFile);
		System.out.println(selectedFile.getAbsolutePath());

	}

	private void showColor() {
		Color c = JColorChooser.showDialog(this, "My Color", Color.RED);
		this.getContentPane().setBackground(c);
	}
}

package gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Logic.FileReadOperation;
import Logic.ScanDemo;

public class FileExplorer extends JFrame {
	JScrollPane scrollPane;
	private JPanel contentPane;
	List list = new List();
	File selectedFile;
	JLabel lblImage;
	JLabel lblMusic;
	JTextArea textArea = new JTextArea();

	public static void main(String[] args) {
		FileExplorer frame = new FileExplorer();
		frame.setTitle("File Explorer");
		frame.setVisible(true);
	}

	private void applyFilter(String extension) {
		list.clear();
		extension = extension.substring(1);
		System.out.println("ext is : " + extension);
		File files[] = ScanDemo.getFiles(selectedFile, extension);
		if (files.length > 0) {
			for (File file : files) {
				list.add(file.getPath());
			}
		}

	}

	private void playMusic(File selectedFile2) {
		System.out.println("in musicPlay "+selectedFile2.getAbsolutePath());
	if(Desktop.isDesktopSupported()){

		try {
			Desktop.getDesktop().open(selectedFile2.getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		lblMusic = new JLabel("playing music....");
		lblMusic.setSize(1200, 1200);
		lblMusic.setIcon(new ImageIcon(FileExplorer.class.getResource("musicPlayer.jpg")));
		//lblMusic.setIcon(new ImageIcon("musicPlayer.jpg"));
		getContentPane().add(lblMusic);
		scrollPane.getViewport().add(lblMusic);
	}

	private void showImage(File fileContent) {
		System.out.println("in show image " + fileContent.getAbsolutePath());
		lblImage = new JLabel("image here");
		lblImage.setSize(300, 300);
		lblImage.setIcon(new ImageIcon(fileContent.getAbsolutePath()));
		getContentPane().add(lblImage);
		scrollPane.getViewport().add(lblImage);

	}

	private void printFile(String selectedItem) {

		try {
			String fileContent = FileReadOperation.readFile(selectedItem);
			textArea.setText(fileContent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void openDialogBox() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(this);
		selectedFile = fileChooser.getSelectedFile();
		File files[] = ScanDemo.getFiles(selectedFile, null);
		if (files.length > 0) {
			for (File file : files) {
				list.add(file.getPath());
			}
		}
	}

	private void showColor() {
		Color c = JColorChooser.showDialog(this, "My Color", Color.RED);
		this.getContentPane().setBackground(c);
	}

	public FileExplorer() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(24, 23, 117, 29);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openDialogBox();
			}
		});
		contentPane.add(btnBrowse);

		JButton btnColor = new JButton("Add color");
		btnColor.setBounds(138, 23, 117, 29);
		btnColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showColor();
			}

		});
		contentPane.add(btnColor);

		list.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("item sel is : " + list.getSelectedItem());
				if (list.getSelectedItem().endsWith(".jpg")) {
					System.out.println("ends with : .jpg");
					textArea.setVisible(false);
					getContentPane().repaint();
					showImage(new File(list.getSelectedItem()));

				} else if (list.getSelectedItem().endsWith(".mp3")) {
					System.out.println("ends with mp3...");
					
					if(textArea.isVisible()==true){
					textArea.setVisible(false);}
					else if(lblImage.isVisible()==true){
						lblImage.setVisible(false);
					}
					playMusic(new File(list.getSelectedItem()));

				} else {

					textArea.setVisible(true);
					printFile(list.getSelectedItem());
				}
			}
		});
		list.setBounds(34, 58, 262, 288);
		contentPane.add(list);

		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				applyFilter(choice.getSelectedItem());
				System.out.println("apply filter");
			}
		});
		choice.add("*.txt");
		choice.add("*.mp3");
		choice.add("*.jpg");
		choice.add("*.gif");
		choice.add("*.html");
		choice.setBounds(44, 352, 151, 27);
		contentPane.add(choice);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 36, 700, 400);
		contentPane.add(scrollPane);
		scrollPane.getViewport().add(textArea);

		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea.setBounds(348, 36, 400, 300);
		contentPane.add(textArea);

	}

}

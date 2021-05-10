package project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Window extends JFrame {

	private JPanel contentPane;
	String exp ="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 445);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		//https://m.blog.naver.com/war2i7i7/220847905758
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(new File("c:\\11"));
				fc.setDialogTitle("Open a File");
				fc.setFileFilter(new FileTypeFilter(".java", "java File"));
				
				int result = fc.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					exp = file.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
						
						String line = "";
						int c = 0;
						while((c = br.read()) != -1) {
							if((char)c == '\t') {
								line += "    ";
								continue;
							}
							line += (char)c;
						}
						textArea.setText(line);
						if(br != null) {
							br.close();
						}
					} catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		//https://m.blog.naver.com/war2i7i7/220847905758
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(new File("c:\\11\\"));
				fc.setDialogTitle("Save a File");
				fc.setFileFilter(new FileTypeFilter(".java", "java File"));
				int result = fc.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) 
				{
					String content = textArea.getText();
					File file = fc.getSelectedFile();
					exp = file.getPath();
					try 
					{
						
						FileWriter fw = new FileWriter(file.getPath() + ".java");
						fw.write(content);
						fw.flush();
						fw.close();
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Copy");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Paste");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cut");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);
		//https://superblo.tistory.com/entry/커맨드cmd에서-자바-컴파일하기-및-실행-방법
		//https://m.blog.naver.com/PostView.nhn?blogId=slayra&logNo=221215991017&categoryNo=7&proxyReferer=&proxyReferer=https:%2F%2Fwww.google.com%2F
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Compile");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String command = "javac " + exp+".java";
				Runtime rt = Runtime.getRuntime();
				Process p = null;
				try 
				{
					p = rt.exec(command);
					textArea_1.setText(exp+"가 컴파일 되었습니다.");
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}

				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("run");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c = "java " + exp;
				
				Runtime rt = Runtime.getRuntime();
				Process p = null;
				try 
				{
					p = rt.exec(c);
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				String s = "";
				try{
					while((line = br.readLine()) != null) {
						s += line + "\n";
						System.out.println(line);
					}
					textArea_1.setText(s);
					if(br != null)
						br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
	}

}

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
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Window extends JFrame {

	private JPanel contentPane;
	String path = "";
	private JTextArea textArea;
	private JTextArea resultArea;

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
		setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 13));
		setTitle("untitled.java");
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 13));
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				resultArea.setText("");
				setTitle("untitled.java");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		/*
		 * https://m.blog.naver.com/war2i7i7/220847905758
		 */
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		/*
		 * https://m.blog.naver.com/war2i7i7/220847905758
		 */
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(new File("c:\\11\\"));
				fc.setDialogTitle("Save a File");
				fc.setFileFilter(new FileTypeFilter(".java", "java File"));
				int result = fc.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String text = textArea.getText();
					File file = fc.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(file.getPath() + ".java");
						fw.write(text);
						fw.close();
						path = file.getPath() + ".java";
						setTitle(file.getName() + ".java");

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		mnNewMenu_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Copy");
		mntmNewMenuItem_4.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Paste");
		mntmNewMenuItem_5.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cut");
		mntmNewMenuItem_6.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_2 = new JMenu("Compile");
		mnNewMenu_2.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_2);
		/*
		 * https://superblo.tistory.com/entry/커맨드cmd에서-자바-컴파일하기-및-실행-방법
		 * *https://m.blog.naver.com/PostView.nhn?blogId=slayra&logNo=221215991017&
		 * categoryNo=7&proxyReferer=&proxyReferer=https:%2F%2Fwww.google.com%2F
		 */
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Compile");
		mntmNewMenuItem_7.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String command = "javac " + path;
				Runtime rt = Runtime.getRuntime();
				Process p = null;
				if(textArea.getText().isBlank()) {
					resultArea.setText("코드가 입력되지 않았습니다.");
				}
				else {
					try {
						p = rt.exec(command);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					resultArea.setText(path + "가 컴파일 되었습니다.");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Run");
		mntmNewMenuItem_8.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = "java " + path;

				Runtime rt = Runtime.getRuntime();
				Process p = null;
				if(textArea.getText().isBlank()) {
					resultArea.setText("코드가 입력되지 않았습니다.");
				}
				else {
				try {
					p = rt.exec(command);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				String text = "";
				try {
					while ((line = br.readLine()) != null) {
						text += line + "\n";
					}
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}resultArea.setText(text);
			}}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);

		JMenu mnNewMenu_3 = new JMenu("Option");
		mnNewMenu_3.setVerticalTextPosition(SwingConstants.TOP);
		mnNewMenu_3.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Developer");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Developer(getLocation());
			}
		});
		mntmNewMenuItem_9.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Line");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = "";
				
			}
		});
		mntmNewMenuItem_10.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mnNewMenu_3.add(mntmNewMenuItem_10);

		textArea = new JTextArea();
		
		textArea.setTabSize(4);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(SystemColor.menu);
		textArea.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		
	
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		resultArea = new JTextArea();
		resultArea.setForeground(Color.BLACK);
		resultArea.setBackground(SystemColor.controlHighlight);
		resultArea.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		scrollPane_1.setViewportView(resultArea);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getresultArea() {
		return resultArea;
	}
}

/*
 * https://arintvsecond.tistory.com/14 [ARINTV ARCHIVE]
 * http://blog.naver.com/PostView.nhn?blogId=polpoipol&logNo=140104775477
*/
class Developer extends JFrame {
	Point point;
	Developer(Point point) {
		setTitle("개발자");
		JPanel DeveloperContainer = new JPanel();
		setContentPane(DeveloperContainer);

		JLabel NewLabel = new JLabel("<html>이름 : 나수하<br>학번 : 183607</html>");
		setLocation(point);
		DeveloperContainer.add(NewLabel);
		setSize(250, 100);
		setResizable(false);
		setVisible(true);
	}
}


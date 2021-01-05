package MovieReviews;

import java.awt.Font;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SubmitFrame extends JFrame implements ActionListener{
	
	JButton button,browse;
	JPanel panel;
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4;
	JTextArea area; 
	String s;
	
	public SubmitFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l1 = new JLabel("Name:");
		l1.setBounds(20, 15, 100, 30);
		
		l2 = new JLabel("Genre:");
		l2.setBounds(20, 55, 100, 30);
		
		l3 = new JLabel("Rating:");
		l3.setBounds(20, 95, 100, 30);
		
		l4 = new JLabel("Discription:");
		l4.setBounds(20, 135, 100, 30);
		
		l5 = new JLabel("Img path:");
		l5.setBounds(20, 195, 100, 30);
		
		t1 = new JTextField();
		t1.setBounds(90, 20, 150, 20);
		t1.setFont(new Font("Consolas",Font.PLAIN,15));
		
		t2 = new JTextField();
		t2.setBounds(90, 60, 150, 20);
		
		t3 = new JTextField();
		t3.setBounds(90, 100, 150, 20);
		
		area = new JTextArea();
		area.setBounds(90, 140, 150, 50);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		
		JScrollPane sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(90, 140, 150, 50);
		
		browse = new JButton("Browse");
		browse.addActionListener(this);
		browse.setBounds(100,200,100, 30);
		
		button = new JButton("Submit");
		button.addActionListener(this);
		button.setBounds(100,250,100, 30);
		
		this.add(t1);
		this.add(t2);
		this.add(t3);
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(l5);
		this.add(sp);
//		area.add(pane);
		this.add(button);
		this.add(browse);
		
		this.setLayout(null);
		this.setSize(300,400);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			try {
				dbconn conn = new dbconn();
				conn.stmt.executeUpdate("insert into moviesdetails (id,name,genre,rating,description,imgpath) VALUES (null,'"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+area.getText()+"','"+s+"')");
				new HomeFrame();
				this.dispose();

			}catch(Exception exc) {
				System.out.println(exc);
			}
		}
		
		if(e.getSource() == browse) {
		     JFileChooser fileChooser = new JFileChooser();
	         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
	         fileChooser.addChoosableFileFilter(filter);
	         int result = fileChooser.showSaveDialog(null);
	         if(result == JFileChooser.APPROVE_OPTION){
	             File selectedFile = fileChooser.getSelectedFile();
	             String path = selectedFile.getAbsolutePath();
	             s = path.replace("\\", "/");
	             s = s.replace("C:/Users/hp/eclipse-workspace/MovieReviews/res/", "/");
	             System.out.println(path);
	              }
	         else if(result == JFileChooser.CANCEL_OPTION){
	             System.out.println("No Data");
	         }
		}
	}
	
	

}

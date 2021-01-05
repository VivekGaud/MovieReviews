package MovieReviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class HomeFrame extends JFrame implements ActionListener{
	JButton button,showimg;
	ImageIcon imgicon,i2;
	JLabel label,l2,l3;
	JPanel panel,p1,p2;
	JComboBox<String> cb;
	
	public HomeFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton("Add New");
		button.addActionListener(this);
		button.setBounds(30,50,120,30);
		
		panel =new JPanel();
		panel.setBounds(180, 47, 125, 30);
//		panel.setBackground(Color.black);
		
		cb = new JComboBox<>();
		cb.setFont(new Font("Consolas",Font.PLAIN,17));
		cb.setPrototypeDisplayValue("text here");
		cb.setBackground(Color.white);
		
		showimg = new JButton("Show Movies");
		showimg.addActionListener(this);
		showimg.setBounds(300,50,120,30);
		
		label = new JLabel();
		label.setBounds(10, 100, 110, 163);
		
		l2 =new JLabel();
		l2.setSize(new Dimension(110,163));
		l2.setLocation(10, 90);
		l2.setBackground(Color.black);
		
//		p2 =new JLabel();
//		p2.setSize(new Dimension(110,163));
//		p2.setLocation(125, 90);
//		p2.setBackground(Color.green);
		
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.stmt.executeQuery("select name,imgpath from moviesdetails");
			while(rs.next()) {
				cb.addItem(rs.getString("name"));
				i2 = new ImageIcon(getClass().getResource(rs.getString("imgpath")));
				l2.setIcon(i2);
				
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		
		this.add(button);
		this.add(showimg);
		this.add(panel);
		panel.add(cb);
		this.add(l2);
//		this.add(p2);
		this.add(label);
		
		
		this.setSize(500,500);
//		this.setLayout(new GridLayout(2,3));
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			new SubmitFrame();
			this.dispose();
		}
		
		if(e.getSource() == showimg) {
			new MovieFrame(cb.getSelectedItem().toString());
			this.dispose();
		}
				
				
	}
	
}

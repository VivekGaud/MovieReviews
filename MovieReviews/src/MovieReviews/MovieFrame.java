package MovieReviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MovieFrame extends JFrame implements ActionListener{
	
	private String name,rating,genre,desc,imgpath;
	JPanel panel,p1;
	JLabel label,l2,l3,l4,l5;
	JButton back;
	JTextArea area;
	
	public MovieFrame(String name) {
		this.name = name;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.stmt.executeQuery("select * from moviesdetails where name='"+name+"'");
			if(rs.next()) {
				genre = rs.getString("genre");
				rating = rs.getString("rating");
				desc = rs.getString("description");
				imgpath = rs.getString("imgpath");
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		panel = new JPanel();
		panel.setBounds(20, 60, 110, 163);
//		panel.setBackground(Color.green);
		
		l5 = new JLabel();
		
		ImageIcon icon = new ImageIcon(getClass().getResource(imgpath));
		l5.setIcon(icon);
		
		label = new JLabel(name);
		label.setBounds(150, 60, 200, 30);
		
		l2 = new JLabel(genre);
		l2.setBounds(150, 80, 200, 30);
		
		l3 = new JLabel(rating);
		l3.setBounds(150, 100, 50, 30);
		
		area = new JTextArea(desc);
		area.setBounds(150, 130, 200, 100);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		
		JScrollPane sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(150, 130, 200, 100);
		
		back = new JButton("<---");
		back.setFont(new Font("Consolas",Font.PLAIN,15));
		back.setBounds(280, 280, 90, 30);
		back.addActionListener(this);
		
		this.add(back);
		this.add(panel);
		this.add(label);
		this.add(l2);
		this.add(l3);
		this.add(sp);
		panel.add(l5);
		
		this.setSize(400,400);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back) {
			new HomeFrame();
			this.dispose();
		}
		
	}

}

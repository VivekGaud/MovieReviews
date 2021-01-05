package MovieReviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

public class HomeFrame extends JFrame implements ActionListener{
	
	ArrayList<MovieDetails> list = new ArrayList<>();
	MovieDetails moviedetails;
	
	JButton button,showimg;
	JButton[] btn = new JButton[8];
	ImageIcon imgicon,i2;
	JLabel label,l2;
	JPanel panel,p1,p2;
	JComboBox<String> cb;int i;
	
	public HomeFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Movie Revie");
		
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
		
//		p2 =new JLabel();
//		p2.setSize(new Dimension(110,163));
//		p2.setLocation(125, 90);
//		p2.setBackground(Color.green);
		
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.stmt.executeQuery("select id,name,imgpath from moviesdetails");
			while(rs.next()) {
				cb.addItem(rs.getString("name"));
				moviedetails = new MovieDetails(rs.getInt("id"),rs.getString("name"),rs.getString("imgpath"));
				list.add(moviedetails);				
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		
		//To display 8 image on label
		if(list.size() <=8) {
			displayMovie(list.size());
		}else {
			displayMovie(8);
		}
		
		this.add(button);
		this.add(showimg);
		this.add(panel);
		panel.add(cb);
//		this.add(l2);
//		this.add(p2);
		this.add(label);
		
		
		this.setSize(500,500);
//		this.setLayout(new GridLayout(2,3));
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	public void displayMovie(int len) {
		
		int x = 10,y=110,temp=10,cx=15,cy=240,temp2=15;
		if(len>0) {
		for(i=0;i<len;i++) {
//			System.out.println("cx"+cx+" cy"+cy);
			l2=new JLabel();
			l2.setSize(new Dimension(110,163));
			l2.setLocation(x, y);
			l2.setBackground(Color.black);
			
			i2 = new ImageIcon(getClass().getResource(list.get(i).getImgpath()));
			l2.setIcon(i2);
			
			btn[i] = new JButton(list.get(i).getName());
			btn[i].setBounds(cx, cy, 100, 30);
//			btn[i].addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					new MovieFrame(list.get(i).getName());	
//					HomeFrame.this.dispose();
//				}
//			});
			
			this.add(btn[i]);
			this.add(l2);
			if(i<3) {
				x += 120;
				cx += 120;
			}else {
				y = 280;
				cy = 410;cx=temp2;temp2+=120;
				x = temp;
				temp = temp+120;
			}
		}
		}
		
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

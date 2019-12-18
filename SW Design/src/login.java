import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login extends JFrame{

	public String Id;
	public login(){
		DB a = new DB();
		JPanel p = new JPanel();
		JButton j1 = new JButton("Register");
		JButton j2 = new JButton("Login");
		JButton j3 = new JButton("Admin");
		JTextField t1 = new JTextField();
		JPasswordField t2 = new JPasswordField();
		JLabel l1 = new JLabel("ID");
		JLabel l2 = new JLabel("Password");
		j1.setBounds(720,450,150,30); //회원가입
		j2.setBounds(720,400,150,30); //로그인
		j3.setBounds(900,420,100,40); //Admin
		t1.setBounds(700,300,200,30); //아이디칸
		t2.setBounds(700,350,200,30); //비밀번호칸
		l1.setBounds(600,300,150,30); //아이디
		l2.setBounds(600,350,150,30); //비밀번호
		add(j1);
		add(j2);
		add(j3);
		add(t1);
		add(t2);
		add(l1);
		add(l2);
		setSize(1500,1000);
		setLayout(null);
		setVisible(true);
		j1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register f2 = new register();
				f2.setVisible(true);
			}
		});;
		
		j2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				String s1;
				String pw = "";
				char[] s2 = new char[20];
				s1=t1.getText();
				s2=t2.getPassword();
					
				for(char cha : s2) {
					pw += Character.toString(cha);
				}
				if(a.find(s1,pw))
				{
					Id = s1;
					JOptionPane.showMessageDialog(null, "Sucess Login!");
					Main gui = new Main(s1); //일반 점주용 페이지
					gui.setId(s1);
					System.out.println("Id in Login: "+s1);
					
					gui.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong ID or Password");
				}		
			}
		});
		
		j3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				String s1; 
				char[] s2 = new char[20];
				String s3 = "";
				s1=(String) t1.getText();
				s2=t2.getPassword();
				for(char cha : s2) {
					Character.toString(cha);
					s3 += (s3.equals(""))?""+cha+"":""+cha+"";
				}
				
				if(a.find(s1, s3))
				{
					if(a.ad_find(s1))
					{
						JOptionPane.showMessageDialog(null, "Hello, Sir!");
						Admain gui = new Admain();
						gui.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "This Id is not Admin!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong ID or Password");
				}
			}
		});
	}
	public String getId()
	{
		return Id;
	}
	
	public static void main(String[] args)
	{
		login gui = new login();
		gui.setVisible(true);
	}
}
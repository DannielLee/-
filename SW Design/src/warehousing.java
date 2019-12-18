import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class warehousing extends JFrame{

public static final int WIDTH = 800;
public static final int HEIGHT = 500;
int i = 0;
public String Id;

public String Pname[] = new String[20], Exdate[] = new String[20];
public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
public Date Edate[] = new Date[20];
public boolean Event[] = new boolean[20];
// Stock set

public int Margin[] = new int[20];
public double Psales[] = new double[20];
// Sales set
	DB database = new DB();

	public warehousing(String _Id)
	{
		Id= _Id;
		Pname = database.getPname(Id); Pcost = database.getPcost(Id); Quantity = database.getQuantity(Id);
		Whole = database.getWhole(Id); Edate = database.getEdate(Id); Event = database.getEvent(Id);
		setSize(WIDTH, HEIGHT);
		setTitle("Warehousing");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(6,2));
		JTextField text1, text2, text3, text4, text5;
		JButton b1  = new JButton("확인");
		JLabel label_name = new JLabel("제품명");
		JLabel label_amount = new JLabel("수량");
		JLabel label_price = new JLabel("가격");
		JLabel label_expdate = new JLabel("유통기한");
		JLabel label_whole = new JLabel("도매가");
		label_name.setFont(new Font("굴림", Font.BOLD, 20));
		label_amount.setFont(new Font("굴림", Font.BOLD, 20));
		label_price.setFont(new Font("굴림", Font.BOLD, 20));
		label_expdate.setFont(new Font("굴림", Font.BOLD, 20));
		label_whole.setFont(new Font("굴림", Font.BOLD, 20));
		label_name.setHorizontalAlignment(JLabel.CENTER);
		text1 = new JTextField(10);
		label_amount.setHorizontalAlignment(JLabel.CENTER);
		text2 = new JTextField(10);
		label_price.setHorizontalAlignment(JLabel.CENTER);
		text3 = new JTextField(10);
		label_expdate.setHorizontalAlignment(JLabel.CENTER);
		text4 = new JTextField(10);
		label_whole.setHorizontalAlignment(JLabel.CENTER);
		text5 = new JTextField(10);
		b1.setFont(new Font("돋움", Font.BOLD, 20));
		b1.setPreferredSize(new Dimension(200, 100));
		panel1.add(label_name);
		panel1.add(text1); // name
		panel1.add(label_amount);
		panel1.add(text2); // amount
		panel1.add(label_price);
		panel1.add(text3); // price
		panel1.add(label_expdate);
		panel1.add(text4); // edate
		panel1.add(label_whole);
		panel1.add(text5); // whole
		panel1.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String amount_t = text2.getText().toString(); String price_t = text3.getText().toString();
					String name = text1.getText().toString(); String expdate = text4.getText().toString();
					String whole_t = text5.getText().toString();
					int amount = Integer.parseInt(amount_t); int price = Integer.parseInt(price_t);
					int whole = Integer.parseInt(whole_t);
					database.Pinsert(name, price, whole, expdate, amount, Id);
					Pname[i] = name; Exdate[i] = expdate;  Pcost[i] = price; Quantity[i] = amount;
					Whole[i] = whole;
					dispose();
				} catch(NumberFormatException e1) {
					System.out.println("Parse 오류");
				} catch(Exception e2) {
					System.out.println("그냥 오류");
				}
				
			}
		});
		add(panel1);
	}

	public void setId(String _Id)
	{
		Id = _Id;
	}
	
	public String[] getPname()
	{
		return Pname;
	}
	
	public String[] getEdate()
	{
		return Exdate;
	}
	
	public int[] getPrice()
	{
		return Pcost;
	}
	
	public int[] getAmount()
	{
		return Quantity;
	}
	
	public int[] getWhole()
	{
		return Whole;
	}
	
}
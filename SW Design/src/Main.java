import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;



public class Main extends JFrame
{
	private DefaultTableModel model1;
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	
	public String Id;
	
	public String Pname[] = new String[20], Expdate[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20];
	// Sales set
 	DB database = new DB();
 	
 	
	String[] title = {"제품명","수량","가격","유통기한"};
	Object[][] data = new Object[100][4];
	JButton b1, b2, b3;
	
	
	public void setId(String _Id)
	{
		Id = _Id;
	}
	
	
	public Main(String _Id)
	{
		Id = _Id;
		Pname = database.getPname(Id); Pcost = database.getPcost(Id); Quantity = database.getQuantity(Id);
		Whole = database.getWhole(Id); Edate = database.getEdate(Id); Event = database.getEvent(Id);
		setSize(WIDTH, HEIGHT);
		setTitle("메인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Management Main");
		label1.setFont(new Font("Arial", Font.BOLD, 60));
		panel1.setBackground(Color.white);
		panel1.add(label1);
		add(panel1, BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.white);
		JTable table;
		JScrollPane scroll;
		model1 = new DefaultTableModel(data, title) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model1);
		table.setRowHeight(32);
		table.setFont(new Font("굴림", Font.BOLD, 30));
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(1000, 600));
		panel2.add(scroll);
		add(panel2, BorderLayout.CENTER);
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.black);
		b1 = new JButton("재고 관리");
		b2 = new JButton("행사 상품");
		b3 = new JButton("매출 분석");
		b1.setFont(new Font("돋움", Font.BOLD, 30));
		b2.setFont(new Font("돋움", Font.BOLD, 30));
		b3.setFont(new Font("돋움", Font.BOLD, 30));
		b1.setPreferredSize(new Dimension(200, 100));
		b2.setPreferredSize(new Dimension(200, 100));
		b3.setPreferredSize(new Dimension(200, 100));
		buttonpanel.add(b1);
		buttonpanel.add(b2);
		buttonpanel.add(b3);
		add(buttonpanel, BorderLayout.SOUTH);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockmanagement sh = new stockmanagement(Id);
				sh.setId(Id);
				sh.setVisible(true);
				init();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event rs = new Event(Id);
				rs.setId(Id);
				rs.setVisible(true);
				init();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analysis as = new Analysis(Id);
				as.setId(Id);
				as.setVisible(true);
				init();
			}
		});
	}

	public void init()
	{
		int i;

		for(i = 0; i<20; i++)
		{
			if(Pname[i]==null)
				break;
			Expdate[i] = Edate[i].toString(); 

			data[i][0] = Pname[i]; data[i][1] = Quantity[i];
			data[i][2] = Pcost[i]; data[i][3] = Expdate[i];

		}

	}
	/*
	public static void main(String[] args)
	{
		Main gui = new Main();
		gui.setVisible(true);
	}
	*/
}	
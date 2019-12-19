import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Analysis extends JFrame
{
	private DefaultTableModel model1;
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	public String Id;
	
	public int All[] = new int[20];
	public String Pname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20];
	// Sales set
 	DB database = new DB();
	
	String[] title = {"제품명","판매량","판매액", "마진"}; // 제품명, 판매량, 제품 총 판매액, 제품 순이익
	Object[][] data = new Object[100][4];

 	public Analysis(String _Id)
    {
 		Id= _Id;
 		Pname = database.getPname(Id);
 		Pcost = database.getPcost(Id);
 		Quantity = database.getQuantity(Id);
 		Whole = database.getWhole(Id);
 		
 		Calculate();
        setSize(WIDTH-150, HEIGHT-150);
        setTitle("Sales Analysis");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Sales Analysis");
	    label1.setFont(new Font("Arial", Font.BOLD, 60));
	    panel1.add(label1);
	    add(panel1, BorderLayout.NORTH);
        
        JPanel panel2 = new JPanel();
	    panel2.setBackground(Color.green);
        JTable table;
        JScrollPane scroll;
        model1 = new DefaultTableModel(data, title) {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
	    table = new JTable(model1);
	    table.setRowHeight(28);
	    table.setFont(new Font("굴림", Font.BOLD, 25));
	    scroll = new JScrollPane(table);
	    scroll.setPreferredSize(new Dimension(1000, 600));
	    panel2.add(scroll);
	    add(panel2, BorderLayout.CENTER);
	    
	    JPanel buttonpanel = new JPanel();
	    buttonpanel.setLayout(new GridLayout(2,2));
	    JLabel all = new JLabel("총 판매액"), income = new JLabel("순이익");
	    JTextField all_t = new JTextField(""+database.getSaleAll(Id)), 
	    		income_t = new JTextField(""+database.getNetincome());
	    all_t.setEditable(false);
	    income_t.setEditable(false);
	    buttonpanel.setBackground(Color.gray);
	    all.setFont(new Font("돋움", Font.BOLD, 20));
	    income.setFont(new Font("돋움", Font.BOLD, 20));
	    buttonpanel.add(all);
	    buttonpanel.add(all_t);
	    buttonpanel.add(income);
	    buttonpanel.add(income_t);
	    add(buttonpanel, BorderLayout.SOUTH);
    }
 	
 	public void Calculate()
 	{
 		int i;

 		for(i = 0; i<20; i++)
 		{
 			if(Pname[i]==null)
 				break;
 			All[i] = Pcost[i] * (100 -Quantity[i]);
 			Margin[i] = All[i] - (Whole[i] * (100 - Quantity[i]));
 			double Numerator = (double)(100-Quantity[i]);
 			Psales[i] = Numerator/100;
 			
 			data[i][0] = Pname[i]; data[i][1] = (100 - Quantity[i]);
 			data[i][2] = All[i]; data[i][3] = Margin[i];
 			System.out.println(""+Psales[i]);
 			
 			database.Sinsert(Pname[i], (double) Psales[i], Margin[i], 0);
 		}
 	}
 	
 	public void setId(String _Id)
 	{
 		Id = _Id;
 	}
 	
 
}
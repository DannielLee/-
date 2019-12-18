import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class sales_history extends JFrame{

	private DefaultTableModel model1;
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;

	public String Id;

	public String Pname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20], Soldout[] = new boolean[20];
	// Stock set

	public int Margin[] = new int[20];
	public double Psales[] = new double[20];
	// Sales set
	DB database = new DB();
	
	String[] title = {"제품명","판매량","품절여부"};
	Object[][] data = new Object[100][3];
	

 	public sales_history()
    {
 		for(int i = 0; i<20; i++)
 		{
 			Event[i]=false;
 			Soldout[i]=false;
 		}
	 	Pname = database.getPname(); Pcost = database.getPcost(); Quantity = database.getQuantity();
		Whole = database.getWhole(); Edate = database.getEdate(); Event = database.getEvent();
        setSize(WIDTH-150, HEIGHT-150);
        setTitle("Sales History");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Sales History");
	    label1.setFont(new Font("Arial", Font.BOLD, 60));
	    panel1.add(label1);
	    add(panel1, BorderLayout.NORTH);
	    init();
        
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
	    JButton b1;
	    buttonpanel.setBackground(Color.gray);
	    b1 = new JButton("확인");
	    b1.setFont(new Font("돋움", Font.BOLD, 20));
	    b1.setPreferredSize(new Dimension(150, 70));
	    buttonpanel.add(b1);
	    add(buttonpanel, BorderLayout.SOUTH);
	    b1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
    }
 
 	public void setId(String _Id)
 	{
 		Id = _Id;
 	}
 	
 	public void init()
 	{
		int i;

		for(i = 0; i<20; i++)
		{
			if(Pname[i]==null)
				break;
			if(Quantity[i]==0)
				Soldout[i] = true; // Soldout
			data[i][0] = Pname[i]; data[i][1] = 100 - Quantity[i];
			if(Soldout[i])
				data[i][2] = "O";
			else
				data[i][2] = "X";
			
		}
 	}
 
 	public static void main(String[] args)
 	{
    	sales_history gui = new sales_history();
    	gui.setVisible(true);
    }
}
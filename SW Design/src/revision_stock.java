import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class revision_stock extends JFrame{

	private DefaultTableModel model1;
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	
	public String Id;
	
	public String Pname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20];
	public String Expdate[] = new String[20]; 
	// Sales set
 	DB database = new DB();
 	
	String[] title = {"제품명","수량","가격","유통기한"};
	Object[][] data = new Object[100][4];

	public revision_stock()
	{
		Pname = database.getPname(); Pcost = database.getPcost(); Quantity = database.getQuantity();
		Whole = database.getWhole(); Edate = database.getEdate(); Event = database.getEvent();
        setSize(WIDTH-150, HEIGHT-150);
        setTitle("Stock Revision");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Revision");
	    label1.setFont(new Font("Arial", Font.BOLD, 60));
	    panel1.add(label1);
	    add(panel1, BorderLayout.NORTH);
	    init();
        
	    JPanel panel2 = new JPanel();
	    panel2.setBackground(Color.red);
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
	    scroll.setPreferredSize(new Dimension(1000, 400));
	    panel2.add(scroll);
	    add(panel2, BorderLayout.CENTER);
	    
	    JPanel panel3 = new JPanel();
	    JButton b1 = new JButton("수정");
	    JButton b2 = new JButton("취소");
	    b1.setFont(new Font("돋움", Font.BOLD, 30));
	    b1.setPreferredSize(new Dimension(200, 100));
	    b2.setFont(new Font("돋움", Font.BOLD, 30));
	    b2.setPreferredSize(new Dimension(200, 100));
	    panel3.add(b1);
	    panel3.add(b2);
	    b1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		revision r = new revision();
	    		r.setId(Id);
	    		r.setVisible(true);
	    	}
	    });
	    b2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    add(panel3, BorderLayout.SOUTH);
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
			Expdate[i] = Edate[i].toString(); 
			
			data[i][0] = Pname[i]; data[i][1] = Quantity[i];
			data[i][2] = Pcost[i]; data[i][3] = Expdate[i];
			
		}
	}
	
	public static void main(String[] args)
	{
		revision_stock gui = new revision_stock();
		gui.setVisible(true);
	}
}
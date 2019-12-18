//import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class Event extends JFrame implements ActionListener {

	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	public static final int first_count = 100;
	public String Id;
	
	public String Pname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20], Discounts[] = new double[20];
	// Sales set
 	DB database = new DB();
 	
 //	public boolean c;
 	

 	public Event(String _Id)
    {
 		Id= _Id;
 		for(int i = 0; i<20; i++)
 		{
 			Event[i]=false;
 		}
 		Pname = database.getPname(Id); Pcost = database.getPcost(Id); Quantity = database.getQuantity(Id);
 		Whole = database.getWhole(Id); Edate = database.getEdate(Id); Event = database.getEvent(Id);
 		Margin = database.getMargin(Id); Psales = database.getPsales(Id); Discounts = database.getDiscount(Id);
 		
        setTitle("Event");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel3_u = new JPanel();
        JPanel flow = new JPanel();
        panel1.setLayout(new GridLayout(2,1,0,0));
        panel2.setLayout(new GridLayout(2,1,0,0));
        panel3_u.setLayout(new GridLayout(2,1));
        panel3.setLayout(new FlowLayout());
        flow.setLayout(new GridLayout(2,1,20,0));
        
        panel1.setBackground(Color.black);
        panel2.setBackground(Color.black);
        panel3.setBackground(Color.black);
        panel3_u.setBackground(Color.black);
        
        //위쪽 테이블
        JLabel PRODUCT_SALES = new JLabel("제품 판매율");
        PRODUCT_SALES.setForeground(Color.white);
        PRODUCT_SALES.setFont(new Font("굴림", Font.BOLD, 50));
        PRODUCT_SALES.setPreferredSize(new Dimension(500,-3000));
        panel1.add(PRODUCT_SALES); 
        
        String header_up[]= {"제품명","수량","제품 판매율"};
        Object cells_up[][] = new Object[100][3];
        init(cells_up);
        JTable table_up = new JTable(cells_up,header_up);
        JScrollPane js1 = new JScrollPane(table_up);
        table_up.setRowHeight(30);
        table_up.setFont(new Font("굴림",Font.PLAIN, 20));
        panel1.add(js1);
        flow.add(panel1);
       
        //아래쪽 테이블
        JLabel Discount_product = new JLabel("행사 제품");
        Discount_product.setForeground(Color.white);
        Discount_product.setFont(new Font("굴림", Font.BOLD, 50));
        Discount_product.setPreferredSize(new Dimension(500,-3000));
        panel2.add(Discount_product); 
        
        String header_down[]= {"행사 상품명","할인율","할인가"};
        Object cells_down[][] = new Object[100][3]; // = {{"egg","30%","350"}};
        down(cells_down);
        JTable table_down = new JTable(cells_down,header_down);
        JScrollPane js2 = new JScrollPane(table_down);
        table_down.setRowHeight(30);
        table_down.setFont(new Font("굴림",Font.PLAIN, 20));
        panel2.add(js2); 
        flow.add(panel2);
        add(flow,BorderLayout.CENTER);
        
        JButton standard = new JButton("행사 기준 설정");
        standard.setPreferredSize(new Dimension(300,60));
        standard.setFont(new Font("굴림", Font.BOLD, 18));
        standard.addActionListener(this);
        panel3.add(standard);
        panel3_u.add(panel3);
        add(panel3_u,BorderLayout.SOUTH);
    }
 	
 	public void setId(String _Id)
 	{
 		Id = _Id;
 	}
 	
 	public void init(Object cells[][])
 	{
 		int i;

 		for(i = 0; i<20; i++)
 		{
 			if(Pname[i]==null)
 				break;
 			
 			cells[i][0] = Pname[i]; cells[i][1] = Quantity[i];
 			cells[i][2] = Psales[i];
 		}
 	}
 	
 	public void down(Object cells[][])
 	{
 		int i;
 		Event = database.getEvent(Id);
 		Pcost = database.getPcost(Id);

 		for(i = 0; i<20; i++)
 		{
 			if(Pname[i]==null)
 				break;
 			if(Event[i]==false)
 				continue;
 			cells[i][0] = Pname[i]; cells[i][1] = ""+(int)Discounts[i]*100 +"%";
 			cells[i][2] = Pcost[i];
 		}
 	}
 	
 	public void actionPerformed(ActionEvent e)
    {
 		Event2 gui = new Event2(Id);
 		gui.setId(Id);
 		gui.setVisible(true);
    }
}
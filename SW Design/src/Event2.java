import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class Event2 extends JFrame {

	public static final int WIDTH = 1300;
	public static final int HEIGHT = 1000;
	
	public String Id;
	public int Sales;
	public double stand;
	
	public String Pname[] = new String[20], Sname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public int Scost[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20], Discounts[] = new double[20];
	// Sales set
 	DB database = new DB();

 	public Event2(String _Id)
    {
 		Id= _Id;
 		Pname = database.getPname(Id); Pcost = database.getPcost(Id); Quantity = database.getQuantity(Id);
 		Whole = database.getWhole(Id); Edate = database.getEdate(Id); Event = database.getEvent(Id);
 		Margin = database.getMargin(Id); Psales = database.getPsales(Id); Discounts = database.getDiscount(Id);
 		
        setTitle("standard registration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setLayout(new BorderLayout());
        
        JPanel panel1_u = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel3_u = new JPanel();
        JPanel flow = new JPanel();
        panel1.setLayout(new GridLayout(3,1,0,0));
        panel1_u.setLayout(new FlowLayout());
        panel2.setLayout(new GridLayout(2,1,0,0));
        panel3_u.setLayout(new GridLayout(2,1));
        panel3.setLayout(new FlowLayout());
        flow.setLayout(new GridLayout(2,1,20,0));
        String header_down[]= {"행사 상품명","할인율","할인가"};
        Object cells_down[][] = new Object[100][3]; //= {{"egg","30%","350"}};
        
        panel1_u.setBackground(Color.white);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel3_u.setBackground(Color.white);
        
        //위쪽 테이블
        JLabel STANDARD_REGISTRATION = new JLabel("제품 판매율 입력 (~ 이하 할인)");
        STANDARD_REGISTRATION.setFont(new Font("굴림", Font.BOLD, 50));
        STANDARD_REGISTRATION.setPreferredSize(new Dimension(500,-3000));
        panel1.add(STANDARD_REGISTRATION); 
        
        JTextField sr = new JTextField(500);
       // sr.setFont(new Font("굴림",Font.PLAIN, 20));
        panel1.add(sr);
        
        JButton srb = new JButton("기준 설정");
        srb.setPreferredSize(new Dimension(300,60));
        srb.setFont(new Font("굴림", Font.BOLD, 18));
        srb.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String srt = sr.getText().toString();
        		Sales = Integer.parseInt(srt);
        		stand = (double)(Sales)/100;
        		System.out.println("기준: "+stand);
        		show(stand, cells_down);
        	}
        });
        panel1_u.add(srb);
        panel1.add(panel1_u);
        
        flow.add(panel1);
       
        //아래쪽 테이블
        JLabel Discount_product = new JLabel("조건에 맞는 상품");
        Discount_product.setFont(new Font("굴림", Font.BOLD, 50));
        Discount_product.setPreferredSize(new Dimension(500,-3000));
        panel2.add(Discount_product); 
        

        JTable table_down = new JTable(cells_down,header_down);
        JScrollPane js2 = new JScrollPane(table_down);
        table_down.setRowHeight(30);
        table_down.setFont(new Font("굴림",Font.PLAIN, 20));
        panel2.add(js2);
   
        flow.add(panel2);
        
        add(flow,BorderLayout.CENTER);
        
        JButton standard = new JButton("행사 상품 등록");
        standard.setPreferredSize(new Dimension(300,60));
        standard.setFont(new Font("굴림", Font.BOLD, 18));
        standard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		database.Pevent(Pname, Pcost, Event);
        		database.Sevent(Pname, Discounts);
        		dispose();
        	}
        });
        panel3.add(standard);
        panel3_u.add(panel3);
        add(panel3_u,BorderLayout.SOUTH);
    }
 	
 	public void setId(String _Id)
 	{
 		Id = _Id;
 	}
 	
 	public int Calculate(double Sales)
 	{
 		if(Sales>0.7)
 			return 0; // no discount
 		else if(Sales>0.5 && Sales<=0.7)
 			return 1; // 10% discount
 		else if(Sales>0.3 && Sales<=0.5)
 			return 2; // 20% discount
 		else
 			return 3; // 30% discount
 	}
 	
 	public void show(double standard, Object cells[][])
 	{
 		int i;
 		int j = 0;
 		for(i = 0; i<20; i++)
 		{	
 			if(Psales[i]<=standard)
 			{
 				if(Pname[i]==null)
 					break;
 				if(Calculate(Psales[i])==1)
 				{
 					Pcost[i] = (int) (Pcost[i]*0.9);
 					Discounts[i] = 0.1;
 				}
 						
 				else if(Calculate(Psales[i])==2)
 				{
 					Pcost[i] = (int) (Pcost[i]*0.8);
 					Discounts[i] = 0.2;
 				}
 				else if(Calculate(Psales[i])==3)
 				{
 					Pcost[i] = (int) (Pcost[i]*0.7);
 					Discounts[i] = 0.3;
 				}
 				
 				cells[j][0] = Pname[i]; cells[j][1] = ""+(int)(Discounts[i]*100) +"%";
 				cells[j][2] = Pcost[i];
 				Event[i] = true;
 				System.out.println(""+Pname[i]);
 				
 				j++;
 			}
		}
	}
 		

}
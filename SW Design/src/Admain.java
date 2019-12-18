import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Admain extends JDialog implements ActionListener
{

private DefaultTableModel model1;
public static final int WIDTH = 1500;
public static final int HEIGHT = 1000;
public String Id[] = new String[20], Password[] = new String[20];
String[] title = {"아이디 ","비밀번호","삭제할 계정(체크)"};
Object data[][]= new Object[100][3]; // {{"idid","pass",""},{"idid2","pass2",""},{"idid3","pass3",""},{"idid4","pass4",""},{"idid5","pass5",""}};
JTable table;
JScrollPane scroll;
JButton b;
DB a = new DB();

public Admain() {
	Id = a.getId(); Password = a.getPW();
	setTitle("계정 관리");
	setSize(WIDTH, HEIGHT);
	init();
	//setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	setLayout(new BorderLayout());

	JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("계정 관리");
	label1.setFont(new Font("굴림", Font.BOLD, 60));
	panel1.setBackground(Color.white);
	panel1.add(label1);
	add(panel1, BorderLayout.NORTH);
	
	JPanel panel2 = new JPanel();
	panel2.setBackground(Color.white);
	model1 = new DefaultTableModel(data, title);
	
	table = new JTable(model1);
	
	table.getColumnModel().getColumn(2).setCellRenderer(new TableCell());;
	table.getColumnModel().getColumn(2).setCellEditor(new TableCell());;
	
	table.setRowHeight(32);
	table.setFont(new Font("굴림", Font.BOLD, 30));
	scroll = new JScrollPane(table);
	scroll.setPreferredSize(new Dimension(1000, 600));
	panel2.add(scroll);
	add(panel2, BorderLayout.CENTER);
	
	
	JPanel buttonpanel = new JPanel();
	buttonpanel.setBackground(Color.black);
	
	b = new JButton("계정 삭제");
	
	b.setFont(new Font("돋움", Font.BOLD, 30));
	
	b.setPreferredSize(new Dimension(200, 100));
	
	buttonpanel.add(b);
	add(buttonpanel, BorderLayout.SOUTH);
	b.addActionListener(this);
	
    
}

public void init()
{
	int i;

	for(i = 0; i<20; i++)
	{
		if(Id[i]==null)
			break;

		data[i][0] = Id[i]; data[i][1] = Password[i];
	}

}
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
		 
        JCheckBox jchk;
         
        public TableCell() {
            // TODO Auto-generated constructor stub
            jchk = new JCheckBox("삭제");
             
            jchk.addActionListener(e -> {
                System.out.println(table.getValueAt(table.getSelectedRow(), 0));
            });
         
        }
         
        @Override
        public Object getCellEditorValue() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            // TODO Auto-generated method stub
            return jchk;
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            // TODO Auto-generated method stub
            return jchk;
        }
         
    }
	
	
public static void main(String[] args){
	Admain gui = new Admain();
	gui.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	int i,j;
	String A =(String) table.getValueAt(table.getSelectedRow(), 0);
	for(i=0; i<data.length;i++)
	{
		System.out.println("i " + i +"\n"+ data[i][0] +" " +data[i][1]);
		if(data[i][0].equals(A))
		{
			
			model1.removeRow(i);
			
			data[i][0]="";
			data[i][1]="";
			
			for(j=i;j+1<data.length;j++)
			{
				data[j][0] = data[j+1][1];
				data[j][1] = data[j+1][1];
			}
			a.Rdelete((String) data[i][0]);
		
			break;
		}
	}
	
    model1.fireTableDataChanged();
	table.repaint();
	scroll.repaint();
	i=0;
}
}
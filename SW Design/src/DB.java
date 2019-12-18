import java.sql.*;
import java.text.*;

public class DB{
	public Connection conn = null;
	public PreparedStatement ptst = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	public boolean one = false;
	
	public String Id[] = new String[20], Password[] = new String[20];
	public boolean Admin[] = new boolean[20];
	// Register set
	
	public String Pname[] = new String[20];
	public int Pcost[] = new int[20], Quantity[] = new int[20], Whole[] = new int[20];
	public Date Edate[] = new Date[20];
	public boolean Event[] = new boolean[20];
	// Stock set
	
	public int Margin[] = new int[20];
	public double Psales[] = new double[20], discounts[] = new double[20];
	// Sales set
	
	public DB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to Load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: " + e);
		}
	}
	

	public void Rinsert(String _Id, String _password)
	// input Date in Register Relations for login 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean admin = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "INSERT INTO Register VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, _Id);
			pstmt.setString(2, _password);
			pstmt.setBoolean(3, admin);
			
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Pinsert(String _Pname, int _Pcost, int _Whole, String _Pdate
			, int _Quantity, String _Lid)
	// Input Data in Stock Relations
	{
		int i;
		Connection conn = null;
		PreparedStatement pstmt = null;
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date tempDate = null;
		String Exp;
		Date _Edate;
		boolean _Event = false;
		
	
		try {
			Exp = "20" + _Pdate;
			tempDate = beforeFormat.parse(Exp);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		String transDate = afterFormat.format(tempDate);
		_Edate = Date.valueOf(transDate);
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "INSERT INTO Stock VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, _Pname);
			pstmt.setInt(2, _Pcost);
			pstmt.setInt(3, _Whole);
			pstmt.setDate(4, _Edate);
			pstmt.setInt(5, _Quantity);
			pstmt.setBoolean(6, _Event);
			pstmt.setString(7, _Lid);

			
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void Pupdate(String _Pname, int _Pcost, String _Pdate, int _Quantity)
	// Update Data in Stock Relations for Edit Class
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date tempDate = null;
		String Exp = "20" + _Pdate;
		Date _Edate = null;
		
		try {
			tempDate = beforeFormat.parse(Exp);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		String transDate = afterFormat.format(tempDate);
		_Edate = Date.valueOf(transDate);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "UPDATE Stock set Cost=?, Edate=?, Quantity=?"
					+ " WHERE Name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, _Pcost);
			pstmt.setDate(2, _Edate);
			pstmt.setInt(3, _Quantity);
			pstmt.setString(4, _Pname);
			
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void Pevent(String[] _Pname, int[] _Pcost, boolean[] _Event)
	// Update Data in Stock Relations for Enrolling Events
	{
		int i;
		Connection conn = null;
		PreparedStatement pstmt = null;
	//	boolean _Event = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "UPDATE STOCK set Cost=?, Event=? WHERE Name=?";
			pstmt = conn.prepareStatement(sql);
			
			for(i=0; i<_Pname.length; i++)
			{
				pstmt.setInt(1, _Pcost[i]);
				pstmt.setBoolean(2, _Event[i]);
				pstmt.setString(3, _Pname[i]);
			}
				
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void Sinsert(String _Pname, double _Psales, int _Margin, double _Discounts)
	// Input Data in Sales Relations
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "INSERT INTO Sales VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			if(!one)
			{
				for(int i = 0; i<20; i++)
				{
					if(_Pname.equals(Pname[i]))
					{
						Supdate(_Pname, _Psales, _Margin, _Discounts);
						one=true;
						break;
					}
				}
			}
			else
			{
				pstmt.setString(1, _Pname);
				pstmt.setDouble(2, _Psales);
				pstmt.setInt(3, _Margin);
				pstmt.setDouble(4, _Discounts);
			}
			
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		one = false;
	}
	
	public void Sevent(String[] _Pname, double[] _Discount)
	// Update Data in Sales Relations for Enrolling Events
	{
		int i;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "UPDATE Sales set Discounts=? WHERE Pname=?";
			pstmt = conn.prepareStatement(sql);
			
			for(i=0; i<_Pname.length; i++)
			{
				pstmt.setDouble(1, _Discount[i]);
				pstmt.setString(2, _Pname[i]);
			}
				
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void Supdate(String _Pname, double _Psales, double _Margin, double discounts)
	// Update Data in Sales Relations for Enrolling Events
	{
		int i;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "UPDATE Sales set Psales=?, Margin=?, Discounts=? WHERE Pname=?";
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setDouble(1, _Psales);
			pstmt.setDouble(2, _Margin);
			pstmt.setDouble(3, discounts);
			pstmt.setString(4, _Pname);

				
			int count = pstmt.executeUpdate();
			if(count==0) {
				System.out.println("Failed to input data");
			}
			else {
				System.out.println("Data input success");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void Rselect()
	// Select Data in Register Relations
	{
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		int i=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM Register;";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Id[i] = rs.getString(1);
				Password[i] = rs.getString(2);
				Admin[i] = rs.getBoolean(3);
				
				i++;
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	

	public void Pselect()
	// Select Data in Stock Relations
	{
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		int i=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "SELECT * FROM Stock;";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Pname[i] = rs.getString(1);
				Pcost[i] = rs.getInt(2);
				Whole[i] = rs.getInt(3);
				Edate[i] = rs.getDate(4);
				Quantity[i] = rs.getInt(5);
				Event[i] = rs.getBoolean(6);
				
				i++;
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	

	public void Sselect()
	// Select Data in Sales Relations
	{
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		int i=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "SELECT * FROM Sales;";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				if(Pname[i].equals(rs.getString(1)))
				{
					Psales[i] = rs.getDouble(2);
					Margin[i] = rs.getInt(3);
					discounts[i] = rs.getDouble(4);
				}

				i++;
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public boolean find(String _Id, String _Password)
	// For login
	{
		int i;
		Rselect();
		
		for(i=0; i<20; i++)
		{
			if(Id[i]==null)
				break;
			if(Password[i]==null)
				break;
			// if Id or password is null stop
			if(_Id.equals(Id[i]))
				if(_Password.equals(Password[i]))
					return true;
		}
		return false;
	}
	

	public boolean ad_find(String _Id)
	// Admin confirm
	{
		boolean isAdmin;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		int i=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url, "root", "Rlatjdrhs20");
			System.out.println("Connection Success");
			
			String sql = "SELECT Admin FROM Register where Id = "+ "'"+_Id +"'" +";";
			System.out.println(sql);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				isAdmin=rs.getBoolean(1);
				if(isAdmin==true)
					return true; // this id is admin
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
		finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false; // that id is not admin
	}
	
	public String[] getPname()
	{
		Pselect();
		return Pname;
	}
	
	public int[] getPcost()
	{
		Pselect();
		return Pcost;
	}
	
	public int[] getQuantity()
	{
		Pselect();
		return Quantity;
	}
	
	public int[] getWhole()
	{
		Pselect();
		return Whole;
	}
	
	public Date[] getEdate()
	{
		Pselect();
		return Edate;
	}
	
	public boolean[] getEvent()
	{
		Pselect();
		return Event;
	}
	
	public double[] getPsales()
	{
		Sselect();
		return Psales;
	}
	public int[] getMargin()
	{
		Sselect();
		return Margin;
	}
	
	public double[] getDiscount()
	{
		Sselect();
		return discounts;
	}
	public int getSaleAll()
	{
		int[] Sales = new int[20];
		int All = 0; // Chong Panme
		int i;
		Pselect();
		
		for(i=0; i<20; i++)
		{
			if(Pname[i]==null)
				return All;
			Sales[i] = Quantity[i]*Pcost[i];
			All += Sales[i];
		}
		return All;
	}
	
	public int getNetincome()
	{
		int[] Sales = new int[20];
		int Netincome = 0;
		int i;
		
		for(i=0; i<20; i++)
		{
			if(Pname[i]==null)
				return Netincome;
			Sales[i] = Quantity[i] * (Pcost[i] - Whole[i]);
			Netincome += Sales[i];
		}
		
		return Netincome;
	}
	
	public static void main(String[] args) {

	}





}

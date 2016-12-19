package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbOperation {
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public DbOperation() {
		JDBCPostgresql psql = new JDBCPostgresql();
		this.con = psql.getconnect();
	}

	public List<Dbcontent> getdbcontent(String caller,String dest,String date){
		List<Dbcontent> contents = new ArrayList<Dbcontent>();
		try {
			Statement sql = this.con.createStatement();
			String sqlcmd = this.getsqlcmd(caller, dest, date);
			System.out.println(sqlcmd);
			ResultSet res = sql.executeQuery(sqlcmd);
			int i=0;
			while(res.next())
			{
				Dbcontent content = new Dbcontent(res.getString("caller_id_name"),
						res.getString("destination_number"),
						res.getString("start_stamp"),
						res.getString("answer_stamp"),
						res.getString("end_stamp"),
						Integer.parseInt(res.getString("duration")),
						Integer.parseInt(res.getString("billsec")),
						res.getString("hangup_cause"));
				//System.out.println("caller:"+res.getString("caller_id_name")+",billsec:"+res.getString("billsec"));
				i++;
				contents.add(content);
			}
			System.out.println(i);
			res.close();
			sql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sorry,there are some problems when connect to database!");
		}
		
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contents;
	}
	
	public List<Username> getalluser(String usertype){
		List<Username> allusers = new ArrayList<Username>();
		ResultSet res = null;
		try {
			Statement sql = this.con.createStatement();
			if(usertype.equals("caller_id_name")){
				res = sql.executeQuery("select distinct fsw_cdr.caller_id_name from fsw_cdr ");
				while(res.next()){
					Username caller = new Username(res.getString("caller_id_name"));
					allusers.add(caller);
				}
			}
				
			else{
				res = sql.executeQuery("select distinct fsw_cdr.destination_number from fsw_cdr WHERE fsw_cdr.destination_number ~* '^([a-zA-Z][0-9]{2})'");
				while(res.next()){
					Username dest = new Username(res.getString("destination_number"));
					allusers.add(dest);
				}
			}
			res.close();
			sql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allusers;
	}
	
	protected String getsqlcmd(String caller,String dest,String date){
		String sqlcmd = null;
		String sqlcaller = "where fsw_cdr.caller_id_name='"+caller+"' ";
		String sqldest = "where fsw_cdr.destination_number='"+dest+"' ";
		String sqldate = "where fsw_cdr.start_stamp='"+date+"' ";
		String and1 = "and ";
		String and2 = "and ";
		if(caller==null||caller==""){
			and1="";
			sqlcaller="";
		}
		if(dest==null||dest==""){
			sqldest="";
		}
		if(date==null||date==""){
			and2="";
			sqldate="";
		}
		sqlcmd = "select * from fsw_cdr "+sqlcaller+and1+sqldest+and2+sqldate+"order by fsw_cdr.caller_id_name,fsw_cdr.destination_number,fsw_cdr.start_stamp desc";
		return sqlcmd;
	}
	
}

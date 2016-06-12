
package club.jmint.mifty.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import club.jmint.mifty.dao.Dao;
import club.jmint.mifty.log.MyLog;

public class Sql {
	private static final Sql sql = new Sql();
	public static Sql getInstance() {
		return sql;
	}
	
	public static String getSqlResultFieldAsString(Object obj){
		if (obj==null){
			return "";
		}
		else {
			return obj.toString();
		}
	}
	
	public static String formatField(String field, boolean dotted){
		String ff = null;
		if (field==null) {
			ff = "NULL";
		}
		else{
			ff = "'" + field + "'";
		}
		//last one
		if (dotted){
			ff = ff + ",";
		}
		
		return ff;
	}
	
	//insert
	public int doInsert(String sql){
		int returnObj = -1;
		try{
			Session session = Dao.getSession();
			session.beginTransaction();
			SQLQuery sq = session.createSQLQuery(sql);
			returnObj = sq.executeUpdate();
			session.getTransaction().commit();
			
		}catch(Exception e){
			MyLog.logger.error("doInsert error.");
			MyLog.printStackTrace(e);
		}
		return returnObj;
	}
	
	//delete
	public int doDelete(String sql){
		int returnObj = -1;
		try{
			Session session = Dao.getSession();
			session.beginTransaction();
			SQLQuery sq = session.createSQLQuery(sql);
			returnObj = sq.executeUpdate();
			session.getTransaction().commit();
			
		}catch(Exception e){
			MyLog.logger.error("doDelete error.");
			MyLog.printStackTrace(e);
		}
		return returnObj;		
	}
	
	//update
	public int doUpdate(String sql){
		int returnObj = -1;
		try{
			Session session = Dao.getSession();
			session.beginTransaction();
			SQLQuery sq = session.createSQLQuery(sql);
			returnObj = sq.executeUpdate();
			session.getTransaction().commit();
			
		}catch(Exception e){
			MyLog.logger.error("doUpdate error.");
			MyLog.printStackTrace(e);
		}
		return returnObj;		
	}
	
	//select
	public List doSelect(String sql){
		List returnObj = null;
		try{
			Session session = Dao.getSession();
			session.beginTransaction();
			SQLQuery sq = session.createSQLQuery(sql);
			returnObj = sq.list();
			session.getTransaction().commit();
			
		}catch(Exception e){
			MyLog.logger.error("doSelect error.");
			MyLog.printStackTrace(e);
		}
		return returnObj;		
	}


	//Versatile CRUD 
	//Create
	public int sqlCreate(String table, Map<String, String> fieldmap) {	
		
		Iterator<Entry<String,String>> it = fieldmap.entrySet().iterator();
		Entry<String,String> en;
		String fields="", values="";
		while(it.hasNext()){
			en = it.next();
			fields = fields + en.getKey() + ",";
			values = values + formatField(en.getValue(), true);
		}
		fields = fields + "create_time";
		values = values + "now()";
		String insertsql = "insert into "+table+" ("+fields+") values (" + values + ")";
		
		return doInsert(insertsql);
	}
	
	//Delete
	public int sqlDelete(String table, String condition) {
		
		String sql = "delete from "+table+" where " + condition;
		
		return doDelete(sql);
	}
	
	//Update
	public int sqlUpdate(String table, Map<String, String> fieldmap, String condition) {
		
		StringBuffer updatesql = new StringBuffer();
		updatesql.append("update " + table + " set ");
		
		Iterator<Entry<String,String>> it = fieldmap.entrySet().iterator();
		Entry<String,String> en;
		while(it.hasNext()){
			//there maybe errors for different database fields' type, let's see later or some fixes.
			en = it.next();
			updatesql.append(en.getKey() + "=" + formatField(en.getValue(),true)); 
		}
		updatesql.append("update_time=now()");
		updatesql.append(" where " + condition);
		MyLog.logger.debug("SQL: " + updatesql.toString());
		
		return doUpdate(updatesql.toString());
	}

	//Select
	public List<Object[]> sqlSelect(String table, String fields, String condition) {
		
		String sql = "select " + fields + " from " + table + " where " + condition;
		
		return doSelect(sql);
	}	
}


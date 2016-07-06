
package club.jmint.mifty.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import club.jmint.mifty.dao.Dao;
import club.jmint.mifty.utils.CrossLog;

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
			CrossLog.logger.error("doInsert error.");
			CrossLog.printStackTrace(e);
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
			CrossLog.logger.error("doDelete error.");
			CrossLog.printStackTrace(e);
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
			CrossLog.logger.error("doUpdate error.");
			CrossLog.printStackTrace(e);
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
			CrossLog.logger.error("doSelect error.");
			CrossLog.printStackTrace(e);
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
	
	/**
	 * 
	 * @param table	
	 * @param map	fields/value1/value2/value3...
	 * 				fields format: "f1,f2,f3,f4"
	 * 				values format: "v1,v2,v3,v4"
	 * @return effected rows
	 */
	//Multi-line Create
	public int sqlMultiCreate(String table, String fields, String[] values) {	
		String fs = fields + "create_time";
		String vs;
		String vslist = null;
		
		vs = values[0] + ",now()";
		vslist += "(" + vs + ")";
		for (int i=1;i<values.length;i++){
			vs = values[i] + ",now()";
			vslist += ",(" + vs + ")";
		}

		String insertsql = "insert into "+table+" ("+fs+") values " + vslist;
		
		return doInsert(insertsql);
	}
	
	//Delete
	public int sqlDelete(String table, String condition) {
		
		String sql = "delete from "+table;
		if (condition!=null && !condition.isEmpty()){
			sql += " where " + condition;
		}
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
		if (condition!=null && !condition.isEmpty()){
			updatesql.append(" where " + condition);
		}
		CrossLog.logger.debug("SQL: " + updatesql.toString());
		
		return doUpdate(updatesql.toString());
	}

	//Select
	public List<Object[]> sqlSelect(String table, String fields, String condition) {
		
		String sql;
		
		if (condition==null || condition.isEmpty()){
			sql = "select " + fields + " from " + table;
		}
		else {
			sql = "select " + fields + " from " + table + " where " + condition;
		}
		
		return doSelect(sql);
	}
	
	//Get last (auto increment)id
	public int sqlLastInsertId(){
		String sql = "select last_insert_id();";
		List<Object[]> list = doSelect(sql);
		if (!list.isEmpty()){
			String id = list.get(0)[0].toString();
			return Integer.parseInt(id);
		} else {
			return 0;
		}
	}
}


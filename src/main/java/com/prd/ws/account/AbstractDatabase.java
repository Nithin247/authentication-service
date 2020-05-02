package com.prd.ws.account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;

import com.prd.ws.db.Dao;
import com.prd.ws.db.HibernateDao;

public abstract class AbstractDatabase {
	
	public abstract SessionFactory getSessionFactory();
	
	public boolean isReady(){
		boolean ready = getSessionFactory() != null;
		if(ready) ready = getSessionFactory().getCurrentSession() != null;
		if(ready) ready = getSessionFactory().getCurrentSession().isConnected();
		return ready;
	}
	
	public Date getTimeStamp(){
		return getSessionFactory().getCurrentSession().doReturningWork(new ReturningWork<Date>() {
			@Override
			public Date execute(Connection conn) throws SQLException {
				Statement stmt = conn.createStatement( );
				ResultSet rs =  stmt.executeQuery("select 1 as result, sysdate from dual");
				Date date = null;
				if(rs.next()) date = rs.getTimestamp("sysdate");
				rs.close();
				stmt.close();
				return date;
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	private Map daos = new HashMap();
	
	@SuppressWarnings("unchecked")
	public <T> Dao<T, Long> getDao(Class<T> type){
		if(!daos.containsKey(type)){
			HibernateDao<T, Long> hibernateDao = new HibernateDao<T, Long>(type);
			hibernateDao.setSessionFactory(getSessionFactory());
			daos.put(type, hibernateDao);
		}
		
		return (Dao<T, Long>) daos.get(type);
	}
}

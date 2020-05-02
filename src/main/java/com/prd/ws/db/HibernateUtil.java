package com.prd.ws.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("hibernateUtil")
public class HibernateUtil {
	
	@Autowired
	private SessionFactory commonSessionFactory;
	
	@Transactional(value = "common", propagation=Propagation.REQUIRED)
	public boolean isCommonReady(){
		boolean ready = commonSessionFactory != null;
		if(ready) ready = commonSessionFactory.getCurrentSession() != null;
		if(ready) ready = commonSessionFactory.getCurrentSession().isConnected();
		return ready;
	}
	
	@Transactional(value = "common", propagation=Propagation.REQUIRED)
	public Date getCommonTimeStamp(){
		return commonSessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Date>() {
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
	public <T> Dao<T, Long> getDao1(Class<T> type) {
		if(!daos.containsKey(type)){
			HibernateDao<T, Long> hibernateDao = new HibernateDao<T, Long>(type);
			hibernateDao.setSessionFactory(commonSessionFactory);
			daos.put(type, hibernateDao);
		}
		
		return (Dao<T, Long>) daos.get(type);
	}
	
	public <T> Dao<T, Long> getDao(Class<T> type, SessionFactory sessionFactory) {
		HibernateDao<T, Long> hibernateDao = new HibernateDao<T, Long>(type);
		hibernateDao.setSessionFactory(sessionFactory);
		return hibernateDao ;
	}
	
}

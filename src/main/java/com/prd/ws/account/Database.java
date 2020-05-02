package com.prd.ws.account;

import java.util.Date;

import org.hibernate.SessionFactory;

import com.prd.ws.db.Dao;

public interface Database {
	
	
	public boolean isReady();
	
	public Date getTimeStamp();
	
	public SessionFactory getSessionFactory();
	
	public <T> Dao<T, Long> getDao(Class<T> type);
	
}

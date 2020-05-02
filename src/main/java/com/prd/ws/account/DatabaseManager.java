package com.prd.ws.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("databaseManager")
public class DatabaseManager {
	
	@Autowired @Qualifier("commonDatabase")
	private Database commonDatabase;
	
	public Database getDatabase(String account){
		return commonDatabase;
	}
}

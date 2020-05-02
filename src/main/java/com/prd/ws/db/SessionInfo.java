package com.prd.ws.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionInfo {
    private Session session;
    private boolean needsTransaction;
    private boolean closeSession;
    private Transaction tx;
    
    public SessionInfo(Session session, boolean needsClosing) {
        this(session, needsClosing, needsClosing);
    }
    
    public SessionInfo(Session session, boolean transact, boolean closeSession) {
        this.session = session;
        this.needsTransaction = transact;
        this.closeSession = closeSession;
        if (this.needsTransaction) { this.tx = this.session.beginTransaction(); }
    }
    public Session getSession() {
        return this.session;
    }
    
    public Session getSessionForWriting() {
        if (this.session.getTransaction() == null) {
            this.tx = this.session.beginTransaction();
            this.needsTransaction = true;
        }
        return this.session;
    }
  

    
    public void cleanup() {
        if (this.needsTransaction) {
            try {this.tx.commit(); } catch (HibernateException he) {  }
        }
        if (this.closeSession) {
            try {this.session.close(); } catch (HibernateException he) { }
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        cleanup();
        super.finalize();
    }
    
}

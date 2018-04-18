package com.ofs.spring.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionAnnotationFactory;
	private Session currentSession;
	private Transaction currentTransaction;

	private static SessionFactory buildSessionAnnotationFactory() {

		try {

			SessionFactory sessionFactory = new AnnotationConfiguration().addResource("hibernate.cfg.xml").configure()
					.buildSessionFactory();

			return sessionFactory;
		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}

	}

	public static SessionFactory getSessionAnnotationFactory() {
		if (sessionAnnotationFactory == null)
			sessionAnnotationFactory = buildSessionAnnotationFactory();
		return sessionAnnotationFactory;
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		closeCurrentSession();
	}

	private static SessionFactory getSessionFactory() {

		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
}

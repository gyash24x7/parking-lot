package dev.yashgupta.parkit.entity;

import dev.yashgupta.parkit.config.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseEntity< T > {

	private final Class< T > genericType;

	public BaseEntity( Class< T > genericType ) {
		this.genericType = genericType;
	}

	public void save() {
		Transaction tx = null;
		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			session.save( this );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}
	}

	public void delete() {
		Transaction tx = null;
		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			session.delete( this );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}
	}

	public T getById( int id ) {
		Transaction tx = null;
		T t = null;

		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			t = session.get( genericType, id );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}

		return t;
	}
}

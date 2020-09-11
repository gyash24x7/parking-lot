package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.config.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseRepository< T > {

	private final Class< T > genericType;

	public BaseRepository( Class< T > genericType ) {
		this.genericType = genericType;
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

	public T save( T t ) {
		Transaction tx = null;
		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			session.save( t );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}

		return t;
	}

	public void delete( T t ) {
		Transaction tx = null;
		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			session.delete( t );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}
	}
}

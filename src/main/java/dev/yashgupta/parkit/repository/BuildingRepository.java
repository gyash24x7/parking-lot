package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.config.HibernateConfig;
import dev.yashgupta.parkit.entity.Building;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;

public class BuildingRepository {

	public Building getBuildingById(int id) {
		Transaction tx = null;
		Building building = null;

		try(Session session = HibernateConfig.getSessionFactory().openSession() ){
			tx = session.beginTransaction();
			building = session.get( Building.class, id );
			tx.commit();
		} catch ( HibernateException e ) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}

		return building;
	}

	public Building createBuilding(Building building) {
		Transaction tx = null;
		Building createdBuilding = null;

		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			tx = session.beginTransaction();
			int buildingId =(Integer ) session.save( building );
			createdBuilding = session.get( Building.class, buildingId );
			tx.commit();
		} catch ( HibernateException e ) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}

		return createdBuilding;
	}

	public void deleteBuilding(Building building) {
		Transaction tx = null;
		try ( Session session = HibernateConfig.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			session.delete( building );
			tx.commit();
		} catch ( HibernateException e ) {
			if ( tx != null ) tx.rollback();
			e.printStackTrace();
		}
	}
}

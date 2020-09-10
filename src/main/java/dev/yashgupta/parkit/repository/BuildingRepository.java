package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.entity.Building;

import javax.persistence.EntityManager;

public class BuildingRepository {

	private EntityManager entityManager;

	public BuildingRepository( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}

	public Building getBuildingById(int id) {
		return entityManager.find( Building.class, id );
	}

	public Building createBuilding(Building building) {
		if(building.getId()==0) {
			entityManager.persist( building );
		} else {
			building = entityManager.merge( building );
		}
		return building;
	}

	public void deleteBuilding(Building building) {
		if(entityManager.contains( building )) {
			entityManager.remove( building );
		} else {
			entityManager.merge( building );
		}
	}
}

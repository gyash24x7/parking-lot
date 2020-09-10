package dev.yashgupta.parkit.main;

import dev.yashgupta.parkit.entity.Building;
import dev.yashgupta.parkit.repository.BuildingRepository;

public class Main {

	public static void main( String[] args ) {
		BuildingRepository buildingRepository = new BuildingRepository();
		System.out.println(buildingRepository.createBuilding( new Building("New Kanpur Parking Lot") ));
	}
}

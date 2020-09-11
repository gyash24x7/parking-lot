package dev.yashgupta.parkit.main;

import dev.yashgupta.parkit.Exception.InvalidInputException;
import dev.yashgupta.parkit.entity.Building;
import dev.yashgupta.parkit.entity.Floor;
import dev.yashgupta.parkit.entity.Space;
import dev.yashgupta.parkit.repository.BuildingRepository;
import dev.yashgupta.parkit.repository.FloorRepository;
import dev.yashgupta.parkit.repository.SpaceRepository;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

	static ResourceBundle rb = ResourceBundle.getBundle( "config" );
	static Scanner sc = new Scanner( System.in );

	public static void main( String[] args ) {
		boolean isCreateMode = Boolean.parseBoolean( rb.getString( "createMode" ) );
		BuildingRepository buildingRepository = new BuildingRepository();
		FloorRepository floorRepository = new FloorRepository();
		SpaceRepository spaceRepository = new SpaceRepository();

		if ( isCreateMode ) {
			int floorCount = Integer.parseInt( rb.getString( "floorCount" ) );
			int premiumSpaces = Integer.parseInt( rb.getString( "premiumSpaces" ) );
			int nonPremiumSpaces = Integer.parseInt( rb.getString( "nonPremiumSpaces" ) );
			String name = rb.getString( "name" );

			Building building = new Building( name );
			buildingRepository.save( building );

			IntStream.range( 0, floorCount ).forEach( i -> {
				System.out.println( i );
				Floor floor = new Floor( i + 1 );
				floor.setBuilding( building );
				floorRepository.save( floor );

				IntStream.range( 0, premiumSpaces + nonPremiumSpaces ).forEach( j -> {
					Space space = new Space( j + 1, j < premiumSpaces );
					space.setFloor( floor );
					spaceRepository.save( space );
				} );
			} );

			Floor floor = floorRepository.getById( 1 );
			System.out.println( floor );

			System.out.println( floor.getBuilding() );

		} else {
			int buildingId = Integer.parseInt( rb.getString( "buildingId" ) );
			Building building = buildingRepository.getById( buildingId );

			System.out.println( "\n------  Welcome to " + ( building.getName() ) + "  ------" );

			while ( true ) {
				int choice;
				while ( true ) {
					System.out.println( "\nChoose from following : " );
					System.out.println( "1) Park your vehicle" );
					System.out.println( "2) Move your parked vehicle" );
					System.out.println( "3) Exit the Parking Lot" );
					System.out.print( "Enter your choice: " );

					try {
						choice = sc.nextInt();
						if ( choice != 1 && choice != 2 && choice != 3 ) {
							throw new InvalidInputException();
						}
						break;
					} catch ( InputMismatchException | InvalidInputException e ) {
						System.out.println( "Invalid Input!" );
						System.out.println( "Please try again!\n" );
						sc.nextLine();
					}
				}

				if ( choice == 1 ) {
					boolean isParkSuccessful = buildingRepository.parkVehicleInBuilding( building );
					if ( isParkSuccessful ) System.out.println( "Park Successful!" );
				}

				if ( choice == 3 ) {
					buildingRepository.exitBuilding( building );
					break;
				}
			}
		}
	}
}

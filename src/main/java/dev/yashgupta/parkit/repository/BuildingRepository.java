package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.Exception.InvalidInputException;
import dev.yashgupta.parkit.entity.Building;
import dev.yashgupta.parkit.entity.Floor;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BuildingRepository extends BaseRepository< Building > {
	static Scanner sc = new Scanner( System.in );

	public BuildingRepository() {
		super( Building.class );
	}

	public void printBuildingAvailability( Building building ) {
		FloorRepository floorRepository = new FloorRepository();
		System.out.println( "\n------         Available Spaces         ------\n" );

		for ( Floor floor : building.getFloors() ) {
			System.out.println( "Floor " + ( floor.getFloorNumber() ) + ": " +
					( floorRepository.getAvailablePremiumSpaces( floor ).size() ) + " Premium    " +
					( floorRepository.getAvailableNonPremiumSpaces( floor ).size() ) + " Non-Premium\n" );
		}
	}

	public Floor selectFloorFromBuilding( Building building ) {
		int floorNumber;
		List< Floor > floors = building.getFloors();

		while ( true ) {
			try {
				System.out.print( "\nEnter floor number: " );
				floorNumber = sc.nextInt();
				if ( floorNumber <= 0 || floorNumber > floors.size() ) {
					throw new InvalidInputException();
				}
				break;
			} catch ( InputMismatchException | InvalidInputException e ) {
				System.out.println( "Invalid Input!" );
				System.out.println( "Please try again!\n" );
				sc.nextLine();
			}
		}

		return floors.get( floorNumber - 1 );
	}

	public boolean parkVehicleInBuilding( Building building ) {
		Floor floor;
		int choice;
		boolean isEntrySuccessful = false;
		FloorRepository floorRepository = new FloorRepository();

		while ( true ) {
			while ( true ) {
				printBuildingAvailability( building );
				System.out.println( "Choose from following: " );
				System.out.println( "1) Choose Floor to Park" );
				System.out.println( "2) Exit" );
				System.out.print( "Enter your choice: " );

				try {
					choice = sc.nextInt();
					if ( choice != 2 && choice != 1 ) {
						throw new InvalidInputException();
					}
					break;
				} catch ( InvalidInputException | InputMismatchException e ) {
					System.out.println( "Invalid Input!" );
					System.out.println( "Please try again!\n" );
					sc.nextLine();
				}
			}

			if ( choice == 1 ) {
				floor = selectFloorFromBuilding( building );
				isEntrySuccessful = floorRepository.enter( floor );
				if ( isEntrySuccessful ) break;
			}

			if ( choice == 2 ) break;
		}

		return isEntrySuccessful;
	}

	public boolean unparkVehicleFromBuilding() {
		System.out.println( "Enter Ticket ID: " );
		String ticketId = sc.nextLine();
		// find ticket with ticketId and occupied;
		// calculate bill for the space based on current time and generated time diff
		return false;
	}

	public void exitBuilding( Building building ) {
		System.out.println( "Thanks for using our Parking lot!" );
		System.out.println( "See you soon!" );
		System.out.println( building.getName() );
	}

}

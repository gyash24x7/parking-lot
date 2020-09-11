package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.Exception.InvalidInputException;
import dev.yashgupta.parkit.entity.Floor;
import dev.yashgupta.parkit.entity.Space;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FloorRepository extends BaseRepository< Floor > {
	static Scanner sc = new Scanner( System.in );

	public FloorRepository() {
		super( Floor.class );
	}

	public void printAvailability( Floor floor ) {
		System.out.println( "\n------             Floor " + ( floor.getFloorNumber() ) + "              ------\n" );
		System.out.println( "Available Spaces on Floor " + ( floor.getFloorNumber() ) + ": " +
				( getAvailablePremiumSpaces( floor ).size() ) + " Premium\t" +
				( getAvailableNonPremiumSpaces( floor ).size() ) + " Non-Premium" );
	}

	public boolean isPremiumSpaceAvailable( Floor floor ) {
		int availablePremiumSpacesCount = getAvailablePremiumSpaces( floor ).size();
		return availablePremiumSpacesCount > 0;
	}

	public List< Space > getAvailablePremiumSpaces( Floor floor ) {
		return getAllPremiumSpaces( floor ).stream().filter( Space::isVacant ).collect( Collectors.toList() );
	}

	public List< Space > getAvailableNonPremiumSpaces( Floor floor ) {
		return getAllNonPremiumSpaces( floor ).stream().filter( Space::isVacant ).collect( Collectors.toList() );
	}

	public List< Space > getAllPremiumSpaces( Floor floor ) {
		return floor.getSpaces().stream().filter( Space::isPremium ).collect( Collectors.toList() );
	}

	public List< Space > getAllNonPremiumSpaces( Floor floor ) {
		return floor.getSpaces().stream().filter( Space::isNotPremium ).collect( Collectors.toList() );
	}

	public boolean isEntrySuccessful( Floor floor ) {
		int choice;
		SpaceRepository spaceRepository = new SpaceRepository();

		while ( true ) {
			System.out.println( "Choose from following: " );
			System.out.println( "1) Park your vehicle on this floor ( Floor " + floor.getFloorNumber() + " )" );
			System.out.println( "2) Exit Floor " + floor.getFloorNumber() );
			System.out.print( "Enter your choice: " );

			try {
				choice = sc.nextInt();
				if ( choice != 1 && choice != 2 ) {
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
			Space space;
			int premiumChoice = askForPremiumParking( floor );

			if ( premiumChoice != 3 ) {
				space = selectSpace( floor, premiumChoice == 1 );
				spaceRepository.occupySpace( space );
				return true;
			}
		}

		return false;
	}

	public int askForPremiumParking( Floor floor ) {
		if ( isPremiumSpaceAvailable( floor ) ) {
			while ( true ) {
				System.out.println( "\nThere are premium spaces available on this floor." );
				System.out.println( "Do you want to use a premium space?" );
				System.out.print( "Enter 1 for yes and 2 for no. Ans: " );

				try {
					int input = sc.nextInt();
					if ( input != 1 && input != 2 ) {
						throw new InvalidInputException();
					}
					if ( input == 1 ) {
						return 1;
					} else {
						return 2;
					}
				} catch ( InputMismatchException | InvalidInputException e ) {
					System.out.println( "Invalid Input!" );
					System.out.println( "Please try again!\n" );
					sc.nextLine();
				}
			}
		} else {
			while ( true ) {
				System.out.println( "\nThere are no premium spaces available on this floor." );
				System.out.println( "Choose from following: " );
				System.out.println( "1) Go with a non-premium space on this floor" );
				System.out.println( "2) Look for a premium space on other floors" );
				System.out.print( "Enter your choice: " );

				try {
					int input = sc.nextInt();
					if ( input != 1 && input != 2 ) {
						throw new InvalidInputException();
					}
					if ( input == 1 ) {
						return 2;
					}
					break;
				} catch ( InputMismatchException | InvalidInputException e ) {
					System.out.println( "Invalid Input!" );
					System.out.println( "Please try again!\n" );
					sc.nextLine();
				}
			}
		}

		return 3;
	}

	public Space selectSpace( Floor floor, boolean requirePremium ) {
		int spaceNumber;
		List< Space > spaces = requirePremium ? getAvailablePremiumSpaces( floor ) : getAvailableNonPremiumSpaces( floor );
		List< Integer > spaceNumbers = spaces.stream().map( Space::getSpaceNumber ).collect( Collectors.toList() );

		while ( true ) {
			System.out.println( "\nAvailable Spaces: " );
			for ( Space space : spaces ) {
				System.out.println( "Space " + space.getSpaceNumber() + ": \t" +
						( space.isOccupied() ? "Occupied" : "Unoccupied" ) );
			}

			System.out.print( "\nEnter Space Number: " );

			try {
				spaceNumber = sc.nextInt();
				if ( !spaceNumbers.contains( spaceNumber ) ) {
					throw new InvalidInputException();
				}
				break;
			} catch ( InputMismatchException e ) {
				System.out.println( "Invalid Input!" );
				System.out.println( "Please try again!\n" );
				sc.nextLine();
			} catch ( InvalidInputException e ) {
				System.out.println( "This Parking Space is not available." );
				System.out.println( "Please try again!\n" );
				sc.nextLine();
			}
		}

		int finalSpaceNumber = spaceNumber;
		return spaces.stream().filter( space -> space.getSpaceNumber() == finalSpaceNumber ).findFirst().orElse( null );
	}

	public boolean enter( Floor floor ) {
		printAvailability( floor );

		if ( floor.getSpaces().stream().noneMatch( Space::isVacant ) ) {
			System.out.println( "There are no Parking Spaces available on this floor!" );
			System.out.println( "Choose another floor!" );
			return false;
		}

		return isEntrySuccessful( floor );
	}

	public void exit( Floor floor ) {
		System.out.println( "Exiting Floor " + floor.getFloorNumber() + "!" );
	}

}

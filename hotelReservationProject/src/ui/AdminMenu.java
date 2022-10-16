package ui;

import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Scanner;

public class AdminMenu {
    public static void displayAdminMenu(Scanner scanner){
        showAdminMenu();
        int userinput= -1;
        while (userinput<5){
            try{
                userinput= scanner.nextInt();

                switch (userinput) {
                    case 1 -> AdminMenu.seeAllCustomers(scanner);
                    case 2 -> AdminMenu.seeAllRooms(scanner);
                    case 3 -> AdminMenu.seeAllReservations();
                    case 4 -> AdminMenu. addARoom(scanner);
                    case 5 -> AdminMenu.backToMainManu(scanner);
                    default -> System.out.println("enter a number ");
                }

            }catch (NumberFormatException numberFormatException){
                System.out.println("Please enter a number between 1 and 5:");
            }
        }
    }

    private static void backToMainManu(Scanner scanner) {
        MainMenu.displayMainMenu(scanner);
    }

    private static void addARoom(Scanner scanner) {
        boolean roomNumberAccepted= false;
        String roomNumber="";

        while (!roomNumberAccepted){
            try {
                roomNumber = scanner.nextLine();
                if(Integer.parseInt(roomNumber)>=0|| Integer.parseInt(roomNumber)<10000){
                    roomNumberAccepted=true;
                }
            }catch (Exception exception){
                System.out.println("please enter a room number..");
            }
        }


        System.out.println("Enter room price");
        Double roomPrice= 0.0;
        boolean priceAccepted= false;
        while (!priceAccepted){
            try{
                roomPrice= Double.parseDouble(scanner.nextLine());
                priceAccepted=true;
            }catch (NullPointerException nullPointerException){
                System.out.println("enter a price");
            }catch (NumberFormatException numberFormatException){
                System.out.println("enter a valid number for the price..");
            }
        }
        System.out.println("Enter room type choose 1 for 1 bed or 2 for double bed..");
        boolean roomTypeAccepted= false;
        int roomType= 0;
        while (!roomTypeAccepted){
            try {
                roomType = Integer.parseInt(scanner.nextLine());
                if(roomType==1 || roomType==2){
                    roomTypeAccepted=true;
                }else
                    System.out.println("please enter only 1 or 2");
            }catch (NumberFormatException numberFormatException){
                System.out.println("enter valid number only 1 or 2 accepted");
            }

        }
        RoomType roomTypes= RoomType.SINGLE;
        if(roomType==2){
            roomTypes=RoomType.DOUBLE;
        }
        Room room = new Room(roomNumber,roomPrice,roomTypes);
        ReservationService.getInstance().addRoom(room);
        displayAdminMenu(scanner);

    }

    private static void seeAllReservations() {
        ReservationService.getInstance().printAllReservation();

    }

    private static void seeAllRooms(Scanner scanner) {
        ReservationService.getInstance().getAllRooms();
        System.out.println("this is all the rooms in the Hotel");
        displayAdminMenu(scanner);


    }

    private static void seeAllCustomers(Scanner scanner) {
        System.out.println(CustomerService.getInstance().getAllCustomers());
        displayAdminMenu(scanner);


    }


    public static void showAdminMenu() {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms ");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a Room ");
        System.out.println("5. Back to Main Menu");
    }

}


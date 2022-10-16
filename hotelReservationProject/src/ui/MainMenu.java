package ui;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private static Date checkInDate;
    private static Date checkOutDate;
    private static Date checkInDate2;
    private static Date checkOutDate2;


    public static void displayMainMenu(Scanner scanner) {
        showMainMenu();
        int userSelection = -1;
        while (userSelection > 0 || userSelection < 6) {
            try {
                userSelection = Integer.parseInt(scanner.nextLine());
                switch (userSelection) {
                    case 1:
                        MainMenu.findAndReserveARoom(scanner);

                        break;
                    case 2:
                        MainMenu.seeReservation(scanner);

                        break;
                    case 3:
                        MainMenu.createAnAccount(scanner);

                        break;
                    case 4:
                        MainMenu.showAdminMenue(scanner);

                        break;
                    case 5:

                        break;

                    default:
                        System.out.println("Enter a number form 1-5");

                }
            } catch (NumberFormatException | ParseException numberFormatException) {
                System.out.println("please enter a number between 1 and 5");
            }

        }
    }

    private static void showAdminMenue(Scanner scanner) {
        AdminMenu.displayAdminMenu(scanner);
    }

    private static void seeReservation(Scanner scanner) {
        System.out.println("please enter customer email..");
        String custEmail= scanner.nextLine();
        Customer customer= CustomerService.getInstance().getCustomer(custEmail);
        if (customer == null){
            System.out.println("No reservation found");
        }else {
            Collection<Reservation> customerReservation = ReservationService.getInstance().getCoustomerReservation(customer);
            for(Reservation res : customerReservation){
                System.out.println(res);
            }
        }

    }

    private static void createAnAccount(Scanner scanner) {
        System.out.println("please enter your first name..");
        String newCustomerfirstName= scanner.nextLine();
        System.out.println("enter your last name..");
        String newCustomerLastName= scanner.nextLine();
        System.out.println("please enter your email..");
        String newCustoemrEmail= scanner.nextLine();
        CustomerService.getInstance().addCustomer(newCustoemrEmail,newCustomerfirstName,newCustomerLastName);
        System.out.println("Account created successfully..");
        displayMainMenu(scanner);


    }

    private static void findAndReserveARoom(Scanner scanner) throws ParseException {

        System.out.println("please enter you checkIn date");
        String checkInInput= scanner.nextLine();
        System.out.println("please enter you checkIn date");
        String checkOutInput= scanner.nextLine();

        checkInDate = new SimpleDateFormat("MM/dd/yyy").parse(checkInInput);
        checkOutDate = new SimpleDateFormat("MM/dd/yyy").parse(checkOutInput);

        Collection<IRoom> availableRooms = ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
//        if(AdminResource.getAllRooms().isEmpty()){
//            System.out.println("No Rooms please let the Admin to add one..");
//        }
//        else
        if (availableRooms.isEmpty()){
            checkInDate2=addDays(checkInDate,7);
            checkOutDate2=addDays(checkOutDate,7);
            String checkIn7= dateString(checkInDate2);
            String checkOut7=dateString(checkOutDate2);
            System.out.println("we don't have available rooms in these dates, here is a suggestion checkIn date "+ checkIn7+ " for next 7 days : "
                            +"checkOut date "+ checkOut7);

            availableRooms= ReservationService.getInstance().findRooms(checkInDate2,checkOutDate2);
            System.out.println(availableRooms);
        }else {
            System.out.println("Rooms available for your dates...");
            availableRooms=ReservationService.getInstance().findRooms(checkInDate,checkOutDate);

            for(IRoom room:availableRooms){
                System.out.println(room);
            }
        }
//        for (IRoom rooms : availableRooms) {
//            System.out.println(rooms);
//        }

//        System.out.println("Do you want to reserve a room ? Enter Y/N");
//        String customerAnswer = "y";
//            try {
//                customerAnswer = scanner.next();
//                if (customerAnswer == "y" || customerAnswer == "Y") {
//                    System.out.println("Do you hava an account with us? if No please create one. Y/N");
//                    String hasAnAccount = null;
//                    hasAnAccount = scanner.nextLine();
//                    if (hasAnAccount == "yes" || hasAnAccount == "y" || hasAnAccount == "Y") {
//                        System.out.println("please enter your email..");
//                        String customerEmail = scanner.nextLine();
//                        if (CustomerService.getInstance().getCustomer(customerEmail) == null) {
//                            System.out.println("you don't have an account with us please create one");
//
//                        }
//                    }
//                }
//
//            } catch (Exception exception) {
//                System.out.println("please enter a valid input");
//            }

        System.out.println("Do you have an account with us? if No please create one. Y/N");
        String haveAnAccount =  scanner.nextLine();
        if (Objects.equals(haveAnAccount, "yes") || Objects.equals(haveAnAccount, "y") || Objects.equals(haveAnAccount, "Yes")) {
            System.out.println("Enter you email");
            String customerEmail = scanner.nextLine();
            if (CustomerService.getInstance().getCustomer(customerEmail) == null) {
                System.out.println("You don't hava an account with us please create one.");
                System.out.println("Do you want to create one. y/n");
                String s = null;
                s = scanner.nextLine();
                if (Objects.equals(s, "Y") || Objects.equals(s, "y") || Objects.equals(s, "Yes")) {
                    MainMenu.createAnAccount(scanner);
                    System.out.println("Please choose room number");
                    String roomchosen = scanner.nextLine();
                    IRoom roomSelected = ReservationService.getInstance().getAroom(roomchosen);

                    Customer newCustomer = CustomerService.getInstance().getCustomer(customerEmail);
                    ReservationService.getInstance().reserveARoom(newCustomer, roomSelected, checkInDate, checkOutDate);
                    System.out.println("Reservation completed successfully thank you have a nice stay with us.. \n \n");

                } else if (Objects.equals(s, "N") || Objects.equals(s, "n") || Objects.equals(s, "NO")) {
                    System.out.println("you can't reserve a room without an account with us Thank you");
                    displayMainMenu(scanner);

                } else
                    System.out.println("please enter Y or No ");
                displayMainMenu(scanner);
            } else
                System.out.println("Please choose room number");
                String roomchosen = scanner.nextLine();
                IRoom roomSelected = ReservationService.getInstance().getAroom(roomchosen);

                Customer newCustomer = CustomerService.getInstance().getCustomer(customerEmail);
                ReservationService.getInstance().reserveARoom(newCustomer, roomSelected, checkInDate, checkOutDate);
                System.out.println("Reservation completed successfully thank you have a nice stay with us..\n \n");
        }else if(Objects.equals(haveAnAccount, "no") || Objects.equals(haveAnAccount, "n") || Objects.equals(haveAnAccount, "N")){
            System.out.println("Do you want to create an account? Y/N");
            String ss=scanner.nextLine();
            if(Objects.equals(ss, "yes") || Objects.equals(ss, "y") || Objects.equals(ss, "Yes")){
                System.out.println("Enter you email");
                String customerEmail = scanner.nextLine();
                MainMenu.createAnAccount(scanner);
                System.out.println("Please choose room number");
                for(IRoom rooms: availableRooms){
                    System.out.println("available rooms" + rooms);
                }
                String roomchosen = scanner.nextLine();
                IRoom roomSelected = ReservationService.getInstance().getAroom(roomchosen);

                Customer newCustomer = CustomerService.getInstance().getCustomer(customerEmail);
                ReservationService.getInstance().reserveARoom(newCustomer, roomSelected, checkInDate, checkOutDate);
                System.out.println("Reservation created successfully \n  \n");

                 }if(Objects.equals(ss, "no") || Objects.equals(ss, "n") || Objects.equals(ss, "N")){
                System.out.println("we cant reserve a room without an account..");

                }
                }
                 displayMainMenu(scanner);
    }







    private static Date formatDate(String checkInOutMessage, String s , Scanner scanner) {
        SimpleDateFormat formater = new SimpleDateFormat(s, Locale.ENGLISH);
        System.out.println(checkInOutMessage);
        String dateSting= null;
        Date date = null;
        while (date==null){
            try {
                dateSting= scanner.nextLine();
                date= formater.parse(dateSting);

            }catch (ParseException parseException){
                System.out.println("Date input is not valid try this format MM/DD/YYYY"+ parseException);
                System.out.println(checkInOutMessage);

            }
        }
        return date;

    }
    public static String dateString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }
    private static Date addDays(Date oldDate, int addedDays){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE,addedDays);
        return calendar.getTime();

    }


    public static void showMainMenu(){
        System.out.println("Welcome to the Hotel Reservation App");
        System.out.println("========= Please enter a number between 1 and 5 ================");

        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservation ");
        System.out.println("3. Create an account");
        System.out.println("4. Admin Menu");
        System.out.println("5. Exit");
    }





}

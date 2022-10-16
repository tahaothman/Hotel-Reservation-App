package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public static Customer getCustomer(String email){
        Customer customer = CustomerService.getInstance().getCustomer(email);
        return customer;
    }
    public static void addRoom(List<IRoom> rooms){
        for(IRoom allrooms: rooms){

            ReservationService.getInstance().addRoom(allrooms);
        }

    }
    public static  Collection<model.IRoom> getAllRooms(){
       return ReservationService.getInstance().getAllRooms();

    }
    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getInstance().getAllCustomers();

    }
    public static void displayAllReservations(){
        ReservationService.getInstance().printAllReservation();

    }
}

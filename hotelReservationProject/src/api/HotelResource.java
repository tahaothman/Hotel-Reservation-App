package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static Customer getCustmer(String email) {
        Customer customer= CustomerService.getInstance().getCustomer(email);
        return customer;
    }
    public static void createCustomer(String email, String firstName, String lastName){
        CustomerService.getInstance().addCustomer(email,firstName,lastName);

    }
    public IRoom getRoom(String roomNumber){
        return ReservationService.getInstance().getAroom(roomNumber);

    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkoutDate){
        Customer customer = CustomerService.getInstance().getCustomer(customerEmail);

        Reservation reservation = ReservationService.getInstance().reserveARoom(customer,room,checkInDate,checkoutDate);
        return reservation;
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
        return ReservationService.getInstance().getCoustomerReservation(customer);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getInstance().findRooms(checkIn,checkOut);

    }


}




package service;


import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;


import java.util.*;



public class ReservationService {

    private ReservationService(){}
    private static ReservationService reservationService= new ReservationService();

    public static ReservationService getInstance(){
        return reservationService;
    }


    private Map<String, IRoom> roomsList= new HashMap<>();

   // private Collection<Reservation> reservations= new ArrayList<>();
   // private Map<String,IRoom> availableRooms= new HashMap();
    public static Map<String,Collection<Reservation>> roomsReservations= new HashMap<>();
   // Collection<String> unAvailableRooms= new ArrayList<>();



    public void addRoom(IRoom room){
        if(!roomsList.containsKey(room.getRoomNumber())){

            roomsList.put(room.getRoomNumber(),room);
            System.out.println("Room added successfully");
        }else
            System.out.println("this room number is already available");

    }
    public IRoom getAroom(String roomId){
        return roomsList.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newResrvation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customersReservations= getCoustomerReservation(customer);
        if(customersReservations==null){
            customersReservations=new LinkedList<>();
        }
        customersReservations.add(newResrvation);
        roomsReservations.put(customer.getEmail(),customersReservations);

//        if (roomsReservations.containsKey(room)) {
//            Collection<Reservation> roomReserve = roomsReservations.get(room);
//            for (Reservation reservation : roomReserve) {
//                if (reservation.equals(newResrvation)) {
//                    System.out.println("Room" + room.getRoomNumber() + "is already reserved");
//                    return null;
//                } else {
//                    roomReserve.add(newResrvation);
//                    roomsReservations.put(room, roomReserve);
//
//                }
//
//            }
//
//        }
        return newResrvation;
    }

    public Collection <IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        Collection<IRoom> unAvailableRooms=new ArrayList<>();

        for(Collection<Reservation> roomsReservation: roomsReservations.values()){
            for(Reservation reservation:roomsReservation){
                if(conflictReservation(reservation,checkInDate,checkOutDate)){
                    unAvailableRooms.add(reservation.getRoom());
                }
//

            }
        }
//        Collection<Reservation> allReservations=getAllReservation();
//        Collection<IRoom> notAvailableRooms= new ArrayList<>();
//        for(Reservation reservation: allReservations){
//            if(conflictReservation(reservation,checkInDate,checkOutDate)){
//                notAvailableRooms.add(reservation.getRoom());
//
//            }
//        }
        Collection<IRoom> availableRooms=new ArrayList<>();
        availableRooms=roomsList.values();
        for(IRoom unAvailableRoom: unAvailableRooms){
                availableRooms.remove(unAvailableRoom);
            }


        return availableRooms;
    }

    private boolean conflictReservation(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())&& checkOutDate.after(reservation.getCheckInDate());
    }


//        final Collection<IRoom> unavailableRooms = new ArrayList<>();
//        for (Collection<Reservation> reservations : roomsReservations.values()) {
//            for (Reservation reservation : reservations) {
//                if (thereIsAConflict(reservation, checkInDate, checkOutDate)) {
//                    unavailableRooms.add(reservation.getRoom());
//                }
//            }
//        }
//        final Collection<IRoom> availableRooms = roomsList.values();
//        for (IRoom unavaiRooms : unavailableRooms) {
//            availableRooms.remove(unavaiRooms);
//
//        }
//        return availableRooms;




//    public Reservation reserveARoom(Customer customer, IRoom room, Date checkIndate, Date checkOutDate){
//        Reservation reservation= null;
//
//        if(availableRooms.contains(roomsList.keySet())){
//            reservation= new Reservation(customer,room,checkIndate,checkOutDate);
//            reservations.add(reservation);
//        }else
//            System.out.println("enter available room");
//        return reservation;
//
//    }

//    private boolean thereIsComflict(IRoom room, Date checkIndate, Date checkOutDate) {
//        boolean conflicttriger= false;
//        for(Reservation reservation:reservations) {
//            if (reservation.getRoom().getRoomNumber().equalsIgnoreCase(room.getRoomNumber()))
//                if (reservationHasConflict(reservation, checkIndate, checkOutDate)) ;
//            conflicttriger = true;
//            break;
//        }
//        return conflicttriger;
//    }
//
//    private boolean reservationHasConflict(Reservation reservation, Date checkIndate, Date checkOutDate) {
//        boolean hasConflict= false;
//        if(checkOutConflect(reservation,checkIndate,checkOutDate)||checkInAndOutConflict(reservation,checkIndate,checkOutDate)
//             ||checkInConflict(reservation,checkIndate,checkOutDate)||checkInAndOutConflicts(reservation,checkIndate,checkOutDate)){
//            hasConflict=true;
//        }
//        return hasConflict;
//    }
//
//    private boolean checkInConflict(Reservation reservation, Date checkIndate, Date checkOutDate) {
//        boolean checkInOutFlag= false;
//        if(reservation.getCheckInDate().before(checkIndate)&&reservation.getCheckOutDate().before(checkIndate)){
//            checkInOutFlag=true;
//        }
//        return checkInOutFlag;
//    }
//
//    private boolean checkInAndOutConflict(Reservation reservation, Date checkIndate, Date checkOutDate) {
//        boolean checkInsideflag= false;
//        if(reservation.getCheckInDate().before(checkIndate)&&reservation.getCheckOutDate().after(checkOutDate)){
//            checkInsideflag=true;
//
//        }
//        return checkInsideflag;
//    }
//
//    private boolean checkOutConflect(Reservation reservation, Date checkIndate, Date checkOutDate) {
//        boolean checkOutflag=false;
//        if(reservation.getCheckInDate().before(checkOutDate)&&reservation.getCheckInDate().after(checkIndate)){
//            checkOutflag=true;
//        }
//        return checkOutflag;
//    }
//
//    public Collection<IRoom>findRooms(Date checkInDate, Date checkOutDate){
//
//        for (Reservation reservation : reservations){
//            if(conflictRoomReservations(reservation,checkInDate ,checkOutDate)){
//                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
//
//            }
//        }
//        for(String string : roomsList.keySet()){
//            if(!unAvailableRooms.contains(string)){
//                Object obj=string;
//
//            }
//
//
//        }
//        return availableRooms;
//
//
//
//
//
//
//
//
//
//    }
//
//     boolean conflictRoomReservations(Reservation reservation, Date checkInDate, Date checkOutDate) {
//        boolean conflitflag= false;
//        if(conflict1(reservation, checkInDate,checkOutDate)||conflict2(reservation, checkInDate,checkOutDate)||conflict3(reservation, checkInDate,checkOutDate)||
//                conflict4(reservation, checkInDate,checkOutDate)){
//            conflitflag=true;
//
//        }
//        return conflitflag;
//    }
//
//    private boolean conflict4(Reservation reservation, Date checkInDate, Date checkOutDate) {
//        boolean conflictFlag4= false;
//        if(reservation.getCheckInDate().before(checkInDate)&& reservation.getCheckOutDate().after(checkOutDate)){
//            conflictFlag4=true;
//        }
//        return conflictFlag4;
//    }
//
//    private boolean conflict3(Reservation reservation, Date checkInDate, Date checkOutDate) {
//        boolean conflictFlag3= false;
//        if(reservation.getCheckInDate().after(checkInDate)&&reservation.getCheckOutDate().before(checkOutDate)){
//            conflictFlag3=true;
//        }
//        return conflictFlag3;
//    }
//
//    private boolean conflict2(Reservation reservation, Date checkInDate, Date checkOutDate) {
//        boolean conflictFlag2= false;
//        if(reservation.getCheckInDate().after(checkInDate)&& reservation.getCheckOutDate().after(checkInDate)){
//            conflictFlag2=true;
//        }
//        return conflictFlag2;
//    }
//
//    private boolean conflict1(Reservation reservation, Date checkInDate, Date checkOutDate) {
//        boolean conflictFlag=false;
//        if(reservation.getCheckInDate().before(checkInDate)&&reservation.getCheckOutDate().after(checkInDate)){
//            conflictFlag=true;
//        }
//        return conflictFlag;
//    }
        public Collection<Reservation> getCoustomerReservation (Customer customer){
            Collection<Reservation> customerReservation = new ArrayList<>();
            for (Collection reservation : roomsReservations.values()) {
                if (reservation.contains(customer.getEmail())) {
                    customerReservation.add((Reservation) reservation);
                }

            }
            return customerReservation;
        }

        public void printAllReservation () {
            for (Collection reservation : roomsReservations.values()) {
                System.out.println(reservation);
            }
        }
        public Collection<IRoom> getAllRooms () {
            for(IRoom room : roomsList.values()){
                System.out.println(room+"\n");;
            }
            return null;

        }

    private boolean thereIsAConflict(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())&&checkOutDate.after(reservation.getCheckInDate());
    }

    Collection<Reservation> getAllReservation(){
        Collection<Reservation> allReservations= new ArrayList<>();
        for(Collection<Reservation> reservations: roomsReservations.values()){
            allReservations.addAll(reservations);

        }
        return allReservations;
    }
}

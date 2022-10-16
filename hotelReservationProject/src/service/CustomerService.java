package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private CustomerService(){}
    private static CustomerService customerService= new CustomerService();
    public static  CustomerService getInstance(){
        return customerService;
    }


    static Map<String, Customer> cutomerList= new HashMap<>();

    public void addCustomer(String email, String firstName, String lastName)throws IllegalArgumentException {

        if(!cutomerList.containsKey(email)){
            Customer customer= new Customer(firstName,lastName,email);
            cutomerList.put(email,customer);

        }else
            throw new IllegalArgumentException("Customer email is already exist");

    }


    public Customer getCustomer(String customerEmail){
        return cutomerList.get(customerEmail);

    }
    public Collection<Customer> getAllCustomers(){
        return cutomerList.values();

    }
}

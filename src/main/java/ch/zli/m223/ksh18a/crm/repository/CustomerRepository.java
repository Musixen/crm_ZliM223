package ch.zli.m223.ksh18a.crm.repository;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.model.AppCustomerImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<AppCustomerImpl, Long> {

    /**
     * Create Customer
     * @param name customers name
     * @param street customers street
     * @param city customers city
     * @return Customer
     */
    default AppCustomer create(String name, String street, String city) {
        AppCustomerImpl customer = new AppCustomerImpl(name, street, city);
        return save(customer); // Persist changes to data store and return the new customer
    }

    /**
     * Update Customer
     * @param name the customers name
     * @param street the customers street
     * @param city the customers city
     * @return the updated customer
     */
    default AppCustomer update(AppCustomer customer, String name, String street, String city) {
        AppCustomerImpl customerImpl = (AppCustomerImpl) customer;
        customerImpl.setName(name);
        customerImpl.setStreet(street);
        customerImpl.setCity(city);
        return save(customerImpl);  // Persist changes to data store and return the updated customer
    }
}

package ch.zli.m223.ksh18a.crm.service;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.model.AppCustomerImpl;
import ch.zli.m223.ksh18a.crm.model.AppMemo;
import ch.zli.m223.ksh18a.crm.repository.CustomerRepository;
import ch.zli.m223.ksh18a.crm.repository.MemoRepository;
import ch.zli.m223.ksh18a.crm.role.AppRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired private MemoRepository memorepository;

    @Override
    @PermitAll
    public Collection<AppCustomer> getCustomerList() {
        return new ArrayList<AppCustomer>(customerRepository.findAll(Sort.by("name")));
    }

    @Override
    @RolesAllowed(AppRoles.user)
    public AppCustomer getCustomer(long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    @RolesAllowed(AppRoles.user)
    public AppCustomer addCustomer(String name, String street, String city) {
        return customerRepository.create(name, street, city);
    }

    @Override
    @RolesAllowed(AppRoles.user)
    public AppMemo addMemoToCustomer(long customerId, String memoText) {
        AppCustomer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) { return null; }

        return memorepository.addMemo((AppCustomerImpl)customer, memoText);
    }

    @Override
    public AppCustomer updateCustomer(Long customerId, String name, String street, String city) {
        AppCustomer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) { return null; }

        return customerRepository.update(customer, name, street, city);
    }

    @Override
    public void deleteCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Collection<AppMemo> getMemos(long customerId) {
        AppCustomer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) { return null; }

        return customer.getMemos();
    }

    @Override
    public AppMemo getMemo(long memoId) {
        return memorepository.findById(memoId).orElse(null);
    }

    @Override
    public AppMemo updateMemo(Long memoId, String memoText, Date date) {
        AppMemo memo = memorepository.findById(memoId).orElse(null);
        if (memo == null) { return null; }

        return memorepository.update(memo, memoText, date);
    }

    @Override
    public void deleteMemo(long memoId) {
        AppMemo memo = memorepository.findById(memoId).orElse(null);
        if (memo == null) { return; }

        memorepository.deleteMemoFromCustomer(memo);
    }

}

package ch.zli.m223.ksh18a.crm.service;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.model.AppMemo;

import java.util.Collection;
import java.util.Date;

public interface CustomerService {
    Collection<AppCustomer> getCustomerList();

    AppCustomer getCustomer(long customerId);

    AppCustomer addCustomer(String name, String street, String city);

    AppCustomer updateCustomer(Long customerId, String name, String street, String city);

    void deleteCustomer(long customerId);

    Collection<AppMemo> getMemos(long customerId);

    AppMemo getMemo(long memoId);

    AppMemo addMemoToCustomer(long customerId, String memoText);

    AppMemo updateMemo(Long memoId, String memoText, Date localDateTime);

    void deleteMemo(long memoId);
}

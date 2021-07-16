package ch.zli.m223.ksh18a.crm.repository;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.model.AppCustomerImpl;
import ch.zli.m223.ksh18a.crm.model.AppMemo;
import ch.zli.m223.ksh18a.crm.model.AppMemoImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface MemoRepository extends CrudRepository<AppMemoImpl, Long> {

    public default AppMemo addMemo(AppCustomer customer, String memoText) {
        AppCustomerImpl customerImpl = (AppCustomerImpl) customer;

        AppMemoImpl memo = new AppMemoImpl(customerImpl, memoText);
        customerImpl.addMemo(memo);
        save(memo);
        return memo;
    }
    default public AppMemo update(AppMemo memo, String memoText, Date date) {
        AppMemoImpl memoImpl = (AppMemoImpl) memo;
        memoImpl.setMemoText(memoText);
        memoImpl.setDate(date);
        return save(memoImpl);
    }

    /**
     * Delete a memo
     * @param memo
     */
    default public void deleteMemoFromCustomer(AppMemo memo) {
        AppCustomerImpl customer = (AppCustomerImpl) memo.getCustomer();
        customer.removeMemo((AppMemoImpl) memo);
        try {deleteById(memo.getId());} catch (EmptyResultDataAccessException ignored) {}
    }
}
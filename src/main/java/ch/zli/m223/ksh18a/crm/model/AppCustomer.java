package ch.zli.m223.ksh18a.crm.model;


import java.util.Collection;
import java.util.List;

public interface AppCustomer {
    Long getId();

    String getName();

    String getStreet();

    String getCity();

    Collection<AppMemo> getMemos();

}

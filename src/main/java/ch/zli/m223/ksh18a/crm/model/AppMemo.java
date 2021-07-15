package ch.zli.m223.ksh18a.crm.model;

import java.util.Date;

public interface AppMemo {

    Long getId();

    Date getCoverageDate();

    String getNote();

    AppCustomer getCustomer();
}

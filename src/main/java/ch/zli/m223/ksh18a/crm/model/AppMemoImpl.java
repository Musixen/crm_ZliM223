package ch.zli.m223.ksh18a.crm.model;

import javax.persistence.*;
import java.util.Date;


@Entity(name="Memo")
public class AppMemoImpl implements AppMemo{

    @Id
    @GeneratedValue
    private Long id;

    private long coverage;
    private String noteText;

    @ManyToOne
    private AppCustomerImpl customer;

    public AppMemoImpl(AppCustomerImpl customer, String noteText) {
        this();
        this.customer = customer;
        this.noteText = noteText;
    }

    public AppMemoImpl() {
        coverage = new Date().getTime();
    }

    @Override public Long getId(){
        return id;
    }
    @Override public Date getCoverageDate(){
        return new Date(coverage);
    }
    @Override public String getNote(){
        return noteText;
    }
    @Override public AppCustomer getCustomer(){
        return customer;
    }

    public void setMemoText(String memoText){
        noteText = memoText;
    }

    public void setDate(Date date){
        coverage = date.getTime();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppMemoImpl other = (AppMemoImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemoImpl [id=" + id + ", coverage=" + coverage + ", noteText=" + noteText + "]";
    }
}

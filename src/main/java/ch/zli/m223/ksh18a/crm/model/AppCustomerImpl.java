package ch.zli.m223.ksh18a.crm.model;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Customer")
public class AppCustomerImpl implements AppCustomer {

    @Id	// (2)
    @GeneratedValue // (3)
    private Long id;
    private String name;
    private String street;
    private String city;

    @OneToMany(
            mappedBy="customer",
            fetch= FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private List<AppMemoImpl> memos;

    public void addMemo(AppMemoImpl memo) {
        memos.add(memo);
    }

    public void removeMemo(AppMemoImpl memo) {
        memos.remove(memo);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getCity() {
        return city;
    }


    public Collection<AppMemo> getMemos() {
        ArrayList<AppMemo> res = new ArrayList<AppMemo>(memos);
        Collections.sort(res, new Comparator<AppMemo>() {
            public int compare(AppMemo o1, AppMemo o2) {
                return (int)(o2.getId() - o1.getId());
            }
        });
        return res;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
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
        AppCustomerImpl other = (AppCustomerImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerImpl [id=" + id + ", name=" + name + ", street=" + street + ", city=" + city + "]";
    }
}

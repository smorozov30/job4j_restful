package ru.job4j.auth.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long itn;
    private String name;
    private String lastname;
    private Timestamp date;

    public static Employee of(int itn, String name, String lastname) {
        Employee employee = new Employee();
        employee.itn = itn;
        employee.name = name;
        employee.lastname = lastname;
        employee.date = new Timestamp(System.currentTimeMillis());
        return employee;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_accounts",
    joinColumns = @JoinColumn(name="employee_id", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name="person_id", referencedColumnName="id", unique = true))
    private Set<Person> accounts = new HashSet<>();

    public static Employee of(String name, String lastname, long itn) {
        Employee employee = new Employee();
        employee.name = name;
        employee.lastname = lastname;
        employee.itn = itn;
        employee.date = new Timestamp(System.currentTimeMillis());
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getItn() {
        return itn;
    }

    public void setItn(long itn) {
        this.itn = itn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Person> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Person account) {
        accounts.add(account);
    }

    public void deleteAccountById(int id) {
        accounts.removeIf(p -> p.getId() == id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return itn == employee.itn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itn);
    }
}

package webservicees.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String salutation;
    private String email;

    public CustomerEntity(String id, String firstName, String lastName, String salutation, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = salutation;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public CustomerEntity() {
    }

    public String getSalutation() {
        return salutation;
    }
}

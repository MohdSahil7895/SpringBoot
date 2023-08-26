package com.project.registerLogin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Index;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "registerLogin")
public class Register {

    @Id
    @Column(name = "ID",length = 10)
    @GeneratedValue()
    private String id;

    @Column(name = "Name",length = 20)
    private String name;


    @Indexed(unique = true)
    @Column(name = "Email",length = 40)
    private String email;

    @Column(name = "Password",length = 15)
    private String password;

    public Register(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Register() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

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

    @Column(name = "Name")
    private String name;


    @Indexed(unique = true)
    @Column(name = "Email",length = 40)
    private String email;

    @Column(name = "Password",length = 15)
    private String password;
    @Column(name = "reset_token",length = 15)
    private String resetToken;
    public Register(String id, String name, String email, String password, String setResetToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.resetToken = setResetToken;
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
                ", setResetToken='" + resetToken + '\'' +
                '}';
    }

}

package com.project.registerLogin.DTO;

import com.project.registerLogin.Entity.Register;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.swing.text.ElementIterator;

@Getter
@Setter
public class RegisterationDTO {

    private String id;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*", message = "Invalid name format")
    private String name;

    @Email
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*\\d)(?!^\\d)[^@]+@.*\\.com$", message = "Invalid email format")
    private String email;

    @Size(min = 5,max = 10 ,message = "Password length invalid")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}:;<>,.?~])\\S+$",message = "Password format invalid")
    private String password;

    public RegisterationDTO(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmployeeDetailDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

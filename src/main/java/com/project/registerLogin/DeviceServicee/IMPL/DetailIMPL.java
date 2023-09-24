package com.project.registerLogin.DeviceServicee.IMPL;


import com.project.registerLogin.DTO.RegisterResponseDTO;
import com.project.registerLogin.DTO.RegisterationDTO;
import com.project.registerLogin.DeviceServicee.DetailService;
import com.project.registerLogin.Entity.Register;
import com.project.registerLogin.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DetailIMPL implements DetailService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public  List<Register> findAll(){
        return mongoTemplate.findAll(Register.class);
    }
    @Override
    public RegisterResponseDTO getLoginInfo(RegisterationDTO registerationDTO) {
        Register register = new Register();
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        register.setName(registerationDTO.getName());
        register.setEmail(registerationDTO.getEmail());
        register.setPassword(registerationDTO.getPassword());
//        register.setPassword(passwordEncoder.encode(registerationDTO.getPassword()));
        registerRepository.save(register);
        registerResponseDTO.setMessage("Register Successful");
        return registerResponseDTO;

    }

    @Override
    public RegisterResponseDTO getLogin(RegisterationDTO registerationDTO) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        Register register = registerRepository.findByEmail(registerationDTO.getEmail());
        if (register == null)
            registerResponseDTO.setMessage("Data Not Present");
//        else if (register.getPassword().equals(registerationDTO.getPassword()))
            else if (passwordEncoder.matches(registerationDTO.getPassword(), register.getPassword()))
        registerResponseDTO.setMessage("Login Successful");
        else
            registerResponseDTO.setMessage("Wrong Password");
        return registerResponseDTO;
    }

    @Override
    public List<Register> findByname(@PathVariable(value = "name") String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.find(query, Register.class);

    }
    @Override
    public RegisterResponseDTO getResetPassword(RegisterationDTO registerationDTO){
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        Register register = registerRepository.findByEmail(registerationDTO.getEmail());
        if (register == null)
            registerResponseDTO.setMessage("Email Not Registered");

        else if(!registerationDTO.getPassword().equalsIgnoreCase(register.getPassword())){
            register.setPassword(registerationDTO.getPassword());
            registerResponseDTO.setMessage("Password reset successful");
            registerRepository.save(register);}
        else registerResponseDTO.setMessage("Password should be different");

        return registerResponseDTO;
    }

    @Override
    public RegisterResponseDTO getForgotPasswordEmail(RegisterationDTO registerationDTO) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        Register register = registerRepository.findByEmail(registerationDTO.getEmail());
        if (Objects.isNull(register)) {
            registerResponseDTO.setMessage("Email Not Registered");
            return registerResponseDTO;
    }
        // Generate a reset token and send it via email
        register.setResetToken(generateResetToken());
        registerRepository.save(register);
        sendPasswordResetEmail(registerationDTO.getEmail(), register.getResetToken(),register.getId());

        // You may also want to save the resetToken and email in your database for verification later.
        registerResponseDTO.setMessage("Password reset email sent successfully");
        return registerResponseDTO;
    }

    private void sendPasswordResetEmail(String toEmail, String resetToken ,String id) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset");
        message.setText("Your password reset token is: " + "http://localhost:3000/" + resetToken +"/"+ id);
        System.out.println("Email Sent Successfully");
        javaMailSender.send(message);
    }
    private String generateResetToken() {

        return UUID.randomUUID().toString();
        // Generate a unique reset token (you can use UUID or any other method)
        // and return it.
        // Example: return UUID.randomUUID().toString();
//        return "your-reset-token";
    }


}

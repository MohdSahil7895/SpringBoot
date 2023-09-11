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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DetailIMPL implements DetailService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MongoTemplate mongoTemplate;

    //    @Autowired private Register register;
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
    public  List<Register> findAll(){
       return mongoTemplate.findAll(Register.class);
    }
}

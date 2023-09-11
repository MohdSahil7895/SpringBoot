package com.project.registerLogin.DeviceServicee;

import com.project.registerLogin.DTO.RegisterResponseDTO;
import com.project.registerLogin.DTO.RegisterationDTO;
import com.project.registerLogin.Entity.Register;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DetailService {
    RegisterResponseDTO getLoginInfo(RegisterationDTO registerationDTO);

    RegisterResponseDTO getLogin(RegisterationDTO registerationDTO);

    List<Register> findByname(@PathVariable(value = "name") String name);

    List<Register> findAll();

    RegisterResponseDTO getResetPassword(RegisterationDTO registerationDTO);

//    RegisterationDTO getSearchInfo(RegisterationDTO registerationDTO);
}

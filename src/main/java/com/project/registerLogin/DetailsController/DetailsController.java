package com.project.registerLogin.DetailsController;


import com.project.registerLogin.DTO.RegisterResponseDTO;
import com.project.registerLogin.DTO.RegisterationDTO;
import com.project.registerLogin.DeviceServicee.DetailService;
import com.project.registerLogin.Entity.Register;
import com.project.registerLogin.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/loginLogout")
public class DetailsController {

        @Autowired
        private DetailService detailService;

        @Autowired
        private RegisterRepository registerRepository;


        @Autowired
        private MongoTemplate mongoTemplate;

        @PostMapping(value="register")
        public RegisterResponseDTO getDetail(@Validated @RequestBody RegisterationDTO registerationDTO){
            return detailService.getLoginInfo(registerationDTO);
        }
        @PostMapping(value="login")
        public <names> RegisterResponseDTO getLogin(@Validated @RequestBody RegisterationDTO registerationDTO) {
                return detailService.getLogin(registerationDTO);
        }
        @GetMapping(value = "searchIn/{name}")
        List<Register> findName(@PathVariable (value = "name") String name) {
                return detailService.findByname(name);

        }
        @GetMapping(value = "searchAll")
        List<Register> findAllData() {
                return detailService.findAll();

        }
        @PostMapping("/reset/{token}${id}")
        public RegisterResponseDTO getReset(@PathVariable (value = "resetToken") String token,@PathVariable (value = "id") String id,
                                            @Validated @RequestBody RegisterationDTO registerationDTO)
                                            {
                return detailService.getResetPassword(registerationDTO);
        }
        @GetMapping("/debug")
        public String debug() {
                String str = "Debugging is on ";
                return str;
        }

        @PostMapping("/forgotEmail")
        public RegisterResponseDTO getForgotEmail(@Validated @RequestBody RegisterationDTO registerationDTO){
                return detailService.getForgotPasswordEmail(registerationDTO);
        }
//        @PostMapping("/")
//        public RegisterResponseDTO getForgotEmail(@Validated @RequestBody RegisterationDTO registerationDTO){
//                return detailService.getForgotPasswordEmail(registerationDTO);
//        }

}

package com.project.registerLogin.Repository;

import com.project.registerLogin.Entity.Register;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends MongoRepository<Register,String> {

    Register findByEmail(String email);
    Register findByID(String id);

}

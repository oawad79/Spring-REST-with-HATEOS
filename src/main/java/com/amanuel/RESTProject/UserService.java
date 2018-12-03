package com.amanuel.RESTProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public Optional<User> getUser(int id){
        return repository.findById(id);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
}

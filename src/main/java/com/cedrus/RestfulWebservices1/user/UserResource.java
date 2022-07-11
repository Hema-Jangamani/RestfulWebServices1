package com.cedrus.RestfulWebservices1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null){
            throw new UserNotFoundException("id-"+id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

//    @PutMapping("/update/{id}")
//    public User updateUser(@RequestBody User user,@PathVariable("id") int id){
//        User user1 = this.service.editUser(user);
//        if (user.getId().equals(id)){
//            return user;
//        }else {
//            throw new UserNotFoundException("id-"+id);
//        }
//    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if (user == null){
            throw new UserNotFoundException("id-"+id);
        }
    }
}

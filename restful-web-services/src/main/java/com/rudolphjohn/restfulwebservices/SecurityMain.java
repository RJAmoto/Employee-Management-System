package com.rudolphjohn.restfulwebservices;

import com.rudolphjohn.restfulwebservices.userDetailsService.MyUserDetailsService;
import com.rudolphjohn.restfulwebservices.users.User;
import com.rudolphjohn.restfulwebservices.users.UserRepository;
import com.rudolphjohn.restfulwebservices.users.jawt.JwtUtil;
import com.rudolphjohn.restfulwebservices.users.models.AuthenticationRequest;
import com.rudolphjohn.restfulwebservices.users.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecurityMain {

    @Autowired
    UserRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/home")
    public String home(){
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome admin</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome user</h1>");
    }

    @PostMapping("/addUser")
    public void defaultAddUser(@RequestBody User user){
        repository.save(user);
    }

    @DeleteMapping("/removeUser/{id}")
    public void deleteUser(@PathVariable int id){
        boolean checkUser = repository.existsById(id);

        if (checkUser) {
            repository.deleteById(id);
        }
        throw new IllegalStateException("User not here");
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?>createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
        throws Exception{
    try {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword()));
         }
        catch  (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

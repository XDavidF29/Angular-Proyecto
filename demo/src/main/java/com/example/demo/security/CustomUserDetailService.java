package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Rol;
import com.example.demo.entidades.UserEntity;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RolRepository;
import com.example.demo.repositorio.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository RolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );

        UserDetails userDetails = new User(userDB.getUsername(), userDB.getPassword(), mapToGrantedAuthorities(userDB.getRoles()));

        return userDetails;

    }

    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Rol> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    public  UserEntity UserToUser(Usuario usuario) {

        UserEntity user = new UserEntity();
        user.setUsername(usuario.getCorreo());
        user.setPassword(passwordEncoder.encode("123"));

        Rol roles = RolRepository.findByName("USUARIO").get();
        user.setRoles(List.of(roles));
        return user;
    }

    public  UserEntity VeterinarioToUser(Veterinario veterinario) {

    
        UserEntity veterinari   = new UserEntity();
        veterinari.setUsername(veterinario.getCedula());
        veterinari.setPassword(passwordEncoder.encode("123"));

        Rol roles = RolRepository.findByName("VETERINARIO").get();
        veterinari.setRoles(List.of(roles));
        return veterinari;
    }
    
    
}

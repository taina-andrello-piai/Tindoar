package com.techninjas.tindoar.controllers;

import com.techninjas.tindoar.requests.*;
import com.techninjas.tindoar.responses.*;
import com.techninjas.tindoar.services.*;
import org.springframework.web.bind.annotation.*;
import com.techninjas.tindoar.repositories.*;
import com.techninjas.tindoar.jwt.*;
import com.techninjas.tindoar.models.ERole;
import com.techninjas.tindoar.models.Role;
import com.techninjas.tindoar.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	   AuthenticationManager authenticationManager;

	    UsuarioRepository usuarioRepository;

	    RoleRepository  roleRepository;

	    PasswordEncoder passwordEncoder;

	    JwtUtils jwtUtils;

	    @Autowired
	    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
	        this.authenticationManager = authenticationManager;
	        this.usuarioRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtUtils = jwtUtils;
	    }

	    @PostMapping("/signin")
	    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
	        List<String> roles = myUserDetails.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(new JwtResponse(
	                jwt,
	                myUserDetails.getId(),
	                myUserDetails.getUsername(),
	                myUserDetails.getNome(),
	                myUserDetails.getEmail(),
	                myUserDetails.getWhatsapp(),
	                myUserDetails.getRedeSocial(),
	                roles
	        ));
	    }

	    @PostMapping("/signup")
	    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
	        if (usuarioRepository.existsByUsername(signupRequest.getUsername())) {
	            return ResponseEntity
	                    .badRequest().body(new MessageResponse("Error: Username is already taken!"));
	        }

	        if (usuarioRepository.existsByEmail(signupRequest.getEmail())) {
	            return ResponseEntity
	                    .badRequest().body(new MessageResponse("Error: Email is already in use!"));
	        }

	        Usuario usuario = new Usuario(
	                signupRequest.getUsername(),
	            	signupRequest.getNome(),
	                signupRequest.getEmail(),
	                signupRequest.getWhatsapp(),
	                signupRequest.getRedeSocial(),
	                passwordEncoder.encode(signupRequest.getPassword())
	        );

	        Set<String> strRoles = signupRequest.getRole();
	        Set<Role> roles = new HashSet<>();

	        if (strRoles == null) {
	             Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	                     .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	             roles.add(userRole);
	        } else {
	            strRoles.forEach(role -> {
	                switch (role) {
	                    case "admin":
	                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	                        roles.add(adminRole);
	                        break;
	                    case "mod":
	                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
	                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	                        roles.add(modRole);
	                        break;
	                    default:
	                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	                        roles.add(userRole);
	                }
	            });
	        }

	        usuario.setRoles(roles);
	        usuarioRepository.save(usuario);

	        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	    }
}

package com.techninjas.tindoar.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techninjas.tindoar.models.Usuario;


public class MyUserDetails implements UserDetails{

	private static final long serialVersioUID = 1l; 
	
	private Integer id;
	private String username;
	private String nome;
	private String email;
	private String whatsapp;
	private String redeSocial;
	@JsonIgnore
	private String password;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	 
	public MyUserDetails(Integer id, String username, String nome, String email, String whatsapp, String redeSocial,
			String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.email = email;
		this.whatsapp = whatsapp;
		this.redeSocial = redeSocial;
		this.password = password;
		this.authorities = authorities;
	}
	
	 public static MyUserDetails build(Usuario usuario) {
	        List<GrantedAuthority> authorities = usuario.getRoles().stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
	                .collect(Collectors.toList());

	        return new MyUserDetails(
	                usuario.getId(),
	                usuario.getUsername(),
	                usuario.getNome(),
	                usuario.getEmail(),
	                usuario.getWhatsapp(),
	                usuario.getRedeSocial(),
	                usuario.getPassword(),
	                authorities);
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

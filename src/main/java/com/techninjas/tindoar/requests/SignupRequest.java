package com.techninjas.tindoar.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

	    @NotBlank
	    @Size(min = 3, max = 20)
	    private String username;
	    
	    private String nome;

	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private String whatsapp;
	    
	    private String RedeSocial;

	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;

	    private Set<String> role;

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Set<String> getRole() {
	        return role;
	    }

	    public void setRole(Set<String> role) {
	        this.role = role;
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
			return RedeSocial;
		}

		public void setRedeSocial(String redeSocial) {
			RedeSocial = redeSocial;
		}
	    
	    
}

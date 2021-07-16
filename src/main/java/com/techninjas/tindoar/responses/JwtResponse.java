package com.techninjas.tindoar.responses;

import java.util.List;

public class JwtResponse {
	
	   private String token;
	    private String type = "Bearer ";
	    private Integer id;
	    private String username;
	    private String nome;
	    private String email;
	    private String whatsapp;
	    private String redeSocial;
	    private List<String> roles;

	    public JwtResponse(String token, Integer id, String username, String nome, String email, String whatsapp, String redeSocial, List<String> roles) {
	        this.token = token;
	        this.id = id;
	        this.username = username;
	        this.nome = nome;
	        this.email = email;
	        this.whatsapp = whatsapp;
	        this.redeSocial = redeSocial;
	        this.roles = roles;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

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

	    public List<String> getRoles() {
	        return roles;
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
	    
	    

}

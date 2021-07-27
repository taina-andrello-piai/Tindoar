package com.techninjas.tindoar.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios",uniqueConstraints = {@UniqueConstraint(columnNames = "username"),@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "whatsapp"),@UniqueConstraint(columnNames = "redeSocial")})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @NotBlank
    @Size(max = 15)
	private String username;
	
	private String nome;
	
    @NotBlank
    @Size(max = 50)
	@Email
	private String email;
	
	private String whatsapp;
	
	private String redeSocial;
	
    @NotBlank
    //@Lob
    //@Column(columnDefinition="text")
	private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy="user") //certo
    private List<Produto> produtos = new ArrayList<>();
    
    @OneToMany(mappedBy="doador")
    private List<Doacao> doacoes = new ArrayList<>();
    
    @OneToMany(mappedBy="recebedor")
    private List<Doacao> recebidos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public Usuario(@NotBlank @Size(max = 15) String username, String nome,
			@NotBlank @Size(max = 50) @Email String email, String whatsapp, String redeSocial,
			@NotBlank @Size(max = 16) String password) {
		super();
		this.username = username;
		this.nome = nome;
		this.email = email;
		this.whatsapp = whatsapp;
		this.redeSocial = redeSocial;
		this.password = password;
	}

	public Usuario() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<Doacao> doacoes) {
		this.doacoes = doacoes;
	}

	public List<Doacao> getRecebidos() {
		return recebidos;
	}

	public void setRecebidos(List<Doacao> recebidos) {
		this.recebidos = recebidos;
	}
	
	
}

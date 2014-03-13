package it.springlesson4.domain;

public class Persona {

	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}

	private String nome;
	private String cognome;

	public Long getId() {
		return id;
	}
	public Persona(){}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}

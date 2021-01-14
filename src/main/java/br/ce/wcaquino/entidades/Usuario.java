package br.ce.wcaquino.entidades;

public class Usuario {

	private String nome;
	
	public Usuario() {}
	
	public Usuario(String nome) { this.nome = nome; }

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	// implementar metodo equals para conseguir realizar o teste assertequals
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;

		Usuario other = (Usuario) o;
		if (nome == null) {
			if (other.nome != null) return false;
		} else if (!nome.equals(other.nome)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return nome.hashCode();
	}
}
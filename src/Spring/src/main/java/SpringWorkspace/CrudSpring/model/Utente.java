package SpringWorkspace.CrudSpring.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 25, nullable = false)
	private String nome;
	@Column(length = 30, nullable = false)
	private String cognome;
	@Column(length = 16, nullable = false, unique = true)
	private String cf;
	// constraint fk_utenti_idRuolo foreign key(idRuolo) references ruoli(id)
	@ManyToOne
	@JoinColumn(name = "idRuolo", referencedColumnName = "id")
	private Ruolo ruolo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	@JsonBackReference
	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + cognome + "," + cf + "," + ruolo;
	}
}

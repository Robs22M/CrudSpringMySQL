package SpringWorkspace.CrudSpring.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // annotazione che permette di identificare una classe come model(entity)
@Table(name = "ruoli")
public class Ruolo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 20, nullable = false, unique = true)
	private String ruolo;
	@OneToMany(mappedBy = "ruolo")
	private List<Utente> utenti;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@JsonManagedReference
	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	public String toString() {
		return ruolo + " (" + id + ")";
	}

}

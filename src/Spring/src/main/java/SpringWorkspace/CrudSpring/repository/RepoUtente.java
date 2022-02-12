package SpringWorkspace.CrudSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringWorkspace.CrudSpring.model.Utente;

public interface RepoUtente extends JpaRepository<Utente, Integer> {
	public Utente findById(int id);
}

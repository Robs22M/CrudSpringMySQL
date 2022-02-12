package SpringWorkspace.CrudSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringWorkspace.CrudSpring.model.Ruolo;

public interface RepoRuolo extends JpaRepository<Ruolo, Integer> {

	public Ruolo findById(int id);
}

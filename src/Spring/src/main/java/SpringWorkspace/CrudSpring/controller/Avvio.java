package SpringWorkspace.CrudSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import SpringWorkspace.CrudSpring.model.Ruolo;
import SpringWorkspace.CrudSpring.model.Utente;
import SpringWorkspace.CrudSpring.repository.RepoRuolo;
import SpringWorkspace.CrudSpring.repository.RepoUtente;

@RestController // annotazione che permette di identificare una classe come controller
public class Avvio {

	// con questa annotazione applichiamo il DI (Dependency Injection)
	// ovvero prendiamo un oggetto istanziato da Spring dal suo container
	// (qui vengono salvati tutti gli oggetti istanziati dal framework)
	// e lo iniettiamo dove desideriamo;
	// quando Spring ha a che fare con un'interfaccia crea un oggetto di classe
	// anonima
	// che implementa l'interfaccia stessa(una classe anonima è una classe non
	// dichiarata esplicitamente)
	@Autowired
	RepoRuolo rr;
	@Autowired
	RepoUtente ru;

	// definiamo la destinazione della richiesta, il client(postman) dovrà
	// conoscerla per mandare uno o più valori a questo metodo
	@RequestMapping("/insertRuolo")
	public String inserimentoRuolo(@RequestParam String ruolo) { // =getParameter();
		Ruolo r = new Ruolo();
		r.setRuolo(ruolo);
		rr.save(r);
		return "Ruolo inserito con successo!";
	}

	@RequestMapping("/updateRuolo")
	public String modificaRuolo(@RequestParam int id, String ruolo) { // =getParameter();
		Ruolo r = rr.findById(id);// = select * from ruoli where id=?
		if (rr.existsById(id)) {

			r.setRuolo(ruolo);
			rr.save(r);
			return "Ruolo modificato con successo!";
		} else {
			return "Ruolo non trovato";
		}
	}

	@RequestMapping("/deleteRuolo")
	public String cancellaRuolo(@RequestParam int id) { // =getParameter();
		Ruolo r = rr.findById(id);// = select * from ruoli where id=?
		if (rr.existsById(id)) {
			rr.delete(r);
			return "Ruolo cancellato con successo!";
		} else {
			return "Ruolo non trovato";
		}
	}

	@RequestMapping("/getRuolo")
	public String leggiRuolo(@RequestParam int id) {
		if (rr.existsById(id)) {
			return rr.findById(id).toString();
		} else {
			return "Ruolo non trovato";
		}
	}

	@RequestMapping("/getRuoli")
	public List<Ruolo> leggiRuoli() {
		return rr.findAll();
	}

//	@RequestMapping("/insertUtente")
//	public String inserimentoUtente(@RequestParam int idRuolo, String nome, String cognome, String cf) {
//		Utente u = new Utente();
//		u.setNome(nome);
//		u.setCognome(cognome);
//		u.setCf(cf);
//		Ruolo r = rr.findById(idRuolo);
//		// applico il OneToMany assegnando un utente a ruolo(l'utente dovrà far parte di
//		// una lista di utenti legati ad uno specifico ruolo)
//		r.getUtenti().add(u); // aggiorno la lista degli utenti attribuiti ad un ruolo
//		// scelto(esempio: admin)
//		// applico il ManyToOne
//		u.setRuolo(r);
//		ru.save(u);
//		return "Utente inserito con successo";
//	}

	@RequestMapping("/insertUtente")
	public String inserimentoUtente(@RequestParam int idRuolo, Utente u) { // applico il Data Binding
		// è un altro esempio di disaccoppiamento in quanto in questo caso Spring crea
		// un oggetto di tipo Utente riempito dei valori mandati da Postman e passato
		// poi come argomento nel metodo
		Ruolo r = rr.findById(idRuolo);
		// applico il OneToMany assegnando un utente a ruolo(l'utente dovrà far parte di
		// una lista di utenti legati ad uno specifico ruolo)
		r.getUtenti().add(u); // aggiorno la lista degli utenti attribuiti ad un ruolo
		// scelto(esempio: admin)
		// applico il ManyToOne
		u.setRuolo(r);
		ru.save(u);
		return "Utente inserito con successo";
	}

	@RequestMapping("/updateUtente")
	public String modificaUtente(@RequestParam int id, Utente u) {
		if (ru.existsById(id)) {
			Utente utenteCercato = ru.findById(id);
			utenteCercato.setNome(u.getNome());
			utenteCercato.setCognome(u.getCognome());
			utenteCercato.setCf(u.getCf());
			// Ruolo ruoloCercato = rr.findById(idRuolo);
			// utenteCercato.setRuolo(ruoloCercato);
			ru.save(utenteCercato);
			return "Utente modificato con successo!";
		} else {
			return "Utente non trovato";
		}
	}

	@RequestMapping("/updateUtente")
	public String modificaUtente(@RequestParam int id, Utente u, int idRuolo) {
		if (!ru.existsById(id)) {
			return "ID non trovato";
		} else if (!rr.existsById(idRuolo)) {
			return "ID ruolo non trovato";
		} else {
			Utente uc = ru.findById(id);

//			int idRuoloCorrente = uc.getRuolo().getId();

			// Cerchiamo il ruolo corrente dell'utente per fare in modo che quest'ultimo
			// venga rimosso
			// dalla lista delvecchio ruolo
//			Ruolo oldRuolo = repoRuolo.findById(idRuoloCorrente);
//			oldRuolo.getUtenti().remove(uc); ormai non si utilizzano più perchè il framework provvede a farlo in automatico

			uc.setNome(u.getNome());
			uc.setCognome(u.getCognome());
			uc.setCf(u.getCf());

			// Cerchiamo il nuovo ruolo da assegnare all'utente il quale dovrà poi essere
			// aggiunto
			// alla lista del nuovo ruolo
			Ruolo newRuolo = rr.findById(idRuolo);
			uc.setRuolo(newRuolo);
//			newRuolo.getUtenti().add(uc); ormai non si utilizzano più perchè il framework provvede a farlo in automatico

			ru.save(uc);
			return "Modificato";
		}
	}

	@RequestMapping("/deleteUtente")
	public String cancellaUtente(@RequestParam int id) {
		if (ru.existsById(id)) {
			ru.deleteById(id);
			return "Utente cancellato con successo!";
		} else {
			return "Utente non trovato";
		}
	}

	@RequestMapping("/getUtenti")
	public List<Utente> leggiUtenti() {
		return ru.findAll();
	}

	@RequestMapping("/getUtente")
	public String leggiUtente(@RequestParam int id) {
		if (ru.existsById(id)) {
			return ru.findById(id).toString();
		} else {
			return "Utente non trovato";
		}
	}
}

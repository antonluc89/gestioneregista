package it.prova.raccoltafilm.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.raccoltafilm.dao.FilmDAO;
import it.prova.raccoltafilm.dao.MyDaoFactory;
import it.prova.raccoltafilm.dao.RegistaDAO;
import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.web.listener.LocalEntityManagerFactoryListener;

public class RegistaServiceImpl implements RegistaService {

	private RegistaDAO registaDAO;

	@Override
	public void setRegistaDAO(RegistaDAO registaDAO) {
		this.registaDAO = registaDAO;
	}

	@Override
	public List<Regista> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			registaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return registaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Regista caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			registaDAO.setEntityManager(entityManager);

			return registaDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Regista caricaSingoloElementoConFilms(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			registaDAO.setEntityManager(entityManager);

			return registaDAO.findWithFilms(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Regista registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			registaDAO.setEntityManager(entityManager);

			registaDAO.update(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Regista registaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			registaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			registaDAO.insert(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Regista registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			FilmDAO filmDAO = MyDaoFactory.getFilmDAOInstance();
			filmDAO.setEntityManager(entityManager);

			List<Film> lista = filmDAO.findFilmByRegista(registaInstance);

			for (Film filmItem : lista) {
				filmItem.setRegista(null);
				filmDAO.delete(filmItem);
			}

			registaDAO.setEntityManager(entityManager);

			registaInstance = registaDAO.findWithFilms(registaInstance.getId());

			registaDAO.delete(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Regista> findByExample(Regista example) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			registaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return registaDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}

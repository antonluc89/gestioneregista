package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteUpdateFilmServlet")
public class ExecuteUpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilityForm utilityForm;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idFilmParam = request.getParameter("idFilm");
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String dataPubblicazioneStringParam = request.getParameter("dataPubblicazione");
		String minutiDurataInputParam = request.getParameter("minutiDurata");
		String registaIdStringParam = request.getParameter("regista.id");

		Date dataPubblicazioneParsed = utilityForm.parseDateArrivoFromString(dataPubblicazioneStringParam);

		if (!utilityForm.validateFilmFormInput(titoloInputParam, genereInputParam, minutiDurataInputParam,
				dataPubblicazioneStringParam, registaIdStringParam)) {
			request.setAttribute("errorMessage", "Errori nella validazione");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}

		Film filmInstance;
		try {
			filmInstance = MyServiceFactory.getFilmServiceInstance().caricaSingoloElemento(Long.parseLong(idFilmParam));
			filmInstance.setTitolo(titoloInputParam);
			filmInstance.setGenere(genereInputParam);
			filmInstance.setDataPubblicazione(dataPubblicazioneParsed);
			filmInstance.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
			filmInstance.setRegista(MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(registaIdStringParam)));
			

			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);

			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione di aggiornamento effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}
}

package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteUpdateRegistaServlet")
public class ExecuteUpdateRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilityForm utilityForm;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idRegistaParam = request.getParameter("idRegista");
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String nickNameInputParam = request.getParameter("nickName");
		String dataDiNascitaStringParam = request.getParameter("dataDiNascita");
	

		Date dataDiNascitaParsed = utilityForm.parseDateArrivoFromString(dataDiNascitaStringParam);

		if (!utilityForm.validateRegistaFormInput(nomeInputParam, cognomeInputParam, nickNameInputParam,
				dataDiNascitaStringParam)) {
			request.setAttribute("errorMessage", "Errori nella validazione");
			request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
			return;
		}

		Regista registaInstance;
		try {
			registaInstance = MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(idRegistaParam));
			registaInstance.setNome(nomeInputParam);
			registaInstance.setCognome(cognomeInputParam);
			registaInstance.setNickName(nickNameInputParam);
			registaInstance.setDataDiNascita(dataDiNascitaParsed);
			
			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);

			request.setAttribute("registi_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione di aggiornamento effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}
}
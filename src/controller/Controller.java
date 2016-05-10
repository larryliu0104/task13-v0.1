package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

//import controller.customer.ChangeCustomerPasswordAction;
//import controller.customer.CustomerBuyFundAction;

import util.Log;

public class Controller extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1561542386870494042L;
	private static final String TAG = "Controller";

	public void init() throws ServletException {

		Model model;
		try {
			//model = new Model(getServletConfig());

//			try {
//				if (model.getEmployeeDAO().getCount() == 0) {
//					createAdminAccount(model);
//				}
//			} catch (RollbackException e) {
//				e.printStackTrace();
//			} finally {
//				if (Transaction.isActive()) {
//					Transaction.rollback();
//				}
//			}

			Log.e("Controller", "init");

			Action.add(new InitializeBusInfoAction());
			Action.add(new NearbyAction());
			Action.add(new BusPredictionAction());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		Log.i("perform action ", action);

		HttpSession session = request.getSession(true);
		String nextPage = Action.perform(action, request);
		Log.i("next page ", nextPage);
		sendToNextPage(nextPage, request, response);

	}

	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}
		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}
		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
			d.forward(request, response);
			return;
		}
		if (nextPage.startsWith("http://")) {
			response.sendRedirect(nextPage);
			return;
		}

		Log.e(TAG, "next page is " + nextPage);
		// RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" +
		// nextPage);
		// d.forward(request, response);
		throw new ServletException(
				Controller.class.getName() + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");

	}
	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

}


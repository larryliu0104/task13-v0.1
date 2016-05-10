package model;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

//import org.genericdao.ConnectionPool;
//import org.genericdao.DAOException;

public class Model {
//		private CustomerDAO customerDAO;
//		private EmployeeDAO employeeDAO;
	    private HelloWorldDAO helloWorldDAO;

		private static final String TAG = "Model";

//		public Model(ServletConfig config) throws ServletException, DAOException {
//			String jdbcDriver = config.getInitParameter("jdbcDriverName");
//			String jdbcURL = config.getInitParameter("jdbcURL");
//			ConnectionPool pool = null;
//			if ("\\".equals(File.separator)) {
//				pool = new ConnectionPool(jdbcDriver, jdbcURL, "root", "");
//			} else {
//				pool = new ConnectionPool(jdbcDriver, jdbcURL);
//			}
//			helloWorldDAO = new HelloWorldDAO();
////			customerDAO = new CustomerDAO(pool, "Customer");
////			employeeDAO = new EmployeeDAO(pool, "Employee");
//
//		}
}

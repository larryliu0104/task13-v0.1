package controller;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import model.Model;

public class HelloWorldAction extends Action {
	private static final String ACTION_NAME = "helloworld.do";
	private static final String HELLO_WORLD_JSP_NAME = "helloworld.jsp";


	public HelloWorldAction(Model model) {
		
	}
	
	@Override
	public String getName() {
		return ACTION_NAME;
	}

	@Override
	public String perform(HttpServletRequest request) {
		return HELLO_WORLD_JSP_NAME;
	
	}
	
}

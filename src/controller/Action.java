package controller;

import util.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class Action {
	private static final String TAG = "ACTION";

	// rewrite getName and perform in detailed actions
	public abstract String getName();

	// Returns the name of the jsp used to render the output.
	public abstract String perform(HttpServletRequest request);

	// Class methods to manage dispatching to Actions
	private static Map<String, Action> hash = new HashMap<String, Action>();

	public static void add(Action a) {
		synchronized (hash) {
			if (hash.get(a.getName()) != null) {
				Log.e(TAG, "Action " + a.getName() + " exists");
			}
			hash.put(a.getName(), a);
			Log.i("Action", "add action " + a.getName());
		}
	}

	public static String perform(String name, HttpServletRequest request) {
		Action a;
		synchronized (hash) {
			a = hash.get(name);
		}
		if (a == null) {
			return null;
		}
		return a.perform(request);
	}
}

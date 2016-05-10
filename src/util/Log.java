package util;

public final class Log {

	/**
	 * @param tag
	 *          the tag
	 * @param message
	 *          the message Prints an error log message to the console.
	 * 
	 */
	public static void e(String tag, String message) {
		System.err.format("%s: %s%n", tag, message);
		System.err.flush();
	}

	/**
	 * @param tag
	 *          the tag
	 * @param message
	 *          the message
	 * @param throwable
	 *          the throwable Prints an error log message to the console, logging
	 *          the {@link Throwable} that caused it as well.
	 *
	 */
	public static void e(String tag, String message, Throwable throwable) {
		System.err.format("%s: %s%n", tag, message);
		System.err.flush();
		throwable.printStackTrace();
	}

	/**
	 * Prints an informational log message to the console.
	 *
	 * @param tag
	 *          the tag
	 * @param message
	 *          the message
	 */
	public static void i(String tag, String message) {
		System.out.format("%s: %s%n", tag, message);
		System.out.flush();
	}

	private Log() {
		
	}
}

package blue.endless.csd402;

import java.util.Map;

import blue.endless.csd402.module1.Module1_3;

/**
 * This class is just a little router that selects and runs a module based on a command-line argument. Please see the
 * module-specific folders for actual module code.
 */
public class App {
	public static final Map<String, Runnable> ASSIGNMENTS = Map.of(
			"module1", Module1_3::run
			);
	
	public static void main(String... args) {
		if (args.length != 1) {
			if (args.length == 0) {
				System.out.println("No assignment selected.");
			} else {
				System.out.println("Too many arguments. Only one assignment can be selected at a time.");
			}
			System.out.println("Usage: java -jar csd403.jar <assignment>");
			System.out.println("Example: java -jar csd403.jar module1");
			
			System.exit(-1);
		}
		
		// Assume if the user typed "1" they mean "module1"
		String assignmentName = args[0];
		try {
			Integer.parseInt(assignmentName);
			assignmentName = "module"+assignmentName;
		} catch (Throwable t) {}
		
		Runnable assignment = ASSIGNMENTS.get(assignmentName);
		if (assignment == null) {
			System.out.println("Didn't understand assignment \""+assignmentName+"\".");
			System.exit(-2);
		} else {
		
			System.out.println("Running "+assignmentName);
			System.out.println("==============================");
			assignment.run();
		}
	}
}

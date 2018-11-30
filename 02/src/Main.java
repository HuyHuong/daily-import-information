import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vn.vti.core02.controler.TransactionController;
import com.vn.vti.core02.controler.UpdateStatusController;

public class Main {
	final static String FUNCTION_LOAD_DATA = "load";
	final static String FUNCTION_UPDATE_STATUS = "check";
	final static Logger logger = Logger.getLogger(Main.class);
	final static String ERROR = "Invalid arguments, please try again";

	public static void main(String[] args) throws SQLException {
		TransactionController transaction = new TransactionController();
		UpdateStatusController updateStatus = new UpdateStatusController();
		if (args.length == 2) {
			if (args[0].equals(FUNCTION_LOAD_DATA)) {
				transaction.readFolder(args[1]);
				System.out.println("Done!");
			} else {
				System.out.println(ERROR);
			}
		} else if (args.length == 1) {
			if (args[0].equals(FUNCTION_UPDATE_STATUS)) {
				updateStatus.updateStatus();
				System.out.println("Done!");
			} else {
				System.out.println(ERROR);
			}
		} else {
			System.out.println(ERROR);
		}
	}
}

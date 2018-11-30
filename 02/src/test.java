import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.vn.vti.core02.model.DataUsers;
import com.vn.vti.core02.model.DateUtils;
import com.vn.vti.core02.model.ProcessFiles;
import com.vn.vti.core02.model.TransactionDao;

public class test {
	DataUsers user = new DataUsers();
	ProcessFiles processFile = new ProcessFiles();
	TransactionDao tranDao = new TransactionDao();
	DateUtils util = new DateUtils();

	@Test
	void testSetPhoneNumber() {
		String number = "123456789";
		user.setPhoneNumber(number);
		assertEquals(number, user.getPhoneNumber());
	}

	@Test
	void testSetAmount() {
		int amount = 1000;
		user.setAmount(amount);
		assertEquals(amount, user.getAmount());
	}

	@Test
	void testSetTimeTransaction() {
		String time = "2018-06-16 10-01-01";
		user.setTimeTransaction(util.convertTimeTransaction(time));
		assertNotNull(user.getTimeTransaction());
	}

	@Test
	public void setStatus() {
		int status = 0;
		user.setStatus(status);
		assertEquals(status, user.getStatus());
	}

	@Test
	public void testIsExisted() throws SQLException {
		String number = "84123456701";
		assertEquals(true, tranDao.isExisted(number));
	}

	@Test
	public void testIsNotExisted() throws SQLException {
		String number = null;
		assertEquals(false, tranDao.isExisted(number));
	}

	@Test
	void testReadDataNorm() throws SQLException {
		File file = new File("D\\02\\file1.csv");
		assertFalse(processFile.readData(file));
	}

	@Test
	public void readDataEmptyFileTest() throws SQLException {
		File file = new File("D:\\02\\test\\data4.csv");
		assertTrue(processFile.readData(file));
	}

	@Test
	public void readDataFileNotFoundTest() throws SQLException {
		File file = new File("D:\\02\\data.csv");
		assertFalse(processFile.readData(file));
	}

	@Test
	public void readDataFileErrorTest() throws SQLException {
		File file = new File("D:\\02\\test\\data5.csv");
		assertFalse(processFile.readData(file));
	}

	@Test
	public void mainClassLoadDataTest() throws SQLException {
		String args[] = { "load", "D:\\02\\testDB2" };
		Main.main(args);
	}

	@Test
	public void mainClassCheckDataTest() throws SQLException {
		String args[] = { "check" };
		Main.main(args);
	}
}

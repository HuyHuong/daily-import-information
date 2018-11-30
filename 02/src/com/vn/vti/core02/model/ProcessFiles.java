package com.vn.vti.core02.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ProcessFiles {

	private static Logger logger = Logger.getLogger(ProcessFiles.class);
	public static final String ERROR_FOLDER_PATH = "/home/huong/errorFileFolder/";
	public static final String SUCCESS_FOLDER_PATH = "/home/huong/successfulFileFolder/";
	public static final String ERROR_FOLDER = "Maybe folder is empty or can not allocate!";
	HistoryTransaction historyTransaction = new HistoryTransaction();
	UserTransaction userTran = new UserTransaction();
	TransactionDao tranDao = new TransactionDao();
	DateUtils util = new DateUtils();

	public void moveFile(File fileName, String targetPath) {
		File targetFolder = new File(targetPath);
		if (targetFolder.exists()) {
			String newFilePath = targetPath + fileName.getName();
			File movedFile = new File(newFilePath);
			if (movedFile.exists()) {
				movedFile.delete();
			}
			fileName.renameTo(new File(newFilePath));
			System.out.println(fileName + " moved");
		} else {
			logger.warn("unable to move file " + fileName.getName() + " to directory " + targetPath
					+ " -> target directory does not exist");
		}
	}

	public boolean readData(File fileName) throws SQLException {
		List<DataUsers> userList = new ArrayList<DataUsers>();
		DataUsers user = null;
		BufferedReader br = null;
		String in = null;
		boolean result = false;

		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((in = br.readLine()) != null) {
				String[] data = in.split(",");
				if (data == null || data.length != 3) {
					break;
				}
				user = new DataUsers();
				user.setPhoneNumber(data[0]);
				user.setAmount(Integer.parseInt(data[1]));
				user.setTimeTransaction(util.convertTimeTransaction(data[2]));
				user.setStatus(1);
				userList.add(user);
			}
			if (userList.size() > 2000) {
				System.out.println("File " + fileName + " too large!");
				return result;
			} else {
				historyTransaction.insertHistoryTransaction(userList);
				tranDao.checkUser(userList);
				result = true;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

	public void readFolder(String folderName) throws SQLException {
		Connection con = ConnectionDB.getConnection();
		File folder = new File(folderName);
		try {
			if (!folder.exists() || !folder.isDirectory()) {
				System.out.println(ERROR_FOLDER);
				return;
			}
			if (folder.listFiles().length > 30) {
				System.out.println("The files size is too large!");
				return;
			}
			for (File file : folder.listFiles()) {
				if (file.isFile()) {
					System.out.println("Already read file " + file.getName());
					if (readData(file)) {
						moveFile(file, SUCCESS_FOLDER_PATH);
					} else {
						moveFile(file, ERROR_FOLDER_PATH);
					}
				}
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
}

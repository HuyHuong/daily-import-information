package com.vn.vti.core02.controler;

import java.sql.SQLException;

import com.vn.vti.core02.model.ProcessFiles;

public class TransactionController {
	
	ProcessFiles processFile = new ProcessFiles();

	public void readFolder(String folderName) throws SQLException{
		processFile.readFolder(folderName);
	}
}

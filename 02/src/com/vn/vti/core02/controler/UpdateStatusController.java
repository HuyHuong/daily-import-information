package com.vn.vti.core02.controler;

import java.sql.SQLException;
import com.vn.vti.core02.model.UpdateStatusDao;

public class UpdateStatusController {
	
	UpdateStatusDao updateStatus = new UpdateStatusDao();
	
	public void updateStatus() throws SQLException{
		updateStatus.updateStatus();
	}
}

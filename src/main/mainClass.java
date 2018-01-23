package main;

import db.DBConnection;
import db.OracleConnection;
import delegator.Delegator;

public class mainClass {
	public static void main(String[] args) {
		
		
		Delegator delegator = Delegator.getInstance();
		
		delegator.DBConnector.initConnect();
		delegator.DBConnector.makeConnection();
		
		delegator.mainController.Main();
	}
}


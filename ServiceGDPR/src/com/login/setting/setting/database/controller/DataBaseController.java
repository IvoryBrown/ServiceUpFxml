package com.login.setting.setting.database.controller;

import com.login.setting.setting.database.filewrite.SettingDBFile;
import com.login.setting.setting.devicename.controller.SettingDeviceNameController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DataBaseController extends SettingDeviceNameController{
	@FXML
	private TextField urlTxt, nameTxt, passwordTxt;
	
	
	// SettingDBFile
	protected void setDBtextField() {
		SettingDBFile.setDataBaseOutput();
		urlTxt.setText(SettingDBFile.getDBOutput());
		nameTxt.setText(SettingDBFile.getNameOutput());
		passwordTxt.setText(SettingDBFile.getPasswordOutput());
	}
	@FXML
	private void saveDBButton() {
		if (urlTxt.getText().trim().isEmpty() || nameTxt.getText().trim().isEmpty()) {
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!!");
		} else {
			SettingDBFile.writeDB(urlTxt.getText(), nameTxt.getText(), passwordTxt.getText());
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setDBtextField();
		}
	}
	
}

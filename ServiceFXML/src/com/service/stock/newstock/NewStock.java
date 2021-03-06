package com.service.stock.newstock;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class NewStock implements Initializable {
	@FXML
	private TextField stockDeviceName, stockDeviceQuantity;
	@FXML
	private TextArea stockDeviceDescription;
	@FXML
	private DatePicker stockDeviceDate, stockDeviceSalesDate;

	private TrayNotification tray;

	private boolean setStockCheckTxt() {
		if (stockDeviceName.getText().trim().isEmpty()) {
			stockDeviceName.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceQuantity.getText().trim().isEmpty()) {
			stockDeviceQuantity.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceDescription.getText().trim().isEmpty()) {
			stockDeviceDescription.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceDate.getValue() == null) {
			stockDeviceDate.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceName.getText().trim().isEmpty() || stockDeviceQuantity.getText().trim().isEmpty()
				|| stockDeviceDescription.getText().trim().isEmpty() || stockDeviceDate.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	@FXML
	private void newDev(ActionEvent event) {
		if (setStockCheckTxt()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertStock = con.prepareStatement(
						"INSERT INTO raktar(eszkoznev, kelte, mennyiseg, raktaron, leiras)" + "values(?,?,?,?,?) ");
				insertStock.setString(1, stockDeviceName.getText());
				insertStock.setString(2, ((TextField) stockDeviceDate.getEditor()).getText());
				try {
					insertStock.setInt(3, Integer.parseInt(stockDeviceQuantity.getText()));
				} catch (NumberFormatException e) {
					tray = new TrayNotification("HIBA", "Nem megfelelő Szám!", NotificationType.ERROR);
					tray.showAndDismiss(Duration.seconds(2));
				}
				insertStock.setString(4, stockDeviceQuantity.getText());
				insertStock.setString(5, stockDeviceDescription.getText());
				insertStock.executeUpdate();
				tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stockDeviceDescription.setWrapText(true);
		stockDeviceDate.setValue(LocalDate.now());
	}

}

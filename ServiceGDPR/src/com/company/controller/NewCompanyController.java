package com.company.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.setting.database.DataBaseConnect;
import com.setting.identification.ClientIdentficationGenerator;
import com.setting.showinfo.ShowInfo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class NewCompanyController implements Initializable {
	private LocalDate localDate;
	@FXML
	private ComboBox<String> cmbClientInputCounty;
	@FXML
	private TextField txtClientInputClientName, txtClientInputZipCode, txtClientInputSettlement, txtClientInputAddress,
			txtClientInputEmail, txtClientInputMobil, txtClientCompany, txtClientCompanyMobil, txtClientCompanyEmail,
			txtClientCompanyPackard;
	@FXML
	private Label txtClientInputNumber;
	@FXML
	private TextArea txtClientInputComment;

	private final String COUNTRYCOUNTIES[] = { "Bács-Kiskun", "Baranya", "Békés", " Borsod-Abaúj-Zemplén", "Csongrád",
			" Fejér", " Győr-Moson-Sopron", "Hajdú-Bihar", "Heves", "Jász-Nagykun-Szolnok", " Komárom-Esztergom ",
			"Nógrád", "Pest", "Somogy", "Szabolcs-Szatmár-Bereg", "Tolna", "Vas", " Veszprém", "Zala" };

	private TrayNotification tray;

	private void setComponentAll() {
		txtClientInputComment.setWrapText(true);
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
	}

	private boolean companyInputBoolen() {
		if (txtClientInputClientName.getText().trim().isEmpty()) {
			txtClientInputClientName.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (cmbClientInputCounty.getValue() == null) {
			cmbClientInputCounty.setPromptText("Kérlek válasz!");
		}
		if (txtClientInputSettlement.getText().trim().isEmpty()) {
			txtClientInputSettlement.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputZipCode.getText().trim().isEmpty()) {
			txtClientInputZipCode.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputMobil.getText().trim().isEmpty()) {
			txtClientInputMobil.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputAddress.getText().trim().isEmpty()) {
			txtClientInputAddress.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientCompany.getText().trim().isEmpty()) {
			txtClientCompany.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientCompanyMobil.getText().trim().isEmpty()) {
			txtClientCompanyMobil.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (cmbClientInputCounty.getValue() == null || txtClientInputClientName.getText().trim().isEmpty()
				|| txtClientInputSettlement.getText().trim().isEmpty()
				|| txtClientInputZipCode.getText().trim().isEmpty() || txtClientInputAddress.getText().trim().isEmpty()
				|| txtClientInputMobil.getText().trim().isEmpty() || txtClientCompany.getText().trim().isEmpty()
				|| txtClientCompanyMobil.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean companyZipCodedBoolen() {
		try {
			if (Integer.valueOf(txtClientInputZipCode.getText()) >= 1000
					&& Integer.valueOf(txtClientInputZipCode.getText()) <= 9985) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	private void btnClientNewclient() {
		if (companyInputBoolen()) {
			if (companyZipCodedBoolen()) {
				try {
					localDate = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String formattedString = localDate.format(formatter);
					Connection con = DataBaseConnect.getConnection();
					PreparedStatement insertCompany = null;
					insertCompany = con
							.prepareStatement("INSERT INTO ugyfel_adatok(ugyfel_azonosito, cegnev, ugyfel_nev, megye,"
									+ "telepules, iranyitoszam, cim, ceg_telefon, ceg_email, ugyfel_email, ugyfel_telefon, csomag_tipus, ugyfel_megjegyzes, felvetel_datum)"
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
					txtClientInputNumber.setText(ClientIdentficationGenerator.random());
					insertCompany.setString(1, txtClientInputNumber.getText());
					insertCompany.setString(2, txtClientCompany.getText());
					insertCompany.setString(3, txtClientInputClientName.getText());
					insertCompany.setString(4, cmbClientInputCounty.getSelectionModel().getSelectedItem());
					insertCompany.setString(5, txtClientInputSettlement.getText());
					insertCompany.setInt(6, Integer.parseInt(txtClientInputZipCode.getText()));
					insertCompany.setString(7, txtClientInputAddress.getText());
					insertCompany.setString(8, txtClientCompanyMobil.getText());
					insertCompany.setString(9, txtClientCompanyEmail.getText());
					insertCompany.setString(10, txtClientInputEmail.getText());
					insertCompany.setString(11, txtClientInputMobil.getText());
					insertCompany.setString(12, txtClientCompanyPackard.getText());
					insertCompany.setString(13, txtClientInputComment.getText());
					insertCompany.setString(14, formattedString);
					insertCompany.executeUpdate();
					tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
					setCompanyInputText();
				} catch (SQLException ex) {
					new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
				}
			} else {
				tray = new TrayNotification("HIBA", "Nem megfelelő Irányítószám!", NotificationType.ERROR);
				tray.showAndDismiss(Duration.seconds(2));
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	private void setCompanyInputText() {
		txtClientInputNumber.setText(null);
		txtClientInputClientName.clear();
		cmbClientInputCounty.getSelectionModel().clearSelection();
		txtClientInputSettlement.clear();
		txtClientInputZipCode.clear();
		txtClientInputAddress.clear();
		txtClientInputEmail.clear();
		txtClientInputMobil.clear();
		txtClientInputComment.clear();
		txtClientCompany.clear();
		txtClientCompanyMobil.clear();
		txtClientCompanyEmail.clear();
		txtClientCompanyPackard.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComponentAll();

	}

}
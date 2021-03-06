package com.calendar.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.device.pojo.Device;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarDataBase {
	public ObservableList<String> calendarList = FXCollections.observableArrayList();

	public CalendarDataBase() {
		setCalendar();
	}

	public void setCalendar() {
		String SQLaDMINISTRATOR = null;
		SQLaDMINISTRATOR = " select `ugyfél_nev_gep`,`hatarido_datuma` from `gepadatok1` ";

		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				calendarList.add(stnRS.getString("hatarido_datuma"));
				calendarList.add(stnRS.getString("ugyfél_nev_gep"));
			}
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		} finally {
			try {
				if (pstStn != null) {
					pstStn.close();
				}
				if (stnRS != null) {
					stnRS.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public ArrayList<Device> getDeviceNameCalendar(String date) {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `gepadatok1` WHERE  hatarido_datuma ='" + date + "'";
		ArrayList<Device> device = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {

				Device actualDevice = new Device(rs.getString("id_gepadatok"), rs.getString("ugyfel_adatok_id_ugyfel"),
						rs.getString("eszkoz_azonosito"), rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"),
						rs.getString("eszkoz"), rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
						rs.getBoolean("garancia"), rs.getString("felvetel_helye"), rs.getString("javitas_helye"),
						rs.getString("allapot"), rs.getString("uj_gep"), rs.getString("ugyintezo"),
						rs.getString("prioritas"), rs.getString("jelszo"), rs.getString("hivatkozasi_szam"),
						rs.getString("tartozekok"), rs.getString("serules"), rs.getString("hiba_leirasa"),
						rs.getString("eszkoz_megjegyzes"), rs.getDate("vasarlasi_datuma"),
						rs.getDate("bejelentes_datuma"), rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"),
						rs.getString("adatmentes"), rs.getString("softver"), rs.getString("operacios_rendszer"),
						rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
						rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
						rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
						rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
						rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
						rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
				device.add(actualDevice);

			}
		} catch (SQLException e) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return device;
	}
}

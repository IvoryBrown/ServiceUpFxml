package com.service.device.fillteringdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.device.Device;
import com.service.device.DeviceClient;
import com.service.main.LoginController;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceFillteringDB {

	public ObservableList<String> administratorList = FXCollections.observableArrayList();
	public ObservableList<String> technikalIstratorList = FXCollections.observableArrayList();
	public ObservableList<String> dateLincList = FXCollections.observableArrayList();
	public ObservableList<String> calendarList = FXCollections.observableArrayList();
	private static String sql = null;

	public DeviceFillteringDB() {
		setGetDateLin();
		if (LoginController.setLogin !=null) {
			setGetAllAdministrator();
			setGetAllTechnikal();
			setCalendar();
			}
	}

	public static ArrayList<DeviceClient> getAllDeviceCompanyFiltering(String companyName) {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `ugyfel_adatok` JOIN `gepadatok1` ON id_ugyfel = ugyfel_adatok_id_ugyfel WHERE CONCAT"
					+ "(`" + "ceg_nev_gep" + "`) LIKE '%" + companyName + "%' AND" + "(`" + "allapot" + "`) LIKE '%"
					+ "Bevételezve" + "%'";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `ugyfel_adatok_exi` JOIN `gepadatok1_exi` ON id_ugyfel = ugyfel_adatok_id_ugyfel_exi WHERE CONCAT"
					+ "(`" + "ceg_nev_gep" + "`) LIKE '%" + companyName + "%' AND" + "(`" + "allapot" + "`) LIKE '%"
					+ "Bevételezve" + "%'";
		}
		ArrayList<DeviceClient> deviceClient = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceClient = new ArrayList<>();
			while (rs.next()) {
				DeviceClient actualDevice = new DeviceClient(rs.getString("ugyfel_azonosito"),
						rs.getString("telepules"), rs.getString("iranyitoszam"), rs.getString("cim"),
						rs.getString("ugyfel_telefon"), rs.getString("id_gepadatok"), rs.getString("eszkoz_azonosito"),
						rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
						rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
						rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
						rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
						rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
						rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"), rs.getDate("vasarlasi_datuma"),
						rs.getDate("bejelentes_datuma"), rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"),
						rs.getString("adatmentes"), rs.getString("softver"), rs.getString("operacios_rendszer"),
						rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
						rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
						rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
						rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
						rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
						rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
				deviceClient.add(actualDevice);
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return deviceClient;
	}

	public static ArrayList<DeviceClient> getAllDeviceClientFiltering(String clientName) {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `ugyfel_adatok` JOIN `gepadatok1` ON id_ugyfel = ugyfel_adatok_id_ugyfel WHERE CONCAT"
					+ "(`" + "ugyfel_nev" + "`) LIKE '%" + clientName + "%' AND" + "(`" + "allapot" + "`) LIKE '%"
					+ "Bevételezve" + "%'";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `ugyfel_adatok_exi` JOIN `gepadatok1_exi` ON id_ugyfel = ugyfel_adatok_id_ugyfel_exi WHERE CONCAT"
					+ "(`" + "ugyfel_nev" + "`) LIKE '%" + clientName + "%' AND" + "(`" + "allapot" + "`) LIKE '%"
					+ "Bevételezve" + "%'";
		}
		ArrayList<DeviceClient> deviceClient = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceClient = new ArrayList<>();
			while (rs.next()) {
				DeviceClient actualDevice = new DeviceClient(rs.getString("ugyfel_azonosito"),
						rs.getString("telepules"), rs.getString("iranyitoszam"), rs.getString("cim"),
						rs.getString("ugyfel_telefon"), rs.getString("id_gepadatok"), rs.getString("eszkoz_azonosito"),
						rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
						rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
						rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
						rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
						rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
						rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"), rs.getDate("vasarlasi_datuma"),
						rs.getDate("bejelentes_datuma"), rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"),
						rs.getString("adatmentes"), rs.getString("softver"), rs.getString("operacios_rendszer"),
						rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
						rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
						rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
						rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
						rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
						rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
				deviceClient.add(actualDevice);
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return deviceClient;
	}

	public static ArrayList<DeviceClient> getAllDeviceClient() {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `ugyfel_adatok` JOIN `gepadatok1` ON id_ugyfel = ugyfel_adatok_id_ugyfel WHERE CONCAT"
					+ "(`" + "allapot" + "`) LIKE '%" + "Bevételezve" + "%'";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `ugyfel_adatok_exi` JOIN `gepadatok1_exi` ON id_ugyfel = ugyfel_adatok_id_ugyfel_exi WHERE CONCAT"
					+ "(`" + "allapot" + "`) LIKE '%" + "Bevételezve" + "%'";
		}
		ArrayList<DeviceClient> deviceClient = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceClient = new ArrayList<>();
			while (rs.next()) {
				DeviceClient actualDevice = new DeviceClient(rs.getString("ugyfel_azonosito"),
						rs.getString("telepules"), rs.getString("iranyitoszam"), rs.getString("cim"),
						rs.getString("ugyfel_telefon"), rs.getString("id_gepadatok"), rs.getString("eszkoz_azonosito"),
						rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
						rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
						rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
						rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
						rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
						rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"), rs.getDate("vasarlasi_datuma"),
						rs.getDate("bejelentes_datuma"), rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"),
						rs.getString("adatmentes"), rs.getString("softver"), rs.getString("operacios_rendszer"),
						rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
						rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
						rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
						rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
						rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
						rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
				deviceClient.add(actualDevice);
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return deviceClient;
	}

	public static ArrayList<Device> getAllDevice() {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `gepadatok1`";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `gepadatok1_exi`";
		}
		ArrayList<Device> device = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				if (LoginController.setLogin.equals("Irisz")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
				if (LoginController.setLogin.equals("Exicom")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel_exi"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return device;
	}

	public static ArrayList<Device> getDeviceNameFilltering(String deviceName) {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `gepadatok1` WHERE CONCAT (`" + "ugyfél_nev_gep" + "`) LIKE '%" + deviceName + "%'";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `gepadatok1_exi` WHERE CONCAT (`" + "ugyfél_nev_gep" + "`) LIKE '%" + deviceName
					+ "%'";
		}
		ArrayList<Device> device = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				if (LoginController.setLogin.equals("Irisz")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
				if (LoginController.setLogin.equals("Exicom")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel_exi"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return device;
	}

	public static ArrayList<Device> getDeviceNameCalendar(String date) {
		Connection con = DataBaseConnect.getConnection();
		if (LoginController.setLogin.equals("Irisz")) {
			sql = "SELECT * FROM `gepadatok1` WHERE  hatarido_datuma ='" + date + "'";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			sql = "SELECT * FROM `gepadatok1_exi` WHERE  hatarido_datuma ='" + date + "'";
		}
		ArrayList<Device> device = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				if (LoginController.setLogin.equals("Irisz")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
				if (LoginController.setLogin.equals("Exicom")) {
					Device actualDevice = new Device(rs.getString("id_gepadatok"),
							rs.getString("ugyfel_adatok_id_ugyfel_exi"), rs.getString("eszkoz_azonosito"),
							rs.getString("ceg_nev_gep"), rs.getString("ugyfél_nev_gep"), rs.getString("eszkoz"),
							rs.getString("eszkoz_gyarto"), rs.getString("eszkoz_gyari_szama"),
							rs.getString("javitas_helye"), rs.getString("allapot"), rs.getString("uj_gep"),
							rs.getString("ugyintezo"), rs.getString("prioritas"), rs.getString("jelszo"),
							rs.getString("hivatkozasi_szam"), rs.getString("tartozekok"), rs.getString("serules"),
							rs.getString("hiba_leirasa"), rs.getString("eszkoz_megjegyzes"),
							rs.getDate("vasarlasi_datuma"), rs.getDate("bejelentes_datuma"),
							rs.getDate("hatarido_datuma"), rs.getDate("kiszallas_datuma"), rs.getString("adatmentes"),
							rs.getString("softver"), rs.getString("operacios_rendszer"),
							rs.getString("softver_megjegyzés"), rs.getBoolean("haz"), rs.getBoolean("tapegyseg"),
							rs.getBoolean("processzor"), rs.getBoolean("alaplap"), rs.getBoolean("memoria"),
							rs.getBoolean("videokartya"), rs.getBoolean("ssd"), rs.getBoolean("meghajto"),
							rs.getBoolean("hutoventilator"), rs.getBoolean("optikai_meghajto"),
							rs.getBoolean("bovitokartya"), rs.getBoolean("laptop"), rs.getDate("elkeszult_datuma"),
							rs.getString("hibajavitas_leirasa"), rs.getString("technikus"), rs.getString("statusz"));
					device.add(actualDevice);
				}
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return device;
	}

	public void setGetAllAdministrator() {
		String SQLaDMINISTRATOR = null;
		if (LoginController.setLogin.equals("Irisz")) {
			SQLaDMINISTRATOR = " select * from dolgozok ";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			SQLaDMINISTRATOR = " select * from dolgozok_exi ";
		}
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				administratorList.add(stnRS.getString("ugyintezo_neve"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void setCalendar() {
		String SQLaDMINISTRATOR = null;
		if (LoginController.setLogin.equals("Irisz")) {
			SQLaDMINISTRATOR = " select `ugyfél_nev_gep`,`hatarido_datuma` from `gepadatok1` ";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			SQLaDMINISTRATOR = " select `ugyfél_nev_gep`,`hatarido_datuma` from `gepadatok1_exi` ";
		}
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
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void setGetAllTechnikal() {
		String SQLaDMINISTRATOR = null;
		if (LoginController.setLogin.equals("Irisz")) {
			SQLaDMINISTRATOR = " select * from dolgozok ";
		}
		if (LoginController.setLogin.equals("Exicom")) {
			SQLaDMINISTRATOR = " select * from dolgozok_exi ";
		}
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				technikalIstratorList.add(stnRS.getString("technikus_neve"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void setGetDateLin() {
		String SQLaDMINISTRATOR = " select * from date ";
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				dateLincList.add(stnRS.getString("date"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void updateDevice(Device device) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlDevice = null;
			if (LoginController.setLogin.equals("Irisz")) {
				sqlDevice = "UPDATE `gepadatok1` set eszkoz = ?, eszkoz_gyarto = ?, javitas_helye = ?, allapot = ?, tartozekok = ?,"
						+ "hiba_leirasa = ?, eszkoz_megjegyzes = ?, bejelentes_datuma = ?, hatarido_datuma = ?, kiszallas_datuma = ?, softver_megjegyzés = ?,"
						+ " elkeszult_datuma = ?, hibajavitas_leirasa = ?, technikus = ?, statusz = ?"
						+ " WHERE id_gepadatok = ?";
			}
			if (LoginController.setLogin.equals("Exicom")) {
				sqlDevice = "UPDATE `gepadatok1_exi` set eszkoz = ?, eszkoz_gyarto = ?, javitas_helye = ?, allapot = ?, tartozekok = ?,"
						+ "hiba_leirasa = ?, eszkoz_megjegyzes = ?, bejelentes_datuma = ?, hatarido_datuma = ?, kiszallas_datuma = ?, softver_megjegyzés = ?,"
						+ " elkeszult_datuma = ?, hibajavitas_leirasa = ?, technikus = ?, statusz = ?"
						+ " WHERE id_gepadatok = ?";
			}
			pr = conn.prepareStatement(sqlDevice);
			pr.setString(1, device.getDeviceName());
			pr.setString(2, device.getDeviceManufacturer());
			pr.setString(3, device.getDeviceRepairLocation());
			pr.setString(4, device.getDeviceStatus());
			pr.setString(5, device.getDeviceAccesssory());
			pr.setString(6, device.getDeviceErrorDescription());
			pr.setString(7, device.getDeviceComment());
			pr.setObject(8, device.getDeviceAddDate());
			pr.setObject(9, device.getDeviceEndDate());
			pr.setObject(10, device.getDeviceDeliveryDate());
			pr.setString(11, device.getDeviceSoftverComment());
			pr.setObject(12, device.getDeviceCompletedDate());
			pr.setString(13, device.getDeviceErrorCorrection());
			pr.setString(14, device.getDeviceTechnicalPerson());
			pr.setString(15, device.getDeviceStatusz());
			pr.setString(16, device.getDeviceID());

			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (pr != null) {
					pr.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void updateDeviceClient(DeviceClient device) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlDevice = null;
			if (LoginController.setLogin.equals("Irisz")) {
				sqlDevice = "UPDATE `gepadatok1` set allapot = ?" + " WHERE id_gepadatok = ?";
			}
			if (LoginController.setLogin.equals("Exicom")) {
				sqlDevice = "UPDATE `gepadatok1_exi` set allapot = ?" + " WHERE id_gepadatok = ?";
			}
			pr = conn.prepareStatement(sqlDevice);
			pr.setString(1, device.getDeviceStatus());
			pr.setString(2, device.getDeviceID());
			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (pr != null) {
					pr.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void removeContact(Device device) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = null;
			if (LoginController.setLogin.equals("Irisz")) {
				sql = "DELETE FROM `gepadatok1` WHERE id_gepadatok = ?";
			}
			if (LoginController.setLogin.equals("Exicom")) {
				sql = "DELETE FROM `gepadatok1_exi` WHERE id_gepadatok = ?";
			}
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(device.getDeviceID()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a eszköz törlésekor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}
}
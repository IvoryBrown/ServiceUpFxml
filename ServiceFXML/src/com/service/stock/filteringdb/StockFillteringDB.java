package com.service.stock.filteringdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;
import com.service.stock.Stock;

public class StockFillteringDB {
	static Connection con = DataBaseConnect.getConnection();
	Connection conn = DataBaseConnect.getConnection();

	public static ArrayList<Stock> getAllStock() {
		String sql = "SELECT * FROM `raktar`";
		ArrayList<Stock> device = null;

		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Stock actualStock = new Stock(rs.getInt("category_id"), rs.getString("eszkoznev"),
						rs.getString("kelte"), rs.getString("eladas_kelte"), rs.getInt("mennyiseg"),
						rs.getString("leiras"));
				device.add(actualStock);
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

	public static ArrayList<Stock> getStockNameFiltering(String stockDeviceNameFilteringTxt) {

		String sql = "SELECT * FROM `raktar` WHERE CONCAT (`" + "eszkoznev" + "`) LIKE '%" + stockDeviceNameFilteringTxt
				+ "%'";
		ArrayList<Stock> device = null;

		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Stock actualStock = new Stock(rs.getInt("category_id"), rs.getString("eszkoznev"),
						rs.getString("kelte"), rs.getString("eladas_kelte"), rs.getInt("mennyiseg"),
						rs.getString("leiras"));
				device.add(actualStock);
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

	public void updateStock(Stock stock) {
		try {
			String sqlStock = "UPDATE `raktar` set eszkoznev = ?, kelte = ?, eladas_kelte = ?, mennyiseg = ?, leiras = ? WHERE category_id = ?";
			java.sql.PreparedStatement pr = conn.prepareStatement(sqlStock);
			pr.setString(1, stock.getStockDeviceName());
			pr.setString(2, stock.getStockDeviceDate());
			pr.setString(3, stock.getStockDeviceSalesDate());
			pr.setString(4, stock.getStockDeviceQuantity());
			pr.setString(5, stock.getStockDeviceDescription());
			pr.setInt(6, stock.getStockDeviceId());
			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

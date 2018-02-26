package com.service.setting.menutreeitem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuTreeItemController {
	@FXML
	protected StackPane menuPane;

	@FXML
	protected Pane homePane, clientPane;
	@FXML
	protected AnchorPane stockPane, clientTablePane, newDevicePane;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_CONTACTS = "Ügyfelek";
	private final String MENU_CLIENT = "Magánszemély";
	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_DEVICENEW = "Új Eszköz";
	private final String MENU_DEVICETABLE = "Eszköz Tábla";
	private final String MENU_CLIENT_TABLE = "Ügyfelek Tábla";
	private final String MENU_STOCK = "Raktár";
	private final String MENU_EXIT = "Kilépés";

	@SuppressWarnings("unchecked")
	protected void setMenuData() {

		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		TreeView<String> treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_HOME);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_CONTACTS);
		nodeItemB.setExpanded(false);
		TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_CLIENT);
		TreeItem<String> nodeItemB2 = new TreeItem<>(MENU_CLIENT_TABLE);

		TreeItem<String> nodeItemD = new TreeItem<>(MENU_DEVICE);
		nodeItemD.setExpanded(false);
		TreeItem<String> nodeItemD1 = new TreeItem<>(MENU_DEVICENEW);
		TreeItem<String> nodeItemD2 = new TreeItem<>(MENU_DEVICETABLE);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<>(MENU_EXIT);
		nodeItemD.getChildren().addAll(nodeItemD1, nodeItemD2);
		nodeItemB.getChildren().addAll(nodeItemB1, nodeItemB2);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemD, nodeItemC, nodeItemE);

		menuPane.getChildren().add(treeView);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();

				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_HOME:
						homePane.setVisible(true);
						clientPane.setVisible(false);
						newDevicePane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_CLIENT:
						homePane.setVisible(false);
						clientPane.setVisible(true);
						newDevicePane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_DEVICENEW:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						newDevicePane.setVisible(true);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_CLIENT_TABLE:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						newDevicePane.setVisible(false);
						clientTablePane.setVisible(true);
						stockPane.setVisible(false);
						break;
					case MENU_STOCK:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						newDevicePane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(true);
						break;
					case MENU_EXIT:
						System.exit(0);
						break;
					}
				}
			}
		});

	}

}

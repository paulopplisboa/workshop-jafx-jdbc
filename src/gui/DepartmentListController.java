package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import gui.listeners.DataChangeListener;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener {

	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;

	@FXML
	private TableColumn<Department, String> tableColumnName;

	@FXML
	private TableColumn<Department, Department> tableColumnEDIT;

	@FXML
	private Button btNew;

	// para carregar os Departamentos
	private ObservableList<Department> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		// ao clicar no botão cria um Departamento vazio sem dados
		Department obj = new Department();
		// System.out.println("onBtNewAction");
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	// Podedia escrever (private DepartmentService service = new
	// DepartmenteService), isto seria um acoplamento
	// forte, uma boa prática é criar os métodos get e set para acessár a esse
	// atributo
	public void setDepartmenteService(DepartmentService service) {
		this.service = service;
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	// método responsavel por acessár ao serviço e carregar a tableView
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
		initEditButtons();
	}

	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(new DepartmentService());
			// inscrever-se para receber o evento
			controller.subscribeDataChangeListener(this);

			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			// define que é o stage pai desta janela
			dialogStage.initOwner(parentStage);
			// função para travar a janela, enquanto não for fechada não se pode acessar à
			// anterior
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();

	}

	// metodo para editar Departamento
	private void initEditButtons() { 
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue())); 
		tableColumnEDIT.setCellFactory(param -> new TableCell<Department, Department>() {
			
			private final Button button = new Button("edit"); 
			
			@Override 
			protected void updateItem(Department obj, boolean empty) { 
				super.updateItem(obj, empty); 
				if (obj == null) {                 
					setGraphic(null); 
					return; 
					}             
				setGraphic(button); 
				button.setOnAction( 
				event -> createDialogForm( 
						obj, "/gui/DepartmentForm.fxml",Utils.currentStage(event)));      
				}     }); 
		}
}









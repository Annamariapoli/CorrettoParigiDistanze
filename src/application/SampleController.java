package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import bean.Fermata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> comboStart;

    @FXML
    private ComboBox<Fermata> comboEnd;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcola(ActionEvent event) {
    	txtResult.clear();
    	Fermata start = comboStart.getValue();
    	Fermata end = comboEnd.getValue();
    	if(start ==null || end ==null){
    		txtResult.appendText("Seleziona due fermate\n");
    		return;
    	}
    	if(start.equals(end)){
    		txtResult.appendText("Seleziona due fermate diverse!\n");
    		return;
    	}
    	
    	m.buildGraph();
    	txtResult.appendText("Il cammino tra le due fermate è : \n");
    	
    	List<Fermata> cammino = m.getCamminoMinimo(start, end);
    	if(cammino.isEmpty()){
    		txtResult.appendText("Non sono raggiungibili!\n");
    		return ;
    	}
    	for(Fermata f : cammino){
    		txtResult.appendText(f+" \n");
    	}

    	int numFermate = cammino.size();
    	txtResult.appendText("Il numero di fermate è :  "+numFermate+" \n");
    	
    	double lunghezzaCammino = m.getCamminoMinimoLunghezza(start, end);
    	txtResult.appendText("la lunghezza del cammino é : " +lunghezzaCammino+" \n ");

   
    	
    	
    	
    	
    	List<DefaultWeightedEdge> camminoArchi = m.getCamminoMinimoArchi(start, end);
    	txtResult.appendText("Gli archi sono :   \n");
    	
    	if(cammino.isEmpty()){
    		txtResult.appendText("Non sono raggiungibili!\n");
    		return ;
    	}
    	for(DefaultWeightedEdge f : camminoArchi){
    		txtResult.appendText(f+" \n");
    	}
    }

    @FXML
    void initialize() {
        assert comboStart != null : "fx:id=\"comboStart\" was not injected: check your FXML file 'Sample.fxml'.";
        assert comboEnd != null : "fx:id=\"comboEnd\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboStart.getItems().addAll(m.getAllFermate());
        comboEnd.getItems().addAll(m.getAllFermate());
    }
}

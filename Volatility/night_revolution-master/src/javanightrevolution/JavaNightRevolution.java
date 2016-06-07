/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javanightrevolution;

import javafx.scene.layout.GridPane;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.converter.FloatStringConverter;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author admin
 */
public class JavaNightRevolution extends Application {
    private Desktop desktop = Desktop.getDesktop();
    private List<Float> raw_data = new ArrayList<Float>();
    private List<Double> result;

    @Override
    public void start(Stage primaryStage) {
        final FileChooser fileChooser = new FileChooser();

        Label label1 = new Label("Result: ");
        TextField resultField = new TextField ();
        HBox resultBox = new HBox();
        resultBox.getChildren().addAll(label1, resultField);
        resultBox.setSpacing(10);
        
        Button openFile = new Button();
        openFile.setText("Open file");
        openFile.setOnAction(new EventHandler<ActionEvent>() {
  
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    openFile(file);
                    result = calculate();
                    String formatted_string = new String("[");
                    for (final Double element : result) {
                       formatted_string += Double.toString(element);
                    }
                    formatted_string += "]";
                    resultField.setText(formatted_string);
                }
            }
        });
        
        Button writeFile = new Button();
        writeFile.setText("Write file");
        writeFile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save calculated result");
                File file = fileChooser.showSaveDialog(primaryStage);
//                writeStringToFile(file, resultField.getText());
                writeArrayToFile(file, result);
            }
        });
        
        
        StackPane root = new StackPane();
        
        // show buttons 
        root.getChildren().add(resultBox);
        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(openFile, 0, 5);
        GridPane.setConstraints(writeFile, 1, 5);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openFile, writeFile);
        root.getChildren().add(inputGridPane);

        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hey you! =)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void openFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;

            while ((line = br.readLine()) != null) {
                float value = Float.parseFloat(line);
                raw_data.add(value);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private List<Double> calculate() {
        List<Double> result = new ArrayList();
        double sum = 0;
        double wind = 25; //change
        List<Double> pr = new ArrayList();
        int n = raw_data.size();
        for(n = 0; n < raw_data.size() - 1; n++) {
           pr.add(Math.log(raw_data.get(n + 1)) - Math.log(raw_data.get(n))); //прибутковості      
        } 
        for (int k = 0; k <= raw_data.size() - wind - 1; k++){
            for (int j = k; j <= k + wind - 1; j++) {
                sum += pr.get(j);
            }
            result.add(1 / wind * Math.abs(sum));
        } 
        return result;
    }
    
    
    private void writeStringToFile(File file, String result) {
        if (file != null) {
            try {
                FileWriter wr = new FileWriter(file);
                wr.write(result);
                wr.close();
                desktop.open(file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    private void writeArrayToFile(File file, List<Double> resultList) {
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                for (Double item : resultList) {
                    writer.write(Double.toString(item));
                    writer.write(System.lineSeparator());
                }
                writer.close();
                desktop.open(file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }  
}

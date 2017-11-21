package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	private static Canvas canvas = new Canvas(300, 300);
	private static GraphicsContext gc = canvas.getGraphicsContext2D();
	private static TextField inputValue = new TextField();
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			primaryStage.setTitle("H-Tree Fractal");

			root.setCenter(canvas);
			HBox input = new HBox();
			Button display = new Button("Display");
			display.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					try{
					gc.clearRect(0, 0, 300, 300);
					String input = inputValue.getText();
					int cycles = Integer.parseInt(input);
					this.drawH(cycles, 150, 150, 50);
					} catch(Exception exception){
						System.out.println("ERROR: Invalid Input");
					}
				}

				public void drawH(int cycles, int centerX, int centerY, int length){
					if(cycles < 0){
						return;
					}					
					gc.setLineWidth(1.0);
					gc.strokeLine(centerX - length, centerY - length, centerX - length, centerY + length);
					gc.strokeLine(centerX + length, centerY - length, centerX + length, centerY + length);
					gc.strokeLine(centerX - length, centerY, centerX + length, centerY);
					if(cycles == 0){
						return;
					} 
						drawH(cycles - 1, centerX - length, centerY + length, length / 2);
						drawH(cycles - 1, centerX - length, centerY - length, length / 2);
						drawH(cycles - 1, centerX + length, centerY + length, length / 2);
						drawH(cycles - 1, centerX + length, centerY - length, length / 2);
				}
			});

			input.getChildren().addAll(new Label("Enter an order: "), inputValue, display);

			root.setBottom(input);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


}

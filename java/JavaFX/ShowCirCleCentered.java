import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowCirCleCentered extends Application{
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage){
		// Create a pane to hold the circle 
		Pane pane = new Pane(); 
		
		// Create a circle and set its properties 
		Circle circle = new Circle(); 
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.setRadius(50);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHEAT);
		pane.getChildren().add(circle); 	// Add circle to the pane 
		
		// Create a scene and place it in the stage 
		Scene scene = new Scene(pane, 200, 200); 
		primaryStage.setTitle("ShowCircleCentered"); 	// Set the stage title 
		primaryStage.setScene(scene);
		primaryStage.show(); 	
	}
	/*				Not Start-up 
	public static  void main(String args[]) {
		Application.launch(args);		
	}
	*/
}

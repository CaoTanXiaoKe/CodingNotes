import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage; 
import javafx.scene.layout.StackPane;

public class ButtonInPane extends Application{
	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage){
		// Create a scene and place a button in the scene 
		StackPane pane = new StackPane(); 
		pane.getChildren().add(new Button("OK")); 
		Scene scene = new Scene(pane, 200, 50); 
		primaryStage.setTitle("Button in a pane"); 	// Set the stage title 
		primaryStage.setScene(scene); 			// Place the scene in the stage 
		primaryStage.setResizable(false);       // Define can't change the size of the stage
		primaryStage.show();
	}
	/*	Not start-up 
	public static void main(String args[] ){
		Application.launch(args);
	}
	*/
}

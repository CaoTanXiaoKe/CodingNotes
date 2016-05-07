import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage; 

public class MultipleStageDemo extends Application{
	@Override // Override the start method in the scene 
	public void start(Stage primaryStage){
		// Create a scene and place a button in the scene 
		Scene scene = new Scene(new Button("OK"), 200, 250); 
		primaryStage.setTitle("MyJavaFX"); // Set the stage title 
		primaryStage.setScene(scene);	// place the scene in the stage 
		primaryStage.show();	// Display the stage 
		
		Stage stage = new Stage(); 		// Create a new stage 
		stage.setTitle("SeSecondcond Stage");	// Set the stage title 
		// Set a scene with a button in the stage 
		stage.setScene(new Scene(new Button("New Stage"), 100, 100));
		stage.setResizable(false); //  Define the scene size can't change 
		stage.show();			// Display the stage 
	}
	
	/*   Not start-up 
	public static void  main(String [] args) {
		Application.launch(args);

	}
	*/
}

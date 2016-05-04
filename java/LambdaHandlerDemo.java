import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage; 

/**
 * Title: 使用lambda表达式简化事件处理。<br>
 * Description: 使用lambda表达式， 简化事件处理的内部类的写法。<br>
 * @author ChenWenKe
 * @version 1.0 
 */
public class LambdaHandlerDemo extends Application{
	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage){
		// Hold two buttons in an HBox 
		HBox hBox = new HBox(); 
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		Button btNew = new Button("New"); 
		Button btOpen = new Button("Open"); 
		Button btSave = new Button("Save"); 
		Button btPrint = new Button("Print"); 
		hBox.getChildren().addAll(btNew, btOpen, btPrint, btSave); 
		
		// Create and register the handler 
		btNew.setOnAction((ActionEvent e) -> {
			System.out.println("Process New");
		});
		
		btOpen.setOnAction((e) -> {
			System.out.println("Process Open");
		});
		
		btSave.setOnAction( e -> {
			System.out.println("Process Save");
		});
		
		btPrint.setOnAction(e -> System.out.println("Process Print")); 
		
		// Create a scene and place it in the stage 
		Scene scene = new Scene(hBox, 300, 50); 
		primaryStage.setTitle("LambdaHandlerDemo"); 	// Set title 
		primaryStage.setScene(scene);
		primaryStage.show();  	// Display the stage 
	}
	public  static void  main(String args[]) {
		Application.launch(args);
	}
}

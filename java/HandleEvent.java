import com.sun.scenario.effect.impl.prism.PrMergePeer;

import javafx.application.Application;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Title: 事件处理器简单应用。 <br>
 * Description: 两个按钮事件的简单示例。<br> 
 * @author ChenWenKe
 * @version 1.0
 */
public class HandleEvent extends Application{
	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage){
		// Create a pane and set its properties
		HBox pane = new HBox(10); 
		pane.setAlignment(Pos.CENTER);
		Button btOK = new Button("OK"); 
		Button btCancel = new Button("Cancel"); 
		OKHandlerClass handler1 = new OKHandlerClass();
		btOK.setOnAction(handler1);
		
		CancelHandlerClass handler2 = new CancelHandlerClass(); 
		btCancel.setOnAction(handler2);
		pane.getChildren().addAll(btOK, btCancel); 
		// Create a scene and place it in the stage 
		Scene scene = new Scene(pane, 250, 250); 
		primaryStage.setTitle("HandleEvent"); 	// Set the stage title 
		primaryStage.setScene(scene); 		// Place the stage in the stage 
		primaryStage.show(); 			// Display the stage 
	}
	public static  void main(String args[]) {
		Application.launch(args);
	}
}

class OKHandlerClass implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e){
		System.out.println("OK button clicked");
	}
}

class CancelHandlerClass implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e){
		System.out.println("Cancel button clicked");
	}
}
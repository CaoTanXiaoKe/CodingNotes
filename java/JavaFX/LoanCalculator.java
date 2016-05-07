import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage; 

public class LoanCalculator extends Application{
	private TextField tfAnnualInterestRate = new TextField(); 
	private TextField tfNumberOfYears = new TextField(); 
	private TextField tfLoanAmont = new TextField(); 
	private TextField tfMonthlyPayment = new TextField(); 
	private TextField tfTotalPayment = new TextField(); 
	private Button btCalculate = new Button("Calculate"); 
	
	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage){
		// Create UI 
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
		gridPane.add(tfAnnualInterestRate, 1, 0);
		gridPane.add(new Label("Number of Years:"), 0, 1);
		gridPane.add(tfNumberOfYears, 1, 1);
		gridPane.add(new Label("Loan Amount:"), 0, 2);
		gridPane.add(tfLoanAmont, 1, 2);
		gridPane.add(new Label("Monthly Payment:"),	0, 3);
		gridPane.add(tfMonthlyPayment, 1, 3);
		gridPane.add(new Label("Total Payment:"), 0, 4);
		gridPane.add(tfTotalPayment, 1, 4);
		
		gridPane.add(btCalculate, 1, 5);
		
		// Set properties for UI 
		gridPane.setAlignment(Pos.CENTER);
		tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
		tfLoanAmont.setAlignment(Pos.BOTTOM_RIGHT);
		tfMonthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
		tfNumberOfYears.setAlignment(Pos.BASELINE_RIGHT);
		tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
		tfMonthlyPayment.setEditable(false);
		tfTotalPayment.setEditable(false);
		GridPane.setHalignment(btCalculate, HPos.RIGHT);
		
		// Process events 
		btCalculate.setOnAction(e -> calculateLoanPayment());
		
		// Create a scene and place it in the stage 
		Scene scene = new Scene(gridPane, 400, 250); 
		primaryStage.setTitle("LoanCalculator"); 	// Set title 
		primaryStage.setScene(scene); 	// Place the scene in the stage 
		primaryStage.show(); 	// Display the stage 
	}
	
	private void calculateLoanPayment(){
		// Get values from text fields 
		double interest = 
				Double.parseDouble(tfAnnualInterestRate.getText()); 
		int year = Integer.parseInt(tfNumberOfYears.getText()); 
		double loanAmount = 
				Double.parseDouble(tfLoanAmont.getText()); 
		
		// Create a loan object. 
		Loan loan = new Loan(interest, year, loanAmount); 
		
		// Display monthly payment and total payment 
		tfMonthlyPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));   // 注意这两个语句的顺序不能对调
		tfTotalPayment.setText(String.format("$%.2f", loan.getTotalPayment()));
	}
	public static void  main(String args[]) {
		Application.launch(args);
	}
}

class Loan{
	private double interest; 
	private int year; 
	private double loanAmount; 
	private double MonthlyPayment; 
	private double TotalPayment; 
	
	public Loan(double interest, int year, double loanAmount){
		this.interest = interest; 
		this.year = year; 
		this.loanAmount = loanAmount; 
	}
	
	public double getTotalPayment() {
		TotalPayment = getMonthlyPayment() * year * 12; 
		return TotalPayment;
	}
	
	public double getMonthlyPayment() {
		double monthlyInterestRate = interest / 1200; 
		MonthlyPayment = loanAmount * monthlyInterestRate / (1 - 
				(1 / Math.pow(1 + monthlyInterestRate, year * 12))); 
		return MonthlyPayment;
	}
	

}
import java.awt.PaintContext;
import java.util.Calendar; 
import java.util.GregorianCalendar;

import javax.print.attribute.standard.RequestingUserName;

import com.sun.prism.paint.RadialGradient;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane{
	private int hour; 
	private int minute; 
	private int second; 
	
	// Clock pane's width and height 
	private double w = 250, h = 250; 
	
	/** Construct a clock with the current time*/
	public ClockPane(){
		setCurrentTime(); 
	}
	
	/** Construct a clock with specified hour, minute, and second */ 
	public ClockPane(int hour, int minute, int second){
		this.hour = hour; 
		this.minute = minute; 
		this.second = second; 
		paintClock(); 
	}
	
	/** Return hour */ 
	public int getHour(){
		return hour; 
	}
	
	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour; 
		paintClock(); 
	}
	
	/** Return minute */
	public int getMinute() {
		return minute; 
	}
	
	/** Set a new minute */
	public void setMinute(int minute){
		this.minute = minute; 
		paintClock(); 
	}
	
	/** Return second */
	public int getSecond(){
		return second; 
	}
	
	/** Set a new second */
	public void setSecond(int second) {
		this.second = second; 
		paintClock(); 
	}
	/** Return clock pane's width */
	public double getW(){
		return w;
	}
	
	/** Set clock pane's width */ 
	public void setW(double w) {
		this.w = w; 
		paintClock(); 
	}
	
	/** Return clock pane's height */ 
	public double getH(){
		return h;
	}
	
	/** Set clock pane's height */
	public void setH(int h) {
		this.h = h; 
		paintClock();
	}
	
	/** Set the current time for the clock */
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar(); 
		
		// Set current hour, minute and second 
		this.hour = calendar.get(Calendar.HOUR_OF_DAY); 
		this.minute = calendar.get(Calendar.MINUTE); 
		this.second = calendar.get(Calendar.SECOND); 
		paintClock(); 		// Repaint the clock 
	}
	
	/** Paint the clock */ 
	protected void paintClock() {
		// Initialize clock parameters 
		double clockRadius = Math.min(w, h) * 0.8 * 0.5; // get a reasonable radius 
		double centerX = w / 2; 
		double centerY = h / 2; 
		
		// Draw circle 
		Circle circle = new Circle(centerX, centerY, clockRadius); 
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		
		// 待完成 -----> 需要用 Text 数组实现 12 个数字 的位置的刻画。  
		Text t12 =new Text(centerX - 5, centerY - clockRadius + 12, "12"); 
		Text t9 = new Text(centerX - clockRadius + 3, centerY + 5, "9"); 
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3"); 
		Text t6 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
		
		// 待完成 ----> 需要用 Line 数组实现分钟刻度与时针刻度的刻画。 
		// 画分钟刻度
		double plength = clockRadius * 0.95; 
		
		//for(int i = 0; i < 60; i++){
		int i = 5; 
			double plengthX = centerX + plength * 
					Math.sin(i * (2 * Math.PI / 360 )); 
			double plengthY = centerY + plength * 
					Math.cos(i * (2 * Math.PI / 360));
			double startX = centerX + clockRadius * 
					Math.sin(i * (2 * Math.PI / 360)); 
			double startY = centerY + clockRadius * 
					Math.cos(i * (2 * Math.PI / 360)); 
			Line pLine = new Line(startX, startY, plengthX, plengthY); 
			pLine.setStroke(Color.BLACK);
		//	getChildren().add(pLine); 
		//}
		
		// Draw second hand 
		double sLength = clockRadius * 0.8; 
		double secondX = centerX + sLength * 
				Math.sin(second * ( 2 * Math.PI / 60)); 
		double secondY = centerY - sLength * 
				Math.cos(second * (2 * Math.PI / 60)); 
		Line sLine = new Line(centerX, centerY, secondX, secondY); 
		sLine.setStroke(Color.RED);
		
		// Draw minute hand
		double mLength = clockRadius * 0.65; 
		double minuteX = centerX + mLength * 
				Math.sin(minute * (2 * Math.PI / 60)); 
		double minuteY = centerY + mLength * 
				Math.cos(minute * (2 * Math.PI / 60)); 
		Line mLine = new Line(centerX, centerY, minuteX, minuteY);
		mLine.setStroke(Color.BLUE);
		
		// Draw hour hand
		double hLength = clockRadius * 0.5; 
		double hourX = centerX + hLength * 
				Math.sin(hour * (2 * Math.PI / 12)); 
		double hourY = centerY + hLength * 
				Math.cos(hour * (2 * Math.PI / 12)); 
		Line hLine = new Line(centerX, centerY, hourX, hourY); 
		hLine.setStroke(Color.GREEN);
		
		getChildren().clear();
		getChildren().add(circle); 
		getChildren().add(t12); 
		getChildren().add(t9); 
		getChildren().add(t6); 
		getChildren().add(t3); 
		getChildren().add(hLine); 
		getChildren().add(mLine); 
		getChildren().add(sLine);
		//for()
		//getChildren().addAll(circle, t12, t9, t3, t6, sLine, mLine, hLine, pLine); 
	}
}

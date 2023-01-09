package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;

public class JavaFX_2D_Shapes extends Application {
	private Group CreateLine() {
		int lx = 10, ly = 10, rx = 110, ry = 60;
	
		Line l1 = new Line(lx, ly, rx, ly);
		Line l2 = new Line(rx, ly, rx, ry);
		Line l3 = new Line(rx, ry, lx, ry);
		Line l4 = new Line(lx, ry, lx, ly);
		
		Group rootGroup = new Group();
		rootGroup.getChildren().add(l1);
		rootGroup.getChildren().add(l2);
		rootGroup.getChildren().add(l3);
		rootGroup.getChildren().add(l4);
		
		return rootGroup;
	}
	
	private Group CreateRectangle() {
		int width = 100, height = 60, arcWidth = 20, arcHeight = 20;
		
		Rectangle rectangle = new Rectangle(20, 20, width, height);
		rectangle.setFill(Color.GREEN);
//		rectangle.setArcWidth(arcWidth);
//		rectangle.setArcHeight(arcHeight);
		
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(10);
		rectangle.setSmooth(true);
		rectangle.setStrokeLineJoin(StrokeLineJoin.BEVEL);
		
		Group rootGroup = new Group();
		rootGroup.getChildren().add(rectangle);
		
		return rootGroup;
	}
	
	private Group CreateEllipse()
	{
		int cx = 200, cy = 200, hRad = 50, vRad = 100;
		
		Ellipse ellipse = new Ellipse(cx, cy, hRad, vRad);
		ellipse.setStroke(Color.WHITE);
		ellipse.setStrokeWidth(20);
		
		Group group = new Group();
		group.getChildren().add(ellipse);
		
		return group;
	}
	
	private Group CreateArc() {
		int posX = 100, posY = 150, hRad = 50, vRad = 100, sAngle = 20, arcAngle = 70;
		
		Arc arc = new Arc();
		
		arc.setCenterX(posX);
		arc.setCenterY(posY);
		arc.setRadiusX(hRad);
		arc.setRadiusY(vRad);
		arc.setStartAngle(sAngle);
		arc.setLength(arcAngle);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.BLUE);
		
		arc.setStroke(Color.WHITE);
		arc.setStrokeWidth(2);
		
		Group group = new Group();
		group.getChildren().add(arc);
		
		return group;
	}
	
	private Group CreateCircle()
	{
		int cx = 100, cy = 100, rad = 50;
		
		Circle circle = new Circle(cx, cy, rad);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(10);
		
		Group group = new Group();
		group.getChildren().add(circle);
		
		return group;
	}
	
	
	private Group CreatePolygon()
	{
		Double[] verticesArray = {
				10.0, 10.0,
				200.0, 100.0,
				100.0, 200.0
		};
		
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(verticesArray);
		polygon.setFill(Color.WHITE);
		polygon.setStroke(Color.BLACK);
		polygon.setStrokeWidth(10);
		
		Group group = new Group();
		group.getChildren().add(polygon);
		
		return group;
	}
	
	
	private Group CreateLinearGradiant()
	{
		Stop[] stops = new Stop[] {
				new Stop(0, Color.BLUE),
				new Stop(1, Color.PURPLE)
		};
		
		LinearGradient linearGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
		
		Rectangle rectangle = new Rectangle(0, 0, 200, 200);
		rectangle.setFill(linearGradient);
		
		Group group = new Group();
		group.getChildren().add(rectangle);
		
		return group;
	}
	
	private Group CreateRadialGradiant()
	{
		RadialGradient radialGradient = new RadialGradient(
				0, 
				0.1, 
				200, 
				200, 
				200, 
				false, 
				CycleMethod.NO_CYCLE, 
				new Stop(0, Color.BLUE), 
				new Stop(1, Color.PURPLE)
		);
		
		Circle circle = new Circle(100, 100, 200);
		circle.setFill(radialGradient);
		
		Group group = new Group();
		group.getChildren().add(circle);
		
		return group;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Group root = CreateLine();
//		Group root = CreateRectangle();
//		Group root = CreateEllipse();
//		Group root = CreateArc();
//		Group root = CreateCircle();
//		Group root = CreatePolygon();
//		Group root = CreateLinearGradiant();
		Group root = CreateRadialGradiant();

		Scene scene = new Scene(root, 400, 400, Color.RED);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("2D Shapes Demonstrastration");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

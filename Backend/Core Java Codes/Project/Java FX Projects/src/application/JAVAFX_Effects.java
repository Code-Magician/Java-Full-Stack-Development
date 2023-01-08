package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JAVAFX_Effects extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 700);
		scene.setFill(Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing JavaFx Effects");
		
//		ColorAdjustEffect(scene);
//		BlendEffect(scene);
//		BloomEffect(scene);
//		GlowEffect(scene);
//		BlurEffects(scene);
//		ReflectionEffect(scene);
//		SepiaToneEffect(scene);
//		ShadowEffects(scene);
		LightingEffects(scene);
		 
		primaryStage.show();
	}
	
	private void LightingEffects(Scene scene) {
		Image img1 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		Image img2 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view1 = new ImageView(img1);
		ImageView view2 = new ImageView(img2);
		view1.setX(100); view2.setX(600);
		view1.setY(10); view2.setY(10);
		
		 Text t1 = new Text();
		 Text t2 = new Text();
		 t1.setText("Image With Effects");	t2.setText("Image Without Effects");
		 t1.setX(100); t2.setX(600);
		 t1.setY(650); t2.setY(650);
		 
//		 Distant Light
//		 Light.Distant light = new Light.Distant();
//		 light.setAzimuth(.9);
//		 light.setColor(Color.BLUE);
		 
//		 Spot Light
//		 Light.Spot light = new Light.Spot();
//		 light.setPointsAtX(0);	light.setPointsAtY(0);	light.setPointsAtZ(-50);
//		 light.setSpecularExponent(.1);
		 
//		 Point Light
		 Light.Point light = new Light.Point();
		 light.setX(0);
		 light.setY(0);
		 light.setZ(-100);
		
//		Simple Lighting Effect  
		 Lighting effect = new Lighting(light);
		 effect.setSurfaceScale(5);
        
        view1.setEffect(effect);
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(view1, view2, t1, t2);
	}
	
	private void ShadowEffects(Scene scene) {
		Image img1 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		Image img2 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view1 = new ImageView(img1);
		ImageView view2 = new ImageView(img2);
		view1.setX(100); view2.setX(600);
		view1.setY(10); view2.setY(10);
		
		Text t1 = new Text();
		Text t2 = new Text();
		t1.setText("Image With Effects");	t2.setText("Image Without Effects");
		t1.setX(100); t2.setX(600);
		t1.setY(650); t2.setY(650);
		
//		Shadow Effect
//		Shadow effect = new Shadow();
//		effect.setBlurType(BlurType.GAUSSIAN);
//		effect.setColor(Color.BLACK);
//		effect.setRadius(100);
//		effect.setHeight(300);
//		effect.setWidth(200);
		
//		Drop Shadow Effect
//		DropShadow effect = new DropShadow();
//		effect.setBlurType(BlurType.GAUSSIAN);
//		effect.setWidth(200); 	effect.setHeight(100);
//		effect.setOffsetX(10);	effect.setOffsetY(10);
//		effect.setRadius(10);
//		effect.setColor(Color.GRAY);
//		effect.setSpread(.4);
		
//		InnerShadow Effect
		InnerShadow effect = new InnerShadow();
		effect.setHeight(100);	effect.setWidth(200);
		effect.setOffsetX(10);	effect.setOffsetY(10);
		effect.setRadius(10);
		effect.setColor(Color.RED);
		effect.setBlurType(BlurType.GAUSSIAN);
		effect.setChoke(.9);
        
        view1.setEffect(effect);
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(view1, view2, t1, t2);
	}
	
	private void SepiaToneEffect(Scene scene)
	{
		Image img1 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		Image img2 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view1 = new ImageView(img1);
		ImageView view2 = new ImageView(img2);
		view1.setX(100); view2.setX(600);
		view1.setY(10); view2.setY(10);
		
		 Text t1 = new Text();
		 Text t2 = new Text();
		 t1.setText("Image With Effects");	t2.setText("Image Without Effects");
		 t1.setX(100); t2.setX(600);
		 t1.setY(650); t2.setY(650);
		
		 SepiaTone effect = new SepiaTone();
		 effect.setLevel(0.5);
        
		 view1.setEffect(effect);
        
		 Group rootGroup = (Group)scene.getRoot();
		 rootGroup.getChildren().addAll(view1, view2, t1, t2);
	}
	
	private void ReflectionEffect(Scene scene) {
		Text text = new Text();  
        text.setFont(Font.font("calibri",FontWeight.BLACK,FontPosture.REGULAR,20));  
        text.setText("Welcome to JavaTPoint");  
        text.setX(90);  
        text.setY(90); 
        
        Reflection ref = new Reflection();  
        ref.setBottomOpacity(1);  
        ref.setFraction(.6);  
        ref.setTopOffset(5);  
        ref.setTopOpacity(0); 
        
        text.setEffect(ref); 
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(text);
	}
	
	private void BlurEffects(Scene scene) {
		Text text = new Text();  
        text.setText("Welcome to JavaTpoint");  
        text.setX(100);  
        text.setY(100);  
        text.setFont(Font.font("Calibri",FontWeight.BLACK,FontPosture.ITALIC,20));  
        
//		Box Blur
//        BoxBlur effect = new BoxBlur();
//        effect.setHeight(5);
//        effect.setWidth(2);
//        effect.setIterations(1);
        
//      Gaussian Blur
//        GaussianBlur effect = new GaussianBlur();
//        effect.setRadius(5);
//        
        
//        Motion Blur
        MotionBlur effect = new MotionBlur();
        effect.setAngle(45);
        effect.setRadius(5);
        
        text.setEffect(effect);
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(text);
	}
	
	private void GlowEffect(Scene scene) {
		Image img1 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		Image img2 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view1 = new ImageView(img1);
		ImageView view2 = new ImageView(img2);
		view1.setX(100); view2.setX(600);
		view1.setY(10); view2.setY(10);
		
		 Text t1 = new Text();
		 Text t2 = new Text();
		 t1.setText("Image With Effects");	t2.setText("Image Without Effects");
		 t1.setX(100); t2.setX(600);
		 t1.setY(650); t2.setY(650);
		
		Glow effect = new Glow();
		effect.setLevel(10);
        
        view1.setEffect(effect);
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(view1, view2, t1, t2);
	}
	
	private void BloomEffect(Scene scene) {
		Rectangle rect1= new Rectangle(60,50,150,200);  
	    Rectangle rect2 = new Rectangle(325,50,150,200);  
	    rect1.setFill(Color.GREEN);  
	    rect1.setStroke(Color.BLACK);  
	    rect1.setStrokeWidth(5);  
	    rect2.setFill(Color.GREEN);  
	    rect2.setStroke(Color.BLACK);  
	    rect2.setStrokeWidth(5);  
	    
	    Text text1 = new Text();  
	    Text text2 = new Text();  
	    text1.setText("Effected shape");  
	    text2.setText("Original shape");  
	    text1.setX(65);  
	    text1.setY(300);  
	    text2.setX(335);  
	    text2.setY(300); 
	    
	    Bloom bloom = new Bloom();
	    bloom.setThreshold(0.1);
	    
	    rect1.setEffect(bloom);
	    
	    Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(rect1, rect2, text1, text2);
	}
	
	private void BlendEffect(Scene scene) {
		Circle circle = new Circle(150,200,120);         
		circle.setFill(Color.BLUE);
		
		Blend blend = new Blend();    
		ColorInput color = new ColorInput(70, 20, 160, 150, Color.LIMEGREEN);  
		blend.setTopInput(color);  
		blend.setMode(BlendMode.COLOR_DODGE); 
		
		circle.setEffect(blend); 
		
		Group rootGroup = (Group)scene.getRoot();
	    rootGroup.getChildren().addAll(circle);
	}
	
	private void ColorAdjustEffect(Scene scene) {
		Image img1 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		Image img2 = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view1 = new ImageView(img1);
		ImageView view2 = new ImageView(img2);
		view1.setX(100); view2.setX(600);
		view1.setY(10); view2.setY(10);
		
		 Text t1 = new Text();
		 Text t2 = new Text();
		 t1.setText("Image With Effects");	t2.setText("Image Without Effects");
		 t1.setX(100); t2.setX(600);
		 t1.setY(650); t2.setY(650);
		
		ColorAdjust effect = new ColorAdjust();
		effect.setBrightness(0.2); // setting the brightness of the color.   
        effect.setContrast(0.1); // setting the contrast of the color  
        effect.setHue(0.3); // setting the hue of the color  
        effect.setSaturation(0.45); // setting the hue of the color.  
        
        view1.setEffect(effect);
        
        Group rootGroup = (Group)scene.getRoot();
        rootGroup.getChildren().addAll(view1, view2, t1, t2);
	}
}


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Hw3_RonOhayon extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage stage2 = new Stage();
		stage2.setTitle("hw3");
		// set colors
		String[] colorsName = new String[5];
		colorsName[0] = "red";
		colorsName[1] = "pink";
		colorsName[2] = "gold";
		colorsName[3] = "green";
		colorsName[4] = "purple";

		Color[] colors = new Color[5];
		colors[0] = Color.RED;
		colors[1] = Color.HOTPINK;
		colors[2] = Color.GOLD;
		colors[3] = Color.GREEN;
		colors[4] = Color.PURPLE;

		// set the panes
		BorderPane mainPane = new BorderPane();
		BorderPane bottomPane = new BorderPane();
		StackPane leftPane = new StackPane();
		StackPane rightPane = new StackPane();
		Pane setPane = new Pane();
		Pane centerPane = new Pane();

		// set the comboBoxs
		ObservableList<String> setColor = FXCollections.observableArrayList(colorsName);
		ComboBox<String> color = new ComboBox<>(setColor);
		ComboBox<String> frame = new ComboBox<>(setColor);
		color.setPromptText(colorsName[0]);
		frame.setPromptText(colorsName[0]);
		StackPane.setMargin(color, new Insets(45, 0, 0, 0));
		StackPane.setMargin(frame, new Insets(45, 0, 0, 0));
		// set circle
		Circle cir = new Circle(50);
		cir.setCenterX(100);
		cir.setCenterY(100);
		cir.setStrokeWidth(5);
		setCirclefill(0, cir, colors);
		setCircleframe(0, cir, colors);
		color.setOnAction(e -> setCirclefill(setColor.indexOf(color.getValue()), cir, colors));
		frame.setOnAction(e -> setCircleframe(setColor.indexOf(frame.getValue()), cir, colors));
		// set rectangle
		Rectangle rec1 = new Rectangle(110, 50);
		rec1.setStrokeWidth(1);
		rec1.setStroke(Color.DARKGREY);
		rec1.setFill(Color.TRANSPARENT);
		Rectangle rec2 = new Rectangle(110, 50);
		rec2.setStrokeWidth(1);
		rec2.setStroke(Color.DARKGREY);
		rec2.setFill(Color.TRANSPARENT);
		StackPane.setMargin(rec1, new Insets(45, 0, 0, 0));
		StackPane.setMargin(rec2, new Insets(45, 0, 0, 0));
		// set animation
		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(7), e -> {
			if (cir.getTranslateY() == 0) {
				cir.setTranslateY(300);
			} else {
				cir.setTranslateY(0);
			}
		}));
		animation.setRate(5);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		// set labels
		Label label1 = new Label("Circle Color");
		label1.setStyle("-fx-font-weight:bold; -fx-background-color:#F4F4F4;");
		Label label2 = new Label("Frame Color");
		label2.setStyle("-fx-font-weight:bold; -fx-background-color:#F4F4F4;");
		Text textIn = new Text();
		StackPane.setMargin(label1, new Insets(0, 0, 10, 0));
		StackPane.setMargin(label2, new Insets(0, 0, 10, 0));

		// set scene2
		bottomPane.setPadding(new Insets(10, 165, 30, 165));

		leftPane.getChildren().addAll(rec1, color, label1);
		rightPane.getChildren().addAll(rec2, frame, label2);

		bottomPane.setLeft(leftPane);
		bottomPane.setRight(rightPane);
		setPane.getChildren().add(textIn);

		// set action
		centerPane.setOnMouseMoved(e -> {
			textIn.setX(e.getX() + 30);
			textIn.setY(e.getY());
			textIn.setText(checkR(e.getX(), e.getY(), cir));

		});
		centerPane.setOnMouseDragged(e -> {
			textIn.setX(e.getX() + 30);
			textIn.setY(e.getY());
			textIn.setText(checkR(e.getX(), e.getY(), cir));
		});

		centerPane.getChildren().addAll(cir, setPane);

		mainPane.setBottom(bottomPane);
		mainPane.setCenter(centerPane);

		Scene scene2 = new Scene(mainPane, 600, 600);

		// cancel the X button
		stage2.setScene(scene2);
		stage2.setOnCloseRequest(e -> {
			e.consume();
		});

		Button b1 = new Button("Off/On Point M");
		b1.setOnAction(e -> {
			if (stage2.isShowing())
				stage2.close();
			else
				stage2.show();
		});
		// close the program
		primaryStage.setOnCloseRequest(e -> {
			stage2.close();
		});

		// set scene 1
		Pane layout2 = new StackPane();
		layout2.getChildren().add(b1);

		Scene scene1 = new Scene(layout2, 300, 70);

		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	public void setCirclefill(int index, Circle cir, Color[] colors) {
		cir.setFill(colors[index]);

	}

	public void setCircleframe(int index, Circle cir, Color[] colors) {
		cir.setStroke(colors[index]);

	}

	public void moveball(Circle cir) {
		if (cir.getCenterY() == 100) {
			cir.setCenterY(400);
		}
		if (cir.getCenterY() == 400) {
			cir.setCenterY(100);
		}

	}

	public String checkR(double x, double y, Circle cir) {
		if (y >= 500) {
			return " ";
		}
		// distance formula
		double d = Math
				.sqrt(Math.pow((x - (cir.getTranslateX() + 100)), 2) + Math.pow((y - (cir.getTranslateY() + 100)), 2));
		// pitagoras
		double p = Math.pow(d, 2) + Math.pow(cir.getRadius(), 2);

		if (p <= 5000) {
			return "the Mouse is in the circle ";
		} else {
			return "the Mouse is not in the circle ";
		}

	}
}
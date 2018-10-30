import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToe extends Application{

	private static int block = 3;
	private char FirstPlayer = 'X';
	private Cell[][] cell = new Cell[3][3];
	private Label statusMesg = new Label("X Turn");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		for (int i = 0; i < block; i++) {
			for (int j = 0; j < block; j++) {
				cell[i][j] = new Cell();
				pane.add(cell[i][j], i, j);
			}			
		}
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(statusMesg);
		
		Scene scene = new Scene(borderPane, 450, 300);
		primaryStage.setTitle("58070501043");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public boolean isBoardFull() {
		for (int i = 0; i < block; i++) {
			for (int j = 0; j < block; j++) {
				if (cell[i][j].getPlayer() == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	public boolean isWon(char player) {
		for (int i = 0; i < block; i++) {
			if(cell[i][0].getPlayer()==player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer()==player){
				return true;
			}
		}
		for (int i = 0; i < block; i++) {
			if(cell[0][i].getPlayer()==player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer()==player){
				return true;
			}
		}
		if(cell[0][0].getPlayer()==player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer()==player){
			return true;
		}
		if(cell[0][2].getPlayer()==player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer()==player){
			return true;
		}
		return false;
	}
	public class Cell extends Pane{
		private char player = ' ';
		public Cell() {
			// TODO Auto-generated constructor stub
			setStyle("-fx-border-color : black");
			this.setPrefSize(300, 300);
			this.setOnMouseClicked(e->handleClick());
		}
		private void handleClick() {
			// TODO Auto-generated method stub
			if(player == ' '&& FirstPlayer != ' '){
				setPlayer(FirstPlayer);
				if (isWon(FirstPlayer)) {
					statusMesg.setText(FirstPlayer+ " Won!");
					FirstPlayer=' ';
				}
				else if (isBoardFull()) {
					statusMesg.setText("Draw!");
					FirstPlayer=' ';
				}
				else {
					FirstPlayer = (FirstPlayer == 'O')?'X':'O';
					statusMesg.setText(FirstPlayer+" Turn !");
				}
			}
		}
		public char getPlayer() {
			return player;
		}
		public void setPlayer(char c) {
			player = c;
			if (player == 'X') {
				Line line1 = new Line(10,10,this.getWidth()-10,this.getHeight()-10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));
				
				Line line2 = new Line(10,this.getHeight()-10,this.getWidth()-10,10);
				line2.endXProperty().bind(this.widthProperty().subtract(10));
				line2.startYProperty().bind(this.heightProperty().subtract(10));
				
				getChildren().addAll(line1,line2);
			}else if (player == 'O') {
				Ellipse ellipse = new Ellipse(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2-10,this.getHeight()/2-10);
				ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				getChildren().add(ellipse);
			}
		}
	}


}

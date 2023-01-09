package application.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController implements Initializable{
	@FXML private MediaView mediaView;
	@FXML private Button playButton;
	@FXML private Button pauseButton;
	@FXML private Button reseButton;

	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		file = new File("E:\\MyFolder\\VID_20220121004006.mp4");
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		mediaView.setMediaPlayer(mediaPlayer);
	}

	public void PlayButton() {
		mediaPlayer.play();
	}
	
	public void PauseButton() {
		mediaPlayer.pause();
	}
	
	public void ResetButton() {
		
		if(mediaPlayer.getStatus() != MediaPlayer.Status.READY )
			mediaPlayer.seek(Duration.seconds(0.00));
	}
}

package application;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import application.combatFlow.combatControl;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	private Stage loadingStage;
	// music tracks
	
	String combatMusic = getClass().getResource("/Music/DD1RuinsTheme.mp3").toExternalForm();
	Media mediaPath = new Media(combatMusic);
	String menuMusic = getClass().getResource("/Music/MainMenu.mp3").toExternalForm();
	Media mediaPath2 = new Media(menuMusic);
	String shopMusic = getClass().getResource("/Music/ShopTheme.mp3").toExternalForm();
	Media mediaPath3 = new Media(shopMusic);
	String gameOver =  getClass().getResource("/Music/gameOver.mp3").toExternalForm();
	Media mediaPath4 = new Media(gameOver);
	int count = 0; // For use in combat
	int round = 0;
	int fightsWon = 0;
	
	Font KingArthurLegend = Font.loadFont(getClass().getResourceAsStream("/fonts/KingArthurLegend.ttf"), 40);
	Font Ubuntu = Font.loadFont(getClass().getResourceAsStream("/fonts/UbuntuRegular.ttf"), 40);
	Font DwarvenAxe = Font.loadFont(getClass().getResourceAsStream("/fonts/DwarvenAxe.ttf"), 40);
	@Override
	public void start(Stage primaryStage) {
//		setupLoadingStage(primaryStage);
		initialization(primaryStage);
//		shop(primaryStage);
//		gameOver(primaryStage);
	}

	private void initialization(Stage primaryStage) { // this scene is used specifically so that we can make our application full screen.
	    Image cursorImage = new Image("GUIAssets/cursor.png");

	    Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
	    ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
	    ImageView logoWhite = new ImageView(new Image("applicationImagesBackgrounds/endlessMountainLogoWhite.png"));
	    ImageView logoBlack = new ImageView(new Image("applicationImagesBackgrounds/endlessMountainLogoBlack.png"));
	    
	    Button play = new Button("Enter Game");
	    Text enterGameText = new Text ("Enter Game");
	    Text titlewhite = new Text ("ENDLESS MOUNTAIN \n   OF MONSTERS");
	    Text titleblack = new Text ("ENDLESS MOUNTAIN \n   OF MONSTERS");
	    
	    titleblack.setFont(DwarvenAxe);
	    titlewhite.setFont(DwarvenAxe);
	    titlewhite.setFill(Color.web("#d5d5d5"));
	    titleblack.setFill(Color.web("#000000"));
	    
	    enterGameText.setFont(DwarvenAxe);
	    enterGameText.setFill(Color.web("#d5d5d5"));
	    play.setOnAction(e -> {homePage(primaryStage);});
//        FadeUtils.transitionBetweenStages(primaryStage, loadingStage, () -> homePage(primaryStage));});
	    // VBox 1 for first set of buttons
	    VBox playbutton = new VBox(10);
	    playbutton.getChildren().addAll(play);

	    titleblack.setLayoutX(865);
	    titleblack.setLayoutY(650); 
	    titleblack.setScaleX(2.55);
	    titleblack.setScaleY(2.6);
	    
	    titlewhite.setLayoutX(865);
	    titlewhite.setLayoutY(650); 
	    titlewhite.setScaleX(2.5);
	    titlewhite.setScaleY(2.5);
	    
	    logoBlack.setLayoutX(465);
	    logoBlack.setLayoutY(-200); 
	    logoBlack.setScaleX(.77);
	    logoBlack.setScaleY(.77);
	    
	    logoWhite.setLayoutX(465);
	    logoWhite.setLayoutY(-200); 
	    logoWhite.setScaleX(.75);
	    logoWhite.setScaleY(.75);
	    
	    enterGame.setLayoutX(310);
	    enterGame.setLayoutY(635); 
	    enterGame.setScaleX(.5);
	    enterGame.setScaleY(.8);
	    enterGame.setRotate(90);
	    enterGame.setMouseTransparent(true);
	    
	    enterGameText.setLayoutX(910);
	    enterGameText.setLayoutY(975); 
	    enterGameText.setScaleX(2);
	    enterGameText.setScaleY(2);
	    enterGameText.setMouseTransparent(true);
	    
	    playbutton.setSpacing(10);
	    playbutton.setPadding(new Insets(20));
	    playbutton.setLayoutX(768); // Adjust X for play button
	    playbutton.setLayoutY(880); // Adjust Y for play button
	    playbutton.setOpacity(0);
	    
	    playbutton.setOnMouseEntered(e -> {enterGameText.setFill(Color.web("#d10000"));});
	    playbutton.setOnMouseExited(e -> {enterGameText.setFill(Color.web("#d5d5d5"));});

	    Image image = new Image("applicationImagesBackgrounds/EndlessMountain16x9.png");
	    BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    
	    Pane root = new Pane();
	    root.getChildren().addAll(logoBlack);
	    root.getChildren().addAll(logoWhite);
	    root.getChildren().addAll(titleblack);
	    root.getChildren().addAll(titlewhite);
	    root.getChildren().addAll(enterGame);
	    root.getChildren().addAll(playbutton); // Add both button lists
	    root.getChildren().addAll(enterGameText);
	    
	    root.setBackground(new Background(backgroundImage));
	    // Set button styles and sizes for both sets of buttons
	    playbutton.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.12));});
	    Scene scene = new Scene(root, 1920, 1080); // Screen size
	    // Ensure the stage stays in fullscreen mode when the scene changes
	    primaryStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
	    primaryStage.setMaximized(true);});
	    scene.setCursor(customCursor);
	    primaryStage.setTitle("Endless Mountain of Monsters");
	    primaryStage.initStyle(StageStyle.UNDECORATED); // Set only once, before showing the stage
	    primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	private void homePage(Stage primaryStage) {
	    Image cursorImage = new Image("GUIAssets/cursor.png");
	    ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
	    ImageView logoWhite = new ImageView(new Image("applicationImagesBackgrounds/endlessMountainLogoWhite.png"));
	    ImageView logoBlack = new ImageView(new Image("applicationImagesBackgrounds/endlessMountainLogoBlack.png"));
	    
	    
	    Cursor customCursor = Cursor.cursor(cursorImage.getUrl());

	    Media media = new Media(menuMusic);
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
	    mediaPlayer.play(); // music player

	    // First set of buttons
	    Button play = new Button("Begin New Journey");
	    Button tutorial = new Button("Tutorial");
//	    Button unlocks = new Button("Unlocks");
//	    Button stats = new Button("Stats");
	    Button credits = new Button("Credits");
	    Button quit = new Button("Exit Game");
	    Button shop = new Button("Shop");
	    Button gameOver = new Button ("Game Over Sample");
	    Button goldEarned = new Button ("Gold Earned Sample");
	    
	    Text enterGameText = new Text ("Begin New Journey");
	    Text titlewhite = new Text ("ENDLESS MOUNTAIN \n   OF MONSTERS");
	    Text titleblack = new Text ("ENDLESS MOUNTAIN \n   OF MONSTERS");
	    
	    titleblack.setFont(DwarvenAxe);
	    titlewhite.setFont(DwarvenAxe);
	    titlewhite.setFill(Color.web("#d5d5d5"));
	    titleblack.setFill(Color.web("#000000"));
	    enterGameText.setFont(DwarvenAxe);
	    enterGameText.setFill(Color.web("#d5d5d5"));
	    
	    titleblack.setLayoutX(865);
	    titleblack.setLayoutY(650); 
	    titleblack.setScaleX(2.55);
	    titleblack.setScaleY(2.6);
	    
	    titlewhite.setLayoutX(865);
	    titlewhite.setLayoutY(650); 
	    titlewhite.setScaleX(2.5);
	    titlewhite.setScaleY(2.5);
	    
	    logoBlack.setLayoutX(465);
	    logoBlack.setLayoutY(-200); 
	    logoBlack.setScaleX(.77);
	    logoBlack.setScaleY(.77);
	    
	    logoWhite.setLayoutX(465);
	    logoWhite.setLayoutY(-200); 
	    logoWhite.setScaleX(.75);
	    logoWhite.setScaleY(.75);
	    
	    enterGame.setLayoutX(310);
	    enterGame.setLayoutY(635); 
	    enterGame.setScaleX(.5);
	    enterGame.setScaleY(.8);
	    enterGame.setRotate(90);
	    enterGame.setMouseTransparent(true);
//	    enterGame.setVisible(false);
	    
	    enterGameText.setLayoutX(855);
	    enterGameText.setLayoutY(975); 
	    enterGameText.setScaleX(1.4);
	    enterGameText.setScaleY(2);
	    enterGameText.setMouseTransparent(true);
	    
	    
	    
	    play.setOnMouseEntered(e -> {enterGameText.setFill(Color.web("#d10000"));});
	    play.setOnMouseExited(e -> {enterGameText.setFill(Color.web("#d5d5d5"));});
	    play.setOnAction(e -> {
	        try {
				battleScene(primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        mediaPlayer.stop();
	    });

	    tutorial.setOnAction(e -> {
	        textTutorial(primaryStage);
	        mediaPlayer.stop();
	    });
//	    unlocks.setOnAction(e -> {
//	        unlocks(primaryStage);
//	        mediaPlayer.stop();
//	    });
//	    stats.setOnAction(e -> {
//	        stats(primaryStage);
//	        mediaPlayer.stop();
//	    });
	    credits.setOnAction(e -> {
	        credits(primaryStage);
	        mediaPlayer.stop();
	    });
	    quit.setOnAction(e -> {
	        Platform.exit();
	        mediaPlayer.stop();
	    });
	    shop.setOnAction(e -> {
	        shop(primaryStage);
	        mediaPlayer.stop();
	    });
	    gameOver.setOnAction(e -> {
	        gameOver(primaryStage);
	        mediaPlayer.stop();
	    });

	    goldEarned.setOnAction(e -> {
	    	goldEarned(primaryStage);
	        mediaPlayer.stop();
	    });
	    
	    // VBox 1 for first set of buttons
	    VBox playbutton = new VBox(10);
	    playbutton.getChildren().addAll(play);
	    playbutton.setSpacing(10);
	    playbutton.setPadding(new Insets(20));
	    playbutton.setLayoutX(768); // Adjust X for play button
	    playbutton.setLayoutY(900); // Adjust Y for play button
	    playbutton.setScaleX(1.05);
	    playbutton.setScaleY(1.6);
	    playbutton.setOpacity(0);

	    // VBox 2 for second set of buttons
	    VBox buttonBox2 = new VBox(10);
	    buttonBox2.getChildren().addAll(tutorial, credits, quit); // unlocks, stats,
	    buttonBox2.setSpacing(10);
	    buttonBox2.setPadding(new Insets(20));
	    buttonBox2.setLayoutX(7); // Adjust X for second VBox
	    buttonBox2.setLayoutY(880); // Adjust Y for second VBox

	    VBox shopTesting = new VBox(10);
	    shopTesting.getChildren().addAll(shop, gameOver, goldEarned);
	    shopTesting.setSpacing(10);
	    shopTesting.setPadding(new Insets(20));
	    shopTesting.setLayoutX(10); // Adjust X for second VBox
	    shopTesting.setLayoutY(10); // Adjust Y for second VBox

	    // Load the background image
	    Image image = new Image("applicationImagesBackgrounds/EndlessMountain16x9.png");
	    // Define the background size and position
	    BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

	    // Use a Pane to allow free positioning
	    Pane root = new Pane();
	 // Add both button lists
	    root.getChildren().addAll(logoBlack);
	    root.getChildren().addAll(logoWhite);
	    root.getChildren().addAll(titleblack);
	    root.getChildren().addAll(titlewhite);
	    root.getChildren().addAll(enterGame);
	    root.getChildren().addAll(enterGameText);
	    root.getChildren().addAll(playbutton, buttonBox2, shopTesting, gameOver);
	    
	    
	    root.setBackground(new Background(backgroundImage));
	    

	    // Set button styles and sizes for both sets of buttons
	    playbutton.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.08));
	    });

	    buttonBox2.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
	    });

	    Scene scene = new Scene(root, 1920, 1080); // Screen size

	    // Ensure the stage stays in fullscreen mode when the scene changes
	    primaryStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
	    });

	    scene.setCursor(customCursor);
	    primaryStage.setTitle("Endless Mountain of Monsters");
	    primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    primaryStage.show();

	    // Stop the music when the stage is closed
	    primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());
	}


	private void battleScene(Stage primaryStage) throws IOException {
		
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		
		String heroName = "hero name Variable"; // this will be used for our hero's name to change. // var
		String enemyName = "enemy name Variable"; // var
		String moveName = "moveName";
		
		// music player
		Media media = new Media(combatMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button heroPosition4 = new Button("heroPosition4");
		Button heroPosition3 = new Button("heroPosition3");
		Button heroPosition2 = new Button("heroPosition2");
		Button heroPosition1 = new Button("heroPosition1");
		Button enemyPosition1 = new Button("enemyPosition1");
		Button enemyPosition2 = new Button("enemyPosition2");
		Button enemyPosition3 = new Button("enemyPosition3");
		Button enemyPosition4 = new Button("enemyPosition4");
		Button passTurnButton = new Button("passTurnButton");
		
		//--------------------------------------------------------------------------------------------------------
		// Combat assets
		combatControl flow = new combatControl(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);
		flow.createEnemyTeam();
		flow.adjustSpeeds();
		flow.determineTurnOrder();
		entities[] tempTurnOrder = flow.getTurnOrder();
		playerTeamArray tempPTeamArray = flow.getPlayerTeamArray();
		Characters[] tempPTeam = tempPTeamArray.getTeam();
		enemyTeam tempETeamArray = flow.getEnemyTeam();
		Enemies[] tempETeam = tempETeamArray.getTeam();
		count = 0;
		round = 0;
		boolean gameOver = false;
		Timer timer = new Timer();
		//--------------------------------------------------------------------------------------------------------
		

		ToggleGroup skillButtonGroupPaladin = new ToggleGroup();
		RadioButton skillbutton1Paladin = new RadioButton("skill 1");
		skillbutton1Paladin.setToggleGroup(skillButtonGroupPaladin);
		RadioButton skillbutton2Paladin = new RadioButton("skill 2");
		skillbutton2Paladin.setToggleGroup(skillButtonGroupPaladin);
		RadioButton skillbutton3Paladin = new RadioButton("skill 3");
		skillbutton3Paladin.setToggleGroup(skillButtonGroupPaladin);
		RadioButton skillbutton4Paladin = new RadioButton("skill 4");
		skillbutton4Paladin.setToggleGroup(skillButtonGroupPaladin );
		// RadioButton movebuttonPaladin = new RadioButton("move");
		// movebuttonPaladin.setToggleGroup(skillButtonGroupPaladin);

		
		ToggleGroup skillButtonGroupAssassin = new ToggleGroup();
		RadioButton skillbutton1Assassin = new RadioButton("skill 1");
		skillbutton1Assassin.setToggleGroup(skillButtonGroupAssassin);
		RadioButton skillbutton2Assassin = new RadioButton("skill 2");
		skillbutton2Assassin.setToggleGroup(skillButtonGroupAssassin);
		RadioButton skillbutton3Assassin = new RadioButton("skill 3");
		skillbutton3Assassin.setToggleGroup(skillButtonGroupAssassin);
		RadioButton skillbutton4Assassin = new RadioButton("skill 4");
		skillbutton4Assassin.setToggleGroup(skillButtonGroupAssassin );
		// RadioButton movebuttonAssassin = new RadioButton("move");
		// movebuttonAssassin.setToggleGroup(skillButtonGroupAssassin);


		ToggleGroup skillButtonGroupWizard = new ToggleGroup();
		RadioButton skillbutton1Wizard = new RadioButton("skill 1");
		skillbutton1Wizard.setToggleGroup(skillButtonGroupWizard);
		RadioButton skillbutton2Wizard = new RadioButton("skill 2");
		skillbutton2Wizard.setToggleGroup(skillButtonGroupWizard);
		RadioButton skillbutton3Wizard = new RadioButton("skill 3");
		skillbutton3Wizard.setToggleGroup(skillButtonGroupWizard);
		RadioButton skillbutton4Wizard = new RadioButton("skill 4");
		skillbutton4Wizard.setToggleGroup(skillButtonGroupWizard);
		// RadioButton movebuttonWizard = new RadioButton("move");
		// movebuttonWizard.setToggleGroup(skillButtonGroupWizard);


		ToggleGroup skillButtonGroupAlchemist = new ToggleGroup();
		RadioButton skillbutton1Alchemist = new RadioButton("skill 1");
		skillbutton1Alchemist.setToggleGroup(skillButtonGroupAlchemist);
		RadioButton skillbutton2Alchemist = new RadioButton("skill 2");
		skillbutton2Alchemist.setToggleGroup(skillButtonGroupAlchemist);
		RadioButton skillbutton3Alchemist = new RadioButton("skill 3");
		skillbutton3Alchemist.setToggleGroup(skillButtonGroupAlchemist);
		RadioButton skillbutton4Alchemist = new RadioButton("skill 4");
		skillbutton4Alchemist.setToggleGroup(skillButtonGroupAlchemist);
		// RadioButton movebuttonAlchemist = new RadioButton("move");
		// movebuttonAlchemist.setToggleGroup(skillButtonGroupAlchemist);



		 
		Button menuBackButton = new Button ("Back"); 	
		Button menuQuitButton = new Button ("Quit Game");
		
		Text heroNameText = new Text(heroName);
		Text enemyNameText = new Text(enemyName);
		Text enemyBleedResistanceNumberText = new Text("BLD");
		Text enemyBlightResistanceNumberText = new Text("BLGT");
		Text enemyBurnResistanceNumberText = new Text("BRN");
		Text enemyStunResistanceNumberText = new Text("STN");
		Text enemyMoveResistanceNumberText = new Text("MOV");
		Text enemyDebuffResistanceNumberText = new Text("DBFF");
		Text enemyDeathResistanceNumberText = new Text("DTH");
		Text enemy1BleedResistanceNumber = new Text(Double.toString(tempETeam[0].getBleedResist())); 
		Text enemy1BlightResistanceNumber= new Text(Double.toString(tempETeam[0].getBlightResist())); 
		Text enemy1BurnResistanceNumber= new Text(Double.toString(tempETeam[0].getBurnResist())); 
		Text enemy1StunResistanceNumber= new Text(Double.toString(tempETeam[0].getStunResist())); 
		Text enemy1MoveResistanceNumber= new Text(Double.toString(tempETeam[0].getMoveResist())); 
		Text enemy1DebuffResistanceNumber= new Text(Double.toString(tempETeam[0].getDebuffResist())); 
		Text enemy1DeathResistanceNumber= new Text(Double.toString(tempETeam[0].getDeathResist())); 
		Text enemy2BleedResistanceNumber = new Text(Double.toString(tempETeam[1].getBleedResist())); 
		Text enemy2BlightResistanceNumber= new Text(Double.toString(tempETeam[1].getBlightResist())); 
		Text enemy2BurnResistanceNumber= new Text(Double.toString(tempETeam[1].getBurnResist())); 
		Text enemy2StunResistanceNumber= new Text(Double.toString(tempETeam[1].getStunResist())); 
		Text enemy2MoveResistanceNumber= new Text(Double.toString(tempETeam[1].getMoveResist())); 
		Text enemy2DebuffResistanceNumber= new Text(Double.toString(tempETeam[1].getDebuffResist())); 
		Text enemy2DeathResistanceNumber= new Text(Double.toString(tempETeam[1].getDeathResist())); 
		Text enemy3BleedResistanceNumber = new Text(Double.toString(tempETeam[2].getBleedResist())); 
		Text enemy3BlightResistanceNumber= new Text(Double.toString(tempETeam[2].getBlightResist())); 
		Text enemy3BurnResistanceNumber= new Text(Double.toString(tempETeam[2].getBurnResist())); 
		Text enemy3StunResistanceNumber= new Text(Double.toString(tempETeam[2].getStunResist())); 
		Text enemy3MoveResistanceNumber= new Text(Double.toString(tempETeam[2].getMoveResist())); 
		Text enemy3DebuffResistanceNumber= new Text(Double.toString(tempETeam[2].getDebuffResist())); 
		Text enemy3DeathResistanceNumber= new Text(Double.toString(tempETeam[2].getDeathResist())); 
		Text enemy4BleedResistanceNumber = new Text(Double.toString(tempETeam[3].getBleedResist())); 
		Text enemy4BlightResistanceNumber= new Text(Double.toString(tempETeam[3].getBlightResist())); 
		Text enemy4BurnResistanceNumber= new Text(Double.toString(tempETeam[3].getBurnResist())); 
		Text enemy4StunResistanceNumber= new Text(Double.toString(tempETeam[3].getStunResist())); 
		Text enemy4MoveResistanceNumber= new Text(Double.toString(tempETeam[3].getMoveResist())); 
		Text enemy4DebuffResistanceNumber= new Text(Double.toString(tempETeam[3].getDebuffResist())); 
		Text enemy4DeathResistanceNumber= new Text(Double.toString(tempETeam[3].getDeathResist())); 
		Text heroHPPos4 = new Text ("heroHPPos4"); // var
		Text heroHPPos3 = new Text ("heroHPPos3"); // var
		Text heroHPPos2 = new Text ("heroHPPos2"); // var
		Text heroHPPos1 = new Text ("heroHPPos1"); // var
		Text enemyHPPos1 = new Text ("enemyHPPos1"); // var
		Text enemyHPPos2 = new Text ("enemyHPPos2"); // var
		Text enemyHPPos3 = new Text ("enemyHPPos3"); // var
		Text enemyHPPos4 = new Text ("enemyHPPos4"); // var
		Text roundNumberText = new Text ("0"); // var + roundCounter
		
		Text moveDescriptionText = new Text ("DMG:  CRIT:(critpercent)"); // var
		Text moveNameText = new Text (moveName); // var
		
		Text menuQuitGameText = new Text ("Quit Game");
		
		heroNameText.setFont(KingArthurLegend);
		heroNameText.setFill(Color.web("#FFEB80"));
		enemyNameText.setFont(KingArthurLegend);
		enemyNameText.setFill(Color.web("d10000"));
		enemyBleedResistanceNumberText.setFont(DwarvenAxe);
		enemyBleedResistanceNumberText.setFill(Color.web("#bc1313"));
		enemyBlightResistanceNumberText.setFont(DwarvenAxe);
		enemyBlightResistanceNumberText.setFill(Color.web("#437c36"));
		enemyBurnResistanceNumberText.setFont(DwarvenAxe);
		enemyBurnResistanceNumberText.setFill(Color.web("#ca7430"));
		enemyStunResistanceNumberText.setFont(DwarvenAxe);
		enemyStunResistanceNumberText.setFill(Color.web("#cfc257"));
		enemyMoveResistanceNumberText.setFont(DwarvenAxe);
		enemyMoveResistanceNumberText.setFill(Color.web("#3f87b7"));
		enemyDebuffResistanceNumberText.setFont(DwarvenAxe);
		enemyDebuffResistanceNumberText.setFill(Color.web("#d5661b"));
		enemyDeathResistanceNumberText.setFont(DwarvenAxe);
		enemyDeathResistanceNumberText.setFill(Color.web("#8e0000"));
		roundNumberText.setFont(KingArthurLegend);
		roundNumberText.setFill(Color.web("#d5d5d5"));
		enemy1BleedResistanceNumber.setFont(DwarvenAxe);
		enemy1BleedResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1BlightResistanceNumber.setFont(DwarvenAxe);
		enemy1BlightResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1BurnResistanceNumber.setFont(DwarvenAxe);
		enemy1BurnResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1StunResistanceNumber.setFont(DwarvenAxe);
		enemy1StunResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1MoveResistanceNumber.setFont(DwarvenAxe);
		enemy1MoveResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1DebuffResistanceNumber.setFont(DwarvenAxe);
		enemy1DebuffResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy1DeathResistanceNumber.setFont(DwarvenAxe);
		enemy1DeathResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2BleedResistanceNumber.setFont(DwarvenAxe);
		enemy2BleedResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2BlightResistanceNumber.setFont(DwarvenAxe);
		enemy2BlightResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2BurnResistanceNumber.setFont(DwarvenAxe);
		enemy2BurnResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2StunResistanceNumber.setFont(DwarvenAxe);
		enemy2StunResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2MoveResistanceNumber.setFont(DwarvenAxe);
		enemy2MoveResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2DebuffResistanceNumber.setFont(DwarvenAxe);
		enemy2DebuffResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy2DeathResistanceNumber.setFont(DwarvenAxe);
		enemy2DeathResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3BleedResistanceNumber.setFont(DwarvenAxe);
		enemy3BleedResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3BlightResistanceNumber.setFont(DwarvenAxe);
		enemy3BlightResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3BurnResistanceNumber.setFont(DwarvenAxe);
		enemy3BurnResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3StunResistanceNumber.setFont(DwarvenAxe);
		enemy3StunResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3MoveResistanceNumber.setFont(DwarvenAxe);
		enemy3MoveResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3DebuffResistanceNumber.setFont(DwarvenAxe);
		enemy3DebuffResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy3DeathResistanceNumber.setFont(DwarvenAxe);
		enemy3DeathResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4BleedResistanceNumber.setFont(DwarvenAxe);
		enemy4BleedResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4BlightResistanceNumber.setFont(DwarvenAxe);
		enemy4BlightResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4BurnResistanceNumber.setFont(DwarvenAxe);
		enemy4BurnResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4StunResistanceNumber.setFont(DwarvenAxe);
		enemy4StunResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4MoveResistanceNumber.setFont(DwarvenAxe);
		enemy4MoveResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4DebuffResistanceNumber.setFont(DwarvenAxe);
		enemy4DebuffResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemy4DeathResistanceNumber.setFont(DwarvenAxe);
		enemy4DeathResistanceNumber.setFill(Color.web("#d5d5d5"));
		
		heroHPPos4.setFont(DwarvenAxe);
		heroHPPos4.setFill(Color.web("#bc1313"));
		heroHPPos3.setFont(DwarvenAxe);
		heroHPPos3.setFill(Color.web("#bc1313"));
		heroHPPos2.setFont(DwarvenAxe);
		heroHPPos2.setFill(Color.web("#bc1313"));
		heroHPPos1.setFont(DwarvenAxe);
		heroHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos1.setFont(DwarvenAxe);
		enemyHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos2.setFont(DwarvenAxe);
		enemyHPPos2.setFill(Color.web("#bc1313"));
		enemyHPPos3.setFont(DwarvenAxe);
		enemyHPPos3.setFill(Color.web("#bc1313"));
		enemyHPPos4.setFont(DwarvenAxe);
		enemyHPPos4.setFill(Color.web("#bc1313"));
		moveDescriptionText.setFont(DwarvenAxe); 
		moveDescriptionText.setFill(Color.web("#d5d5d5"));
		moveNameText.setFont(DwarvenAxe); 
		moveNameText.setFill(Color.web("#d5d5d5"));
		moveNameText.setTextAlignment(TextAlignment.CENTER);
		
		menuQuitGameText.setFont(KingArthurLegend);
		menuQuitGameText.setFill(Color.web("#cfc257"));
		// -------------------------------------------------------------

		Paint redToBlackGradient = new LinearGradient(
                0, 0, // Start at the top
                0, 1, // End at the bottom
                true, // proportional
                CycleMethod.NO_CYCLE, // Cycle method
                new Stop(0, Color.RED), // Start color (top)
                new Stop(1, Color.BLACK) // End color (bottom)
        );
		Paint whiteToBlackGradient = new LinearGradient(
				 0, 0, // Start at the top
	                0, 1, // End at the bottom
	                true, // proportional
	                CycleMethod.NO_CYCLE, // Cycle method
	                new Stop(0, Color.BLACK), // Start color (top)
	                new Stop(1, Color.rgb(50, 50, 50)) // End color (bottom, darker gray)
	    
				);
		BoxBlur blur = new BoxBlur(10, 10, 3);
		Rectangle heroHealthBarRedRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color // var
		Rectangle heroHealthBarRedRectangle3 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarRedRectangle2 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarRedRectangle1 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarBlackRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color
		Rectangle heroHealthBarBlackRectangle3 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle2 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle1 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle2 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle3 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle4 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarBlackRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle2 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle3 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle4 = new Rectangle(100, 15);
		heroHealthBarRedRectangle4.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle4.setArcWidth(5);
	    heroHealthBarRedRectangle4.setArcHeight(5);
		heroHealthBarRedRectangle3.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle3.setArcWidth(5);
	    heroHealthBarRedRectangle3.setArcHeight(5);
		heroHealthBarRedRectangle2.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle2.setArcWidth(5);
	    heroHealthBarRedRectangle2.setArcHeight(5);
		heroHealthBarRedRectangle1.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle1.setArcWidth(5);
	    heroHealthBarRedRectangle1.setArcHeight(5);
	    heroHealthBarBlackRectangle4.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle4.setArcWidth(5);
	    heroHealthBarBlackRectangle4.setArcHeight(5);
	    heroHealthBarBlackRectangle3.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle3.setArcWidth(5);
	    heroHealthBarBlackRectangle3.setArcHeight(5);
	    heroHealthBarBlackRectangle2.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle2.setArcWidth(5);
	    heroHealthBarBlackRectangle2.setArcHeight(5);
	    heroHealthBarBlackRectangle1.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle1.setArcWidth(5);
	    heroHealthBarBlackRectangle1.setArcHeight(5);
	    enemyHealthBarRedRectangle1.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle1.setArcWidth(5);
	    enemyHealthBarRedRectangle1.setArcHeight(5);
	    enemyHealthBarRedRectangle2.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle2.setArcWidth(5);
	    enemyHealthBarRedRectangle2.setArcHeight(5);
	    enemyHealthBarRedRectangle3.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle3.setArcWidth(5);
	    enemyHealthBarRedRectangle3.setArcHeight(5);
	    enemyHealthBarRedRectangle4.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle4.setArcWidth(5);
	    enemyHealthBarRedRectangle4.setArcHeight(5);
	    enemyHealthBarBlackRectangle1.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle1.setArcWidth(5);
	    enemyHealthBarBlackRectangle1.setArcHeight(5);
	    enemyHealthBarBlackRectangle2.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle2.setArcWidth(5);
	    enemyHealthBarBlackRectangle2.setArcHeight(5);
	    enemyHealthBarBlackRectangle3.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle3.setArcWidth(5);
	    enemyHealthBarBlackRectangle3.setArcHeight(5);
	    enemyHealthBarBlackRectangle4.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle4.setArcWidth(5);
	    enemyHealthBarBlackRectangle4.setArcHeight(5);
		// -------------------------------------------------------------
		// Images for everything
	    Image image1 = SwingFXUtils.toFXImage(tempETeam[0].getIdleSprite(), null);
		ImageView enemyInPosition1 = new ImageView(image1); // var
		Image image2 = SwingFXUtils.toFXImage(tempETeam[1].getIdleSprite(), null);
		ImageView enemyInPosition2 = new ImageView(image2); // var
		Image image3 = SwingFXUtils.toFXImage(tempETeam[2].getIdleSprite(), null);
		ImageView enemyInPosition3 = new ImageView(image3); // var
		Image image4 = SwingFXUtils.toFXImage(tempETeam[3].getIdleSprite(), null);
		ImageView enemyInPosition4 = new ImageView(image4); // var
		
		ImageView heroInPosition1 = new ImageView(new Image("applicationImagesPlayerSprites/Paladin_Idle.png"));
		ImageView heroInPosition2 = new ImageView(new Image("applicationImagesPlayerSprites/Assassin_Idle.png"));
		ImageView heroInPosition3 = new ImageView(new Image("applicationImagesPlayerSprites/Wizard_Idle.png"));
		ImageView heroInPosition4 = new ImageView(new Image("applicationImagesPlayerSprites/Alchemist_Idle.png"));
		
		ImageView skillbuttonimage1Paladin = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png")); 
		ImageView skillbuttonimage2Paladin = new ImageView(new Image("abilityIconsPaladin/divineSmite.png")); 
		ImageView skillbuttonimage3Paladin = new ImageView(new Image("abilityIconsPaladin/auraOfCourage.png"));
		ImageView skillbuttonimage4Paladin = new ImageView(new Image("abilityIconsPaladin/shieldOfFaith.png"));
		
		ImageView skillbuttonimage1Assassin = new ImageView(new Image("abilityIconsAssassin/piercingStab.png")); 
		ImageView skillbuttonimage2Assassin = new ImageView(new Image("abilityIconsAssassin/Backstab.png")); 
		ImageView skillbuttonimage3Assassin = new ImageView(new Image("abilityIconsAssassin/daggerBarrage.png")); 
		ImageView skillbuttonimage4Assassin = new ImageView(new Image("abilityIconsAssassin/poisonBlade.png")); 
		
		ImageView skillbuttonimage1Wizard = new ImageView(new Image("abilityIconsWizard/fireball.png")); 
		ImageView skillbuttonimage2Wizard = new ImageView(new Image("abilityIconsWizard/magicMissile.png"));
		ImageView skillbuttonimage3Wizard = new ImageView(new Image("abilityIconsWizard/frostBolt.png")); 
		ImageView skillbuttonimage4Wizard = new ImageView(new Image("abilityIconsWizard/staffStrike.png"));
		
		ImageView skillbuttonimage1Alchemist = new ImageView(new Image("abilityIconsAlchemist/acidPuddle.png")); 
		ImageView skillbuttonimage2Alchemist = new ImageView(new Image("abilityIconsAlchemist/explosiveFlask.png"));
		ImageView skillbuttonimage3Alchemist = new ImageView(new Image("abilityIconsAlchemist/invigoration.png"));
		ImageView skillbuttonimage4Alchemist = new ImageView(new Image("abilityIconsAlchemist/healing_elixer.png")); 
		
//		ImageView skillbuttonimagemove = new ImageView(new Image("GUIAssets/skillbuttonimagemove.png"));
		ImageView skillbuttonimagepass = new ImageView(new Image("GUIAssets/skillbuttonimagepass.png"));
		ImageView heroNameTextPlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView enemyNameTextPlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView turnOrderBarLeftAndRight = new ImageView(new Image("GUIAssets/turnOrderBarLeftAndRight.png"));
		ImageView heroSelectionIndicator4 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator3 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator2 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator1 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator1 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator2 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator3 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator4 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		
		ImageView heroTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker4 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker4 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView skillButtonSelectedFrame1 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame2 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame3 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame4 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
//		ImageView skillButtonMoveSelectedFrame = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		
		ImageView enemyBLDResistanceIcon = new ImageView(new Image("GUIAssets/BLDResistance.png"));
		ImageView enemyBLGTResistanceIcon = new ImageView(new Image("GUIAssets/BLGTResistance.png"));
		ImageView enemyBURNResistanceIcon = new ImageView(new Image("GUIAssets/BURNResistance.png"));
		ImageView enemySTNResistanceIcon = new ImageView(new Image("GUIAssets/STNResistance.png"));
		ImageView enemyMOVResistanceIcon = new ImageView(new Image("GUIAssets/MOVResistance.png"));
		ImageView enemyDBFFResistanceIcon = new ImageView(new Image("GUIAssets/DBFFResistance.png"));
		ImageView enemyDTHResistanceIcon = new ImageView(new Image("GUIAssets/DTHResistance.png"));
		
		ImageView menuBackground = new ImageView(new Image("GUIAssets/menuBackground.png"));
		ImageView menuBackButtonImage = new ImageView(new Image("GUIAssets/menuBackButtonImage.png"));
		ImageView menuButtonFrame1 = new ImageView(new Image("GUIAssets/menuButtonFrame.png"));
		
		ImageView enemyAttackingBloodStain  = new ImageView(new Image("GUIAssets/enemyAttackingBloodStain.png"));
		ImageView heroAttackingBloodStain  = new ImageView(new Image("GUIAssets/heroAttackingBloodStain.png"));
		
//		Image backgroundImagesetup = new Image("combatBackgrounds/cryptsRoomWallDrain.png");  // var
		Image backgroundImagesetup = new Image("combatBackgrounds/combatBackground1.png");  // var
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);
		
		FadeTransition fadeInHeroHPPos1 = FadeUtils.createFade(heroHPPos1, 0, 1, 200);
		FadeTransition fadeInHeroHPPos2 = FadeUtils.createFade(heroHPPos2, 0, 1, 200);
		FadeTransition fadeInHeroHPPos3 = FadeUtils.createFade(heroHPPos3, 0, 1, 200);
		FadeTransition fadeInHeroHPPos4 = FadeUtils.createFade(heroHPPos4, 0, 1, 200);
		FadeTransition fadeOutHeroHPPos1 = FadeUtils.createFade(heroHPPos1, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos2 = FadeUtils.createFade(heroHPPos2, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos3 = FadeUtils.createFade(heroHPPos3, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos4 = FadeUtils.createFade(heroHPPos4, 1, 0, 200);
		
		FadeTransition fadeInEnemyHPPos1 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos2 = FadeUtils.createFade(enemyHPPos2, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos3 = FadeUtils.createFade(enemyHPPos3, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos4 = FadeUtils.createFade(enemyHPPos4, 0, 1, 200);
		FadeTransition fadeOutEnemyHPPos1 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos2 = FadeUtils.createFade(enemyHPPos2, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos3 = FadeUtils.createFade(enemyHPPos3, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos4 = FadeUtils.createFade(enemyHPPos4, 1, 0, 200);
		
	    FadeTransition fadeInHeroSelectionIndicator1 = FadeUtils.createFade(heroSelectionIndicator1, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator2 = FadeUtils.createFade(heroSelectionIndicator2, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator3 = FadeUtils.createFade(heroSelectionIndicator3, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator4 = FadeUtils.createFade(heroSelectionIndicator4, 0, 1, 200);
	    FadeTransition fadeOutHeroSelectionIndicator1 = FadeUtils.createFade(heroSelectionIndicator1, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator2 = FadeUtils.createFade(heroSelectionIndicator2, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator3 = FadeUtils.createFade(heroSelectionIndicator3, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator4 = FadeUtils.createFade(heroSelectionIndicator4, 1, 0, 200);
	    
        FadeTransition fadeInEnemySelectionIndicator1 = FadeUtils.createFade(enemySelectionIndicator1, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator2 = FadeUtils.createFade(enemySelectionIndicator2, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator3 = FadeUtils.createFade(enemySelectionIndicator3, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator4 = FadeUtils.createFade(enemySelectionIndicator4, 0, 1, 200);
        FadeTransition fadeOutEnemySelectionIndicator1 = FadeUtils.createFade(enemySelectionIndicator1, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator2 = FadeUtils.createFade(enemySelectionIndicator2, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator3 = FadeUtils.createFade(enemySelectionIndicator3, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator4 = FadeUtils.createFade(enemySelectionIndicator4, 1, 0, 200);
        
        PauseTransition twoSecDelay = new PauseTransition(Duration.seconds(2));
        
		HBox enemyResistances = new HBox(50);
		enemyResistances.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon );
		HBox heroPositions = new HBox(50);
		heroPositions.getChildren().addAll(heroPosition4, heroPosition3, heroPosition2, heroPosition1);
		HBox enemyPositions = new HBox(50);
		enemyPositions.getChildren().addAll(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);
		HBox skillButtonsPaladin = new HBox(10);
		skillButtonsPaladin.getChildren().addAll(skillbutton1Paladin, skillbutton2Paladin, skillbutton3Paladin, skillbutton4Paladin);// ,movebutton
		
		HBox skillButtonsAssassin = new HBox(10);
		skillButtonsAssassin.getChildren().addAll(skillbutton1Assassin, skillbutton2Assassin, skillbutton3Assassin, skillbutton4Assassin);// ,movebutton
		skillButtonsAssassin.setMouseTransparent(true);
		HBox skillButtonsWizard = new HBox(10);
		skillButtonsWizard.getChildren().addAll(skillbutton1Wizard, skillbutton2Wizard, skillbutton3Wizard, skillbutton4Wizard);// ,movebutton
		skillButtonsWizard.setMouseTransparent(true);
		HBox skillButtonsAlchemist = new HBox(10);
		skillButtonsAlchemist.getChildren().addAll(skillbutton1Alchemist, skillbutton2Alchemist, skillbutton3Alchemist, skillbutton4Alchemist);// ,movebutton
		skillButtonsAlchemist.setMouseTransparent(true);
		
		HBox skillButtonsImagesPaladin = new HBox(10);
		skillButtonsImagesPaladin.getChildren().addAll(skillbuttonimage1Paladin, skillbuttonimage2Paladin, skillbuttonimage3Paladin, skillbuttonimage4Paladin);
		HBox skillButtonsImagesAssassin = new HBox(10);
		skillButtonsImagesAssassin.getChildren().addAll(skillbuttonimage1Assassin, skillbuttonimage2Assassin, skillbuttonimage3Assassin, skillbuttonimage4Assassin);
		HBox skillButtonsImagesWizard = new HBox(10);
		skillButtonsImagesWizard.getChildren().addAll(skillbuttonimage1Wizard, skillbuttonimage2Wizard, skillbuttonimage3Wizard, skillbuttonimage4Wizard);
		HBox skillButtonsImagesAlchemist= new HBox(10);
		skillButtonsImagesAlchemist.getChildren().addAll(skillbuttonimage1Alchemist, skillbuttonimage2Alchemist, skillbuttonimage3Alchemist, skillbuttonimage4Alchemist);
		
		// -------------------------------------------------------------
		// Set the size of the images to match the button size
		enemyInPosition1.setScaleX(.75);
		enemyInPosition1.setScaleY(.85); 
		enemyInPosition2.setScaleX(.75);
		enemyInPosition2.setScaleY(.85);
		enemyInPosition3.setScaleX(.75);
		enemyInPosition3.setScaleY(.85);
		enemyInPosition4.setScaleX(1);	
		enemyInPosition4.setScaleY(1.1);
		heroInPosition1.setScaleX(.55);
		heroInPosition1.setScaleY(.65); 
		heroInPosition2.setScaleX(.45);
		heroInPosition2.setScaleY(.45);
		heroInPosition3.setScaleX(.65);
		heroInPosition3.setScaleY(.65);
		heroInPosition4.setScaleX(.55);
		heroInPosition4.setScaleY(.55);
		
		
		skillbuttonimage1Paladin.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1Paladin.setFitHeight(0.09 * 1080);
		skillbuttonimage2Paladin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2Paladin.setFitHeight(0.09 * 1080);
		skillbuttonimage3Paladin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3Paladin.setFitHeight(0.09 * 1080);
		skillbuttonimage4Paladin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4Paladin.setFitHeight(0.09 * 1080);
//		skillbuttonimagemove.setFitWidth(0.06 * 1920);
//		skillbuttonimagemove.setFitHeight(0.051 * 1920);
		
		skillbuttonimage1Assassin.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1Assassin.setFitHeight(0.09 * 1080);
		skillbuttonimage2Assassin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2Assassin.setFitHeight(0.09 * 1080);
		skillbuttonimage3Assassin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3Assassin.setFitHeight(0.09 * 1080);
		skillbuttonimage4Assassin.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4Assassin.setFitHeight(0.09 * 1080);

		skillbuttonimage1Wizard.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1Wizard.setFitHeight(0.09 * 1080);
		skillbuttonimage2Wizard.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2Wizard.setFitHeight(0.09 * 1080);
		skillbuttonimage3Wizard.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3Wizard.setFitHeight(0.09 * 1080);
		skillbuttonimage4Wizard.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4Wizard.setFitHeight(0.09 * 1080);
		
		skillbuttonimage1Alchemist.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1Alchemist.setFitHeight(0.09 * 1080);
		skillbuttonimage2Alchemist.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2Alchemist.setFitHeight(0.09 * 1080);
		skillbuttonimage3Alchemist.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3Alchemist.setFitHeight(0.09 * 1080);
		skillbuttonimage4Alchemist.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4Alchemist.setFitHeight(0.09 * 1080);
		
		
		
		skillButtonSelectedFrame1.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame1.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame2.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame2.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame3.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame3.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame4.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame4.setFitHeight(0.09 * 1080);
//		skillButtonMoveSelectedFrame.setFitWidth(0.06 * 1920);
//		skillButtonMoveSelectedFrame.setFitHeight(0.09 * 1080);
	

		// -------------------------------------------------------------
		enemyBLDResistanceIcon.setFitWidth(0.03 * 1920); 
		enemyBLDResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyBLGTResistanceIcon.setFitWidth(0.03 * 1920);
		enemyBLGTResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyBURNResistanceIcon.setFitWidth(0.03 * 1920);
		enemyBURNResistanceIcon.setFitHeight(0.03 * 1080); 
		enemySTNResistanceIcon.setFitWidth(0.03 * 1920);
		enemySTNResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyMOVResistanceIcon.setFitWidth(0.03 * 1920);
		enemyMOVResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyDBFFResistanceIcon.setFitWidth(0.03 * 1920);
		enemyDBFFResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyDTHResistanceIcon.setFitWidth(0.03 * 1920);
		enemyDTHResistanceIcon.setFitHeight(0.03 * 1080); 
		// -------------------------------------------------------------
		enemyInPosition1.setMouseTransparent(true);
		enemyInPosition2.setMouseTransparent(true);
		enemyInPosition3.setMouseTransparent(true);
		enemyInPosition4.setMouseTransparent(true);
		heroInPosition1.setMouseTransparent(true);
		heroInPosition2.setMouseTransparent(true);
		heroInPosition3.setMouseTransparent(true);
		heroInPosition4.setMouseTransparent(true);
		
		skillbuttonimage1Paladin.setMouseTransparent(true);
		skillbuttonimage2Paladin.setMouseTransparent(true);
		skillbuttonimage3Paladin.setMouseTransparent(true);
		skillbuttonimage4Paladin.setMouseTransparent(true);
		skillbuttonimage1Assassin.setMouseTransparent(true);
		skillbuttonimage2Assassin.setMouseTransparent(true);
		skillbuttonimage3Assassin.setMouseTransparent(true);
		skillbuttonimage4Assassin.setMouseTransparent(true);
		skillbuttonimage1Wizard.setMouseTransparent(true);
		skillbuttonimage2Wizard.setMouseTransparent(true);
		skillbuttonimage3Wizard.setMouseTransparent(true);
		skillbuttonimage4Wizard.setMouseTransparent(true);
		skillbuttonimage1Alchemist.setMouseTransparent(true);
		skillbuttonimage2Alchemist.setMouseTransparent(true);
		skillbuttonimage3Alchemist.setMouseTransparent(true);
		skillbuttonimage4Alchemist.setMouseTransparent(true);
		
		
		heroSelectionIndicator4.setMouseTransparent(true);
		heroSelectionIndicator3.setMouseTransparent(true);
		heroSelectionIndicator2.setMouseTransparent(true);
		heroSelectionIndicator1.setMouseTransparent(true);
		enemySelectionIndicator1.setMouseTransparent(true);
		enemySelectionIndicator2.setMouseTransparent(true);
		enemySelectionIndicator3.setMouseTransparent(true);
		enemySelectionIndicator4.setMouseTransparent(true);
//		skillbuttonimagemove.setMouseTransparent(true);
		skillbuttonimagepass.setMouseTransparent(true);

		menuBackButtonImage.setMouseTransparent(true);
		menuQuitGameText.setMouseTransparent(true);
		skillButtonSelectedFrame1.setMouseTransparent(true);
		skillButtonSelectedFrame2.setMouseTransparent(true);
		skillButtonSelectedFrame3.setMouseTransparent(true);
		skillButtonSelectedFrame4.setMouseTransparent(true);
//		skillButtonMoveSelectedFrame.setMouseTransparent(true);
		skillButtonSelectedFrame1.setVisible(false);
		skillButtonSelectedFrame2.setVisible(false);
		skillButtonSelectedFrame3.setVisible(false);
		skillButtonSelectedFrame4.setVisible(false);
//		skillButtonMoveSelectedFrame.setVisible(false);
		
		enemyBLDResistanceIcon.setVisible(false);
		enemyBLGTResistanceIcon.setVisible(false);
		enemyBURNResistanceIcon.setVisible(false);
		enemySTNResistanceIcon.setVisible(false);
		enemyMOVResistanceIcon.setVisible(false);
		enemyDBFFResistanceIcon.setVisible(false);
		enemyDTHResistanceIcon.setVisible(false);
		enemyBleedResistanceNumberText.setVisible(false);
		enemyBlightResistanceNumberText.setVisible(false);
		enemyBurnResistanceNumberText.setVisible(false);
		enemyStunResistanceNumberText.setVisible(false);
		enemyMoveResistanceNumberText.setVisible(false);
		enemyDebuffResistanceNumberText.setVisible(false);
		enemyDeathResistanceNumberText.setVisible(false);
		enemy1BleedResistanceNumber.setVisible(false);
		enemy1BlightResistanceNumber.setVisible(false);
		enemy1BurnResistanceNumber.setVisible(false);
		enemy1StunResistanceNumber.setVisible(false);
		enemy1MoveResistanceNumber.setVisible(false);
		enemy1DebuffResistanceNumber.setVisible(false);
		enemy1DeathResistanceNumber.setVisible(false);
		enemy2BleedResistanceNumber.setVisible(false);
		enemy2BlightResistanceNumber.setVisible(false);
		enemy2BurnResistanceNumber.setVisible(false);
		enemy2StunResistanceNumber.setVisible(false);
		enemy2MoveResistanceNumber.setVisible(false);
		enemy2DebuffResistanceNumber.setVisible(false);
		enemy2DeathResistanceNumber.setVisible(false);
		enemy3BleedResistanceNumber.setVisible(false);
		enemy3BlightResistanceNumber.setVisible(false);
		enemy3BurnResistanceNumber.setVisible(false);
		enemy3StunResistanceNumber.setVisible(false);
		enemy3MoveResistanceNumber.setVisible(false);
		enemy3DebuffResistanceNumber.setVisible(false);
		enemy3DeathResistanceNumber.setVisible(false);
		enemy4BleedResistanceNumber.setVisible(false);
		enemy4BlightResistanceNumber.setVisible(false);
		enemy4BurnResistanceNumber.setVisible(false);
		enemy4StunResistanceNumber.setVisible(false);
		enemy4MoveResistanceNumber.setVisible(false);
		enemy4DebuffResistanceNumber.setVisible(false);
		enemy4DeathResistanceNumber.setVisible(false);
		
		enemyNameText.setVisible(false);
		
		heroPosition4.setOpacity(0);
		heroPosition3.setOpacity(0);
		heroPosition2.setOpacity(0);
		heroPosition1.setOpacity(0);
		enemyPosition1.setOpacity(0);
		enemyPosition2.setOpacity(0);
		enemyPosition3.setOpacity(0);
		enemyPosition4.setOpacity(0);


//		movebutton.setOpacity(0);
		passTurnButton.setOpacity(00);
		
//		while (heroInPos1Turn = true){
//
//			//all other buttons and images cannot be clicked or seen.
//			skillbuttonimage1Paladin.setOpacity(100); //image is visible
//			skillbuttonimage2Paladin.setOpacity(100);
//			skillbuttonimage3Paladin.setOpacity(100);
//			skillbuttonimage4Paladin.setOpacity(100);
//			skillbuttonimage1Assassin.setOpacity(0); // image is not visible
//			skillbuttonimage2Assassin.setOpacity(0);
//			skillbuttonimage3Assassin.setOpacity(0);
//			skillbuttonimage4Assassin.setOpacity(0);
//			skillbuttonimage1Wizard.setOpacity(0);
//			skillbuttonimage2Wizard.setOpacity(0);
//			skillbuttonimage3Wizard.setOpacity(0);
//			skillbuttonimage4Wizard.setOpacity(0);
//			skillbuttonimage1Alchemist.setOpacity(0);
//			skillbuttonimage2Alchemist.setOpacity(0);
//			skillbuttonimage3Alchemist.setOpacity(0);
//			skillbuttonimage4Alchemist.setOpacity(0);
//			skillbutton1Paladin.setMouseTransparent(false); //button is clickable
//			skillbutton2Paladin.setMouseTransparent(false);
//			skillbutton3Paladin.setMouseTransparent(false);
//			skillbutton4Paladin.setMouseTransparent(false);
//			skillbutton1Assassin.setMouseTransparent(true); //button is not clickable
//			skillbutton2Assassin.setMouseTransparent(true); 
//			skillbutton3Assassin.setMouseTransparent(true); 
//			skillbutton4Assassin.setMouseTransparent(true); 
//			skillbutton1Wizard.setMouseTransparent(true); 
//			skillbutton2Wizard.setMouseTransparent(true); 
//			skillbutton3Wizard.setMouseTransparent(true); 
//			skillbutton4Wizard.setMouseTransparent(true); 
//			skillbutton1Alchemist.setMouseTransparent(true); 
//			skillbutton2Alchemist.setMouseTransparent(true); 
//			skillbutton3Alchemist.setMouseTransparent(true); 
//			skillbutton4Alchemist.setMouseTransparent(true); 
//		}
//		
//		while (heroInPos2Turn = true){
//
//			//all other buttons and images cannot be clicked or seen.
//			skillbuttonimage1Paladin.setOpacity(0); //image is visible
//			skillbuttonimage2Paladin.setOpacity(0);
//			skillbuttonimage3Paladin.setOpacity(0);
//			skillbuttonimage4Paladin.setOpacity(0);
//			skillbuttonimage1Assassin.setOpacity(100); // image is not visible
//			skillbuttonimage2Assassin.setOpacity(100);
//			skillbuttonimage3Assassin.setOpacity(100);
//			skillbuttonimage4Assassin.setOpacity(100);
//			skillbuttonimage1Wizard.setOpacity(0);
//			skillbuttonimage2Wizard.setOpacity(0);
//			skillbuttonimage3Wizard.setOpacity(0);
//			skillbuttonimage4Wizard.setOpacity(0);
//			skillbuttonimage1Alchemist.setOpacity(0);
//			skillbuttonimage2Alchemist.setOpacity(0);
//			skillbuttonimage3Alchemist.setOpacity(0);
//			skillbuttonimage4Alchemist.setOpacity(0);
//			skillbutton1Paladin.setMouseTransparent(true); //button is clickable
//			skillbutton2Paladin.setMouseTransparent(true);
//			skillbutton3Paladin.setMouseTransparent(true);
//			skillbutton4Paladin.setMouseTransparent(true);
//			skillbutton1Assassin.setMouseTransparent(false); //button is not clickable
//			skillbutton2Assassin.setMouseTransparent(false); 
//			skillbutton3Assassin.setMouseTransparent(false); 
//			skillbutton4Assassin.setMouseTransparent(false); 
//			skillbutton1Wizard.setMouseTransparent(true); 
//			skillbutton2Wizard.setMouseTransparent(true); 
//			skillbutton3Wizard.setMouseTransparent(true); 
//			skillbutton4Wizard.setMouseTransparent(true); 
//			skillbutton1Alchemist.setMouseTransparent(true); 
//			skillbutton2Alchemist.setMouseTransparent(true); 
//			skillbutton3Alchemist.setMouseTransparent(true); 
//			skillbutton4Alchemist.setMouseTransparent(true); 
//		}
//		
//		while (heroInPos3Turn = true){
//
//			//all other buttons and images cannot be clicked or seen.
//			skillbuttonimage1Paladin.setOpacity(0); //image is visible
//			skillbuttonimage2Paladin.setOpacity(0);
//			skillbuttonimage3Paladin.setOpacity(0);
//			skillbuttonimage4Paladin.setOpacity(0);
//			skillbuttonimage1Assassin.setOpacity(0); // image is not visible
//			skillbuttonimage2Assassin.setOpacity(0);
//			skillbuttonimage3Assassin.setOpacity(0);
//			skillbuttonimage4Assassin.setOpacity(0);
//			skillbuttonimage1Wizard.setOpacity(100);
//			skillbuttonimage2Wizard.setOpacity(100);
//			skillbuttonimage3Wizard.setOpacity(100);
//			skillbuttonimage4Wizard.setOpacity(100);
//			skillbuttonimage1Alchemist.setOpacity(0);
//			skillbuttonimage2Alchemist.setOpacity(0);
//			skillbuttonimage3Alchemist.setOpacity(0);
//			skillbuttonimage4Alchemist.setOpacity(0);
//			skillbutton1Paladin.setMouseTransparent(true); //button is clickable
//			skillbutton2Paladin.setMouseTransparent(true);
//			skillbutton3Paladin.setMouseTransparent(true);
//			skillbutton4Paladin.setMouseTransparent(true);
//			skillbutton1Assassin.setMouseTransparent(true); //button is not clickable
//			skillbutton2Assassin.setMouseTransparent(true); 
//			skillbutton3Assassin.setMouseTransparent(true); 
//			skillbutton4Assassin.setMouseTransparent(true); 
//			skillbutton1Wizard.setMouseTransparent(false); 
//			skillbutton2Wizard.setMouseTransparent(false); 
//			skillbutton3Wizard.setMouseTransparent(false); 
//			skillbutton4Wizard.setMouseTransparent(false); 
//			skillbutton1Alchemist.setMouseTransparent(true); 
//			skillbutton2Alchemist.setMouseTransparent(true); 
//			skillbutton3Alchemist.setMouseTransparent(true); 
//			skillbutton4Alchemist.setMouseTransparent(true); 
//		}
//		
//		while (heroInPos4Turn = true){
//
//			//all other buttons and images cannot be clicked or seen.
//			skillbuttonimage1Paladin.setOpacity(0); //image is visible
//			skillbuttonimage2Paladin.setOpacity(0);
//			skillbuttonimage3Paladin.setOpacity(0);
//			skillbuttonimage4Paladin.setOpacity(0);
//			skillbuttonimage1Assassin.setOpacity(0); // image is not visible
//			skillbuttonimage2Assassin.setOpacity(0);
//			skillbuttonimage3Assassin.setOpacity(0);
//			skillbuttonimage4Assassin.setOpacity(0);
//			skillbuttonimage1Wizard.setOpacity(0);
//			skillbuttonimage2Wizard.setOpacity(0);
//			skillbuttonimage3Wizard.setOpacity(0);
//			skillbuttonimage4Wizard.setOpacity(0);
//			skillbuttonimage1Alchemist.setOpacity(100);
//			skillbuttonimage2Alchemist.setOpacity(100);
//			skillbuttonimage3Alchemist.setOpacity(100);
//			skillbuttonimage4Alchemist.setOpacity(100);
//			skillbutton1Paladin.setMouseTransparent(true); //button is clickable
//			skillbutton2Paladin.setMouseTransparent(true);
//			skillbutton3Paladin.setMouseTransparent(true);
//			skillbutton4Paladin.setMouseTransparent(true);
//			skillbutton1Assassin.setMouseTransparent(true); //button is not clickable
//			skillbutton2Assassin.setMouseTransparent(true); 
//			skillbutton3Assassin.setMouseTransparent(true); 
//			skillbutton4Assassin.setMouseTransparent(true); 
//			skillbutton1Wizard.setMouseTransparent(true); 
//			skillbutton2Wizard.setMouseTransparent(true); 
//			skillbutton3Wizard.setMouseTransparent(true); 
//			skillbutton4Wizard.setMouseTransparent(true); 
//			skillbutton1Alchemist.setMouseTransparent(false); 
//			skillbutton2Alchemist.setMouseTransparent(false); 
//			skillbutton3Alchemist.setMouseTransparent(false); 
//			skillbutton4Alchemist.setMouseTransparent(false); 
//		}
		
		skillbutton1Paladin.setOpacity(0);
		skillbutton2Paladin.setOpacity(0);
		skillbutton3Paladin.setOpacity(0);
		skillbutton4Paladin.setOpacity(0);
		skillbutton1Assassin.setOpacity(0); // cant see the button
		skillbutton2Assassin.setOpacity(0);
		skillbutton3Assassin.setOpacity(0);
		skillbutton4Assassin.setOpacity(0);
		skillbutton1Wizard.setOpacity(0);
		skillbutton2Wizard.setOpacity(0);
		skillbutton3Wizard.setOpacity(0);
		skillbutton4Wizard.setOpacity(0);
		skillbutton1Alchemist.setOpacity(0);
		skillbutton2Alchemist.setOpacity(0);
		skillbutton3Alchemist.setOpacity(0);
		skillbutton4Alchemist.setOpacity(0);
		skillbutton1Paladin.setMouseTransparent(true); // cant click the button
		skillbutton2Paladin.setMouseTransparent(true);
		skillbutton3Paladin.setMouseTransparent(true);
		skillbutton4Paladin.setMouseTransparent(true);
		skillbutton1Assassin.setMouseTransparent(true); 
		skillbutton2Assassin.setMouseTransparent(true);
		skillbutton3Assassin.setMouseTransparent(true);
		skillbutton4Assassin.setMouseTransparent(true);
		skillbutton1Wizard.setMouseTransparent(true);
		skillbutton2Wizard.setMouseTransparent(true);
		skillbutton3Wizard.setMouseTransparent(true);
		skillbutton4Wizard.setMouseTransparent(true);
		skillbutton1Alchemist.setMouseTransparent(true);
		skillbutton2Alchemist.setMouseTransparent(true);
		skillbutton3Alchemist.setMouseTransparent(true);
		skillbutton4Alchemist.setMouseTransparent(true);

		
		skillbuttonimage1Paladin.setOpacity(00);
		skillbuttonimage2Paladin.setOpacity(00);
		skillbuttonimage3Paladin.setOpacity(00);
		skillbuttonimage4Paladin.setOpacity(00);
		skillbuttonimage1Assassin.setOpacity(00);
		skillbuttonimage2Assassin.setOpacity(00);
		skillbuttonimage3Assassin.setOpacity(00);
		skillbuttonimage4Assassin.setOpacity(00);
		skillbuttonimage1Wizard.setOpacity(00);
		skillbuttonimage2Wizard.setOpacity(00);
		skillbuttonimage3Wizard.setOpacity(00);
		skillbuttonimage4Wizard.setOpacity(00);
		skillbuttonimage1Alchemist.setOpacity(00);
		skillbuttonimage2Alchemist.setOpacity(00);
		skillbuttonimage3Alchemist.setOpacity(00);
		skillbuttonimage4Alchemist.setOpacity(00);
		
		skillbuttonimagepass.setOpacity(100);
		
		menuBackButton.setOpacity(0);
		menuQuitButton.setOpacity(0);
		// -------------------------------------------------------------
		skillbutton1Paladin.setOnAction(e -> { 
			skillButtonSelectedFrame1.setVisible(true);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton2Paladin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(true);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton3Paladin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(true);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton4Paladin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(true);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
//		movebutton.setOnAction(e -> {
//			skillButtonSelectedFrame1.setVisible(false);
//			skillButtonSelectedFrame2.setVisible(false);
//			skillButtonSelectedFrame3.setVisible(false);
//			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(true);
//		});
		passTurnButton.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
		});
		skillbutton1Assassin.setOnAction(e -> { 
			skillButtonSelectedFrame1.setVisible(true);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton2Assassin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(true);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton3Assassin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(true);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton4Assassin.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(true);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		
		skillbutton1Wizard.setOnAction(e -> { 
			skillButtonSelectedFrame1.setVisible(true);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton2Wizard.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(true);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton3Wizard.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(true);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton4Wizard.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(true);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		
		skillbutton1Alchemist.setOnAction(e -> { 
			skillButtonSelectedFrame1.setVisible(true);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton2Alchemist.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(true);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton3Alchemist.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(true);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton4Alchemist.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(true);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		
		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		// none of this is correct. these need to be changed so that when a move is selected, it only shows visible on which enemies can be attacked.  // var
		heroPosition1.setOnMouseEntered(e -> {heroSelectionIndicator1.setVisible(true);
		fadeInHeroSelectionIndicator1.play();
		});
		heroPosition1.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator1.play();
		});
		heroSelectionIndicator1.setVisible(false);
		
		heroPosition2.setOnMouseEntered(e -> {heroSelectionIndicator2.setVisible(true);
		fadeInHeroSelectionIndicator2.play();
		});
		heroPosition2.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator2.play();
		});
		heroSelectionIndicator2.setVisible(false);

		heroPosition3.setOnMouseEntered(e -> {heroSelectionIndicator3.setVisible(true);
		fadeInHeroSelectionIndicator3.play();
		});
		heroPosition3.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator3.play();
		});
		heroSelectionIndicator3.setVisible(false);

		heroPosition4.setOnMouseEntered(e -> {heroSelectionIndicator4.setVisible(true);
		fadeInHeroSelectionIndicator4.play();
		});
		heroPosition4.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator4.play();
		});
		heroSelectionIndicator4.setVisible(false);
		
		
		enemyPosition1.setOnMouseEntered(e -> {enemySelectionIndicator1.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator1.play();
		});
		enemyPosition1.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator1.play();
		});
		
		enemySelectionIndicator1.setVisible(false);
		
		enemyPosition2.setOnMouseEntered(e -> {enemySelectionIndicator2.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator2.play();
		});
		enemyPosition2.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator2.play();
		});
		
		enemySelectionIndicator2.setVisible(false);
		
		enemyPosition3.setOnMouseEntered(e -> {enemySelectionIndicator3.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator3.play();
		});
		enemyPosition3.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator3.play();
		});
		
		enemySelectionIndicator3.setVisible(false);
		
		enemyPosition4.setOnMouseEntered(e -> {enemySelectionIndicator4.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator4.play();
		});
		enemyPosition4.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator4.play();
		});
		
		enemySelectionIndicator4.setVisible(false);

		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		enemyPosition1.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
			enemyNameText.setText(tempETeam[0].getName());
			enemyNameText.setVisible(true);
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemy1BleedResistanceNumber.setVisible(true);
		    enemy1BlightResistanceNumber.setVisible(true);
		    enemy1BurnResistanceNumber.setVisible(true);
		    enemy1StunResistanceNumber.setVisible(true);
		    enemy1MoveResistanceNumber.setVisible(true);
		    enemy1DebuffResistanceNumber.setVisible(true);
		    enemy1DeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator1.setVisible(true);
		    fadeInEnemySelectionIndicator1.play();
		});

		enemyPosition1.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator1.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemy1BleedResistanceNumber.setVisible(false);
	        enemy1BlightResistanceNumber.setVisible(false);
	        enemy1BurnResistanceNumber.setVisible(false);
	        enemy1StunResistanceNumber.setVisible(false);
	        enemy1MoveResistanceNumber.setVisible(false);
	        enemy1DebuffResistanceNumber.setVisible(false);
	        enemy1DeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator1.setOnFinished(event -> {
		        enemySelectionIndicator1.setVisible(false);
		       
		    });
		});


		enemyPosition2.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
			enemyNameText.setText(tempETeam[1].getName());
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemy2BleedResistanceNumber.setVisible(true);
		    enemy2BlightResistanceNumber.setVisible(true);
		    enemy2BurnResistanceNumber.setVisible(true);
		    enemy2StunResistanceNumber.setVisible(true);
		    enemy2MoveResistanceNumber.setVisible(true);
		    enemy2DebuffResistanceNumber.setVisible(true);
		    enemy2DeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator2.setVisible(true);
		    fadeInEnemySelectionIndicator2.play();
		});

		enemyPosition2.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator2.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemy2BleedResistanceNumber.setVisible(false);
	        enemy2BlightResistanceNumber.setVisible(false);
	        enemy2BurnResistanceNumber.setVisible(false);
	        enemy2StunResistanceNumber.setVisible(false);
	        enemy2MoveResistanceNumber.setVisible(false);
	        enemy2DebuffResistanceNumber.setVisible(false);
	        enemy2DeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator2.setOnFinished(event -> {
		        enemySelectionIndicator2.setVisible(false);
		     
		    });
		});

		enemyPosition3.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
			enemyNameText.setText(tempETeam[2].getName());
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemy3BleedResistanceNumber.setVisible(true);
		    enemy3BlightResistanceNumber.setVisible(true);
		    enemy3BurnResistanceNumber.setVisible(true);
		    enemy3StunResistanceNumber.setVisible(true);
		    enemy3MoveResistanceNumber.setVisible(true);
		    enemy3DebuffResistanceNumber.setVisible(true);
		    enemy3DeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator3.setVisible(true);
		    fadeInEnemySelectionIndicator3.play();
		});

		enemyPosition3.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator3.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemy3BleedResistanceNumber.setVisible(false);
	        enemy3BlightResistanceNumber.setVisible(false);
	        enemy3BurnResistanceNumber.setVisible(false);
	        enemy3StunResistanceNumber.setVisible(false);
	        enemy3MoveResistanceNumber.setVisible(false);
	        enemy3DebuffResistanceNumber.setVisible(false);
	        enemy3DeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator3.setOnFinished(event -> {
		        enemySelectionIndicator3.setVisible(false);
		       
		    });
		});

		enemyPosition4.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
			enemyNameText.setText(tempETeam[3].getName());
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemy4BleedResistanceNumber.setVisible(true);
		    enemy4BlightResistanceNumber.setVisible(true);
		    enemy4BurnResistanceNumber.setVisible(true);
		    enemy4StunResistanceNumber.setVisible(true);
		    enemy4MoveResistanceNumber.setVisible(true);
		    enemy4DebuffResistanceNumber.setVisible(true);
		    enemy4DeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator4.setVisible(true);
		    fadeInEnemySelectionIndicator4.play();
		});

		enemyPosition4.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator4.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemy4BleedResistanceNumber.setVisible(false);
	        enemy4BlightResistanceNumber.setVisible(false);
	        enemy4BurnResistanceNumber.setVisible(false);
	        enemy4StunResistanceNumber.setVisible(false);
	        enemy4MoveResistanceNumber.setVisible(false);
	        enemy4DebuffResistanceNumber.setVisible(false);
	        enemy4DeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator4.setOnFinished(event -> {
		        enemySelectionIndicator4.setVisible(false);
		       
		    });
		});
		// -------------------------------------------------------------

		
		
		heroHealthBarRedRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);
		fadeInHeroHPPos4.play();});
		heroHealthBarRedRectangle4.setOnMouseExited(e -> {fadeOutHeroHPPos4.play();});
		heroHPPos4.setVisible(false);
		heroHealthBarBlackRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);
		fadeInHeroHPPos4.play();});
		heroHealthBarBlackRectangle4.setOnMouseExited(e -> {fadeOutHeroHPPos4.play();});
		heroHPPos4.setVisible(false);
		 
		heroHealthBarRedRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);
		fadeInHeroHPPos3.play();});
		heroHealthBarRedRectangle3.setOnMouseExited(e -> {fadeOutHeroHPPos3.play();});
		heroHPPos3.setVisible(false);
		heroHealthBarBlackRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);
		fadeInHeroHPPos3.play();});
		heroHealthBarBlackRectangle3.setOnMouseExited(e -> {fadeOutHeroHPPos3.play();});
		heroHPPos3.setVisible(false);
		
		heroHealthBarRedRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);
		fadeInHeroHPPos2.play();});
		heroHealthBarRedRectangle2.setOnMouseExited(e -> {fadeOutHeroHPPos2.play();});
		heroHPPos2.setVisible(false);
		heroHealthBarBlackRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);
		fadeInHeroHPPos2.play();});
		heroHealthBarBlackRectangle2.setOnMouseExited(e -> {fadeOutHeroHPPos2.play();});
		heroHPPos2.setVisible(false);
		
		heroHealthBarRedRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);
		fadeInHeroHPPos1.play();});
		heroHealthBarRedRectangle1.setOnMouseExited(e -> {fadeOutHeroHPPos1.play();});
		heroHPPos1.setVisible(false);
		heroHealthBarBlackRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);
		fadeInHeroHPPos1.play();});
		heroHealthBarBlackRectangle1.setOnMouseExited(e -> {fadeOutHeroHPPos1.play();});
		heroHPPos1.setVisible(false);
		
		
		enemyHealthBarRedRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);
		fadeInEnemyHPPos4.play();});
		enemyHealthBarRedRectangle4.setOnMouseExited(e -> {fadeOutEnemyHPPos4.play();});
		enemyHPPos4.setVisible(false);
		enemyHealthBarBlackRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);
		fadeInEnemyHPPos4.play();});
		enemyHealthBarBlackRectangle4.setOnMouseExited(e -> {fadeOutEnemyHPPos4.play();});
		enemyHPPos4.setVisible(false);
		
		enemyHealthBarRedRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);
		fadeInEnemyHPPos3.play();});
		enemyHealthBarRedRectangle3.setOnMouseExited(e -> {fadeOutEnemyHPPos3.play();});
		enemyHPPos3.setVisible(false);
		enemyHealthBarBlackRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);
		fadeInEnemyHPPos3.play();});
		enemyHealthBarBlackRectangle3.setOnMouseExited(e -> {fadeOutEnemyHPPos3.play();});
		enemyHPPos3.setVisible(false);
		
		enemyHealthBarRedRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);
		fadeInEnemyHPPos2.play();});
		enemyHealthBarRedRectangle2.setOnMouseExited(e -> {fadeOutEnemyHPPos2.play();});
		enemyHPPos2.setVisible(false);
		enemyHealthBarBlackRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);
		fadeInEnemyHPPos2.play();});
		enemyHealthBarBlackRectangle2.setOnMouseExited(e -> {fadeOutEnemyHPPos2.play();});
		enemyHPPos2.setVisible(false);
		
		enemyHealthBarRedRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);
		fadeInEnemyHPPos1.play();});
		enemyHealthBarRedRectangle1.setOnMouseExited(e -> {fadeOutEnemyHPPos1.play();});
		enemyHPPos1.setVisible(false);
		enemyHealthBarBlackRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);
		fadeInEnemyHPPos1.play();});
		enemyHealthBarBlackRectangle1.setOnMouseExited(e -> {fadeOutEnemyHPPos1.play();});
		enemyHPPos1.setVisible(false);
		// -------------------------------------------------------------

		// Create a Pane for free positioning
		Pane root = new Pane();
		
		Pane heroAttackSplashScreen = new Pane();
		Pane enemyAttackSplashScreen = new Pane();
		
		Pane combatMenu = new Pane();
		
		
		heroAttackSplashScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); // semi-transparent black
		heroAttackSplashScreen.setVisible(false); // initially hidden
		FadeTransition fadeInheroAttackSplashScreen = new FadeTransition(Duration.millis(100), heroAttackSplashScreen);
		fadeInheroAttackSplashScreen.setFromValue(0);
		fadeInheroAttackSplashScreen.setToValue(1);
		twoSecDelay.play();
		FadeTransition fadeOutheroAttackSplashScreen = new FadeTransition(Duration.millis(100), heroAttackSplashScreen);
		fadeOutheroAttackSplashScreen.setFromValue(1);
		fadeOutheroAttackSplashScreen.setToValue(0);
		fadeOutheroAttackSplashScreen.setOnFinished(e -> heroAttackSplashScreen.setVisible(false)); // Hide after fading out
		
		enemyAttackSplashScreen.getChildren().addAll(heroAttackingBloodStain);
//		enemyAttackSplashScreen.getChildren().addAll(charAttacking, charDefending); // var

		// -------------------------------------------------------------

		
		enemyAttackSplashScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); // semi-transparent black
		enemyAttackSplashScreen.setVisible(false); // initially hidden
		FadeTransition fadeInenemyAttackSplashScreen = new FadeTransition(Duration.millis(100), enemyAttackSplashScreen);
		fadeInenemyAttackSplashScreen.setFromValue(0);
		fadeInenemyAttackSplashScreen.setToValue(1);
		twoSecDelay.play();
		FadeTransition fadeOutenemyAttackSplashScreen = new FadeTransition(Duration.millis(100), enemyAttackSplashScreen);
		fadeOutenemyAttackSplashScreen.setFromValue(1);
		fadeOutenemyAttackSplashScreen.setToValue(0);
		fadeOutenemyAttackSplashScreen.setOnFinished(e -> enemyAttackSplashScreen.setVisible(false)); // Hide after fading out
		
		enemyAttackSplashScreen.getChildren().addAll(enemyAttackingBloodStain);
//		enemyAttackSplashScreen.getChildren().addAll(charAttacking, charDefending); // var
		
		// -------------------------------------------------------------
		
		combatMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // semi-transparent black
		combatMenu.setVisible(false); // initially hidden
        FadeTransition fadeInCombatMenu = new FadeTransition(Duration.millis(300), combatMenu);
        fadeInCombatMenu.setFromValue(0);
        fadeInCombatMenu.setToValue(1);
        FadeTransition fadeOutCombatMenu = new FadeTransition(Duration.millis(300), combatMenu);
        fadeOutCombatMenu.setFromValue(1);
        fadeOutCombatMenu.setToValue(0);
        fadeOutCombatMenu.setOnFinished(e -> combatMenu.setVisible(false)); // Hide after fading out
        
        Group darkenGroup = new Group();
        darkenGroup.getChildren().addAll(heroPositions, enemyPositions);
        darkenGroup.getChildren().addAll(enemyInPosition1, enemyInPosition2, enemyInPosition3, enemyInPosition4);
        darkenGroup.getChildren().addAll(heroInPosition1, heroInPosition2, heroInPosition3, heroInPosition4);
        // Create a semi-transparent black overlay for the Group
        Rectangle darkOverlay = new Rectangle(1920, 1080); // Adjust size as needed
        darkOverlay.setFill(Color.rgb(0, 0, 0, 0.25)); // 25% opacity
        darkOverlay.setMouseTransparent(true); // Allow clicks to pass through
        // Add the overlay on top of the Group
        darkenGroup.getChildren().add(darkOverlay);
        
		// Add buttons, images
		root.getChildren().addAll(darkenGroup);
		root.getChildren().addAll(heroTurnTicker1, heroTurnTicker2, heroTurnTicker3, heroTurnTicker4);
		root.getChildren().addAll(enemyTurnTicker1, enemyTurnTicker2, enemyTurnTicker3, enemyTurnTicker4);
		root.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon);
		root.getChildren().addAll(enemyBleedResistanceNumberText, enemyBlightResistanceNumberText, enemyBurnResistanceNumberText, enemyStunResistanceNumberText, enemyMoveResistanceNumberText, enemyDebuffResistanceNumberText, enemyDeathResistanceNumberText);
		root.getChildren().addAll(enemy1BleedResistanceNumber,enemy1BlightResistanceNumber,enemy1BurnResistanceNumber,enemy1StunResistanceNumber,enemy1MoveResistanceNumber,enemy1DebuffResistanceNumber, enemy1DeathResistanceNumber);
		root.getChildren().addAll(enemy2BleedResistanceNumber,enemy2BlightResistanceNumber,enemy2BurnResistanceNumber,enemy2StunResistanceNumber,enemy2MoveResistanceNumber,enemy2DebuffResistanceNumber, enemy2DeathResistanceNumber);
		root.getChildren().addAll(enemy3BleedResistanceNumber,enemy3BlightResistanceNumber,enemy3BurnResistanceNumber,enemy3StunResistanceNumber,enemy3MoveResistanceNumber,enemy3DebuffResistanceNumber, enemy3DeathResistanceNumber);
		root.getChildren().addAll(enemy4BleedResistanceNumber,enemy4BlightResistanceNumber,enemy4BurnResistanceNumber,enemy4StunResistanceNumber,enemy4MoveResistanceNumber,enemy4DebuffResistanceNumber, enemy4DeathResistanceNumber);
		root.getChildren().addAll(skillButtonsPaladin);
		root.getChildren().add(skillButtonsAssassin);
		root.getChildren().add(skillButtonsWizard);
		root.getChildren().add(skillButtonsAlchemist);
		root.getChildren().add(passTurnButton);
		root.getChildren().addAll(skillButtonsImagesPaladin);
		root.getChildren().addAll(skillButtonsImagesAssassin);																			
		root.getChildren().addAll(skillButtonsImagesWizard);																			
		root.getChildren().addAll(skillButtonsImagesAlchemist);			
		root.getChildren().add(skillbuttonimagepass);

		root.getChildren().addAll(skillButtonSelectedFrame1,skillButtonSelectedFrame2,skillButtonSelectedFrame3,skillButtonSelectedFrame4); // ,skillButtonMoveSelectedFrame
		root.getChildren().addAll(heroNameTextPlate, enemyNameTextPlate);
		root.getChildren().add(turnOrderBarLeftAndRight);
		root.getChildren().addAll(heroNameText, enemyNameText);// keep name after name plate to avoid layering issues
		root.getChildren().add(roundNumberText);
		root.getChildren().addAll(heroSelectionIndicator1, heroSelectionIndicator2, heroSelectionIndicator3,heroSelectionIndicator4); 
		root.getChildren().addAll(enemySelectionIndicator1, enemySelectionIndicator2, enemySelectionIndicator3, enemySelectionIndicator4); // might need 4 of these.
		root.getChildren().addAll(heroHealthBarBlackRectangle1, heroHealthBarBlackRectangle2, heroHealthBarBlackRectangle3, heroHealthBarBlackRectangle4);
		root.getChildren().addAll(heroHealthBarRedRectangle1, heroHealthBarRedRectangle2, heroHealthBarRedRectangle3, heroHealthBarRedRectangle4);
		root.getChildren().addAll(enemyHealthBarBlackRectangle1, enemyHealthBarBlackRectangle2, enemyHealthBarBlackRectangle3, enemyHealthBarBlackRectangle4);
		root.getChildren().addAll(enemyHealthBarRedRectangle1, enemyHealthBarRedRectangle2, enemyHealthBarRedRectangle3, enemyHealthBarRedRectangle4);
		root.getChildren().addAll(heroHPPos4,heroHPPos3,heroHPPos2,heroHPPos1);
		root.getChildren().addAll(enemyHPPos4,enemyHPPos3,enemyHPPos2,enemyHPPos1);
		root.getChildren().addAll(moveDescriptionText,moveNameText);
		
		root.setBackground(new Background(backgroundImagePayoff)); // set background image	
		
		combatMenu.getChildren().addAll(menuBackground);
		combatMenu.getChildren().addAll(menuButtonFrame1);
		combatMenu.getChildren().addAll(menuBackButton,menuQuitButton);
		combatMenu.getChildren().add(menuQuitGameText);
		combatMenu.getChildren().add(menuBackButtonImage);
		// -------------------------------------------------------------
		// Manually position the HBoxes and back button
		heroPositions.setLayoutX(125); // Position X for hero positions
		heroPositions.setLayoutY(250); // Position Y for hero positions

		enemyPositions.setLayoutX(1025); // Position X for enemy positions
		enemyPositions.setLayoutY(250); // Position Y for enemy positions
		
		heroNameText.setLayoutX(-50); // hero's name
		heroNameText.setLayoutY(750);
		heroNameText.setScaleX(.65);
		heroNameText.setScaleY(.65);
		
		roundNumberText.setLayoutX(1340);
		roundNumberText.setLayoutY(45);
		roundNumberText.setScaleX(.95);
		roundNumberText.setScaleY(.95);
		
		enemyNameText.setLayoutX(1460);
		enemyNameText.setLayoutY(750);
		enemyNameText.setScaleX(.65);
		enemyNameText.setScaleY(.65);
		
		heroNameTextPlate.setLayoutX(-5);
		heroNameTextPlate.setLayoutY(710);

		enemyNameTextPlate.setLayoutX(1415);
		enemyNameTextPlate.setLayoutY(710);
		enemyNameTextPlate.setScaleX(-1);
		
		turnOrderBarLeftAndRight.setLayoutX(0);
		turnOrderBarLeftAndRight.setLayoutY(0);
		// -------------------------------------------------------------
		//health bars
		heroHealthBarRedRectangle1.setLayoutX(770); // 205 spacing
		heroHealthBarRedRectangle1.setLayoutY(600);
		heroHealthBarRedRectangle2.setLayoutX(565);
		heroHealthBarRedRectangle2.setLayoutY(600);
		heroHealthBarRedRectangle3.setLayoutX(360);
		heroHealthBarRedRectangle3.setLayoutY(600);
		heroHealthBarRedRectangle4.setLayoutX(155);
		heroHealthBarRedRectangle4.setLayoutY(600);
		
		heroHealthBarBlackRectangle1.setLayoutX(770);
		heroHealthBarBlackRectangle1.setLayoutY(600);
		heroHealthBarBlackRectangle2.setLayoutX(565);
		heroHealthBarBlackRectangle2.setLayoutY(600);
		heroHealthBarBlackRectangle3.setLayoutX(360);
		heroHealthBarBlackRectangle3.setLayoutY(600);
		heroHealthBarBlackRectangle4.setLayoutX(155);
		heroHealthBarBlackRectangle4.setLayoutY(600);

		enemyHealthBarRedRectangle1.setLayoutX(1055);
		enemyHealthBarRedRectangle1.setLayoutY(600);
		enemyHealthBarRedRectangle2.setLayoutX(1260);
		enemyHealthBarRedRectangle2.setLayoutY(600);
		enemyHealthBarRedRectangle3.setLayoutX(1465);
		enemyHealthBarRedRectangle3.setLayoutY(600);
		enemyHealthBarRedRectangle4.setLayoutX(1670);
		enemyHealthBarRedRectangle4.setLayoutY(600);
		
		enemyHealthBarBlackRectangle1.setLayoutX(1055);
		enemyHealthBarBlackRectangle1.setLayoutY(600);
		enemyHealthBarBlackRectangle2.setLayoutX(1260);
		enemyHealthBarBlackRectangle2.setLayoutY(600);
		enemyHealthBarBlackRectangle3.setLayoutX(1465);
		enemyHealthBarBlackRectangle3.setLayoutY(600);
		enemyHealthBarBlackRectangle4.setLayoutX(1670);
		enemyHealthBarBlackRectangle4.setLayoutY(600);
		// -------------------------------------------------------------
		moveDescriptionText.setLayoutX(25);
		moveDescriptionText.setLayoutY(800);
		
		moveNameText.setLayoutX(900);
		moveNameText.setLayoutY(800);
		// -------------------------------------------------------------
		heroTurnTicker1.setLayoutX(255);// 205 spacing
		heroTurnTicker1.setLayoutY(595);
		heroTurnTicker2.setLayoutX(460); 
		heroTurnTicker2.setLayoutY(595);
		heroTurnTicker3.setLayoutX(665);
		heroTurnTicker3.setLayoutY(595);
		heroTurnTicker4.setLayoutX(870);
		heroTurnTicker4.setLayoutY(595);
		// -------------------------------------------------------------
		enemyTurnTicker1.setLayoutX(1155); // 205 spacing
		enemyTurnTicker1.setLayoutY(595); 
		enemyTurnTicker2.setLayoutX(1360);
		enemyTurnTicker2.setLayoutY(595);
		enemyTurnTicker3.setLayoutX(1565);
		enemyTurnTicker3.setLayoutY(595);
		enemyTurnTicker4.setLayoutX(1770);
		enemyTurnTicker4.setLayoutY(595);
		// -------------------------------------------------------------

		enemyBLDResistanceIcon.setLayoutX(1412); // these 3 assets are weird, so need eyeball'd placements.
		enemyBLDResistanceIcon.setLayoutY(952); 
		enemyBLGTResistanceIcon.setLayoutX(1480); 
		enemyBLGTResistanceIcon.setLayoutY(953); 
		enemyBURNResistanceIcon.setLayoutX(1564);
		enemyBURNResistanceIcon.setLayoutY(950); 
		
		enemySTNResistanceIcon.setLayoutX(1621);//75 spacing
		enemySTNResistanceIcon.setLayoutY(950); 
		enemyMOVResistanceIcon.setLayoutX(1695);
		enemyMOVResistanceIcon.setLayoutY(950); 
		enemyDBFFResistanceIcon.setLayoutX(1776);
		enemyDBFFResistanceIcon.setLayoutY(950); 
		enemyDTHResistanceIcon.setLayoutX(1852); 
		enemyDTHResistanceIcon.setLayoutY(950); 
		
		enemyBleedResistanceNumberText.setLayoutX(1403);
		enemyBleedResistanceNumberText.setLayoutY(940); 
		enemyBlightResistanceNumberText.setLayoutX(1469);
		enemyBlightResistanceNumberText.setLayoutY(940); 
		enemyBurnResistanceNumberText.setLayoutX(1554);
		enemyBurnResistanceNumberText.setLayoutY(940); 
		enemyStunResistanceNumberText.setLayoutX(1629);
		enemyStunResistanceNumberText.setLayoutY(940); 
		enemyMoveResistanceNumberText.setLayoutX(1696);
		enemyMoveResistanceNumberText.setLayoutY(940); 
		enemyDebuffResistanceNumberText.setLayoutX(1771);
		enemyDebuffResistanceNumberText.setLayoutY(940); 
		enemyDeathResistanceNumberText.setLayoutX(1855);
		enemyDeathResistanceNumberText.setLayoutY(940); 
		
		enemyBleedResistanceNumberText.setScaleX(.65);
		enemyBleedResistanceNumberText.setScaleY(.65); 
		enemyBlightResistanceNumberText.setScaleX(.65);
		enemyBlightResistanceNumberText.setScaleY(.65); 
		enemyBurnResistanceNumberText.setScaleX(.65);
		enemyBurnResistanceNumberText.setScaleY(.65); 
		enemyStunResistanceNumberText.setScaleX(.65);
		enemyStunResistanceNumberText.setScaleY(.65); 
		enemyMoveResistanceNumberText.setScaleX(.65);
		enemyMoveResistanceNumberText.setScaleY(.65); 
		enemyDebuffResistanceNumberText.setScaleX(.65);
		enemyDebuffResistanceNumberText.setScaleY(.65); 
		enemyDeathResistanceNumberText.setScaleX(.65);
		enemyDeathResistanceNumberText.setScaleY(.65); 
		
		enemy1BleedResistanceNumber.setLayoutX(1418); // 77 spacing
		enemy1BleedResistanceNumber.setLayoutY(1020);
		enemy1BleedResistanceNumber.setScaleX(.75);
		enemy1BleedResistanceNumber.setScaleY(.75); 
		enemy1BlightResistanceNumber.setLayoutX(1495);
		enemy1BlightResistanceNumber.setLayoutY(1020); 
		enemy1BlightResistanceNumber.setScaleX(.75);
		enemy1BlightResistanceNumber.setScaleY(.75); 
		enemy1BurnResistanceNumber.setLayoutX(1572);
		enemy1BurnResistanceNumber.setLayoutY(1020); 
		enemy1BurnResistanceNumber.setScaleX(.75);
		enemy1BurnResistanceNumber.setScaleY(.75); 
		enemy1StunResistanceNumber.setLayoutX(1642);
		enemy1StunResistanceNumber.setLayoutY(1020); 
		enemy1StunResistanceNumber.setScaleX(.75);
		enemy1StunResistanceNumber.setScaleY(.75); 
		enemy1MoveResistanceNumber.setLayoutX(1719);
		enemy1MoveResistanceNumber.setLayoutY(1020); 
		enemy1MoveResistanceNumber.setScaleX(.75);
		enemy1MoveResistanceNumber.setScaleY(.75); 
		enemy1DebuffResistanceNumber.setLayoutX(1796);
		enemy1DebuffResistanceNumber.setLayoutY(1020); 
		enemy1DebuffResistanceNumber.setScaleX(.75);
		enemy1DebuffResistanceNumber.setScaleY(.75); 
		enemy1DeathResistanceNumber.setLayoutX(1873);
		enemy1DeathResistanceNumber.setLayoutY(1020); 
		enemy1DeathResistanceNumber.setScaleX(.75);
		enemy1DeathResistanceNumber.setScaleY(.75); 
		
		enemy2BleedResistanceNumber.setLayoutX(1418); // 77 spacing
		enemy2BleedResistanceNumber.setLayoutY(1020);
		enemy2BleedResistanceNumber.setScaleX(.75);
		enemy2BleedResistanceNumber.setScaleY(.75); 
		enemy2BlightResistanceNumber.setLayoutX(1495);
		enemy2BlightResistanceNumber.setLayoutY(1020); 
		enemy2BlightResistanceNumber.setScaleX(.75);
		enemy2BlightResistanceNumber.setScaleY(.75); 
		enemy2BurnResistanceNumber.setLayoutX(1572);
		enemy2BurnResistanceNumber.setLayoutY(1020); 
		enemy2BurnResistanceNumber.setScaleX(.75);
		enemy2BurnResistanceNumber.setScaleY(.75); 
		enemy2StunResistanceNumber.setLayoutX(1642);
		enemy2StunResistanceNumber.setLayoutY(1020); 
		enemy2StunResistanceNumber.setScaleX(.75);
		enemy2StunResistanceNumber.setScaleY(.75); 
		enemy2MoveResistanceNumber.setLayoutX(1719);
		enemy2MoveResistanceNumber.setLayoutY(1020); 
		enemy2MoveResistanceNumber.setScaleX(.75);
		enemy2MoveResistanceNumber.setScaleY(.75); 
		enemy2DebuffResistanceNumber.setLayoutX(1796);
		enemy2DebuffResistanceNumber.setLayoutY(1020); 
		enemy2DebuffResistanceNumber.setScaleX(.75);
		enemy2DebuffResistanceNumber.setScaleY(.75); 
		enemy2DeathResistanceNumber.setLayoutX(1873);
		enemy2DeathResistanceNumber.setLayoutY(1020); 
		enemy2DeathResistanceNumber.setScaleX(.75);
		enemy2DeathResistanceNumber.setScaleY(.75); 
		
		enemy3BleedResistanceNumber.setLayoutX(1418); // 77 spacing
		enemy3BleedResistanceNumber.setLayoutY(1020);
		enemy3BleedResistanceNumber.setScaleX(.75);
		enemy3BleedResistanceNumber.setScaleY(.75); 
		enemy3BlightResistanceNumber.setLayoutX(1495);
		enemy3BlightResistanceNumber.setLayoutY(1020); 
		enemy3BlightResistanceNumber.setScaleX(.75);
		enemy3BlightResistanceNumber.setScaleY(.75); 
		enemy3BurnResistanceNumber.setLayoutX(1572);
		enemy3BurnResistanceNumber.setLayoutY(1020); 
		enemy3BurnResistanceNumber.setScaleX(.75);
		enemy3BurnResistanceNumber.setScaleY(.75); 
		enemy3StunResistanceNumber.setLayoutX(1642);
		enemy3StunResistanceNumber.setLayoutY(1020); 
		enemy3StunResistanceNumber.setScaleX(.75);
		enemy3StunResistanceNumber.setScaleY(.75); 
		enemy3MoveResistanceNumber.setLayoutX(1719);
		enemy3MoveResistanceNumber.setLayoutY(1020); 
		enemy3MoveResistanceNumber.setScaleX(.75);
		enemy3MoveResistanceNumber.setScaleY(.75); 
		enemy3DebuffResistanceNumber.setLayoutX(1796);
		enemy3DebuffResistanceNumber.setLayoutY(1020); 
		enemy3DebuffResistanceNumber.setScaleX(.75);
		enemy3DebuffResistanceNumber.setScaleY(.75); 
		enemy3DeathResistanceNumber.setLayoutX(1873);
		enemy3DeathResistanceNumber.setLayoutY(1020); 
		enemy3DeathResistanceNumber.setScaleX(.75);
		enemy3DeathResistanceNumber.setScaleY(.75); 
		
		enemy4BleedResistanceNumber.setLayoutX(1418); // 77 spacing
		enemy4BleedResistanceNumber.setLayoutY(1020);
		enemy4BleedResistanceNumber.setScaleX(.75);
		enemy4BleedResistanceNumber.setScaleY(.75); 
		enemy4BlightResistanceNumber.setLayoutX(1495);
		enemy4BlightResistanceNumber.setLayoutY(1020); 
		enemy4BlightResistanceNumber.setScaleX(.75);
		enemy4BlightResistanceNumber.setScaleY(.75); 
		enemy4BurnResistanceNumber.setLayoutX(1572);
		enemy4BurnResistanceNumber.setLayoutY(1020); 
		enemy4BurnResistanceNumber.setScaleX(.75);
		enemy4BurnResistanceNumber.setScaleY(.75); 
		enemy4StunResistanceNumber.setLayoutX(1642);
		enemy4StunResistanceNumber.setLayoutY(1020); 
		enemy4StunResistanceNumber.setScaleX(.75);
		enemy4StunResistanceNumber.setScaleY(.75); 
		enemy4MoveResistanceNumber.setLayoutX(1719);
		enemy4MoveResistanceNumber.setLayoutY(1020); 
		enemy4MoveResistanceNumber.setScaleX(.75);
		enemy4MoveResistanceNumber.setScaleY(.75); 
		enemy4DebuffResistanceNumber.setLayoutX(1796);
		enemy4DebuffResistanceNumber.setLayoutY(1020); 
		enemy4DebuffResistanceNumber.setScaleX(.75);
		enemy4DebuffResistanceNumber.setScaleY(.75); 
		enemy4DeathResistanceNumber.setLayoutX(1873);
		enemy4DeathResistanceNumber.setLayoutY(1020); 
		enemy4DeathResistanceNumber.setScaleX(.75);
		enemy4DeathResistanceNumber.setScaleY(.75); 
		
		
		heroHPPos4.setLayoutX(180); //92
		heroHPPos4.setLayoutY(650); 
		heroHPPos4.setScaleX(.5); 
		heroHPPos4.setScaleY(.5); 
		
		heroHPPos3.setLayoutX(385); // space 205
		heroHPPos3.setLayoutY(650);
		heroHPPos3.setScaleX(.5); 
		heroHPPos3.setScaleY(.5); 
		
		heroHPPos2.setLayoutX(590); 
		heroHPPos2.setLayoutY(650); 
		heroHPPos2.setScaleX(.5); 
		heroHPPos2.setScaleY(.5); 
		
		heroHPPos1.setLayoutX(805); 
		heroHPPos1.setLayoutY(650);  
		heroHPPos1.setScaleX(.5); 
		heroHPPos1.setScaleY(.5); 
		
		enemyHPPos1.setLayoutX(1080); 
		enemyHPPos1.setLayoutY(650);   
		enemyHPPos1.setScaleX(.5); 
		enemyHPPos1.setScaleY(.5); 
		enemyHPPos2.setLayoutX(1285); 
		enemyHPPos2.setLayoutY(650);  
		enemyHPPos2.setScaleX(.5); 
		enemyHPPos2.setScaleY(.5); 
		enemyHPPos3.setLayoutX(1490); 
		enemyHPPos3.setLayoutY(650);  
		enemyHPPos3.setScaleX(.5); 
		enemyHPPos3.setScaleY(.5); 
		enemyHPPos4.setLayoutX(1695); 
		enemyHPPos4.setLayoutY(650); 
		enemyHPPos4.setScaleX(.5); 
		enemyHPPos4.setScaleY(.5); 
		// -------------------------------------------------------------
		heroSelectionIndicator1.setLayoutX(733);
		heroSelectionIndicator1.setLayoutY(420);
		heroSelectionIndicator2.setLayoutX(528);
		heroSelectionIndicator2.setLayoutY(420);
		heroSelectionIndicator3.setLayoutX(323);
		heroSelectionIndicator3.setLayoutY(420);
		heroSelectionIndicator4.setLayoutX(118);
		heroSelectionIndicator4.setLayoutY(420);
		// -------------------------------------------------------------
		enemySelectionIndicator1.setLayoutX(1019); // spacing of 205 between each
		enemySelectionIndicator1.setLayoutY(423);
		enemySelectionIndicator2.setLayoutX(1224);
		enemySelectionIndicator2.setLayoutY(423);
		enemySelectionIndicator3.setLayoutX(1429);
		enemySelectionIndicator3.setLayoutY(423);
		enemySelectionIndicator4.setLayoutX(1634);
		enemySelectionIndicator4.setLayoutY(423);
		// -------------------------------------------------------------
		// -------------------------------------------------------------
		// Manually position the images on top of the buttons
		enemyInPosition1.setLayoutX(875); // spacing of 205 in between each.
		enemyInPosition1.setLayoutY(190);
		enemyInPosition2.setLayoutX(1080);
		enemyInPosition2.setLayoutY(190);
		enemyInPosition3.setLayoutX(1235);
		enemyInPosition3.setLayoutY(190);
		enemyInPosition4.setLayoutX(1460);
		enemyInPosition4.setLayoutY(155);
		// -------------------------------------------------------------
		heroInPosition1.setLayoutX(585); // spacing of 205 in between each.
		heroInPosition1.setLayoutY(190);
		heroInPosition2.setLayoutX(380);
		heroInPosition2.setLayoutY(260);
		heroInPosition3.setLayoutX(145);
		heroInPosition3.setLayoutY(200);
		heroInPosition4.setLayoutX(-50);
		heroInPosition4.setLayoutY(210);
		// -------------------------------------------------------------
		
		skillButtonsPaladin.setLayoutX(696); // 585 sweet spot
		skillButtonsPaladin.setLayoutY(828);
		skillButtonsAssassin.setLayoutX(696); // 585 sweet spot
		skillButtonsAssassin.setLayoutY(828);
		skillButtonsWizard.setLayoutX(696); // 585 sweet spot
		skillButtonsWizard.setLayoutY(828);
		skillButtonsAlchemist.setLayoutX(696); // 585 sweet spot
		skillButtonsAlchemist.setLayoutY(828);
		
		skillButtonsImagesPaladin.setLayoutX(696);
		skillButtonsImagesPaladin.setLayoutY(828);
		skillButtonsImagesPaladin.setMouseTransparent(true);
		skillButtonsImagesAssassin.setLayoutX(696);
		skillButtonsImagesAssassin.setLayoutY(828);
		skillButtonsImagesAssassin.setMouseTransparent(true);
		skillButtonsImagesWizard.setLayoutX(696);
		skillButtonsImagesWizard.setLayoutY(828);
		skillButtonsImagesWizard.setMouseTransparent(true);
		skillButtonsImagesAlchemist.setLayoutX(696);
		skillButtonsImagesAlchemist.setLayoutY(828);
		skillButtonsImagesAlchemist.setMouseTransparent(true);
		
		passTurnButton.setLayoutX(1202);
		passTurnButton.setLayoutY(828);
		
		skillbuttonimage1Paladin.setLayoutX(696);
		skillbuttonimage1Paladin.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2Paladin.setLayoutX(823);
		skillbuttonimage2Paladin.setLayoutY(828);
		skillbuttonimage3Paladin.setLayoutX(950);
		skillbuttonimage3Paladin.setLayoutY(828);
		skillbuttonimage4Paladin.setLayoutX(1077);
		skillbuttonimage4Paladin.setLayoutY(828);
		
		skillbuttonimage1Assassin.setLayoutX(696);
		skillbuttonimage1Assassin.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2Assassin.setLayoutX(823);
		skillbuttonimage2Assassin.setLayoutY(828);
		skillbuttonimage3Assassin.setLayoutX(950);
		skillbuttonimage3Assassin.setLayoutY(828);
		skillbuttonimage4Assassin.setLayoutX(1077);
		skillbuttonimage4Assassin.setLayoutY(828);
		
		skillbuttonimage1Wizard.setLayoutX(696);
		skillbuttonimage1Wizard.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2Wizard.setLayoutX(823);
		skillbuttonimage2Wizard.setLayoutY(828);
		skillbuttonimage3Wizard.setLayoutX(950);
		skillbuttonimage3Wizard.setLayoutY(828);
		skillbuttonimage4Wizard.setLayoutX(1077);
		skillbuttonimage4Wizard.setLayoutY(828);
		
		skillbuttonimage1Alchemist.setLayoutX(696);
		skillbuttonimage1Alchemist.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2Alchemist.setLayoutX(823);
		skillbuttonimage2Alchemist.setLayoutY(828);
		skillbuttonimage3Alchemist.setLayoutX(950);
		skillbuttonimage3Alchemist.setLayoutY(828);
		skillbuttonimage4Alchemist.setLayoutX(1077);
		skillbuttonimage4Alchemist.setLayoutY(828);
		
//		skillbuttonimagemove.setLayoutX(1094);
//		skillbuttonimagemove.setLayoutY(778);
		skillbuttonimagepass.setLayoutX(1207); // 1227
		skillbuttonimagepass.setLayoutY(841); // 791
		skillbuttonimagepass.setScaleX(1.35);
		skillbuttonimagepass.setScaleY(1.35);
		
		skillButtonSelectedFrame1.setLayoutX(696);
		skillButtonSelectedFrame1.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillButtonSelectedFrame2.setLayoutX(823);
		skillButtonSelectedFrame2.setLayoutY(828);
		skillButtonSelectedFrame3.setLayoutX(950);
		skillButtonSelectedFrame3.setLayoutY(828);
		skillButtonSelectedFrame4.setLayoutX(1077);
		skillButtonSelectedFrame4.setLayoutY(828);
//		skillButtonMoveSelectedFrame.setLayoutX(1094);
//		skillButtonMoveSelectedFrame.setLayoutY(778);
		
		skillButtonSelectedFrame1.setScaleX(1.6);
		skillButtonSelectedFrame1.setScaleY(1.6);// 778 SWEET SPOT // 127 x multiple
		skillButtonSelectedFrame2.setScaleX(1.6);
		skillButtonSelectedFrame2.setScaleY(1.6);
		skillButtonSelectedFrame3.setScaleX(1.6);
		skillButtonSelectedFrame3.setScaleY(1.6);
		skillButtonSelectedFrame4.setScaleX(1.6);
		skillButtonSelectedFrame4.setScaleY(1.6);
//		skillButtonMoveSelectedFrame.setScaleX(1.6);
//		skillButtonMoveSelectedFrame.setScaleY(1.6);
		// -------------------------------------------------------------
		menuBackground.setLayoutX(450);
		menuBackground.setLayoutY(0);
		menuBackground.setScaleX(1);
		menuBackground.setScaleY(1);
		
		menuQuitButton.setLayoutX(925);
		menuQuitButton.setLayoutY(633);
		menuQuitButton.setScaleX(7.5);
		menuQuitButton.setScaleY(2.5);
		
		menuBackButtonImage.setLayoutX(1310);
		menuBackButtonImage.setLayoutY(103);
		menuBackButtonImage.setScaleX(1);
		menuBackButtonImage.setScaleY(1);
		
		menuBackButton.setLayoutX(1310);
		menuBackButton.setLayoutY(110);
		menuBackButton.setScaleX(1);
		menuBackButton.setScaleY(2);
		
		menuButtonFrame1.setLayoutX(650);
		menuButtonFrame1.setLayoutY(580);
		menuButtonFrame1.setScaleX(1);
		menuButtonFrame1.setScaleY(1);

		menuQuitGameText.setLayoutX(835);
		menuQuitGameText.setLayoutY(660);
		menuQuitGameText.setScaleX(1);
		menuQuitGameText.setScaleY(1);
		// -------------------------------------------------------------
		// Set the button sizes for all buttons in HBoxes
		heroPositions.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
		});

		enemyPositions.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
		});
		skillButtonsPaladin.getChildren().forEach(button -> {
		    ((RadioButton) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
		    ((RadioButton) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});
		skillButtonsAssassin.getChildren().forEach(button -> {
		    ((RadioButton) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
		    ((RadioButton) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});
		skillButtonsWizard.getChildren().forEach(button -> {
		    ((RadioButton) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
		    ((RadioButton) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});
		skillButtonsAlchemist.getChildren().forEach(button -> {
		    ((RadioButton) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
		    ((RadioButton) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});

		passTurnButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.015));
		passTurnButton.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));

		menuBackButton.setOnAction(e -> {
			fadeOutCombatMenu.playFromStart(); 
			root.setEffect(null); // remove blur
		});
		menuQuitButton.setOnAction(e -> {
			mediaPlayer.stop(); // Stop the music when the back button is pressed
			
			homePage(primaryStage);
		});
		primaryStage.setMaximized(true);
		
		Scene scene = new Scene(new StackPane(root, combatMenu), 1920, 1080);
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				 if (combatMenu.isVisible()) {
					 fadeOutCombatMenu.playFromStart(); // Start fade out transition
			            root.setEffect(null);    // Remove blur
			        } else {
			            combatMenu.setVisible(true); // Show the menu first
			            root.setEffect(blur);       // Apply blur to root
			            fadeInCombatMenu.playFromStart();     // Fade in the menu
			        }
			    }
		});

		scene.setCursor(customCursor);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(event -> {
			mediaPlayer.stop(); // Stop the music when the stage is closed
		});
		
		// This task checks if either team is dead, and if not
		// runs the primary combat control method.
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if (tempPTeamArray.checkGameOver() || tempETeamArray.checkGameOver()) {
					
					if(tempPTeamArray.checkGameOver()) {
						System.out.println("Player Team Defeated. Game Over.");
						timer.cancel();
						timer.purge();
					}
					else if (tempETeamArray.checkGameOver()) {
						System.out.println("Enemy Team Defeated. Round Over.");
						fightsWon++;
						timer.cancel();
						timer.purge();
						// Shop Scene, continue to next round?
					}
				}
					
				else {
						
					// Reset turn counter if order has been run through
					if (count > 7) {
						count = 0;
						round++;
					}
					
					count = flow.runCombat(count);
					
					// Health number updates:
					heroHPPos1.setText(Double.toString(tempPTeam[0].getHealth()));
					heroHPPos2.setText(Double.toString(tempPTeam[1].getHealth()));
					heroHPPos3.setText(Double.toString(tempPTeam[2].getHealth()));
					heroHPPos4.setText(Double.toString(tempPTeam[3].getHealth()));
					enemyHPPos1.setText(Double.toString(tempETeam[0].getHealth()));
					enemyHPPos2.setText(Double.toString(tempETeam[1].getHealth()));
					enemyHPPos3.setText(Double.toString(tempETeam[2].getHealth()));
					enemyHPPos4.setText(Double.toString(tempETeam[3].getHealth()));
					
					// Round number update:
					roundNumberText.setText("Round " + String.valueOf(round));
					
					// Current Characters Info (Update to move info once skills are finished)
					heroNameText.setText(flow.getCurrent().getName());
					moveDescriptionText.setText("DMG: " + flow.getCurrent().getDamage() + " Crit %: " + flow.getCurrent().getCritChance());
					
					// Hero Health Bars:
					heroHealthBarRedRectangle4.setWidth(tempPTeam[3].setHealthBarAmount());
					//heroHealthBarRedRectangle4.setX(0);
					heroHealthBarRedRectangle3.setWidth(tempPTeam[2].setHealthBarAmount());
					//heroHealthBarRedRectangle3.setX(204);
					heroHealthBarRedRectangle2.setWidth(tempPTeam[1].setHealthBarAmount());
					//heroHealthBarRedRectangle2.setX(409);
					heroHealthBarRedRectangle1.setWidth(tempPTeam[0].setHealthBarAmount());
					//heroHealthBarRedRectangle4.setX(614);
					
					// Enemy Health Bars:
					enemyHealthBarRedRectangle4.setWidth(tempETeam[3].setHealthBarAmount());
					//enemyHealthBarRedRectangle4.setX(0);
					enemyHealthBarRedRectangle3.setWidth(tempETeam[2].setHealthBarAmount());
					//enemyHealthBarRedRectangle3.setX(204);
					enemyHealthBarRedRectangle2.setWidth(tempETeam[1].setHealthBarAmount());
					//enemyHealthBarRedRectangle2.setX(409);
					enemyHealthBarRedRectangle1.setWidth(tempETeam[0].setHealthBarAmount());
					//enemyHealthBarRedRectangle1.setX(614);
				}
			}

		};
		
		round = 1;
		System.out.println("Starting Combat:");
		timer.schedule(task, 0, 5000);
		
		// Figure out game over scene change still
		if(tempPTeamArray.checkGameOver()) {
			gameOver(primaryStage);
			mediaPlayer.stop();
		}
		
		/*
		else if (tempETeamArray.checkGameOver()) {
			
		}
		*/
		
		
		// This will be what we implement and add animation stuff to 
		// when we get to that stage: 
		/*
		Timeline fiveSecondsCombat = new Timeline(
                			new KeyFrame(Duration.seconds(5), 
                			new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("this is called every 5 seconds on UI thread");
				if (tempPTeamArray.checkGameOver() || tempETeamArray.checkGameOver()) {
					System.out.println("Game Over.");
				}
					
				else {
						
					// Reset turn counter if order has been run through
					if (count > 7)
						count = 0;
					
					count = flow.runCombat(count);
				}
            }
        }));
		fiveSecondsCombat.setCycleCount(Timeline.INDEFINITE); // Probably won't be indefinite, have to look into this
		fiveSecondsCombat.play();
		*/

	}
	
	private void shop(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		
		// music player
		Media media = new Media(shopMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0), use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button back = new Button("Back");
		Text itemDescriptions = new Text("Item Descriptions will go here");
		Text item1Price = new Text("1");
		Text item2Price = new Text("2");
		Text item3Price = new Text("3");
		Text playerGold = new Text("playerGold");
		Text purchaseConfirmationText = new Text("Buy (item) for (char) (-X Gold)");
		Text exitShopText = new Text("exit shop");

		ToggleGroup itemSelection = new ToggleGroup();
		RadioButton item1Button = new RadioButton("item1");
		item1Button.setToggleGroup(itemSelection);
		RadioButton item2Button = new RadioButton("item2");
		item2Button.setToggleGroup(itemSelection);
		RadioButton item3Button = new RadioButton("item3");
		item3Button.setToggleGroup(itemSelection);
		Button purchaseConfirmationButton = new Button("purchaseConfirmationButton");

		Button charChoiceAlchemistButton= new Button("charChoiceAlchemist");
		Button charChoiceWizardButton= new Button("charChoiceWizard");
		Button charChoiceAssassinButton= new Button("charChoiceAssassin");
		Button charChoicePaladinButton= new Button("charChoicePaladin");


		itemDescriptions.setFont(DwarvenAxe);
		item1Price.setFont(DwarvenAxe);
		item2Price.setFont(DwarvenAxe);
		item3Price.setFont(DwarvenAxe);
		playerGold.setFont(DwarvenAxe);
		purchaseConfirmationText.setFont(DwarvenAxe);
		exitShopText.setFont(DwarvenAxe);
		
		itemDescriptions.setFill(Color.web("#d5d5d5"));
		item1Price.setFill(Color.web("#d5d5d5"));
		item2Price.setFill(Color.web("#d5d5d5"));
		item3Price.setFill(Color.web("#d5d5d5"));
		playerGold.setFill(Color.web("#FFEB80"));
		purchaseConfirmationText.setFill(Color.web("#d5d5d5"));
		exitShopText.setFill(Color.web("#d5d5d5"));
		
		ImageView shopKeeper = new ImageView(new Image("shopAssets/shopKeeper.png"));
		ImageView itemForSaleFrame1 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView itemForSaleFrame2 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView itemForSaleFrame3 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		
		ImageView itemForSaleFrameArrow1 = new ImageView(new Image("shopAssets/selectionArrow.png"));
		ImageView itemForSaleFrameArrow2 = new ImageView(new Image("shopAssets/selectionArrow.png"));
		ImageView itemForSaleFrameArrow3 = new ImageView(new Image("shopAssets/selectionArrow.png"));

		
		
		ImageView exitShopBanner = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView shopSeparatonBar1 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView shopSeparatonBar2 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView shopSeparatonBar3 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView purchaseConfirmationImage = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		
		ImageView goldIcon = new ImageView(new Image("shopAssets/goldIcon.png"));
		ImageView paladinPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Paladin/paladinPlayerIcon.png"));
		ImageView assassinPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Assassin/assassinPlayerIcon.png"));
		ImageView wizardPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Wizard/wizardPlayerIcon.png"));
		ImageView alchemistPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Alchemist/alchemistPlayerIcon.png"));
		ImageView charSelectedAlchemist = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedWizard = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedAssassin = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedPaladin = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		
		Image backgroundImagesetup = new Image("shopAssets/shopBackground.png");
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background																									// image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top																					// left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);
		
		Pane root = new Pane();
		
		purchaseConfirmationText.setMouseTransparent(true);
		alchemistPlayerIcon.setMouseTransparent(true);
		wizardPlayerIcon.setMouseTransparent(true);
		assassinPlayerIcon.setMouseTransparent(true);
		paladinPlayerIcon.setMouseTransparent(true);
		exitShopText.setMouseTransparent(true);
		
		charSelectedAlchemist.setVisible(false);
		charSelectedWizard.setVisible(false);
		charSelectedAssassin.setVisible(false);
		charSelectedPaladin.setVisible(false);
		
		itemForSaleFrameArrow1.setVisible(false);
		itemForSaleFrameArrow2.setVisible(false);
		itemForSaleFrameArrow3.setVisible(false);
		
		charChoiceAlchemistButton.setOpacity(0);
		charChoiceWizardButton.setOpacity(0);
		charChoiceAssassinButton.setOpacity(0);
		charChoicePaladinButton.setOpacity(0);
		purchaseConfirmationButton.setOpacity(0);
		item1Button.setOpacity(0);
		item2Button.setOpacity(0);
		item3Button.setOpacity(0);
		
		back.setOpacity(0);
		
		
		charChoiceAlchemistButton.setOnAction(e -> { // clicking on skill buttons makes the indicator visible, and disables visibility of all others.
			// i was going to do fade transistions here, but it seems like it doesn't work on button clicks, but only button hovers?
			charSelectedAlchemist.setVisible(true);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(false);
		});
		charChoiceWizardButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(true);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(false);
		});
		charChoiceAssassinButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(true);
			charSelectedPaladin.setVisible(false);
		});
		charChoicePaladinButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(true);
		});
//--------------------------------------------------------------------------------

			item1Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(true);
				itemForSaleFrameArrow2.setVisible(false);
				itemForSaleFrameArrow3.setVisible(false);
			});
			item2Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(false);
				itemForSaleFrameArrow2.setVisible(true);
				itemForSaleFrameArrow3.setVisible(false);
			});
			item3Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(false);
				itemForSaleFrameArrow2.setVisible(false);
				itemForSaleFrameArrow3.setVisible(true);
			});
			
//--------------------------------------------------------------------------------

		back.setOnMouseEntered(e -> {
			exitShopText.setFill(Color.web("#d10000"));
		});
		back.setOnMouseExited(e -> {
			exitShopText.setFill(Color.web("#d5d5d5"));
		});
		purchaseConfirmationButton.setOnMouseEntered(e -> {
			purchaseConfirmationText.setFill(Color.web("#d10000"));
		});
		purchaseConfirmationButton.setOnMouseExited(e -> {
			purchaseConfirmationText.setFill(Color.web("#d5d5d5"));
		});
		
//		charChoiceAlchemistButton.setVisible(false);

		// Add buttons, images, and back button to the root Pane

		root.getChildren().addAll(shopKeeper, purchaseConfirmationImage); 	
		root.getChildren().addAll(itemForSaleFrame1, itemForSaleFrame2, itemForSaleFrame3, shopSeparatonBar1, shopSeparatonBar2, shopSeparatonBar3, goldIcon);
		root.getChildren().addAll(itemForSaleFrameArrow1,itemForSaleFrameArrow2,itemForSaleFrameArrow3);
		root.getChildren().addAll(item1Button, item2Button, item3Button, purchaseConfirmationButton);
		root.getChildren().addAll(charChoiceAlchemistButton, charChoiceWizardButton, charChoiceAssassinButton, charChoicePaladinButton);
		root.getChildren().addAll(paladinPlayerIcon,assassinPlayerIcon,wizardPlayerIcon,alchemistPlayerIcon);
		root.getChildren().addAll(charSelectedAlchemist,charSelectedWizard,charSelectedAssassin,charSelectedPaladin);
		root.getChildren().addAll(playerGold, purchaseConfirmationText);
		root.getChildren().addAll(itemDescriptions,item1Price,item2Price,item3Price);
		root.getChildren().addAll(exitShopBanner);
		root.getChildren().addAll(back);
		root.getChildren().addAll(exitShopText);

		root.setBackground(new Background(backgroundImagePayoff)); // set background image

		// -------------------------------------------------------------
		
		// -------------------------------------------------------------
		item1Button.setLayoutX(1231);
		item1Button.setLayoutY(148);
		item2Button.setLayoutX(1481);
		item2Button.setLayoutY(148);
		item3Button.setLayoutX(1731);
		item3Button.setLayoutY(148);
		
		itemForSaleFrameArrow1.setLayoutX(1244);
		itemForSaleFrameArrow1.setLayoutY(5);
		itemForSaleFrameArrow1.setScaleX(1);
		itemForSaleFrameArrow1.setScaleY(3);
		itemForSaleFrameArrow1.setRotate(90);
		
		itemForSaleFrameArrow2.setLayoutX(1494);
		itemForSaleFrameArrow2.setLayoutY(5);
		itemForSaleFrameArrow2.setScaleX(1);
		itemForSaleFrameArrow2.setScaleY(3);
		itemForSaleFrameArrow2.setRotate(90);
		
		itemForSaleFrameArrow3.setLayoutX(1744);
		itemForSaleFrameArrow3.setLayoutY(5);
		itemForSaleFrameArrow3.setScaleX(1);
		itemForSaleFrameArrow3.setScaleY(3);
		itemForSaleFrameArrow3.setRotate(90);
		
		purchaseConfirmationButton.setLayoutX(1420);
		purchaseConfirmationButton.setLayoutY(990);
		purchaseConfirmationButton.setScaleX(4.9);
		purchaseConfirmationButton.setScaleY(3);
		
		purchaseConfirmationImage.setLayoutX(659);
		purchaseConfirmationImage.setLayoutY(607);
		purchaseConfirmationImage.setScaleX(.3);
		purchaseConfirmationImage.setScaleY(1.65);
		purchaseConfirmationImage.setRotate(90);
		
		
		item1Button.setScaleX(2.5);
		item1Button.setScaleY(13.75);
		item2Button.setScaleX(2.5);
		item2Button.setScaleY(13.75);
		item3Button.setScaleX(2.5);
		item3Button.setScaleY(13.75);

		// -------------------------------------------------------------
		// frames around character images that appear if the char is selected
		charSelectedAlchemist.setLayoutX(1157);
		charSelectedAlchemist.setLayoutY(772);
		charSelectedWizard.setLayoutX(1357);
		charSelectedWizard.setLayoutY(772);
		charSelectedAssassin.setLayoutX(1557);
		charSelectedAssassin.setLayoutY(772);
		charSelectedPaladin.setLayoutX(1757);
		charSelectedPaladin.setLayoutY(772);
		
		charSelectedAlchemist.setScaleX(1.7);
		charSelectedAlchemist.setScaleY(1.7);
		charSelectedWizard.setScaleX(1.7);
		charSelectedWizard.setScaleY(1.7);
		charSelectedAssassin.setScaleX(1.7);
		charSelectedAssassin.setScaleY(1.7);
		charSelectedPaladin.setScaleX(1.7);
		charSelectedPaladin.setScaleY(1.7);
		// -------------------------------------------------------------

		charChoiceAlchemistButton.setLayoutX(1148);
		charChoiceAlchemistButton.setLayoutY(812);
		charChoiceWizardButton.setLayoutX(1357);
		charChoiceWizardButton.setLayoutY(812);
		charChoiceAssassinButton.setLayoutX(1555);
		charChoiceAssassinButton.setLayoutY(812);
		charChoicePaladinButton.setLayoutX(1755);
		charChoicePaladinButton.setLayoutY(812);
		
		charChoiceAlchemistButton.setScaleX(1);
		charChoiceAlchemistButton.setScaleY(4.95);
		charChoiceWizardButton.setScaleX(1.1);
		charChoiceWizardButton.setScaleY(4.95);
		charChoiceAssassinButton.setScaleX(1.05);
		charChoiceAssassinButton.setScaleY(4.95);
		charChoicePaladinButton.setScaleX(1.1);
		charChoicePaladinButton.setScaleY(4.95);
		// -------------------------------------------------------------
		paladinPlayerIcon.setLayoutX(1785);
		paladinPlayerIcon.setLayoutY(800);
		assassinPlayerIcon.setLayoutX(1585);
		assassinPlayerIcon.setLayoutY(800);
		wizardPlayerIcon.setLayoutX(1385);
		wizardPlayerIcon.setLayoutY(800);
		alchemistPlayerIcon.setLayoutX(1185);
		alchemistPlayerIcon.setLayoutY(800);
		
		paladinPlayerIcon.setScaleX(1.75);
		paladinPlayerIcon.setScaleY(1.75);
		assassinPlayerIcon.setScaleX(1.75);
		assassinPlayerIcon.setScaleY(1.75);
		wizardPlayerIcon.setScaleX(1.75);
		wizardPlayerIcon.setScaleY(1.75);
		alchemistPlayerIcon.setScaleX(1.75);
		alchemistPlayerIcon.setScaleY(1.75);
		
		
		exitShopBanner.setLayoutX(-440);
		exitShopBanner.setLayoutY(-330);
		exitShopBanner.setScaleX(.3);
		exitShopBanner.setScaleY(.5);
		exitShopBanner.setRotate(90);
		exitShopText.setLayoutX(105);
		exitShopText.setLayoutY(75);
		exitShopText.setScaleX(1.8);
		exitShopText.setScaleY(1.8);
		back.setLayoutX(145); // Position X for back button
		back.setLayoutY(55); // Position Y for back button
		back.setScaleX(6);
		back.setScaleY(3.1);
		itemDescriptions.setLayoutX(1180);
		itemDescriptions.setLayoutY(600);
		
		purchaseConfirmationText.setLayoutX(1300);
		purchaseConfirmationText.setLayoutY(1010);
		purchaseConfirmationText.setScaleX(1.9);
		purchaseConfirmationText.setScaleY(1.9);
		// -------------------------------------------------------------
		shopSeparatonBar1.setLayoutX(1100);
		shopSeparatonBar1.setLayoutY(690);
		shopSeparatonBar1.setScaleX(1);
		shopSeparatonBar1.setScaleY(1.5);
		shopSeparatonBar2.setLayoutX(1100);
		shopSeparatonBar2.setLayoutY(950);
		shopSeparatonBar2.setScaleX(1);
		shopSeparatonBar2.setScaleY(1.5);
		shopSeparatonBar3.setLayoutX(1100);
		shopSeparatonBar3.setLayoutY(1175);
		shopSeparatonBar3.setScaleX(1);
		shopSeparatonBar3.setScaleY(1.5);
		
		item1Price.setLayoutX(1247);
		item1Price.setLayoutY(350);
		item2Price.setLayoutX(1497);
		item2Price.setLayoutY(350);
		item3Price.setLayoutX(1747);
		item3Price.setLayoutY(350);
		
		itemForSaleFrame1.setLayoutX(925);
		itemForSaleFrame1.setLayoutY(-230);
		itemForSaleFrame2.setLayoutX(1175);
		itemForSaleFrame2.setLayoutY(-230);
		itemForSaleFrame3.setLayoutX(1425);
		itemForSaleFrame3.setLayoutY(-230);
		
		itemForSaleFrame1.setScaleX(.5);
		itemForSaleFrame1.setScaleY(.5);
		itemForSaleFrame2.setScaleX(.5);
		itemForSaleFrame2.setScaleY(.5);
		itemForSaleFrame3.setScaleX(.5);
		itemForSaleFrame3.setScaleY(.5);
		
		goldIcon.setLayoutX(160);
		goldIcon.setLayoutY(900);
		goldIcon.setScaleX(1.5);
		goldIcon.setScaleY(1.5);
		playerGold.setLayoutX(375);
		playerGold.setLayoutY(965);
		playerGold.setScaleX(2.25);
		playerGold.setScaleY(2.25);
		// -------------------------------------------------------------
		shopKeeper.setLayoutX(20);
		shopKeeper.setLayoutY(200);
		shopKeeper.setFitWidth(0.55 * 1920);
		shopKeeper.setFitHeight(0.7 * 1080);
		back.setOnAction(e -> {
			mediaPlayer.stop(); // Stop the music when the back button is pressed
			homePage(primaryStage);
		});

		Scene scene = new Scene(root, 1920, 1080); // Create a scene with the Pane
		
		scene.setCursor(customCursor);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
			mediaPlayer.stop(); // Stop the music when the stage is closed
		});

	}
	
	private void gameOver(Stage primaryStage) {
		Media media = new Media(gameOver);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.05); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.play(); // music player

		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView exitGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView gameOverFrame = new ImageView(new Image("applicationImagesBackgrounds/gameOverFrame.png"));
		ImageView gameOverSkeletonLeft = new ImageView(new Image("GUIAssets/gameOverSkeleton.png"));
		ImageView gameOverSkeletonRight = new ImageView(new Image("GUIAssets/gameOverSkeleton.png"));

		Button play = new Button("New Journey");
		Button exit = new Button("Exit Game");
		Text exitGameText = new Text("Exit Game");
		Text newRunText = new Text("New Journey");
		Text gameOver = new Text("Game Over");
		
		play.setOnAction(e -> {
			try {
				battleScene(primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		exit.setOnAction(e -> homePage(primaryStage));

		gameOver.setFill(Color.web("#d10000"));
		gameOver.setFont(DwarvenAxe);
		newRunText.setFill(Color.web("#d5d5d5"));
		newRunText.setFont(DwarvenAxe);
		newRunText.setFill(Color.web("#d5d5d5"));
		exitGameText.setFont(DwarvenAxe);
		exitGameText.setFill(Color.web("#d5d5d5"));

		gameOverSkeletonLeft.setLayoutX(0);
		gameOverSkeletonLeft.setLayoutY(720);
		gameOverSkeletonLeft.setScaleX(1);
		gameOverSkeletonLeft.setScaleY(1);
		gameOverSkeletonLeft.setScaleX(-1);
		
		gameOverSkeletonRight.setLayoutX(1670);
		gameOverSkeletonRight.setLayoutY(720);
		gameOverSkeletonRight.setScaleX(1);
		gameOverSkeletonRight.setScaleY(1);
		
		
		gameOver.setLayoutX(900);
		gameOver.setLayoutY(350);
		gameOver.setScaleX(4);
		gameOver.setScaleY(4);

		gameOverFrame.setLayoutX(520);
		gameOverFrame.setLayoutY(0);
		gameOverFrame.setScaleX(1);
		gameOverFrame.setScaleY(1);

		enterGame.setLayoutX(310);
		enterGame.setLayoutY(435);
		enterGame.setScaleX(.5);
		enterGame.setScaleY(.8);
		enterGame.setRotate(90);
		enterGame.setMouseTransparent(true);
		exitGame.setLayoutX(310);
		exitGame.setLayoutY(635);
		exitGame.setScaleX(.5);
		exitGame.setScaleY(.8);
		exitGame.setRotate(90);
		exitGame.setMouseTransparent(true);
		
		newRunText.setLayoutX(895);
		newRunText.setLayoutY(775);
		newRunText.setScaleX(2);
		newRunText.setScaleY(2);
		newRunText.setMouseTransparent(true);

		play.setLayoutX(935); // Adjust X for play button
		play.setLayoutY(750); // Adjust Y for play button
		play.setScaleX(4.8);
		play.setScaleY(5);
		play.setOpacity(0);

		exit.setLayoutX(945); // Adjust X for play button
		exit.setLayoutY(950); // Adjust Y for play button
		exit.setScaleX(5.8);
		exit.setScaleY(5);
		exit.setOpacity(0);


		exitGameText.setLayoutX(905);
		exitGameText.setLayoutY(975);
		exitGameText.setScaleX(2);
		exitGameText.setScaleY(2);
		exitGameText.setMouseTransparent(true);


	    play.setOnMouseEntered(e -> {newRunText.setFill(Color.web("#d10000"));});
	    play.setOnMouseExited(e -> {newRunText.setFill(Color.web("#d5d5d5"));});
	    play.setOnAction(e -> {
	        try {
				battleScene(primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        mediaPlayer.stop();
	    });
	    
	    exit.setOnMouseEntered(e -> {exitGameText.setFill(Color.web("#d10000"));});
	    exit.setOnMouseExited(e -> {exitGameText.setFill(Color.web("#d5d5d5"));});
	    exit.setOnAction(e -> {
	        homePage(primaryStage);
	        mediaPlayer.stop();
	    });
		
		
	    Image image = new Image("applicationImagesBackgrounds/gameOverBackground.png");
	    // Define the background size and position
	    BackgroundSize size = new BackgroundSize(1, 1, true, true, false, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		
		Pane root = new Pane();
		root.getChildren().addAll(gameOverFrame,gameOverSkeletonLeft,gameOverSkeletonRight);
		root.getChildren().addAll(play, exit); 
		root.getChildren().addAll(enterGame, exitGame);
		root.getChildren().addAll(exitGameText, newRunText, gameOver);

	    root.setBackground(new Background(backgroundImage)); 
		
		root.setPadding(new Insets(20));
		Scene scene = new Scene(root, 1920, 1080);
		scene.setCursor(customCursor);

		scene.setCursor(customCursor);
		primaryStage.setTitle("Endless Mountain of Monsters");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());
	}
	
	private void goldEarned(Stage primaryStage) {
		AudioClip successSFX = new AudioClip(getClass().getResource("/SFX/goldEarnedScreenSuccessSFX.wav").toString());
		successSFX.setVolume(0.05); // Volume level (0.0 to 1.0)
		successSFX.play();

		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		
		ImageView toShopImage = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView goldIconLeft = new ImageView(new Image("shopAssets/goldIcon.png"));
		ImageView goldIconRight = new ImageView(new Image("shopAssets/goldIcon.png"));

		
		Button ShopButton = new Button("Shop");

		Text toShopText = new Text("Shop");
		Text goldEarnedText = new Text("Gold Earned");
//		Text goldEarnedVarText = new Text(gold Earned); // var

//		Text goldEarnedVarText = new Text("Gold Earned \n " + ); // var
	


		goldEarnedText.setFill(Color.web("#FFEB80"));
		goldEarnedText.setFont(DwarvenAxe);
		toShopText.setFill(Color.web("#d5d5d5"));
		toShopText.setFont(DwarvenAxe);

		goldEarnedText.setLayoutX(900);
		goldEarnedText.setLayoutY(300);
		goldEarnedText.setScaleX(3);
		goldEarnedText.setScaleY(3);
		
		toShopImage.setLayoutX(310);
		toShopImage.setLayoutY(635);
		toShopImage.setScaleX(.5);
		toShopImage.setScaleY(.8);
		toShopImage.setRotate(90);
		toShopImage.setMouseTransparent(true);
		
		toShopText.setLayoutX(950);
		toShopText.setLayoutY(970);
		toShopText.setScaleX(3);
		toShopText.setScaleY(3);
		toShopText.setMouseTransparent(true);

		ShopButton.setLayoutX(960); // Adjust X for play button
		ShopButton.setLayoutY(950); // Adjust Y for play button
		ShopButton.setScaleX(9);
		ShopButton.setScaleY(5);
		ShopButton.setOpacity(0);
		ShopButton.setOnMouseEntered(e -> {toShopText.setFill(Color.web("#d10000"));});
		ShopButton.setOnMouseExited(e -> {toShopText.setFill(Color.web("#d5d5d5"));});
		ShopButton.setOnAction(e -> shop(primaryStage));
	    
		goldIconLeft.setLayoutX(1290);
		goldIconLeft.setLayoutY(250);
		goldIconLeft.setScaleX(-2);
		goldIconLeft.setScaleY(2);
		
		
		goldIconRight.setLayoutX(570);
		goldIconRight.setLayoutY(250);
		goldIconRight.setScaleX(2);
		goldIconRight.setScaleY(2);
		
	    Image image = new Image("applicationImagesBackgrounds/goldEarnedBackground.png");
	    // Define the background size and position
	    BackgroundSize size = new BackgroundSize(1, 1, true, true, false, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		
		Pane root = new Pane();
		root.getChildren().addAll(ShopButton); 
		root.getChildren().addAll(toShopImage);
		root.getChildren().addAll(toShopText, goldEarnedText, goldIconRight, goldIconLeft);

	    root.setBackground(new Background(backgroundImage)); 
		
		root.setPadding(new Insets(20));
		Scene scene = new Scene(root, 1920, 1080);
		scene.setCursor(customCursor);
		primaryStage.setTitle("Endless Mountain of Monsters");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void textTutorial(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("This is where we will explain how to play the game.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
		root.setSpacing(10);
		root.setPadding(new Insets(20));

		// Bind button size properties to scale with the window
		root.getChildren().forEach(node -> {
			if (node instanceof Button) {
				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
			}
		});

		root.setStyle("-fx-background-color: black;"); // Set background color to black
		Scene scene = new Scene(root, 1920, 1080);
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}
	
	private void credits(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("CPS 298, Alex, Charlie, Killian, Tyler.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
		root.setSpacing(10);
		root.setPadding(new Insets(20));

		// Bind button size properties to scale with the window
		root.getChildren().forEach(node -> {
			if (node instanceof Button) {
				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
			}
		});

		root.setStyle("-fx-background-color: black;"); // Set background color to black
		Scene scene = new Scene(root, 1920, 1080);
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}
	
//	// putting this on the sideline, in case we decide we need a play options tab.
//	private void playOptions(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//
//		back.setOnAction(e -> homePage(primaryStage));
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(back);
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button width to 40% of the scene's width and height
//		root.getChildren().forEach(button -> {
//			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//		});
//
//		root.setStyle("-fx-background-color: black;");
//		Scene scene = new Scene(root, 1920, 1080);
//
////        primaryStage.setFullScreen(true);
//		primaryStage.setScene(scene);
////        primaryStage.requestFocus();
//
//	}



//	private void unlocks(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//		back.setOnAction(e -> homePage(primaryStage));
//
//		// Create a Label for displaying text
//		Label tutorialText = new Label("This is where we list all the unlocks the player has completed.");
//		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button size properties to scale with the window
//		root.getChildren().forEach(node -> {
//			if (node instanceof Button) {
//				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//			}
//		});
//
//		root.setStyle("-fx-background-color: black;"); // Set background color to black
//		Scene scene = new Scene(root, 1920, 1080);
//		scene.setCursor(customCursor);
//
//		primaryStage.setScene(scene);
//	}
//
//	private void stats(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//		back.setOnAction(e -> homePage(primaryStage));
//
//		// Create a Label for displaying text
//		Label tutorialText = new Label("This is where we will keep track of all the player's statistics.");
//		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button size properties to scale with the window
//		root.getChildren().forEach(node -> {
//			if (node instanceof Button) {
//				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//			}
//		});

//		root.setStyle("-fx-background-color: black;"); // Set background color to black
//		Scene scene = new Scene(root, 1920, 1080);
//		scene.setCursor(customCursor);
//
//		primaryStage.setScene(scene);
//	}


//	 private void setupLoadingStage(Stage primaryStage) {
//			Image cursorImage = new Image("GUIAssets/cursor.png");
//			Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//	        loadingStage = new Stage();
//	        StackPane loadingRoot = new StackPane();
//	        loadingRoot.setStyle("-fx-background-color: black;");
//	        Scene scene = new Scene(loadingRoot, 1920, 1080);
//	        
//		    loadingRoot.sceneProperty().addListener((obs, oldScene, newScene) -> {
//		    });
//	        
//			scene.setCursor(customCursor);
//	        loadingStage.setScene(scene);
//		    primaryStage.setMaximized(true);
//	    }
	public static void main(String[] args) {
		launch(args);
	}
}

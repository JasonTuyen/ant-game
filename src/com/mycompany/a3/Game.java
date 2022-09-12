package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.UITimer;

/**
 * Serves as the "controller" in the MVC architecture
 * Interacts with GameWorld, MapView, and ScoreView to complete the MVC architecture
 * Game extends built-in Form class
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see 	GameWorld and MapView and ScoreView
 * @since   1.0
 */
public class Game extends Form implements Runnable{
	
	/**
	 * The GameWorld "model" and MapView and ScoreView "views" 
	 * that the Game "controller" interacts with in the MVC architecture
	 */
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private ButtonStyled buttonAccelerate;
	private ButtonStyled buttonBrake;
	private ButtonStyled buttonLeft;
	private ButtonStyled buttonRight;
	private ButtonStyled buttonPause;
	private ButtonStyled buttonPosition;
	private AccelerateCmd myAccelerateCmd;
	private BrakeCmd myBrakeCmd;
	private LeftCmd myLeftCmd;
	private RightCmd myRightCmd;
	private PauseCmd myPauseCmd;
	private PositionCmd myPositionCmd;
	private BGSound bgSound;
	private boolean bPause = false;
	private boolean pauseState = false;
	private UITimer timer;
	
	/**
	 * Creates a new instance of Game 
	 * Sets up the layout of the "controller"
	 * Adds the "views" as an observer to the "model"
	 */
	public Game() {
		this.setLayout(new BorderLayout());
		
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		/**
		 * Create Toolbar and control containers for buttons
		 */
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle("The Path");
		
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 500);
		leftContainer.setScrollableY(false);
		this.addComponent(BorderLayout.WEST, leftContainer);
		
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 500);
		rightContainer.setScrollableY(false);
		this.addComponent(BorderLayout.EAST, rightContainer);
		
		Container botContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		botContainer.getAllStyles().setPadding(Component.LEFT, 600);
		botContainer.setScrollableX(false);
		this.addComponent(BorderLayout.SOUTH, botContainer);
		
		/**
		 * Code here to add and create Command objects for each command
		 * Add commands to either the side menu, title area or other side component
		 * Bind commands to keys and buttons as necessary
		 */
		CheckBox sound = new CheckBox("Sound");
		SoundCmd soundCmd = new SoundCmd(gw, sound);
		sound.setCommand(soundCmd);
		menu.addComponentToSideMenu(sound);
		
		AboutCmd aboutCmd = new AboutCmd();
		menu.addCommandToSideMenu(aboutCmd);
		
		HelpCmd helpCmd = new HelpCmd();
		menu.addCommandToRightBar(helpCmd);
		
		ExitCmd exitCmd = new ExitCmd(gw);
		menu.addCommandToSideMenu(exitCmd);
		
		myAccelerateCmd = new AccelerateCmd(gw);
		buttonAccelerate = new ButtonStyled(myAccelerateCmd);
		buttonAccelerate.setCommand(myAccelerateCmd);
		leftContainer.add(buttonAccelerate);
		menu.addCommandToSideMenu(myAccelerateCmd);
		addKeyListener('a', myAccelerateCmd);
		
		myBrakeCmd = new BrakeCmd(gw);
		buttonBrake = new ButtonStyled(myBrakeCmd);
		buttonBrake.setCommand(myBrakeCmd);
		rightContainer.add(buttonBrake);
		addKeyListener('b', myBrakeCmd);
		
		myLeftCmd = new LeftCmd(gw);
		buttonLeft = new ButtonStyled(myLeftCmd);
		buttonLeft.setCommand(myLeftCmd);
		leftContainer.add(buttonLeft);
		addKeyListener('l', myLeftCmd);
		
		myRightCmd = new RightCmd(gw);
		buttonRight = new ButtonStyled(myRightCmd);
		buttonRight.setCommand(myRightCmd);
		rightContainer.add(buttonRight);
		addKeyListener('r', myRightCmd);
		
		myPauseCmd = new PauseCmd(this);
		buttonPause = new ButtonStyled(myPauseCmd);
		buttonPause.setCommand(myPauseCmd);
		botContainer.add(buttonPause);
		addKeyListener('p', myPauseCmd);
		
		myPositionCmd = new PositionCmd(gw, mv);
		buttonPosition = new ButtonStyled(myPositionCmd);
		buttonPosition.setCommand(myPositionCmd);
		botContainer.add(buttonPosition);
		Container listenContainer = new Container();
		listenContainer.addPointerPressedListener(myPositionCmd);
		this.addComponent(BorderLayout.CENTER, listenContainer);
		
		/**
		 * @see GameObject#collidesWith()
		 * @deprecated As of version 3.0
		 *
		FlagCmd myFlagCmd = new FlagCmd(gw);
		ButtonStyled buttonFlag = new ButtonStyled(myFlagCmd);
		buttonFlag.setCommand(myFlagCmd);
		botContainer.add(buttonFlag);
		
		FoodCmd myFoodCmd = new FoodCmd(gw);
		ButtonStyled buttonFood = new ButtonStyled(myFoodCmd);
		buttonFood.setCommand(myFoodCmd);
		botContainer.add(buttonFood);
		addKeyListener('f', myFoodCmd);
		
		SpiderCmd mySpiderCmd = new SpiderCmd(gw);
		ButtonStyled buttonSpider = new ButtonStyled(mySpiderCmd);
		buttonSpider.setCommand(mySpiderCmd);
		botContainer.add(buttonSpider);
		addKeyListener('g', mySpiderCmd);
		 */
		
		/**
		 * @see Game#run()
		 * @deprecated As of version 3.0
		 *
		TickCmd myTickCmd = new TickCmd(gw);
		ButtonStyled buttonTick = new ButtonStyled(myTickCmd);
		buttonTick.setCommand(myTickCmd);
		botContainer.add(buttonTick);
		addKeyListener('t', myTickCmd);
		 */
		
		/**
		 * MapView and ScoreView are added to the layout
		 */
		this.addComponent(BorderLayout.NORTH, sv);
		this.addComponent(BorderLayout.CENTER, mv);
		this.show();
		
		/**
		 * Code here to query MapView's width and height
		 * and set them as GameWorld's width and height
		 * @see GameWorld#setGWHeight(double) and GameWorld#setGWWidth(double)
		 * @see MapView#getMapHeight() and MapView#getMapWidth()
		 */
		gw.setGWHeight(mv.getMapHeight());
		gw.setGWWidth(mv.getMapWidth());
		
		/**
		 * Code here to initiate the game work and create sounds and timer
		 */
		gw.init();
		gw.createSounds();
		revalidate();
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		bgSound = new BGSound("background.wav");
		bgSound.play();
	}
	
	/**
	 * Method that calls the tick command from GameWorld 
	 * and makes use of UITimer to automatically tick the game
	 */
	public void run() {
		gw.ticked(20);
	}
	
	/**
	 * Method that pauses game and disable necessary things when game is paused
	 */
	public void pauseGame() {
		this.pauseState = !pauseState;
		
		buttonAccelerate.setEnabled(!buttonAccelerate.isEnabled());
		buttonBrake.setEnabled(!buttonBrake.isEnabled());
		buttonLeft.setEnabled(!buttonLeft.isEnabled());
		buttonRight.setEnabled(!buttonRight.isEnabled());
		
		if(pauseState) {
			timer.cancel();
			gw.setGameState();
			bgSound.pause();
			buttonPause.setText("Play");
			removeKeyListener('a', myAccelerateCmd);
			removeKeyListener('b', myBrakeCmd);
			removeKeyListener('l', myLeftCmd);
			removeKeyListener('r', myRightCmd);
		}else {
			timer.schedule(20, true, this);
			gw.setGameState();
			bgSound.play();
			buttonPause.setText("Pause");
			addKeyListener('a', myAccelerateCmd);
			addKeyListener('b', myBrakeCmd);
			addKeyListener('l', myLeftCmd);
			addKeyListener('r', myRightCmd);
		}
	}
	
	/**
	 * @deprecated As of version 2.0
	 *
	private void play() {
		Label myLabel = new Label ("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.add(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
						case 'a':
							gw.accelerate();
							break;
						case 'b':
							gw.brake();
							break;
						case 'l':
							gw.left();
							break;
						case 'r':
							gw.right();
							break;
						case '1':
							gw.flagCollision(1);
							break;
						case '2':
							gw.flagCollision(2);
							break;
						case '3':
							gw.flagCollision(3);
							break;
						case '4':
							gw.flagCollision(4);
							break;
						case 'f':
							gw.foodCollision();
							break;
						case 'g':
							gw.gottenSpiderCollision();
							break;
						case 't':
							gw.ticked();
							break;
						case 'd':
							gw.display();
							break;
						case 'm':
							gw.map();
							break;
						case 'x':
							gw.exit();
							break;
						case 'y':
							gw.yes();
							break;
						case 'n':
							gw.no();
							break;
						default:
							System.out.println("Invalid input");
							break;
					}
			}
		}
		);
	}
	*
	*/
}

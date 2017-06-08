
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
public class GGTop extends OverChar {
	static int count = 0;
	public 
	boolean first = true;
	boolean second = true, third = true;
	public static boolean clear = false;
	int maxX;
	int maxNegX;
	int maxY;
	int maxNegY;
	int delay=0;
	double scale = 1360.0/MainMenu.width;
	int ranX;//for random enemy x
	int ranY;//for random enemy y
	//****************************************************************************

	public enum STATE {			// states of map areas (base, field, sidescroller map, etc)
		Field,
		Base,
		Stone,
		Cave,
		Realm,
		Grass
	};

	//******************************************************************************


	public static STATE mapState = STATE.Realm;

	public GGTop(int x, int y, ID player) {
		super(x, y, player, LoadImage("basemap.png"),25/1600.0*MainMenu.width);
		maxX = (int)(-555000/1360.0*MainMenu.width);
		maxNegX =  (int)(3554000.0/1360*MainMenu.width);
		maxY = (int)(-1110000.0/1360*MainMenu.width);
		maxNegY = (int)(2954000.0/1360*MainMenu.width);
	}

	public void tick() {

		//System.out.println(mapState);

		if(id==ID.Player){
			if(velX!=0&&velY!=0){
				if(velX>0){
					OverChar.playerX+=Math.sqrt((velX*velX)/2);// moves the character around
				}else OverChar.playerX-=Math.sqrt((velX*velX)/2);
				if(velY>0){	
					OverChar.playerY+=Math.sqrt((velY*velY)/2);
				}else OverChar.playerY-=Math.sqrt((velY*velY)/2);
			}else{
				OverChar.playerX += velX;
				OverChar.playerY += velY;
			}



			for(int i = 2;i<Handler.object.size();i++){
				OverChar check = Handler.object.get(i);
				if(check.id==ID.Enemy&&check.Overlap(playerX, playerY)){
					playerHealth -= check.collisionDamage;
				}
			}
			for(int i = 0;i<Handler.eBullet.size();i++){
				EBullet check = Handler.eBullet.get(i);
				if(check.Overlap(playerX,playerY)){
					playerHealth-=check.collisionDamage;
					Handler.removeEBullet(check);
				}
			}
			if(playerHealth<=0){
				//System.out.println("You died");//put actual death code here<<<<<<<<<<<<<<<<<<<<<<<<<<DEATH
				System.exit(0);
			}
			return;
		}
		if (id == ID.Map)
		{
			if (playerX <= maxX) playerX = maxX;
			if (playerX >= maxNegX) playerX = maxNegX;
			if (playerY <= maxY) playerY = maxY;
			if (playerY >= maxNegY) playerY = maxNegY;


			if (mapState == STATE.Base)
			{
				if (first)
				{
					x = (int)(30000.0/1360*MainMenu.width);
					y = (int)(-860000.0/1360*MainMenu.width);
					map = LoadImage("basemap.png");
					System.out.println("start");
					//first = false;
					//second = true;
					size = 25/1600.0*MainMenu.width;
					maxX = (int)(-555000/1360.0*MainMenu.width);
					maxNegX =  (int)(3554000.0/1360*MainMenu.width);
					maxY = (int)(-1110000.0/1360*MainMenu.width);
					maxNegY = (int)(2954000.0/1360*MainMenu.width);
					OverChar.playerX=0;
					OverChar.playerY=0;
					first = false;
				}
				
				if (playerX >= 3544000.0/1360.0*MainMenu.width && playerY >= 2375000.0/1360.0*MainMenu.width && playerY<= 2500000.0/1360.0*MainMenu.width)	// entering the battle field area
				{		//*****************************************************************************
					mapState = STATE.Field;
					first = true;
				}

				if (playerY >= 3061061/scale && playerX >= 1286000/scale && playerX <= 1400347/scale)
				{
					mapState = STATE.Cave;
					first = true;
				}
				if (playerY >= 2954000/scale && playerX >= 2538799/scale && playerX <= 2700299/scale)
				{
					mapState = STATE.Grass;
					first = true;
				}

			}
			else if (mapState == STATE.Field)
			{		
				if (first)
				{
					x = (int)(30000.0/1360*MainMenu.width);
					y = (int)(-860000.0/1360*MainMenu.width);
					map = LoadImage("ruckss2.png");
					//second = false;
					//first = true;
					size = 7/1600.0*MainMenu.width;
					maxX = (int)(56090/1360.0*MainMenu.width);
					maxNegX =  (int)(6944789/1360.0*MainMenu.width);
					maxY =(int)(-827293/1360.0*MainMenu.width);
					maxNegY = (int)(3002948/1360.0*MainMenu.width);
					OverChar.playerX=0;
					OverChar.playerY=0;

					Handler.addObject(new Spawner((int)((445970)/scale), (int)(( 311594)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					Handler.addObject(new Spawner((int)((377970)/scale), (int)((-827293)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					Handler.addObject(new Spawner((int)((3134449)/scale), (int)((-835793)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					Handler.addObject(new Spawner((int)((6724109)/scale), (int)((-915793)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					Handler.addObject(new Spawner((int)((6003724)/scale), (int)((2647057)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"),1.3/scale));
					Handler.addObject(new Spawner((int)((308933)/scale),(int) ((2461056)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					Handler.addObject(new Spawner((int)((5151110)/scale), (int)((554649)/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 1.3/scale));
					
					first = false;
				}

				if (playerX >= 6944788/1360.0*MainMenu.width && playerY >= 1046653/1360.0*MainMenu.width && playerY <= 1335653/1360.0*MainMenu.width)	// returning to the main area
				{
					mapState = STATE.Stone;
					first = true;
				}
				
			}
			else if (mapState == STATE.Cave)
			{
				if(first)
				{
					//if (second)
					//{
					x = (int)(30000.0/1360*MainMenu.width);
					y = (int)(-860000.0/1360*MainMenu.width);
					map = LoadImage("grassss2.jpg");
					//second = false;
					//first = true;
					size = 7/1600.0*MainMenu.width;
					maxX = (int)(56090/1360.0*MainMenu.width);
					maxNegX =  (int)(6910788/1360.0*MainMenu.width);
					maxY =(int)(-827293/1360.0*MainMenu.width);
					maxNegY = (int)(3002948/1360.0*MainMenu.width);
					OverChar.playerX = 0;
					Handler.addObject(new NGSpawner((int)(3706347/scale), (int)(1151818/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 3/scale));
					first = false;
				}
				if (playerX >= 6910788/1360.0*MainMenu.width && playerY >= 880406/1360.0*MainMenu.width && playerY <= 1135406/1360.0*MainMenu.width)	// returning to the main area
				{
					mapState = STATE.Realm;
					first = true;
				}
			}
			else if (mapState == STATE.Grass)
			{
				if(first)
				{
					//if (second)
					//{
					x = (int)(30000.0/1360*MainMenu.width);
					y = (int)(-860000.0/1360*MainMenu.width);
					map = LoadImage("muddd2.jpg");
					//second = false;
					//first = true;
					size = 7/1600.0*MainMenu.width;
					
					maxX = (int)(56090/1360.0*MainMenu.width);
					maxNegX =  (int)(6910788/1360.0*MainMenu.width);
					maxY =(int)(-827293/1360.0*MainMenu.width);
					maxNegY = (int)(3002948/1360.0*MainMenu.width);
					
					OverChar.playerX = 0;
					//Handler.addObject(new NGSpawner((int)(3706347/scale), (int)(1151818/scale), ID.Enemy,OverChar.LoadImage("Sp clone.png"), 3/scale));
					first = false;
				}
				if (playerX >= 6910788/1360.0*MainMenu.width && playerY >= 315778/1360.0*MainMenu.width && playerY <= 2070300/1360.0*MainMenu.width)	// returning to the main area
				{
					mapState = STATE.Base;
					first = true;
				}
			}
			else if (mapState == STATE.Stone)
			{
				if(first)
				{
					//if (second)
					//{
					x = (int)(100205.0/1440*MainMenu.width);
					y = (int)(-744879.0/1440*MainMenu.width);
					map = LoadImage("stoneS2.png");
					//second = false;
					//first = true;
					size = 7/1600.0*MainMenu.width;
					maxX = (int)(-436158/1360.0*MainMenu.width);
					maxNegX =  (int)(4031394/1360.0*MainMenu.width);
					maxY =(int)(-956700/1360.0*MainMenu.width);
					maxNegY = (int)(3535835/1360.0*MainMenu.width);
					OverChar.playerX = 0;
					Handler.addObject(new BossOne(OverChar.playerX,OverChar.playerY-500000,ID.Enemy,OverChar.LoadImage("Boss1-1.png"),4));
					first = false;	
				}
				if (playerX >= 4031394/scale && playerY >= -956700)
				{
					mapState = STATE.Base;
				}
				
				
			}
			else if (mapState == STATE.Realm)
			{
				if(first)
				{
					//if (second)
					//{
					x = (int)(100205.0/1440*MainMenu.width);
					y = (int)(-744879.0/1440*MainMenu.width);
					map = LoadImage("cardsss2.png");
					//second = false;
					//first = true;
					size = 7/1600.0*MainMenu.width;
					maxX = (int)(-436158/1360.0*MainMenu.width);
					maxNegX =  (int)(4031394/1360.0*MainMenu.width);
					maxY =(int)(-956700/1360.0*MainMenu.width);
					maxNegY = (int)(3535835/1360.0*MainMenu.width);
					OverChar.playerX = 0;
					//Handler.addObject(new BossTwo(1797618/1360*MainMenu.width,1289567/1360*MainMenu.width,ID.Enemy,OverChar.LoadImage("Sp clone.png"),0.5));
					first = false;
					
				}
			}
		}
	}


	//System.out.println(velX + "   " + velY);

	public void render(Graphics g, double angle) {	// enter character picture and information here

		if(id == ID.Map)		//creates the map
		{

			//*************************************************************

			AffineTransform at = AffineTransform.getTranslateInstance((x-playerX)/1000, (y-playerY)/1000);	// moves the picture around

			at.scale(size,size);

			//at.scale(25, 25);

			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(map, at, null);		// draws it

		}


		else if (id == ID.Player) // creates the player
		{
			int mouseY = MouseInfo.getPointerInfo().getLocation().y;		// mouse tracking
			int mouseX = MouseInfo.getPointerInfo().getLocation().x;


			BufferedImage map = LoadImage("GGtop.png");
			//AffineTransform tat = AffineTransform.getTranslateInstance(0,0);
			AffineTransform tat = AffineTransform.getTranslateInstance((MainMenu.width/2-map.getWidth()/1600.0*MainMenu.width+MainMenu.offsetx), MainMenu.height/2-map.getHeight()/1600.0*MainMenu.width+MainMenu.offsety);
			tat.scale(2.0/1600.0*MainMenu.width, 2.0/1600.0*MainMenu.width);
			angle = Math.atan2(MainMenu.height/2.0 - mouseY+MainMenu.offsety, MainMenu.width/2.0 - mouseX+MainMenu.offsetx) - Math.PI/2;
			tat.rotate(angle, map.getWidth()/2, map.getHeight()/2);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(map, tat, null);

		}


	}

}


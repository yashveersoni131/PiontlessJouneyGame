package com.pointless.journey.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.pointless.journey.framework.GameObject;
import com.pointless.journey.framework.ObjectId;
import com.pointless.journey.objects.Block;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		
		for (int i = 0; i < object.size(); i++){
			
			tempObject = object.get(i);//setting tempObject to current object in list
			
			tempObject.tick(object);
			
		}
		
	}
	
	public void render(Graphics g){
		
		for (int i = 0; i < object.size(); i++){
			
			tempObject = object.get(i);//setting tempObject to current object in list
			
			tempObject.render(g);
			
		}
		
	}
	
	public void addObject(GameObject object){
		
		this.object.add(object);
		
	}
	public void removeObject(GameObject object){
		
		this.object.remove(object);
		
	}
	
	public void createLevel(){
		
		for(int xx = 0; xx < Game.WIDTH*2; xx += 32)
			addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
		
		for(int yy = 0; yy < Game.HEIGHT+32; yy += 32)
			addObject(new Block(0, yy-6, ObjectId.Block));
		
		for (int xx = 200; xx < 600; xx += 32)
			addObject(new Block(xx, 560, ObjectId.Block));
		
		for (int xx = 800; xx < 1200; xx += 32)
			addObject(new Block(xx, 460, ObjectId.Block));
		
	}


}

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) 
	{
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	
	public void automaticMoves() {
		for (int i = 0; i < objects.size() ; i++) 
		{
			GameObject object = objects.get(i);
			object.automaticMove();
			performAttack(object);
		}
		this.organizeList(); //los elementos muertos se sacan de la lista
	}

	public void computerActions() {
		for(int i = 0; i < objects.size(); i++)
		{
			GameObject object = objects.get(i);
			object.computerAction();
		}
	}
	
	public void performAttack(GameObject weapon)//Lo hago al revés porq si hay bomba para que impacte
	{//antes con laser que con UCMShip
		for(int i = objects.size() - 1; i >= 0; i--)
		{
			GameObject object = objects.get(i);
			weapon.performAttack(object);
		}
	}
	
	public void receiveAttack(GameObject victim)
	{
		int i = 0;
		while(i < objects.size() && victim.isAlive())
		{
			GameObject object = objects.get(i);
			object.performAttack(victim);
			i++;
		}
	}
	
	public void organizeList()
	{
		int i = 0;
		while(i < objects.size())
		{
			GameObject object = objects.get(i);
			if(!object.isAlive())
			{
				object.onDelete();
				objects.remove(object);
			}
			else 
				i++;
		}
	}

	public String toString(Position position) {
		String obj = "";
		int i = 0;
		while(i < objects.size() && obj == "")
		{
			GameObject object = objects.get(i);
			if(object.isOnPosition(position))
				obj = object.toString();
			i++;
		}
		return obj;
	}
	
	
	
}

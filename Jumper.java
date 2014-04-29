//Barak Zhou
//https://github.com/Fitzy97/GridWorld
//APCS PD 8
//HW #33
//2014-04-29

import info.gridworld.*;

import java.awt.Color;

public class Jumper extends Actor {
	public Jumper() {
		setColor( Color.PINK );
	} //end constructor
	
	public Jumper( Color JumperColor ) {
		setcolor( JumperColor );
	} //end constructor
	
	public void act() {
	//either jump or turn
		if (canJump()) jump();
		else turn();
	} //end method act()
	
	public void turn() {
		setDirection(getDirection + Location.HALF_RIGHT);
	} //end method turn()
	
	public void jump() {
		Grid<Actor> gr = getGrid(); //get grid this actor is in
		if ( gr == null ) return;
		Location loc = getLocation();
		Location next = l.getAdjacentLocation(getDirection()); //finds adj location in your direction
		Location nextnext = next.getAdjacentLocation(getDirection()); //two panels away
		if ( gr.isValid( nextnext ) ) moveTo(nextnext);
		else removeSelfFromGrid();
	} //end method jump()
	
	public boolean canJump() {
		Grid<Actor> gr = getGrid();
		if ( gr == null ) return false;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		//Location next = getLocation().getAdjacentLocation(getDirection());
		if (!gr.isValid(next)) return false;
		Actor neighbor = gr.get(next);
		//look at the neighbor
		if ( !( (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock))) return false;
		Location nextnext = next.getAdjacentLocation(getDirection());
		if ( !gr.isValid(nextnext) ) return false;
		
		neighbor = gr.get(nextnext);
		return ( neighbor == null ) || ( neighbor instanceof Flower );
	} //end method canJump()
	
} //end class Jumper

package controler;

import java.awt.Rectangle;
import java.util.Iterator;

import model.Layer;
import model.Place;

public class Rule {

	private final int maxArea = 40000;

	public Rule() {

	}

	// i dont want to build large places
	public boolean checkArea(Place p) {

		int area = p.getHeight() * p.getWidth();
		if (area <= maxArea) {
			return true;

		}

		return false;
	}

	// check to see if a place will be build in water
	public boolean checkBuildOnWater(Place p) {
		boolean ok = false;
		Layer layer = new Layer();
		Rectangle r = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		Iterator<Layer> iterator = layer.selectAllLayer().iterator();
		while (iterator.hasNext()) {
			Layer l2 = iterator.next();
			Rectangle r1 = new Rectangle(l2.getX(), l2.getY(), l2.getWidth(), l2.getHeight());
			if (l2.getLayerType().getName().equals("hydro"))
				if (r1.contains(r) || r1.intersects(r)) {
					ok = true;
					break;
				}
		}
		if (ok )
			return true;
		else
			return false;
	}

	// check to see if a place will be build on another place
	public boolean checkBuildOnBuild(Place p) {
		boolean ok = false;
		Rectangle r = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		Iterator<Place> iterator = p.selectAllPlace().iterator();
		while (iterator.hasNext()) {
			Place p2 = iterator.next();
			Rectangle r1 = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());

			if (r1.contains(r) || r1.intersects(r)) {
				ok = true;
				break;
			}
		}
		if (ok )
			return true;
		else
			return false;
	}

	// test if a place it will be out of the map (the map limits are on x from 0 to
	// 900 and y form 0 to 700)
	public boolean checkBuildOutOfMap(Place p) {

		if ((p.getX() >= 0 && p.getX() <= 850) && (p.getX() + p.getWidth() <= 900)) {
			if ((p.getY() >= 0 && p.getY() <= 650) && (p.getY() + p.getHeight() <= 700))
				return true;
		}
		return false;

	}

	// check to see if a hydro layer will be under a place
	public boolean checkHidroUnderBuild(Layer l) {
		boolean ok = false;
		if (l.getLayerType().getId() == 2) {

			Place p = new Place();
			Rectangle r = new Rectangle(l.getX(), l.getY(), l.getWidth(), l.getHeight());

			Iterator<Place> iterator = p.selectAllPlace().iterator();
			while (iterator.hasNext()) {
				Place p2 = iterator.next();
				Rectangle r1 = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());

				if (r1.contains(r) || r1.intersects(r)) {
					ok = true;
					break;
				}
			}
		}
		if (ok)
			return true;
		else
			return false;
	}
}

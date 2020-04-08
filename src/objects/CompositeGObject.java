package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;

		for (GObject gObject: gObjects) {
			gObject.move(dX, dY);
		}
	}
	
	public void recalculateRegion() {
		GObject refGObject = gObjects.get(0);

		int minX = refGObject.x;
		int maxX = refGObject.x + refGObject.width;
		int minY = refGObject.y;
		int maxY = refGObject.y + refGObject.height;

		for (GObject gObject: gObjects) {
			if (gObject.x < minX) minX = gObject.x;

			if (gObject.x + gObject.width > maxX) maxX = gObject.x + gObject.width;

			if (gObject.y < minY) minY = gObject.y;

			if (gObject.y + gObject.height > maxY) maxY = gObject.y + gObject.height;
		}

		this.x = minX;
		this.width = maxX - minX;
		this.y = minY;
		this.height = maxY - minY;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject gObject: gObjects) {
			gObject.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		for (GObject gObject: gObjects) {
			gObject.paintLabel(g);
		}
	}
}

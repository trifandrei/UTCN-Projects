package model;

import java.io.IOException;

import view.GUI;

public class Main {

	public static void populateDistrict() {
		District d1 = new District("District_1", 1);
		District d2 = new District("District_2", 1);
		District d3 = new District("District_3", 1);
		District d4 = new District("District_4", 1);
		d1.createTbDistrict();
		d1.insertDistrict();
		d2.insertDistrict();
		d3.insertDistrict();
		d4.insertDistrict();
	}

	public static void printRating() throws IOException {
		District d1 = new District();

		System.out.println("Ratingul districtului 1=" + d1.getDistrictRating(1));
		System.out.println("Ratingul orasului=" + d1.getALLDistrictRating());

		System.out.println("Logger-ul districtului a fost creat in out_place.txt");
		d1.distictLogger(1);


	}

	public static void populateLayer() {
		Layer l1 = new Layer(0, 0, 450, 400, 1, 1);
		Layer l2 = new Layer(450, 0, 450, 400, 2, 1);
		Layer l3 = new Layer(450, 450, 450, 400, 3, 1);
		Layer l4 = new Layer(0, 450, 450, 400, 4, 1);

		Layer h1 = new Layer(250, 0, 30, 400, 1, 2);
		Layer h2 = new Layer(600, 50, 50, 50, 2, 2);
		Layer h3 = new Layer(0, 400, 900, 50, 3, 2);
		Layer h4 = new Layer(600, 700, 50, 50, 4, 2);

		l1.createTbLayer();
		l1.insertLayer();
		l2.insertLayer();
		l3.insertLayer();
		l4.insertLayer();

		h1.insertLayer();
		h2.insertLayer();
		h3.insertLayer();
		h4.insertLayer();
	}

	public static void populateLayerType() {
		LayerType lt1 = new LayerType("ground");
		LayerType lt2 = new LayerType("hydro");
		lt1.createTbLayerTp();
		lt1.insertLayerTp();
		lt2.insertLayerTp();
	}

	public static void populatePlaceType() {
		PlaceType pt1 = new PlaceType("banca", "c::///img.png");
		pt1.createTbPlaceTp();
		pt1.insertPlaceTp();
	}

	public static void populateCity() {
		City c1 = new City("alba", 20);
		c1.createTbCity();
		c1.insertCity();
	}

	public static void populatePlace() {
		Place p1 = new Place("muzeu", 100, 250, 100, 100, "open", 10, 1);
		Place p2 = new Place("piata", 400, 200, 100, 100, "close", 10, 2);
		Place p3 = new Place("parc", 20, 470, 100, 100, "open", 10, 3);
		Place p4 = new Place("stadion", 750, 550, 100, 100, "close", 10, 4);
		Place p5 = new Place("bar", 50, 50, 50, 50, "Irish pub", 7, 1);
		Place p6 = new Place("magazin", 600, 50, 50, 50, "alimentar", 6, 2);
		Place p7 = new Place("cafenea", 50, 600, 50, 50, "Str.Horia", 9, 3);
		Place p8 = new Place("magazin", 600, 600, 50, 50, "piese_auto", 4, 4);
		Place p9 = new Place("casa", 150, 50, 50, 50, "nr.12", 5, 1);
		Place p10 = new Place("casa", 150, 150, 50, 50, "nr.44", 9, 1);
		Place p11 = new Place("casa", 350, 50, 50, 50, "nr.134", 8, 2);
		Place p12 = new Place("casa", 550, 250, 50, 50, "nr.33", 7, 2);
		Place p13 = new Place("casa", 150, 550, 50, 50, "nr.54", 4, 3);
		Place p14 = new Place("casa", 300, 550, 50, 50, "nr.40", 5, 3);
		Place p15 = new Place("casa", 550, 550, 50, 50, "nr.57", 8, 4);
		Place p16 = new Place("casa", 650, 500, 50, 50, "nr.21", 9, 4);

		p1.createTbPlace();

		p1.insertPlace();
		p2.insertPlace();
		p3.insertPlace();
		p4.insertPlace();
		p5.insertPlace();
		p6.insertPlace();
		p7.insertPlace();
		p8.insertPlace();
		p9.insertPlace();
		p10.insertPlace();
		p11.insertPlace();
		p12.insertPlace();
		p13.insertPlace();
		p14.insertPlace();
		p15.insertPlace();
		p16.insertPlace();
	}

	public static void main(String[] args) throws IOException {

		new GUI();
		// populateCity();
		// populateDistrict();
		// populateLayer();
		// populateLayerType();
		// populatePlace();
		// populatePlaceType();
		// printRating();
		
		printRating();
	}

}

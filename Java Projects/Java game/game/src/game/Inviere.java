package game;

public class Inviere {
	private Manuire hendler;
	private HUD hud;
	private int scork=0;
	
	
	public Inviere(Manuire hendler,HUD hud) {
		this.hendler=hendler;
		this.hud=hud;
	}
	
	public void tick() {
		
		scork++;
		if(scork==500) {
			scork=0;
			hud.setNivel(hud.getNivel()+1);
			
			if(hud.getNivel()==2)
				hendler.addObject(new NormalEnemy(50,50,ID.NormalEnemy,hendler));
				else
				if(hud.getNivel()==3) 
					hendler.addObject(new NormalEnemy(50,50,ID.NormalEnemy,hendler));
					else
						if(hud.getNivel()==4)
							hendler.addObject(new NormalEnemy(50,50,ID.NormalEnemy,hendler));
							else
								if(hud.getNivel()==5)
									hendler.addObject(new FastEnemy(50,50,ID.FastEnemy,hendler));
								else
									if(hud.getNivel()==6)
										hendler.addObject(new FastEnemy(50,50,ID.FastEnemy,hendler));
									else
										if(hud.getNivel()==7)
											hendler.addObject(new FastEnemy(50,50,ID.FastEnemy,hendler));
										else
											if(hud.getNivel()==8)
												hendler.addObject(new SmartEnemy(50,50,ID.SmartEnemy,hendler));
											else
												if(hud.getNivel()==9)
													hendler.addObject(new SmartEnemy(50,50,ID.SmartEnemy,hendler));
												else
													if(hud.getNivel()==10) {
														hendler.StergeEnemy();
														hendler.addObject(new SuperEnemy(Game.WIDTH/2-32,-100,ID.Boss,hendler));
													}
														else
															if(hud.getNivel()==13) {
																hendler.StergeEnemy();
																hendler.addObject(new SuperEnemy(Game.WIDTH/3-32,-100,ID.Boss,hendler));
																hendler.addObject(new SuperEnemy(Game.WIDTH/2-32,-100,ID.Boss,hendler));
																hendler.addObject(new FastEnemy(50,50,ID.FastEnemy,hendler));
																hendler.addObject(new FastEnemy(200,50,ID.FastEnemy,hendler));
															}								
													}
				}
		}
	



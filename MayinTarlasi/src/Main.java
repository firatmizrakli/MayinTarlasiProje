public class Main implements Runnable {

	GUI gui = new GUI();
	
	public static void main(String[] args) {
	new Thread(new Main()).start();

	}

	@Override
	public void run() {
		while(true) {
			 gui.repaint(); 
			 if(gui.sifirla == false) {
				 gui.checkVictoryStatus();
				 System.out.println("Zafer: "+gui.galibiyet+", Mağlubiyet: "+ gui.yenilgi);
			 }
			 
		}
		
	}

}

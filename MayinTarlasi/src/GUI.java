
import javax.swing.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
	
	public boolean sifirla =false;
	
	Date basTarih= new Date();
	Date bitisTarih;
	
	int bosluk = 5;
	int komsu = 0;
	String galipMesaj = "Henüz değil!";
	
	public int mx = -100;
	public int my = -100;
	
	public int gulenX = 605;
	public int gulenY = 5;
	
	public int gulensuratX = gulenX+35;
	public int gulensuratY = gulenY+35;
	
	public int zamanX = 1130;
	public int zamanY = 5;
	
	public int galipMesajX = 325;
	public int galipMesajY = -50;
	
	public int saniye=0;
	
	
	public boolean mutluluk = true;
	public boolean galibiyet = false;
	public boolean yenilgi = false;
	
	Random rand = new Random();
	
	
	int[][] mayin = new int [16][9];
	int[][] komsu1 = new int [16][9];
	boolean[][] meyCik = new boolean [16][9];
	boolean[][] isaretli = new boolean [16][9];
	
	int doge = rand.nextInt(5);
	
	public GUI() {
		
		this.setTitle("Mayın Tarlası");
		this.setSize(1286, 829);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				if(rand.nextInt(100) < 20) {
					mayin[i][j] = 1;
				}
				else {
					mayin[i][j] = 0;
				}
				meyCik[i][j] = false;
			}
		}
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				komsu = 0 ;
				for (int m = 0; m< 16; m++) {
					for (int n = 0; n< 9; n++) {
						if(!(m==i && n == j)) {
							if(inN (i,j,m,n) == true) {
								komsu++;
						}
					}}
					komsu1[i][j] = komsu;
				}
			}
		}
		Board board = new Board();
		this.setContentPane(board);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
	
		Click click = new Click();
		this.addMouseListener(click);
		
	}
	
	public class Board extends JPanel{
		
		public void paintComponent(Graphics g) {
			
			g.setColor(Color.GRAY);
			g.fillRect(0,0,1280,800);
			
			for (int i = 0; i< 16; i++) {
				for (int j = 0; j< 9; j++) {
					g.setColor(Color.LIGHT_GRAY);
					
					 
					
					  if(meyCik[i][j] == true) {
						  g.setColor(Color.white);
						  if(mayin[i][j] == 1) {
							  g.setColor(Color.red);
						  }
						}
						
					if(mx >= bosluk+i*80 && mx < bosluk+i*80+80-2*bosluk && my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk) {
						g.setColor(Color.gray);
					}
					g.fillRect(bosluk+i*80, bosluk+j*80+80, 80-2*bosluk, 80-2*bosluk);
					if(meyCik[i][j] == true) {
						g.setColor(Color.black);
						if(mayin[i][j] == 0 ) {
							if(komsu1[i][j]==0) {
								g.setColor(Color.CYAN);
							}
							else if(komsu1[i][j]==1) {
								g.setColor(Color.blue);
							}else if(komsu1[i][j] == 2) {
								g.setColor(Color.green);
							}
							else if(komsu1[i][j] == 3) {
								g.setColor(Color.red);
							}
							else if(komsu1[i][j] == 4) {
								g.setColor(new Color(0,0,128));
							}
							else if(komsu1[i][j] == 5) {
								g.setColor(new Color(178,34,34));
							}
							else if(komsu1[i][j] == 6) {
								g.setColor(new Color(72,209,204));
							}
							else if(komsu1[i][j] == 7) {
								g.setColor(Color.black);
							}
							else if(komsu1[i][j] == 8) {
								g.setColor(Color.darkGray);
							}
							g.setFont(new Font("Tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString(komsu1[i][j]), i*80+27, j*80+80+55);
						}
						else if(mayin[i][j] == 1) {
							g.fillRect(i*80+10+20, j*80+80+20, 20, 40);
							g.fillRect(i*80+20, j*80+80+10+20, 40, 20);
							g.fillRect(i*80+5+20, j*80+80+5+20, 30, 30);
							g.fillRect(i*80+38, j*80+80+15, 4, 50);
							g.fillRect(i*80+15, j*80+80+38, 50, 4);
						 }
						}
					
				}
			}
			
			//gülümseme çiziliyor
			
			g.setColor(Color.GREEN);
			g.fillOval(gulenX, gulenY, 70, 70);
			g.setColor(Color.BLACK);
			g.fillOval(gulenX+15, gulenY+20, 10, 10);
			g.fillOval(gulenX+45, gulenY+20, 10, 10);
			if(mutluluk==true) {
				g.fillRect(gulenX+20, gulenY+50, 30, 5);
				g.fillRect(gulenX+17, gulenY+45, 5, 5);
				g.fillRect(gulenX+48, gulenY+45, 5, 5);
			}
			else {
				g.setColor(Color.BLACK);
				g.fillRect(gulenX+20, gulenY+45, 30, 5);
				g.fillRect(gulenX+17, gulenY+50, 5, 5);
				g.fillRect(gulenX+48, gulenY+50, 5, 5);
			}
		
			//Zamanlayıcı
			
			g.setColor(Color.black);
			g.fillRect(zamanX, zamanY, 140, 70);
			if(yenilgi==false&&galibiyet==false) {
				saniye = (int)((new Date().getTime()-basTarih.getTime()) / 1000);
					
			}
			if(saniye>999) {
				saniye=999;
			}
			g.setColor(Color.WHITE);
			if(galibiyet==true) {
				g.setColor(Color.green);}
				else if(yenilgi==true) {
					g.setColor(Color.red);
				
			}
			g.setFont(new Font("Tahoma",Font.PLAIN,80));
			if(saniye<10) {
				g.drawString("00"+Integer.toString(saniye), zamanX, zamanY+65);
			}
			else if(saniye<100) {
				g.drawString("0"+Integer.toString(saniye), zamanX, zamanY+65);
			}
			else {
				g.drawString(Integer.toString(saniye), zamanX, zamanY+65);
			}
			
			//galibiyet mesajı alanı
			
			
			if(galibiyet==true) {
				g.setColor(Color.GREEN);
				galipMesaj = "KAZANDIN!";
			}
			else if(yenilgi ==true){
				g.setColor(Color.BLACK);
				galipMesaj = "KAYBETTİN!      TEKRAR DENE :)";
			}
			
			if(galibiyet==true || yenilgi==true) {
				galipMesajY = -50 +(int) (new Date().getTime() - bitisTarih.getTime())/10;
				if(galipMesajY>67) {
					galipMesajY=67;
				}
				g.setFont(new Font("Tahoma", Font.PLAIN,50));
				g.drawString(galipMesaj, galipMesajX, galipMesajY);
			}
			
		}
		
	}
	public class Move implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			//System.out.println("Mouse Hareket Etti");
			//System.out.print("X: "+ mx +", Y"+ my);
		}
		
	}
	public class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			
			if(inBoxX() !=-1 && inBoxY() != -1) {
				meyCik[inBoxX()][inBoxY()] = true;
			}
			
			if(inBoxX() !=-1 && inBoxY() != -1) {
				//System.out.println("Mouse ["+ inBoxX() + "," + inBoxY() + "]" +" icinde Komşu Mayin Sayisi: " +komsu1[inBoxX()][inBoxY()]);
			}
			else {
				//System.out.println("Hicbir kareye basmadiniz!..");
					
			}
			if(inSmiley()== true) {
				resetAll();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	public void checkVictoryStatus() {
		if(yenilgi==false) {
			for (int i = 0; i< 16; i++) {
				for (int j = 0; j< 9; j++) {
					if(meyCik[i][j]==true && mayin[i][j] == 1) {
						yenilgi=true;
						mutluluk=false;
						bitisTarih =new Date();
						
					}
				}
			}
		}
		
		
		if(totalBoxesRevealed() >= 144-totalMines() && galibiyet==false) {
			galibiyet = true;
			bitisTarih =new Date();
		}
		
	}
	public int totalMines(){
		int total = 0;
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				if(mayin[i][j]==1) {
					total++;
				}
			}
		}
		return total;
		
	}
	public int totalBoxesRevealed() {
		int total = 0;
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				if(meyCik[i][j]==true) {
					total++;
				}
			}
		}
		return total;
	}
	
	
	public void resetAll() {
		
		sifirla = true;
		
		basTarih = new Date();
		
		galipMesajY = -50;
		galipMesaj = "Henüz değil!";
		
		mutluluk=true;
		galibiyet=false;
		yenilgi=false;
		
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				if(rand.nextInt(100) < 20) {
					mayin[i][j] = 1;
				}
				else {
					mayin[i][j] = 0;
				}
				meyCik[i][j] = false;
				isaretli[i][j] = false;
			}
		}
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				komsu = 0 ;
				for (int m = 0; m< 16; m++) {
					for (int n = 0; n< 9; n++) {
						if(!(m==i && n == j)) {
							if(inN (i,j,m,n) == true) {
								komsu++;
						}
					}}
					komsu1[i][j] = komsu;
				}
			}
		}
		sifirla = false;
	}
	
	public boolean inSmiley() {
		int dif = (int)Math.sqrt(Math.abs(mx-gulensuratX)*Math.abs(mx-gulensuratX+Math.abs(my-gulensuratY)*Math.abs(my-gulensuratY)));
		if(dif<35) {
			return true;
		}
		
		
		return false;
	}
	
	
	public int inBoxX() {
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				
				if(mx >= bosluk+i*80 && mx < bosluk+i*80+80-2*bosluk && my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk) {
				return i;
				}
			}
		}
		return -1;
	
	}
	public int inBoxY() {
		for (int i = 0; i< 16; i++) {
			for (int j = 0; j< 9; j++) {
				
				if(mx >= bosluk+i*80 && mx < bosluk+i*80+80-2*bosluk && my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk) {
				return j;
				}
			}
		}
		return -1;
	}
	
	public boolean inN(int mX, int mY, int cX, int cY) {
		if(mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY-cY >-2 && mayin [cX][cY]==1 ) {
			return true;
		}
		
		
		return false;
	}
}

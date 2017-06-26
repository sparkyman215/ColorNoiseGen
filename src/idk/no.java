package idk;

import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class no {

	
	public static int corneravg(int[][] takeme, int tempk, int tempi) { // a very specific function that averages the four corners of a array location
		
		int avg = 0; // initialize because eclipse errors are annoying as shit
		avg = ((takeme[tempk-1][tempi]) +
				(takeme[tempk+1][tempi]) +
				(takeme[tempk][tempi-1]) +
				(takeme[tempk][tempi+1])) /
				4;
		
		int randomizer = (int)(Math.random()*20);
		
		if (Math.random()*50 < 25) {
			avg -= randomizer;
		} else {
			avg += randomizer; // needs a random addition otherwise it'll just be zeroes xd
		}
		
		if (avg > 255) { avg = 255; }
		else if (avg < 0) { avg = 0; }
		
		return avg;
	}
	
	public static int[][] colorGen(int size) {
		
		int[][] arr = new int[size+2][5];
		int randN = 170;
		int temp[] = {0,((int)(Math.random()*randN+170)),((int)(Math.random()*randN)),((int)(Math.random()*randN))};
		arr[1] = Arrays.copyOf(temp, 5);
		
		Color tempC; int rgb=0; 
		BufferedImage img = new BufferedImage(size+1,size+1,BufferedImage.TYPE_INT_RGB);
		
		for (int xd = 0; xd < size-2; xd++) {
			for(int k = 2; k <= size; k++) {
				int iPerm = 0;
				for(int i = 1; i <= 3; i++) {
					arr[k][i] = corneravg(arr, k, i);
					iPerm = i;
				}
				tempC = new Color(arr[k][1],arr[k][2],arr[k][3]);
				rgb = tempC.getRGB();
				img.setRGB(k,iPerm+xd,rgb);
			}
		}
		
		
		//File output = new File("saved" + Calendar.getInstance().getTimeInMillis() + ".png");
		File output = new File("output.png");
		try {
			ImageIO.write(img, "png", output);			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(Arrays.deepToString(colorGen(512)));
		
	}
}

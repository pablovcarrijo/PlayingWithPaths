package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file name: ");
		String sourceFileStr = sc.next();
		
		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean sucess = new File(sourceFolderStr + "\\out").mkdir();
		
		String targetPath = sourceFolderStr + "\\out\\sumarry.txt";
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(sourceFile))){
			String readBd = br.readLine();
			while(readBd != null) {
				
				String[] vect = readBd.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				
				list.add(new Product(name, price, quantity));
						
				readBd = br.readLine();
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))){
				for(Product items : list) {
					bw.write(items.getName() + ", " + String.format("%.2f", items.tota()));
					bw.newLine();
				}
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			
		}
		catch(IOException e){
			System.out.println("Error in read file: " + e.getMessage());
		}
		
		sc.close();
		
	}
	
}

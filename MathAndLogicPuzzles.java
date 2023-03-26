package mathAndLogicPuzzles;
import java.util.ArrayList;
import java.util.Random;

public class MathAndLogicPuzzles {

public enum gender {boy, girl} 	
	
public static void theApocalypse(ArrayList<ArrayList<gender>> family_list, int num_of_families) {
	Random rand = new Random();
	boolean is_girl = rand.nextBoolean();
	int i = 0;
	while(i < num_of_families) {
		ArrayList<gender> fam = new ArrayList<gender>();
		if(is_girl == true) {
			fam.add(gender.girl);
			family_list.add(fam);
		} else {
			while(is_girl != true) {
				fam.add(gender.boy);
				is_girl = rand.nextBoolean();
			}
			fam.add(gender.girl);
			family_list.add(fam);
		}
		is_girl = rand.nextBoolean();
		i++;
	}
	int count_boys = 0;
	int count_girls = 0;
	for(ArrayList<gender> fam : family_list) {
		for(int j = 0; j < fam.size(); j++) {
			if(fam.get(j) == gender.girl) {
				count_girls++;
			} else {
				count_boys++;
			}
		}
	}
	System.out.println("Number of boys: " + count_boys);
	System.out.println("Number of girls: " + count_girls);
}
	public static void main(String[] args) {
		ArrayList<ArrayList<gender>> families = new ArrayList<ArrayList<gender>>();
		theApocalypse(families, 10000);
	}
}

package dwe;



import java.util.ArrayList;

public class liste {
	ArrayList<hashtag> listeX = new ArrayList();
	
	public void gefunden(String text) {
		int i;
		boolean mussErstelltWerden = true;
		for(i=0;i<listeX.size();i++) {
			if(text.equals(listeX.get(i).getText())) {
				listeX.get(i).erneut();
				mussErstelltWerden = false;
			}
		}
		if(mussErstelltWerden) {
		hashtag g = new hashtag(text);
		listeX.add(g);
		
		}
		
	}
	public void ende() {
		listeX.sort(null);
	}
	
	
	public void ausgeben() {
		int i;
		for(i = 0; i<listeX.size();i++) {
			System.out.println(listeX.get(i));
		}
	}
	public ArrayList<hashtag> getListeX() {
		return listeX;
	}

}

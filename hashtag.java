package dwe;



public class hashtag implements Comparable<hashtag> {
	public int anz;
	public String text;
	
	public hashtag(String text) {
		this.text = text;
		this.anz = 1;
	}
	
	
	public void erneut() {
		anz++;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
	@Override
	public int compareTo(hashtag r) {
		if(r.getAnz() > this.anz) {
			return 1;
			
		}
		else if(r.getAnz()<this.anz) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return (anz + " mal der Hashtag " + text);
	}
	


	public int getAnz() {
		return anz;
	}


	public void setAnz(int anz) {
		this.anz = anz;
	}
	
	
	

}

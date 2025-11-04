public class Location {
    String name,abv,imageName;
    int x,y;
    public Location(String name, String abv,int x, int y, String imageName) {
        this.name=name;
        this.abv=abv;
        this.y=y;
        this.x=x;
        this.imageName=imageName;
    }
    public String toString() {
        return name;
    }
    public String getABV() {
        return abv;
    }
    public String getImage() {
        return imageName;
    }
    @Override
    public int hashCode() {
        char char1 = abv.charAt(0);
        int value1 = (int)char1 - 65; 

        char char2 = abv.charAt(1);
        int value2 = (int)char2 - 65; 
 
        char char3 = abv.charAt(2);
        int value3 = (int)char2 - 65; 

        return  value1*(26*26) + value2*(26) + value3*1;
    }
    @Override
    public boolean equals(Object abv) {
        Location other = (Location)(abv);
        if (this.abv.equals(other.getABV())) {
            return true;
        }
        return false;
    } 
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
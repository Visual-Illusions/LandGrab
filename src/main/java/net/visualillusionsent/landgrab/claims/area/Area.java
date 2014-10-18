package net.visualillusionsent.landgrab.claims.area;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public final class Area {
    private Vertex origin, offset;

    public Area(Vertex origin, Vertex offset){
        this.origin = origin;
        this.offset = offset;
        verifyVerticies(); // Offset should always be at a position greater than origin
    }

    public final int getOccupiedSpace(){ // Area
        return getDistance(offset.getX(), origin.getX()) * getDistance(offset.getZ(), origin.getZ());
    }

    public final Vertex getOrigin(){
        return origin;
    }

    public final Vertex getOffset(){
        return offset;
    }

    public boolean isValid(){
        return isLargeEnough();
    }

    public boolean intersects(Vertex vertex){
        return vertex.getX() >= origin.getX() &&
               vertex.getX() <= offset.getX() &&
               vertex.getZ() >= origin.getZ() &&
               vertex.getZ() <= offset.getZ();
    }

    private boolean isLargeEnough(){
        return origin != null && offset != null &&
                getOccupiedSpace() >= 4; // that 4 is gonna be changed at somepoint
    }

    private void verifyVerticies(){
        if(origin.getX() == offset.getX()){
            throw new InvalidAreaVerticiesException("Origin X was equal to Offset X");
        }
        else if (origin.getZ() == offset.getZ()) {
            throw new InvalidAreaVerticiesException("Origin Z was equal to Offset Z");
        }
    }

    private int getDistance(int a, int b){
        int offset = 0;
        if((a < 0 && b > 0) || (a > 0 && b < 0) || (a == 0 || b == 0)){
            offset = 1; // Include the 0 in the distance calculation
        }
        return Math.abs(a - b) + offset;
    }
}

package net.visualillusionsent.landgrab.claims.area;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public final class Area {
    private Vertex origin, offset;

    public Area(Vertex origin, Vertex offset){
        this.origin = origin;
        this.offset = offset;
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
               vertex.getZ() >= offset.getZ();
    }

    private boolean isLargeEnough(){
        return origin != null && offset != null && Math.abs(offset.getX() - origin.getX()) * Math.abs(offset.getZ() - origin.getZ()) >= 4;
    }
}

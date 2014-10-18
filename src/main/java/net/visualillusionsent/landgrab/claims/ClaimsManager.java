package net.visualillusionsent.landgrab.claims;

import net.visualillusionsent.landgrab.claims.area.Area;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class ClaimsManager {

    public boolean intersects(Area area1, Area area2){
        return area1.intersects(area2.getOrigin()) || area1.intersects(area2.getOffset());
    }
}

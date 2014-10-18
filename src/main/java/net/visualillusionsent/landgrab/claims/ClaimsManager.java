package net.visualillusionsent.landgrab.claims;

import com.google.common.collect.Lists;
import net.visualillusionsent.landgrab.claims.area.Area;

import java.util.ArrayList;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class ClaimsManager {
    private final ArrayList<Claim> claims = Lists.newArrayList();

    public boolean addClaim(Claim claim){
        return this.claims.add(claim);
    }

    public boolean removeClaim(Claim claim){
        return this.claims.remove(claim);
    }

    public boolean intersects(Area area1, Area area2){
        return area1.intersects(area2.getOrigin()) || area1.intersects(area2.getOffset());
    }
}

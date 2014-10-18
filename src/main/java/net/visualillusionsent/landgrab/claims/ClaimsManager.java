package net.visualillusionsent.landgrab.claims;

import com.google.common.collect.Lists;
import net.canarymod.Canary;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.visualillusionsent.landgrab.claims.area.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public final class ClaimsManager {
    private final ArrayList<Claim> claims = Lists.newArrayList();

    public boolean addClaim(Claim claim){
        return claims.add(claim);
    }

    public boolean removeClaim(Claim claim){
        return claims.remove(claim);
    }

    public boolean canClaim(Vertex vertex){
        for(Claim claim : claims){
            if(claim.getArea().intersects(vertex)){
               return false;
            }
        }
        return true;
    }
    
    public final void saveAll(){
        for(Claim claim : claims){
            try {
                HashMap<String, Object> filter = new HashMap<String, Object>();
                filter.put("owner", claim.getOwner());
                Canary.db().update(claim.getDataAccess(), filter);
            }
            catch (DatabaseWriteException dbwex) {
            }
        }
    }
}

package net.visualillusionsent.landgrab.claims;

import com.google.common.collect.Lists;
import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.visualillusionsent.landgrab.LandGrab;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by darkdiplomat on 10/18/14.
 */
public final class UserManager {
    private final LandGrab landGrab;
    private final ArrayList<ClaimUser> users = Lists.newArrayList();

    public UserManager(LandGrab landGrab){
        this.landGrab = landGrab;
    }

    public boolean trackNewUser(PlayerReference reference){
        return trackUser(new ClaimUser(reference.getUUID(), landGrab.getConfig().getInt("starting-blocks"), 0));
    }

    public boolean trackUser(ClaimUser user){
        return users.add(user);
    }

    public final void saveAll(){
        for(ClaimUser user : users){
            try {
                HashMap<String, Object> filter = new HashMap<String, Object>();
                filter.put("uuid", user.getId());
                Canary.db().update(user.getDataAccess(), filter);
            }
            catch (DatabaseWriteException dbwex) {
            }
        }
    }
}

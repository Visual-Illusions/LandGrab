package net.visualillusionsent.landgrab.claims;

import net.canarymod.api.PlayerReference;
import net.visualillusionsent.landgrab.database.UserDataAccess;

import java.util.UUID;

/**
 * Created by darkdiplomat on 10/18/14.
 */
public final class ClaimUser {
    private final UUID uuid;
    private int blocksAccured, blocksUsed;

    public ClaimUser(UUID uuid, int blocksAccured, int blocksUsed){
        this.uuid = uuid;
        this.blocksAccured = blocksAccured;
        this.blocksUsed = blocksUsed;
    }

    public ClaimUser(UserDataAccess data){
        this.uuid = UUID.fromString(data.uuid);
        this.blocksAccured = data.blocksaccured;
        this.blocksUsed = data.blocksused;
    }

    public final UUID getId(){
        return uuid;
    }

    public final int getBlocksAccured(){
        return blocksAccured;
    }

    public final void addBlocksAccured(int add){
        this.blocksAccured += add;
    }

    public final UserDataAccess getDataAccess(){
        UserDataAccess data = new UserDataAccess();
        data.uuid = uuid.toString();
        data.blocksaccured = blocksAccured;
        data.blocksused = blocksUsed;
        return data;
    }

    public final boolean equals(Object obj){
        return obj == this || (obj instanceof PlayerReference && ((PlayerReference) obj).getUUID().equals(uuid));
    }
}

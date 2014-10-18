package net.visualillusionsent.landgrab.database;

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;

import java.util.List;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class ClaimDataAccess extends DataAccess {

    public ClaimDataAccess(){
        super("landgrab.claims");
    }

    @Column(columnName = "owner", dataType = Column.DataType.STRING)
    public String owner;

    @Column(columnName = "origin", dataType = Column.DataType.STRING)
    public String origin;

    @Column(columnName = "offset", dataType = Column.DataType.STRING)
    public String offset;

    @Column(columnName = "permissions", dataType = Column.DataType.STRING, isList = true)
    public List<String> permissions;

    @Override
    public DataAccess getInstance() {
        return new ClaimDataAccess();
    }
}

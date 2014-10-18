package net.visualillusionsent.landgrab.database;

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class UserDataAccess extends DataAccess {

    public UserDataAccess(){
        super("landgrab.users");
    }

    @Column(columnName = "uuid", dataType = Column.DataType.STRING, columnType = Column.ColumnType.UNIQUE)
    public String uuid;

    @Column(columnName = "blocksaccured", dataType = Column.DataType.INTEGER)
    public int blocksaccured;

    @Column(columnName = "blocksused", dataType = Column.DataType.INTEGER)
    public int blocksused;

    @Override
    public DataAccess getInstance() {
        return new UserDataAccess();
    }
}

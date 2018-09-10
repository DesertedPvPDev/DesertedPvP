package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedpvp.DesertedPvP;

public class TeamsDataAccess extends DatabaseAccess {


    private TeamsDataAccess ins;

    private DesertedPvP pvp;

    public TeamsDataAccess(Database db, DesertedPvP pvp) {
        super(db);
        this.pvp = pvp;
        ins = this;
    }

    @Override
    public void loadTables() {

    }

}

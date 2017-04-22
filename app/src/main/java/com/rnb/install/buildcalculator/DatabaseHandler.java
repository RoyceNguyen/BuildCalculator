package com.rnb.install.buildcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by blaze on 2017-03-23.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Keep Track of the database versions
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * create the name of the database
     */
    private static final String DATABASE_NAME = "buildcalculator";

    /**
     * Create the names of all the tables
     */
    private static final String TABLE_BUILDS = "builds";
    private static final String TABLE_WEAPON = "weapon";
    private static final String TABLE_GEAR = "gear";
    private static final String TABLE_PICTURES = "picture";
    private static final String TABLE_IMAGELOCATION = "image_location";

    /**
     * Common column names
     */
    private static final String COLUMN_ID = "id";

    /**
     * Build Table Column Names
     */
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_WEAPON = "weapon";
    private static final String COLUMN_GEAR = "gear";
    private static final String COLUMN_ATTACKDAMAGE = "attackdamage";
    private static final String COLUMN_ATTACKSPEED = "attackspeed";
    private static final String COLUMN_MAGICDAMAGE = "magicdamage";
    private static final String COLUMN_CRIT = "crit";
    private static final String COLUMN_CRITDAMAGE = "critdamage";
    private static final String COLUMN_HEALTH = "health";
    private static final String COLUMN_ARMOR = "ARMOR";
    private static final String COLUMN_MAGICRESIST = "magicresist";


    /**
     *Picture Table Column Names
     */
    private static final String COLUMN_RESOURCE = "resource";

    /**
     *image_trip Table Column Names
     */
    private static final String COLUMN_PICTURE = "id_picture";
    private static final String COLUMN_LOCATION = "location";

    /**
     * Create statements for all of our tables
     */

    private static final String CREATE_WEAPON_TABLE = "CREATE TABLE " +
            TABLE_WEAPON + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            COLUMN_ATTACKDAMAGE + " INTEGER, " +
            COLUMN_ATTACKSPEED + " DECIMAL, " +
            COLUMN_CRIT + " INTEGER, " +
            COLUMN_CRITDAMAGE + " INTEGER " +
            ")";

    private static final String CREATE_GEAR_TABLE = "CREATE TABLE " +
            TABLE_GEAR + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            COLUMN_HEALTH + " INTEGER, " +
            COLUMN_ARMOR + " INTEGER, " +
            COLUMN_MAGICRESIST + " INTEGER " +
            ")";

    private static final String CREATE_BUILDS_TABLE = "CREATE TABLE " +
            TABLE_BUILDS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            COLUMN_WEAPON + " INTEGER REFERENCES " +
            TABLE_WEAPON + "("+COLUMN_ID+")," +
            COLUMN_GEAR + " INTEGER REFERENCES " +
            TABLE_GEAR + "("+COLUMN_ID+")" +
            ")";

    private static final String CREATE_PICTURES_TABLE = "CREATE TABLE " +
            TABLE_PICTURES + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_RESOURCE + " TEXT" +
            ")";

    private static final String CREATE_IMAGE_LOCATION_TABLE = "CREATE TABLE " +
            TABLE_IMAGELOCATION + "(" +
            COLUMN_LOCATION + " INTEGER REFERENCES " +
            TABLE_BUILDS + "(" +
            COLUMN_ID + ")," +
            COLUMN_PICTURE + " INTEGER REFERENCES " +
            TABLE_PICTURES + "(" +
            COLUMN_ID + ")" +
            ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create the tables inside of the database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BUILDS_TABLE);
        db.execSQL(CREATE_PICTURES_TABLE);
        db.execSQL(CREATE_IMAGE_LOCATION_TABLE);
        db.execSQL(CREATE_WEAPON_TABLE);
        db.execSQL(CREATE_GEAR_TABLE);
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('RAGE', 5000, 1.6, 5, 400)");
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('SUNDER', 4785, 1.9, 4, 470)");
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('HYDRAS', 5600, 1.4, 5, 390)");
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('DEATHBRINGER', 6000, 1.1, 4, 400)");
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('WRATH', 6100, 1.3, 4, 500)");
        db.execSQL("INSERT INTO " + TABLE_WEAPON + "(NAME, ATTACKDAMAGE, ATTACKSPEED, CRIT, CRITDAMAGE ) VALUES ('TITANS', 4000, 2.3, 6, 350)");

        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('MYSTICAL MAIL', 1300, 400, 350)");
        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('OLYMPUS', 1500, 390, 400)");
        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('BULWARK', 1300, 500, 250)");
        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('ARCHON', 1700, 300, 300)");
        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('ROBE', 4000, 100, 150)");
        db.execSQL("INSERT INTO " + TABLE_GEAR + "(NAME, HEALTH, ARMOR, MAGICRESIST ) VALUES ('DRAGONSKIN', 600, 600, 600)");
    }


    /**
     * When the database upgrades delete the old tables and recreate them
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGELOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEAPON);
        onCreate(db);
    }

    /**
     * CRUD OPERATIONS FOR THE DATABASE AND TABLES
     */

    /**
     * CREATE new objects for the tables
     *
     */
    public void addBuild(Build build) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, build.getName());
        Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_WEAPON, build.getWeapon());
        values.put(COLUMN_GEAR, build.getGear());
        db.insert(TABLE_BUILDS, null, values);
        db.close();
    }

    public void addGear(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_HEALTH, item.getHealth());
        values.put(COLUMN_ARMOR, item.getArmor());
        values.put(COLUMN_MAGICRESIST, item.getMagicResist());
        db.insert(TABLE_GEAR, null, values);
        db.close();
    }

    public void addWeapon(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_ATTACKDAMAGE, item.getAttackDamage());
        values.put(COLUMN_ATTACKSPEED, item.getAttackSpeed());
        values.put(COLUMN_CRIT, item.getCrit());
        values.put(COLUMN_CRITDAMAGE, item.getCritDamage());
        values.put(COLUMN_MAGICDAMAGE, item.getMagicDamage());
        db.insert(TABLE_WEAPON, null, values);
        db.close();
    }

    //We modified addPicture to return the rowNumber it was added into
    public int addPicture(Picture picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESOURCE, picture.getResource());
        db.insert(TABLE_PICTURES, null, values);
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        if(cursor.moveToFirst()) {
            int location = Integer.parseInt(cursor.getString(0));
            System.out.println("Record ID " + location);
            db.close();
            return location;
        }
        return -1;
    }

    //Added a method that will add an image location record
    public void addImageLocation(int image, int location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_PICTURE, image);
        db.insert(TABLE_IMAGELOCATION, null, values);
        db.close();
    }


    /**
     * READ objects from database
     */
    //Creating getBuild and getAllBuilds
    public Build getBuild(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BUILDS,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_WEAPON, COLUMN_GEAR}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Build build = new Build(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
        return build;
    }

    public ArrayList<Build> getAllBuilds() {
        ArrayList<Build> buildsList = new ArrayList<Build>();
        String selectQuery = "SELECT * FROM " + TABLE_BUILDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Build build = new Build();
                build.setId(Integer.parseInt(cursor.getString(0)));
                Log.d("getAllBuildsValue", cursor.getString(0)+ "");
                build.setName(cursor.getString(1));
                Log.d("getAllBuildsValue", cursor.getString(1)+ "");
                build.setWeapon(cursor.getInt(2));
                Log.d("getAllBuildsValue", cursor.getInt(2)+ "");
                build.setGear(cursor.getInt(3));
                Log.d("getAllBuildsValue", cursor.getInt(3)+ "");
                buildsList.add(build);
            } while (cursor.moveToNext());
        }
        return buildsList;
    }

    //Creating getWeapon and getAllWeapons
    public Item getWeapon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Item item = null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WEAPON + " WHERE " + COLUMN_ID  + "="  + id + "+1 "  , null);
                //TABLE_WEAPON,
                //new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_ATTACKDAMAGE, COLUMN_ATTACKSPEED, COLUMN_CRIT, COLUMN_CRITDAMAGE}, COLUMN_ID + "=?",
                //new String[] { String.valueOf(id) }, null, null, null, null);
        Log.d("GETWEAP", cursor.toString());
        if (cursor != null && cursor.moveToFirst()) {
            Log.d("GETWEAP", "GOT HERE 2");
            item = new Item(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5));
        }
        return item;
    }

    public ArrayList<Item> getAllWeapons() {
        ArrayList<Item> weaponsList = new ArrayList<Item>();
        String selectQuery = "SELECT * FROM " + TABLE_WEAPON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setAttackDamage(cursor.getInt(2));
                item.setAttackSpeed(cursor.getDouble(3));
                item.setCrit(cursor.getInt(4));
                item.setCritDamage(cursor.getInt(5));
                weaponsList.add(item);
            } while (cursor.moveToNext());
        }
        return weaponsList;
    }

    //Creating getGear and getAllGear
    public Item getGear(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GEAR,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_HEALTH, COLUMN_ARMOR, COLUMN_MAGICRESIST}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Item item = new Item(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        return item;
    }

    public ArrayList<Item> getAllGears() {
        ArrayList<Item> gearsList = new ArrayList<Item>();
        String selectQuery = "SELECT * FROM " + TABLE_GEAR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setHealth(cursor.getInt(2));
                item.setArmor(cursor.getInt(3));
                item.setMagicResist(cursor.getInt(4));
                gearsList.add(item);
            } while (cursor.moveToNext());
        }
        return gearsList;
    }


    public Picture getPicture(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PICTURES, new String[] {COLUMN_ID, COLUMN_RESOURCE}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Picture picture = new Picture(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        return picture;
    }

    public ArrayList<Picture> getAllPictures() {
        ArrayList<Picture> pictureList = new ArrayList<Picture>();
        String selectQuery = "SELECT  * FROM " + TABLE_PICTURES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Picture picture = new Picture();
                picture.setId(Integer.parseInt(cursor.getString(0)));
                picture.setResource(cursor.getString(1));
                pictureList.add(picture);
            } while (cursor.moveToNext());
        }
        return pictureList;
    }

    /**
     * The second getAllPictures is used to grab all images associated with an location
     * @param location
     * @return
     */
    public ArrayList<Picture> getAllPictures(int location) {
        ArrayList<Picture> pictureList = new ArrayList<Picture>();
        String selectQuery = "SELECT  * FROM " + TABLE_IMAGELOCATION + " WHERE " + COLUMN_LOCATION + " = " + location;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String innerQuery = "SELECT * FROM " + TABLE_PICTURES + " WHERE " + COLUMN_ID + "=" + cursor.getInt(1);
                Cursor innerCursor = db.rawQuery(innerQuery, null);
                if (innerCursor.moveToFirst()) {
                    do {
                        Picture picture = new Picture();
                        picture.setId(Integer.parseInt(innerCursor.getString(0)));
                        picture.setResource(innerCursor.getString(1));
                        pictureList.add(picture);
                    } while (innerCursor.moveToNext());
                }
            }while (cursor.moveToNext());
        }
        return pictureList;
    }


    /**
     * UPDATE objects in database
     */
    public int updateBuild(Build build) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, build.getName());
        values.put(COLUMN_WEAPON, build.getWeapon());
        values.put(COLUMN_GEAR, build.getGear());
        return db.update(TABLE_BUILDS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(build.getId()) });
    }

    public int updateWeapon(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_ATTACKDAMAGE, item.getAttackDamage());
        values.put(COLUMN_ATTACKSPEED, item.getAttackSpeed());
        values.put(COLUMN_CRIT, item.getCrit());
        values.put(COLUMN_CRITDAMAGE, item.getCritDamage());
        return db.update(TABLE_WEAPON, values, COLUMN_ID + " = ?", new String[] { String.valueOf(item.getId()) });
    }

    public int updateGear(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_HEALTH, item.getHealth());
        values.put(COLUMN_ARMOR, item.getArmor());
        values.put(COLUMN_MAGICRESIST, item.getMagicResist());
        return db.update(TABLE_GEAR, values, COLUMN_ID + " = ?", new String[] { String.valueOf(item.getId()) });
    }


    public int updatePicture(Picture picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESOURCE, picture.getResource());
        return db.update(TABLE_PICTURES, values, COLUMN_ID + " = ?", new String[] { String.valueOf(picture.getId()) });
    }

    /**
     * DELETE objects from database
     */
    public void deleteBuild(long build_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUILDS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(build_id) });
    }

    public void deleteWeapon(long weapon_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEAPON, COLUMN_ID + " = ?",
                new String[] {String.valueOf(weapon_id)});
    }

    public void deleteGear(long gear_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GEAR, COLUMN_ID + " = ?",
                new String[] {String.valueOf(gear_id)});
    }

    public void deletePicture(long picture_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PICTURES, COLUMN_ID + " = ?",
                new String[] { String.valueOf(picture_id) });
    }

    /**
     * Closing the database connection
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}


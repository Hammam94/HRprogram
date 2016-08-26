package com.company.Back_End.Core;
import java.io.File;
import java.sql.*;

/**
 * Created by user on 8/25/2016.
 */
public class model{

    private query myquires;

    public model () throws SQLException {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        myquires = new query(c);
    }

    public void remove(String condition) throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        myquires.delete(table,condition);
    }

    public void update(int fieldsNumber, String[] fields, String[] values, String condition) throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        String data = "";
        for(int i = 0; i < fieldsNumber; ++i){
            data +=  i >= fieldsNumber ? fields[i] + " = " + values[i] : fields[i] + " = " + values[i] + " , ";
        }
        myquires.update(table,data,condition);
    }

    public void save(String table, String fields, String values) throws SQLException {
        myquires.insert(table, fields, values);
    }

    public ResultSet where(String condition) throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        return myquires.selectAllWhere(table, condition);
    }

    public ResultSet find (String id) throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        return myquires.selectAllById(table,id);
    }

    public ResultSet all() throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        return myquires.selectAllTable(table);
    }

    public void attach(String role, String id,String key) throws SQLException {
        myquires.insertAttach(new Exception().getStackTrace()[1].getClassName()+ "s_" + role + "s", id, key );
    }

    public void attachInverse(String role, String id,String key) throws SQLException{
        myquires.insertAttach(new Exception().getStackTrace()[1].getClassName()+ "s_" + role + "s", key, id);
    }

    public void dettach(String role, String id, String value) throws SQLException {
        myquires.deleteDettach(new Exception().getStackTrace()[1].getClassName() + "s_" + role + "s", id , role, value);
    }

    public ResultSet belongsToMany(String role, String id) throws SQLException {
        String className = new Exception().getStackTrace()[1].getClassName() + "s";
        return myquires.selectAllByIdBelongs(className, className + "_" +role +"s", id);
    }

    public ResultSet belongsToManytoMany(String role, String id) throws SQLException {
        String className = new Exception().getStackTrace()[1].getClassName() + "s";
        return myquires.selectAllByIdBelongsMany(role + "s", getclassNameforattach(role), id);
    }

    public ResultSet belongsTo(String role, String id) throws SQLException {
        return myquires.selectAllById(role + "s", id);
    }

    public ResultSet hasMany(String role, String id) throws  SQLException {
        return myquires.selectAllByMany(role + "s", new Exception().getStackTrace()[1].getClassName() + "_id" , id);
    }

    public void manyTOManyAttach(String role, String fields, String values) throws SQLException {
        myquires.insert(getclassNameforattach(role), fields, values);
    }

    public ResultSet manyToMany(String role, String id) throws SQLException {
        return myquires.selectAllWhere(getclassNameforattach(role),  new Exception().getStackTrace()[1].getClassName() + "_id = " + id );
    }

    /////////////////////////////// Private methods ////////////////////////////////////////////////////////////////////

    private String getclassNameforattach(String role){
        String className = new Exception().getStackTrace()[1].getClassName() + "s";
        if(new File("database/" +className + "_" + role + "s.sql").exists()){
            return className + "_" + role + "s";
        } else {
            return role+"s_" + className;
        }
    }
}

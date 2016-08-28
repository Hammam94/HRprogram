package com.company.Back_End.Core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
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

    public  boolean createTable(String tableName) throws SQLException, IOException {
        String content = null;
        if(!new File(tableName).exists()){
            File table = new File(tableName);
            FileReader fr = new FileReader(table);
            char[] chars = new char[(int) table.length()];
            fr.read(chars);
            content = new String(chars);
            myquires.createTable(content);
            fr.close();
            return true;
        }else if(new File(tableName).exists()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean createRelationTable(String role) throws Exception{
        String tabelName = getclassNameforattach(role);
        String content = null;
        if(!new File(tabelName).exists()){
            File table = new File(tabelName);
            FileReader fr = new FileReader(table);
            char[] chars = new char[(int) table.length()];
            fr.read(chars);
            content = new String(chars);
            myquires.createTable(content);
            fr.close();
            return true;
        }else if(new File(tabelName).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void dropTable (){

    }

    public void remove(String condition) throws SQLException {
        String table = new Exception().getStackTrace()[1].getClassName() + "s";
        myquires.delete(table,condition);
    }

    public void update(int fieldsNumber, String[] fields, String[] values, String condition) throws SQLException {
        String data = "";
        for(int i = 0; i < fieldsNumber; ++i){
            data +=  i >= fieldsNumber ? fields[i] + " = " + values[i] : fields[i] + " = " + values[i] + " , ";
        }
        myquires.update(new Exception().getStackTrace()[1].getClassName() + "s",data,condition);
    }

    public void save(String fields, String values) throws SQLException {
        myquires.insert(new Exception().getStackTrace()[1].getClassName() + "s", fields, values);
    }

    public ResultSet where(String condition) throws SQLException {
        return myquires.selectAllWhere(new Exception().getStackTrace()[1].getClassName() + "s", condition);
    }

    public ResultSet find (String id) throws SQLException {
        return myquires.selectAllById(new Exception().getStackTrace()[1].getClassName() + "s",id);
    }

    public ResultSet all() throws SQLException {
        return myquires.selectAllTable(new Exception().getStackTrace()[1].getClassName() + "s");
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

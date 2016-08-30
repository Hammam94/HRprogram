package com.company.Back_End.Core;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public boolean createTable(String tableName) throws SQLException, IOException {
        String content = null;
        String filePath = "src/com/company/Back_End/database/" +tableName;
        if(new File(filePath).exists()){
            File table = new File(filePath);
            FileReader fr = new FileReader(table);
            char[] chars = new char[(int) table.length()];
            fr.read(chars);
            content = new String(chars);
            myquires.createTable(content);
            fr.close();
            return true;
        }else if(!new File(filePath).exists()) {
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

    public void dropTable () throws SQLException {
        myquires.dropTable(getClassName());
    }

    public void remove(String condition) throws SQLException {
        String table = getClassName() + "s";
        myquires.delete(table,condition);
    }

    public void update(String[] fields, String[] values, String condition) throws SQLException {
        String data = "";
        for(int i = 0; i < fields.length; ++i){
            data +=  i >= fields.length ? fields[i] + " = " + values[i] : fields[i] + " = " + values[i] + " , ";
        }
        myquires.update(getClassName() + "s",data,condition);
    }

    public void save(String fields, String values) throws SQLException {
        myquires.insert(getClassName() + "s", fields, values);
    }

    public ResultSet where(String condition) throws SQLException {
        return myquires.selectAllWhere(getClassName() + "s", condition);
    }

    public ResultSet find(String id) throws SQLException {
        return myquires.selectAllById(getClassName() + "s",id);
    }

    public ResultSet all() throws SQLException {
        return myquires.selectAllTable(getClassName() + "s");
    }

    public void attach(String role, String id,String key) throws SQLException {
        myquires.insertAttach(getclassNameforattach(role), id, key );
    }

    public void attachInverse(String role, String id,String key) throws SQLException{
        myquires.insertAttach(getclassNameforattach(role), key, id);
    }

    public void dettach(String role, String id, String value) throws SQLException {
        myquires.deleteDettach(getclassNameforattach(role), id , role, value);
    }

    public ResultSet belongsToMany(String role, String id) throws SQLException {
        return myquires.selectAllByIdBelongs(getClassName(), getclassNameforattach(role), id);
    }

    public ResultSet belongsToManytoMany(String role, String id) throws SQLException {
        return myquires.selectAllByIdBelongsMany(role + "s", getclassNameforattach(role), id);
    }

    public ResultSet belongsTo(String role, String id) throws SQLException {
        return myquires.selectAllById(role + "s", id);
    }

    public ResultSet hasMany(String role, String id) throws  SQLException {
        return myquires.selectAllByMany(role + "s", getClassName() + "_id" , id);
    }

    public void manyTOManyAttach(String role, String fields, String values) throws SQLException {
        myquires.insert(getclassNameforattach(role), fields, values);
    }

    public ResultSet manyToMany(String role, String id) throws SQLException {
        return myquires.selectAllWhere(getclassNameforattach(role),  new Exception().getStackTrace()[1].getClassName() + "_id = " + id );
    }

    /////////////////////////////// Private methods ////////////////////////////////////////////////////////////////////

    private String getclassNameforattach(String role){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[2].getClassName().split("\\.");
        String className = myList[myList.length - 1] + "s";
        if(new File("database/" +className + "_" + role + "s.sql").exists()){
            return className + "_" + role + "s";
        } else {
            return role+"s_" + className;
        }
    }

    private String getClassName(){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[2].getClassName().split("\\.");
        String targetName = myList[myList.length - 1];
        return targetName.substring(0, targetName.length() - 10);
    }
}

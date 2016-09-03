package com.company.Back_End.core;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void update(String[] fields, Object[] values, String condition) throws SQLException {
        String data = "";
        for(int i = 0; i < fields.length; ++i){
            if(values[i].getClass() == int.class){
                data += i >= fields.length ? fields[i] + " = " + (int) values[i] : fields[i] + " = " + (int) values[i] + " , ";
            } else {
                data += i >= fields.length ? fields[i] + " = \"" + (String) values[i] + "\"" : fields[i] + " = \"" + (String) values[i] + "\" , ";
            }
        }
        myquires.update(getClassName() + "s",data,condition);
    }

    public void save(String fields, String values) throws SQLException {
        myquires.insert(getClassName() + "s", fields, values);
    }

    public void attach(String role, String fields, String values) throws SQLException {
        myquires.insertAttach(getclassNameforattach(role), fields, values);
    }

    public void attachMany(String frole, String srole, String fields, String values) throws SQLException {
        myquires.insertAttach(frole + "s_" + getClassName() + "s_" + srole + "s", fields, values);
    }

    public void attachInverse(String role,String fields, String values) throws SQLException{
        myquires.insertAttach(getclassNameforattach(role), fields, values);
    }

    public void manyTOManyAttach(String role, String fields, String values) throws SQLException {
        myquires.insert(getclassNameforattach(role), fields, values);
    }

    public void dettach(String role, String id, String value) throws SQLException {
        myquires.deleteDettach(getclassNameforattach(role), id , role, value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ResultSet all() throws SQLException {
        return myquires.selectAllTable(getClassName() + "s");
    }

    public ResultSet find(String id) throws SQLException {
        return myquires.selectAllById(getClassName() + "s",id);
    }

    public ResultSet findBy(String role, String value) throws SQLException {
        return myquires.selectAllByRole(getClassName() + "s", role, value);
    }

    public ResultSet where(String condition) throws SQLException {
        return myquires.selectAllWhere(getClassName() + "s", condition);
    }

    public ResultSet belongsTo(String role, String id) throws SQLException {
        return myquires.selectAllById(role + "s", id);
    }

    public ResultSet hasMany(String role, String id) throws  SQLException {
        return myquires.selectAllByMany(role + "s", getClassName() + "_id" , id);
    }

    public ResultSet belongsToManytoMany(String role, String id) throws SQLException {
        return myquires.selectAllByIdBelongsMany(role + "s", getClassNameForSelect(role), getColumnidName(),id);
    }

    public ResultSet manyTOManyTables(String mainRole, String firstRole, String id) throws SQLException{
        return myquires.selectAllbyIdBelongsTables(mainRole + "s", getClassNameForSelect(firstRole), getColumnidName(), id);
    }

    public ResultSet manyTOManyTables(String mainRole, String firstRole, String secondRole, String id) throws SQLException{
        String className = getClassName() + "s";
        return myquires.selectAllByIdBelongs(mainRole + "s",firstRole + "_" + className + "_" + secondRole, id);
    }

    public ResultSet aggregation(String aggregationName) throws SQLException {
        return myquires.selectAggregationALL(aggregationName, getClassName() + "s");
    }

    public ResultSet aggregation(String aggregationName, String role) throws SQLException {
        return myquires.selectAggregationRole(aggregationName, getClassName() + "s" , role);
    }

    public ResultSet aggregation(String aggregationName, String role, String condition) throws SQLException {
        return myquires.selectAggregationRoleCondition(aggregationName, getClassName() + "s", role, condition);
    }

    public ResultSet orderedBy(String condition, String orderedColumns, String arrenged) throws SQLException {
        return myquires.order(getClassName() + "s", condition, orderedColumns, arrenged);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void remove(String id) throws SQLException {
        String table = getClassName() + "s";
        System.out.print(table);
        myquires.delete(table, "id = " + id);
    }

    /////////////////////////////// Private methods ////////////////////////////////////////////////////////////////////

    private String getclassNameforattach(String role){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[2].getClassName().split("\\.");
        String className = myList[myList.length - 1];
        className = className.substring(0, className.length() - 10) + "s";
        if(new File("src/com/company/Back_End/database/" +className + "_" + role + "s.sql").exists()){
            return className + "_" + role + "s";
        } else {
            return role+"s_" + className;
        }
    }

    private String getClassNameForSelect(String role){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[3].getClassName().split("\\.");
        String className = myList[myList.length - 1];
        className = className.substring(0, className.length() - 10) + "s";
        if(new File("src/com/company/Back_End/database/" +className + "_" + role + "s.sql").exists()){
            return className + "_" + role + "s";
        } else {
            return role+"s_" + className;
        }
    }

    private String getColumnidName(){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[3].getClassName().split("\\.");
        String className = myList[myList.length - 1];
        return className.substring(0, className.length() - 10);
    }
    private String getClassName(){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String[] myList = stack[2].getClassName().split("\\.");
        String targetName = myList[myList.length - 1];
        return targetName.substring(0, targetName.length() - 10);
    }

}

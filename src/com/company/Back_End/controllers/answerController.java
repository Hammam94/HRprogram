package com.company.Back_End.controllers;

import com.company.Back_End.models.Answer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class answerController extends Answer {
    private String fields = "answer, interviewee_id, question_id";
    public answerController() throws SQLException, IOException {
    }

    public boolean create(String answer, String interviewee_id, String question_id) throws SQLException {
        String values = "\"" + answer + "\"," + interviewee_id + "," + question_id;
        this.save(fields,values);
        return true;
    }

    public ResultSet index() throws SQLException {
        return this.all();
    }

    public ResultSet show(String id) throws SQLException {
        return this.find(id);
    }

    public boolean updata (String[] fields, Object[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        this.delete(id);
        return true;
    }
}

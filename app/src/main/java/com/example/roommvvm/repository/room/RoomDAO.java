package com.example.roommvvm.repository.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RoomDAO {


//    this interface will help in communication between the repo and the sql table

//    these annotation will automaticlly build the code for the sql commnds

    @Insert
    void insert(Note note);


    @Update
    void update(Note note);


    @Delete
    void delete(Note note);


//    for making the custom queries we use the @Query() annotation
//    and if we make any mistake in the sql queriy then the app will not compile
    @Query("DELETE FROM notes")
    void deleteAll();



//    at the compile time room will check weather the columns of the table matches with the Note.class or not
//and the columns doesn't matches with the Note.class file then it will give the compile time error


//  by using the LiveData, we can observe this object now
    @Query("SELECT * FROM notes ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}

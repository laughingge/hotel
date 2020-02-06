package com.example.hotel.service;



import com.example.hotel.entity.Roomtype;

import java.util.List;

public interface RoomtypeService {
    List<Roomtype> getAll();
    List<Roomtype> getroomtypename();
    Integer insertRoomtype(Roomtype roomtype);
    Integer deleteRoomtypeByID(Integer roomtypeid);
    Roomtype getRoomtypeByroomtypeid(Integer roomtypeid);
    Integer updateRoomtypeByID(Roomtype roomtype);
    Roomtype getRoomtypeByroomtypename(String roomtypename);
    Integer getCount();
}

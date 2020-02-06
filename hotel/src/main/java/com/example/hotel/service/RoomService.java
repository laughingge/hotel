package com.example.hotel.service;

import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll(int pageNum, int pageSize);
    Integer insertRoom(Room room);
    Integer deleteRoomByID(Integer roomid);
    Room getRoomByroomid(Integer roomid);
    Integer updateRoomByID(Room room);
    List<Room> getnumber();
    Room getRoomBynumber(String number);
    Integer updatestateBynumber(Room room);
    Float getBaifenbi();
    List<PieChart> getstate();
    List<Room> getSome();
}

package com.example.hotel.serviceImpl;



import com.example.hotel.entity.Roomtype;
import com.example.hotel.mapper.RoomtypeMapper;
import com.example.hotel.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomtypeServiceImpl implements RoomtypeService {
    @Autowired
    private RoomtypeMapper roomtypeMapper;


    @Override
    public List<Roomtype> getAll() {
        return roomtypeMapper.getAll();
    }

    @Override
    public List<Roomtype> getroomtypename() {
        return roomtypeMapper.getroomtypename();
    }

    @Override
    public Integer insertRoomtype(Roomtype roomtype) {
        return roomtypeMapper.insertRoomtype(roomtype);
    }

    @Override
    public Integer deleteRoomtypeByID(Integer roomtypeid) {
        return roomtypeMapper.deleteRoomtypeByID(roomtypeid);
    }

    @Override
    public Roomtype getRoomtypeByroomtypeid(Integer roomtypeid) {
        return roomtypeMapper.getRoomtypeByroomtypeid(roomtypeid);
    }

    @Override
    public Integer updateRoomtypeByID(Roomtype roomtype) {
        return roomtypeMapper.updateRoomtypeByID(roomtype);
    }

    @Override
    public Roomtype getRoomtypeByroomtypename(String roomtypename) {
        return roomtypeMapper.getRoomtypeByroomtypename(roomtypename);
    }

    @Override
    public Integer getCount() {
        return roomtypeMapper.getCount();
    }
}


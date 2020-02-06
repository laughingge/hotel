package com.example.hotel.serviceImpl;



import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Room;
import com.example.hotel.mapper.RoomMapper;

import com.example.hotel.service.RoomService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;


    @Override
    public List<Room> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return roomMapper.getAll();
    }

    @Override
    public Integer insertRoom(Room room) {
        return roomMapper.insertRoom(room);
    }

    @Override
    public Integer deleteRoomByID(Integer roomid) {
        return roomMapper.deleteRoomByID(roomid);
    }

    @Override
    public Room getRoomByroomid(Integer roomid) {
        return roomMapper.getRoomByroomid(roomid);
    }

    @Override
    public Integer updateRoomByID(Room room) {
        return roomMapper.updateRoomByID(room);
    }

    @Override
    public List<Room> getnumber() {
        return roomMapper.getnumber();
    }

    @Override
    public Room getRoomBynumber(String number) {
        return roomMapper.getRoomBynumber(number);
    }

    @Override
    public Integer updatestateBynumber(Room room) {
        return roomMapper.updatestateBynumber(room);
    }

    @Override
    public Float getBaifenbi() {
        return roomMapper.getBaifenbi();
    }

    @Override
    public List<PieChart> getstate() {
        return roomMapper.getstate();
    }

    @Override
    public List<Room> getSome() {
        return roomMapper.getSome();
    }

}


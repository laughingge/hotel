package com.example.hotel.serviceImpl;


import com.example.hotel.entity.Floor;
import com.example.hotel.mapper.FloorMapper;

import com.example.hotel.service.FloorService;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorMapper floorMapper;


    @Override
    public List<Floor> getAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Floor> list=floorMapper.getAll();

        return list;
    }

    @Override
    public List<Floor> getfloorname() {
        return floorMapper.getfloorname();
    }

    @Override
    public Integer insertFloor(Floor floor) {
        return floorMapper.insertFloor(floor);
    }

    @Override
    public Integer deleteFloortByID(Integer floorid) {
        return floorMapper.deleteFloorByID(floorid);
    }

    @Override
    public Floor getUserByfloorid(Integer floorid) {
        return floorMapper.getUserByfloorid(floorid);
    }

    @Override
    public Integer updateFloorByID(Floor floor) {
        return floorMapper.updateFloorByID(floor);
    }

    @Override
    public Floor getFloorByfloorname(String floorname) {
        return floorMapper.getFloorByfloorname(floorname);
    }

    @Override
    public Floor getfloornameByfloorid(Integer floorid) {
        return floorMapper.getfloornameByfloorid(floorid);
    }
}


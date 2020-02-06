package com.example.hotel.service;



import com.example.hotel.entity.Floor;

import java.util.List;

public interface FloorService {
    List<Floor> getAll(int pageNum, int pageSize);
    List<Floor> getfloorname();
    Integer insertFloor(Floor floor);
    Integer deleteFloortByID(Integer floorid);
    Floor getUserByfloorid(Integer floorid);
    Integer updateFloorByID(Floor floor);
    Floor getFloorByfloorname(String floorname);
    Floor getfloornameByfloorid(Integer floorid);
}

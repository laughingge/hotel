package com.example.hotel.entity;

import java.io.Serializable;

public class Floor implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column floor.floorid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private Integer floorid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column floor.floorname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String floorname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column floor.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String message;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column floor.floorid
     *
     * @return the value of floor.floorid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public Integer getFloorid() {
        return floorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column floor.floorid
     *
     * @param floorid the value for floor.floorid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setFloorid(Integer floorid) {
        this.floorid = floorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column floor.floorname
     *
     * @return the value of floor.floorname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getFloorname() {
        return floorname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column floor.floorname
     *
     * @param floorname the value for floor.floorname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setFloorname(String floorname) {
        this.floorname = floorname == null ? null : floorname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column floor.message
     *
     * @return the value of floor.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column floor.message
     *
     * @param message the value for floor.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
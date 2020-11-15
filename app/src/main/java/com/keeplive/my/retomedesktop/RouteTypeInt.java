package com.keeplive.my.retomedesktop;

public class RouteTypeInt {
    public static final int WALK = 0;
    public static final int BUS = 1;
    public static final int CAR = 2;

    public static void setRouteType(@RouteTypeDef int routeType){

    }

    public static void main(String[] args) {
        RouteTypeInt.setRouteType(BUS);
    }
}
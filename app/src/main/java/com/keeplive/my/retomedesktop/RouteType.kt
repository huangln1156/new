package com.bennyhuo.kotlin

import com.keeplive.my.retomedesktop.RouteTypeDef

//枚举开销大
enum class RouteTypeEnum {
    WALK,
    BUS,
    CAR
}


fun setRouteType(@RouteTypeDef routeType: Int){

}

fun setRouteType(routeType: RouteTypeInline){

}


fun main(){
    // IntDef 在 Kotlin 中不生效
    setRouteType(4)
    setRouteType(RouteTypes.BUS)
}

//编译后拆箱为整型，降低内存开销
inline class RouteTypeInline(val value: Int)

object RouteTypes {
    val WALK = RouteTypeInline(0)
    val BUS = RouteTypeInline(1)
    val CAR = RouteTypeInline(2)
}


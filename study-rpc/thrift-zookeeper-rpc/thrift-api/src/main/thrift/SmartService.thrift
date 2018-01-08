namespace java cn.yefan.thrift.api

include "SmartTypes.thrift"

service SmartService{

SmartTypes.User getUserById(i32 uid)

}
- một số loại Area được xác định bởi 2 số nguyên, gồm có loại nhà chung (type1) và loại nhà cụ thể (type2):
+ defense building
+ obstacle
+ resource building
+ storage building

- API khái quát (có thể chỉnh sửa trong quá trình code), output trạng thái trả về SUCCESS sẽ là 0, các trạng thái còn lại từ 1 trở đi

0. login: 
input:
   ...
output:
   UserInfo + UserGameData (trong class diagram)
1.addBuilding
input: 
   type1, type2, pos
output:
   BUILD_FAIL_NOT_ENOUGH_RESOURCES
   BUILD_FAIL_NO_WORKER_AVAILABLE
   BUILD_FAIL_NOT_VALID_POSITION
   BUILD_FAIL_ENOUGH_FOR_THIS_LEVEL
   BUILD_SUCCESS, objectId


2.stopUpgrading
input:
   objectId
output:
   STOP_UPGRADE_FAIL_INVALID_OBJECTID
   STOP_UPGRADE_FAIL_STATUS_UPGRADING_ALREADY
   STOP_UPGRADE_SUCCESS


3.moveBuilding
input:
   objectId, newPos
output:
   MOVE_FAIL_INVALID_OBJECTID
   MOVE_FAIL_INVALID_POSITION
   MOVE_SUCCESS


4.upgradeBuilding
input:
   objectId
output:
   UPGRADE_FAIL_INVALID_OBJECTID
   UPGRADE_FAIL_NOT_ENOUGH_RESOURCES
   UPGRADE_FAIL_NO_WORKER_AVAILABLE
   UPGRADE_FAIL_NOT_ENOUGH_LEVEL_TOWN_HALL
   UPGRADE_FAIL_UPGRADING_ALREADY
   UPGRADE_SUCCESS


5.upgradeNow
input:
   objectId
output:
   UPGRADE_NOW_FAIL_INVALID_OBJECTID
   UPGRADE_NOW_FAIL_NOT_ENOUGH_RESOURCES
   UPGRADE_NOW_FAIL_NOT_UPGRADING
   UPGRADE_NOW_SUCCESS


6.harvest
input:
   objectId
output:
   HARVEST_FAIL_INVALID_OBJECTID
   HARVEST_FAIL_NOT_ENOUGH_AMOUNT
   HARVEST_SUCCESS, amountHarvested


7.investigate
input:
   objectId, type2
output:
   INVESTIGATE_FAIL_INVALID_OBJECTID
   INVESTIGATE_FAIL_NOT_ENOUGH_RESOURCES
   INVESTIGATE_FAIL_NOT_ENOUGH_LEVEL_TOWN_HALL
   INVESTIGATE_FAIL_UPGRADING
   INVESTIGATE_FAIL_INVESTIGATING_ALREADY
   INVESTIGATE_SUCCESS


8.investigateNow
input:
   objectId, type2,amount
output:
   INVESTIGATE_NOW_FAIL_INVALID_OBJECTID
   INVESTIGATE_NOW_FAIL_NOT_ENOUGH_RESOURCES
   INVESTIGATE_NOW_FAIL_UPGRADING
   INVESTIGATE_NOW_FAIL_NOT_INVESTIGATING
   INVESTIGATE_NOW_SUCCESS


9.stopInvestigate
input: 
   objectId
output:
   STOP_INVESTIGATE_FAIL_INVALID_OBJECTID
   STOP_INVESTIGATE_FAIL_NOT_INVESTIGATING
   STOP_INVESTIGATE_SUCCESS


10.trainSoldier/Sorcery
input:
   objectId, amount (+,-) ,type2
output:
   TRAIN_FAIL_INVALID_OBJECTID
   TRAIN_FAIL_NOT_ENOUGH_RESOURCES
   TRAIN_FAIL_NOT_ENOUGH_LEVEL_TOWN_HALL
   TRAIN_FAIL_UPGRADING
   TRAIN_FAIL_NOT_ENOUGH_CAPACITY
   INVESTIGATE_SUCCESS


11.trainSoldier/Sorcery now
input: 
   objectId
output:
   TRAIN_NOW_FAIL_INVALID_OBJECTID
   TRAIN_NOW_FAIL_NOT_ENOUGH_RESOURCES
   TRAIN_NOW_FAIL_NOT_TRAINING
   TRAIN_NOW_FAIL_NOT_ENOUGH_ROOM
   TRAIN_NOW_SUCCESS


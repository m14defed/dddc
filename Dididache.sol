pragma solidity  0.5.0;
pragma experimental ABIEncoderV2;



contract  Dididache{
    address private   ower;
    //司机的结构体
    struct Driver{
        //司机的账户address
        address account;
        //资金
        int256  tokens;
        //订单数组
        string[] ids;
    }
    //顾客的结构体
    struct Passenger{
        //司机的账户address
        address account;
        //资金
        int256  tokens;
        //订单数组
        string[] ids;
    }

    mapping (address =>bool) isDriver;
    mapping (address=>bool) isPassenger;


    //通过地址查询司机
    mapping (address => Driver) getDriver;
    //查询顾客
    mapping  (address => Passenger) getPassenger;
    //构造函数
    constructor() public {
        ower=msg.sender;
    }
    //管理者
    modifier onlyOwner(){
        require(msg.sender == ower, "Only the owner can call this function");
        _;
    }
    //司机权限
    modifier onlyDriver(){
        require(isDriver[msg.sender], "Only the Driver can call this function");
        _;
    }
    //乘客
    modifier onlyPassenger(){
        require(isPassenger[msg.sender], "Only the Passenger can call this function");
        _;
    }
    //添加司机
    function  addDriver(address _add,int256 _tokens) public onlyOwner {
        Driver memory newDriver = Driver(_add,_tokens, new string[](0));
        getDriver[_add]=newDriver;
        isDriver[_add]=true;
    }
    //添加乘客
    function  addPassenger(address _add,int256 _tokens) public onlyOwner{
        Passenger memory newPassenger =Passenger(_add,_tokens, new string[](0));
        getPassenger[_add]=newPassenger;
        isPassenger[_add]=true;
    }
    //订单的结构体
    struct  Order {
        //订单的id
        string id;
        //起点
        string origin;
        //终点
        string end;
        //司机
        address accountDriver;
        //乘客
        address accountPassenger;
        //状态码 （0）代表未被接收 （1）已经被接单 （2） 已经被完成
        uint status;
        //价格 这个应该是系统生成的，这里我给个固定值
        uint  price;
    }
    mapping (string=>Order) orderMaping;
    //根据id返回订单信息
    function getOrder(string memory id) public view  returns (string memory,string memory,address,address,uint,uint) {
        Order memory order = orderMaping[id];
        return (order.origin,order.end,order.accountDriver,order.accountPassenger,order.status,order.price);
    }



    //顾客提交订单
    function addOrder(string memory id,string memory origin,string memory end) onlyPassenger public {
        Order memory newOrder = Order(id,origin,end,address(0),msg.sender,0,10);
        orderMaping[id]=newOrder;
        Passenger memory passenger = getPassenger[msg.sender];
        string[]  memory ids1 = passenger.ids;
        string[] memory newIds = new string[](ids1.length+1);
        for(uint i=0;i<ids1.length;i++){
            newIds[i]=ids1[i];
        }
        newIds[ids1.length]=id;
        passenger.ids=newIds;
        getPassenger[msg.sender]=passenger;
    }
    //司机接单
    function reception(string memory id) onlyDriver public {
        Order memory order = orderMaping[id];
        require(order.status==0,"no 0");
        order.accountDriver=msg.sender;
        order.status=1;
        orderMaping[id]=order;
        Driver memory driver =getDriver[msg.sender];
        string[]  memory ids1 = driver.ids;
        string[] memory newIds = new string[](ids1.length+1);
        for(uint i=0;i<ids1.length;i++){
            newIds[i]=ids1[i];
        }
        newIds[ids1.length]=id;
        driver.ids=newIds;
        getDriver[msg.sender]=driver;

    }
    //顾客提交订单
    function complete(string memory id) onlyPassenger public {
        Order memory order = orderMaping[id];
        require(order.accountPassenger==msg.sender,"no ziji");
        order.status=2;
        orderMaping[id]=order;
        Passenger memory passenger = getPassenger[msg.sender];
        passenger.tokens=passenger.tokens-int(order.price);
        getPassenger[msg.sender]=passenger;
        Driver memory driver =getDriver[order.accountDriver];
        driver.tokens=driver.tokens+int(order.price);
        getDriver[order.accountDriver]=driver;
    }
    //查询自己的信息 myType是人物类型（0）司机 （1）乘客
    function  getPeople(uint8 myType) public view  returns (int256 ,string[] memory){
        if (myType==0){
            require(isDriver[msg.sender], "Only the Driver can call this function");
            Driver memory driver =getDriver[msg.sender];
            return ( driver.tokens, driver.ids);
        }else {
            require(isPassenger[msg.sender], "Only the Passenger can call this function");
            Passenger memory passenger = getPassenger[msg.sender];
            return ( passenger.tokens, passenger.ids);
        }
    }
    //充值接口
    function recharge (int256 money) public onlyPassenger {
        Passenger memory passenger = getPassenger[msg.sender];
        passenger.tokens=passenger.tokens+money;
        getPassenger[msg.sender]=passenger;
    }
    //提款接口
    function deposit(int256 money) public onlyDriver{
        Driver memory driver = getDriver[msg.sender];
        require(driver.tokens>money,"qian tai shao le");
        driver.tokens=driver.tokens-money;
        getDriver[msg.sender]=driver;
    }

}
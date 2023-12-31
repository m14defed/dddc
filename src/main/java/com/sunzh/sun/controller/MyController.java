package com.sunzh.sun.controller;

import com.alibaba.fastjson.JSONObject;
import com.sunzh.sun.pojo.OrdorBO;
import com.sunzh.sun.pojo.OrdorBchuanru;
import com.sunzh.sun.raw.Dididache;
import com.sunzh.sun.util.Result;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple6;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.UUID;

@RestController
public class MyController {
    @Autowired
    Client client;
    @Autowired
    CryptoKeyPair cryptoKeyPair;
    @Value("${add}")
    private  String contractAddress;
//    @RequestMapping("/getBolk")
//    public  String get(){
//        BlockNumber blockNumber = client.getBlockNumber();
//        return blockNumber.getBlockNumber().toString();
//    }
//    @RequestMapping("/getBolk2")
//    public BigInteger get2() throws ContractException {
//        MainContract mc = MainContract.load("0x18d1edbde91831654d554d873013f317e22ab645", client, cryptoKeyPair);
//        Tuple2<String, String> addEmployerAccountInput = mc.getAddEmployerAccountInput(new TransactionReceipt());
//        addEmployerAccountInput.getValue1();
//        addEmployerAccountInput.getValue1();
//    }
//    @RequestMapping("/getBolk3")
//     public  String get3() throws ContractException {
//         Mycontra load = Mycontra.load("0x0a2be1acd875dcfcfe021958baf7de62a34e687c", client, cryptoKeyPair);
//         String myAccount = load.getMyAccount();
//        System.out.println(contractAddress);
//         return myAccount;
//     }
//
//
//    @RequestMapping("/getBolk4")
//    public String loadPemAccount() throws ContractException {
//        // 通过client获取CryptoSuite对象
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        // 加载pem账户文件
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem", "kaiwanx", null);
////        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
////        Mycontra load = Mycontra.load("0x0a2be1acd875dcfcfe021958baf7de62a34e687c", client, cryptoKeyPair1);
//        String myAccount = load.getMyAccount();
//        return myAccount;
//
//
//    }

    // Rest of your service code...



    //添加订单
//    @PostMapping("/addOrder")
//    public Result<String> addOrder(@RequestBody OrdorBchuanru ordorBO){
//        // 通过client获取CryptoSuite对象
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        //写出路径
//        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+ordorBO.getAccount()+".pem";
//        System.out.println(myaccout);
//        // 加载pem账户文件
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem",myaccout, null);
//        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//        Dididache dididache = Dididache.load(contractAddress, client, cryptoKeyPair1);
//        //本来想用UUID后来发现是整数崩溃了
//        String uuid = UUID.randomUUID().toString();
//
//        TransactionReceipt transactionReceipt = dididache.addOrder(uuid, ordorBO.getOrigin(), ordorBO.getEnd());
//
//
//        if (transactionReceipt.isStatusOK()==true){
//            System.out.println(Result.Success());
//           return  Result.Success(transactionReceipt.toString());
//
//        }
//        return  Result.Eorr(transactionReceipt.toString());
//    }
    //接收
//    @RequestMapping("/reception")
//    public Result reception(String id ,String accout ){
//        // 通过client获取CryptoSuite对象
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//        // 加载pem账户文件
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem",myaccout , null);
//        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//        TransactionReceipt reception = dididache.reception(id);
//        if (reception.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(reception.toString());
//
//        }
//        return  Result.Eorr(reception.toString());
//    }

    //提交
//    @RequestMapping("/complete")
//    public Result complete(String id ,String accout){
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        // 加载pem账户文件
//        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem", myaccout, null);
//        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//        TransactionReceipt complete = dididache.complete(id);
//        if (complete.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(complete.toString());
//
//        }
//        return  Result.Eorr(complete.toString());
//    }

    //增加司机
//    @RequestMapping("/addDriver")
//    public Result addDriver(@RequestBody UserInfoBO people){
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair);
//        String s = AccoutUtil.saveAccountWithPem(people.getAccount());
//        TransactionReceipt transactionReceipt = dididache.addDriver(s, people.getTokens());
//        if (transactionReceipt.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(transactionReceipt.toString());
//
//        }
//        return  Result.Eorr(transactionReceipt.toString());
//
//    }
//    //增加乘客
//    @RequestMapping("/addPassenger")
//    public Result addPassenger(@RequestBody UserInfoBO people){
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair);
//        String s = AccoutUtil.saveAccountWithPem(people.getAccount());
//        TransactionReceipt transactionReceipt = dididache.addPassenger(s, people.getTokens());
//        if (transactionReceipt.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(transactionReceipt.toString());
//
//        }
//        return  Result.Eorr(transactionReceipt.toString());
//    }
//    @RequestMapping("/getOrder")
//    public Result getOrder(String id) throws ContractException {
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair);
//        Tuple6<String, String, String, String, BigInteger, BigInteger> order = dididache.getOrder(id);
//        OrdorBO ordorBO = new OrdorBO();
//        ordorBO.setId(id);
//        ordorBO.setOrigin(order.getValue1());
//        ordorBO.setEnd(order.getValue2());
//        ordorBO.setAddress1(order.getValue3());
//        ordorBO.setAddress1(order.getValue4());
//        ordorBO.setStatus(order.getValue5());
//        ordorBO.setPrice(order.getValue6());
//        JSONObject outjson = new JSONObject();
//        outjson.put("dingdanxinxi",ordorBO);
//
//        return Result.Success(outjson);
//    }
//    @RequestMapping("/getUserinfo")
//    public Result getUserinfo(Integer types, String accout) throws ContractException {
//        if (types==0){
//            // 通过client获取CryptoSuite对象
//            CryptoSuite cryptoSuite = client.getCryptoSuite();
//            String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//            // 加载pem账户文件
//            CryptoSuite cryptoSuite1 = cryptoSuite;
//            cryptoSuite1.loadAccount("pem",myaccout, null);
//            CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//            Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//            Tuple2<BigInteger, List<String>> people = dididache.getPeople(BigInteger.valueOf(0));
//            BigInteger value1 = people.getValue1();
//            List<String> value2 = people.getValue2();
//            UserInfoBO people1 = new UserInfoBO();
//
//            people1.setTokens(value1);
//            people1.setIds(value2);
//            JSONObject outjson = new JSONObject();
//            outjson.put("siji",people1);
//            return Result.Success(outjson);
//
//        }else {
//            // 通过client获取CryptoSuite对象
//            CryptoSuite cryptoSuite = client.getCryptoSuite();
//            // 加载pem账户文件
//            CryptoSuite cryptoSuite1 = cryptoSuite;
//            String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//            cryptoSuite1.loadAccount("pem",myaccout , null);
//            CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//            Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//            Tuple2<BigInteger, List<String>> people = dididache.getPeople(BigInteger.valueOf(1));
//            BigInteger value1 = people.getValue1();
//            List<String> value2 = people.getValue2();
//            UserInfoBO people1 = new UserInfoBO();
//            people1.setTokens(value1);
//            people1.setIds(value2);
//            JSONObject outjson = new JSONObject();
//            outjson.put("chengke",people1);
//            return Result.Success(outjson);
//        }

//    }
/*
* 乘客充值接口
*
* */
//    @RequestMapping("/recharge")
//    public Result recharge(BigInteger qian,String accout){
//        // 通过client获取CryptoSuite对象
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//        // 加载pem账户文件
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem",myaccout, null);
//        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//        TransactionReceipt recharge = dididache.recharge(qian);
//
//        if (recharge.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(recharge.toString());
//
//        }
//        return  Result.Eorr(recharge.toString());
//
//    }
    /*
    * 车主提钱接口
    *
    * */
//    @RequestMapping("/deposit")
//    public Result deposit(BigInteger qian,String accout){
//        // 通过client获取CryptoSuite对象
//        CryptoSuite cryptoSuite = client.getCryptoSuite();
//        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
//        // 加载pem账户文件
//        CryptoSuite cryptoSuite1 = cryptoSuite;
//        cryptoSuite1.loadAccount("pem",myaccout, null);
//        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
//        Dididache  dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
//        TransactionReceipt deposit = dididache.deposit(qian);
//        if (deposit.isStatusOK()==true){
//            System.out.println(Result.Success());
//            return  Result.Success(deposit.toString());
//
//        }
//        return  Result.Eorr(deposit.toString());
//    }

}

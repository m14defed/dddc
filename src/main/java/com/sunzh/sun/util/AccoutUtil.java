package com.sunzh.sun.util;

import com.sunzh.sun.raw.Dididache;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
//私人工具包
public class AccoutUtil {
    @Autowired
    Client  client;
    @Autowired
    CryptoKeyPair cryptoKeyPair;
    @Value("${add}")
    private  String contractAddress;
    public static String saveAccountWithPem(String name) {

        // 以pem的格式保存账户文件到pemFilePath路径
        // 创建非国密类型的CryptoSuite
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
// 随机生成非国密公私钥对
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
// 获取账户地址
        String accountAddress = cryptoKeyPair.getAddress();
        System.out.println(accountAddress);
        String filePath = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+name+".pem";

        // 创建Path对象
        Path path = Paths.get(filePath);

        try {
            // 使用Files工具类创建文件
            Files.createFile(path);
            System.out.println("文件创建成功");
        } catch (IOException e) {
            System.err.println("文件创建失败: " + e.getMessage());
        }

        cryptoKeyPair.storeKeyPairWithPem(filePath);
        return accountAddress;
    }
    public  Dididache getDididache(String accout){
        CryptoSuite cryptoSuite = client.getCryptoSuite();
        // 加载pem账户文件
        String myaccout = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\"+accout+".pem";
        CryptoSuite cryptoSuite1 = cryptoSuite;
        cryptoSuite1.loadAccount("pem", myaccout, null);
        CryptoKeyPair cryptoKeyPair1 = cryptoSuite1.getCryptoKeyPair();
        Dididache dididache = Dididache.load(contractAddress,client,cryptoKeyPair1);
        return dididache;
    }
    public  Dididache getDididache(){
        Dididache dididache = Dididache.load(contractAddress,client,cryptoKeyPair);
        return dididache;
    }
}

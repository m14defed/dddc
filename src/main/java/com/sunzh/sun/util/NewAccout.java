package com.sunzh.sun.util;

import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewAccout {
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
}

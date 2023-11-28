package com.sunzh.sun;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.solc.compiler.SolidityCompiler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.fisco.solc.compiler.SolidityCompiler.Options;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@SpringBootTest
class SunApplicationTests {
    @Autowired
    CryptoKeyPair cryptoKeyPair;
    @Test
    void contextLoads() {

    }
    @Test
    public void test() {
        SolidityCompiler.Result result;
        try {
            // Solidity 源代码
            String soliditySourceCode = "contract HelloWorld { function sayHello() public pure returns(string memory) { return 'Hello, World!'; } }";

            // 使用 solcJ 编译 Solidity 源代码

            result = SolidityCompiler.compile(soliditySourceCode.getBytes(), true,true, Options.ABI, Options.BIN);
            System.out.println(result.getOutput());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    Client client;
    @Test
    public void saveAccountWithPem() {

        // 以pem的格式保存账户文件到pemFilePath路径
        // 创建非国密类型的CryptoSuite
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
// 随机生成非国密公私钥对
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
// 获取账户地址
        String accountAddress = cryptoKeyPair.getAddress();
        System.out.println(accountAddress);
        String filePath = "D:\\JavaPrejeck\\studySDK\\sun\\src\\main\\resources\\account\\孙中辉";

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
    }
}

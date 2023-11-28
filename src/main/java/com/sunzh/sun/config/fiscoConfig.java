package com.sunzh.sun.config;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
public class fiscoConfig {
    @Value("${fisco.network.nodeList}")
    private String nodeLists;
    @Value("${fisco.crptoMaterial.certPath}")
    private String certPath;
    @Value("${fisco.account.accountFilePath}")
    private String accountFilePath;
    @Value("${fisco.account.accountAddress}")
    private String accountAddress;



    @Bean
    public ConfigProperty getConfigProperty() {
        // 配置cryptoMaterial
        ConfigProperty configProperty = new ConfigProperty();
        HashMap<String, Object> cryptoMaterialMap = new HashMap<>();
        cryptoMaterialMap.put("certPath", certPath);
        configProperty.setCryptoMaterial(cryptoMaterialMap);
        // 配置network
        HashMap<String, Object> networkMap = new HashMap<>();
        String[] split = nodeLists.split(",");
        List<String> nodeList = Arrays.asList(split);
        networkMap.put("peers", nodeList);
        configProperty.setNetwork(networkMap);
        //配置account
        HashMap<String, Object> accountMap = new HashMap<>();
        accountMap.put("keyStoreDir", "account");
        accountMap.put("accountAddress", accountAddress);
        accountMap.put("accountFileFormat", "pem");
        accountMap.put("password", "");
        accountMap.put("accountFilePath", accountFilePath);
        configProperty.setAccount(accountMap);


        return configProperty;
    }
    @Bean
    public ConfigOption getConfigOption(ConfigProperty configProperty) throws ConfigException {
        return new ConfigOption(configProperty);
    }
    @Bean
    public  BcosSDK getBcosSDK(ConfigOption configOption){
        BcosSDK bcosSDK = new BcosSDK(configOption);
        return bcosSDK;
    }
    @Bean
    public Client getClient(BcosSDK bcosSDK){
        Client client = bcosSDK.getClient(Integer.valueOf(1));
        return client;
    }
    @Bean
    public CryptoKeyPair getCryptoKeyPair(Client client) {
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        return cryptoKeyPair;
    }

 }

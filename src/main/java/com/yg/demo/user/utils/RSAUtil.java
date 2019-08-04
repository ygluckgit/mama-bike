package com.yg.demo.user.utils;
//package com.yg.demo.user.utils;/**
// * Created by wangjianbin on 2017/7/31.
// */
//
//
//import org.bouncycastle.asn1.*;
//
//import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
//import javax.crypto.Cipher;
//import java.io.InputStream;
//import java.security.*;
//import java.security.spec.AlgorithmParameterSpec;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * 加密解密工具类
// * @author wangjianbin
// * @create 2017-07-31 15:28
// **/
//
//public class RSAUtil {
//
////    public static void getKeyGene() throws NoSuchAlgorithmException {
////        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
////        keyPairGenerator.initialize(1024);
////        KeyPair keyPair = keyPairGenerator.generateKeyPair();
////        PublicKey publicKey = keyPair.getPublic();
////        PrivateKey privateKey = keyPair.getPrivate();
////        System.out.println(Base64Util.encode(privateKey.getEncoded()));
////    }
//
//
//    /**
//     * 私钥字符串
//     */
//    private static String PRIVATE_KEY = "";
//    /**
//     * 公钥字符串
//     */
//
//    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHLvDhH6L1S2T5KyfQqNZz W+rK6hH3Cl7S4YmkWHg7TMWTFfuumLlAOlr7vvZ/7gj83iXn7t7nhHtN6eNe zYLB3bQw6tgedsbSpwPpl4eFTrqmNbYVzOkXW11+QiuEHXmqx/STRX4nQZZC Ly7YZO3w/Vzr6Urshacxd3K16w6+FwIDAQAB";
//
//    public static final String KEY_ALGORITHM = "RSA";
//
//    public RSAUtil() throws NoSuchAlgorithmException {
//    }
//
//
//    /**
//     * 读取密钥字符串
//     *
//     * @throws Exception
//     */
//
//    public static void convert() throws Exception {
//        byte[] data = null;
//
//        try {
//            InputStream is = RSAUtil.class.getResourceAsStream("/enc_pri");
//            //将文件内容全部读取
//            int length = is.available();
//            data = new byte[length];
//            // Base64Util.encode(data);
//            //返回一个值如果读取到最后一个值返回-1
//            is.read(data);
//        } catch (Exception e) {
//        }
//
//        String dataStr = new String(data);
//        try {
//            PRIVATE_KEY = dataStr;
//        } catch (Exception e) {
//        }
//
//        if (PRIVATE_KEY == null) {
//            throw new Exception("Fail to retrieve key");
//        }
//    }
//
//
//    /**
//     * 私钥解密
//     *
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
//        convert();
//        //生成公钥和私钥对的时候使用BaseUtil.encode进行加密。
//        byte[] keyBytes = Base64Util.decode(PRIVATE_KEY);
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        java.security.Security.addProvider(
//                new org.bouncycastle.jce.provider.BouncyCastleProvider()
//        );
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//         Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
//      //  Key privateKey = makePrivateKey(PRIVATE_KEY);
//        Cipher cipher = Cipher.getInstance("RSA");
//        //Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * @ClassName 公钥加密
//     * @Author Yguang
//     * @Date 2019/7/17 10:57
//     * @Description
//     */
//    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
//        byte[] keyBytes = Base64Util.decode(key);
//        X509EncodedKeySpec pkcs8KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Key publicKey = keyFactory.generatePublic(pkcs8KeySpec);
//        // Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] dosec = cipher.doFinal(data);
//        return dosec;
//    }
//
//    /**
//     * 引入第三方密码工具包 处理编码
//     *
//     * @param stored
//     * @return
//     * @throws GeneralSecurityException
//     * @throws Exception
//     */
//    public static PrivateKey makePrivateKey(String stored) throws GeneralSecurityException, Exception {
//        /*byte[] data = Base64.getDecoder().decode(stored);
//        PKCS8EncodedKeySpec spec = new  PKCS8EncodedKeySpec(data);
//        KeyFactory fact = KeyFactory.getInstance("RSA");
//        return fact.generatePrivate(spec);*/
//        //生成公钥私钥时都是用Base64Utilencode的
//        byte[] data = Base64Util.decode(stored);
//        ASN1EncodableVector v = new ASN1EncodableVector();
//        v.add(new ASN1Integer(0));
//        ASN1EncodableVector v2 = new ASN1EncodableVector();
//        v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
//        v2.add(DERNull.INSTANCE);
//        v.add(new DERSequence(v2));
//        v.add(new DEROctetString(data));
//        ASN1Sequence seq = new DERSequence(v);
//        byte[] privKey = seq.getEncoded("DER");
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privKey);
//        KeyFactory fact = KeyFactory.getInstance("RSA");
//        PrivateKey key = fact.generatePrivate(spec);
//
//        return key;
//
//    }
//
//
//    public static void main(String[] args) throws Exception {
////        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
//////        keyPairGen.initialize(1024);
//////        KeyPair keyPair = keyPairGen.generateKeyPair();
//////        PrivateKey privateKey = keyPair.getPrivate();
//////        PublicKey publicKey = keyPair.getPublic();
//////        System.out.println(Base64.encode(privateKey.getEncoded()));
//////        System.out.println(Base64.encode(publicKey.getEncoded()));
//       convert();
//        byte[] enR = encryptByPublicKey("老王来了。。。".getBytes("utf-8"),PUBLIC_KEY);
//        System.out.println(enR.toString());
//        byte[] result = decryptByPrivateKey(enR);
//        System.out.println(new String(result,"utf-8"));
//
//}
//
//  }
//
//


import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import static javax.crypto.Cipher.PRIVATE_KEY;

public class RSAUtil {
    private static String PRIVATE_KEY = "";
    private static String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMidrbG8YZHBv/7lkd486R gQBWEhMt+byqIdzR/i3pahmisDCvilTmc0kIhsGMowEkL8IMc/aWFald0/gy K+i5dDVqm5O51oAfP/rVKmizSmQF0/C0wT86sR3QWSkur07xldAi3b1kYRpw iYpFRYuFlyVUqdObU9wU8jj3NBdyXwIDAQAB";
    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";

    public static void degetPrivatekey() throws Exception {
                Map<String, String> keyMap = RSAUtil.createKeys();
                String  publicKey = keyMap.get("publicKey");
                String  privateKey = keyMap.get("privateKey");
                System.out.println("公钥: \n\r" + publicKey);
                System.out.println("私钥： \n\r" + privateKey);

                System.out.println("公钥加密——私钥解密");
                String str = "站在大明门前守卫的禁卫军，事先没有接到\n" +
                        "有关的命令，但看到大批盛装的官员来临，也就\n" +
                        "以为确系举行大典，因而未加询问。进大明门即\n" +
                        "为皇城。文武百官看到端门午门之前气氛平静，\n" +
                        "城楼上下也无朝会的迹象，既无几案，站队点名\n" +
                        "的御史和御前侍卫“大汉将军”也不见踪影，不免\n" +
                        "心中揣测，互相询问：所谓午朝是否讹传？";
                System.out.println("\r明文：\r\n" + str);
                System.out.println("\r明文大小：\r\n" + str.getBytes().length);
                //公钥加密
                String encodedData = RSAUtil.publicEncrypt(str, RSAUtil.getPublicKey(publicKey));

                System.out.println("密文：\r\n" + encodedData);
                //私钥解密
                String decodedData = RSAUtil.privateDecrypt(encodedData, RSAUtil.getPrivateKey(privateKey));

                System.out.println("解密后文字: \r\n" + decodedData);

        }


        //-----------------------------

    /**
     * 从文件获取公钥和私钥
     * @throws Exception
     */
        public static void convert() throws Exception {
        byte[] data = null;

        try {
            InputStream is = RSAUtil.class.getResourceAsStream("/enc_pri");
            //将文件内容全部读取
            int length = is.available();
            data = new byte[length];
            //Base64Util.encode(data);
            //返回一个值如果读取到最后一个值返回-1
            is.read(data);
        } catch (Exception e) {
        }

        String dataStr = new String(data);
        try {
            PRIVATE_KEY = dataStr;
        } catch (Exception e) {
        }

        if (PRIVATE_KEY == null) {
            throw new Exception("Fail to retrieve key");
        }
    }



/**
*@ClassName
*@Author Yguang
*@Date 2019/7/23 19:21
*@Description 将公钥和私钥返回到Map
*/
    public static Map<String, String> createKeys() throws Exception {
        //为RSA算法创建一个KeyPairGenerator对象
//        KeyPairGenerator kpg;
//        try{
//            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
//        }catch(NoSuchAlgorithmException e){
//            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
//        }
//
//        //初始化KeyPairGenerator对象,密钥长度
//        kpg.initialize(keySize);                //keySize 可以为1024
//        //生成密匙对
//        KeyPair keyPair = kpg.generateKeyPair();
//        //得到公钥
//        Key publicKey = keyPair.getPublic();
//        String publicKeyStr = Base64Util.encode(publicKey.getEncoded());
//        //得到私钥
//        Key privateKey = keyPair.getPrivate();
//        String privateKeyStr = Base64Util.encode(privateKey.getEncoded());
        convert();
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", PUBLIC_KEY);
        keyPairMap.put("privateKey",PRIVATE_KEY);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过Utilbase64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Util.decode(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过Utilbase64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64Util.encode(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64Util.decode(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64Util.encode(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64Util.decode(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

}
package com.xuanwugate.blockchain.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.util.AddressType;
import com.xuanwugate.util.AddressUtils;
import com.xuanwugate.util.Base58Check;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

public abstract class AddressService extends BaseConstructor {
    public AddressService(EndpointConfig config){
        this.config = config;
    }

    /**
     * 创建新的密钥对
     *
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    protected KeyPair newECKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException  {
        // 注册 BC Provider
        Security.addProvider(new BouncyCastleProvider());
        // 创建椭圆曲线算法的密钥对生成器，算法为 ECDSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
        // 椭圆曲线（EC）域参数设定
        // bitcoin 为什么会选择 secp256k1，详见：https://bitcointalk.org/index.php?topic=151120.0
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取地址
     *@param type AddressType
     * @return
     * @throws Exception
     */
    public String getAddress(AddressType type) throws Exception {
        try {
            KeyPair keyPair = newECKeyPair();
            BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
            BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();
            byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);
            // 1. 获取 ripemdHashedKey
            byte[] ripemdHashedKey = AddressUtils.ripeMD160Hash(publicKeyBytes);
            
            /*
                Type    	                    Version prefix (hex)	Base58 result prefix
                Bitcoin Address	                0x00	                1
                Pay-to-Script-Hash Address  	0x05	                3
                Bitcoin Testnet Address	        0x6F	                m or n
                Private Key WIF	                0x80	                5, K, or L
                BIP-38 Encrypted Private Key	0x0142	                6P
                BIP-32 Extended Public Key	    0x0488B21E	            xpub
             */
            // 2. 添加版本 0x00
            ByteArrayOutputStream addrStream = new ByteArrayOutputStream();
            if(type == AddressType.BitcoinAddress){
                addrStream.write((byte) 0);
            }
            else if(type == AddressType.PayToScriptHashAddress){
                addrStream.write((byte) 5);
            }
            else if(type == AddressType.BitcoinTestnetAddress){
                addrStream.write((byte) 0x6f);
            }
            
            addrStream.write(ripemdHashedKey);    
            
            byte[] versionedPayload = addrStream.toByteArray();

            // 3. 计算校验码
            byte[] checksum = AddressUtils.checksum(versionedPayload);

            // 4. 得到 version + paylod + checksum 的组合
            addrStream.write(checksum);
            byte[] binaryAddress = addrStream.toByteArray();

            // 5. 执行Base58转换处理
            return Base58Check.rawBytesToBase58(binaryAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Fail to get wallet address ! ");
    }

    /**
     * hex转byte数组
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex){
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }
}

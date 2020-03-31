package com.xuanwugate.store;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuanwugate.blockchain.bitcoin.rpcresponse.CreateWalletResult;
import com.xuanwugate.util.MD5Utils;
import com.xuanwugate.util.SerializeUtils;

import org.jboss.logging.Logger;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
/**
 * RocksDBUtils
 */
public class RocksDBUtils {
    private static final Logger log = Logger.getLogger(RocksDBUtils.class);

    /**
     * Wallet bucket key，it contains normal wallet and hd wallet
     */
    private static final String WALLET_BUCKET_KEY = "WalletBucket";
    

    private volatile static RocksDBUtils instance;

    public static RocksDBUtils createAndLoadDB(String dbName) {
        if (instance == null) {
            synchronized (RocksDBUtils.class) {
                if (instance == null) {
                    instance = new RocksDBUtils(dbName);
                }
            }
        }
        return instance;
    }


    /**
     * Get DB
     * Call method getDB before  call method createAndLoadDB
     * @return
     */
    public static RocksDBUtils getDB(){
        return instance;
    }

    private RocksDB db;

    /**
     * Wallet buckets, Including Wallet type String , network String, List<CreateWalletResult> Object
     */
    private Map<String, Map<String,List<CreateWalletResult>>> walletsBucket;

    private RocksDBUtils(String dbName) {
        RocksDB.loadLibrary();
        openDB(dbName);
        initWalletsBucket();
    }

	/**
     * 打开数据库
     */
    private void openDB(String dbName) {
        Options rockopts = new Options().setCreateIfMissing(true);
        try {
            String dbPath = String.format("%s.db", MD5Utils.stringToMD5(dbName));
            db = RocksDB.open(rockopts,dbPath);
        } catch (RocksDBException e) {
            log.error("Fail to open db ! ", e);
            throw new RuntimeException("Fail to open db ! ", e);
        }
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        try {
            db.close();
        } catch (Exception e) {
            log.error("Fail to close db ! ", e);
            throw new RuntimeException("Fail to close db ! ", e);
        }
    }


    private void initWalletsBucket() {
        try {
            byte[] walletsBucketKeyBytes = SerializeUtils.serialize(WALLET_BUCKET_KEY);
            byte[] walletsBucketBytes = db.get(walletsBucketKeyBytes);
            if (walletsBucketBytes != null) {
                walletsBucket = (Map<String,Map<String,List<CreateWalletResult>>>) SerializeUtils.deserialize(walletsBucketBytes);
            } else {
                // walletsBucket =  Maps.newHashMap();
                walletsBucket =  new HashMap<String,Map<String,List<CreateWalletResult>>>();
                db.put(walletsBucketKeyBytes, SerializeUtils.serialize(walletsBucket));
            }
        } catch (RocksDBException e) {
            log.error("Fail to init normal walletbucket ! ", e);
            throw new RuntimeException("Fail to init normal bucket ! ", e);
        }
    }

    public void pushWallet(WalletType walletType,String networks,CreateWalletResult wallet){
        try {
            Map<String,List<CreateWalletResult>> map = walletsBucket.get(walletType.name());
            if(map == null){
                map = new HashMap<String,List<CreateWalletResult>>();
                walletsBucket.put(walletType.name(),map);
            }

            List<CreateWalletResult> walletList = map.get(networks);
            if(walletList == null){
                walletList = new ArrayList<CreateWalletResult>();
                map.put(networks, walletList);
            }

            walletList.add(wallet);
            db.put(SerializeUtils.serialize(WALLET_BUCKET_KEY), SerializeUtils.serialize(walletsBucket));
        } catch (RocksDBException e) {
            log.error("Fail to put normal wallet!", e);
            throw new RuntimeException("Fail to put normal wallet!", e);
        }
    }

    public List<String> getWalletList(WalletType walletType,String networks){
        List<String> res = new ArrayList<String>();

        Map<String,List<CreateWalletResult>> map = walletsBucket.get(walletType.name());
        if(map != null){
            List<CreateWalletResult> walletList = map.get(networks);
            if(walletList != null){
                for(CreateWalletResult item : walletList){
                    res.add(item.getName());
                }
            }
        }

        return res;
    }    
}
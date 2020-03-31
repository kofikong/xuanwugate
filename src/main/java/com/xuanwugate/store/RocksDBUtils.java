package com.xuanwugate.store;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuanwugate.blockchain.bitcoin.rpcresponse.CreateWalletResult;
import com.xuanwugate.util.MD5Utils;
import com.xuanwugate.util.SerializeUtils;

import java.security.MessageDigest;
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

    // /**
    //  * 初始化 blocks 数据桶
    //  */
    // private void initBlockBucket() {
    //     try {
    //         byte[] blockBucketKey = SerializeUtils.serialize(BLOCKS_BUCKET_KEY);
    //         byte[] blockBucketBytes = db.get(blockBucketKey);
    //         if (blockBucketBytes != null) {
    //             blocksBucket = (Map) SerializeUtils.deserialize(blockBucketBytes);
    //         } else {
    //             blocksBucket = Maps.newHashMap();
    //             db.put(blockBucketKey, SerializeUtils.serialize(blocksBucket));
    //         }
    //     } catch (RocksDBException e) {
    //         log.error("Fail to init block bucket ! ", e);
    //         throw new RuntimeException("Fail to init block bucket ! ", e);
    //     }
    // }

    // /**
    //  * 初始化 blocks 数据桶
    //  */
    // private void initChainStateBucket() {
    //     try {
    //         byte[] chainstateBucketKey = SerializeUtils.serialize(CHAINSTATE_BUCKET_KEY);
    //         byte[] chainstateBucketBytes = db.get(chainstateBucketKey);
    //         if (chainstateBucketBytes != null) {
    //             chainstateBucket = (Map) SerializeUtils.deserialize(chainstateBucketBytes);
    //         } else {
    //             chainstateBucket = Maps.newHashMap();
    //             db.put(chainstateBucketKey, SerializeUtils.serialize(chainstateBucket));
    //         }
    //     } catch (RocksDBException e) {
    //         log.error("Fail to init chainstate bucket ! ", e);
    //         throw new RuntimeException("Fail to init chainstate bucket ! ", e);
    //     }
    // }

    // /**
    //  * 保存最新一个区块的Hash值
    //  *
    //  * @param tipBlockHash
    //  */
    // public void putLastBlockHash(String tipBlockHash) {
    //     try {
    //         blocksBucket.put(LAST_BLOCK_KEY, SerializeUtils.serialize(tipBlockHash));
    //         db.put(SerializeUtils.serialize(BLOCKS_BUCKET_KEY), SerializeUtils.serialize(blocksBucket));
    //     } catch (RocksDBException e) {
    //         log.error("Fail to put last block hash ! tipBlockHash=" + tipBlockHash, e);
    //         throw new RuntimeException("Fail to put last block hash ! tipBlockHash=" + tipBlockHash, e);
    //     }
    // }

    // /**
    //  * 查询最新一个区块的Hash值
    //  *
    //  * @return
    //  */
    // public String getLastBlockHash() {
    //     byte[] lastBlockHashBytes = blocksBucket.get(LAST_BLOCK_KEY);
    //     if (lastBlockHashBytes != null) {
    //         return (String) SerializeUtils.deserialize(lastBlockHashBytes);
    //     }
    //     return "";
    // }

    // /**
    //  * 保存区块
    //  *
    //  * @param block
    //  */
    // public void putBlock(Block block) {
    //     try {
    //         blocksBucket.put(block.getHash(), SerializeUtils.serialize(block));
    //         db.put(SerializeUtils.serialize(BLOCKS_BUCKET_KEY), SerializeUtils.serialize(blocksBucket));
    //     } catch (RocksDBException e) {
    //         log.error("Fail to put block ! block=" + block.toString(), e);
    //         throw new RuntimeException("Fail to put block ! block=" + block.toString(), e);
    //     }
    // }

    // /**
    //  * 查询区块
    //  *
    //  * @param blockHash
    //  * @return
    //  */
    // public Block getBlock(String blockHash) {
    //     byte[] blockBytes = blocksBucket.get(blockHash);
    //     if (blockBytes != null) {
    //         return (Block) SerializeUtils.deserialize(blockBytes);
    //     }
    //     throw new RuntimeException("Fail to get block ! blockHash=" + blockHash);
    // }


    // /**
    //  * 清空chainstate bucket
    //  */
    // public void cleanChainStateBucket() {
    //     try {
    //         chainstateBucket.clear();
    //     } catch (Exception e) {
    //         log.error("Fail to clear chainstate bucket ! ", e);
    //         throw new RuntimeException("Fail to clear chainstate bucket ! ", e);
    //     }
    // }

    // /**
    //  * 保存UTXO数据
    //  *
    //  * @param key   交易ID
    //  * @param utxos UTXOs
    //  */
    // public void putUTXOs(String key, TXOutput[] utxos) {
    //     try {
    //         chainstateBucket.put(key, SerializeUtils.serialize(utxos));
    //         db.put(SerializeUtils.serialize(CHAINSTATE_BUCKET_KEY), SerializeUtils.serialize(chainstateBucket));
    //     } catch (Exception e) {
    //         log.error("Fail to put UTXOs into chainstate bucket ! key=" + key, e);
    //         throw new RuntimeException("Fail to put UTXOs into chainstate bucket ! key=" + key, e);
    //     }
    // }


    // /**
    //  * 查询UTXO数据
    //  *
    //  * @param key 交易ID
    //  */
    // public TXOutput[] getUTXOs(String key) {
    //     byte[] utxosByte = chainstateBucket.get(key);
    //     if (utxosByte != null) {
    //         return (TXOutput[]) SerializeUtils.deserialize(utxosByte);
    //     }
    //     return null;
    // }


    // /**
    //  * 删除 UTXO 数据
    //  *
    //  * @param key 交易ID
    //  */
    // public void deleteUTXOs(String key) {
    //     try {
    //         chainstateBucket.remove(key);
    //         db.put(SerializeUtils.serialize(CHAINSTATE_BUCKET_KEY), SerializeUtils.serialize(chainstateBucket));
    //     } catch (Exception e) {
    //         log.error("Fail to delete UTXOs by key ! key=" + key, e);
    //         throw new RuntimeException("Fail to delete UTXOs by key ! key=" + key, e);
    //     }
    // }

    
}
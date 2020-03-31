package com.xuanwugate.blockchain.bitcoin;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import com.xuanwugate.blockchain.bitcoin.response.GetTransactionDetailsResponse;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinTransactionService;
import com.xuanwugate.client.XuanwuGate;
import com.xuanwugate.response.Response;

@Path("/{version}/bc/btc/{network}/txs")
public class TransactionsResource {
    private static final Logger log = Logger.getLogger(WalletResource.class);

    @Inject
    JsonWebToken jwt;

    @javax.ws.rs.PathParam("version")
    private String version;

    @javax.ws.rs.PathParam("network")
    private String network;


    

    /**
     * Get Basic Transaction Details By Transaction ID (txid)
     * @param ctx 
     * @param txid 
     * @return 
     * example: 
     * {"payload": {"amount": 0.00952623,
        "fee": 0.00001648,
        "unit": "btc",
        "datetime": "2020-03-04 11:25:57 UTC",
        "timestamp": 1583321157,
        "confirmations": 288,
        "sent": {
            "2N2QvYKC9EShfRvo1YP9A7cZhRSNcQTxWfH": 0.00954271
        },
        "received": {
            "2N7kT5pwbxUbUiY5Lvz9WowYrXkzeFNchQL": 0.0001365,
            "2N2QvYKC9EShfRvo1YP9A7cZhRSNcQTxWfH": 0.00938973
        }}
       }
     */
    @GET
    @Path("basic/txid/{txid}")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBasicTransactionDetails(@Context SecurityContext ctx,@PathParam("txid") String txid) {
        // XuanwuGate gate = new XuanwuGate(version);
        // Bitcoin btc = gate.connectToBtc(network);
        // BitcoinTransactionService service = btc.getTransactionService();
        // GetBasicTransactionDetailsResponse obj = service.getBasicTransactionDetails(txid);
        // return Response.build(obj);
        return null;
    }

    /**
     * Get Transaction Details By Transaction ID (Txid)
     * @apiNote Bitcoin-qt must be use -txindex option.
     * @param ctx 
     * @param walletName
     * @param txid 
     * @return 
     * example: 
     * {
        "payload": {
            "txid": "5a4ebf66822b0b2d56bd9dc64ece0bc38ee7844a23ff1d7320a88c5fdb2ad3e2",
            "hash": "5a4ebf66822b0b2d56bd9dc64ece0bc38ee7844a23ff1d7320a88c5fdb2ad3e2",
            "index": 1,
            "version": 1,
            "size": 158,
            "vsize": 158,
            "locktime": 0,
            "time": "2010-09-16 05:03:47 UTC",
            "blockhash": "000000000043a8c0fd1d6f726790caa2a406010d19efd2780db27bdbbd93baf6",
            "blockheight": 80000,
            "blocktime": "2010-09-16 05:03:47 UTC",
            "timestamp": 1284613427,
            "confirmations": 484349,
            "txins": [
                {
                    "txout": "f5d8ee39a430901c91a5917b9f2dc19d6d1a0e9cea205b009ca73dd04470b9a6",
                    "vout": 0,
                    "amount": "50.00000000",
                    "addresses": [
                        "1JBSCVF6VM6QjFZyTnbpLjoCJTQEqVbepG"
                    ],
                    "script": {
                        "asm": "304502206e21798a42fae0e854281abd38bacd1aeed3ee3738d9e1446618c4571d1090db022100e2ac980643b0b82c0e88ffdfec6b64e3e6ba35e7ba5fdd7d5d6cc8d25c6b2415[ALL]",
                        "hex": "48304502206e21798a42fae0e854281abd38bacd1aeed3ee3738d9e1446618c4571d1090db022100e2ac980643b0b82c0e88ffdfec6b64e3e6ba35e7ba5fdd7d5d6cc8d25c6b241501"
                    },
                    "votype": "pubkey"
                }
            ],
            "txouts": [
                {
                    "amount": "50.00000000",
                    "type": "pubkeyhash",
                    "spent": true,
                    "addresses": [
                        "16ro3Jptwo4asSevZnsRX6vfRS24TGE6uK"
                    ],
                    "script": {
                        "asm": "OP_DUP OP_HASH160 404371705fa9bd789a2fcd52d2c580b65d35549d OP_EQUALVERIFY OP_CHECKSIG",
                        "hex": "76a914404371705fa9bd789a2fcd52d2c580b65d35549d88ac",
                        "reqsigs": 1
                    }
                }
            ]
        }
        }
     */
    @GET
    @Path("txid/{txid}")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionDetailsByTxid(@Context SecurityContext ctx, @PathParam("txid") String txid) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinTransactionService service = btc.getTransactionService();
        GetTransactionDetailsResponse obj = service.getTransactionDetailsByTxid(txid);
        return Response.build(obj);
    }
}
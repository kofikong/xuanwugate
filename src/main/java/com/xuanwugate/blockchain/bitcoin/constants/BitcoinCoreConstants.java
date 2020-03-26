package com.xuanwugate.blockchain.bitcoin.constants;

/**
 * BitcoinCoreConstants
 */
public interface BitcoinCoreConstants {
    /**
     * curl --user user --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblockchaininfo", "params": [] }' -H 'content-type: text/plain;' https://btccore-test.bdnodes.net?auth=4-k3zyRSDj2zEV1NO3m4BAOXYp1c4WdxqNWrJcuhxlk
     */
    String GET_NODE_INFORMATION = "getblockchaininfo";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblock", "params": ["00000000c937983704a73af28acdec37b049d214adbda81d7e2a3dd146f6ed09"] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String GET_BLOCK = "getblock";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblockhash", "params": [1000] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String GET_BLOCK_HASH = "getblockhash";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblockcount", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String GET_BLOCK_COUNT = "getblockcount";


    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getnewaddress", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String GET_NEW_ADDRESS = "getnewaddress";

     /**   
     * --user myusername --data-binary '{"method":"dumpprivkey","params":["2MtxGty81MZvSB5qEvhPpvUfypXU9NGsQFq"],"id":"koftest3","jsonrpc":"2.0"}' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String DUMP_PRIV_KEY = "dumpprivkey";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getaddressinfo", "params": ["1PSSGeFHDnKNxiEyFrD1wcEaHr9hrQDDWc"] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String GET_ADDRESS_INFO = "getaddressinfo";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "createwallet", "params": ["testwallet"] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String CREATE_WALLET = "createwallet";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "addmultisigaddress", "params": [2, "[\"16sSauSf5pF2UkUwvKGq4qjNRzBZYqgEL5\",\"171sgjn4YtPu27adkKGrdDwzRTxnRkBfKV\"]"] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/
     */
    String ADD_MULTISIG_ADDRESS = "addmultisigaddress";

    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getwalletinfo", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/wallet/walletName
     */
    String GET_WALLET_INFO = "getwalletinfo";
    
    /**
     * curl --user myusername --data-binary '{"method":"importprivkey","params":["cWALDVKUUR9eZCC3qk1MjQBuAq9MKQppEw7w1DCdqoSkr57SEYEW","testnetDVD",false],"id":"koftest3","jsonrpc":"2.0"}' -H 'content-type: text/plain;' http://127.0.0.1:8332/wallet/walletName
     */
    String IMPORT_PRIVKEY = "importprivkey";
    
    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "listlabels", "params": [receive] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/wallet/walletName
     */
    String LIST_LABELS = "listlabels";
    
    /**
     * curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getaddressesbylabel", "params": ["tabby"] }' -H 'content-type: text/plain;' http://127.0.0.1:8332/wallet/walletName
     */
    String GET_ADDRESSES_BY_LABEL = "getaddressesbylabel";



    /**
￼
help

== Blockchain ==
getbestblockhash
getblock "blockhash" ( verbosity )
getblockchaininfo
getblockcount
getblockfilter "blockhash" ( "filtertype" )
getblockhash height
getblockheader "blockhash" ( verbose )
getblockstats hash_or_height ( stats )
getchaintips
getchaintxstats ( nblocks "blockhash" )
getdifficulty
getmempoolancestors "txid" ( verbose )
getmempooldescendants "txid" ( verbose )
getmempoolentry "txid"
getmempoolinfo
getrawmempool ( verbose )
gettxout "txid" n ( include_mempool )
gettxoutproof ["txid",...] ( "blockhash" )
gettxoutsetinfo
preciousblock "blockhash"
pruneblockchain height
savemempool
scantxoutset "action" ( [scanobjects,...] )
verifychain ( checklevel nblocks )
verifytxoutproof "proof"

== Control ==
getmemoryinfo ( "mode" )
getrpcinfo
help ( "command" )
logging ( ["include_category",...] ["exclude_category",...] )
stop
uptime

== Generating ==
generatetoaddress nblocks "address" ( maxtries )

== Mining ==
getblocktemplate ( "template_request" )
getmininginfo
getnetworkhashps ( nblocks height )
prioritisetransaction "txid" ( dummy ) fee_delta
submitblock "hexdata" ( "dummy" )
submitheader "hexdata"

== Network ==
addnode "node" "command"
clearbanned
disconnectnode ( "address" nodeid )
getaddednodeinfo ( "node" )
getconnectioncount
getnettotals
getnetworkinfo
getnodeaddresses ( count )
getpeerinfo
listbanned
ping
setban "subnet" "command" ( bantime absolute )
setnetworkactive state

== Rawtransactions ==
analyzepsbt "psbt"
combinepsbt ["psbt",...]
combinerawtransaction ["hexstring",...]
converttopsbt "hexstring" ( permitsigdata iswitness )
createpsbt [{"txid":"hex","vout":n,"sequence":n},...] [{"address":amount},{"data":"hex"},...] ( locktime replaceable )
createrawtransaction [{"txid":"hex","vout":n,"sequence":n},...] [{"address":amount},{"data":"hex"},...] ( locktime replaceable )
decodepsbt "psbt"
decoderawtransaction "hexstring" ( iswitness )
decodescript "hexstring"
finalizepsbt "psbt" ( extract )
fundrawtransaction "hexstring" ( options iswitness )
getrawtransaction "txid" ( verbose "blockhash" )
joinpsbts ["psbt",...]
sendrawtransaction "hexstring" ( maxfeerate )
signrawtransactionwithkey "hexstring" ["privatekey",...] ( [{"txid":"hex","vout":n,"scriptPubKey":"hex","redeemScript":"hex","witnessScript":"hex","amount":amount},...] "sighashtype" )
testmempoolaccept ["rawtx",...] ( maxfeerate )
utxoupdatepsbt "psbt" ( ["",{"desc":"str","range":n or [n,n]},...] )

== Util ==
createmultisig nrequired ["key",...] ( "address_type" )
deriveaddresses "descriptor" ( range )
estimatesmartfee conf_target ( "estimate_mode" )
getdescriptorinfo "descriptor"
signmessagewithprivkey "privkey" "message"
validateaddress "address"
verifymessage "address" "signature" "message"

== Wallet ==
abandontransaction "txid"
abortrescan
addmultisigaddress nrequired ["key",...] ( "label" "address_type" )
backupwallet "destination"
bumpfee "txid" ( options )
createwallet "wallet_name" ( disable_private_keys blank "passphrase" avoid_reuse )
dumpprivkey "address"
dumpwallet "filename"
encryptwallet "passphrase"
getaddressesbylabel "label"
getaddressinfo "address"
getbalance ( "dummy" minconf include_watchonly avoid_reuse )
getbalances
getnewaddress ( "label" "address_type" )
getrawchangeaddress ( "address_type" )
getreceivedbyaddress "address" ( minconf )
getreceivedbylabel "label" ( minconf )
gettransaction "txid" ( include_watchonly verbose )
getunconfirmedbalance
getwalletinfo
importaddress "address" ( "label" rescan p2sh )
importmulti "requests" ( "options" )
importprivkey "privkey" ( "label" rescan )
importprunedfunds "rawtransaction" "txoutproof"
importpubkey "pubkey" ( "label" rescan )
importwallet "filename"
keypoolrefill ( newsize )
listaddressgroupings
listlabels ( "purpose" )
listlockunspent
listreceivedbyaddress ( minconf include_empty include_watchonly "address_filter" )
listreceivedbylabel ( minconf include_empty include_watchonly )
listsinceblock ( "blockhash" target_confirmations include_watchonly include_removed )
listtransactions ( "label" count skip include_watchonly )
listunspent ( minconf maxconf ["address",...] include_unsafe query_options )
listwalletdir
listwallets
loadwallet "filename"
lockunspent unlock ( [{"txid":"hex","vout":n},...] )
removeprunedfunds "txid"
rescanblockchain ( start_height stop_height )
sendmany "" {"address":amount} ( minconf "comment" ["address",...] replaceable conf_target "estimate_mode" )
sendtoaddress "address" amount ( "comment" "comment_to" subtractfeefromamount replaceable conf_target "estimate_mode" avoid_reuse )
sethdseed ( newkeypool "seed" )
setlabel "address" "label"
settxfee amount
setwalletflag "flag" ( value )
signmessage "address" "message"
signrawtransactionwithwallet "hexstring" ( [{"txid":"hex","vout":n,"scriptPubKey":"hex","redeemScript":"hex","witnessScript":"hex","amount":amount},...] "sighashtype" )
unloadwallet ( "wallet_name" )
walletcreatefundedpsbt [{"txid":"hex","vout":n,"sequence":n},...] [{"address":amount},{"data":"hex"},...] ( locktime options bip32derivs )
walletlock
walletpassphrase "passphrase" timeout
walletpassphrasechange "oldpassphrase" "newpassphrase"
walletprocesspsbt "psbt" ( sign "sighashtype" bip32derivs )

== Zmq ==
getzmqnotifications
     */

     /**
      Bitcoin Core 版本 v0.19.1 (64 位)
Usage:  bitcoin-qt [command-line options]                     

Options:


-?
Print this help message and exit 
-alertnotify=<cmd>
Execute command when a relevant alert is received or we see a really long fork (%s in cmd is replaced by message) 
-assumevalid=<hex>
If this block is in the chain assume that it and its ancestors are valid and potentially skip their script verification (0 to verify all, default: 00000000000000000005f8920febd3925f8272a6a71237563d78c2edfdd09ddf, testnet: 00000000000000b7ab6ce61eb6d571003fbe5fe892da4c9b740c49a07542462d) 
-blockfilterindex=<type>
Maintain an index of compact filters by block (default: 0, values: basic). If <type> is not supplied or if <type> = 1, indexes for all known types are enabled. 
-blocknotify=<cmd>
Execute command when the best block changes (%s in cmd is replaced by block hash) 
-blockreconstructionextratxn=<n>
Extra transactions to keep in memory for compact block reconstructions (default: 100) 
-blocksdir=<dir>
Specify directory to hold blocks subdirectory for *.dat files (default: <datadir>) 
-blocksonly
Whether to reject transactions from network peers. Transactions from the wallet, RPC and relay whitelisted inbound peers are not affected. (default: 0) 
-conf=<file>
Specify configuration file. Relative paths will be prefixed by datadir location. (default: bitcoin.conf) 
-daemon
Run in the background as a daemon and accept commands 
-datadir=<dir>
Specify data directory 
-dbcache=<n>
Maximum database cache size <n> MiB (4 to 16384, default: 450). In addition, unused mempool memory is shared for this cache (see -maxmempool). 
-debuglogfile=<file>
Specify location of debug log file. Relative paths will be prefixed by a net-specific datadir location. (-nodebuglogfile to disable; default: debug.log) 
-includeconf=<file>
Specify additional configuration file, relative to the -datadir path (only useable from configuration file, not command line) 
-loadblock=<file>
Imports blocks from external blk000??.dat file on startup 
-maxmempool=<n>
Keep the transaction memory pool below <n> megabytes (default: 300) 
-maxorphantx=<n>
Keep at most <n> unconnectable transactions in memory (default: 100) 
-mempoolexpiry=<n>
Do not keep transactions in the mempool longer than <n> hours (default: 336) 
-par=<n>
Set the number of script verification threads (-8 to 16, 0 = auto, <0 = leave that many cores free, default: 0) 
-persistmempool
Whether to save the mempool on shutdown and load on restart (default: 1) 
-pid=<file>
Specify pid file. Relative paths will be prefixed by a net-specific datadir location. (default: bitcoind.pid) 
-prune=<n>
Reduce storage requirements by enabling pruning (deleting) of old blocks. This allows the pruneblockchain RPC to be called to delete specific blocks, and enables automatic pruning of old blocks if a target size in MiB is provided. This mode is incompatible with -txindex and -rescan. Warning: Reverting this setting requires re-downloading the entire blockchain. (default: 0 = disable pruning blocks, 1 = allow manual pruning via RPC, >=550 = automatically prune block files to stay under the specified target size in MiB) 
-reindex
Rebuild chain state and block index from the blk*.dat files on disk 
-reindex-chainstate
Rebuild chain state from the currently indexed blocks. When in pruning mode or if blocks on disk might be corrupted, use full -reindex instead. 
-sysperms
Create new files with system default permissions, instead of umask 077 (only effective with disabled wallet functionality) 
-txindex
Maintain a full transaction index, used by the getrawtransaction rpc call (default: 0) 
-version
Print version and exit 


Connection options:


-addnode=<ip>
Add a node to connect to and attempt to keep the connection open (see the `addnode` RPC command help for more info). This option can be specified multiple times to add multiple nodes. 
-banscore=<n>
Threshold for disconnecting misbehaving peers (default: 100) 
-bantime=<n>
Number of seconds to keep misbehaving peers from reconnecting (default: 86400) 
-bind=<addr>
Bind to given address and always listen on it. Use [host]:port notation for IPv6 
-connect=<ip>
Connect only to the specified node; -noconnect disables automatic connections (the rules for this peer are the same as for -addnode). This option can be specified multiple times to connect to multiple nodes. 
-discover
Discover own IP addresses (default: 1 when listening and no -externalip or -proxy) 
-dns
Allow DNS lookups for -addnode, -seednode and -connect (default: 1) 
-dnsseed
Query for peer addresses via DNS lookup, if low on addresses (default: 1 unless -connect used) 
-enablebip61
Send reject messages per BIP61 (default: 0) 
-externalip=<ip>
Specify your own public address 
-forcednsseed
Always query for peer addresses via DNS lookup (default: 0) 
-listen
Accept connections from outside (default: 1 if no -proxy or -connect) 
-listenonion
Automatically create Tor hidden service (default: 1) 
-maxconnections=<n>
Maintain at most <n> connections to peers (default: 125) 
-maxreceivebuffer=<n>
Maximum per-connection receive buffer, <n>*1000 bytes (default: 5000) 
-maxsendbuffer=<n>
Maximum per-connection send buffer, <n>*1000 bytes (default: 1000) 
-maxtimeadjustment
Maximum allowed median peer time offset adjustment. Local perspective of time may be influenced by peers forward or backward by this amount. (default: 4200 seconds) 
-maxuploadtarget=<n>
Tries to keep outbound traffic under the given target (in MiB per 24h), 0 = no limit (default: 0) 
-onion=<ip:port>
Use separate SOCKS5 proxy to reach peers via Tor hidden services, set -noonion to disable (default: -proxy) 
-onlynet=<net>
Make outgoing connections only through network <net> (ipv4, ipv6 or onion). Incoming connections are not affected by this option. This option can be specified multiple times to allow multiple networks. 
-peerbloomfilters
Support filtering of blocks and transaction with bloom filters (default: 0) 
-permitbaremultisig
Relay non-P2SH multisig (default: 1) 
-port=<port>
Listen for connections on <port> (default: 8333, testnet: 18333, regtest: 18444) 
-proxy=<ip:port>
Connect through SOCKS5 proxy, set -noproxy to disable (default: disabled) 
-proxyrandomize
Randomize credentials for every proxy connection. This enables Tor stream isolation (default: 1) 
-seednode=<ip>
Connect to a node to retrieve peer addresses, and disconnect. This option can be specified multiple times to connect to multiple nodes. 
-timeout=<n>
Specify connection timeout in milliseconds (minimum: 1, default: 5000) 
-torcontrol=<ip>:<port>
Tor control port to use if onion listening enabled (default: 127.0.0.1:9051) 
-torpassword=<pass>
Tor control port password (default: empty) 
-upnp
Use UPnP to map the listening port (default: 0) 
-whitebind=<[permissions@]addr>
Bind to given address and whitelist peers connecting to it. Use [host]:port notation for IPv6. Allowed permissions are bloomfilter (allow requesting BIP37 filtered blocks and transactions), noban (do not ban for misbehavior), forcerelay (relay even non-standard transactions), relay (relay even in -blocksonly mode), and mempool (allow requesting BIP35 mempool contents). Specify multiple permissions separated by commas (default: noban,mempool,relay). Can be specified multiple times. 
-whitelist=<[permissions@]IP address or network>
Whitelist peers connecting from the given IP address (e.g. 1.2.3.4) or CIDR notated network(e.g. 1.2.3.0/24). Uses same permissions as -whitebind. Can be specified multiple times. 


Wallet options:


-addresstype
What type of addresses to use ("legacy", "p2sh-segwit", or "bech32", default: "p2sh-segwit") 
-avoidpartialspends
Group outputs by address, selecting all or none, instead of selecting on a per-output basis. Privacy is improved as an address is only used once (unless someone sends to it after spending from it), but may result in slightly higher fees as suboptimal coin selection may result due to the added limitation (default: 0 (always enabled for wallets with "avoid_reuse" enabled)) 
-changetype
What type of change to use ("legacy", "p2sh-segwit", or "bech32"). Default is same as -addresstype, except when -addresstype=p2sh-segwit a native segwit output is used when sending to a native segwit address) 
-disablewallet
Do not load the wallet and disable wallet RPC calls 
-discardfee=<amt>
The fee rate (in BTC/kB) that indicates your tolerance for discarding change by adding it to the fee (default: 0.0001). Note: An output is discarded if it is dust at this rate, but we will always discard up to the dust relay fee and a discard fee above that is limited by the fee estimate for the longest target 
-fallbackfee=<amt>
A fee rate (in BTC/kB) that will be used when fee estimation has insufficient data (default: 0.0002) 
-keypool=<n>
Set key pool size to <n> (default: 1000) 
-mintxfee=<amt>
Fees (in BTC/kB) smaller than this are considered zero fee for transaction creation (default: 0.00001) 
-paytxfee=<amt>
Fee (in BTC/kB) to add to transactions you send (default: 0.00) 
-rescan
Rescan the block chain for missing wallet transactions on startup 
-salvagewallet
Attempt to recover private keys from a corrupt wallet on startup 
-spendzeroconfchange
Spend unconfirmed change when sending transactions (default: 1) 
-txconfirmtarget=<n>
If paytxfee is not set, include enough fee so transactions begin confirmation on average within n blocks (default: 6) 
-upgradewallet
Upgrade wallet to latest format on startup 
-wallet=<path>
Specify wallet database path. Can be specified multiple times to load multiple wallets. Path is interpreted relative to <walletdir> if it is not absolute, and will be created if it does not exist (as a directory containing a wallet.dat file and log files). For backwards compatibility this will also accept names of existing data files in <walletdir>.) 
-walletbroadcast
Make the wallet broadcast transactions (default: 1) 
-walletdir=<dir>
Specify directory to hold wallets (default: <datadir>/wallets if it exists, otherwise <datadir>) 
-walletnotify=<cmd>
Execute command when a wallet transaction changes (%s in cmd is replaced by TxID) 
-walletrbf
Send transactions with full-RBF opt-in enabled (RPC only, default: 0) 
-zapwallettxes=<mode>
Delete all wallet transactions and only recover those parts of the blockchain through -rescan on startup (1 = keep tx meta data e.g. payment request information, 2 = drop tx meta data) 


ZeroMQ notification options:


-zmqpubhashblock=<address>
Enable publish hash block in <address> 
-zmqpubhashblockhwm=<n>
Set publish hash block outbound message high water mark (default: 1000) 
-zmqpubhashtx=<address>
Enable publish hash transaction in <address> 
-zmqpubhashtxhwm=<n>
Set publish hash transaction outbound message high water mark (default: 1000) 
-zmqpubrawblock=<address>
Enable publish raw block in <address> 
-zmqpubrawblockhwm=<n>
Set publish raw block outbound message high water mark (default: 1000) 
-zmqpubrawtx=<address>
Enable publish raw transaction in <address> 
-zmqpubrawtxhwm=<n>
Set publish raw transaction outbound message high water mark (default: 1000) 


Debugging/Testing options:


-debug=<category>
Output debugging information (default: -nodebug, supplying <category> is optional). If <category> is not supplied or if <category> = 1, output all debugging information. <category> can be: net, tor, mempool, http, bench, zmq, db, rpc, estimatefee, addrman, selectcoins, reindex, cmpctblock, rand, prune, proxy, mempoolrej, libevent, coindb, qt, leveldb. 
-debugexclude=<category>
Exclude debugging information for a category. Can be used in conjunction with -debug=1 to output debug logs for all categories except one or more specified categories. 
-help-debug
Print help message with debugging options and exit 
-logips
Include IP addresses in debug output (default: 0) 
-logthreadnames
Prepend debug output with name of the originating thread (only available on platforms supporting thread_local) (default: 0) 
-logtimestamps
Prepend debug output with timestamp (default: 1) 
-maxtxfee=<amt>
Maximum total fees (in BTC) to use in a single wallet transaction; setting this too low may abort large transactions (default: 0.10) 
-printtoconsole
Send trace/debug info to console (default: 1 when no -daemon. To disable logging to file, set -nodebuglogfile) 
-shrinkdebugfile
Shrink debug.log file on client startup (default: 1 when no -debug) 
-uacomment=<cmt>
Append comment to the user agent string 


Chain selection options:


-chain=<chain>
Use the chain <chain> (default: main). Allowed values: main, test, regtest 
-testnet
Use the test chain. Equivalent to -chain=test. 


Node relay options:


-bytespersigop
Equivalent bytes per sigop in transactions for relay and mining (default: 20) 
-datacarrier
Relay and mine data carrier transactions (default: 1) 
-datacarriersize
Maximum size of data in data carrier transactions we relay and mine (default: 83) 
-minrelaytxfee=<amt>
Fees (in BTC/kB) smaller than this are considered zero fee for relaying, mining and transaction creation (default: 0.00001) 
-whitelistforcerelay
Add 'forcerelay' permission to whitelisted inbound peers with default permissions. This will relay transactions even if the transactions were already in the mempool or violate local relay policy. (default: 0) 
-whitelistrelay
Add 'relay' permission to whitelisted inbound peers with default permissions. The will accept relayed transactions even when not relaying transactions (default: 1) 


Block creation options:


-blockmaxweight=<n>
Set maximum BIP141 block weight (default: 3996000) 
-blockmintxfee=<amt>
Set lowest fee rate (in BTC/kB) for transactions to be included in block creation. (default: 0.00001) 


RPC server options:


-rest
Accept public REST requests (default: 0) 
-rpcallowip=<ip>
Allow JSON-RPC connections from specified source. Valid for <ip> are a single IP (e.g. 1.2.3.4), a network/netmask (e.g. 1.2.3.4/255.255.255.0) or a network/CIDR (e.g. 1.2.3.4/24). This option can be specified multiple times 
-rpcauth=<userpw>
Username and HMAC-SHA-256 hashed password for JSON-RPC connections. The field <userpw> comes in the format: <USERNAME>:<SALT>$<HASH>. A canonical python script is included in share/rpcauth. The client then connects normally using the rpcuser=<USERNAME>/rpcpassword=<PASSWORD> pair of arguments. This option can be specified multiple times 
-rpcbind=<addr>[:port]
Bind to given address to listen for JSON-RPC connections. Do not expose the RPC server to untrusted networks such as the public internet! This option is ignored unless -rpcallowip is also passed. Port is optional and overrides -rpcport. Use [host]:port notation for IPv6. This option can be specified multiple times (default: 127.0.0.1 and ::1 i.e., localhost) 
-rpccookiefile=<loc>
Location of the auth cookie. Relative paths will be prefixed by a net-specific datadir location. (default: data dir) 
-rpcpassword=<pw>
Password for JSON-RPC connections 
-rpcport=<port>
Listen for JSON-RPC connections on <port> (default: 8332, testnet: 18332, regtest: 18443) 
-rpcserialversion
Sets the serialization of raw transaction or block hex returned in non-verbose mode, non-segwit(0) or segwit(1) (default: 1) 
-rpcthreads=<n>
Set the number of threads to service RPC calls (default: 4) 
-rpcuser=<user>
Username for JSON-RPC connections 
-server
Accept command line and JSON-RPC commands 


UI Options:


-choosedatadir
Choose data directory on startup (default: 0) 
-lang=<lang>
Set language, for example "de_DE" (default: system locale) 
-min
Start minimized 
-resetguisettings
Reset all settings changed in the GUI 
-rootcertificates=<file>
Set SSL root certificates for payment request (default: -system-) 
-splash
Show splash screen on startup (default: 1) 

      */
}
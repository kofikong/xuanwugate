package com.xuanwugate.rpc.shell;
/**
* ShellProxy
*/
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.rpc.IProxy;
import com.xuanwugate.rpc.IRequestParams;

import org.jboss.logging.Logger;

public class ShellProxy implements IProxy {

    private static final Logger log = Logger.getLogger(ShellProxy.class);

    static ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    
    @Override
    public String run(IRequestParams params) throws IOException{
        List<String> strList = new ArrayList<String>();
        try {
            String methodStr = params.getJSONRPC().getMethod();
            String paramsStr = String.join(",", params.getJSONRPC().getParams().toArray(new String[params.getJSONRPC().getParams().size()])) ;
            Process process = Runtime.getRuntime().exec(new String[]{"/usr/local/bin/bitcoin-cli",methodStr,paramsStr},null,null);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            process.waitFor();
            while ((line = input.readLine()) != null){
                strList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList.toString();
        
        // Process process = null;
        // InputStream pIn = null;
        // InputStream pErr = null;
        // StreamPool outputGobbler = null;
        // StreamPool errorGobbler = null;
        // Future<Integer> executeFuture = null;
        // try {
        //     String methodStr = params.getJSONRPC().getMethod();
        //     String paramsStr = String.join(",", params.getJSONRPC().getParams().toArray(new String[params.getJSONRPC().getParams().size()])) ;
        //     String cmd = String.format("bitcoin-cli %s%s", methodStr,paramsStr);
        //     process = Runtime.getRuntime().exec(cmd);
        //     final Process p = process;
        //     p.getOutputStream().close();

        //     pIn = process.getInputStream();
        //     outputGobbler = new StreamPool(pIn, "OUTPUT");
        //     outputGobbler.start();

        //     pErr = process.getErrorStream();
        //     errorGobbler = new StreamPool(pErr, "ERROR");
        //     errorGobbler.start();

        //     Callable<Integer> call = new Callable<Integer>() {
        //         public Integer call() throws Exception {
        //             p.waitFor();
        //             return p.exitValue();
        //         }
        //     };

        //     // submit the command's call and get the result from a
        //     executeFuture = pool.submit(call);
        //     int timeout = BlockchainConfig.getInstance().getBlockchainRPCChannelTimeout();       
        //     int exitCode = executeFuture.get(timeout, TimeUnit.MILLISECONDS);
        //     if(exitCode == 0){

        //     }
        //     String raw = outputGobbler.getContent();
        //     return raw;
        // } catch(InterruptedException | ExecutionException | java.util.concurrent.TimeoutException e){
        //     log.error(e.getMessage());
        //     throw new IOException(e);
        // }finally {
        //     if (executeFuture != null) {
        //         try {
        //             executeFuture.cancel(true);
        //         } catch (Exception ignore) {
        //             ignore.printStackTrace();
        //         }
        //     }
        //     if (pIn != null) {
        //         this.closeQuietly(pIn);
        //         if (outputGobbler != null && !outputGobbler.isInterrupted()) {
        //             outputGobbler.interrupt();
        //         }
        //     }
        //     if (pErr != null) {
        //         this.closeQuietly(pErr);
        //         if (errorGobbler != null && !errorGobbler.isInterrupted()) {
        //             errorGobbler.interrupt();
        //         }
        //     }
        //     if (process != null) {
        //         process.destroy();
        //     }
        // }
    }

    private void closeQuietly(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
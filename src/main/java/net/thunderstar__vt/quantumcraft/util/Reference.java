package net.thunderstar__vt.quantumcraft.util;

import com.mojang.logging.LogUtils;
import net.thunderstar__vt.quantumcraft.Config;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Reference {
    public static final String MOD_ID = "quantumcraft";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static ExecutorService machineProcessingThreadPool;


    public static void Setup() {
        machineProcessingThreadPool = Executors.newFixedThreadPool(Config.MACHINE_PROCESSING_THREADS.getAsInt());

        LOGGER.info("Created machine processing thread pool with size {}", Config.MACHINE_PROCESSING_THREADS.getAsInt());
    }
}

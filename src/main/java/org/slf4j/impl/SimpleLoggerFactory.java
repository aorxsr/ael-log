package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SimpleLoggerFactory implements ILoggerFactory {
    ConcurrentHashMap<String, Logger> logs = null;

    public SimpleLoggerFactory() {
        this.logs = new ConcurrentHashMap();
        SimpleLogger.lazyInit();
    }

    public Logger getLogger(String name) {
        Logger logger = (Logger)this.logs.get(name);
        if (null != logger) {
            return logger;
        } else {
            Logger newLogger = new SimpleLogger(name);
            Logger old = (Logger)this.logs.putIfAbsent(name, newLogger);
            return (Logger)(null == old ? newLogger : old);
        }
    }
}

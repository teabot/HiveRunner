/*
 * Copyright 2013 Klarna AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klarna.hiverunner;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.service.HiveServer;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.List;


/**
 * Test handle to the hive server.
 *
 * Please refer to test class {@link com.klarna.hiverunner.HelloHiveRunner} for usage examples.
 */
public interface HiveShell {

    /**
     * Executes a single query.
     * <p/>
     * May only be called post #start()
     */
    List<String> executeQuery(String script);

    /**
     * Executes a hive script. The script may contain multiple statements delimited by ';'
     * <p/>
     * May only be called post #start()
     */
    void execute(String script);

    /**
     * Direct access to hive client.
     * <p/>
     * May only be called post #start()
     */
    HiveServer.HiveServerHandler getClient();

    /**
     * Start the shell. May only be called once. The test engine will by default call this method,
     * Set {@link com.klarna.hiverunner.annotations.HiveSQL#autoStart()} to false to explicitly control
     * when to start from the test case.
     * <p/>
     * This might be useful for test methods that needs additional setup not catered for with the provided annotations.
     */
    void start();

    /**
     * Set a HiveConf property.
     * <p/>
     * May only be called pre #start()
     */
    void setProperty(String key, String value);

    /**
     * Get the current HiveConf from hive
     */
    HiveConf getHiveConf();

    /**
     * Copy test data into hdfs
     * May only be called pre #start()
     * <p/>
     * {@see com.klarna.hiverunner.MethodLevelResourceTest#resourceLoadingAsFileTest()}
     * and {@see com.klarna.hiverunner.MethodLevelResourceTest#resourceLoadingAsStringTest()}
     */
    void addResource(String targetFile, File sourceFile);

    /**
     * Copy test data into hdfs
     * May only be called pre #start()
     * <p/>
     * {@see com.klarna.hiverunner.MethodLevelResourceTest#resourceLoadingAsFileTest()}
     * and {@see com.klarna.hiverunner.MethodLevelResourceTest#resourceLoadingAsStringTest()}
     */
    void addResource(String targetFile, String data);


    /**
     * Get the test case sand box base dir
     */
    TemporaryFolder getBaseDir();
}

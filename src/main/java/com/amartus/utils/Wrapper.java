/*
 * Copyright 2020 Amartus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amartus.utils;

import com.amartus.utils.cmd.Generate;
import io.airlift.airline.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * @author bartosz.michalik@amartus.com
 */
public class Wrapper {
    private static final Logger log = LoggerFactory.getLogger(Wrapper.class);
    public static void main(String[] args) {
        String version = "1.0";
        Cli.CliBuilder<Runnable> builder =
                Cli.<Runnable>builder("openapi-generator-wrapper-cli")
                        .withDescription(
                                String.format(
                                        Locale.ROOT,
                                        "OpenAPI generator wrapper CLI (version %s).",
                                        version))
                        .withDefaultCommand(Help.class)
                        .withCommands(
                                Generate.class,
                                Help.class
                        );

        try {
            builder.build().parse(args).run();
        } catch (Exception e) {
            log.error("Error:", e);
            System.out.println(e.getMessage());
        }
    }
}

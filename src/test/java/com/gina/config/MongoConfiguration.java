package com.gina.config;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class MongoConfiguration {

    @Bean
    public Mongo mongo() throws IOException {
        System.setProperty("DB.TRACE", "true");
        return new EmbeddedMongoBuilder().version(Version.V2_4_5).bindIp("127.0.0.1").port(allocateRandomPort()).build();
    }

    public static int allocateRandomPort() {
        try {
            ServerSocket server = new ServerSocket(0);
            int port = server.getLocalPort();
            server.close();
            return port;
        } catch (IOException e) {
            throw new RuntimeException("Failed to acquire a random free port", e);
        }
    }
}

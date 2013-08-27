
package com.kimboo.giffy.server.rest;

import org.springframework.http.ResponseEntity;

import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.utils.Utils;

public class RestFactory {

    public static Gif[] getPopularGifs(RestClient restClient) throws Exception {
        ResponseEntity<Gif[]> response = restClient.getForEntity(
                Utils.getRestResource("gif/popular/"), Gif[].class);
        return response.getBody();
    }

}

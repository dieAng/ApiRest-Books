package com.example.apirestbooks.responses;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Response rest.
 */
@Getter
public class ResponseRest {
    private ArrayList<Map<String, String>> metadata = new ArrayList<>();

    /**
     * Sets metadata.
     *
     * @param tipo   the tipo
     * @param codigo the codigo
     * @param data   the date
     */
    public void setMetadata(String tipo, String codigo, String data) {
        HashMap<String, String> map = new HashMap<>();

        map.put("tipo", tipo);
        map.put("codigo", codigo);
        map.put("data", data);
        
        metadata.add(map);
    }
}

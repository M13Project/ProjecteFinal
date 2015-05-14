package com.example.aleix.projectefinal.Controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Michal.hostienda on 13/05/2015.
 */
public class MyJasonEntityConverter {

    public MyJasonEntityConverter() {

    }

    public static List<Map<String, Object>> formatJsonInput(String rawJsonInput) {
        List<Map<String, Object>> listOfEntities = new ArrayList();
        Map<String, Object> mappedAttributes = null;
        try {
            JSONObject jsonResponse = new JSONObject(rawJsonInput);
            JSONArray array = jsonResponse.getJSONArray("value");
            for (int j = 0; j < array.length(); j++) {
                JSONObject jsonChildNode = array.getJSONObject(j);
                mappedAttributes = new TreeMap<>();
                for (int k = 0; k < jsonChildNode.names().length(); k++) {
                    String key = jsonChildNode.names().getString(k);
                    Object value = jsonChildNode.get(key);
                    /*Aquest if no se si fara falta. El contingut del if, sí*/
                    if (!key.contains("odata")) {
                        mappedAttributes.put(key, value);
                    }
                    /**/
                }
                listOfEntities.add(mappedAttributes);
            }
        } catch (Exception e) {
            System.out.println("ERROR IN formatJson... " + e.getMessage());
        }
        return listOfEntities;
    }

    //Converteix una llista d'objectes en forma d'un map en una llista d'objectes d'una classe determinada
    public static <T> List<T> getObjectsFromFormattedJson(Class<T> objectClass, List<Map<String, Object>> listOfFormattedObjects) {
        List<T> listOfEntities = new ArrayList();
        Field[] fieldsOfEntity = objectClass.getDeclaredFields();
        Method[] methodsOfEntity = objectClass.getDeclaredMethods();
        T entity = null;
        Map<String, Object> entityInMapFormat = null;
        Iterator<String> mapIterator = null;

        try {
            for (int i = 0; i < listOfFormattedObjects.size(); i++) {
                entity = objectClass.newInstance();
                entityInMapFormat = listOfFormattedObjects.get(i);
                mapIterator = entityInMapFormat.keySet().iterator();
                while (mapIterator.hasNext()) {
                    String key = mapIterator.next();
                    Object value = entityInMapFormat.get(key);
                    for (int j = 0; j < methodsOfEntity.length; j++) {
                        Method method = methodsOfEntity[j];
                        if (method.getName().contains("set") && method.getName().toLowerCase().contains(key.toLowerCase())) {
                            method.invoke(entity, value);
                        }
                    }
                }
                listOfEntities.add(entity);
            }
        } catch (Exception e) {
            System.out.println("Error in getObjectsFromFormattedJson: " + e.getMessage());
        }
        return listOfEntities;
    }

    //Converteix un objecte d'una classe coneguda en un string en format que pot ser enviat al servidor
    public static <T> String getJsonObjectFromEntity(Class<T> objectClass, T objectToTransform) {
        Field[] fieldsOfEntity = objectClass.getDeclaredFields();
        Method[] methodsOfEntity = objectClass.getDeclaredMethods();
        JSONObject stringJson = new JSONObject();
        try {
            for (int i = 0; i < fieldsOfEntity.length; i++) {
                Field field = fieldsOfEntity[i];
                for (int j = 0; j < methodsOfEntity.length; j++) {
                    Method method = methodsOfEntity[j];
                    if (method.getName().toLowerCase().contains("get") && method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                        stringJson.put(field.getName(), method.invoke(objectToTransform, null));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return stringJson.toString();
    }

    public static <T> String getJsonObjectsFromEntitiesWithKnowsClass(Class<T> objectClass, List<T> objectsToTransform) {
        Field[] fieldsOfEntity = objectClass.getDeclaredFields();
        Method[] methodsOfEntity = objectClass.getDeclaredMethods();
        JSONObject stringJson = new JSONObject();
        JSONArray stringJsonArray = new JSONArray();
        try {
            for (int k = 0; k < objectsToTransform.size(); k++) {
                for (int i = 0; i < fieldsOfEntity.length; i++) {
                    Field field = fieldsOfEntity[i];
                    for (int j = 0; j < methodsOfEntity.length; j++) {
                        Method method = methodsOfEntity[j];
                        if (method.getName().toLowerCase().contains("get") && method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                            stringJson.put(field.getName(), method.invoke(objectsToTransform.get(k), null));
                        }
                    }
                }
                stringJsonArray.put(stringJson);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return stringJsonArray.toString();
    }
}

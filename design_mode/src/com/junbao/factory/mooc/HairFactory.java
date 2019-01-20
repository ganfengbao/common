package com.junbao.factory.mooc;

import com.junbao.factory.mooc.util.PropertiesReader;

import java.util.Map;

/**
 * 发型工厂
 */
public class HairFactory {
    public HairInterface getHair(String hair) {
        if ("left".equals(hair)) {
            return new LeftHair();
        } else if ("right".equals(hair)) {
            return new RightHair();
        }

        return null;
    }

    /**
     * 根据类的名称来生产对象
     * @param className
     * @return
     */
    public HairInterface getHairByClassKey(String key) {
        try {
            Map<String, String> map = new PropertiesReader().getProperties();
            HairInterface hairInterface = (HairInterface) Class.forName(map.get(key)).newInstance();
            return hairInterface;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

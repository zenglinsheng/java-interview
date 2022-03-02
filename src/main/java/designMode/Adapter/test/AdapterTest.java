package designMode.adapter.test;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/3/2 14:03
 * @Description:
 */
public class AdapterTest {

    @Test
    public void dataTypeTest(){
        DataJson dataJson = () -> {
            System.out.println("get json");
            return "json";
        };

        DataXml dataXml = () -> {
            System.out.println("get xml");
            return "xml";
        };

//        DataJson dataTypeJson = new DataTypeConversion(dataXml);
//        String json = dataTypeJson.getJson();
//        System.out.println(json);
//        DataXml dataTypeXml = new DataTypeConversion(dataJson);
//        String xml = dataTypeXml.getXml();
//        System.out.println(xml);

        DataTypeConversion dataTwoType = new DataTypeConversion(dataJson, dataXml);
        String json = dataTwoType.getJson();
        System.out.println(json);
        String xml = dataTwoType.getXml();
        System.out.println(xml);

    }

}

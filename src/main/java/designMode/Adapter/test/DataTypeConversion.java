package designMode.adapter.test;

/**
 * @Auther: zls
 * @Date: 2022/3/2 14:00
 * @Description:
 */
public class DataTypeConversion implements DataJson, DataXml {

    private DataJson dataJson;
    private DataXml dataXml;

    public DataTypeConversion(DataJson dataJson) {
        this.dataJson = dataJson;
    }

    public DataTypeConversion(DataXml dataXml) {
        this.dataXml = dataXml;
    }

    public DataTypeConversion(DataJson dataJson, DataXml xmlConversion) {
        this.dataJson = dataJson;
        this.dataXml = xmlConversion;
    }

    @Override
    public String getJson() {
        String xml = dataXml.getXml();
        return xml + " conversion to json";
    }

    @Override
    public String getXml() {
        String json = dataJson.getJson();
        return json + " conversion to xml";
    }

}

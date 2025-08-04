package org.portfolio.util;

import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String path = "src/test/resources/loginData.xlsx";
        return ExcelUtils.getExcelData(path, "Hoja1");
    }

    @DataProvider(name = "signupData")
    public Object[][] signupData() {
        String path = "src/test/resources/signupData.xlsx";
        List<Map<String, String>> data = ExcelUtils.getExcelDataAsListOfMap(path, "Hoja1");

        Object[][] result = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }
}

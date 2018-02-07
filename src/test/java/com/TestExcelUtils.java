package com;

import com.github.yt.excel.util.ExcelUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExcelUtils {

    public static List<TestBean> getTestList() {
        List<TestBean> list = new ArrayList();
        TestBean testBean1 = new TestBean("name1", 123, new Date(), new BigDecimal(1111));
        list.add(testBean1);
        TestBean testBean2 = new TestBean("name2", 456, new Date(), new BigDecimal(2222));
        list.add(testBean2);
        return list;
    }

    @Test
    public void testCreateExcel() {
        File file = new File("D://test.xls");
        try {
            OutputStream stream = ExcelUtils.createExcel(new FileOutputStream(file), TestBean.class, getTestList());
            stream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

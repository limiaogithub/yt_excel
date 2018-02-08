# yt_excel
<h3>介绍</h3>
相信大多数公司都基于poi封装了生成和读取excel的工具，yt_excel就是其中的一个实现，但是他比较简单，基于注解，支持表达式转义等特点，值得一用。</br>
您可以下载https://github.com/limiaogithub/yt_mybatis_example 直接运行示例代码。</br>

<h3>特性</h3>
1.基于apache poi</br>
2.注解的方式进行配置</br>
3.支持多sheet</br>
4.使用简单</br>

<h3>准备</h3>
1.idea开发工具</br>
2.大约10分钟</br>

<h3>集成</h3>
1.引入maven依赖</br>
<pre>
&lt;dependency&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.github.limiaogithub&lt;/groupId&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;yt_excel&lt;/artifactId&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;1.0.0&lt;/version&gt;
&lt;/dependency&gt;
</pre>

2.编写你的Pojo类，这里@ImportExcel指导入的字段，@ExportExcel指导出的字段，属性一看就懂了。</br>
<pre>
public class TestBean {

    private int id;

    @ExportExcel(title = "姓名", order = 1)
    @ImportExcel(order = 1)
    private String name;

    @ExportExcel(title = "密码", order = 2)
    @ImportExcel(order = 2)
    private int password;

    @ExportExcel(title = "日期", order = 3)
    @ImportExcel(order = 3)
    private Date date;

    @ExportExcel(title = "BigDecimal", order = 4)
    @ImportExcel(order = 4)
    private BigDecimal big;

    public TestBean() {
    }

    public TestBean(String name, int password, Date date, BigDecimal big) {
        this.name = name;
        this.password = password;
        this.date = date;
        this.big = big;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBig() {
        return big;
    }

    public void setBig(BigDecimal big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + password +
                ", date=" + date +
                ", big=" + big +
                '}';
    }
}
</pre>

3.导出</br>
<pre>
@ApiOperation(value = "export")
@RequestMapping(value = "/export", method = RequestMethod.GET)
public void export() throws Exception {
    ServletOutputStream out = response.getOutputStream();
    String fileName = EncodeUtils.toUtf8String(request.getHeader("User-Agent"), "导出文件.xls");
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    response.setContentType("application/octet-stream; charset=utf-8");

    ExcelUtils.createExcel(out, TestBean.class, getTestList());

    out.flush();
}

private static List<TestBean> getTestList() {
    List<TestBean> list = new ArrayList<>();
    TestBean testBean1 = new TestBean("name1", 123, new Date(), new BigDecimal(1111));
    list.add(testBean1);
    TestBean testBean2 = new TestBean("name2", 456, new Date(), new BigDecimal(2222));
    list.add(testBean2);
    return list;
}

</pre>

4.导入</br>
<pre>
@ApiOperation(value = "import")
@RequestMapping(value = "/import", method = RequestMethod.GET)
public void import1(MultipartFile file) throws Exception {
    File file1=new File("D:\\导出文件.xls");
    
    List<TestBean> list = ExcelUtils.readExcel(file1, TestBean.class, new ExcelConfig().setStartRow(2));
    
    System.out.println(list.size());
    for (TestBean testBean : list) {
        System.out.println(testBean.toString());
    }
}
</pre>

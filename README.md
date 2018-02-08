# yt_excel
以注解的方式完成excel的导入导出，一行代码即可，基于poi</br>
<h3>集成</h3>
1.编写你的Pojo类</br>
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

2.导出</br>
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
</pre>

3.导入</br>
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
